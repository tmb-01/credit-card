package uz.pdp.creditcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.creditcard.payload.TransferDto;
import uz.pdp.creditcard.service.CardService;

import java.awt.*;

@RestController
@RequestMapping("api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping
    public HttpEntity<?> getCard(@RequestHeader("Authorization") String token) {
        return cardService.getCardData(token);
    }

    @PostMapping("/transfer")
    public HttpEntity<?> transfer(@RequestHeader("Authorization") String token, @RequestBody TransferDto transferDto) {
        return cardService.transfer(token, transferDto);
    }
}
