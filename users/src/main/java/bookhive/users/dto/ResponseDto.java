package bookhive.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ResponseDto {
    private final int code;
    private final String message;
}
