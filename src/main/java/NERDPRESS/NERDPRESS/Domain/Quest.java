package NERDPRESS.NERDPRESS.domain;

public class Quest {
    private int Quest_id;           // 문제 ID (PK)
    private int License_id;         // 자격증 ID (FK)
    private String Quest_title;     // 문제 설명
    private String Q_one;           // 1번 보기 정답
    private String Q_two;           // 2번 보기 정답
    private String Q_three;         // 3번 보기 정답
    private String Q_four;          // 4번 보기 정답
    private int Quest_answer;       // 정답 번호

    public int getQuest_id() {
        return Quest_id;
    }

    public void setQuest_id(int quest_id) {
        Quest_id = quest_id;
    }

    public int getLicense_id() {
        return License_id;
    }

    public void setLicense_id(int license_id) {
        License_id = license_id;
    }

    public String getQuest_title() {
        return Quest_title;
    }

    public void setQuest_title(String quest_title) {
        Quest_title = quest_title;
    }

    public String getQ_one() {
        return Q_one;
    }

    public void setQ_one(String q_one) {
        Q_one = q_one;
    }

    public String getQ_two() {
        return Q_two;
    }

    public void setQ_two(String q_two) {
        Q_two = q_two;
    }

    public String getQ_three() {
        return Q_three;
    }

    public void setQ_three(String q_three) {
        Q_three = q_three;
    }

    public String getQ_four() {
        return Q_four;
    }

    public void setQ_four(String q_four) {
        Q_four = q_four;
    }

    public int getQuest_answer() {
        return Quest_answer;
    }

    public void setQuest_answer(int quest_answer) {
        Quest_answer = quest_answer;
    }
}
