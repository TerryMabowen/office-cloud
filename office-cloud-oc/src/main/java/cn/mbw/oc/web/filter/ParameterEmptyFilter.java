package cn.mbw.oc.web.filter;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 请求参数拦截器
 *
 * @author Mabowen
 * @date 2020-03-16 11:39
 */
@Configuration
@WebFilter(urlPatterns = "/**")
public class ParameterEmptyFilter implements Filter {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String method = ((HttpServletRequest) request).getMethod();
        if (HttpMethod.POST.name().equals(method)) {
            ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper((HttpServletRequest) request);
            chain.doFilter(requestWrapper, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
