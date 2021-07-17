package com.ticketsystem.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BookData {
	public String hasFrequentPassenger = "false";
	public String productNum = "1000";
	public String ref = "1000";
	public String flightDate = "";
	public JSONObject customer = new JSONObject();
	public JSONArray tripInfo = new JSONArray();
	public String passengers = "";
	public JSONObject Passengers2 = new JSONObject();
	public JSONArray PassengersInfo2 = new JSONArray();
	public JSONObject Total = new JSONObject();
	public String uuid = "";
	public int totalSum = 0;
	
	//需要传入的字段
	
	public String getPassengers() {
		return passengers;
	}
	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}
	private int addPassenger = 1;
	public void addPassenger(String name, String docId, String phoneNumber) {
		String name1 = name.substring(0,1);
		String name2 = name.substring(1,name.length());
		List<String> qw = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
		{
		    add("0");
		    add("2");
		    add("4");
		    add("6");
		    add("8");
		}};
		String nameP = "Mr";
		if (qw.contains(docId.substring(docId.length()-2, docId.length()-1))) {
			nameP = "Mrs";
		};
		String ss = "{"
				+ "\"index\":\""+addPassenger+"\","
				+ " \"type\":\"ADT\","
				+ "\"personName\":{"
				+ "\"surname\":\""+name+"\","
				+ "\"Surname\":\""+name1+"\","
				+ "\"givenName\":\""+name2+"\","
				+ "\"namePrefix\":\""+nameP+"\""
				+ "},"
				+ "\"passport\":{"
				+ "\"docType\":\"5\","
				+ "\"docId\":\""+docId+"\""
				+ "},"
				+ "\"AddiDoc\":{"
				+ "\"PTCSubType\":\"\""
				+ "},"
				+ "\"save4Freq\":false,"
				+ "\"birthday\":\"\","
				+ "\"phoneNumber\":\""+phoneNumber+"\","
				+ "\"insurance\":[{"
				+ "\"data\":\"[[{\\\"FlightSegmentRPH\\\":\\\"1\\\",\\\"OriginDestinationRPH\\\":\\\"1\\\",\\\"InsuranceRPH\\\":\\\"1\\\"}]]\","
				+ "\"num\":0"
				+ "}]}";
		if (this.passengers.length()<=0) {
			this.passengers = ss;
		} else {
			this.passengers = this.passengers + "," + ss;
		}
		addPassenger++;
	}
	public JSONObject getCustomer() {
		return customer;
	}
	public void setCustomer(String customerName, String cusPhone) {
		String cus = "{"
				+ "\"personName\":{"
				+ "\"givenName\":\""+customerName+"\","
				+ "\"surname\":\""+customerName+"\","
				+ "\"name\":\""+customerName+"\""
				+ "},\"email\":\"\","
				+ "\"telephone\":{"
				+ "\"phoneNumber\":\""+cusPhone+"\","
				+ "\"phoneType\":\"\","
				+ "\"areaCityCode\":\"\","
				+ "\"fixedPhoneNumber\":\"\""
				+ "}"
				+ "}";
		JSONObject custJson = JSONObject.parseObject(cus);
		this.customer = custJson;
	}
	public String getHasFrequentPassenger() {
		return hasFrequentPassenger;
	}
	public void setHasFrequentPassenger(String hasFrequentPassenger) {
		this.hasFrequentPassenger = hasFrequentPassenger;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public JSONArray getTripInfo() {
		return tripInfo;
	}
	public void setTripInfo(JSONArray tripInfo) {
		this.tripInfo = tripInfo;
	}
	
	public JSONObject processTripParam(JSONObject queryData, String flightNo, String cabin) {
		JSONObject tripParam = new JSONObject();
		tripParam.put("airline", flightNo.substring(0,2));
		tripParam.put("flightNumber", flightNo.substring(2, 6));
		tripParam.put("cabin", cabin);
		
		JSONArray flightArray = queryData.getJSONArray("Flights");
		for (int i=0;i<flightArray.size();i++) {
			JSONObject flightData = flightArray.getJSONObject(i);
			if (flightNo.substring(2, 6).equalsIgnoreCase(flightData.getString("FlightNumber"))) {
				JSONArray priceArr = flightData.getJSONArray("price");
				for (int j=0;j<priceArr.size();j++) {
					JSONObject priceData = priceArr.getJSONObject(j);
					JSONObject priceInfoData = priceData.getJSONObject("PriceInfo");
					if (cabin.equalsIgnoreCase(priceInfoData.getString("FareBasis"))) {
						JSONObject detailData = flightData.getJSONObject("detail");
						JSONObject departureData = detailData.getJSONObject("Departure");
						tripParam.put("d_iata", departureData.getString("IATA"));
						tripParam.put("d_airport", departureData.getString("Airport"));
						tripParam.put("d_ts_citycode", departureData.getString("TS_CityCode"));
						tripParam.put("d_terminal", departureData.getString("Terminal"));
						tripParam.put("d_datetime", departureData.getString("DateTime"));
						tripParam.put("d_date", departureData.getString("Date"));
						tripParam.put("d_time", departureData.getString("Time"));
						JSONObject arrivalData = detailData.getJSONObject("Arrival");
						tripParam.put("a_iata", arrivalData.getString("IATA"));
						tripParam.put("a_airport", arrivalData.getString("Airport"));
						tripParam.put("a_ts_citycode", arrivalData.getString("TS_CityCode"));
						tripParam.put("a_terminal", arrivalData.getString("Terminal"));
						tripParam.put("a_datetime", arrivalData.getString("DateTime"));
						tripParam.put("a_date", arrivalData.getString("Date"));
						tripParam.put("a_time", arrivalData.getString("Time"));
						
						tripParam.put("airEquipType", detailData.getString("AirEquipType"));
						tripParam.put("duration", detailData.getString("Duration"));
						String baseCabinFareAmount = priceInfoData.getString("TotalFare");
						int baseAm = (int) Math.round(Double.valueOf(baseCabinFareAmount));
						int totalAm = baseAm+50;
						tripParam.put("baseFare", String.valueOf(baseAm));
						tripParam.put("totalFare", String.valueOf(totalAm));
						tripParam.put("amount", String.valueOf(totalAm));
						
						tripParam.put("priceInfoSeq", priceInfoData.getString("Seq"));
						tripParam.put("priceInfoSeq2", priceInfoData.getString("fareInfoSeq"));
						tripParam.put("fareFamily", priceData.getString("FareFamilyCode"));
					}
				}
			}
		}
		
		return tripParam;
	}
	
	public void addTripInfo(JSONObject tripParam) {
		String ss = "{"
				+ "\"Departure\":{"
				+ "\"IATA\":\""+tripParam.getString("d_iata")+"\","
				+ "\"Airport\":\""+tripParam.getString("d_airport")+"\","
				+ "\"TS_CityCode\":\""+tripParam.getString("d_ts_citycode")+"\","
				+ "\"Terminal\":\""+tripParam.getString("d_terminal")+"\","
				+ "\"DateTime\":\""+tripParam.getString("d_datetime").substring(0, 10)+"T "+tripParam.getString("d_datetime").substring(10, tripParam.getString("d_datetime").length())+":00"+"\","
				+ "\"Date\":\""+tripParam.getString("d_date")+"\","
				+ "\"Time\":\""+tripParam.getString("d_time")+":00"+"\"},"
				+ "\"Arrival\":{"
				+ "\"IATA\":\""+tripParam.getString("a_iata")+"\","
				+ "\"Airport\":\""+tripParam.getString("a_airport")+"\","
				+ "\"TS_CityCode\":\""+tripParam.getString("a_ts_citycode")+"\","
				+ "\"Terminal\":\""+tripParam.getString("a_terminal")+"\","
				+ "\"DateTime\":\""+tripParam.getString("a_datetime").substring(0, 10)+"T"+tripParam.getString("a_datetime").substring(10, tripParam.getString("a_datetime").length())+":00"+"\","
				+ "\"Date\":\""+tripParam.getString("a_date")+"\","
				+ "\"Time\":\""+tripParam.getString("a_time")+":00"+"\""
				+ "},"
				+ "\"MarketingAirline\":\""+tripParam.getString("airline")+"\","
				+ "\"OperatingAirline\":\""+tripParam.getString("airline")+"\","
				+ "\"FlightNumber\":\""+tripParam.getString("flightNumber")+"\","
				+ "\"AirEquipType\":\""+tripParam.getString("airEquipType")+"\","
				+ "\"Duration\":\""+tripParam.getString("duration")+"\","
				+ "\"checkInBaggageRule\":{"
				+ "\"J\":2,\"C\":2,\"W\":1,\"Y\":1,\"M\":1,\"E\":1,\"H\":1,\"K\":1,\"L\":1,\"N\":1,\"R\":0,\"S\":0,\"V\":0,\"D\":0,\"T\":0,\"I\":0,\"Z\":0,\"U\":0"
				+ "},"
				+ "\"handBaggageRule\":{"
				+ "\"J\":1,\"C\":1,\"W\":1,\"Y\":1,\"M\":1,\"E\":1,\"H\":1,\"K\":1,\"L\":1,\"N\":1,\"R\":1,\"S\":1,\"V\":1,\"D\":1,\"T\":1,\"I\":0,\"Z\":0,\"U\":0"
				+ "},"
				+ "\"checkInBaggage\":false,"
				+ "\"StopOver\":false,"
				+ "\"redPackets\":\"\","
				+ "\"FareFamilyCode\":\"HLQ\","
				+ "\"FareFamilyName\":\"欢乐抢\","
				+ "\"CabinClass\":\""+tripParam.getString("cabin")+"\""
				+ "}";
		JSONObject tripJson = JSONObject.parseObject(ss);
		this.tripInfo.add(tripJson);
	}
	public JSONObject getPassengers2() {
		return Passengers2;
	}
	public void setPassengers2(JSONObject passengers2) {
		this.Passengers2 = passengers2;
	}
	public void initPassengers2() {
		this.Passengers2.put("PassengersInfo", this.PassengersInfo2);
		this.Passengers2.put("Total", this.Total);
	}
	public JSONArray getPassengersInfo2() {
		return PassengersInfo2;
	}
	public void setPassengersInfo2(JSONArray passengersInfo2) {
		PassengersInfo2 = passengersInfo2;
	}
	public void addPassengersInfo2(JSONObject passInfo) {
		String ss = "{"
				+ "\"Type\":\"ADT\","
				+ "\"Index\":\"1\","
				+ "\"lastDate\":\""+passInfo.getString("flightDate")+"\","
				+ "\"FareBreakdown\":{"
				+ "\"BaseFare\":{"
				+ "\"Amount\":\""+passInfo.getString("amountOld")+"\","
				+ "\"Currency\":\"￥\","
				+ "\"AmountOld\":\""+passInfo.getString("amountOld")+"\""
				+ "},"
				+ "\"TotalFare\":{"
				+ "\"Adjusted\":\""+passInfo.getString("amount")+"\","
				+ "\"Amount\":\""+passInfo.getString("amount")+"\","
				+ "\"Currency\":\"￥\""
				+ "},"
				+ "\"Taxes\":{"
				+ "\"CN\":50,"
				+ "\"YQ\":0,"
				+ "\"Currency\":\"￥\""
				+ "},"
				+ "\"Type\":\"ADT\","
				+ "\"Quantity\":\""+passInfo.getString("quantity")+"\","
				+ "\"PTCSubType\":\"\""
				+ "},"
				+ "\"age\":\"≥12岁\","
				+ "\"Typetext\":\"成人\","
				+ "\"plusType\":\"COMMON\","
				+ "\"priceType\":\"COMMON\","
				+ "\"flightDate\":\""+passInfo.getString("flightDate")+"\","
				+ "\"name\":\""+passInfo.getString("name")+"\"}";
		JSONObject passJson = JSONObject.parseObject(ss);
		this.PassengersInfo2.add(passJson);
	}
	public JSONObject getTotal() {
		return Total;
	}
	public void setTotal(JSONObject total) {
		Total = total;
	}
	public void addTotal(String amount) {
		this.totalSum = this.totalSum + Integer.valueOf(amount);
		String ss = "{\"Amount\":"+this.totalSum+",\"Currency\":\"￥\"}";
		JSONObject totalJson = JSONObject.parseObject(ss);
		Total = totalJson;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"passengers\":[");
		sb.append(this.passengers);
		sb.append("],");
		
		sb.append("\"customer\":{");
		JSONObject personName = customer.getJSONObject("personName");
		sb.append("\"personName\":{");
		sb.append("\"givenName\":");  sb.append("\""+personName.getString("givenName")+"\","); 
		sb.append("\"surname\":");  sb.append("\""+personName.getString("surname")+"\","); 
		sb.append("\"name\":");  sb.append("\""+personName.getString("name")+"\""); 
		sb.append("},");
		sb.append("\"email\":");  sb.append("\""+customer.getString("email")+"\","); 
		JSONObject telephone = customer.getJSONObject("telephone");
		sb.append("\"telephone\":{");
		sb.append("\"phoneNumber\":");  sb.append("\""+telephone.getString("phoneNumber")+"\","); 
		sb.append("\"phoneType\":");  sb.append("\""+telephone.getString("phoneType")+"\","); 
		sb.append("\"areaCityCode\":");  sb.append("\""+telephone.getString("areaCityCode")+"\","); 
		sb.append("\"fixedPhoneNumber\":");  sb.append("\""+telephone.getString("fixedPhoneNumber")+"\""); 
		sb.append("}");
		sb.append("},");

		sb.append("\"hasFrequentPassenger\":");  sb.append("\""+hasFrequentPassenger+"\","); 
		sb.append("\"productNum\":");  sb.append("\""+productNum+"\","); 
		sb.append("\"ref\":");  sb.append("\""+ref+"\","); 
		sb.append("\"flightDate\":");  sb.append("\""+flightDate+"\","); 

		sb.append("\"tripInfo\":[");
		for (int i=0;i<tripInfo.size();i++) {
			JSONObject trip = tripInfo.getJSONObject(i);
			if (i==0) {
				sb.append("{");
			} else {
				sb.append(",{");
			}
			JSONObject Departure = trip.getJSONObject("Departure");
			sb.append("\"Departure\":{");
			sb.append("\"IATA\":");  sb.append("\""+Departure.getString("IATA")+"\",");
			sb.append("\"Airport\":");  sb.append("\""+Departure.getString("Airport")+"\",");
			sb.append("\"TS_CityCode\":");  sb.append("\""+Departure.getString("TS_CityCode")+"\",");
			sb.append("\"Terminal\":");  sb.append("\""+Departure.getString("Terminal")+"\",");
			sb.append("\"DateTime\":");  sb.append("\""+Departure.getString("DateTime")+"\",");
			sb.append("\"Date\":"); sb.append("\""+Departure.getString("Date")+"\",");
			sb.append("\"Time\":");  sb.append("\""+Departure.getString("Time")+"\"");
			sb.append("},");
			JSONObject Arrival = trip.getJSONObject("Arrival");
			sb.append("\"Arrival\":{");
			sb.append("\"IATA\":");  sb.append("\""+Arrival.getString("IATA")+"\",");
			sb.append("\"Airport\":");  sb.append("\""+Arrival.getString("Airport")+"\",");
			sb.append("\"TS_CityCode\":");  sb.append("\""+Arrival.getString("TS_CityCode")+"\",");
			sb.append("\"Terminal\":");  sb.append("\""+Arrival.getString("Terminal")+"\",");
			sb.append("\"DateTime\":");  sb.append("\""+Arrival.getString("DateTime")+"\",");
			sb.append("\"Date\":"); sb.append("\""+Arrival.getString("Date")+"\",");
			sb.append("\"Time\":");  sb.append("\""+Arrival.getString("Time")+"\"");
			sb.append("},");
			sb.append("\"MarketingAirline\":");  sb.append("\""+trip.getString("MarketingAirline")+"\",");
			sb.append("\"OperatingAirline\":");  sb.append("\""+trip.getString("OperatingAirline")+"\",");
			sb.append("\"FlightNumber\":");  sb.append("\""+trip.getString("FlightNumber")+"\",");
			sb.append("\"AirEquipType\":");  sb.append("\""+trip.getString("AirEquipType")+"\",");
			sb.append("\"Duration\":");  sb.append("\""+trip.getString("Duration")+"\",");
			JSONObject checkInBaggageRule = trip.getJSONObject("checkInBaggageRule");
			sb.append("\"checkInBaggageRule\":{");
			sb.append("\"J\":");  sb.append(""+checkInBaggageRule.getString("J")+",");
			sb.append("\"C\":");  sb.append(""+checkInBaggageRule.getString("C")+",");
			sb.append("\"W\":");  sb.append(""+checkInBaggageRule.getString("W")+",");
			sb.append("\"Y\":");  sb.append(""+checkInBaggageRule.getString("Y")+",");
			sb.append("\"M\":");  sb.append(""+checkInBaggageRule.getString("M")+",");
			sb.append("\"E\":");  sb.append(""+checkInBaggageRule.getString("E")+",");
			sb.append("\"H\":");  sb.append(""+checkInBaggageRule.getString("H")+",");
			sb.append("\"K\":");  sb.append(""+checkInBaggageRule.getString("K")+",");
			sb.append("\"L\":");  sb.append(""+checkInBaggageRule.getString("L")+",");
			sb.append("\"N\":");  sb.append(""+checkInBaggageRule.getString("N")+",");
			sb.append("\"R\":");  sb.append(""+checkInBaggageRule.getString("R")+",");
			sb.append("\"S\":");  sb.append(""+checkInBaggageRule.getString("S")+",");
			sb.append("\"V\":");  sb.append(""+checkInBaggageRule.getString("V")+",");
			sb.append("\"D\":");  sb.append(""+checkInBaggageRule.getString("D")+",");
			sb.append("\"T\":");  sb.append(""+checkInBaggageRule.getString("T")+",");
			sb.append("\"I\":");  sb.append(""+checkInBaggageRule.getString("I")+",");
			sb.append("\"Z\":");  sb.append(""+checkInBaggageRule.getString("Z")+",");
			sb.append("\"U\":");  sb.append(""+checkInBaggageRule.getString("U")+"");
			sb.append("},");
			JSONObject handBaggageRule = trip.getJSONObject("handBaggageRule");
			sb.append("\"handBaggageRule\":{");
			sb.append("\"J\":");  sb.append(""+handBaggageRule.getString("J")+",");
			sb.append("\"C\":");  sb.append(""+handBaggageRule.getString("C")+",");
			sb.append("\"W\":");  sb.append(""+handBaggageRule.getString("W")+",");
			sb.append("\"Y\":");  sb.append(""+handBaggageRule.getString("Y")+",");
			sb.append("\"M\":");  sb.append(""+handBaggageRule.getString("M")+",");
			sb.append("\"E\":");  sb.append(""+handBaggageRule.getString("E")+",");
			sb.append("\"H\":");  sb.append(""+handBaggageRule.getString("H")+",");
			sb.append("\"K\":");  sb.append(""+handBaggageRule.getString("K")+",");
			sb.append("\"L\":");  sb.append(""+handBaggageRule.getString("L")+",");
			sb.append("\"N\":");  sb.append(""+handBaggageRule.getString("N")+",");
			sb.append("\"R\":");  sb.append(""+handBaggageRule.getString("R")+",");
			sb.append("\"S\":");  sb.append(""+handBaggageRule.getString("S")+",");
			sb.append("\"V\":");  sb.append(""+handBaggageRule.getString("V")+",");
			sb.append("\"D\":");  sb.append(""+handBaggageRule.getString("D")+",");
			sb.append("\"T\":");  sb.append(""+handBaggageRule.getString("T")+",");
			sb.append("\"I\":");  sb.append(""+handBaggageRule.getString("I")+",");
			sb.append("\"Z\":");  sb.append(""+handBaggageRule.getString("Z")+",");
			sb.append("\"U\":");  sb.append(""+handBaggageRule.getString("U")+"");
			sb.append("},");
			sb.append("\"checkInBaggage\":");  sb.append(""+trip.getString("checkInBaggage")+",");
			sb.append("\"StopOver\":");  sb.append(""+trip.getString("StopOver")+",");
			sb.append("\"redPackets\":");  sb.append("\""+trip.getString("redPackets")+"\",");
			sb.append("\"FareFamilyCode\":");  sb.append("\""+trip.getString("FareFamilyCode")+"\",");
			sb.append("\"FareFamilyName\":");  sb.append("\""+trip.getString("FareFamilyName")+"\",");
			sb.append("\"CabinClass\":");  sb.append("\""+trip.getString("CabinClass")+"\"");
			sb.append("}");
		}
		sb.append("],");

		sb.append("\"Passengers\":{");
		JSONArray PassengerInfo = Passengers2.getJSONArray("PassengersInfo");
		sb.append("\"PassengerInfo\":[");
		for (int i=0;i<PassengerInfo.size();i++) {
			if (i==0) {
				sb.append("{");
			} else {
				sb.append(",{");
			}
			int in = i+1;
			JSONObject pass = PassengerInfo.getJSONObject(i);
			sb.append("\"Type\":");  sb.append("\""+pass.getString("Type")+"\",");
			sb.append("\"Index\":");  sb.append("\""+in+"\",");
			sb.append("\"lastDate\":");  sb.append("\""+pass.getString("lastDate")+"\",");
			JSONObject FareBreakdown = pass.getJSONObject("FareBreakdown");
			sb.append("\"FareBreakdown\":{");
			JSONObject BaseFare = FareBreakdown.getJSONObject("BaseFare");
			sb.append("\"BaseFare\":{");
			sb.append("\"Amount\":");  sb.append("\""+BaseFare.getString("Amount")+"\",");
			sb.append("\"Currency\":");  sb.append("\""+BaseFare.getString("Currency")+"\",");
			sb.append("\"AmountOld\":");  sb.append("\""+BaseFare.getString("AmountOld")+"\"");
			sb.append("},");
			JSONObject TotalFare = FareBreakdown.getJSONObject("TotalFare");
			sb.append("\"TotalFare\":{");
			sb.append("\"Adjusted\":");  sb.append("\""+TotalFare.getString("Adjusted")+"\",");
			sb.append("\"Amount\":");  sb.append("\""+TotalFare.getString("Amount")+"\",");
			sb.append("\"Currency\":");  sb.append("\""+TotalFare.getString("Currency")+"\"");
			sb.append("},");
			JSONObject Taxes = FareBreakdown.getJSONObject("Taxes");
			sb.append("\"Taxes\":{");
			sb.append("\"CN\":");  sb.append(""+Taxes.getString("CN")+",");
			sb.append("\"YQ\":");  sb.append(""+Taxes.getString("YQ")+",");
			sb.append("\"Currency\":");  sb.append("\""+Taxes.getString("Currency")+"\"");
			sb.append("},");
			sb.append("\"Type\":");  sb.append("\""+FareBreakdown.getString("Type")+"\",");
			sb.append("\"Quantity\":");  sb.append("\""+FareBreakdown.getString("Quantity")+"\",");
			sb.append("\"PTCSubType\":");  sb.append("\""+FareBreakdown.getString("PTCSubType")+"\"");
			sb.append("},");
			sb.append("\"age\":");  sb.append("\""+pass.getString("age")+"\",");
			sb.append("\"Typetext\":");  sb.append("\""+pass.getString("Typetext")+"\",");
			sb.append("\"plusType\":");  sb.append("\""+pass.getString("plusType")+"\",");
			sb.append("\"priceType\":");  sb.append("\""+pass.getString("priceType")+"\",");
			sb.append("\"flightDate\":");  sb.append("\""+pass.getString("flightDate")+"\",");
			sb.append("\"name\":");  sb.append("\""+pass.getString("name")+"\"");
			sb.append("}");
		}
		sb.append("],");
		
		JSONObject Total = Passengers2.getJSONObject("Total");
		sb.append("\"Total\":{");
		sb.append("\"Amount\":");  sb.append(""+Total.getString("Amount")+",");
		sb.append("\"Currency\":");  sb.append("\""+Total.getString("Currency")+"\"");
		sb.append("}");
		sb.append("},");

		sb.append("\"uuid\":");  sb.append("\""+uuid+"\"");
		sb.append("}"); 
		return sb.toString();
	}
}
