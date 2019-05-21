package com.dao;

import com.domain.RealDeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DealDao {
    /**
     * 查询某一证券账户下所有的成交记录
     * @param securities_account_id
     * @return
     */
    List<RealDeal> queryDealRecordsBySid(@Param("securities_account_id")String securities_account_id);

    /**
     * 查询某一证券账户下成交记录条数
     * @param securities_account_id
     * @return
     */
    int queryNumberOfDealBySid(@Param("securities_account_id")String securities_account_id);

    /**
     * 插入一条成交记录
     * @param realDeal
     * @return
     */
    int addOneDealRecord(RealDeal realDeal);
}
