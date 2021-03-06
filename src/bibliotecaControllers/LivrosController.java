package bibliotecaControllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import daos.LivroDAO;
import models.Livro;

@Controller
public class LivrosController {
	

		@RequestMapping("/livros/form")
		public String form() {
			System.out.println("Chamou meu m�todo de formul�rio");
			return "livros/form";
		}
		
		@PostMapping("/livros")
		public String adicionar(Livro livro) {
			System.out.println("Chamou o m�todo de adicionar");
			System.out.println(livro);
			LivroDAO LivroDAO = new LivroDAO();
			LivroDAO.inserir(livro);
			return "redirect:livros/";
		}
		
		@GetMapping("/livros")
		public ModelAndView listar() {
			System.out.println("Chamou m�todo de listagem");
			LivroDAO livroDAO = new LivroDAO();
			List<Livro> lista = livroDAO.buscar();
			ModelAndView model = new ModelAndView("livros/lista");
			model.addObject("livros", lista);
			return model;
		}
		

		@RequestMapping ("/livros/remover")
		public String remover(Livro livro) {
			System.out.println("Chamou o metodo remover");
			LivroDAO livroDAO = new LivroDAO();
			livroDAO.remover(livro);
			
			return "redirect:../livros";
	}
	}



