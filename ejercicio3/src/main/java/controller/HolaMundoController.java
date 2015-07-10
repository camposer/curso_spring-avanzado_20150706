package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Persona;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HolaMundoController {
	// http://localhost:8080/ejercicio2/holaMundo.do
	@RequestMapping("/holaMundo")
	public @ResponseBody String holaMundo() {
		return "<html>" +
				"<head><title>Hola mundo!</title></head>" +
				"<body>Hola mundo!</body>" +
				"</html>";
	}

	@RequestMapping("/holaMundo1")
	public String holaMundo1() {
		return "/WEB-INF/jsp/holaMundo.jsp";
	}

	@RequestMapping("/holaMundo2")
	public void holaMundo2(HttpServletResponse response) throws IOException {
		response.getWriter().println("<html>" +
				"<head><title>Hola mundo!</title></head>" +
				"<body>Hola mundo!</body>" +
				"</html>");
	}
	
	@RequestMapping("/holaMundo3")
	public String holaMundo3(HttpServletRequest request) throws IOException {
		request.setAttribute("nombre", "Juan");
		return "/WEB-INF/jsp/holaMundo.jsp";
	}
	
	@RequestMapping("/holaMundo4")
	public String holaMundo4(Model model) throws IOException {
		model.addAttribute("nombre", "Juan");
		return "/WEB-INF/jsp/holaMundo.jsp";
	}
	
	@RequestMapping("/holaMundo5")
	public ModelAndView holaMundo5() throws IOException {
		return new ModelAndView("/WEB-INF/jsp/holaMundo.jsp", "nombre", "Juan");
	}

	// http://localhost:8080/ejercicio2/holaMundo6.do?nombre=Rodolfo
	@RequestMapping("/holaMundo6")
	public String holaMundo6(HttpServletRequest request, Model model) throws IOException {
		model.addAttribute("nombre", request.getParameter("nombre"));
		return "/WEB-INF/jsp/holaMundo.jsp";
	}

	// http://localhost:8080/ejercicio2/holaMundo7.do?nombre=Rodolfo
	@RequestMapping("/holaMundo7")
	public String holaMundo7(@RequestParam String nombre, Model model) throws IOException {
		model.addAttribute("nombre", nombre);
		return "/WEB-INF/jsp/holaMundo.jsp";
	}

	// http://localhost:8080/ejercicio2/holaMundo8.do?nombre=Pedro
	@RequestMapping("/holaMundo8")
	public String holaMundo8(Persona p, Model model) throws IOException {
		model.addAttribute("nombre", p.getNombre());
		return "/WEB-INF/jsp/holaMundo.jsp";
	}
}










