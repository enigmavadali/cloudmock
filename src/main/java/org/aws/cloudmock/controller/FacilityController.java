package org.aws.cloudmock.controller;

import org.aws.cloudmock.model.Facilities;
import org.aws.cloudmock.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

//    @PutMapping("/updateFacilityData")
//    public String updateFacility(Facilities facility){
//        return this.facilityService.
//    }

    @GetMapping("/migrateData")
    public String migrateData(){
        System.out.println("Migrating all patients from facility 1");
        return this.facilityService.migrateFacilityPatients();
    }

}
