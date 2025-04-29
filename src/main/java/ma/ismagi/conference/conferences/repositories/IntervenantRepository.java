package ma.ismagi.conference.conferences.repositories;

import ma.ismagi.conference.conferences.models.Intervenant;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IntervenantRepository extends ReactiveCrudRepository<Intervenant, Long> {
}
