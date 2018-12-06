package com.cafe24.websample.domain.account;

import com.cafe24.websample.domain.role.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Account {
    @Id @GeneratedValue
    @Column(name="user_id")
    private Long Id;

    @NotEmpty
    @Length(min= 2, message = "Your name must have at least 2 characters")
    private String username;

    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Email
    @NotEmpty
    @Column
    private String email;

    private String phoneNumber;

    @Column(name = "active")
    private int active;

    private String additional;


    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date regDateTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)  // java.util.Date이므로 @Temporal을 붙여준다.
    @Column(name = "last_modified_at", updatable = true)
    private Date lastModifiedDateTime;

    @Column(name = "role_id")
    private Long roleId;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;*/

}
