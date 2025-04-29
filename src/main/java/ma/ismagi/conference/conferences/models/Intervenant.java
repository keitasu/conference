package ma.ismagi.conference.conferences.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("intervenants")
public class Intervenant {
    @Id
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String specialite;
}
