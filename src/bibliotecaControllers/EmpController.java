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
	public String form() {
		return "emprestimo/form";
	}


	@PostMapping("/emprestimo")
	public ModelAndView adicionar(int matriculaAluno , long idLivro) {
		
		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = new Aluno();
		aluno = alunoDAO.getByMatricula(matriculaAluno);
		
		if (aluno == null) { 
			ModelAndView model = new ModelAndView("/erro");
			model.addObject("erro", "Matricula " + matriculaAluno + " não existe no banco de dados.");
			return model; 
			
		}
		long idAluno = aluno.getId();
				
		LivroDAO livroDAO = new LivroDAO();
		Livro livro = new Livro();
		livro = livroDAO.getById(idLivro);
		
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setAluno(aluno);
		emprestimo.setLivro(livro);
		emprestimo.setDataEmprestimo(Calendar.getInstance());
		
		
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
	
		if (emprestimoDAO.verificarAluno(idAluno)) {
 			if(emprestimoDAO.verificarLivro(idLivro)) {
 				emprestimoDAO.inserir(emprestimo);
 				return listarEmprestimos();
 			
 			
 			} else {
 				ModelAndView model = new ModelAndView("/erro");
 				model.addObject("erro", "Livro indisponível.");
 				return model;
 				
 			}
 		
 		} else { 
 			ModelAndView model = new ModelAndView("/erro");
			model.addObject("erro", "Aluno já atingiu o limite!");
			return model; 
 		}
	}
	
	@PostMapping("/emprestimo/devolucao")
	public String devolucao(long idAluno, long idLivro){
		
		Aluno aluno = new Aluno();
		Livro livro = new Livro();
		Emprestimo emprestimo = new Emprestimo();
		
		
		AlunoDAO alunoDAO = new AlunoDAO();
		LivroDAO livroDAO = new LivroDAO();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		
		aluno = alunoDAO.getById(idAluno);
		livro = livroDAO.getById(idLivro);
		
		emprestimo.setAluno(aluno);		
		emprestimo.setLivro(livro);
		
		emprestimoDAO.devolucao(emprestimo);
		
		return "redirect:/emprestimo";

	}

	@GetMapping("/emprestimo")
	public ModelAndView listarEmprestimos(){
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDAO.getEmprestimos();
		ModelAndView model = new ModelAndView("emprestimo/lista");
		model.addObject("emprestimos", lista);
		return model;
	}
	
	@GetMapping("/emprestimo/atrasados")
	public ModelAndView listarEmprestimosAtrasados(){
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDAO.getEmprestimosAtrasados();
		ModelAndView model = new ModelAndView("emprestimo/lista");
		model.addObject("emprestimos", lista);
		return model;
	}
	
	@GetMapping("/emprestimo/ativos")
	public ModelAndView listarEmprestimosAtivos(){
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		List<Emprestimo> lista = emprestimoDAO.getEmprestimosAtivos();
		ModelAndView model = new ModelAndView("emprestimo/lista");
		model.addObject("emprestimos", lista);
		return model;
	}
	

}
