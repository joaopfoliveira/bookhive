package bookhive.apigateway.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
public class ConnValidationResponse {
    private String status;
    private boolean authenticated;
    private String methodType;
    private String username;
    private String token;
    private List<Authority> authorities;
}

