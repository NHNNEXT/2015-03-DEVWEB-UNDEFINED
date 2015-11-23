package nhnnext.novelizer_android.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import nhnnext.novelizer_android.Entity.NovelSummary;
import nhnnext.novelizer_android.R;

public class NovelListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_novel_list, container, false);
        ArrayList<NovelSummary> novelSummarys = new ArrayList<NovelSummary>();

        /* Dummy data */
        NovelSummary dummy1 = new NovelSummary("novel1 name", "novel1 image path", "novel1 id");
        NovelSummary dummy2 = new NovelSummary("novel2 name", "novel2 image path", "novel2 id");
        NovelSummary dummy3 = new NovelSummary("novel3 name", "novel3 image path", "novel3 id");
        NovelSummary dummy4 = new NovelSummary("novel4 name", "novel4 image path", "novel4 id");
        NovelSummary dummy5 = new NovelSummary("novel5 name", "novel5 image path", "novel5 id");
        novelSummarys.add(dummy1);
        novelSummarys.add(dummy2);
        novelSummarys.add(dummy3);
        novelSummarys.add(dummy4);
        novelSummarys.add(dummy5);

        GridView gridView = (GridView) root.findViewById(R.id.novel_list_grid_view);
        NovelListAdapter adapter = new NovelListAdapter(getActivity(), R.layout.novel_item, novelSummarys);
        gridView.setAdapter(adapter);

        return root;
    }
}
