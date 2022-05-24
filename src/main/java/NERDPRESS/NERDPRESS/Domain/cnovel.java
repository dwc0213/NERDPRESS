package NERDPRESS.NERDPRESS.Domain;

import java.util.List;

public class cnovel {
    private String uuid;    // 유니크한 파일 이름을 만들기 위한 속성
    // 같은 이름의 파일 업로드 시, 파일 이름 중복 방지
    // 실제 파일이름 + uuid를 합쳐서 사용하려 하는 것
    private String fileName;    // 실제 파일 이름
    private String contentType;

    private String name;
    private String genre;
    private String explanation;
    private String w_name;
    private String s_cycle;

    public String getS_cycle() {
        return s_cycle;
    }

    public void setS_cycle(String s_cycle) {
        this.s_cycle = s_cycle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getW_name() {
        return w_name;
    }

    public void setW_name(String w_name) {
        this.w_name = w_name;
    }

    public cnovel(String uuid, String fileName, String contentType, String name, String genre, String explanation, String w_name, String s_cycle) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void addAttribute(String files, List<cnovel> list) {
    }
}
