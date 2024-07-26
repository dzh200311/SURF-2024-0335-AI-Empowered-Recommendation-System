package website.surf0335.backend.Model.dao_.utils;


import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativePathExample {
    public static void main(String[] args) {
        // 当前路径
        Path currentPath = Paths.get(System.getProperty("user.dir"));

        // 可以复制druid。properties的全部路径放在这，得出相对路径修改druidjdbcUtils
        Path targetPath = Paths.get("../../../../../druid.properties");

        // 计算相对路径
        Path relativePath = currentPath.relativize(targetPath);

        // 打印结果
        System.out.println("当前路径: " + currentPath);
        System.out.println("目标路径: " + targetPath);
        System.out.println("相对路径: " + relativePath);
    }
}
