package fun.tianrui.blog.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailUtils {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailUtils(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param email 邮箱
     * @return 是否为邮箱
     */
    public static boolean isEmail(@NotNull String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean send(String toEmail, String code) {

        MimeMessage mimeMessage = null;
        try {
            mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //发件人
            helper.setFrom("wenruijie@xtrui.cn");

            //收件人
            helper.setTo(toEmail);
            //标题
            helper.setSubject("个人博客验证吗");
            //文本
            helper.setText("有效时间10分钟，本次验证码是：" + code);
            //附件
//        helper.addAttachment("downFile",new File("D:\\cygwin64\\home\\workspace3\\learn-demo\\zookeeper\\src\\test\\java\\com\\tzxylao\\design\\ZookeeperApplicationTests.java"));
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
