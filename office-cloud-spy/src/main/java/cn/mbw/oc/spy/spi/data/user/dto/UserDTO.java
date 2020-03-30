package cn.mbw.oc.spy.spi.data.user.dto;

import cn.mbw.oc.spy.spi.data.user.BaseUser;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Mabowen
 * @date 2019-12-18 19:33
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserDTO extends BaseUser {
    private static final long serialVersionUID = -3808490381429626982L;
    private String password;
    private String email;
    private String faceImage;
}
