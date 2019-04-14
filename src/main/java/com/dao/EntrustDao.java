package com.dao;

import com.domain.CurrentEntrust;
import org.apache.ibatis.annotations.Param;

public interface EntrustDao {
    int addOneCurrentEntrust(CurrentEntrust ce);

    int addOneHistoryEntrust(CurrentEntrust ce);

    int queryNumberOfEntrustBySid(@Param("securities_account_id") String securities_account_id);
}
