package com.wipro.LetsGo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.LetsGo.entity.VehicleEntity;

public interface VehicleRepo extends JpaRepository<VehicleEntity, Long> {
}
