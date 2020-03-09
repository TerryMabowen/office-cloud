package cn.mbw.oc.controller.user.fb;

import lombok.Data;

/**
 * @author Mabowen
 * @date 2020-01-10 19:09
 */
@Data
public class RegisterUserFB {
    private String username;

    private String password;

    private String phone;

    private String nickname;

    private String realname;

    private Integer gender;
}
