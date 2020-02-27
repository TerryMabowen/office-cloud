package cn.mbw.oc.security;

import cn.mbw.oc.common.results.ResponseResults;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆之后发生的权限不足的处理器
 * @author Mabowen
 * @date 2020-01-10 18:59
 */
@Deprecated
@Slf4j
//@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResults responseResults = ResponseResults.newFailed("没有权限，请先登陆");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(new Gson().toJson(responseResults));
        httpServletResponse.setStatus(403);
    }
}
