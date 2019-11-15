package io.github.samirsales.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CredentialDTO implements Serializable {

    private String username;
    private String password;
}
