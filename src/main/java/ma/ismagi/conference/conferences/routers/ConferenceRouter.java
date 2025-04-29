package ma.ismagi.conference.conferences.routers;

import ma.ismagi.conference.conferences.handlers.ConferenceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ConferenceRouter {

    private final ConferenceHandler conferenceHandler;

    public ConferenceRouter(ConferenceHandler conferenceHandler) {
        this.conferenceHandler = conferenceHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> conferenceRoutes() {
        return RouterFunctions.route()
                .POST("/conferences", conferenceHandler::createConference)
                .GET("/conferences", conferenceHandler::getAllConferences)
                .GET("/conferences/{id}", conferenceHandler::getConferenceById)
                .PUT("/conferences", conferenceHandler::updateConference)
                .DELETE("/conferences/{id}", conferenceHandler::deleteConference)
                .build();
    }
}
