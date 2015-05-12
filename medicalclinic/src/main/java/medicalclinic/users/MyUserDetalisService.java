package medicalclinic.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import medicalclinic.db.Permissions;
import medicalclinic.db.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetalisService implements UserDetailsService {

	@Autowired
	private UserApp userApp;
	
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
		
		Users users =  userApp.findByUserName(userName);
		
		List<GrantedAuthority> authorities = buildUserAuthority(users.getUserRole());
		
		return buildUserForAuthentication(users, authorities);
	}

	/**
	 * Convert Users to User SpringSecurity
	 * */
	private User buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
			return new User(user.getLogin(), user.getPassword(), authorities);
		}
	
	/**
	 * Build User Authority
	 * */
	private List<GrantedAuthority> buildUserAuthority(Set<Permissions> userRoles) {
		 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (Permissions userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
		}
 
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
	}
	
}
