package website.surf0335.backend.Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.surf0335.backend.Model.dao_.domain.ToEmail;
import website.surf0335.backend.Service.MailService;
import website.surf0335.backend.utils.AjaxResult;
import website.surf0335.backend.utils.VerCodeGenerateUtil;
import java.sql.Timestamp;

import java.util.Date;

@RestController
@RequestMapping("/api/email")
public class MailController {

    @Resource
    private MailService mailService;


    @PostMapping("/check_code")
    public String checkCode(@RequestParam("email") String email, @RequestParam("code") String code,
                            @RequestParam("type") int type){
        Date time = new Date();
        Date codeTime = mailService.getDateByEmail(email,type);
        if ((time.getTime() - codeTime.getTime()) / (60 * 60 * 1000) < 2){
            if (mailService.checkCode(email,code,type)){
                return "ok";
            }else {
                return  "wrong";
            }
        }else {
            return "timeout";
        }
    }

//    @PostMapping("/reset_password")
//    public AjaxResult resetPassword(ToEmail toEmail){
//        if(toEmail == null || toEmail.getTos() == null ) {
//            return AjaxResult.fail(-1, "参数错误!");
//        }
//        toEmail.setSubject("You are about to Rest the password");
//        String verCode = VerCodeGenerateUtil.generateVerCode();
//        String content = "Your code is" +verCode;
//
//        toEmail.setContent(content);
//
//        Boolean check = mailService.sendTextMail(toEmail.getTos(), toEmail.getSubject(), toEmail.getContent());
//        if(check) {
//            Date currentDate = new Date();
//            if (mailService.storeRegisterCode(toEmail.getTos(),verCode,currentDate, 1)){
//                return AjaxResult.success(200, "success");
//            }else {
//                return AjaxResult.fail(-2, "发送失败");
//            }
//        } else {
//            return AjaxResult.fail(-2, "发送失败");
//        }
//    }

    @RequestMapping("/send_verification_email")
    public AjaxResult sendEmail(ToEmail toEmail, String username,HttpServletRequest request) {
        if(toEmail == null || toEmail.getTos() == null ) {
            return AjaxResult.fail(-1, "no receive email");
        }
        if(username == null ) {
            return AjaxResult.fail(-1, "no username");
        }
        toEmail.setSubject("Your verification code is: (do not reply)");
        String verCode = VerCodeGenerateUtil.generateVerCode();
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\n" +
                "  <title>Verification Code</title>\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <style type=\"text/css\">\n" +
                "  @media screen {\n" +
                "    @font-face {\n" +
                "      font-family: 'Source Sans Pro';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 400;\n" +
                "      src: local('Source Sans Pro Regular'), local('SourceSansPro-Regular'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff');\n" +
                "    }\n" +
                "    @font-face {\n" +
                "      font-family: 'Source Sans Pro';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 700;\n" +
                "      src: local('Source Sans Pro Bold'), local('SourceSansPro-Bold'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff');\n" +
                "    }\n" +
                "  }\n" +
                "  body,\n" +
                "  table,\n" +
                "  td,\n" +
                "  a {\n" +
                "    -ms-text-size-adjust: 100%; /* 1 */\n" +
                "    -webkit-text-size-adjust: 100%; /* 2 */\n" +
                "  }\n" +
                "  table,\n" +
                "  td {\n" +
                "    mso-table-rspace: 0pt;\n" +
                "    mso-table-lspace: 0pt;\n" +
                "  }\n" +
                "  img {\n" +
                "    -ms-interpolation-mode: bicubic;\n" +
                "  }\n" +
                "  a[x-apple-data-detectors] {\n" +
                "    font-family: inherit !important;\n" +
                "    font-size: inherit !important;\n" +
                "    font-weight: inherit !important;\n" +
                "    line-height: inherit !important;\n" +
                "    color: inherit !important;\n" +
                "    text-decoration: none !important;\n" +
                "  }\n" +
                "  div[style*=\"margin: 16px 0;\"] {\n" +
                "    margin: 0 !important;\n" +
                "  }\n" +
                "  body {\n" +
                "    width: 100% !important;\n" +
                "    height: 100% !important;\n" +
                "    padding: 0 !important;\n" +
                "    margin: 0 !important;\n" +
                "  }\n" +
                "  table {\n" +
                "    border-collapse: collapse !important;\n" +
                "  }\n" +
                "  a {\n" +
                "    color: #1a82e2;\n" +
                "  }\n" +
                "  img {\n" +
                "    height: auto;\n" +
                "    line-height: 100%;\n" +
                "    text-decoration: none;\n" +
                "    border: 0;\n" +
                "    outline: none;\n" +
                "  }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body style=\"background-color: #e9ecef;\">\n" +
                "  <div class=\"preheader\" style=\"display: none; max-width: 0; max-height: 0; overflow: hidden; font-size: 1px; line-height: 1px; color: #fff; opacity: 0;\">\n" +
                "    Verification Code Email.\n" +
                "  </div>\n" +
                "  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "    <tr>\n" +
                "      <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "          <tr>\n" +
                "            <td align=\"center\" valign=\"top\" style=\"padding: 36px 24px;\">\n" +
                "              <a href=\"https://www.blogdesire.com\" target=\"_blank\" style=\"display: inline-block;\">\n" +
                "                <img src=\"http://120.26.136.194:8000/api/image/avatar/download?fileName=avatar_16.jpg\" alt=\"Logo\" border=\"0\" width=\"88\" style=\"display: block; width: 88px; max-width: 88px; min-width: 88px;\">\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "          <tr>\n" +
                "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 36px 24px 0; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; border-top: 3px solid #d4dadf;\">\n" +
                "              <h1 style=\"margin: 0; font-size: 32px; font-weight: 700; letter-spacing: -1px; line-height: 48px;\">"+ username +", Hello!</h1>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "          <tr>\n" +
                "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\n" +
                "              <p style=\"margin: 0;\">The email verification code for this request is: </p>\n" +
                "              <div style=\"text-align: center;\">\n" +
                "                <strong style=\"font-size: 25px;\">"+ verCode +"</strong>\n" +
                "              </div>\n" +
                "              <p style=\"margin: 0;\">This code is valid for 2 hours. Please enter it promptly. (Do not disclose this verification code)</p>\n" +
                "              <p style=\"margin-top: 10px;\">If this is not your operation, please ignore this email. (This is an email sent automatically, please do not reply directly)</p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\n" +
                "              <p style=\"margin: 0;\">Need help? Please contact our<a href=\"mailto:support_MED@163.com\">Support Team.</a></p>\n" +
                "              <p style=\"margin: 0;\">Would you like to provide feedback? Please let us know your thoughts through our<a href=\"https://blogdesire.com\" target=\"_blank\">Feedback Page.</a></p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; border-bottom: 3px solid #d4dadf\">\n" +
                "              <p style=\"margin: 0;\">Cheers,<br> Paste</p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 24px;\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "          <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\n" +
                "              <p style=\"margin: 0;\">You received this email because we received a request for [Registration] for your account. If you didn't request [Registration] you can safely delete this email.</p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\n" +
                "              <p style=\"margin: 0;\">To stop receiving these emails, you can <a href=\"https://www.blogdesire.com\" target=\"_blank\">unsubscribe</a> at any time.</p>\n" +
                "              <p style=\"margin: 0;\">©2024 MED All Rights    Wuzhong District, Suzhou, China, 215000</p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";

        toEmail.setContent(content);

        Boolean check = mailService.sendTextMail(toEmail.getTos(), toEmail.getSubject(), toEmail.getContent());
        if(check) {
            Date date = new Date();

            Timestamp timestamp = new Timestamp(date.getTime());
            if (mailService.storeRegisterCode(toEmail.getTos(),verCode,timestamp, 0)){
                return AjaxResult.success(200, "发送成功");
            }else {
                return AjaxResult.fail(-2, "发送失败");
            }
        } else {
            return AjaxResult.fail(-2, "发送失败");
        }

    }
}

