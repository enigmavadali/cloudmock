package org.aws.cloudmock.service;

import jakarta.annotation.PostConstruct;
import org.aws.cloudmock.dao.PatientDao;
import org.aws.cloudmock.dao.impl.ElasticPatientDaoImpl;
import org.aws.cloudmock.dao.impl.MongoPatientDaoImpl;
import org.aws.cloudmock.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientDao patientDao;
//    @Autowired
//    ElasticPatientDaoImpl elasticPatientDaoImpl;

//    @Value("${source.of.truth}")
//    public String sourceOfTruth;

//    @PostConstruct
//    public void init(){
//        if("elastic".equalsIgnoreCase(sourceOfTruth)){
//            patientDao = new ElasticPatientDaoImpl();
//        } else{
//            patientDao = new MongoPatientDaoImpl();
//        }
//    }

    public void addPatient(Patient patient){
        this.patientDao.saveSinglePatient(patient);
    }

    public void addPatients(List<Patient> patients){
        this.patientDao.saveMultiplePatients(patients);
    }

    public Patient getPatient(int id){
        Patient p = this.patientDao.getSinglePatientData(id);
        if(p == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient with id:" + id + " not in database");
        }
        else{
            return p;
        }
    }

    public List<Patient> getPatients(){
        return this.patientDao.getAllPatientData();
    }

    public String updatePatientData(Patient patient){
        return this.patientDao.updatePatientRecord(patient);
    }
}
