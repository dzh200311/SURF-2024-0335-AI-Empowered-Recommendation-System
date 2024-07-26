package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.VerificationCodeDao;
import website.surf0335.backend.Model.dao_.domain.VerificationCode;

@Repository
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private VerificationCodeDao verificationCodeDao = new VerificationCodeDao();
    @Override
    public void saveCode(VerificationCode verificationCode) {
        verificationCodeDao.update("INSERT INTO verification_code (email, code, time, type) VALUES (?, ?, ?, ?)",
                verificationCode.getEmail(),verificationCode.getCode(),verificationCode.getTime(),verificationCode.getType());
    }

    @Override
    public VerificationCode findCodeByEmail(String email, int type) {
        return verificationCodeDao.querySingle("SELECT * FROM `verification_code` WHERE email = ? AND type = ? ORDER BY id DESC LIMIT 1",
                VerificationCode.class, email, type);
    }

    @Override
    public void deleteById(Long id) {
        verificationCodeDao.update("DELETE FROM verification_code WHERE id = ?", id);
    }

}
