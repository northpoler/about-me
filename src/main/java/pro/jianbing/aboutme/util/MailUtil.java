package pro.jianbing.aboutme.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 发邮件的工具
 *
 * @author 李建兵
 */
public final class MailUtil {
    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    @Autowired
    private JavaMailSender mailSender;

    private String from = "2776204800@qq.com";

    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try {
            mailSender.send(simpleMailMessage);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }

    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content);
            helper.setCc("liyingying@heatedloan.com");//抄送
            mailSender.send(message);
            logger.info("html邮件已经发送。");
        } catch (MessagingException e) {
            logger.info("html邮件已经发送。");
            e.printStackTrace();
        }

    }

    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content);
            helper.setCc("liyingying@heatedloan.com");//抄送
            //添加附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            logger.info("带附件邮件已经发送。");
        } catch (MessagingException e) {
            logger.info("带附件邮件已经发送。");
            e.printStackTrace();
        }
    }

    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content);
            helper.setCc("liyingying@heatedloan.com");//抄送
            //添加附件
            FileSystemResource file = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, file);
            mailSender.send(message);
            logger.info("带静态资源文件邮件已经发送。");
        } catch (MessagingException e) {
            logger.info("带静态资源文件邮件已经发送。");
            e.printStackTrace();
        }
    }

}
