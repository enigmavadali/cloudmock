package org.aws.cloudmock.dao.impl;

import org.aws.cloudmock.dao.PatientDao;
import org.aws.cloudmock.helper.ElasticPayloadHelper;
import org.aws.cloudmock.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@ConditionalOnExpression("'${source.of.truth}'.equals('elastic')")
public class ElasticPatientDaoImpl implements PatientDao {

    @Autowired
    private ElasticPayloadHelper elasticPayloadHelper;

    @Autowired
    @Qualifier("elasticRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public void saveSinglePatient(Patient patient) {
        String payload = this.elasticPayloadHelper.createJSONObjectFromPatient(patient).toString();
        HttpEntity<String> request = new HttpEntity<String>(payload,addElasticHeaders());
        String url = "http://localhost:9200/patient/_doc/"+ patient.getId()+"F"+patient.getFacility();
        ResponseEntity<String> responseEntity  = this.restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
    }

    @Override
    public void saveMultiplePatients(List<Patient> patients) {
        for(Patient patient : patients){
            System.out.println("saving patient " + patient.getName());
            String payload = this.elasticPayloadHelper.createJSONObjectFromPatient(patient).toString();
            HttpEntity<String> request = new HttpEntity<String>(payload,addElasticHeaders());
            String url = "http://localhost:9200/patient/_doc/"+ patient.getId()+"F"+patient.getFacility();
            ResponseEntity<String> responseEntity  = this.restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
            System.out.println(responseEntity.getStatusCode());
        }
    }

    @Override
    public Patient getSinglePatientData(int id) {
        return null;
    }

    @Override
    public List<Patient> getAllPatientData() {
        return null;
    }

    @Override
    public String updatePatientRecord(Patient patient) {
        String payload = this.elasticPayloadHelper.createJSONObjectFromPatient(patient).toString();
        HttpEntity<String> request = new HttpEntity<String>(payload,addElasticHeaders());
        String url = "http://localhost:9200/patient/_doc/"+ patient.getId()+"F"+patient.getFacility();
        ResponseEntity<String> responseEntity  = this.restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        System.out.println("updated patient " + patient.getName() + " in facility " + patient.getFacility());
        System.out.println(responseEntity.getStatusCode());
        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return "Update Successful";
        }
        else{
            return "Update Failed";
        }
    }

    private HttpHeaders addElasticHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept","application/json");
        return headers;
    }
}
