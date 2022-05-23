package NERDPRESS.NERDPRESS.domain;

public class License {
    private int License_id;         // 자격증 ID (PK)
    private String License_type;    // 자격증 종류

    public int getLicense_id() {
        return License_id;
    }

    public void setLicense_id(int license_id) {
        License_id = license_id;
    }

    public String getLicense_type() {
        return License_type;
    }

    public void setLicense_type(String license_type) {
        License_type = license_type;
    }
}
