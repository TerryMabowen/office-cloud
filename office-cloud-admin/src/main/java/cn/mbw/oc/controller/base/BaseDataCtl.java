package cn.mbw.oc.controller.base;

import cn.mbw.oc.common.throwable.ServiceException;
import cn.mbw.oc.data.user.BaseUser;
import cn.mbw.oc.data.user.vo.UserVO;
import cn.mbw.oc.security.SecurityUtils;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.PostConstruct;
import javax.validation.Validator;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;


/**
 * @author Mabowen
 * @date 2019-12-05 20:02
 */
public class BaseDataCtl {
    @Autowired
    protected Validator validator;

    protected ModelMapper mapper;

    protected void doMapper(ModelMapper mapper) {
    }

    protected <T> Result validate(T fb) {
        return FluentValidator.checkAll().on(fb, new HibernateSupportedValidator<T>().setHiberanteValidator(validator))
                .doValidate()
                .result(toSimple());
    }

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @PostConstruct
    protected void initMapper() {
        mapper = new ModelMapper();
        doMapper(mapper);
    }

    public UserVO getCurrentLoginUser() {
//        UserVO userVO = (UserVO) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
        UserVO userVO = SecurityUtils.getSubject();
        if (userVO == null) {
            throw new ServiceException("用户未登录");
        }
        return userVO;
    }
}
