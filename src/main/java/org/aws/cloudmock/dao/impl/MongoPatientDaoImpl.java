package org.aws.cloudmock.dao.impl;

import org.aws.cloudmock.dao.PatientDao;
import org.aws.cloudmock.model.Patient;
import org.aws.cloudmock.repository.MongoPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnExpression("'${source.of.truth}'.equals('mongo')")
public class MongoPatientDaoImpl implements PatientDao {

    @Autowired
    private MongoPatientRepository mongoPatientRepository;

    @Override
    public void saveSinglePatient(Patient patient){
        try{
            this.mongoPatientRepository.save(patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMultiplePatients(List<Patient> patients){
        try{
            this.mongoPatientRepository.saveAll(patients);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not insert multiple records into database");
        }
    }

    @Override
    public Patient getSinglePatientData(int id){
        Optional<Patient> patient;
        try{
            patient = this.mongoPatientRepository.findById(id);
            if(patient.isPresent()){
                return patient.get();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatientData(){
        return this.mongoPatientRepository.findAll();
    }

    @Override
    public String updatePatientRecord(Patient patient) {
        return null;
    }
}
