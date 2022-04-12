package calculator.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import calculator.springboot.model.Index;
import calculator.springboot.model.Loan;
import calculator.springboot.service.LoanService;


@Controller
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/")
	public String calculator(Model model) {
		model.addAttribute("index",new Index());
		return "login";
		
	}
	
	@GetMapping("/calculate")
	public String loanCalculate(@ModelAttribute("index") Index index, Model model) {
		 List<Loan> loan = loanService.loanCalculate(index);
		 model.addAttribute("loans",loan);
		 return "calculation";
		
	}
	

	
}
