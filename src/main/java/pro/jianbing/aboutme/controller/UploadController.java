package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LogoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("upload")
public class UploadController {

    private final LogoService logoService;

    @Autowired
    public UploadController(LogoService logoService) {
        this.logoService = logoService;
    }

    @PostMapping("")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        try {
            String oldName = file.getOriginalFilename();
            String path = request.getServletContext().getRealPath("/static/upload/");
            User user = (User)request.getSession().getAttribute("user");
            String fileName = System.currentTimeMillis()+"_"+user.getId()+oldName.substring(oldName.lastIndexOf("."));
            // 把上传的图片保存一份在本地（根据当前系统是Windows或是Linux来决定保存路径）
            File localFile;
            if (System.getProperty("os.name").toLowerCase().startsWith("win")){
                localFile = new File("C:\\jianbing\\logo\\backup",fileName);
            } else {
                localFile = new File("/usr/temp",fileName);
            }
            if (!localFile.getParentFile().exists()){
                localFile.getParentFile().mkdirs();
            }
            file.transferTo(localFile);
            fileName = path + fileName;
            File fileTemp = new File(fileName);
            if (!fileTemp.getParentFile().exists()){
                fileTemp.getParentFile().mkdirs();
            }
            System.out.println(fileName);
            file.transferTo(fileTemp);
            map.put("code", 0);
            map.put("msg", "");
            map.put("data","");
        } catch (IOException e) {
            map.put("code", 500);
            map.put("msg", "");
            map.put("data","");
            e.printStackTrace();
        }
        return map;
    }
}
