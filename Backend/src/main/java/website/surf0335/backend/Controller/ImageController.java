package website.surf0335.backend.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    private static final String BASE_DIR_AVATAR = "/home/images/avatar/";

    private static final String BASE_DIR_PRODUCT = "/home/images/product/";

    private static final String BASE_DIR = "/home/images/other/";

    @PostMapping("/product/upload")
    public String uploadProduct(@RequestPart("file") MultipartFile file, @RequestParam("id") int id) {
        // Generate file name based on user's ID
        String fileName = "product_" + id + ".jpg";
        try {
            File uploadFile = new File(BASE_DIR_PRODUCT + fileName);
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
            }
            file.transferTo(uploadFile);
            System.out.println("File uploaded successfully to: " + uploadFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the download URL with the file name based on user's ID
        return "http://120.26.136.194:8000/api/image/product/download?fileName=" + fileName;
    }

    public void download(String fileName, HttpServletResponse response, int type){

        String DIR = "";
        if (type == 0){
            DIR = BASE_DIR_AVATAR;
        }else if (type == 1){
            DIR = BASE_DIR_PRODUCT;
        }else {
            DIR = BASE_DIR;
        }
        System.out.println("type:  "+ DIR);
        System.out.println("fileName:  "+ fileName);
        try (FileInputStream fis = new FileInputStream(DIR + fileName);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream os = response.getOutputStream()) {
            byte[] bytes = new byte[bis.available()];
            bis.read(bytes);
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            os.write(bytes);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/product/download")
    public void downloadProduct(@RequestParam String fileName, HttpServletResponse response) {
        download(fileName, response,1);
    }


    @PostMapping("/avatar/upload")
    public String uploadAvatar(@RequestPart("file") MultipartFile file, @RequestParam("id") int id) {
        // Generate file name based on user's ID
        String fileName = "avatar_" + id + ".jpg";
        try {
            File uploadFile = new File(BASE_DIR_AVATAR + fileName);
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
            }
            file.transferTo(uploadFile);
            System.out.println("File uploaded successfully to: " + uploadFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the download URL with the file name based on user's ID
        return "http://120.26.136.194:8000/api/image/avatar/download?fileName=" + fileName;
    }


    @GetMapping("/avatar/download")
    public void downloadAvatar(@RequestParam String fileName, HttpServletResponse response) {
        download(fileName, response,0);
    }

    @GetMapping("/other/download")
    public void downloadOther(@RequestParam String fileName, HttpServletResponse response) {
        download(fileName, response,2);
    }


    @PostMapping("/other/upload")
    public String uploadOther(@RequestPart("file") MultipartFile file, @RequestParam("id") int id) {
        // Generate file name based on user's ID
        String fileName = "other_" + id + ".jpg";
        try {
            File uploadFile = new File(BASE_DIR_AVATAR + fileName);
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
            }
            file.transferTo(uploadFile);
            System.out.println("File uploaded successfully to: " + uploadFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the download URL with the file name based on user's ID
        return "http://120.26.136.194:8000/api/image/other/download?fileName=" + fileName;
    }




}

