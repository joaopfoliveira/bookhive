package bookhive.auth.services;

import bookhive.auth.model.ApplicationUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public AuthUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new ApplicationUser(userService.getByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Username Not Found")));
    }
}
