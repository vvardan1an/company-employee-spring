package am.itspace.companyemployeespring.repository;

import am.itspace.companyemployeespring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

}
