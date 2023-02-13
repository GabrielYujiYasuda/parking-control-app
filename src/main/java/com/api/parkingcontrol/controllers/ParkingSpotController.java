package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    //THIS IS THE "DATA TRANSFER OBJECT".
    //THE CLASS THAT CARRIES DATA BETWEEN PROCESSES.

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    //Method to SAVE the parking spot
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        //Validating some information
        //Check if plateCar already exists
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        }
        //Check if parkingSpotNumber
        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!!");
        }
        //Check if apartment and block is used
        if (parkingSpotService.existsByApartmentAndApartmentBlock(parkingSpotDto.getApartment(), parkingSpotDto.getApartmentBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!!");
        }

        var parkingSpotModel = new ParkingSpotModel();

        //Setting the date (not able to the user)
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDate.from(LocalDateTime.now(ZoneId.of("UTC"))));

        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

}
