package com.typesafe.common;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private String identifier;
    private String firstName;
    private String lastName;


}
