package com.service.impls;

import com.dao.DealDao;
import com.domain.CurrentEntrust;
import com.domain.RealDeal;
import com.github.pagehelper.PageHelper;
import com.service.interfaces.DataService;
import com.service.interfaces.DealService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private static Logger log= Logger.getLogger(DealServiceImpl.class);

    @Autowired
    private DealDao dealDao;

    @Autowired
    private DataService dataService;

    @Override
    public List<RealDeal> queryDealRecordBySid(String securities_account_id, int pageNum) {
        log.info("begin--开始获取第"+pageNum+"页成交记录");

        PageHelper.offsetPage(pageNum,5);
        List<RealDeal> list = dealDao.queryDealRecordsBySid(securities_account_id);

        log.info("end--第"+pageNum+"页成交记录查询结束");
        // 设置stock_name
        for (RealDeal realDeal : list){
            realDeal.setStock_name(dataService.getStockName(realDeal.getStock_code()));
        }
        return list;
    }

    @Override
    public int countNumberOfDealsBySid(String securities_account_id) {
        return dealDao.queryNumberOfDealBySid(securities_account_id);
    }
}
