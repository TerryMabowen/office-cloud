package cn.mbw.oc.sso.spi.data.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mabowen
 * @date 2019-12-25 20:07
 */
@Data
public class BaseUser implements Serializable {
    private static final long serialVersionUID = -1898388496382473057L;
    private Long id;
    private String username;
    private String passwordHash;
    private String password;
    private String phone;
    private String token;
    private String salt;
    private String useStatus;
}
