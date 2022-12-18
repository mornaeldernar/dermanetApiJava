package com.mornaeldernar.api.repository;

import com.mornaeldernar.api.entity.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic,Long>{

}
