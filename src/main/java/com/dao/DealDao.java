package com.dao;

import com.domain.RealDeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DealDao {
    List<RealDeal> queryDealRecordsBySid(@Param("securities_account_id")String securities_account_id);

    int queryNumberOfDealBySid(@Param("securities_account_id")String securities_account_id);
}
