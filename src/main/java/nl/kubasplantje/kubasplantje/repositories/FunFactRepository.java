package nl.kubasplantje.kubasplantje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.kubasplantje.kubasplantje.models.FunFactModel;

@Repository
public interface FunFactRepository extends JpaRepository<FunFactModel, Long> {
}
