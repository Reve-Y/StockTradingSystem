package com.dao;

import com.domain.CurrentEntrust;
import com.domain.HistoryEntrust;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryDao {

    int addOneHistoryEntrust(CurrentEntrust ce);

    List<HistoryEntrust> queryHistoryEntrustBySid(@Param("securities_account_id")String securities_account_id);

    int queryNumberOfHistoryEntBySid(@Param("securities_account_id")String securities_account_id);

    int updateStatusToWithdrawByKey(@Param("entrust_key") String entrust_key);
}
