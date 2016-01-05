package nhnnext.novelizer_android.view;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nhnnext.novelizer_android.Entity.NovelSummary;
import nhnnext.novelizer_android.R;

/**
 * Created by Henry on 2015. 11. 23..
 */
public class NovelListAdapter extends ArrayAdapter<NovelSummary> {

    private List<NovelSummary> items;
    private Context context;
    private int layoutResourceId;

    public NovelListAdapter(Context context, int layoutResourceId, List<NovelSummary> items) {
        super(context, layoutResourceId, items);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
        Log.e("NovelListAdapter","0");

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            Log.e("NovelListAdapter","1");
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            convertView = layoutInflater.inflate(layoutResourceId, parent, false);
        }

        TextView novelName = (TextView) convertView.findViewById(R.id.novel_name);;
        novelName.setText(items.get(position).getNovelName());

        Log.e("NovelListAdapter", "3");
        return convertView;
    }
}
