package uz.pdp.creditcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.creditcard.entity.Card;
import uz.pdp.creditcard.entity.Income;
import uz.pdp.creditcard.entity.Outcome;
import uz.pdp.creditcard.repository.CardRepository;
import uz.pdp.creditcard.repository.IncomeRepository;
import uz.pdp.creditcard.security.JwtProvider;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    CardRepository cardRepository;

    public List<Income> getAll(String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        Card card = cardRepository.findByUsername(username);
        if (card != null) {
            return incomeRepository.findByToCard(card);
        }
        return new ArrayList<>();
    }

}
