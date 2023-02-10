package com.api.parkingcontrol.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private long id;

}
