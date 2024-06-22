package com.account.mapper;

import com.account.dto.AccountDto;
import com.account.entity.Account;
import com.account.entity.Branch;
import com.account.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountMapper {

    private final BranchRepository branchRepository;
    private static final String[] POSSIBLE_DATE_FORMATS = {"dd/MM/yyyy", "dd-MM-yyyy", "yyyy/MM/dd", "yyyy-MM-dd"};

    public static long generateAccountNumbers() {
        // Generate a random number between 8,774,000,000,000 and 8,774,999,999,999 (inclusive)
        long baseNumber = 8774_000_000_000L;
        return ThreadLocalRandom.current().nextLong(baseNumber, baseNumber + 1_000_000_000L);
    }

    public Date dateValidate(String date) {
        for (String format : POSSIBLE_DATE_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                LocalDate localDate = LocalDate.parse(date, formatter);
                Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                return Date.from(instant);
            } catch (DateTimeParseException e) {
                log.error("Date parsing failed for format: {} with error: {}", format, e.getMessage());
            }
        }
        return null;
    }

    public Account mapToAccount(AccountDto accountDto) {
        Date dateOfBirth = dateValidate(accountDto.getDateOfBirth());
        if (dateOfBirth == null) {
            log.error("Failed to parse dateOfBirth from accountDto: {}", accountDto.getDateOfBirth());
            throw new UnsupportedOperationException("Invalid date format for dateOfBirth: " + accountDto.getDateOfBirth());
        }
        String ifscCode = accountDto.getIfscCode();
        Branch branch = branchRepository.findByIFSCCode(ifscCode);
        long accountNumber = generateAccountNumbers();
        return new Account(
                accountDto.getId(),
                accountDto.getName(),
                accountDto.getEmail(),
                accountDto.getPhoneNumber(),
                accountDto.getAddress(),
                dateOfBirth,
                accountDto.getAccountType(),
                accountDto.getBalance(),
                accountNumber,
                branch,
                "true",
                new Date(),
                new Date()

        );
    }

    public AccountDto mapToAccountDto(Account account) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dob = dateFormat.format(account.getDateOfBirth());

        return new AccountDto(
                account.getId(),
                account.getName(),
                account.getEmail(),
                account.getPhoneNumber(),
                account.getAddress(),
                dob,
                account.getAccountType(),
                account.getBalance(),
                account.getAccountNumber(),
                account.getBranch().getIfsc(),
                account.getBranch().getName()

        );
    }
}
