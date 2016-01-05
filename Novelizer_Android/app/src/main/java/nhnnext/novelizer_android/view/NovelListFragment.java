package nhnnext.novelizer_android.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import nhnnext.novelizer_android.Entity.NovelSummaries;
import nhnnext.novelizer_android.Entity.NovelSummary;
import nhnnext.novelizer_android.R;
import nhnnext.novelizer_android.controller.ViewerActivity;
import nhnnext.novelizer_android.network.Proxy;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NovelListFragment extends Fragment {
    private List<NovelSummary> novelSummaries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_novel_list, container, false);
        novelSummaries = new ArrayList<>();

        GridView gridView = (GridView) root.findViewById(R.id.novel_list_grid_view);

        NovelListAdapter adapter = new NovelListAdapter(getActivity(), R.layout.novel_item, novelSummaries);
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ViewerActivity.class);
                intent.putExtra("novelId", novelSummaries.get(position).getNovelId());
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                novelSummaries = getNovelSummaryData();

            }

        });

        return root;
    }

    private List<NovelSummary> getNovelSummaryData() {
        Proxy proxy = new Proxy();
        Log.i("test", "1");
        String projectsJson = proxy.getProjectsByJson(1, new Callback<JsonArray>() {

            @Override
            public void success(JsonArray jsonArray, Response response) {
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {

                    reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                    String line;

                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("Retrofit success", sb.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Retrofit error", error.toString());
            }
        });

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NovelSummaries novelSummaries = objectMapper.readValue(projectsJson, NovelSummaries.class);
            return novelSummaries.getNovelSummaries();
        } catch (Exception e) {
            Log.e("getNovel Error", "" + e);
            return null;
        }

    }

//    public void onDestroyView() {
//        super.onDestroyView();
//        if (v != null) {
//            ViewGroup parent = (ViewGroup) v.getParent();
//            if (parent != null) {
//                parent.removeView(v);
//            }
//        }
//    }
}
