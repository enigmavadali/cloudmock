package org.aws.cloudmock.controller;

import jakarta.annotation.PostConstruct;
import org.aws.cloudmock.model.Patient;
import org.aws.cloudmock.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {


    @Autowired
    PatientService patientService;

    @PostMapping("/addPatient")
    public void addSinglePatient(@RequestBody Patient patient){
        this.patientService.addPatient(patient);
    }

    @PostMapping("/addPatients")
    public void addSinglePatient(@RequestBody List<Patient> patients){
        System.out.println("got api call ");
        this.patientService.addPatients(patients);
    }

    @GetMapping("/getPatient/{id}")
    public Patient getSinglePatient(@PathVariable int id){
        return this.patientService.getPatient(id);
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients(){
        return this.patientService.getPatients();
    }

    @PutMapping("/updatePatient")
    public String updatePatient(@RequestBody Patient patient){
        return this.patientService.updatePatientData(patient);
    }
}
