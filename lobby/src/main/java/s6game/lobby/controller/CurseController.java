package s6game.lobby.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("/curse")
public class CurseController {

    @GetMapping("/{message}")
    public String getCensoredMessage(@PathVariable String message){
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("add FAAS here" + message, String.class);
        return result;
    }
}
