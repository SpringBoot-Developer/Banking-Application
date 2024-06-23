package com.account.service.impl;

import com.account.dto.AccountDto;
import com.account.entity.Account;
import com.account.exception.AccountNotFoundException;
import com.account.mapper.AccountMapper;
import com.account.repository.AccountRepository;
import com.account.repository.BranchRepository;
import com.account.response.APIResponse;
import com.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BranchRepository branchRepository;
    private final AccountMapper accountMapper;

    @Override
    public APIResponse createAccount(AccountDto accountDto) {

        Boolean isBranchPresent = branchRepository.isIfscCodePresent(accountDto.getIfscCode().trim());
        if (!isBranchPresent) {
            return APIResponse.builder().message("Branch is not present").build();
        }
        Account account = accountMapper.mapToAccount(accountDto);
        accountRepository.save(account);
        return APIResponse.builder().message("Account has been created successfully..!!").build();
    }

    @Override
    public AccountDto getAccountById(int id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " does not exist"));
        return accountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accountList = accountRepository.findAll();
        return accountList.stream()
                .map(accountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto deposit(int id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " does not exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(int id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " does not exist"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public APIResponse deleteAccount(int id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " does not exist"));
        accountRepository.deleteById(id);
        return APIResponse.builder().message("Account has been deleted successfully..!!").build();

    }
}
