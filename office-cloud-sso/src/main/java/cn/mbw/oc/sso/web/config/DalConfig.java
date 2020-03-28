/**
 * 
 */
package cn.mbw.oc.sso.web.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author Bean mabowen
 *
 */
@Slf4j
@Configuration
public class DalConfig {

	/**
	 * 主数据源，支持读写和事务
	 */
	@Bean(name = "dataSource")
	@Primary
	@ConfigurationProperties("spring.datasource.druid")
	public DataSource dataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * 主数据源事务管理器
	 */
	@Bean
	@ConditionalOnBean(name = { "dataSource" })
	public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 *
	 */
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		//url 匹配
		reg.addUrlMappings("/druid/*");
		// IP白名单 (没有配置或者为空，则允许所有访问)
		reg.addInitParameter("allow", "");
		//IP黑名单 (存在共同时，deny优先于allow)
		reg.addInitParameter("deny", "");
		//登录名
//		reg.addInitParameter("loginUsername", this.druidLoginName);
		//登录密码
//		reg.addInitParameter("loginPassword", this.druidPassword);
		// 禁用HTML页面上的“Reset All”功能
		reg.addInitParameter("resetEnable", "false");
		return reg;
	}

	@Bean(name = "druidWebStatFilter")
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		//忽略资源
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
		filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
		return filterRegistrationBean;
	}
}
