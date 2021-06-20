package uz.pdp.creditcard.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.creditcard.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByUsername(String username);

    Card findByNumber(Long number);
}
