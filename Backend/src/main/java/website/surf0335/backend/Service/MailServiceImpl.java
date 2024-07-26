package website.surf0335.backend.Service;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import website.surf0335.backend.Model.dao_.domain.VerificationCode;
import website.surf0335.backend.Model.dao_.service.VerificationCodeService;


import java.sql.Timestamp;
import java.util.Date;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private VerificationCodeService verificationCodeService;
    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;


    @Override
    public void checkMail(String receiveEmail, String subject, String emailMsg){
        if(StringUtils.isEmpty(receiveEmail)) {
            throw new RuntimeException("Empty Receiver");
        }
        if(StringUtils.isEmpty(subject)) {
            throw new RuntimeException("Empty Topic");
        }
        if(StringUtils.isEmpty(emailMsg)) {
            throw new RuntimeException("Empty Content");
        }
    }
    @Override
    public Boolean sendTextMail(String receiveEmail, String subject, String emailMsg) {
        checkMail(receiveEmail, subject, emailMsg);
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            mimeMessageHelper.setFrom("Easy Eat Security Center <"+ sendMailer +">");
            mimeMessageHelper.setTo(receiveEmail.split(","));
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(emailMsg,true);
            mimeMessageHelper.setSentDate(new Date());

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功: " + sendMailer + "-->" + receiveEmail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败: " + e.getMessage());
            return false;
        }
    }
    @Override
    public Boolean storeRegisterCode(String email, String code, Timestamp date, int type){
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setCode(code);
        verificationCode.setTime( date);
        verificationCode.setType(type);
        verificationCodeService.saveCode(verificationCode);
        return code.equals(verificationCodeService.findCodeByEmail(email,type).getCode());
    }

    @Override
    public Boolean checkCode(String email, String uncheckedCode, int type){
        VerificationCode verificationCode = verificationCodeService.findCodeByEmail(email,type);
        String code = verificationCode.getCode();
        System.out.println(code);
        System.out.println("asas");
        System.out.println(uncheckedCode);
        if(code.equals(uncheckedCode)){
            verificationCodeService.deleteById(verificationCode.getId());
        }
        return code.equals(uncheckedCode);
    }
    @Override
    public Date getDateByEmail(String email, int type) {
        Date time = verificationCodeService.findCodeByEmail(email, type).getTime();
        System.out.println(time);
        return time;
    }
}
