package ma.ismagi.conference.conferences.handlers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ma.ismagi.conference.conferences.models.Conference;
import ma.ismagi.conference.conferences.services.ConferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
@Component
public class ConferenceHandler {

    private final ConferenceService conferenceService;

    public ConferenceHandler(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Operation(summary = "Ajouter une Conference", description = "Crée une nouvelle conférence avec les informations fournies")
    @ApiResponse(responseCode = "201", description = "Conférence créée avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Conference.class)))
    @ApiResponse(responseCode = "400", description = "Mauvaise requête")
    public Mono<ServerResponse> createConference(ServerRequest request) {
        return request.bodyToMono(Conference.class)
                .flatMap(conference -> conferenceService.create(conference))
                .flatMap(conference -> ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue(conference));
    }

    @Operation(summary = "Liste des Conferences", description = "Récupère la liste de toutes les conférences avec leurs liens associés")
    @ApiResponse(responseCode = "200", description = "Les conférences ont été récupérées avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Conference.class)))
    public Mono<ServerResponse> getAllConferences(ServerRequest request) {
        return ServerResponse.ok()
                .body(conferenceService.getAllConferences(), Conference.class);
    }

    @Operation(summary = "Trouver une conférence par ID", description = "Récupère une conférence par son ID")
    @ApiResponse(responseCode = "200", description = "Conférence récupérée avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Conference.class)))
    @ApiResponse(responseCode = "404", description = "Conférence non trouvée")
    public Mono<ServerResponse> getConferenceById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return conferenceService.getConferenceById(id)
                .flatMap(conference -> ServerResponse.ok().bodyValue(conference))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Operation(summary = "Mettre à jour une conférence", description = "Mettre à jour les informations d'une conférence existante")
    @ApiResponse(responseCode = "200", description = "Conférence mise à jour avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Conference.class)))
    @ApiResponse(responseCode = "404", description = "Conférence non trouvée")
    public Mono<ServerResponse> updateConference(ServerRequest request) {
        return request.bodyToMono(Conference.class)
                .flatMap(conference -> conferenceService.update(conference))
                .flatMap(updatedConference -> ServerResponse.ok().bodyValue(updatedConference));
    }

    @Operation(summary = "Supprimer une conférence", description = "Supprimer une conférence existante")
    @ApiResponse(responseCode = "204", description = "Conférence supprimée avec succès")
    @ApiResponse(responseCode = "404", description = "Conférence non trouvée")
    public Mono<ServerResponse> deleteConference(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return conferenceService.delete(id)
                .then(ServerResponse.noContent().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
