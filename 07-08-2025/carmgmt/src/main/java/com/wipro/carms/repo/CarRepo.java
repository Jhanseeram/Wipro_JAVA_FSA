package com.wipro.carms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.carms.dto.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long>{

}
