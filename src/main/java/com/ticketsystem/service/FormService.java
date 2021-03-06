package com.ticketsystem.service;

import com.ticketsystem.dao.FlightMapper;
import com.ticketsystem.dao.OrderFormMapper;
import com.ticketsystem.dao.TicketMapper;
import com.ticketsystem.dao.UserMapper;
import com.ticketsystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FormService {

    @Autowired
    private OrderFormMapper orderFormMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private FlightMapper flightMapper;

    /**
     * 获取订单列表
     *
     * @param username
     * @return
     */
    public List<OrderForm> getAll(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        User user = userMapper.selectByExample(userExample).get(0);
        OrderFormExample orderFormExample = new OrderFormExample();
        OrderFormExample.Criteria criteria = orderFormExample.createCriteria();
        criteria.andUserIdEqualTo(user.getUserId());
        return orderFormMapper.selectByExample(orderFormExample);
    }

    /**
     * 获取订单总金额
     *
     * @param username
     * @param ticketNum
     * @param flightId
     * @return
     */
    public float getAllPrice(String username, int ticketNum, int flightId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        BigDecimal isVip = userMapper.selectByExample(userExample).get(0).getIsVip();
        double totalPrice = ticketNum * flightMapper.selectByPrimaryKey(flightId).getTicketPrice();
        if (isVip.intValue() == 1)
            totalPrice = (float) 0.8 * totalPrice;
        
        return new BigDecimal(String.valueOf(totalPrice)).floatValue();
    }

    /**
     * 获取订单详细信息
     *
     * @param username
     * @param ticketNum
     * @param tickets
     * @return
     */
    public float order(String username, int ticketNum, List<Ticket> tickets) {
        float totalPrice = 0;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(username);
        User user = userMapper.selectByExample(userExample).get(0);
        OrderForm orderForm = new OrderForm();
        Date now = new Date();
        orderForm.setOrderTime(now);
        orderForm.setTicketNumber(new BigDecimal(String.valueOf(ticketNum)));
        if (user.getIsVip().intValue() == 1) {
            totalPrice = (float) (flightMapper.selectByPrimaryKey(tickets.get(0).getFlightId()).getTicketPrice() * ticketNum * 0.8);
            orderForm.setTotalPrice(new BigDecimal(String.valueOf(totalPrice)).doubleValue());

        }
        orderForm.setUserId(user.getUserId());
        orderFormMapper.insert(orderForm);
        OrderFormExample orderFormExample = new OrderFormExample();
        Date now1 = addSecond(now, 5);
        now = addSecond(now, -1);
        OrderFormExample.Criteria orderFormExampleCriteria = orderFormExample.createCriteria();
        orderFormExampleCriteria.andOrderTimeBetween(now, now1);
        orderFormExampleCriteria.andUserIdEqualTo(user.getUserId());
        orderForm.setOrderFormId(orderFormMapper.selectByExample(orderFormExample).get(0).getOrderFormId());

        for (Ticket ticket : tickets) {
            ticket.setPrice(flightMapper.selectByPrimaryKey(ticket.getFlightId()).getTicketPrice());
            if (user.getIsVip().intValue() == 1)
                ticket.setDiscount(0.8);
            else ticket.setDiscount(1.0);
            ticket.setOrderFormId(orderForm.getOrderFormId());
            ticketMapper.insert(ticket);
        }

        Flight flight = flightMapper.selectByPrimaryKey(tickets.get(0).getFlightId());
        flight.setLeftTicket(flight.getLeftTicket().divide(new BigDecimal(String.valueOf(ticketNum))));
        flightMapper.updateByPrimaryKey(flight);

        return totalPrice;
    }

    /**
     * 获取时间
     *
     * @param date
     * @param second
     * @return
     */
    private Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }
}
