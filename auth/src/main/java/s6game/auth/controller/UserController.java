package s6game.auth.controller;

import  org.apache.commons.codec.binary.Base64;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import s6game.auth.dao.UsersDao;
import s6game.auth.entity.AuthoritiesEntity;
import s6game.auth.entity.TokenResponse;
import s6game.auth.entity.UserDTO;
import s6game.auth.entity.UsersEntity;
import s6game.auth.service.Authority;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.HashSet;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersDao applicationUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${s6game.rabbitmq.exchange}")
    private String exchange;
    @Value("${s6game.rabbitmq.routingkey}")
    private String routingkey;

    @PostMapping("/sign-up")
    public UsersEntity signUp(@RequestBody UsersEntity user) {
        System.out.println(user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("not broken 1");
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        HashSet<AuthoritiesEntity> set = new HashSet<>();
        set.add(new AuthoritiesEntity(user,Authority.ROLE_ADMIN));
        user.setAuthorities(set);
        user = applicationUserRepository.save(user);
        user.setPassword("");

        rabbitTemplate.convertAndSend(exchange,routingkey,new UserDTO(user));
        return user;
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody UsersEntity usersEntity){
        RestTemplate template = new RestTemplate();
        HttpHeaders header = createHeaders("clientId","client-secret");
        MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
        map.add("username",usersEntity.getUsername());
        map.add("password",usersEntity.getPassword());
        map.add("grant_type","password");
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String, String>>(map,header);
        ResponseEntity<TokenResponse> response = template.postForEntity("http://auth-service:8081/oauth/token",request,TokenResponse.class);
        UsersEntity entity = applicationUserRepository.findByUsername(usersEntity.getUsername());
        UserDTO userDTO = new UserDTO(entity,response.getBody().getAccess_token());
        return userDTO;
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestParam String refreshToken){
        RestTemplate template = new RestTemplate();
        HttpHeaders header = createHeaders("clientId","client-secret");
        MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
        map.add("refresh_token",refreshToken);
        map.add("grant_type","refresh_token");
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String, String>>(map,header);
        ResponseEntity<TokenResponse> response = template.postForEntity("http://auth-service:8081/oauth/token",request,TokenResponse.class);
        return response.getBody();
    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/OK")
    public String OK(Principal principal){
        return principal.getName();
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/OKSecured")
    public String OKSec(Principal principal){
        return principal.getName();
    }

    @GetMapping("/findMe")
    public String findMe(Principal principal){
        return "";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/okadmin")
    public String okadmin(Principal principal){
        return principal.getName();
    }
}
