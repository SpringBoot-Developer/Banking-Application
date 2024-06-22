package com.account.repository;

import com.account.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END FROM Branch b WHERE b.ifsc = :ifscCode")
    public Boolean isIfscCodePresent(@Param("ifscCode") String ifscCode);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END FROM Branch b WHERE b.name = :name")
    public Boolean isBranchNamePresent(@Param("name") String name);

    @Query("SELECT b From Branch b WHERE b.ifsc = :ifscCode")
    public Branch findByIFSCCode(@Param("ifscCode") String ifscCode);

}
