package io.turq.turq.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contests")
public class ContestEntity {

    public ContestEntity(long id, String title, Date endDate, String rules, String criteria, boolean approved, String description, UserEntity author) {
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.rules = rules;
        this.criteria = criteria;
        this.approved = approved;
        this.description = description;
        this.author = author;
    }

    public ContestEntity(String title, Date endDate, String rules, String criteria, boolean approved, String description, UserEntity author) {
        this.title = title;
        this.endDate = endDate;
        this.rules = rules;
        this.criteria = criteria;
        this.approved = approved;
        this.description = description;
        this.author = author;
    }

    public ContestEntity(long id, String title, Date endDate, String rules, String criteria, boolean approved, String description, UserEntity author, ContestStatusEntity status) {
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.rules = rules;
        this.criteria = criteria;
        this.approved = approved;
        this.description = description;
        this.author = author;
        this.status = status;
    }

    @PostLoad
    private void setPrize() {
        this.prize = payments.stream().mapToLong(a -> a.getAmount()).sum();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date endDate;
    @Transient
    private long prize;
    private String rules;
    private String criteria;
    private boolean approved;
    private String description;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "contests_author_id_fkey"))
    private UserEntity author;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name="contest_id")
    private List<PaymentsEntity> payments;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "contests_status_id_fkey"))
    private ContestStatusEntity status;
}
