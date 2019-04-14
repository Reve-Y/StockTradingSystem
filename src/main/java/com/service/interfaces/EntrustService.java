package com.service.interfaces;

import com.domain.CurrentEntrust;

public interface EntrustService {
    int normalEntrust(CurrentEntrust ce);

    int countNumberOfEntrustBySid(String securities_account_id);
}
