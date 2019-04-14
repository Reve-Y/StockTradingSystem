package com.dao;

import com.domain.CapitalAccount;
import org.apache.ibatis.annotations.Param;

public interface CapitalDao {
    int insertOneRecord(CapitalAccount ca);

    String queryAccountIdBySecuritiesId(@Param("securities_account_id") String securities_account_id);

    CapitalAccount queryAccountInfoById(@Param("account_id") String account_id);
}
