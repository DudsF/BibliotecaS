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
			System.out.println("Chamou meu método de formulário");
			return "livros/form";
		}
		
		@PostMapping("/livros")
		public String adicionar(Livro livro) {
			System.out.println("Chamou o método de adicionar");
			System.out.println(livro);
			LivroDAO LivroDAO = new LivroDAO();
			LivroDAO.inserir(livro);
			return "livros/tudoCerto";
		}
		
		@GetMapping("/livros")
		public ModelAndView listar() {
			System.out.println("Chamou método de listagem");
			LivroDAO LivroDAO = new LivroDAO();
			List<Livro> lista = LivroDAO.getLivro();
			ModelAndView model = new ModelAndView("livros/lista");
			model.addObject("livros", lista);
			return model;
		}
	}


