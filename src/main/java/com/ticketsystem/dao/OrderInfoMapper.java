package com.ticketsystem.dao;

import com.ticketsystem.model.OrderInfo;
import com.ticketsystem.model.OrderInfoExample;
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

public interface OrderInfoMapper {
    @SelectProvider(type=OrderInfoSqlProvider.class, method="countByExample")
    long countByExample(OrderInfoExample example);

    @DeleteProvider(type=OrderInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrderInfoExample example);

    @Delete({
        "delete from order_info",
        "where oi_id = #{oiId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String oiId);

    @Insert({
        "insert into order_info (oi_id, account_no, ",
        "order_no, trip_code, ",
        "flight_no, cabin_code, ",
        "price, standby_count, ",
        "order_status, count_time, ",
        "round, input_time, ",
        "update_time, input_user, ",
        "attribute0, remark)",
        "values (#{oiId,jdbcType=VARCHAR}, #{accountNo,jdbcType=VARCHAR}, ",
        "#{orderNo,jdbcType=VARCHAR}, #{tripCode,jdbcType=VARCHAR}, ",
        "#{flightNo,jdbcType=VARCHAR}, #{cabinCode,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=VARCHAR}, #{standbyCount,jdbcType=VARCHAR}, ",
        "#{orderStatus,jdbcType=VARCHAR}, #{countTime,jdbcType=VARCHAR}, ",
        "#{round,jdbcType=VARCHAR}, #{inputTime,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=VARCHAR}, #{inputUser,jdbcType=VARCHAR}, ",
        "#{attribute0,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(OrderInfo record);

    @InsertProvider(type=OrderInfoSqlProvider.class, method="insertSelective")
    int insertSelective(OrderInfo record);

    @SelectProvider(type=OrderInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="oi_id", property="oiId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="account_no", property="accountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="trip_code", property="tripCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="flight_no", property="flightNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="cabin_code", property="cabinCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.VARCHAR),
        @Result(column="standby_count", property="standbyCount", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="count_time", property="countTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="round", property="round", jdbcType=JdbcType.VARCHAR),
        @Result(column="input_time", property="inputTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="input_user", property="inputUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="attribute0", property="attribute0", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<OrderInfo> selectByExample(OrderInfoExample example);

    @Select({
        "select",
        "oi_id, account_no, order_no, trip_code, flight_no, cabin_code, price, standby_count, ",
        "order_status, count_time, round, input_time, update_time, input_user, attribute0, ",
        "remark",
        "from order_info",
        "where oi_id = #{oiId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="oi_id", property="oiId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="account_no", property="accountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="trip_code", property="tripCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="flight_no", property="flightNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="cabin_code", property="cabinCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.VARCHAR),
        @Result(column="standby_count", property="standbyCount", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="count_time", property="countTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="round", property="round", jdbcType=JdbcType.VARCHAR),
        @Result(column="input_time", property="inputTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="input_user", property="inputUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="attribute0", property="attribute0", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    OrderInfo selectByPrimaryKey(String oiId);

    @UpdateProvider(type=OrderInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    @UpdateProvider(type=OrderInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    @UpdateProvider(type=OrderInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrderInfo record);

    @Update({
        "update order_info",
        "set account_no = #{accountNo,jdbcType=VARCHAR},",
          "order_no = #{orderNo,jdbcType=VARCHAR},",
          "trip_code = #{tripCode,jdbcType=VARCHAR},",
          "flight_no = #{flightNo,jdbcType=VARCHAR},",
          "cabin_code = #{cabinCode,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=VARCHAR},",
          "standby_count = #{standbyCount,jdbcType=VARCHAR},",
          "order_status = #{orderStatus,jdbcType=VARCHAR},",
          "count_time = #{countTime,jdbcType=VARCHAR},",
          "round = #{round,jdbcType=VARCHAR},",
          "input_time = #{inputTime,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=VARCHAR},",
          "input_user = #{inputUser,jdbcType=VARCHAR},",
          "attribute0 = #{attribute0,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where oi_id = #{oiId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(OrderInfo record);
}