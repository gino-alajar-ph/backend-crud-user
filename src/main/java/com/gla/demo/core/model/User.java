package com.gla.demo.core.model;

import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -4416909546385238440L;

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String role;

}
