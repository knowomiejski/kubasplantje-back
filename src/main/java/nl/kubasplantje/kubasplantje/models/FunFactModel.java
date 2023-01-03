package nl.kubasplantje.kubasplantje.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "FunFact")
@Table(name = "fun_facts")
public class FunFactModel {

    @Id
    @SequenceGenerator(name="fun_fact_id_seq_gen",
                       sequenceName="fun_fact_id_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="fun_fact_id_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String funFact;

    public FunFactModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }

}
