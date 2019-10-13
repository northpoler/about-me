package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pro.jianbing.aboutme.entity.Logo;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LogoService;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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
        Map<String, Object> map = new HashMap<>();
        try {
            String oldName = file.getOriginalFilename();
            String path = request.getServletContext().getRealPath("/upload/");
            User user = (User)request.getSession().getAttribute("user");
            String fileName = System.currentTimeMillis()+"_"+user.getId()+oldName.substring(oldName.lastIndexOf("."));
            File localFile = new File("D:\\cache",fileName);
            file.transferTo(localFile);
            fileName = path + "/" + fileName;
            File fileTemp = new File(fileName);
            if (!fileTemp.getParentFile().exists()){
                fileTemp.getParentFile().mkdirs();
            }
            file.transferTo(fileTemp);
            map.put("code", 0);
            map.put("msg", "");
            map.put("data","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
