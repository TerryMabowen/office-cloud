package cn.mbw.oc.sso.spi.data.user.vo;

import cn.mbw.oc.sso.spi.data.user.BaseUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author Mabowen
 * @date 2019-12-18 19:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVO extends BaseUser {
    private static final long serialVersionUID = 5668540453818811192L;
    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat
     */
    private String sourceType;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 头像地址
     */
    private String headPic;

    /**
     * QQ号码
     */
    private String qq;

    /**
     * 微信号
     */
    private String weiXin;

    /**
     * 性别，1男，2女
     */
    private Integer gender;

    /**
     * 会员等级
     */
    private Integer userLevel;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 经验值
     */
    private Integer experienceValue;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
}
