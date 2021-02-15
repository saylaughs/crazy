package com.saylaughs.crazysaylaugh.Dao;

import com.saylaughs.crazysaylaugh.pojo.Test;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TestMapper {
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into say_test(say_name) values(#{say_name})")
    public int add(Test test);
}
