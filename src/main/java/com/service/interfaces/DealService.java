package com.service.interfaces;

import com.domain.RealDeal;

import java.util.List;

public interface DealService {
    List<RealDeal> queryDealRecordBySid(String securities_account_id, int pageNum);

    int countNumberOfDealsBySid(String securities_account_id);
}
