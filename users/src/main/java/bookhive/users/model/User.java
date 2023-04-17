package bookhive.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "user")
@Data
@Builder(toBuilder = true)
public class User implements Serializable {

    @Id
    private String id;

    @NonNull
    @Field(name = "fullname")
    private String fullname;

    @NonNull
    @Field(name = "username")
    private String username;

    @Field(name = "password")
    @JsonIgnore
    private String password;

    @NonNull
    @Field(name = "isAdminUser")
    private String isAdmin;

    @NonNull
    @Field(name = "timestamp")
    @Builder.Default
    private Date timestamp = new Date();

    @NonNull
    @Field
    @Builder.Default
    private Date createdOn = new Date();

    @NonNull
    @Field(name = "isOauthAccount")
    @Builder.Default
    private String isOauthAccount = "N";

    @Field(name = "deletedFlag")
    @Builder.Default
    private String deletedFlag = "N";

    @Field(name = "isSeller")
    private boolean seller;

    @Field(name = "phoneNo")
    private long phoneNo;

    @Field(name = "sellerAddressID")
    private String sellerAddress;
    @Field(name = "credentialsExpiryDate")
    private LocalDateTime credentialsExpiryDate;

    @Field(name = "isAccountExpired")
    private boolean isAccountExpired;

    @Field(name = "isAccountLocked")
    private boolean isAccountLocked;

    @Field(name = "isReadOnlyUser")
    private boolean isReadOnlyUser;
    @Field(name = "sellerBio")
    private String sellerBio;

    @Override
    public String toString()
    {
        if(isAdmin.equalsIgnoreCase("Y")) {
            return "User [id=" + id + ", uname=" + username + ", Admin User]";
        } else {
            return "User [id=" + id + ", uname=" + username + "]";
        }
    }

}