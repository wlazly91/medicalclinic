package medicalclinic.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	DataSource dataSource;
 
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
 
	  auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("select login, password, active from users where login=?")
		.authoritiesByUsernameQuery("select login,name from permissions_user, users, permissions where permissions_user.id_user = users.id_users and permissions_user.id_per = permissions.id_permissions and users.login =?");
	}	
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		  http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.and()
			  .formLogin().loginPage("/login").failureUrl("/login?error")
			  .usernameParameter("login").passwordParameter("password")
			.and()
			  .logout().logoutSuccessUrl("/")
			.and()
			  .exceptionHandling().accessDeniedPage("/403")
			.and()
			  .csrf();
		  
		  filter.setEncoding("UTF-8");
	      filter.setForceEncoding(true);
	      http.addFilterBefore(filter,CsrfFilter.class);

	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}