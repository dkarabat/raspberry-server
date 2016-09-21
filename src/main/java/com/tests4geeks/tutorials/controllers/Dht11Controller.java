package com.tests4geeks.tutorials.controllers;

import com.tests4geeks.tutorials.repository.Dht11MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Dht11Controller {

	@Autowired
	Dht11MongoRepository dht11MongoRepository;

	
	@RequestMapping("/sensors")
	public String getSensors(Model model) {
		model.addAttribute("dht11List", dht11MongoRepository.findTop10ByOrderByTimeDesc());
		return "sensors";
	}
	
}
