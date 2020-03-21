package cn.mbw.oc.common.util.io;

import cn.mbw.oc.common.throwable.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.*;

/**
 * @author Mabowen
 * @date 2019-11-15 15:04
 */
@Slf4j
public class FileUtil {

    /**
     * 创建文件
     *
     * @author Mabowen
     * @date 15:12 2019-11-19
     */
    private static File createFile(String path, String filename) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 复制文件
     *
     * @author Mabowen
     * @date 14:17 2019-11-19
     * @param source 资源文件，被复制
     * @param targetPath 目标文件地址
     * @param filename 复制后的文件名
     */
    public static void copyFile(File source, String targetPath, String filename) {
        InputStream fis = null;
        OutputStream fos = null;
        try {
            File targetFile = createFile(targetPath, filename);
            fis = new FileInputStream(source);
            fos = new FileOutputStream(targetFile);
            StreamUtils.copy(fis, fos);
        } catch (IOException e) {
            log.error("复制文件异常：" + e.getMessage(), e);
            throw new ServiceException("复制文件异常：" + e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
