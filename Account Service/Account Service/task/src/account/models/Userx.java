package account.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Userx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(value = View.NoPassword.class)
    private Long id;

    @NotNull
    @NotBlank
    @JsonView(value = View.NoPassword.class)
    private String name;

    @NotBlank
    @NotNull
    @JsonView(value = View.NoPassword.class)
    private String lastname;


    @Email(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@acme.com")
    @JsonView(value = View.NoPassword.class)
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 12, message = "The password length must be at least 12 chars!")
    private String password;

    private String role;
}
