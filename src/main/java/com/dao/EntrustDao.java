package com.dao;

import com.domain.CurrentEntrust;

public interface EntrustDao {
    int addOneCurrentEntrust(CurrentEntrust ce);

    int addOneHistoryEntrust(CurrentEntrust ce);
}
