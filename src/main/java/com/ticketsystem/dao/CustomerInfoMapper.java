package com.ticketsystem.dao;

import com.ticketsystem.model.CustomerInfo;
import com.ticketsystem.model.CustomerInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CustomerInfoMapper {
    @SelectProvider(type=CustomerInfoSqlProvider.class, method="countByExample")
    long countByExample(CustomerInfoExample example);

    @DeleteProvider(type=CustomerInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(CustomerInfoExample example);

    @Insert({
        "insert into customer_info (customer_id, name, ",
        "card_type, card_no, ",
        "mobile, birthday, ",
        "ticket_type, customer_status, ",
        "account_no)",
        "values (#{customerId,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{cardType,jdbcType=VARCHAR}, #{cardNo,jdbcType=VARCHAR}, ",
        "#{mobile,jdbcType=VARCHAR}, #{birthday,jdbcType=VARCHAR}, ",
        "#{ticketType,jdbcType=VARCHAR}, #{customerStatus,jdbcType=VARCHAR}, ",
        "#{accountNo,jdbcType=VARCHAR})"
    })
    int insert(CustomerInfo record);

    @InsertProvider(type=CustomerInfoSqlProvider.class, method="insertSelective")
    int insertSelective(CustomerInfo record);

    @SelectProvider(type=CustomerInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_type", property="cardType", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_no", property="cardNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.VARCHAR),
        @Result(column="ticket_type", property="ticketType", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_status", property="customerStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_no", property="accountNo", jdbcType=JdbcType.VARCHAR)
    })
    List<CustomerInfo> selectByExample(CustomerInfoExample example);

    @UpdateProvider(type=CustomerInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CustomerInfo record, @Param("example") CustomerInfoExample example);

    @UpdateProvider(type=CustomerInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CustomerInfo record, @Param("example") CustomerInfoExample example);
}