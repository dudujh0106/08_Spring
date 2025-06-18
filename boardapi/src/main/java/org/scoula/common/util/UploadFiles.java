package org.scoula.common.util;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class UploadFiles {
    public static String upload(String baseDir, MultipartFile part) throws IOException {
// 기본 디렉토리가 있는지 확인, 없으면 새로 생성
        File base = new File(baseDir);
        if(!base.exists()) {
            base.mkdirs(); // 중간에 존재하지 않는 디렉토리까지 모두 생성
        }
        String fileName = part.getOriginalFilename();
        File dest = new File(baseDir, UploadFileName.getUniqueName(fileName));
        part.transferTo(dest); // 지정한 경로로 업로드 파일 이동
        return dest.getPath(); // 저장된 파일 경로 리턴
    }

    /**
     * 파일 크기를 사용자 친화적 형태로 변환
     * @param size 바이트 단위 파일 크기
     * @return 포맷된 문자열 (예: 1.2 MB)
     */
    public static String getFormatSize(Long size) {
        if (size <= 0) return "0";

        final String[] units = new String[]{"Bytes", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));

        return new DecimalFormat("#,##0.#")
                .format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
