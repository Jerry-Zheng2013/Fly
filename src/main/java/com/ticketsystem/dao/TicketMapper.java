package com.ticketsystem.dao;

import com.ticketsystem.model.Ticket;
import com.ticketsystem.model.TicketExample;
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

public interface TicketMapper {
    @SelectProvider(type=TicketSqlProvider.class, method="countByExample")
    long countByExample(TicketExample example);

    @DeleteProvider(type=TicketSqlProvider.class, method="deleteByExample")
    int deleteByExample(TicketExample example);

    @Delete({
        "delete from ticket",
        "where ticket_id = #{ticketId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer ticketId);

    @Insert({
        "insert into ticket (ticket_id, flight_id, ",
        "order_form_id, price, ",
        "discount, passenger_name)",
        "values (#{ticketId,jdbcType=INTEGER}, #{flightId,jdbcType=INTEGER}, ",
        "#{orderFormId,jdbcType=INTEGER}, #{price,jdbcType=DOUBLE}, ",
        "#{discount,jdbcType=DOUBLE}, #{passengerName,jdbcType=VARCHAR})"
    })
    int insert(Ticket record);

    @InsertProvider(type=TicketSqlProvider.class, method="insertSelective")
    int insertSelective(Ticket record);

    @SelectProvider(type=TicketSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ticket_id", property="ticketId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="flight_id", property="flightId", jdbcType=JdbcType.INTEGER),
        @Result(column="order_form_id", property="orderFormId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="discount", property="discount", jdbcType=JdbcType.DOUBLE),
        @Result(column="passenger_name", property="passengerName", jdbcType=JdbcType.VARCHAR)
    })
    List<Ticket> selectByExample(TicketExample example);

    @Select({
        "select",
        "ticket_id, flight_id, order_form_id, price, discount, passenger_name",
        "from ticket",
        "where ticket_id = #{ticketId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ticket_id", property="ticketId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="flight_id", property="flightId", jdbcType=JdbcType.INTEGER),
        @Result(column="order_form_id", property="orderFormId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="discount", property="discount", jdbcType=JdbcType.DOUBLE),
        @Result(column="passenger_name", property="passengerName", jdbcType=JdbcType.VARCHAR)
    })
    Ticket selectByPrimaryKey(Integer ticketId);

    @UpdateProvider(type=TicketSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    @UpdateProvider(type=TicketSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    @UpdateProvider(type=TicketSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Ticket record);

    @Update({
        "update ticket",
        "set flight_id = #{flightId,jdbcType=INTEGER},",
          "order_form_id = #{orderFormId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DOUBLE},",
          "discount = #{discount,jdbcType=DOUBLE},",
          "passenger_name = #{passengerName,jdbcType=VARCHAR}",
        "where ticket_id = #{ticketId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Ticket record);
}