package com.dbwp031.springaws.config.auth.dto;

import com.dbwp031.springaws.domain.user.User;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class SessionUser implements Serializable { //우리가 만든 클래스가 파일에 읽거나 쓸 수 있도록 하거나, 다른 서버로 보내거나 받을 수 있도록 하기 위해서
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
