package nhnnext.novelizer_android.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nhnnext.novelizer_android.Entity.NovelSummaries;
import nhnnext.novelizer_android.Entity.NovelSummary;
import nhnnext.novelizer_android.R;
import nhnnext.novelizer_android.controller.ViewerActivity;
import nhnnext.novelizer_android.network.Proxy;

public class NovelListFragment extends Fragment {
    private List<NovelSummary> novelSummaries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_novel_list, container, false);

        novelSummaries = getNovelSummaryData();

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

        return root;
    }

    private List<NovelSummary> getNovelSummaryData(){
        Proxy proxy = new Proxy();

        String projectsJson = proxy.getProjectsByJson(1);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NovelSummaries novelSummaries = objectMapper.readValue(projectsJson,NovelSummaries.class);
            return novelSummaries.getNovelSummaries();
        } catch (Exception e) {
            Log.e("NovelSummaries Error", "" + e);
            throw new RuntimeException();
        }

    }
}
