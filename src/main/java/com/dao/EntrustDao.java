package com.dao;

import com.domain.CurrentEntrust;
import com.domain.HistoryEntrust;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntrustDao {
    int addOneCurrentEntrust(CurrentEntrust ce);

    int queryNumberOfEntrustBySid(@Param("securities_account_id") String securities_account_id);

    List<CurrentEntrust> queryCurrentEntrustBySid(@Param("securities_account_id") String securities_account_id);

    int withdrawEntrustByKey(@Param("entrust_key") String entrust_key);

}
