package io.github.samirsales.model.dto.user;

import io.github.samirsales.model.dto.ImageDTO;
import io.github.samirsales.model.dto.RoleDTO;
import io.github.samirsales.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@SuppressWarnings("unused")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {

    @NotEmpty(message = "The 'name' attribute must be filled.")
    private String name;

    @NotEmpty(message = "The 'username' attribute must be filled.")
    private String username;

    @NotEmpty(message = "The 'password' attribute must be filled.")
    private String password;

    @NotEmpty(message = "The 'password' attribute must be filled.")
    private String email;

    private Gender gender;
    private ImageDTO imageProfile;
    private Set<RoleDTO> roles;
}
