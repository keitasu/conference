package ma.ismagi.conference.conferences.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("participations")
public class Participation {
    @Column("intervenant_id")
    private Long intervenantId;
    @Column("conference_id")
    private Long conferenceId;
    private String sujet;
}
