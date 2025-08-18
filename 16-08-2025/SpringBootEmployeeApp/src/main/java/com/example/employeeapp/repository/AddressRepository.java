package com.example.employeeapp.repository;

import com.example.employeeapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
