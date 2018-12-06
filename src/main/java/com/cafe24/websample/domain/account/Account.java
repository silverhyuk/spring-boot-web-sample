package com.cafe24.websample.domain.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Account {
    @Id @GeneratedValue
    private Long Id;

    private String username;

    private String password;

    private String phoneNumber;

    private String additional;

    private Date regDate;

}
