package fun.tianrui.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author TIANRUI
 */
@RestController
@RequestMapping("/api/file")
public class FileController {
    @RequestMapping(value = "/upFile", headers = "Content-Type= multipart/form-data", method = RequestMethod.POST)
    public String upFile(@RequestParam MultipartFile file) {
        System.out.println("开始上传了");
        System.out.println(file);
        System.out.println();

//        try {
//            if (file.isEmpty()){
//                return "no,file is empty";
//            }
//            String fileName = file.getOriginalFilename();
//            assert fileName != null;
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            String filePath = "C:\\file";
//            String path = filePath+fileName;
//            File dest = new File(path);
//            //判断文目录是否存在
//            if(!dest.getParentFile().exists()){
//                dest.getParentFile().mkdirs();
//            }
//            //文件写入
//            file.transferTo(dest);
//            return "上传成功";
//        }
//        catch (IllegalArgumentException | IOException e){
//            e.printStackTrace();
//        }

        return "OK";
    }
}
