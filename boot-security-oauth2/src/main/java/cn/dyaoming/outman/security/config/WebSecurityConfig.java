package cn.dyaoming.outman.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/token/**", "/oauth/**").permitAll()
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
        web.ignoring().antMatchers("/css/**", "/js/**");
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



    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    // @Bean
    // @Override
    // protected UserDetailsService userDetailsService() {
    // return super.userDetailsService();
    // }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws
    // Exception {
    // auth.inMemoryAuthentication()
    // .withUser("hx")
    // .password("$2a$10$kwLIAqAupvY87OM.O25.Yu1QKEXV1imAv7jWbDaQRFUFWSnSiDEwG").roles("admin")
    // .and()
    // .withUser("hx1")
    // .password("$2a$10$kwLIAqAupvY87OM.O25.Yu1QKEXV1imAv7jWbDaQRFUFWSnSiDEwG")
    // .roles("user");
    // }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.antMatcher("/oauth/**")
    // .authorizeRequests()
    // .antMatchers("/oauth/**")
    // .permitAll()
    // .and()
    // .csrf()
    // .disable();
    // }

    // @Override
    // protected void configure(ClientDetailsServiceConfigurer client) throws
    // Exception {
    //// client.authenticationProvider(myAuthenticationProvider);
    // }

  @Autowired
  private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("SuperAdmin")
                .password("123456")
                .roles("admin")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}