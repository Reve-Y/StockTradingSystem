package com.service.interfaces;

import com.domain.CurrentEntrust;
import com.domain.HistoryEntrust;

import java.util.List;

public interface EntrustService {
    int normalEntrust(CurrentEntrust ce);

    int countNumberOfEntrustBySid(String securities_account_id);

    List<CurrentEntrust> queryCurrentEntrustBySid(String securities_account_id, int pageNum);

    List<HistoryEntrust> queryHistoryEntrustBySid(String securities_account_id, int pageNum);

    int countNumberOfHistoryEntBySid(String securities_account_id);

    int withdrawEntrustByKey(String entrust_key);

    void debuctServiceCharge(CurrentEntrust ce);

    void sendMessageToMatching(String entrust_key,int entrust_type);
}
