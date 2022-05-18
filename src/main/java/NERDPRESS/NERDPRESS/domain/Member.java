package NERDPRESS.NERDPRESS.domain;

public class Member {
    private int id;//1
    private String userId;//2
    private String PW;//3
    private String name;//4
    private boolean male;//5
    private int birthDate;//6
    private byte grade;//7

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean getMale() {
        return male;
    }

    public void setMale(boolean Male) {
        this.male = male;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public byte getGrade() {
        return grade;
    }

    public void setGrade(byte grade) {
        this.grade = grade;
    }
}
