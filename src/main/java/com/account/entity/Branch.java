package com.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Branch name is required")
    @Column(name = "branch_name")
    private String name;

    @NotBlank(message = "IFSC code is required")
    @Column(name = "ifsc_code")
    private String ifsc;

    @NotBlank(message = "Address is required")
    @Column(name = "branch_address")
    private String address;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "State is mandatory")
    private String state;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @NotBlank(message = "PostalCode is mandatory")
    private String postalCode;

    @Size(max = 10)
    @NotBlank(message = "Number is mandatory")
    private String phone;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Manager name is mandatory")
    private String managerName;

    private String isBranchActive;
    private Date branchCreatedDate;
    private Date branchModifedDate;
}
