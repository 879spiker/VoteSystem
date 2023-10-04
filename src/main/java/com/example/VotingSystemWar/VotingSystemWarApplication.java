package com.example.VotingSystemWar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class VotingSystemWarApplication {

	@GetMapping("user")
	@ResponseBody
	public String user(@RequestParam String name) {
		return String.format("哈囉！%s！", name);
	}

	public static void main(String[] args) {
		SpringApplication.run(VotingSystemWarApplication.class, args);
	}
}

