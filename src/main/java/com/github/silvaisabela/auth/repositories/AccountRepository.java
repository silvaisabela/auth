package com.github.silvaisabela.auth.repositories;

import com.github.silvaisabela.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findOneByEmail(String email);
}
