package uz.pdp.creditcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.creditcard.entity.Income;
import uz.pdp.creditcard.service.IncomeService;

import java.util.List;

@RestController
@RequestMapping("api/income")
public class IncomeController {

    @Autowired
    IncomeService incomeService;

    @GetMapping
    public List<Income> getAll(@RequestHeader("Authorization") String token) {
        return incomeService.getAll(token);
    }

}
