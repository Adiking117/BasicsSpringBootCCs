package com.adi.isolationlevels.DAO;

import com.adi.isolationlevels.Entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDAO extends JpaRepository<BankAccount, Long> {
}
