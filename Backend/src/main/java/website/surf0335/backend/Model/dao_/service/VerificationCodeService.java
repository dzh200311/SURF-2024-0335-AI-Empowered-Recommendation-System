package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.VerificationCode;
@Repository
public interface VerificationCodeService {

    public void saveCode(VerificationCode verificationCode);

    public VerificationCode findCodeByEmail (String email, int type);


    public void deleteById (Long id);

}
