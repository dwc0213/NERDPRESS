package NERDPRESS.NERDPRESS.domain;

public class Novel {
    private int id;
    private int date;
    private String title;
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*
    * 1: 로맨스
    * 2: 판타지
    * 4: 스릴러
    * 8: 역사
    * */
    private String content;

}
