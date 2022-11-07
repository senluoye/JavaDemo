package io;

import java.io.*;
import java.util.Objects;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-09 23:57
 */
public class CopyDir {
    public static void main(String[] args) {
        String from = "a";
        String to = "a_new";
        copyDir(from, to);
    }

    /**
     * 复制文件夹
     * @param from
     * @param to
     */
    public static void copyDir(String from, String to) {
        File formFile = new File(from);
        // 不存在 || 不是文件夹
        if (!formFile.exists() || !formFile.isDirectory()) {
            throw new IllegalArgumentException("参数错误");
        }

        File toFile = new File(to);
        // 如果目标路径不存在，就创建
        if (!toFile.exists()) toFile.mkdir();

        File[] files = formFile.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isFile())
                copyFile(file, new File(to, file.getName()));
            else if (file.isDirectory())
                copyDir(from + File.separator + file.getName(), to + File.separator + file.getName());
        }
    }

    /**
     * 复制文件
     * @param srcFile
     * @param destFile
     */
    public static void copyFile(File srcFile, File destFile) {
        byte[] bytes = new byte[1024];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile))) {
            // 当 长度 == -1 时。表示已经读完
            int len;
            while ((len = bis.read(bytes)) > -1) {
                bos.write(bytes, 0, len);
            }
            // 将剩下的数据也塞进去
            bos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
