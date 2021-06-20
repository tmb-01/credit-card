package uz.pdp.creditcard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/report")
public class ReportController {

    @GetMapping
    public String getReport() {
        return "report";
    }
}
