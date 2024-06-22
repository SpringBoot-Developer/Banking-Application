package com.account.mapper;

import com.account.dto.BranchDto;
import com.account.entity.Branch;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class BranchMapper {


    public static Branch mapToBranch(BranchDto branchDto) {
        return new Branch(
                branchDto.getId(),
                branchDto.getName(),
                branchDto.getIfsc(),
                branchDto.getAddress(),
                branchDto.getCity(),
                branchDto.getState(),
                branchDto.getCountry(),
                branchDto.getPostalCode(),
                branchDto.getPhone(),
                branchDto.getEmail(),
                branchDto.getManagerName(),
                "true",
                new Date(),
                new Date()

        );
    }

    public static BranchDto mapToBranchDto(Branch branch) {
        return new BranchDto(
                branch.getId(),
                branch.getName(),
                branch.getIfsc(),
                branch.getAddress(),
                branch.getCity(),
                branch.getState(),
                branch.getCountry(),
                branch.getPostalCode(),
                branch.getPhone(),
                branch.getEmail(),
                branch.getManagerName(),
                branch.getIsBranchActive(),
                branch.getBranchCreatedDate(),
                branch.getBranchModifedDate()
        );
    }
}
