package com.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
public class BranchDto {


    private Long id;
    private String name;
    private String ifsc;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phone;
    private String email;
    private String managerName;
    private String isBranchActive;
    private Date branchCreatedDate;
    private Date branchModifedDate;

}
