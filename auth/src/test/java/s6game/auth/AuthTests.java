package s6game.auth;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import s6game.auth.dao.UsersDao;
import s6game.auth.entity.AuthoritiesEntity;
import s6game.auth.entity.TokenResponse;
import s6game.auth.entity.UsersEntity;
import s6game.auth.service.Authority;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTests {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TestRestTemplate  restTemplate;

    @Autowired
    private UsersDao usersDao;
    @Test
    public void contextLoads() {

    }


    @Before
    public void setUp() {
    }

//    @Test
//
//    public void signUp(){
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("username", "TestUser21");
//        parameters.put("password", "TestPass21");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        ResponseEntity<String> response = restTemplate.postForEntity("/users/sign-up",parameters,String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        UsersEntity user = usersDao.findByUsername("TestUser21");
//        assertThat(user).isNotEqualTo(null);
//    }
//
//    @Test
//    public void signIn(){
//        //Making the User
//        HashSet<AuthoritiesEntity> set = new HashSet<>();
//        UsersEntity user = new UsersEntity(bCryptPasswordEncoder.encode("TestPass21"),"TestUser21",set);
//        set.add(new AuthoritiesEntity(user, Authority.ROLE_ADMIN));
//        usersDao.save(user);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("username", "TestUser21");
//        parameters.put("password", "TestPass21");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        UsersEntity test = usersDao.findByUsername("TestUser21");
//        ResponseEntity<TokenResponse> response = restTemplate.postForEntity("/users/login", parameters, TokenResponse.class);
//        assertThat(response.getBody().getAccess_token()).isNotNull();
//    }
//
//    @Test
//    public void noToken(){
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization","Bearer " + "");
//        ResponseEntity<String> response = restTemplate.getForEntity("/users/OK",String.class,headers);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//    }
//
//    @Test
//    public void adminAuthorities(){
//        HashSet<AuthoritiesEntity> set = new HashSet<>();
//        UsersEntity user = new UsersEntity(bCryptPasswordEncoder.encode("AdminPass21"),"AdminUser21",set);
//        set.add(new AuthoritiesEntity(user, Authority.ROLE_ADMIN));
//        usersDao.save(user);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("username", "AdminUser21");
//        parameters.put("password", "AdminPass21");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        ResponseEntity<TokenResponse> response = restTemplate.postForEntity("/users/login",parameters,TokenResponse.class);
//        UsersEntity testing = usersDao.findByUsername("AdminUser21");
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.add("Authorization","Bearer " + response.getBody().getAccess_token());
//        HttpEntity entity = new HttpEntity(headers2);
//        ResponseEntity<String> response2 = restTemplate.exchange("/users/OKSecured",HttpMethod.GET,entity,String.class);
//        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//    @Test
//    public void adminAuthoritiesUnauthorized(){
//        HashSet<AuthoritiesEntity> set = new HashSet<>();
//        UsersEntity user = new UsersEntity(bCryptPasswordEncoder.encode("TestPass23"),"TestUser23",set);
//        set.add(new AuthoritiesEntity(user, Authority.ROLE_USER));
//        usersDao.save(user);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("username", "TestUser23");
//        parameters.put("password", "TestPass23");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        ResponseEntity<TokenResponse> response = restTemplate.postForEntity("/users/login",parameters,TokenResponse.class);
//        UsersEntity ent = usersDao.findByUsername("TestUser23");
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.add("Authorization","Bearer " + response.getBody().getAccess_token());
//        HttpEntity entity = new HttpEntity(headers2);
//        ResponseEntity<String> response2 = restTemplate.exchange("/users/OKSecured",HttpMethod.GET,entity,String.class);
//        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
//    }
//
//    @Test
//    public void userAuthorities(){
//        HashSet<AuthoritiesEntity> set = new HashSet<>();
//        UsersEntity user = new UsersEntity(bCryptPasswordEncoder.encode("TestPass22"),"TestUser22",set);
//        set.add(new AuthoritiesEntity(user, Authority.ROLE_USER));
//        usersDao.save(user);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("username", "TestUser22");
//        parameters.put("password", "TestPass22");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        ResponseEntity<TokenResponse> response = restTemplate.postForEntity("/users/login",parameters,TokenResponse.class);
//
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.add("Authorization","Bearer " + response.getBody().getAccess_token());
//        HttpEntity entity = new HttpEntity(headers2);
//        ResponseEntity<String> response2 = restTemplate.exchange("/users/OK",HttpMethod.GET,entity,String.class);
//        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
}
