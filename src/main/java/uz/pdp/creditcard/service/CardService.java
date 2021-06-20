package uz.pdp.creditcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import uz.pdp.creditcard.entity.Card;
import uz.pdp.creditcard.entity.Income;
import uz.pdp.creditcard.entity.Outcome;
import uz.pdp.creditcard.payload.TransferDto;
import uz.pdp.creditcard.repository.CardRepository;
import uz.pdp.creditcard.repository.IncomeRepository;
import uz.pdp.creditcard.repository.OutcomeRepository;
import uz.pdp.creditcard.security.JwtProvider;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    JwtProvider jwtProvider;

    public HttpEntity<?> getCardData(String token) {
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        Card card = cardRepository.findByUsername(username);
        if (card != null) {
            return ResponseEntity.ok(card);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("card is not exist with this username");
    }

    public HttpEntity<?> transfer(String token, TransferDto transferDto) {
        String username = jwtProvider.getUsernameFromToken(token.substring(7));
        Card fromCard = cardRepository.findByUsername(username);
        Card toCard = cardRepository.findByNumber(transferDto.getToCardNumber());
        Float paymentAmount = transferDto.getAmount();


        if (fromCard != null) {
            if (toCard != null) {
                Float fromBalance = fromCard.getBalance();
                Float toBalance = toCard.getBalance();
                if (fromBalance > commission(paymentAmount)) {
                    fromCard.setBalance(fromBalance - commission(paymentAmount));
                    cardRepository.save(fromCard);
                    toCard.setBalance(toBalance + paymentAmount);
                    cardRepository.save(toCard);

                    Income income = new Income();
                    income.setAmount(transferDto.getAmount());
                    income.setDate(LocalDate.now());
                    income.setFromCard(fromCard);
                    income.setToCard(toCard);
                    incomeRepository.save(income);

                    Outcome outcome = new Outcome();
                    outcome.setDate(LocalDate.now());
                    outcome.setAmount(paymentAmount);
                    outcome.setCommission_amount(commissionAmount(paymentAmount));
                    outcome.setFromCard(fromCard);
                    outcome.setToCard(toCard);
                    outcomeRepository.save(outcome);

                    return ResponseEntity.ok("Success");
                }
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("you don't have enough money");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("card is not exist with this card number");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("card is not exist with this username");
    }


    //    calculate percent
    private Float commission(Float amount) {
        float commissionPercent = 1.5F;
        return amount + (amount / 100) * commissionPercent;
    }

    private Float commissionAmount(Float amount) {
        float commissionPercent = 1.5F;
        return (amount / 100) * commissionPercent;
    }

}
