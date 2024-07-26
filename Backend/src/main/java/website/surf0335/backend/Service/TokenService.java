package website.surf0335.backend.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface TokenService {
    public String generateTempToken(String username);
    public String refreshToken(String token);
    public String generateLongTermToken(int days, String token);

    public String decodeUsername(String token);

    public String decodePassword(String token);

    public Boolean validateToken(String token);

    public String loginUser(String token, HttpServletRequest request);
}
