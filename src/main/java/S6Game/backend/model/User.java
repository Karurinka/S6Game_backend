package S6Game.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User
{
	//fields
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	private int stamina;
	private int level;
	private int exp;
	private int rupies;
	private int crystals;

	//getters
	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	//setters
	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	//constructor
	public User()
	{
	}
}
