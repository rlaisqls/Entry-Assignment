package com.practice.shoppingmall.global.security.auth;

import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(AuthDetails::new)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}