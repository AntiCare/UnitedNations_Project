package nl.saxion.project.mobilestation.repositories;

import nl.saxion.project.mobilestation.model.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, String> {

}
