package org.aws.cloudmock.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aws.cloudmock.dao.FacilityDao;
import org.aws.cloudmock.helper.ElasticPayloadHelper;
import org.aws.cloudmock.model.Patient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnExpression("'${source.of.truth}'.equals('elastic')")
public class ElasticFacilityDaoImpl implements FacilityDao {

    @Autowired
    private ElasticPayloadHelper elasticPayloadHelper;

    @Autowired
    @Qualifier("elasticRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("objMapper")
    private ObjectMapper objectMapper;

    @Override
    public String updateFacilityData() {
        return null;
    }

    @Override
    public String migratePatients() {
        String payload = this.elasticPayloadHelper.allPatientsInFacilityPayload(1).toString();
        HttpEntity<String> request = new HttpEntity<String>(payload,addElasticHeaders());
        String url = "http://localhost:9200/patient/_search?size=50";
        ResponseEntity<String> responseEntity  = this.restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        JSONObject response = new JSONObject(responseEntity.getBody());
        JSONArray arr = response.getJSONObject("hits").getJSONArray("hits");
        for(int i=0; i<arr.length(); i++){
            arr.getJSONObject(i).getJSONObject("_source").remove("@timestamp");
            String json = arr.getJSONObject(i).getJSONObject("_source").toString();
            String elasticDocId = arr.getJSONObject(i).get("_id").toString();
            try{
                Patient p = this.objectMapper.readValue(json,Patient.class);
                p.setFacility(2);
                String req = this.elasticPayloadHelper.createJSONObjectFromPatient(p).toString();
                HttpEntity<String> updateReq = new HttpEntity<String>(req,addElasticHeaders());
                String updateURL = "http://localhost:9200/patient/_doc/"+ elasticDocId;
                ResponseEntity<String> updateResponse  = this.restTemplate.exchange(updateURL, HttpMethod.PUT, updateReq, String.class);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return "All patients migrated";
    }

    private HttpHeaders addElasticHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept","application/json");
        return headers;
    }
}
