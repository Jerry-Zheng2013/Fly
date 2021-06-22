package com.ticketsystem.service;

import com.ticketsystem.dao.FlightMapper;
import com.ticketsystem.model.Flight;
import com.ticketsystem.model.FlightExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlightService {

    @Autowired
    private FlightMapper flightMapper;

    public List<Flight> getAllFlight() {
    	RestTemplate restTemplate=new RestTemplate();
    	Map<String,String> params=new HashMap<>();
    	params.put("name","dada");  //
    	ResponseEntity<String> responseEntity=restTemplate.getForEntity("http://USERSERVICE/user?name={name}",String.class,params);
        return flightMapper.selectByExample(new FlightExample());
    }

    public Flight getFlightById(int flightId) {
        return flightMapper.selectByPrimaryKey(flightId);
    }

    public void altFlight(Flight flight) {
        flightMapper.updateByPrimaryKeySelective(flight);
    }

    public void delFlight(int flightId) {
        flightMapper.deleteByPrimaryKey(flightId);
    }

    /**
     * 航班查询
     *
     * @param startCity
     * @param endCity
     * @return
     */
    public List<Flight> queryByCity(String startCity, String endCity) {
        FlightExample flightExample = new FlightExample();
        FlightExample.Criteria criteria = flightExample.createCriteria();
        String s = "";
        if (!startCity.equals(s))
            //if (startCity != "")
            criteria.andStartCityEqualTo(startCity);
        if (!endCity.equals(s))
            //if (endCity != "")
            criteria.andEndCityEqualTo(endCity);
        return flightMapper.selectByExample(flightExample);
    }

    /**
     * 增加航班
     *
     * @param flight
     */
    public void addFlight(Flight flight) {
        flightMapper.insert(flight);
    }
}
