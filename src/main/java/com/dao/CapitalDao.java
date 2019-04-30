package com.dao;

import com.domain.CapitalAccount;
import org.apache.ibatis.annotations.Param;

public interface CapitalDao {
    /**
     * 向表中插入一条记录
     * @param ca
     * @return
     */
    int insertOneRecord(CapitalAccount ca);

    /**
     * 根据证券账户查询资金账户id
     * @param securities_account_id
     * @return
     */
    String queryAccountIdBySecuritiesId(@Param("securities_account_id") String securities_account_id);

    /**
     * 根据资金账户id获取该资金账户信息
     * @param account_id
     * @return
     */
    CapitalAccount queryAccountInfoById(@Param("account_id") String account_id);

    /**
     * 根据资金账户id查询可用余额
     * @param account_id
     * @return
     */
    float queryEnableBalanceByCid(@Param("account_id") String account_id);

    /**
     * 更新证券账户信息: 只记录资金变动，基础信息不会改动
     * @param ca
     * @return
     */
    int updateAccountInfo(CapitalAccount ca);

    /**
     * 查询传入的资金账户id是否已被占用
     * @param account_id
     * @return
     */
    int checkIfCidExists(@Param("account_id") String account_id);
}
