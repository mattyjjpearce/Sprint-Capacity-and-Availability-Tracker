package com.team19.repository;

import com.team19.entity.EmployeeLeaveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeLeaveInfoRepository extends JpaRepository<EmployeeLeaveInfo, Integer> {
}
