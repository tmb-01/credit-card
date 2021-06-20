package uz.pdp.creditcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.creditcard.entity.Outcome;
import uz.pdp.creditcard.service.OutcomeService;

import java.util.List;

@RestController
@RequestMapping("api/outcome")
public class OutcomeController {

    @Autowired
    OutcomeService outcomeService;

    @GetMapping
    public List<Outcome> getAll(@RequestHeader("Authorization") String token) {
        return outcomeService.getAll(token);
    }
}
