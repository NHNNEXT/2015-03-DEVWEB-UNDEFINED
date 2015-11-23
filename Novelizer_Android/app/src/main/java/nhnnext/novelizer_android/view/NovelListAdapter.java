package nhnnext.novelizer_android.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nhnnext.novelizer_android.Entity.NovelSummary;
import nhnnext.novelizer_android.R;

/**
 * Created by Henry on 2015. 11. 23..
 */
public class NovelListAdapter extends ArrayAdapter<NovelSummary>{

    private ArrayList<NovelSummary> items;
    private Context context;
    private int layoutResourceId;

    public NovelListAdapter(Context context, int layoutResourceId, ArrayList<NovelSummary> items){
        super(context, layoutResourceId, items);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
    }

    static class ViewHolder{
        public ImageView novelImage = null;
        public TextView novelName = null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            convertView = layoutInflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.novelName = (TextView) convertView.findViewById(R.id.novel_name);
            /* novel Image에 대한 부분은 이후에 구현 */
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.novelName.setText(items.get(position).getNovelName());

        return convertView;
    }
}
