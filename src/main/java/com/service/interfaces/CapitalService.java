package com.service.interfaces;

import com.domain.CapitalAccount;


public interface CapitalService {

    CapitalAccount getCapitalAccountBySecuritiesId(String securities_account_id);

    String getAccountIdBySid(String securities_account_id);
}
