package website.surf0335.backend.Service;

import java.sql.Timestamp;
import java.util.Date;

public interface MailService {
    public void checkMail(String receiveEmail, String subject, String emailMsg);
    public Boolean sendTextMail(String receiveEmail, String subject, String emailMsg);
    public Boolean storeRegisterCode(String email, String code, Timestamp date, int type);
    public Boolean checkCode(String email, String uncheckedCode, int type);

    public Date getDateByEmail(String email, int type);
}
