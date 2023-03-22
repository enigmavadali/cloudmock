package org.aws.cloudmock.dao;

import org.aws.cloudmock.model.Patient;

import java.util.List;

public interface PatientDao {

    public void saveSinglePatient(Patient patient);

    public void saveMultiplePatients(List<Patient> patients);

    public Patient getSinglePatientData(int id);

    public List<Patient> getAllPatientData();

    public String updatePatientRecord(Patient patient);

}
