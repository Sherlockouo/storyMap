package com.storymap.config;

import com.storymap.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private MyUserDetailService myUserDetailService;

    private String[] user = {"/user/login","/user/register","/admin/login"};

    private String[] notify={"/notify/last"};

    private String[] posts ={"/poster/all","/poster/local","/poster/info/**","/poster/search/**","/poster/type/**"};


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {    //auth.inMemoryAuthentication()
        httpSecurity
                // 配置Basic登录
                //.httpBasic()
                // 配置登出页面
                //.logout().logoutUrl("/logout").logoutSuccessUrl("/")
                // 开放接口访问权限，不需要登录授权就可以访问
                .authorizeRequests()
                    .antMatchers(notify).permitAll()
                    .antMatchers(user).permitAll()
                    .antMatchers(posts).permitAll()
                    .antMatchers(
                            "/chat/*",
                            "/code/**",
                            "/upload/**",
                            "/files/**",
                            "/doc.html"
                    ).permitAll()
                    .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui","/swagger-resources", "/swagger-resources/configuration/security","/swagger-ui.html", "/webjars/**").permitAll()
                    // 其余所有请求全部需要鉴权认证
                    .anyRequest().authenticated()
                    // 关闭session 使用token 进行会话
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().cors()
                    // 关闭跨域保护;
                    .and().csrf().disable();

        httpSecurity.addFilterBefore(this.jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");//修改为添加而不是设置
        configuration.addAllowedMethod("*");//修改为添加而不是设置
        configuration.addAllowedHeader("*");//这里很重要，起码需要允许 Access-Control-Allow-Origin
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
