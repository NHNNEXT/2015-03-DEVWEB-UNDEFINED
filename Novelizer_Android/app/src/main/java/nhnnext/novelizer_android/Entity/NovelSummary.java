package nhnnext.novelizer_android.Entity;

/**
 * Created by Henry on 2015. 11. 23..
 */
public class NovelSummary {

    private String novelName;
    private String novelImagePath;
    private String novelId;

    public NovelSummary(String novelName, String novelImagePath, String novelId){
        this.novelName = novelName;
        this.novelImagePath = novelImagePath;
        this.novelId = novelId;
    }

    public String getNovelName(){
        return novelName;
    }
    public String getNovelId(){
        return novelId;
    }
}
