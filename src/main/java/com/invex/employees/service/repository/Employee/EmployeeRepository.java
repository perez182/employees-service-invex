package com.invex.employees.service.repository.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.invex.employees.service.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

    @Query(value = 
      """
      select ID,
      FIRST_NAME,
      MIDDLE_NAME,
      LAST_NAME,
      SECOND_LAST_NAME,
      AGE,
      GENDER,
      BIRTH_DATE,
      POSITION,
      CREATED_AT,
      IS_ACTIVE
      from employees 
      where UPPER(first_name) like UPPER(CONCAT(CONCAT('%',:name),'%'))
      or UPPER(middle_name) like UPPER(CONCAT(CONCAT('%',:name),'%'))           
      """
      ,nativeQuery = true)
      List<Employee> findByNameParcial(@Param("name")String name);
}