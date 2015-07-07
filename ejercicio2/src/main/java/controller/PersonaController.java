package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persona/*")
public class PersonaController {
	@RequestMapping({ "", "inicio" })
	public String listar() {
		return "/WEB-INF/jsp/persona/inicio.jsp";
	}

}
