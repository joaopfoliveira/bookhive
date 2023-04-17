package bookhive.users.exceptions;

public class UsernameAlreadyExistsException extends Throwable {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
