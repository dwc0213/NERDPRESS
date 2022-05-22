package NERDPRESS.NERDPRESS.Domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class cnovel {
    private String uuid;    // 유니크한 파일 이름을 만들기 위한 속성
    // 같은 이름의 파일 업로드 시, 파일 이름 중복 방지
    // 실제 파일이름 + uuid를 합쳐서 사용하려 하는 것
    private String fileName;    // 실제 파일 이름
    private String contentType;

    public cnovel(String uuid, String fileName, String contentType) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;

        System.out.println(contentType);
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
}
