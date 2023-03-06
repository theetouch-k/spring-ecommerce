package com.example.urnshop.config;

import com.example.urnshop.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder encoder;

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Autowired
    public SecurityConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                
                .csrf().disable()
                
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/products").hasAnyRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
                
               .and().formLogin().defaultSuccessUrl("/api/v1/products", true)
               
               .loginPage("/login").permitAll()
                              
               .and()

               .rememberMe()

                    .userDetailsService(userDetailsService)
    
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("password"))
//                .roles(UserRole.USER.name()) //ROLE_USER
//                .authorities(UserRole.ADMIN.getGrantedAuthorities())
//                .build();

// //        UserDetails admin2 = User.builder()
// //                .username("admin2")
// //                .password(encoder.encode("password"))
// ////                .roles(UserRole.ADMIN.name()) //ROLE_ADMIN
// //                .authorities(UserRole.ADMIN.getGrantedAuthorities())
// //                .build();

//        return new InMemoryUserDetailsManager(
//                admin
// //                ,admin2
//        );
//    }
}
