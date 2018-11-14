package bibliotecaControllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import daos.AlunoDAO;
import daos.EmprestimoDAO;
import daos.LivroDAO;
import models.Aluno;
import models.Emprestimo;
import models.Livro;

@Controller
public class EmpController {
	@RequestMapping("/emprestimo/form")
	public ModelAndView form() {
		System.out.println("Chamou o meu método");
		AlunoDAO alunoDAO = new AlunoDAO();
		List<Aluno> listaA = alunoDAO.getAlunos();

		LivroDAO livroDAO = new LivroDAO();
		List<Livro> listaL = livroDAO.buscar();

		ModelAndView model = new ModelAndView("emprestimo/form");

		model.addObject("alunos", listaA);
		model.addObject("livros", listaL);

		return model;

	}

	@PostMapping("/emprestimo")
	public String inserir(Emprestimo emprestimo) {
		System.out.println("Chamou o método de adicionar");
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		emprestimoDAO.inserir(emprestimo);

		return "redirect:/emprestimo/";
	}

	@GetMapping("/emprestimo")
	public ModelAndView listar() {
		System.out.println("Chamou o metódo de listagem");
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDAO.getLista();
		ModelAndView model = new ModelAndView("emprestimo/list");
		model.addObject("emprestimo", lista);
		return model;
	}

	@GetMapping("/emprestimo/abertos")
	public ModelAndView listarAbertos() {
		System.out.println("Chamou o metódo de listagem");
		EmprestimoDAO emprestimoDao = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDao.getListaAbertos();
		ModelAndView model = new ModelAndView("emprestimo/listaEmpreAbertos");
		model.addObject("emprestimo", lista);
		return model;
	}

	@GetMapping("/emprestimo/atrasados")
	public ModelAndView listarAtrasado() {
		System.out.println("Chamou o metódo de listagem");
		EmprestimoDAO emprestimoDao = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDao.getListaAtraso();
		ModelAndView model = new ModelAndView("emprestimo/listaEmpreAtrasados");
		model.addObject("emprestimo", lista);
		return model;
	}
		@RequestMapping("/emprestimo/devolucao")
		public String devolucao(Emprestimo emprestimo) {
			System.out.println("Chamou o método devolução");
			EmprestimoDAO emprestimoDao = new EmprestimoDAO();
			System.out.println(emprestimo);
			emprestimoDao.devolucao(emprestimo);
			return "redirect:../emprestimo/abertos";

		}
}
