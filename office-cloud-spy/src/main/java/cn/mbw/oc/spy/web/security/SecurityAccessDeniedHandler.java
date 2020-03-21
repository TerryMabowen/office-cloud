package cn.mbw.oc.spy.web.security;

import lombok.extern.slf4j.Slf4j;

/**
 * 登陆之后发生的权限不足的处理器
 * @author Mabowen
 * @date 2020-01-10 18:59
 */
@Deprecated
@Slf4j
//@Component
public class SecurityAccessDeniedHandler {
//public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        ResponseResults responseResults = ResponseResults.newFailed("没有权限，请先登陆");
//        httpServletResponse.setCharacterEncoding("utf-8");
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//        httpServletResponse.getWriter().write(new Gson().toJson(responseResults));
//        httpServletResponse.setStatus(403);
//    }
}
