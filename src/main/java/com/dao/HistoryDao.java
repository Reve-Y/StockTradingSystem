package com.dao;

import com.domain.CurrentEntrust;
import com.domain.HistoryEntrust;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryDao {

    /**
     * 增加一条历史委托记录
     * @param ce
     * @return
     */
    int addOneHistoryEntrust(CurrentEntrust ce);

    /**
     * 查询某一证券账户下所有的历史委托记录(包括正在执行的)
     * @param securities_account_id
     * @return
     */
    List<HistoryEntrust> queryHistoryEntrustBySid(@Param("securities_account_id")String securities_account_id);

    /**
     * 查询某一证券账户下历史委托记录的条数
     * @param securities_account_id
     * @return
     */
    int queryNumberOfHistoryEntBySid(@Param("securities_account_id")String securities_account_id);

    /**
     * 委托撤单时同步更改委托记录的 entrust_status 字段值，即委托状态
     * @param entrust_key
     * @return
     */
    int updateStatusToWithdrawByKey(@Param("entrust_key") String entrust_key);

    /**
     * 成交时修改历史记录中的委托状态为 “已成交”
     * @param entrust_key
     * @return
     */
    int updateStatusToDealByKey(@Param("entrust_key") String entrust_key);
}
