package NERDPRESS.NERDPRESS.Domain;

public class episode {
    private String subtitle;
    private String content;
    private String writer;
    private String finish;
    private String check;
/*
    public episode(String subtitle, String content, String writer,String finish,String check) {
        this.subtitle = subtitle;
        this.content = content;
        this.writer = writer;
        this.finish = finish;
        this.check = check;
    }
*/
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
