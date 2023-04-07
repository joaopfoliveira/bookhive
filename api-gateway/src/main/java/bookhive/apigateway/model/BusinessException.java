package bookhive.apigateway.model;

import org.springframework.http.HttpStatus;

public class BusinessException extends Throwable {
    public BusinessException(String s, HttpStatus badGateway, String gatewayError) {
    }
}
