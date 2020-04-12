package pro.jianbing.aboutme.controller;

import io.netty.util.internal.StringUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.enums.FileNameEnum;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("download")
public class DownloadController extends BaseController {

    @Autowired
    private ResourceLoader resourceLoader;

    public DownloadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("picture/{fileName}/{type}")
    public void download(@PathVariable String fileName,@PathVariable String type, HttpServletResponse response) {
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            String path = "static/image/grandpa/"+fileName + "." + type;
            String outName = FileNameEnum.getValueByCode(fileName);
            if (StringUtil.isNullOrEmpty(outName)) {
                outName = fileName;
            }
            Resource resource = resourceLoader.getResource("classpath:"+path);
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode(outName, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);
            inputStream = resource.getInputStream();
            servletOutputStream = response.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                // 召唤jvm的垃圾回收器
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
