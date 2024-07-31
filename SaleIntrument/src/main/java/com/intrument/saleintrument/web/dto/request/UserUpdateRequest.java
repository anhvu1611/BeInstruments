package com.intrument.saleintrument.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserUpdateRequest {
    private String password;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
}
