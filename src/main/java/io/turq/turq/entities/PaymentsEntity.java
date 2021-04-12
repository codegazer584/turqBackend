package io.turq.turq.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class PaymentsEntity {

    public PaymentsEntity(long amount, ContestEntity contest, UserEntity author, int status) {
        this.amount = amount;
        this.author = author;
        this.contest = contest;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long amount;
    private int status;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "payments_author_id_fkey"))
    private UserEntity author;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "payments_contest_id_fkey"))
    private ContestEntity contest;
}
