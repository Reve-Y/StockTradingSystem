package com.dao;

import com.domain.CurrentEntrust;
import com.domain.HistoryEntrust;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntrustDao {
    /**
     * 增加一条"当前委托"记录
     * @param ce
     * @return
     */
    int addOneCurrentEntrust(CurrentEntrust ce);

    /**
     * 查询某一证券账户下正在执行的委托的记录数
     * @param securities_account_id
     * @return
     */
    int queryNumberOfEntrustBySid(@Param("securities_account_id") String securities_account_id);

    /**
     * 查询出某一证券账户下所有的”当前委托记录“
     * @param securities_account_id
     * @return
     */
    List<CurrentEntrust> queryCurrentEntrustBySid(@Param("securities_account_id") String securities_account_id);

    /**
     * 根据每笔委托的唯一标识 entrust_key 执行撤单，从表中删除记录
     * @param entrust_key
     * @return
     */
    int withdrawEntrustByKey(@Param("entrust_key") String entrust_key);

    /**
     * 根据 entrust_key 获取委托明细
     * @param entrust_key
     * @return
     */
    CurrentEntrust getCurrentEntrustDetailByKey(@Param("entrust_key") String entrust_key);

    /**
     * 获取所有的“当前委托”记录
     * @return
     */
    List<CurrentEntrust> queryAllCurrentEntrust();

    /**
     * 更新委托
     * @param ce
     * @return
     */
    int updateEntrust(CurrentEntrust ce);
}
