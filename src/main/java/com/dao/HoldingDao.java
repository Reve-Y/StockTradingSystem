package com.dao;

import com.domain.Holdings;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HoldingDao {

    /**
     * 查询某一证券账户下的持仓信息
     * @param securities_account_id
     * @return
     */
    List<Holdings> queryHoldingsBySid(@Param("securities_account_id") String securities_account_id);

    /**
     * 查询某一证券账户下持仓中不同的公司数量
     * @param securities_account_id
     * @return
     */
    int countNumberOfCompanyBySid(@Param("securities_account_id") String securities_account_id);

    /**
     * 查询某一证券账户下、特定证券的持仓可用数量
     * @param securities_account_id
     * @param stock_code 证券代码
     * @return 可用数量，没有记录返回 0
     */
    long queryOneEnableAmount(@Param("securities_account_id") String securities_account_id,
                              @Param("stock_code") String stock_code);

    /**
     * 更新一条持仓记录。只改变 持有数量、均价、可用数量。
     * 这张表设计有缺陷，没有主键，但是懒得改动了
     * 在这个交易系统中，如果多次买入同一只股票，那么可用数量会在以前的基础上增加，
     *  而不是新增一条记录，同时会按需修改 buy_price 字段的值。这个字段代表的是买入均价。
     * 这里可以根据证券账户id、证券代码来定位一条记录
     * @param holdings
     * @return
     */
    int updateOneHoldingRecord(Holdings holdings);

    /**
     * 查询某一条持仓记录。根据证券账户id、证券代码获取唯一的记录
     * @param securities_account_id
     * @param stock_code
     * @return
     */
    Holdings queryOneHoldingInfo(@Param("securities_account_id") String securities_account_id,
                                 @Param("stock_code") String stock_code);
}
