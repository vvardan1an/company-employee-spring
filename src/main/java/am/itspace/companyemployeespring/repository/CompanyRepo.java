package am.itspace.companyemployeespring.repository;

import am.itspace.companyemployeespring.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company,Integer> {
}
