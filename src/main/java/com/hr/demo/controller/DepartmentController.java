package com.hr.demo.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hr.demo.entity.Department;
import com.hr.demo.service.DepartmentService;


@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/{id}")
	public Department findById(@PathVariable Long id) {

		return departmentService.findById(id);
	}

	@PostMapping()
	public Department insert(@RequestBody Department dept) {

		return departmentService.insert(dept);
	}

	@PutMapping()
	public Department update(@RequestBody Department dept) {

		return departmentService.update(dept);
	}

	@GetMapping()
	public List<Department> findAll() {

		return departmentService.findAll();
	}

	@DeleteMapping("/by-name/{deptName}")
	public ResponseEntity<?> deleteByName(@PathVariable String deptName) {

		return ResponseEntity.ok(departmentService.deleteByName(deptName));
	}
}
