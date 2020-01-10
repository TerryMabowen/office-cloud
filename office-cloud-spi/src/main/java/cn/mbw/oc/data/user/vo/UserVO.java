package cn.mbw.oc.data.user.vo;

import cn.mbw.oc.data.user.BaseUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Mabowen
 * @date 2019-12-18 19:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserVO extends BaseUser {
    private static final long serialVersionUID = 5668540453818811192L;
    private String username;
    private String password;
    private String email;
    private String faceImage;


}
