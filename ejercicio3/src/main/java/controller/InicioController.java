package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicioController {
	@RequestMapping("/inicio")
	public String inicio() {
		return "forward:/WEB-INF/jsp/inicio.jsp";
	}
}
