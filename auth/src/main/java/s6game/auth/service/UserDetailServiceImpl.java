package s6game.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import s6game.auth.config.Users;
import s6game.auth.dao.UsersDao;
import s6game.auth.entity.AuthoritiesEntity;
import s6game.auth.entity.UsersEntity;
import java.util.HashSet;
import java.util.Set;

@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String username)  {

        UsersEntity usersEntity = usersDao.findByUsername(username);

        if (usersEntity == null) {
            throw new UsernameNotFoundException("user not found.");
        }

        Set<AuthoritiesEntity> roles = usersEntity.getAuthorities();

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (AuthoritiesEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority().toString()));
        }

        Users users = new Users();
        users.setUsername(usersEntity.getUsername());
        users.setPassword(usersEntity.getPassword());
        users.setAccountNonExpired(usersEntity.isAccountNonExpired());
        users.setCredentialsNonExpired(usersEntity.isCredentialsNonExpired());
        users.setAccountNonLocked(usersEntity.isAccountNonExpired());
        users.setEnabled(usersEntity.isEnabled());
        users.setAuthorities(authorities);
        return users;
    }
}
