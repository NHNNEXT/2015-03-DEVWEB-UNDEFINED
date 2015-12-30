package nhnnext.novelizer_android.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import nhnnext.novelizer_android.Entity.NovelSummary;
import nhnnext.novelizer_android.R;
import nhnnext.novelizer_android.controller.ViewerActivity;

public class NovelListFragment extends Fragment {
    private ArrayList<NovelSummary> novelSummaries;

    interface Listener{

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_novel_list, container, false);
        novelSummaries = new ArrayList<NovelSummary>();

        getNovelSummaryData(novelSummaries);

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

    private void getNovelSummaryData(ArrayList<NovelSummary> novelSummaries){
        /* novel summary data를 받아오는 model부분 후에 추가 구현  */
        /* Dummy data */
        NovelSummary dummy1 = new NovelSummary("novel1 name", "novel1 id");
        NovelSummary dummy2 = new NovelSummary("novel2 name", "novel2 id");
        NovelSummary dummy3 = new NovelSummary("novel3 name", "novel3 id");
        NovelSummary dummy4 = new NovelSummary("novel4 name", "novel4 id");
        NovelSummary dummy5 = new NovelSummary("novel5 name", "novel5 id");
        novelSummaries.add(dummy1);
        novelSummaries.add(dummy2);
        novelSummaries.add(dummy3);
        novelSummaries.add(dummy4);
        novelSummaries.add(dummy5);
    }
}
