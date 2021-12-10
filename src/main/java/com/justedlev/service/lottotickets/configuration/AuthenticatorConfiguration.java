package com.justedlev.service.lottotickets.configuration;

import com.justedlev.service.lottotickets.repository.UsersRepository;
import com.justedlev.service.lottotickets.repository.entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Configuration
public class AuthenticatorConfiguration implements UserDetailsService {

    private final UsersRepository usersRepository;

    public AuthenticatorConfiguration(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.usersRepository.findById(username).orElse(null);
        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("User by username: %s not found", username));
        }
        String[] roles = userEntity.getRoles().stream()
                .map(r -> "ROLE_" + r)
                .toArray(String[]::new);
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);
        return new User(userEntity.getUsername(), userEntity.getHashCode(), authorities);
    }

}
