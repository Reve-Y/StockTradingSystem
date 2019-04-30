package com.dao;


import com.domain.Holdings;
import com.domain.SecuritiesAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecuritiesDao {
    /**
     * 增加一条证券账户信息记录
     * @param sa
     * @return
     */
    int insertOneRecord(SecuritiesAccount sa);

    /**
     * 查询传入的证券账户id是否已被占用
     * @param securities_account_id
     * @return
     */
    int checkIfSidExists(@Param("securities_account_id") String securities_account_id);
}
