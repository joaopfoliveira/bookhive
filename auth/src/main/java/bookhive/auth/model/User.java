package bookhive.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.NonNull;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User{
    private String id;

    @NonNull
    @Field(name = "Fullname")
    private String fullname;

    @NonNull
    @Field(name = "Username")
    private String username;

    @Field(name = "Password")
    @JsonIgnore
    private String password;
}
