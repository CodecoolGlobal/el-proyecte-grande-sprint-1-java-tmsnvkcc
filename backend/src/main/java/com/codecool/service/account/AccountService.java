package com.codecool.service.account;

import com.codecool.dao.AccountDAO;
import com.codecool.dto.GetAccountDTO;
import com.codecool.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public GetAccountDTO getAccount () {
        Account userAccount =  accountDAO.getAccount();

        return new GetAccountDTO(
                userAccount.uuid(),
                userAccount.name(),
                userAccount.description(),
                userAccount.currency(),
                userAccount.actualBalance(),
                userAccount.savingsBalance()
        );
    }
}
