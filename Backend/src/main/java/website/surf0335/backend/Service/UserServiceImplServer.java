package website.surf0335.backend.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import website.surf0335.backend.Model.dao_.domain.User;
import website.surf0335.backend.Model.dao_.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImplServer implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(UserServiceImplServer.class);


    public UserServiceImplServer(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String role = "";
        User user = userService.getUserByUsername(username);

        if (user == null) {
            log.trace("{} not found!", username);
            throw new UsernameNotFoundException(username + " not found!");
        } else {
            log.trace("{} found!", user.getUserName());
            log.trace(String.valueOf(user));
//            role = user.getRole();
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPasswordHash(),
                authorities
        );
    }
}
