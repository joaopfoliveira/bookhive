package bookhive.users.dto;

import bookhive.users.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto extends ResponseDto {
    private final User user;
    private final String userId;

    @Builder
    public UserResponseDto(int code, String message, User user, String userId) {
        super(code, message);
        this.user = user;
        this.userId = userId;
    }
}
