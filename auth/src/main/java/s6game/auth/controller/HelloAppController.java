package s6game.auth.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import s6game.auth.dao.UsersDao;
import s6game.auth.entity.UserDTO;
import s6game.auth.entity.UsersEntity;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class HelloAppController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UsersDao userRepository;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${s6game.rabbitmq.exchange}")
    private String exchange;
    @Value("${s6game.rabbitmq.routingkey}")
    private String routingkey;

    @PostMapping("/update")
    public UsersEntity updateUser(@RequestBody UsersEntity user){
        UsersEntity realUser = userRepository.findById(user.getId()).get();
        if (user.getUsername() != null){
            realUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null){
            realUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        UsersEntity savedUser = userRepository.save(realUser);
        savedUser.setPassword("");
        rabbitTemplate.convertAndSend(exchange,routingkey,new UserDTO(savedUser));
        return savedUser;
    }
}


