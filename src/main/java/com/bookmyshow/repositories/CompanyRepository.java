package com.bookmyshow.repositories;

import com.bookmyshow.enums.CompanyType;
import com.bookmyshow.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    public List<Company> findByCompanyType(CompanyType companyType);
}
