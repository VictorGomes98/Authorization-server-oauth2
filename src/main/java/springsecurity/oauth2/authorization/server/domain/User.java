package springsecurity.oauth2.authorization.server.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "[name]")
    private String name;

    @Column(name = "[passWord]")
    private String passWord;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Users_Authorities",
            joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Authority_Id")
    )
    private Set<Authority> authorities;
}
