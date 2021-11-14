package com.leolee.reservation.Dao;

import com.leolee.reservation.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query(value = "SELECT * FROM department WHERE name = :depName", nativeQuery = true)
    public Department findDepartmentByName(@Param("depName") String depName);
}
