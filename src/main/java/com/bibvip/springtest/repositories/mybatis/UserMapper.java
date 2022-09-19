package com.bibvip.springtest.repositories.mybatis;
import java.util.List;

import org.apache.ibatis.annotations.*;

import com.bibvip.springtest.model.User;

@Mapper
public interface UserMapper{
    @Select("SELECT id, name, picture, vacCard, primaryId, secondaryId FROM user_info")
    List<User> findAll();

    @Select("SELECT * FROM user_info WHERE id = #{id}")
    User singleExport(Long id);

    @Select("SELECT * FROM user_info WHERE id = #{id}")
    User findById(Long id);

    @Insert("INSERT INTO `user_info`(`name`, `picture`, `vacCard`, `primaryId`, `secondaryId`, picFilename, " +
            "vacFilename, prmFilename, secFilename) " +
            "VALUES (#{name}, #{picture}, #{vacCard}, #{primaryId}, #{secondaryId}, #{picFilename}," +
            "#{vacFilename}, #{prmFilename}, #{secFilename})")
    void save(User user);

    @Delete("DELETE FROM user_info WHERE id = #{id}")
    void delById(Long id);

    @Update("UPDATE `user_info` SET `name`=#{name},`picture`=#{picture},`vacCard`=#{vacCard}," +
            "`primaryId`=#{primaryId},`secondaryId`=#{secondaryId} WHERE id = #{id}")
    void update(User user);



}
