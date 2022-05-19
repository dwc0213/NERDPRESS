function addLicenseCheck(){

    if ($("#License_type").val() == ""){
        alert("자격증 종류를 입력해주세요");
        $("#License_type").focus();
        return false;
    }

    $("#addLicenseForm").submit();

}