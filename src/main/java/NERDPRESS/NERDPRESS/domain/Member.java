package NERDPRESS.NERDPRESS.Domain;

public class Member {
    private int id;
    private String userId;
    private String PW;
    private String name;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private String Email;
    private int birthDate;
    private boolean Male; //남자면 true, 여자면 false
    private byte grade; //유저등급, 0이면 평회원, 1이면 작가, 2면 관리자.
    private int recommend; // 추천수



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public boolean getMale() {
        return Male;
    }

    public void setMale(boolean Male) {
        this.Male = Male;
    }

    public byte getGrade() {
        return grade;
    }

    public void setGrade(byte grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }
}