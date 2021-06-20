package uz.pdp.creditcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.creditcard.entity.Card;
import uz.pdp.creditcard.entity.Income;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByToCard(Card toCard);

}
