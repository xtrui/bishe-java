package fun.tianrui.blog.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author TIANRUI
 */
@Configuration
public class DruidConfig {
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druid() {
//        return new DruidDataSource();
//    }
//
//    //配置一个Druid监控
//
//    //1、配置一个后台的Serverlet
//    @Bean
//    public ServletRegistrationBean<?> statViewServlet() {
//        ServletRegistrationBean<?> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//        Map<String, String> initParams = new HashMap<>(8);
//        initParams.put("loginUsername", "admin");
//        initParams.put("loginPassword", "123456");
//        initParams.put("allow", "");
//        initParams.put("deny", "192.168.2.1");
//        bean.setInitParameters(initParams);
//        return bean;
//    }
//
//    //2、配置一个监控filter
//
//    @Bean
//    public FilterRegistrationBean<?> webStatFilter() {
//        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
//        bean.setFilter(new WebStatFilter());
//        Map<String, String> initParams = new HashMap<>(8);
//        initParams.put("exclusions", "*js,*.css,*.js,/druid/*");
//        bean.setInitParameters(initParams);
//        bean.setUrlPatterns(Collections.singletonList("/*"));
//        return bean;
//    }
}
