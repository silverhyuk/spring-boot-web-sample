package com.cafe24.websample.web.welcome;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WelcomeVO {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String createdAt;
}
