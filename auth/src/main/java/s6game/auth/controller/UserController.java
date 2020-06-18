package s6game.auth.controller;

import s6game.auth.dao.UsersDao;
import s6game.auth.entity.AuthoritiesEntity;
import s6game.auth.entity.TokenResponse;
import s6game.auth.entity.UsersEntity;
import s6game.auth.service.Authority;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.HashSet;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private UsersDao applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UsersDao applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UsersEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        HashSet<AuthoritiesEntity> set = new HashSet<>();
        set.add(new AuthoritiesEntity(user,Authority.ROLE_ADMIN));
        user.setAuthorities(set);
        user = applicationUserRepository.save(user);

        UsersEntity guestUser = new UsersEntity();
        guestUser.setPassword(bCryptPasswordEncoder.encode(user.getUsername()+"guestPassword"));
        guestUser.setAccountNonExpired(true);
        guestUser.setCredentialsNonExpired(true);
        guestUser.setEnabled(true);
        guestUser.setUsername(user.getUsername()+"guestUsername");
        HashSet<AuthoritiesEntity> set2 = new HashSet<>();
        set2.add(new AuthoritiesEntity(guestUser,Authority.ROLE_USER));
        guestUser.setAuthorities(set2);
        guestUser = applicationUserRepository.save(guestUser);

        return "";
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

    @GetMapping("/getGuestToken")
    public TokenResponse generateToken(HttpServletRequest httpServletRequest){
        RestTemplate template = new RestTemplate();
        HttpHeaders header = createHeaders("clientId","client-secret");
        MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
        map.add("username",httpServletRequest.getUserPrincipal().getName() + "guestUsername");
        map.add("password",httpServletRequest.getUserPrincipal().getName()+ "guestPassword");
        map.add("grant_type","password");
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String, String>>(map,header);
        ResponseEntity<TokenResponse> response = template.postForEntity("http://20.50.250.38:8081/oauth/token",request,TokenResponse.class);
        return response.getBody();
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody UsersEntity usersEntity){
        RestTemplate template = new RestTemplate();
        HttpHeaders header = createHeaders("clientId","client-secret");
        MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
        map.add("username",usersEntity.getUsername());
        map.add("password",usersEntity.getPassword());
        map.add("grant_type","password");
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String, String>>(map,header);
        ResponseEntity<TokenResponse> response = template.postForEntity("http://20.50.250.38:8081/oauth/token",request,TokenResponse.class);
        return response.getBody();
    }


    // @PreAuthorize("hasRole('ROLE_USER')")
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
        return "";// ((Role)applicationUserRepository.findByUsername(principal.getName()).getRoles().toArray()[0]).getRoleName();
    }
}
