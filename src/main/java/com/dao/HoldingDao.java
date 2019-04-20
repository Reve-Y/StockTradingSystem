package com.dao;

import com.domain.Holdings;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HoldingDao {
    List<Holdings> queryHoldingsBySid(@Param("securities_account_id") String securities_account_id);

    int countNumberOfCompanyBySid(@Param("securities_account_id") String securities_account_id);
}
