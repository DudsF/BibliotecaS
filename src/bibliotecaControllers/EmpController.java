package bibliotecaControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class EmpController {
	
	@RequestMapping("/emprestimo/form")
	public String form() {
		System.out.println("Chamou meu m�todo de formul�rio");
		return "emprestimo/form";
	}


}
