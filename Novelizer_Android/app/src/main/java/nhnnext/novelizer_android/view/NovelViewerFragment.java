package nhnnext.novelizer_android.view;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import nhnnext.novelizer_android.Entity.Action;
import nhnnext.novelizer_android.Entity.BackgroundAction;
import nhnnext.novelizer_android.Entity.Block;
import nhnnext.novelizer_android.Entity.CharacterAction;
import nhnnext.novelizer_android.Entity.Novel;

import nhnnext.novelizer_android.Entity.Scene;
import nhnnext.novelizer_android.Entity.TextAction;
import nhnnext.novelizer_android.R;


public class NovelViewerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_novel_viewer, container, false);

        String novelId = getActivity().getIntent().getStringExtra("novelId");
        Novel novel = getNovelData(novelId);
        Toast.makeText(getActivity(), novelId, Toast.LENGTH_LONG).show();
        return root;
    }


    private Novel getNovelData(String novelId){
        /* 이후 model에서 novelId를 키로하여 novel의 data를 불러오는 방식 구현 */

        /* Dummy Data */
        /* test action data 생성 */
        Map<String, Action> actions = new HashMap<String, Action>();
        Bitmap backgroundImg = ((BitmapDrawable) getResources().getDrawable(R.mipmap.space)).getBitmap();
        Bitmap boy = ((BitmapDrawable) getResources().getDrawable(R.mipmap.boy)).getBitmap();
        Bitmap girl = ((BitmapDrawable) getResources().getDrawable(R.mipmap.girl)).getBitmap();

        actions.put("Background", new BackgroundAction(0, "Background", backgroundImg));
        actions.put("Character", new CharacterAction(1, "Character", new int[]{10, 10}, boy));
        actions.put("Character", new CharacterAction(2, "Character", new int[]{60, 10}, girl));
        actions.put("Text", new TextAction(3, "Text", "Hello World"));
        /* test block data 생성 */
        List<Block> blocks = new ArrayList<Block>();
        Block block = new Block(0, actions);

        /* test scene data 생성 */
        List<Scene> scenes = new ArrayList<Scene>();
        Scene scene = new Scene(0, blocks);

        Novel novel = new Novel("testNovelId", scenes);

        return novel;
    }

}
