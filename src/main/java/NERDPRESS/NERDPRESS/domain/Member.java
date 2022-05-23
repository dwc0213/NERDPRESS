package NERDPRESS.NERDPRESS.domain;

public class Member {
    private int id;
    private String userId;
    private String PW;
    private String name;
    private int birthDate;
    private boolean isMale; //남자면 true, 여자면 false
    private byte grade; //유저등급, 0이면 평회원, 1이면 작가, 2면 관리자.

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

    public boolean getIsMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
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
}