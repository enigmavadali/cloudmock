package org.aws.cloudmock.repository;

import org.aws.cloudmock.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoPatientRepository extends MongoRepository<Patient,Integer> {
}
