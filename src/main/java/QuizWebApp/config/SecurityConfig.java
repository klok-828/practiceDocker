package QuizWebApp.config;

import QuizWebApp.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//WebSecurityConfigurerAdapter needs to extended to override some of its methods
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    private JwtFilter jwtFilter;

    @Autowired
    public void setJwtFilter(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    // authentication provider uses the userDetailsService by calling the loadUserByUsername()
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//    }

    //This method is used to configure the security of the application
    //Since we are attaching jwt to a request header manually, we don't need to worry about csrf
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/user/test").permitAll()
                .antMatchers("/api/user/*").hasAuthority("read")
                .antMatchers("/api/user/*").hasAuthority("write")
                .antMatchers("/api/user/*").hasAuthority("update")
                .antMatchers("/api/user/*").hasAuthority("delete")
                .anyRequest()
                .authenticated();
    }
}