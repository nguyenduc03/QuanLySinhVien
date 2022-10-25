package com.example.qlsv.Services;

import com.example.qlsv.Entities.RoleUser;
import com.example.qlsv.Repositories.RoleRepository;
import com.example.qlsv.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.qlsv.Entities.User dataUser = userRepository.GetUserByName(username);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        RoleUser roleUser = repository.getRoleUserBy(dataUser.getUserId());
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleUser.getCode());
        grantedAuthorities.add(grantedAuthority);
        return new User(dataUser.getUserName(), dataUser.getPassword(), grantedAuthorities);
    }
}
