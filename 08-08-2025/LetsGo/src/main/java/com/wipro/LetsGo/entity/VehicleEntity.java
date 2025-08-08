package com.wipro.LetsGo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicle_movement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "veh_id")
     int vehId;

    @Column(name = "lat")
     double lat;

    @Column(name = "log")
     double log; 
}
