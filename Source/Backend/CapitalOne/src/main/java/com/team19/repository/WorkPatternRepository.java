package com.team19.repository;

import com.team19.entity.WorkPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPatternRepository extends JpaRepository<WorkPattern, Integer>{
}
