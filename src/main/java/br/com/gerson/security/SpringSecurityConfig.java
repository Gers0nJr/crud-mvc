package br.com.gerson.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	 }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/clientes/**").hasAnyRole("ADMIN")
            .antMatchers("/servicos/**").hasAnyRole("ADMIN")
            .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/clientes")
            .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();
    }
    
    @Autowired
    public void auth(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("$2a$10$eCsran9D8hUnnMkzO87N1OozzqbM/DsYzBV9ZXdvh6ABH.7DnXvO2").roles("ADMIN");
    }
}
