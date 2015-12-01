package nhnnext.novelizer_android.view;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.widget.TextView;
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
    private Novel novel;
    private int curBlockId;
    private int curSceneId;
    private List<Scene> scenesOfCurNovel;
    private List<Block> blocksOfCurScene;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_novel_viewer, container, false);

        String novelId = getActivity().getIntent().getStringExtra("novelId");
        novel = getNovelData(novelId);
        //Toast.makeText(getActivity(), novelId, Toast.LENGTH_LONG).show();

        return root;
    }

    /* fragment에서 findViewById를 이용할 때는 activty와의 생명주기 관계를 고려해야한다 */
    @Override
    public void onResume() {
        super.onResume();

        startVisualNovel(novel);
    }

    private void startVisualNovel(Novel novel){
        /* first Scene & Block ID setting */
        curBlockId = 0;
        curSceneId = 0;

        scenesOfCurNovel = novel.getScenes();
        blocksOfCurScene = scenesOfCurNovel.get(curSceneId).getBlocks();

        screenSetting();
        getView().findViewById(R.id.next_btn).setOnClickListener(new RunViewer());
    }

    /* Click 이벤트를 받아 viewer를 running 시켜주는 로직 */
    /* 이후 controller 쪽으로 로직을 이동시키는게 좋지 않을까 생각중 */
    private class RunViewer implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int nextBlockId = blocksOfCurScene.get(curBlockId).getNextBlockId();
            /* Next Block이 있는 경우의 처리 */
            if(nextBlockId != -1) {
                /* curBlockId 갱신 */
                curBlockId = nextBlockId;
                showNextBlock(nextBlockId);
            }else{
                int nextSceneId = scenesOfCurNovel.get(curSceneId).getNextSceneId();
                /* Next Block이 없고 Next Scene은 있는 경우의 처리 */
                if(nextSceneId != -1){
                    curSceneId = nextSceneId;
                    showNextScene(nextSceneId);
                }
                /* Next Block이 없고 Next Scene도 없는 경우의 처리 */
                else finishNovel();
            }
        }

        private void finishNovel(){
            Toast.makeText(getActivity(), "Game is over", Toast.LENGTH_LONG).show();
        }

        private void showNextScene(int sceneId){

        }

        private void showNextBlock(int blockId){
            // 이후 리펙토링할 항목 : curBlockId, curSceneId를 member variable이 아니라 parameter로 전달하는게 더 좋지 않을까?
            //Block newBlock = blocksOfCurScene.get(blockId);
            screenSetting();
        }
    }

    private void screenSetting(){
        Block block = blocksOfCurScene.get(curBlockId);
        BackgroundAction backgroundAction = (BackgroundAction)block.getActions().get("Background");
        TextAction textAction = (TextAction)block.getActions().get("Text");
        CharacterAction characterAction = (CharacterAction)block.getActions().get("Character");

        ((ImageView) getView().findViewById(R.id.background_image)).setImageBitmap(backgroundAction.getImg());
        ((TextView) getView().findViewById(R.id.caption)).setText(textAction.getText());

        int[] characterPosition = characterAction.getPosition();
        ImageView character = new ImageView(getActivity());
        character.setImageBitmap(characterAction.getImg());
        ((FrameLayout) getView()).addView(character);
    }

    private Novel getNovelData(String novelId){
        /* 이후 model에서 novelId를 키로하여 novel의 data를 불러오는 방식 구현 */

        /* Dummy Data */
        /* test action data 생성 */
            //Block 1의 Action
        Map<String, Action> actions = new HashMap<>();
        Bitmap backgroundImg = ((BitmapDrawable) getResources().getDrawable(R.mipmap.space)).getBitmap();
        Bitmap boy = ((BitmapDrawable) getResources().getDrawable(R.mipmap.boy)).getBitmap();
        Bitmap girl = ((BitmapDrawable) getResources().getDrawable(R.mipmap.girl)).getBitmap();

        actions.put("Background", new BackgroundAction(0, "Background", backgroundImg));
        actions.put("Character", new CharacterAction(1, "Character", new int[]{0, 0}, boy));
        actions.put("Text", new TextAction(2, "Text", "안녕 영희야?"));
            //Block 2의 Action
        Map<String, Action> actions2 = new HashMap<String, Action>();
        actions2.put("Background", new BackgroundAction(3, "Background", backgroundImg));
        actions2.put("Character", new CharacterAction(4, "Character", new int[]{60, 10}, girl));
        actions2.put("Text", new TextAction(5, "Text", "ㅇㅇㅇㅇㅇㅇㅇ"));

        /* test block data 생성 */
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(0, 1, actions);
        blocks.add(block);
        Block block2 = new Block(1, -1, actions2);
        blocks.add(block2);
        /* test scene data 생성 */
        List<Scene> scenes = new ArrayList<>();
        Scene scene = new Scene(0, -1, blocks);
        scenes.add(scene);

        Novel novel = new Novel("testNovelId", scenes);

        return novel;
    }

}
