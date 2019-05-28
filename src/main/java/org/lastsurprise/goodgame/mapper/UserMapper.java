package org.lastsurprise.goodgame.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.lastsurprise.goodgame.model.User;

public interface UserMapper {

    @Select("select id,user_name,user_password,user_salt from user where id=#{id}")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.BIGINT,id = true),
            @Result(column = "user_name",property = "username",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_password",property = "userPassword",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_salt",property = "salt",jdbcType = JdbcType.VARCHAR)
    })
    public User findUserById(@Param("id") Long id);

    @Select("select id,user_name,user_password,user_salt from user where user_name=#{userName}")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.BIGINT,id = true),
            @Result(column = "user_name",property = "username",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_password",property = "userPassword",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_salt",property = "salt",jdbcType = JdbcType.VARCHAR)
    })
    public User findUserByUserName(@Param("userName") String userName);

    @Insert("insert into user (user_name,user_password,user_salt) values (#{username},#{userPassword},#{salt})")
    public void insertUser(User user);
}
