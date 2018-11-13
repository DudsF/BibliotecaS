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
	@RequestMapping("/emprestimos/form")
	public ModelAndView form() {
		System.out.println("Chamou o meu método");
		AlunoDAO alunoDao = new AlunoDAO();
		List<Aluno> listaA = alunoDao.getLista();

		LivroDAO livroDao = new LivroDAO();
		List<Livro> listaL = livroDao.getLista();

		ModelAndView model = new ModelAndView("emprestimos/formEmprestimo");

		model.addObject("alunos", listaA);
		model.addObject("livros", listaL);

		return model;

	}

	@PostMapping("/emprestimos")
	public String adicionar(Emprestimo emprestimo) {
		System.out.println("Chamou o método de adicionar");
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		emprestimoDAO.inserir(emprestimo);

		return "redirect:/emprestimos";
	}

	@GetMapping("/emprestimos")
	public ModelAndView listar() {
		System.out.println("Chamou o metódo de listagem");
		EmprestimoDAO emprestimoDao = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDao.getLista();
		ModelAndView model = new ModelAndView("emprestimos/listaEmprestimo");
		model.addObject("emprestimos", lista);
		return model;
	}

	@GetMapping("/emprestimos/abertos")
	public ModelAndView listarAbertos() {
		System.out.println("Chamou o metódo de listagem");
		EmprestimoDAO emprestimoDao = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDao.getListaAbertos();
		ModelAndView model = new ModelAndView("emprestimos/listaEmpreAbertos");
		model.addObject("emprestimos", lista);
		return model;
	}

	@GetMapping("/emprestimos/atrasados")
	public ModelAndView listarAtrasado() {
		System.out.println("Chamou o metódo de listagem");
		EmprestimoDAO emprestimoDao = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDao.getListaAtraso();
		ModelAndView model = new ModelAndView("emprestimos/listaEmpreAtrasados");
		model.addObject("emprestimos", lista);
		return model;
	}
		@RequestMapping("/emprestimos/devolucao")
		public String devolucao(Emprestimo emprestimo) {
			System.out.println("Chamou o método devolução");
			EmprestimoDAO emprestimoDao = new EmprestimoDAO();
			System.out.println(emprestimo);
			emprestimoDao.devolucao(emprestimo);
			return "redirect:../emprestimos/abertos";

		}
}
