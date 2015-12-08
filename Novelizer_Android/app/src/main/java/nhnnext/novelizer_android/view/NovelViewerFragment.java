package nhnnext.novelizer_android.view;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
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
    private Map<String, ImageView> characters;


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
        characters = new HashMap<>();
        screenSetting();
        getView().setOnClickListener(new RunViewer());
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
                    clearScreen();
                    showNextScene();
                }
                /* Next Block이 없고 Next Scene도 없는 경우의 처리 */
                else finishNovel();
            }
        }

        private void clearScreen(){
            for(String key : characters.keySet()){
                ImageView character = characters.get(key);
                character.setImageBitmap(null);
            }
        }


        private void finishNovel(){
            Toast.makeText(getActivity(), "Game is over", Toast.LENGTH_LONG).show();
        }

        private void showNextScene(){
            blocksOfCurScene = scenesOfCurNovel.get(curSceneId).getBlocks();
            curBlockId = 0;
            screenSetting();
        }

        private void showNextBlock(int blockId){
            // 이후 리펙토링할 항목 : curBlockId, curSceneId를 member variable이 아니라 parameter로 전달하는게 더 좋지 않을까?
            //Block newBlock = blocksOfCurScene.get(blockId);
            screenSetting();
        }
    }

    private void screenSetting() {
        Block block = blocksOfCurScene.get(curBlockId);
        List<Action> actions = block.getActions();

        for(Action action : actions){
            switch(action.getType()){
                case "Background" :
                    setBackgroundAction((BackgroundAction) action);
                    break;
                case "Character" :
                    setCharacterAction((CharacterAction) action);
                    break;
                case "Text" :
                    setTextAction((TextAction) action);
                    break;
                default :
                    /* TODO : action type unvalid에 대한 예외처리 해주기 */
                    break;
            }
        }
    }

    private void setBackgroundAction(BackgroundAction action){
        getView().findViewById(R.id.background_layout).setBackground(action.getImg());
    }

    private void setCharacterAction(CharacterAction action){

        if(action.getOption() == "in") {
            ImageView character = new ImageView(getActivity());
            character.setImageDrawable(action.getImg());
            character.setLayoutParams(new ViewGroup.LayoutParams(400, 800));
            ((RelativeLayout)getView().findViewById(R.id.background_layout)).addView(character);

            /* characters는 후에 character를 사라지게 할 때 이미지뷰를 찾기 위한 용도로 사용 */
            characters.put(action.getCharacterId(), character);
        }else if(action.getOption() == "out"){
            ImageView character = characters.get(action.getCharacterId());
            character.setImageBitmap(null);
        }else{
            /* TODO : Unvalid Character option Exception 처리하기 */
        }
    }

    private void setTextAction(TextAction action){
        ((TextView) getView().findViewById(R.id.caption)).setText(action.getText());
    }
    private Novel getNovelData(String novelId){
        /* 이후 model에서 novelId를 키로하여 novel의 data를 불러오는 방식 구현 */

        /* Dummy Data */
        /* test action data 생성 */
        //Scene1
            //Block 1의 Action
        List<Action> actions1 = new ArrayList<>();
        Drawable backgroundImg = ((BitmapDrawable) getResources().getDrawable(R.mipmap.space));
        Drawable boy = ((BitmapDrawable) getResources().getDrawable(R.mipmap.boy));
        Drawable girl = ((BitmapDrawable) getResources().getDrawable(R.mipmap.girl));

        actions1.add(new BackgroundAction(0, "Background", backgroundImg, "in"));
        actions1.add(new CharacterAction(1, "Character", new int[]{10, 10}, boy, "철수", "in"));
        actions1.add(new TextAction(2, "Text", "안녕 영희야?"));
            //Block 2의 Action
        List<Action> actions2 = new ArrayList<>();
        actions2.add(new CharacterAction(3, "Character", new int[]{0, 0}, boy, "철수","out"));
        actions2.add(new CharacterAction(4, "Character", new int[]{400, 10}, girl, "영희", "in"));
        actions2.add(new TextAction(5, "Text", "어, 철수야 안녕! \n무사히 두번째 블럭으로 넘어왔구나. 여긴 무슨 일이니?"));
            //Block 3의 Action
        List<Action> actions3 = new ArrayList<>();
        actions3.add(new CharacterAction(6, "Character", new int[]{0, 0}, girl, "영희", "out"));
        actions3.add(new CharacterAction(7, "Character", new int[]{10, 10}, boy, "철수", "in"));
        actions3.add(new TextAction(8, "Text", "널 없애러 왔어"));
            //Block 4의 Action
        List<Action> actions4 = new ArrayList<>();
        actions4.add(new CharacterAction(9, "Character", new int[]{0, 0}, boy, "철수", "out"));
        actions4.add(new CharacterAction(10, "Character", new int[]{10, 10}, girl, "영희", "in"));
        actions4.add(new TextAction(11, "Text", "그래 한번 겨뤄 보자"));

        /* test block data 생성 */
        List<Block> blocks1 = new ArrayList<>();
        Block block1 = new Block(0, 1, actions1);
        blocks1.add(block1);
        Block block2 = new Block(1, 2, actions2);
        blocks1.add(block2);
        Block block3 = new Block(2, 3, actions3);
        blocks1.add(block3);
        Block block4 = new Block(3, -1, actions4);
        blocks1.add(block4);

        //Scene2
            //Block 1의 Action
        List<Action> actions5 = new ArrayList<>();
        Drawable backgroundImg2 = ((BitmapDrawable) getResources().getDrawable(R.mipmap.fire));
        Drawable boy2 = ((BitmapDrawable) getResources().getDrawable(R.mipmap.boy));
        Drawable girl2 = ((BitmapDrawable) getResources().getDrawable(R.mipmap.girl));

        actions5.add(new BackgroundAction(12, "Background", backgroundImg2, "in"));
        actions5.add(new CharacterAction(13, "Character", new int[]{10, 10}, boy2, "철수", "in"));
        actions5.add(new TextAction(14, "Text", "이제 두번째 씬이야"));
            //Block 2의 Action
        List<Action> actions6 = new ArrayList<>();
        actions6.add(new CharacterAction(15, "Character", new int[]{0, 0}, boy2, "철수","out"));
        actions6.add(new CharacterAction(16, "Character", new int[]{400, 10}, girl2, "영희", "in"));
        actions6.add(new TextAction(17, "Text", "오 드디어?"));
            //Block 3의 Action
        List<Action> actions7 = new ArrayList<>();
        actions7.add(new CharacterAction(18, "Character", new int[]{0, 0}, girl2, "영희", "out"));
        actions7.add(new CharacterAction(19, "Character", new int[]{10, 10}, boy2, "철수", "in"));
        actions7.add(new TextAction(20, "Text", "응. 여까기지 오느라 삽질 많이 했어."));
            //Block 4의 Action
        List<Action> actions8 = new ArrayList<>();
        actions8.add(new CharacterAction(21, "Character", new int[]{0, 0}, boy2, "철수", "out"));
        actions8.add(new CharacterAction(22, "Character", new int[]{10, 10}, girl2, "영희", "in"));
        actions8.add(new TextAction(23, "Text", "니가 그렇지 뭐"));

        /* test block data 생성 */
        List<Block> blocks2 = new ArrayList<>();
        Block block5 = new Block(0, 1, actions5);
        blocks2.add(block5);
        Block block6 = new Block(1, 2, actions6);
        blocks2.add(block6);
        Block block7 = new Block(2, 3, actions7);
        blocks2.add(block7);
        Block block8 = new Block(3, -1, actions8);
        blocks2.add(block8);


        /* test scene data 생성 */
        List<Scene> scenes = new ArrayList<>();
        Scene scene1 = new Scene(0, 1, blocks1);
        scenes.add(scene1);
        Scene scene2 = new Scene(1, -1, blocks2);
        scenes.add(scene2);


        Novel novel = new Novel("testNovelId", scenes);

        return novel;
    }

}
