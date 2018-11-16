package bibliotecaControllers;


import java.util.Calendar;
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
		return "redirect:/emprestimo";
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

	@GetMapping("/emprestimo/empAbertos")
	public ModelAndView listarAbertos() {
		System.out.println("Chamou o metódo de listagem de emprestimos abertos");
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDAO.getAcessiveis();
		ModelAndView model = new ModelAndView("emprestimo/list");
		model.addObject("emprestimo", lista);
		return model;
	}

	@GetMapping("/emprestimo/empAtrasados")
	public ModelAndView listarAtrasado() {
		System.out.println("Chamou o metódo de listagem");
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDAO.getAtrasados();
		ModelAndView model = new ModelAndView("emprestimo/empAtrasados");
		model.addObject("emprestimo", lista);
		return model;
	}
		@RequestMapping("/emprestimo/devolucao")
		public String devolucao(Long aluno, Long livro ) {
			System.out.println("Chamou o método devolução");
			Aluno aluno1 = new Aluno();
			Livro livro1 = new Livro();
			Emprestimo emprestimo = new Emprestimo();
			AlunoDAO alunoDAO = new AlunoDAO();
			LivroDAO livroDAO = new LivroDAO();
			EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
			aluno1 = alunoDAO.getById(aluno);
			emprestimo.setAluno(aluno1);		
			livro1 = livroDAO.getById(livro);
			emprestimo.setLivro(livro1);
			
			emprestimoDAO.devolucao(emprestimo);
			
			return "redirect:../emprestimo";

		}
}
