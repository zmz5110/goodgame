package org.lastsurprise.goodgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;
    private String username;
    private String userPassword;
    private String salt;
}
