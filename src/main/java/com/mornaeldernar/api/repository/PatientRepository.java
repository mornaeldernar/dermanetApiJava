package com.mornaeldernar.api.repository;

import com.mornaeldernar.api.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface PatientRepository  extends JpaRepository<Patient,Long> {
    Page<Patient> findByNameContaining(String name, Pageable pageable);
}
