package org.aws.cloudmock.helper;

import org.aws.cloudmock.model.Patient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class ElasticPayloadHelper {

    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public JSONObject createJSONObjectFromPatient(Patient patient){
        JSONObject json = new JSONObject(patient);
        String date = dateFormat.format(new Date());
        json.put("@timestamp",date);
        return json;
    }

    public JSONObject allPatientsInFacilityPayload(int facility){
        JSONObject query = new JSONObject()
                .put("query", new JSONObject()
                        .put("bool", new JSONObject()
                                .put("must", new JSONArray()
                                        .put(new JSONObject()
                                                .put("term", new JSONObject().put("facility",facility))))));
        System.out.println("Elastic query : " + query.toString());
        return query;
    }

//    public JSONObject createSearchPatientInFacilityPayload(Patient patient){
//        JSONObject query = new JSONObject()
//                .put("query", new JSONObject()
//                        .put("bool", new JSONObject()
//                                .put("must", new JSONArray()
//                                        .put(new JSONObject()
//                                                .put("term", new JSONObject().put("id",patient.getId())))
//                                        .put(new JSONObject()
//                                                .put("term", new JSONObject().put("facility",patient.getFacility()))))));
//        System.out.println(query.toString());
//        return query;
//    }

}
