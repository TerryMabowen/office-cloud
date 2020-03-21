package cn.mbw.oc.spy.web.security;

import lombok.extern.slf4j.Slf4j;

/**
 * 未登陆不能访问url的处理器
 * @author Mabowen
 * @date 2020-01-10 18:54
 */
@Deprecated
@Slf4j
//@Component
public class SecurityAuthenticationEntryPoint {
//public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        ResponseResults responseResults = ResponseResults.newFailed("没有权限，请先登陆");
//        httpServletResponse.setCharacterEncoding("utf-8");
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//        httpServletResponse.getWriter().write(new Gson().toJson(responseResults));
//        httpServletResponse.setStatus(403);
//    }
}
