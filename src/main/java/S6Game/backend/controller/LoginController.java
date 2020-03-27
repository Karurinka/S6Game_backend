package S6Game.backend.controller;

import S6Game.backend.model.User;
import S6Game.backend.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController
{
	@Autowired
	private LoginRepository loginRepository;

	@PostMapping(path ="/register")
	public boolean register(User user)
	{
		User createdUser = null;
		if (loginRepository.findByUsernameNotLike(user.getUsername()) == null) {
			createdUser = loginRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		}
		return true;
	}
}
