package com.hr.demo.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.hr.demo.entity.Employee;
import com.hr.demo.projection.EmployeeProjection;
import com.hr.demo.projection.HRStatisticProjection;

@Repository
@Transactional
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	
	
	List<Employee> findBySalary (Double salary, String name);	
	
	List<Employee> findByDepartment (@Param("deptId") Long deptId);
	
	List<Employee> findByFirstNameContainingAndDepartmentNameContaining(String empName, String deptName);	
	
	Long countByFirstNameContainingAndDepartmentNameContaining(String empName, String deptName);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	Long deleteByFirstNameContainingAndDepartmentNameContaining(String empName, String deptName);
	
	
	//-------------- For JPQL ----------------------------------------------------
	@Query(value = "select emp from #{#entityName} emp where (:empName is null or emp.firstName like :empName)")
	Page<EmployeeProjection> filter(@Param("empName") String name, Pageable pageable);
	
	//-----------For Sql Native --------------------
	@Query(value = "select * from hr_employees emp where emp.first_Name like :empName", nativeQuery = true)
	List<Employee> filterNative(@Param("empName") String name);
	
	List<Employee> findByDepartmentId (Long deptId);
	
	@Query(value = "select (select count(*) from hr_departments) deptCount,"
			+ " (select count(*) from hr_employees) empCount,"
			+ " (select count(*) from sec_users ) userCount"
			, nativeQuery = true)
	HRStatisticProjection getHRStatistic ();
	
	
//	@Query(value = "select emp from Employee emp join emp.department dept where dept.id = :deptId")
//	List<Employee> findByDepartment (Long deptId);

}
