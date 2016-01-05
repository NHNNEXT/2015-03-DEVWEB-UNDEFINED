package nhnnext.novelizer_android.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Henry on 2015. 11. 23..
 */

public class NovelSummary {

    @JsonProperty("projectName")
    private String novelName;
    @JsonProperty("projectId")
    private String novelId;
    /* 각 novel의 대표 사진(리스트에서 보여줄 사진) 이후에 추가 예정 */

    public NovelSummary(String novelName, String novelId) {
        this.novelName = novelName;
        this.novelId = novelId;
    }

    public String getNovelName() {
        return novelName;
    }

    public String getNovelId() {
        return novelId;
    }
}
