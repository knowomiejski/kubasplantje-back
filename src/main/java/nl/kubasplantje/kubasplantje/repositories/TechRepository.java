package nl.kubasplantje.kubasplantje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.kubasplantje.kubasplantje.models.TechModel;

@Repository
public interface TechRepository extends JpaRepository<TechModel, Long>{

}
