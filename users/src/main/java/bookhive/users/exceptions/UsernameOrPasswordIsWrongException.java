package bookhive.users.exceptions;

public class UsernameOrPasswordIsWrongException extends Throwable{
    public UsernameOrPasswordIsWrongException(String message) {
        super(message);
    }
}
