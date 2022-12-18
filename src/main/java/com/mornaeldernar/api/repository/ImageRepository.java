package com.mornaeldernar.api.repository;

import com.mornaeldernar.api.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long>{

}
