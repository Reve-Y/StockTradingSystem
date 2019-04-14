package com.dao;


import com.domain.Holdings;
import com.domain.SecuritiesAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecuritiesDao {
    int insertOneRecord(SecuritiesAccount sa);

}
