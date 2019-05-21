package com.service.interfaces;

import com.domain.CurrentEntrust;
import com.domain.RealDeal;

import java.util.List;

public interface DealService {
    List<RealDeal> queryDealRecordBySid(String securities_account_id, int pageNum);

    int countNumberOfDealsBySid(String securities_account_id);

    void allClinchADeal(String entrust_key, float buy_price);

    void someClinchADeal(String entrust_key, float buy_price, int buy_amount);

    void sellAll(String entrust_key, float sale_price);

    void sellSome(String entrust_key, float sale_price, int sale_amount);
}
