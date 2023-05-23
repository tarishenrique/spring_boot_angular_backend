package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// recebe todos os empregados
	@GetMapping("/employees")
	public List<Employee> getAllEmplyees(){
		return employeeRepository.findAll();
	}
	
	// criar REST API de empregados
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// get empregados por id - REST API
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		
		Employee empregado = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"Não existe registro de empregado com id = " + id));
		return ResponseEntity.ok(empregado);
	}
	
	// update empregados REST API
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee empregadoDetalhe){
		Employee empregado = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"Não existe registro de empregado com id = " + id));
		
		empregado.setFirstName(empregadoDetalhe.getFirstName());
		empregado.setLastName(empregadoDetalhe.getLastName());
		empregado.setEmailid(empregadoDetalhe.getEmailid());
		
		Employee empregadoAtualizado = employeeRepository.save(empregado);
		
		return ResponseEntity.ok(empregadoAtualizado);
		
	}
	

}
