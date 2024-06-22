package com.account.service.impl;

import com.account.dto.BranchDto;
import com.account.entity.Branch;
import com.account.mapper.BranchMapper;
import com.account.repository.BranchRepository;
import com.account.response.APIResponse;
import com.account.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public APIResponse createBranch(BranchDto branchDto) {

        Boolean branchPresent = branchRepository.isBranchNamePresent(branchDto.getName().trim());
        Boolean ifscCodePresent = branchRepository.isIfscCodePresent(branchDto.getIfsc().trim());
        if (branchPresent) {
            return APIResponse.builder().message("Branch Name is already present").build();
        } else if (ifscCodePresent) {
            return APIResponse.builder().message("IFSC Code is already present").build();
        }
        Branch branch = BranchMapper.mapToBranch(branchDto);
        branchRepository.save(branch);
        return APIResponse.builder().message("Branch has been created successfully..!!").build();
    }

    @Override
    public Branch getBranchByIFSC(String ifscCode) {
        return branchRepository.findByIFSCCode(ifscCode.trim());
    }

    @Override
    public List<BranchDto> getAllBranches() {
        List<Branch> branchList = branchRepository.findAll();
        return branchList.stream()
                .map(BranchMapper::mapToBranchDto)
                .collect(Collectors.toList());
    }

}
