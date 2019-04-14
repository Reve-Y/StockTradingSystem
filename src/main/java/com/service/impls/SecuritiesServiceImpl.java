package com.service.impls;

import com.dao.HoldingDao;
import com.dao.SecuritiesDao;
import com.domain.Holdings;
import com.service.interfaces.DataService;
import com.service.interfaces.SecuritiesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecuritiesServiceImpl implements SecuritiesService {
    private static Logger log= Logger.getLogger(SecuritiesServiceImpl.class);

    @Autowired
    private SecuritiesDao securitiesDao;

    @Autowired
    private DataService dataService;

    @Autowired
    private HoldingDao holdingDao;

    /**
     * 计算持有证券的总市值
     * @param securities_account_id 证券账户id
     * @return 总市值
     */
    @Override
    public float calTotalMarketValue(String securities_account_id) {
        float sum = 0.0f;
        log.info("开始计算当前证券账户"+securities_account_id+"持有证券的总市值");
        List<Holdings> list = holdingDao.queryHoldingsBySid(securities_account_id);
        for (Holdings holdings : list){
            sum += dataService.queryCurrentStockPrice(holdings.getStock_code()) * holdings.getHold_amount();
        }
        return sum;
    }

    /**
     * 根据传入的证券账户查询当前持仓情况，返回一个list集合
     * @param securities_account_id 证券账户id
     * @return List<Holdings>，查询到的集合
     */
    @Override
    public List<Holdings> queryHoldingsBySid(String securities_account_id) {
        log.info("根据证券账户 "+securities_account_id+" 查询持仓情况");
        List<Holdings> list = holdingDao.queryHoldingsBySid(securities_account_id);
        log.info("查询持仓信息完毕");
        return list;
    }
}
