package com.tgi.sd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tgi.sd.dao.UserDAO;
import com.tgi.sd.domain.RoleVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.SMSBusinessException;

public class MyUserDetailsService implements UserDetailsService {

	private UserDAO userDAO;

	
	public UserDetails loadUserByUsername(String username, String roleId)
			throws UsernameNotFoundException, SMSBusinessException {
		String emailAddress = null;
		UserVO user = userDAO.getUserByUserName(username, roleId, emailAddress);

		List<GrantedAuthority> authorities = buildUserAuthority(userDAO
				.getAllRole());

		return buildUserForAuthentication(user, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<RoleVO> list) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (RoleVO userRole : list) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}

	private UserDetails buildUserForAuthentication(UserVO user,
			List<GrantedAuthority> authorities) {
		//TODO 
		return new User(user.getUserName(), user.getPassword(), true, true,
				true, true, authorities);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	
}