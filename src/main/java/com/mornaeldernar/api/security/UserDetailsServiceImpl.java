package com.mornaeldernar.api.security;

import com.mornaeldernar.api.entity.User;
import com.mornaeldernar.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El correo no existe"));
        return new UserDetailsImpl(usuario);
    }
}
