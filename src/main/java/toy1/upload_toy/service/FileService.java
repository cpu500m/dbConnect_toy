package toy1.upload_toy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import toy1.upload_toy.domain.UploadFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    /**
     * 여러 파일 저장 -> n번 storeFile 호출
     */
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> result = new ArrayList<>();
        for (MultipartFile m : multipartFiles) {
            if (!m.isEmpty()) {
                result.add(storeFile(m));
            }
        }
        return result;
    }

    /**
     * 단일 파일 저장
     */
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originName = multipartFile.getOriginalFilename();
        String storeName = makeFullName(originName);

        // 저장
        multipartFile.transferTo(new File(getFullPath(storeName)));

        return new UploadFile(originName, storeName);
    }

    /**
     * 편의 메서드 ( UUID를 이용한 임의 이름 생성)
     */
    private static String makeFullName(String originName) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originName);
        return uuid + "." + ext;
    }

    /**
     * 편의 메서드 (확장자 추출)
     */
    private static String extractExt(String originName) {
        int pos = originName.lastIndexOf(".");
        return originName.substring(pos + 1);
    }
}
