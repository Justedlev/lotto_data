package justedlev.lotto_data.configuration;

import justedlev.lotto_data.repository.UsersRepository;
import justedlev.lotto_data.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UsersRepository usersRepository;

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
