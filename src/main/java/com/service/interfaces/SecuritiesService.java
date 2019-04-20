package com.service.interfaces;

import com.domain.Holdings;

import java.util.List;

public interface SecuritiesService {
    float calTotalMarketValue(String securities_account_id);

    List<Holdings> queryHoldingsBySid(String securities_account_id,int pageNum);

    int countNumberOfCompanyBySid(String securities_account_id);
}
