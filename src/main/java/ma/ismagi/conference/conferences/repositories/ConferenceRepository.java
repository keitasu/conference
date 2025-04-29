package ma.ismagi.conference.conferences.repositories;

import ma.ismagi.conference.conferences.models.Conference;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ConferenceRepository extends ReactiveCrudRepository<Conference, Long> {
}
