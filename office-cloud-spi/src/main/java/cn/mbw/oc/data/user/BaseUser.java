package cn.mbw.oc.data.user;

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
    private String name;
    private String phone;
    private String token;
    private Boolean superAdmin;
    private String salt;
}
