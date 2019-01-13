package com.cafe24.websample.common.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@Component
@ConfigurationProperties (prefix = "person")
public class Person {
    private String firstName;
    private String lastName;
    private int age;
}
