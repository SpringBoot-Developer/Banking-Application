package com.account.service;

import com.account.response.APIResponse;
import com.account.dto.BranchDto;
import com.account.entity.Branch;

import java.util.List;

public interface BranchService {

    APIResponse createBranch(BranchDto branchDto);
    Branch getBranchByIFSC(String ifscCode);
    List<BranchDto> getAllBranches();
}
