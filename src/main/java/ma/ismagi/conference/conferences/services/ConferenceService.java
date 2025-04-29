package ma.ismagi.conference.conferences.services;

import ma.ismagi.conference.conferences.models.Conference;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConferenceService {
    Mono<Conference> getConferenceById(Long id);
    Flux<Conference> getAllConferences();
    Mono<Conference> create(Conference conference);
    Mono<Conference> update(Conference conference);
    Mono<Void> delete(Long id);
}
