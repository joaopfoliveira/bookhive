package bookhive.users.controllers;

import bookhive.users.dto.UserRequestDto;
import bookhive.users.dto.UserResponseDto;
import bookhive.users.dto.ValidationLevel;
import bookhive.users.exceptions.UsernameAlreadyExistsException;
import bookhive.users.exceptions.UsernameOrPasswordIsWrongException;
import bookhive.users.model.User;
import bookhive.users.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAnyAuthority('USER_WRITE')")
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createUser(@Validated(ValidationLevel.onCreate.class) @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto response;
        try {
            response = userService.saveUser(userRequestDto);
        } catch (UsernameAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAnyAuthority('USER_READ', 'USER')")
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "username") String username) {
        try {
            return new ResponseEntity<>(userService.getByUsername(username), HttpStatus.OK);
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return new ResponseEntity<>(usernameNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('USER_WRITE')")
    @PostMapping(value = "/authenticate", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> authenticateUser(@Validated(ValidationLevel.onAuthenticateUser.class) @RequestBody UserRequestDto userRequest) {

        User user;

        try {
            user = userService.validateUserLogin(userRequest);
        } catch (UsernameOrPasswordIsWrongException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        UserResponseDto response = UserResponseDto.builder()
                .user(user)
                .code(HttpStatus.OK.value())
                .build();
        String token = userService.generateToken(user);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(response);
    }

}
