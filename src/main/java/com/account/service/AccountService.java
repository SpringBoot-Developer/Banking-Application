package com.account.service;

import com.account.dto.AccountDto;
import com.account.entity.Account;
import com.account.response.APIResponse;

import java.util.List;

public interface AccountService {

    APIResponse createAccount(AccountDto account);
    AccountDto getAccountById(int id);
    List<AccountDto> getAllAccounts();

}
