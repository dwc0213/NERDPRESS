package NERDPRESS.NERDPRESS.controller;
import NERDPRESS.NERDPRESS.Domain.cnovel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class cnovelcontroller {
    @GetMapping("/fileUpload")
    public String fileUpload() {
        return "/fileUpload";
    }

    @PostMapping("/fileUpload")
    public String fileUploading(@RequestParam MultipartFile[] uploadfile, Model model) throws IOException {

        List<cnovel> list = new ArrayList<>();

        for(MultipartFile file : uploadfile) {
            if (!file.isEmpty()) {  // 파일이 있으면,
                cnovel f = new cnovel(UUID.randomUUID().toString(),
                        file.getOriginalFilename(),
                        file.getContentType());
                list.add(f);

                File newFile = new File(f.getUuid() + "_" + f.getFileName());
                file.transferTo(newFile);
            }
        }

        model.addAttribute("files", list);
        return "fileUploadResult";
    }

    /**
     * application.properties에 적힌 내용 가져오는 방법
     */
    @Value("${spring.servlet.multipart.location}")
    String filePath;

    /**
     * 서버에 있는 파일 내려받는 방법
     * 1) 파일 경로를 찾아서 path에 담는다. (Path 담는 클래스가 기존에 있어요)
     * 2) Http 메세지 안에 Header/Body 부분으로 나눠지는데
     *    Header안에 content 담는 공간이 있어요. 거기 파일이름으로 담을 공간을 할당 받습니다.
     * 3) Header안에 파일 형식도 추가해줘요.
     * 4) 서버에서 클라이언트에 Response할때, Stream으로 파일 내용을 쏟아주기!
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@ModelAttribute cnovel dto) throws IOException {
        Path path = Paths.get(filePath+"/"+dto.getUuid()+"_"+dto.getFileName());
        System.out.println(filePath + "/n" + path);
        String contentType= Files.probeContentType(path);
        // header를 통해서 다운로드 되는 파일의 정보를 설정

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(dto.getFileName(), StandardCharsets.UTF_8).build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
