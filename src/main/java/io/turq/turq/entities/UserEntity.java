package io.turq.turq.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    public UserEntity(String first_name, String last_name, String email, String password, Boolean admin) {
        this.firstname = first_name;
        this.lastname = last_name;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean admin;
    @JsonIgnore
    private String password;

}

