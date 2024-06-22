package com.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Size(max = 10)
    @NotBlank(message = "Number is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Date of Birth is required")
    @Past(message = "Date of Birth should be in the past")
    @Temporal(TemporalType.DATE)
    //without 1994-10-08 00:00:00.000000 with 1994-10-08 in DB
    //@Temporal(TemporalType.DATE) specifies that the dateOfBirth should be mapped as a date without a timestamp in the database.
    private Date dateOfBirth;

    @NotBlank(message = "Account type is required")
    private String accountType;

    @PositiveOrZero(message = "Balance should be positive or zero")
    private double balance;

    @Column(name = "account_number")
    private long accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ifsc_code_pk")
    private Branch branch;

    private String isAccountActive;
    private Date accountCreatedDate;
    private Date accountModifedDate;
}
