package com.niit.Controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.ServiceDAO.UserServiceDAO;
import com.niit.model.User;
import com.niit.others.email;

@Controller
@RequestMapping("/users")
public class UserController 
{
	User user;
	@Autowired
	UserServiceDAO userDAO;
	@RequestMapping("/register")
	public String register(ModelMap map)
	{
		map.addAttribute("users", new User());
		return "Register";
	}
	@PostMapping("/register")
	public String Register(@ModelAttribute("users")User user,ModelMap map)
	{
		userDAO.addUser(user);
		Random r=new Random();
		int random=r.nextInt(8999)+1000;
		email email=new email(user.getEmail(), "Welcome to the Demo", "Your OTP is:"+random);
		email.sendEmail();
		map.addAttribute("otp", random);
		map.addAttribute("username",user.getUsername());
		return "OTP";
	}
	@PostMapping("/otp")
	public String OTP(@RequestParam("userOTP")int userotp,@RequestParam("otp")int otp,@RequestParam("username")String username,ModelMap map)
	{
		user=userDAO.displayUserByUsername(username);
		user.setStatus(true);
		if(userotp==otp)
		{
			userDAO.updateUser(user);
			return "redirect:/";
		}
		else
		{
			Random r=new Random();
			int random=r.nextInt(8999)+1000;
			email email=new email(user.getEmail(), "New OTP", "Your OTP is:"+random);
			email.sendEmail();
			map.addAttribute("otp", random);
			map.addAttribute("username",user.getUsername());
			return "OTP";
		}
	}
	@RequestMapping("/login")
	public String login()
	{
		return "Login";
	}
	@RequestMapping("/username")
	public String username()
	{
		return "Username";
	}
	@PostMapping("/username")
	public String usernameValidate(@RequestParam("username")String username,ModelMap map)
	{
		user=userDAO.displayUserByUsername(username);
		Random r=new Random();
		int random=r.nextInt(8999)+1000;
		email email=new email(user.getEmail(), "Forgot password OTP", "Your OTP is:"+random);
		email.sendEmail();
		map.addAttribute("otp", random);
		map.addAttribute("username",user.getUsername());
		return "SendOTP";
	}
	@PostMapping("/checkOTP")
	public String checkOTP(@RequestParam("userOTP")int userotp,@RequestParam("otp")int otp)
	{
		if(userotp==otp)
		{
			return "changePassword";
		}
		else
		{
			return "redirect:/users/login";
		}
	}
	@PostMapping("/confirmPassword")
	public String confirmPassword(@RequestParam("pass")String pass,@RequestParam("cpass")String cpass)
	{
		if(pass.equals(cpass))
		{
			pass=new BCryptPasswordEncoder().encode(pass);
			user.setPassword(pass);
			userDAO.updateUser(user);
			return "redirect:/users/login";
		}
		else
		{
			return "changePassword";
		}
		
	}

}
