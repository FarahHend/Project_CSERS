package Hend.BackendSpringboot.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    //private String firstname;
    //private String lastname;
    private String email;
    String password;


}
