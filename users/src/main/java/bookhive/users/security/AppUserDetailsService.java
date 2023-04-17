package bookhive.users.security;

import bookhive.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public AppUser loadUserByUsername(String s) throws UsernameNotFoundException {
        return new AppUser(userService.getByUsername(s));
    }
}
