package cn.dyaoming.outman.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/home","/token/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin().loginPage("/token/login")
            .loginProcessingUrl("/token/form")
                .and()
            .logout()
                .permitAll();
        http.sessionManagement().maximumSessions(1).expiredUrl("/token/login");
    }

    
    /**
     * 不拦截静态资源
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**","/js/**");
    }
    
    
    
    /**
     * @Description OAuth2 token持久化接口
     * @Date 2019/7/9 17:45
     * @Version  1.0
     */
	
	  @Bean public TokenStore tokenStore() { 
		  //token保存在内存中（也可以保存在数据库、Redis中）。
	  //如果保存在中间件（数据库、Redis），那么资源服务器与认证服务器可以不在同一个工程中。
	  //注意：如果不保存access_token，则没法通过access_token取得用户信息 
		  return new InMemoryTokenStore(); 
		  // return new RedisTokenStore(redisConnectionFactory);
	  }
	 
    
    
    @Autowired
    private AuthenticationProvider myAuthenticationProvider;
    
    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }
    
    
//    @Override
//    protected void configure(ClientDetailsServiceConfigurer client) throws Exception {
////    	client.authenticationProvider(myAuthenticationProvider);
//    } 
    
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//             User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}