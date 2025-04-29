package ma.ismagi.conference.conferences.services;

import ma.ismagi.conference.conferences.models.Conference;
import ma.ismagi.conference.conferences.repositories.ConferenceRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConferenceServiceImpl implements ConferenceService{
    private final ConferenceRepository conferenceRepository;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Override
    public Mono<Conference> getConferenceById(Long id) {
        return conferenceRepository.findById(id);
    }

    @Override
    public Flux<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    @Override
    public Mono<Conference> create(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public Mono<Conference> update(Conference conference) {
        return conferenceRepository.findById(conference.getId())
                .flatMap(existing -> conferenceRepository.save(conference))
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Conférence non trouvée")));
    }


    @Override
    public Mono<Void> delete(Long id) {
        return conferenceRepository.deleteById(id);
    }
}
