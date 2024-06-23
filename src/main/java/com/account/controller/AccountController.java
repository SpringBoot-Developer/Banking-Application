package com.account.controller;

import com.account.dto.AccountDto;
import com.account.response.APIResponse;
import com.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> addAccount(@Valid @RequestBody AccountDto accountDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        APIResponse createdAccount = accountService.createAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().message(createdAccount.getMessage()).build());

    }

    @GetMapping("/{customerId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable int customerId) {
        AccountDto accountDto = accountService.getAccountById(customerId);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    @PutMapping("/{customerId}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable int customerId, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(customerId, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{customerId}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable int customerId, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(customerId, amount);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<APIResponse> deleteAccount(@PathVariable int customerId) {
        APIResponse apiResponse = accountService.deleteAccount(customerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().message(apiResponse.getMessage()).build());
    }
}
