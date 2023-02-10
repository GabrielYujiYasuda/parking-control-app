package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository //No need to write. Only to be easier reading.
//Repositories are basically an abstraction to the database access.
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> /*Model, Identity*/ {

}
