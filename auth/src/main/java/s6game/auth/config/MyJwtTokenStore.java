package s6game.auth.config;

import s6game.auth.dao.UsersDao;
import s6game.auth.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class MyJwtTokenStore extends JwtTokenStore {

    @Autowired
    UsersDao usersDao;

    public MyJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        String username = authentication.getUserAuthentication().getName();
        UsersEntity user = usersDao.findByUsername(username);
        user.setToken(refreshToken.getValue());
        usersDao.save(user);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String token) {
        OAuth2Authentication authentication = super.readAuthentication(token);
        String username = authentication.getUserAuthentication().getName();
        UsersEntity user = usersDao.findByUsername(username);
        if (!token.equals(user.getToken())) {
            return null;
        }
        OAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(token);
        return refreshToken;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        OAuth2Authentication authentication = super.readAuthentication(token.getValue());
        String username = authentication.getUserAuthentication().getName();
        UsersEntity user = usersDao.findByUsername(username);
        user.setToken(null);
        usersDao.save(user);
    }
}
