package uz.pdp.creditcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.creditcard.entity.Card;
import uz.pdp.creditcard.entity.Outcome;

import java.util.List;

public interface OutcomeRepository extends JpaRepository<Outcome, Long> {
    List<Outcome> findByFromCard(Card fromCard);
}
