package com.ticketsystem.dao;

import com.ticketsystem.model.AccountInfo;
import com.ticketsystem.model.AccountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AccountInfoMapper {
    @SelectProvider(type=AccountInfoSqlProvider.class, method="countByExample")
    long countByExample(AccountInfoExample example);

    @DeleteProvider(type=AccountInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(AccountInfoExample example);

    @Delete({
        "delete from account_info",
        "where account_no = #{accountNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String accountNo);

    @Insert({
        "insert into account_info (account_no, name, ",
        "password, contact_mobile, ",
        "use_time, encryptStr, ",
        "session)",
        "values (#{accountNo,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{contactMobile,jdbcType=VARCHAR}, ",
        "#{useTime,jdbcType=VARCHAR}, #{encryptstr,jdbcType=VARCHAR}, ",
        "#{session,jdbcType=VARCHAR})"
    })
    int insert(AccountInfo record);

    @InsertProvider(type=AccountInfoSqlProvider.class, method="insertSelective")
    int insertSelective(AccountInfo record);

    @SelectProvider(type=AccountInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="account_no", property="accountNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_mobile", property="contactMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="use_time", property="useTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="encryptStr", property="encryptstr", jdbcType=JdbcType.VARCHAR),
        @Result(column="session", property="session", jdbcType=JdbcType.VARCHAR)
    })
    List<AccountInfo> selectByExample(AccountInfoExample example);

    @Select({
        "select",
        "account_no, name, password, contact_mobile, use_time, encryptStr, session",
        "from account_info",
        "where account_no = #{accountNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="account_no", property="accountNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_mobile", property="contactMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="use_time", property="useTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="encryptStr", property="encryptstr", jdbcType=JdbcType.VARCHAR),
        @Result(column="session", property="session", jdbcType=JdbcType.VARCHAR)
    })
    AccountInfo selectByPrimaryKey(String accountNo);

    @UpdateProvider(type=AccountInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);

    @UpdateProvider(type=AccountInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);

    @UpdateProvider(type=AccountInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AccountInfo record);

    @Update({
        "update account_info",
        "set name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "contact_mobile = #{contactMobile,jdbcType=VARCHAR},",
          "use_time = #{useTime,jdbcType=VARCHAR},",
          "encryptStr = #{encryptstr,jdbcType=VARCHAR},",
          "session = #{session,jdbcType=VARCHAR}",
        "where account_no = #{accountNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AccountInfo record);
}