package org.cosmos.modules.web.springmvc.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * User: mew <p />
 * Time: 17/11/6 16:17  <p />
 * Version: V1.0  <p />
 * Description: 文件上传工具类(只支持springMVC) <p />
 */
public class FileUploadUtils {

    /** 默认上传文件夹 */
    private static final String DEFAULT_UPLOAD_DIR = "upload";

    public static String upload(MultipartFile mFile, String relativeDir, String newFileName) throws IOException {
        try {
            if (newFileName != null) {
                String suffix = FilenameUtils.getExtension(newFileName);
                if (StringUtils.isEmpty(suffix)) {
                    suffix = FilenameUtils.getExtension(mFile.getOriginalFilename());
                    newFileName = newFileName.concat(".").concat(suffix);
                }
            } else {
                newFileName = mFile.getOriginalFilename();
            }

            if (StringUtils.isEmpty(relativeDir)) {
                relativeDir = DEFAULT_UPLOAD_DIR;
            }

            File file = new File(relativeDir);
            if (!file.exists()) {
                file.mkdir();
            }

            String newFileFullName = FilenameUtils.concat(relativeDir, newFileName);
            mFile.transferTo(new File(newFileFullName));
            return newFileFullName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String upload(MultipartFile mFile) throws IOException {
        return upload(mFile, null, null);
    }

}
