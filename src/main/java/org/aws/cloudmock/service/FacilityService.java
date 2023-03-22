package org.aws.cloudmock.service;

import org.aws.cloudmock.dao.impl.ElasticFacilityDaoImpl;
import org.aws.cloudmock.dao.impl.ElasticPatientDaoImpl;
import org.aws.cloudmock.model.Facilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {

    @Autowired
    private ElasticFacilityDaoImpl elasticFacilityDao;

    public String updateFacilityDetails(Facilities fac){
        return null;
    }

    public String migrateFacilityPatients(){
        return this.elasticFacilityDao.migratePatients();
    }

}
