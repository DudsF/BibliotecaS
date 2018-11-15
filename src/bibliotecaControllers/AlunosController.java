package bibliotecaControllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import daos.AlunoDAO;
import models.Aluno;


@Controller
public class AlunosController {

	
	@RequestMapping("/alunos/form")
	public String form() {
		System.out.println("Chamou meu método de formulário");
		return "alunos/form";
	}
	
	@PostMapping("/alunos")
	public String adicionar(Aluno aluno) {
		System.out.println("Chamou o método de adicionar");
		System.out.println(aluno);
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.inserir(aluno);
		return "redirect:alunos/";
	}
	
	@GetMapping("/alunos")
	public ModelAndView listar() {
		System.out.println("Chamou método de listagem");
		AlunoDAO AlunoDAO = new AlunoDAO();
		List<Aluno> lista = AlunoDAO.getAlunos();
		ModelAndView model = new ModelAndView("alunos/lista");
		model.addObject("alunos", lista);
		return model;
	}
	
	@RequestMapping ("/alunos/remover")
	public String remover(Aluno aluno) {
		System.out.println("Chamou o metodo remover");
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.remover(aluno);
		
		return "redirect:../alunos";
}
}
