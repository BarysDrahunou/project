package com.senla.finance.project.controller;

import com.senla.finance.project.dto.AccountTopUpDto;
import com.senla.finance.project.dto.BankAccountDto;
import com.senla.finance.project.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.PropertiesValidator.numberValidated;
import static com.senla.finance.project.utils.PropertiesValidator.validated;

@RestController
@RequestMapping("/bank")
public class BankServiceController {

    @Autowired
    private BankService bankService;


    @PostMapping(value = "/account/create")
    public void addAccount(@RequestBody BankAccountDto bankAccountDto) {
        bankService.createAccount(bankAccountDto);
    }

    @PostMapping(value = "/account/topup")
    public void topUp(@RequestBody AccountTopUpDto accountTopUpDto) {
        bankService.topUpAccount(validated(VALID_BANK_ACCOUNT_ID_PROPERTY, accountTopUpDto.getId()),
                numberValidated(VALID_BANK_ACCOUNT_BALANCE_PROPERTY, accountTopUpDto.getSum()));
    }
}
