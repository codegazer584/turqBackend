package io.turq.turq.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "legislation")
public class LegislationEntity {

    public LegislationEntity(String title, String chapter, String section, String accomplishes, String terms, String purpose, String provisions, String exceptions, String other, UserEntity author, ContestEntity contest) {
        this.title = title;
        this.chapter = chapter;
        this.section = section;
        this.accomplishes = accomplishes;
        this.terms = terms;
        this.purpose = purpose;
        this.provisions = provisions;
        this.exceptions = exceptions;
        this.other = other;
        this.author = author;
        this.contest = contest;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String chapter;
    private String section;
    private String accomplishes;
    private String terms;
    private String purpose;
    private String provisions;
    private String exceptions;
    private String other;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "legislation_author_id_fkey"))
    private UserEntity author;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "legislation_contest_id_fkey"))
    private ContestEntity contest;
}

