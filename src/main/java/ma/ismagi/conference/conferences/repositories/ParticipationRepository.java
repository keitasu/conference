package ma.ismagi.conference.conferences.repositories;

import ma.ismagi.conference.conferences.models.Participation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ParticipationRepository extends ReactiveCrudRepository<Participation, Void> {
}
