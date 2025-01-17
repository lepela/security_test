package shop.javaman.club.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import shop.javaman.club.entity.dto.AttachDto;
import shop.javaman.club.service.S3Service;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/v1/file")
@Log4j2
public class FileController {
  @Autowired
  private S3Service s3Service;
  @Value("${aws.s3.bucket-name}")
  private String bucketName;
  @Value("${aws.s3.region}")
  private String region;

  @PostMapping("upload")
  public ResponseEntity<?> uploadFile(@RequestParam("file") List<MultipartFile> files) {
    List<AttachDto> list = new ArrayList<>();
    for(MultipartFile file : files) {
      try {
        // Extract file details
        String origin = file.getOriginalFilename();
        String path = path();
        String uuid = UUID.randomUUID().toString();
        String ext = "";
        if(origin == null) {
          continue;
        }
        int idx = origin.lastIndexOf(".");
        // 1234.
        if(idx > 0) {
          ext = origin.substring(idx+1);
        }
        String fileName = uuid + "." + ext;

        String mimeType = file.getContentType();
        byte[] content = file.getBytes();
        String key = "uploads/" + path + "/" + fileName; // Customize key structure if needed

        // Upload file to S3

        // s3 upload
        s3Service.uploadFile(key, content, mimeType);

        AttachDto dto = AttachDto.builder()
        .uuid(uuid)
        .origin(origin)
        .image(mimeType != null && mimeType.startsWith("image/"))
        .path(path)
        .size(file.getSize())  
        .mime(mimeType)
        .fileName(fileName)
        .ext(ext)
        .url(String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, key))
        .build();
        list.add(dto);

      } catch (Exception e) {
        log.error(e.getMessage());
      }
    }
    log.info(list);
    return ResponseEntity.ok().body(list);
  }
  private String path() {
    return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
  }
}
