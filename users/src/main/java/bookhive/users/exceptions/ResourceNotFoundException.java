package bookhive.users.exceptions;

public class ResourceNotFoundException extends Throwable {
    public ResourceNotFoundException(String user, String email, Long id) {
        super(user + email + id);
    }
}
