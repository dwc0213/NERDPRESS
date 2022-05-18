function addQuestCheck(){

    if ($("#Quest_id").val() == "") {
        alert("문제 ID를 입력해주세요");
        $("#Quest_id").focus();
        return false;
    }
    if ($("#License_id").val() == "") {
        alert("자격증 종류를 선택해주세요");
        $("#License_id").focus();
        return false;
    }
    if ($("#Quest_title").val() == "") {
        alert("문제 설명을 입력해주세요");
        $("#Quest_title").focus();
        return false;
    }

    if ($("#Q_one").val() == "") {
        alert("정답 보기 1번을 입력해주세요");
        $("#Q_one").focus();
        return false;
    }
    if ($("#Q_two").val() == "") {
        alert("정답 보기 2번을 입력해주세요");
        $("#Q_two").focus();
        return false;
    }
    if ($("#Q_three").val() == "") {
        alert("정답 보기 3번을 입력해주세요");
        $("#Q_three").focus();
        return false;
    }
    if ($("#Q_four").val() == "") {
        alert("정답 보기 4번을 입력해주세요");
        $("#Q_four").focus();
        return false;
    }

    if ($("#Quest_answer").val() == "") {
        alert("정답 번호를 입력해주세요");
        $("#Quest_answer").focus();
        return false;
    }

    $("#addQuizForm").submit();

}