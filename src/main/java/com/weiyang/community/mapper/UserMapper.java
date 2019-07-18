package com.weiyang.community.mapper;
import org.apache.ibatis.annotations.*;

import com.weiyang.community.model.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_Id,token,gmt_Create,gmt_Modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void  insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);


    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Long id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id =#{id}")
    void updata(User user);
}
