package com.scrm.message.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by zhb on 2017/9/29/029.
 */
public interface BaseDaoable<T> {

    long insert(@Param("e") T var1);

    long insertBatch(@Param("list") List<T> var1);

    long update(@Param("e") T var1);

    long updateMap(@Param("e") Object var1, @Param("w") Object var2);

    long deleteByKey(@Param("key") Object var1);

    long delete(@Param("w") Object var1);

    long deleteBatch(@Param("w") Object var1);

    T fetch(@Param("key") Object var1);

    long count(@Param("w") Object var1);

    List<T> list(@Param("w") Object var1, @Param("sort") Sort var2);

    List<T> page(@Param("page") PageRequest var1, @Param("w") Object var2);

    T query(@Param("w") Object var1);
}
