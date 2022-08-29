package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CommonController {

	@RequestMapping("/")
	public ModelAndView homePageController() {
		return new ModelAndView("index");
	}
	@RequestMapping("/register")
	public ModelAndView registerPageController() {
		return new ModelAndView("patientRegister");
	}
	
	@RequestMapping("/beforeLogin")
	public ModelAndView beforeLoginController() {
		return new ModelAndView("beforeLogin");
	}
	@RequestMapping("/login")
	public ModelAndView loginPageController() {
		return new ModelAndView("login");
	}
	
	
	
}