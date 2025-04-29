package ma.ismagi.conference.conferences.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Builder
@Table("conferences")
public class Conference {
    @Id
    private Long id;
    private String titre;
    private String theme;
    @Column("date_conference")
    private LocalDate date;
    private String lieu;

}
