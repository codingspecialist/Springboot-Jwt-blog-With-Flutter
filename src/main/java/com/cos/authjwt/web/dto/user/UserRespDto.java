package com.cos.authjwt.web.dto.user;

import java.time.format.DateTimeFormatter;
import java.util.Formatter;

import com.cos.authjwt.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRespDto {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String created;
    private String updated;

    public UserRespDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = "";
        this.email = user.getEmail();
        this.created = user.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updated = user.getUpdated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
