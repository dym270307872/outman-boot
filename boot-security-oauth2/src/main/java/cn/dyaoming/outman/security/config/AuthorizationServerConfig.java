package cn.dyaoming.outman.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import cn.dyaoming.outman.security.demo.MyPasswordEncoder;


/**
 * @Description: TODO
 * @author: hx
 * @date: 2021年04月17日 19:15
 */
//@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Bean PasswordEncoder getPasswordEncoder() {
        return new MyPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //7、 配置一个客户端，支持客户端模式、密码模式和授权码模式
        clients.inMemory()  // 采用内存方式。也可以采用 数据库方式
                .withClient("client1") // clientId 
                .authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token") // 授权模式
                .scopes("admin") // 权限范围 
                .redirectUris("http://localhost:3000/admin/hello") // 授权码模式返回code码的回调地址
                // 自动授权，无需人工手动点击 approve
                .autoApprove(true)  
                .secret(getPasswordEncoder().encode("123456"))
                .and()
                .withClient("bms")
                .authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token")
                .scopes("read")
                .redirectUris("http://localhost:3000/user/hello")
                .autoApprove(true)
                .secret(getPasswordEncoder().encode("bms"));
    }

//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("password")
//                .authorizedGrantTypes("password", "refresh_token")
//                .accessTokenValiditySeconds(2000)
//                .resourceIds("rid")
//                .scopes("all")
//                .secret("$2a$10$kwLIAqAupvY87OM.O25.Yu1QKEXV1imAv7jWbDaQRFUFWSnSiDEwG");
//    }

     @Override
     public void configure(AuthorizationServerEndpointsConfigurer endpoints)
     throws Exception {
     endpoints.tokenStore(new InMemoryTokenStore())
     .authenticationManager(authenticationManager)
     .userDetailsService(userDetailsService);
     }



    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security// 开启/oauth/token_key验证端口无权限访问
        .tokenKeyAccess("permitAll()")
        // 开启/oauth/check_token验证端口认证权限访问
        .checkTokenAccess("permitAll()")
        //允许表单认证    请求/oauth/token的，如果配置支持allowFormAuthenticationForClients的，且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter
        .allowFormAuthenticationForClients();
    }
    
}
