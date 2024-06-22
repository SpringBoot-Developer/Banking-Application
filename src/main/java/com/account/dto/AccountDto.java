package com.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AccountDto {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
    private String accountType;
    private double balance;
    private long accountNumber;
    private String ifscCode;
    private String branchName;
}
