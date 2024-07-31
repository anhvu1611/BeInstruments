package com.intrument.saleintrument.web.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends AbstractModel{
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String username;
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

}
