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
		System.out.println("Chamou meu m�todo de formul�rio");
		return "alunos/form";
	}
	
	@PostMapping("/alunos")
	public String adicionar(Aluno aluno) {
		System.out.println("Chamou o m�todo de adicionar");
		System.out.println(aluno);
		AlunoDAO AlunoDAO = new AlunoDAO();
		AlunoDAO.inserir(aluno);
		return "alunos/tudoCerto";
	}
	
	@GetMapping("/alunos")
	public ModelAndView listar() {
		System.out.println("Chamou m�todo de listagem");
		AlunoDAO AlunoDAO = new AlunoDAO();
		List<Aluno> lista = AlunoDAO.getList();
		ModelAndView model = new ModelAndView("alunos/lista");
		model.addObject("alunos", lista);
		return model;
	}
}