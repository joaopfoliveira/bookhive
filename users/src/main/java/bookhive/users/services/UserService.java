package bookhive.users.services;

import bookhive.users.dto.UserRequestDto;
import bookhive.users.dto.UserResponseDto;
import bookhive.users.exceptions.UsernameAlreadyExistsException;
import bookhive.users.exceptions.UsernameOrPasswordIsWrongException;
import bookhive.users.model.User;
import bookhive.users.repositories.UserRepository;
import bookhive.users.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserResponseDto saveUser(UserRequestDto userRequestDto) throws UsernameAlreadyExistsException {
        User user = User.builder().fullname(userRequestDto.getFullname())
                .password(encryptPassword(userRequestDto.getPassword()))
                .username(userRequestDto.getUsername())
                .isAdmin(isAdmin(userRequestDto))
                .build();

        if (userRepository.findByUsername(userRequestDto.getUsername()).isPresent()) throw new UsernameAlreadyExistsException("Username already exists.");

        User createdUser = userRepository.save(user);

        return UserResponseDto.builder()
                .code(HttpStatus.CREATED.value())
                .message("User created successfully.")
                .userId(createdUser.getId()).build();
    }
    public User getByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User validateUserLogin(UserRequestDto userRequest) throws UsernameOrPasswordIsWrongException {
        Optional<User> user = userRepository.findByUsername(userRequest.getUsername());
        if(user.isEmpty()) {
            throw new UsernameOrPasswordIsWrongException("Wrong credentials.");
        }

        User userData = user.get();
        if(!passwordEncoder.matches(userRequest.getPassword(), userData.getPassword())) {
            throw new UsernameOrPasswordIsWrongException("Wrong credentials.");
        }
        return userData;
    }

    public String generateToken(User user) {
        Date expDate = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);
        Claims claims = Jwts.claims().setSubject(user.getId());
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date())
                .setIssuer(SecurityConstants.ISSUER)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.KEY)
                .compact();
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String isAdmin(UserRequestDto userRequestDto) {
        if (userRequestDto.getIsAdmin() != null && !userRequestDto.getIsAdmin().isEmpty())
            return userRequestDto.getIsAdmin();
        return "N";
    }
}
