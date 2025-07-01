package com.verma.controller;
import com.verma.repository.EmpRepository;
import com.verma.service.EmpService;
import com.verma.service.EmpServiceImpl;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.verma.entity.Employee;

@Controller
public class HomeController {

    private final EmpServiceImpl empServiceImpl;
	
	@Autowired
	private EmpService empService;

    private final EmpRepository empRepository;

    HomeController(EmpRepository empRepository, EmpServiceImpl empServiceImpl) {
        this.empRepository = empRepository;
        this.empServiceImpl = empServiceImpl;
    }

	@GetMapping("/")
	public String index(Model m) {
		List<Employee> list = empServiceImpl.getAllEmp();
		m.addAttribute("empList" , list);
		return "index";
	}
	
	@GetMapping("/loadEmpSave")
	public String loadEmpSave() {
	  return "emp_save";
	}
	
	@GetMapping("/EditEmp/{id}")
	public String EditEmp(@PathVariable int id , Model m) {
		//System.out.println(id);
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp" , emp);
		return"edit_emp";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp , HttpSession session) {
		
		//System.out.println(emp);
		
		Employee newEmp = empService.saveEmp(emp);
		
		if(newEmp != null) {
			
			session.setAttribute("msg", "Register successfully");
		}
		else {
			session.setAttribute("msg", "Something went wrong on server");
		}
		return "redirect:/loadEmpSave";
	}
	
	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee emp , HttpSession session) {
		
		//System.out.println(emp);
		
		Employee updateEmp = empService.saveEmp(emp);
		
		if(updateEmp != null) {
			
			session.setAttribute("msg", "Update successfully");
		}
		else {
			session.setAttribute("msg", "Something went wrong on server");
		}
		return "redirect:/";
	}
	
	@GetMapping("/deleteEmp/{id}")
	public String loadEmpSave(@PathVariable int id , HttpSession session) {
		boolean f = empService.deleteEmp(id);
		
		if(f) {
			session.setAttribute("msg" , "Deleted Successfully");
		}
		else {
			session.setAttribute("msg", "Something went wrong on server");
		}
	  return "redirect:/";
	}

}
