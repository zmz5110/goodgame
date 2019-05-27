package org.lastsurprise.goodgame.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.lastsurprise.goodgame.model.User;

public interface UserMapper {

    @Select("select id,user_name,user_password from user where id=#{id}")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.BIGINT,id = true),
            @Result(column = "user_name",property = "username",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_password",property = "userPassword",jdbcType = JdbcType.VARCHAR)
    })
    public User findUserById(@Param("id") Long id);


}
