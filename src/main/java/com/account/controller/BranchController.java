package com.account.controller;

import com.account.response.APIResponse;
import com.account.dto.BranchDto;
import com.account.entity.Branch;
import com.account.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> addAccount(@Valid @RequestBody BranchDto branchDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        APIResponse createdBranch = branchService.createBranch(branchDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().message(createdBranch.getMessage()).build());
    }

    @GetMapping("/{ifsc}")
    public ResponseEntity<Branch> getAccountById(@PathVariable String ifsc) {
        Branch branchByIFSC = branchService.getBranchByIFSC(ifsc);
        return ResponseEntity.ok(branchByIFSC);
    }

    @GetMapping()
    public ResponseEntity<List<BranchDto>> getAllAccounts() {
        List<BranchDto> allAccounts = branchService.getAllBranches();
        return ResponseEntity.ok(allAccounts);
    }
}
