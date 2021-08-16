package com.ticketsystem.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FlightPackager {
	public String d_iata;
	public String d_airport;
	public String d_ts_citycode;
	public String d_terminal;
	public String d_datetime;
	public String d_date;
	public String d_time;
	public String a_iata;
	public String a_airport;
	public String a_ts_citycode;
	public String a_terminal;
	public String a_datetime;
	public String a_date;
	public String a_time;
	public String airEquipType;
	public String duration;
	public String totalFare;
	public String baseFare;
	public String Amount;

	public String cabinClass;
	public String flightNumber;
	
	public String airline;
	public String farefamily;
	public String priceInfoSeq;
	public String priceInfoSeq2;

	
	
	public void setBookData (JSONObject queryData, String flightNo, String cabin) {
		this.airline = flightNo.substring(0,2);
		this.flightNumber = flightNo.substring(2, 6);
		this.cabinClass = cabin;
		
		JSONArray flightArray = queryData.getJSONArray("Flights");
		for (int i=0;i<flightArray.size();i++) {
			JSONObject flightData = flightArray.getJSONObject(i);
			if (this.flightNumber.equalsIgnoreCase(flightData.getString("FlightNumber"))) {
				JSONArray priceArr = flightData.getJSONArray("price");
				for (int j=0;j<priceArr.size();j++) {
					JSONObject priceData = priceArr.getJSONObject(j);
					JSONObject priceInfoData = priceData.getJSONObject("PriceInfo");
					if (this.cabinClass.equalsIgnoreCase(priceInfoData.getString("FareBasis"))) {
						JSONObject detailData = flightData.getJSONObject("detail");
						JSONObject departureData = detailData.getJSONObject("Departure");
						this.d_iata = departureData.getString("IATA");
						this.d_airport = departureData.getString("Airport");
						this.d_ts_citycode = departureData.getString("TS_CityCode");
						this.d_terminal = departureData.getString("Terminal");
						this.d_datetime = departureData.getString("DateTime");
						this.d_date = departureData.getString("Date");
						this.d_time = departureData.getString("Time");
						JSONObject arrivalData = detailData.getJSONObject("Arrival");
						this.a_iata = arrivalData.getString("IATA");
						this.a_airport = arrivalData.getString("Airport");
						this.a_ts_citycode = arrivalData.getString("TS_CityCode");
						this.a_terminal = arrivalData.getString("Terminal");
						this.a_datetime = arrivalData.getString("DateTime");
						this.a_date = arrivalData.getString("Date");
						this.a_time = arrivalData.getString("Time");
						
						this.airEquipType = detailData.getString("AirEquipType");
						this.duration = detailData.getString("Duration");
						String baseCabinFareAmount = priceInfoData.getString("BaseCabinFareAmount");
						int baseAm = (int) Math.round(Double.valueOf(baseCabinFareAmount));
						int totalAm = baseAm+50;
						this.baseFare = String.valueOf(baseAm);
						this.totalFare = String.valueOf(totalAm);
						this.Amount = String.valueOf(totalAm);
						
						this.priceInfoSeq = priceInfoData.getString("Seq");
						this.priceInfoSeq2 = priceInfoData.getString("fareInfoSeq");
						
						this.farefamily = priceData.getString("FareFamilyCode");
						
					}
				}
			}
		}
	}
	
	public String getBookData(String uuid) {
		String bookPostData = 
				"{" + 
				"    \"passengers\": [" + 
				"        {" + 
				"            \"index\": \"1\"," + 
				"            \"type\": \"ADT\"," + 
				"            \"personName\": {" + 
				"                \"surname\": \"滕飞白\"," + 
				"                \"Surname\": \"滕\"," + 
				"                \"givenName\": \"飞白\"," + 
				"                \"namePrefix\": \"Mr\"" + 
				"            }," + 
				"            \"passport\": {" + 
				"                \"docType\": \"5\"," + 
				"                \"docId\": \"654323196702022216\"" + 
				"            }," + 
				"            \"AddiDoc\": {" + 
				"                \"PTCSubType\": \"\"" + 
				"            }," + 
				"            \"save4Freq\": false," + 
				"            \"birthday\": \"\"," + 
				"            \"phoneNumber\": \"17351122661\"," + 
				"            \"insurance\": [" + 
				"                {" + 
				"                    \"data\": \"[[{\\\"FlightSegmentRPH\\\":\\\"1\\\",\\\"OriginDestinationRPH\\\":\\\"1\\\",\\\"InsuranceRPH\\\":\\\"1\\\"}]]\"," + 
				"                    \"num\": 0" + 
				"                }" + 
				"            ]" + 
				"        }" + 
				"    ]," + 
				"    \"customer\": {" + 
				"        \"personName\": {" + 
				"            \"givenName\": \"郑继青\"," + 
				"            \"surname\": \"郑继青\"," + 
				"            \"name\": \"郑继青\"" + 
				"        }," + 
				"        \"email\": \"\"," + 
				"        \"telephone\": {" + 
				"            \"phoneNumber\": \"17656175477\"," + 
				"            \"phoneType\": \"\"," + 
				"            \"areaCityCode\": \"\"," + 
				"            \"fixedPhoneNumber\": \"\"" + 
				"        }" + 
				"    }," + 
				"    \"hasFrequentPassenger\": \"false\"," + 
				"    \"productNum\": \"1000\"," + 
				"    \"ref\": \"1000\"," + 
				"    \"flightDate\": \"2021-07-30\"," + 
				"    \"tripInfo\": [" + 
				"        {" + 
				"            \"Departure\": {" + 
				"                \"IATA\": \""+this.d_iata+"\"," + 
				"                \"Airport\": \""+this.d_airport+"\"," + 
				"                \"TS_CityCode\": \""+this.d_ts_citycode+"\"," + 
				"                \"Terminal\": \""+this.d_terminal+"\"," + 
				"                \"DateTime\": \""+this.d_datetime.substring(0, 10)+"T"+this.d_datetime.substring(10, this.d_datetime.length())+":00"+"\"," + 
				"                \"Date\": \""+this.d_date+"\"," + 
				"                \"Time\": \""+this.d_time+":00"+"\"" + 
				"            }," + 
				"            \"Arrival\": {" + 
				"                \"IATA\": \""+this.a_iata+"\"," + 
				"                \"Airport\": \""+this.a_airport+"\"," + 
				"                \"TS_CityCode\": \""+this.a_ts_citycode+"\"," + 
				"                \"Terminal\": \""+this.a_terminal+"\"," + 
				"                \"DateTime\": \""+this.a_datetime.substring(0, 10)+"T"+this.a_datetime.substring(10, this.a_datetime.length())+":00"+"\"," + 
				"                \"Date\": \""+this.a_date+"\"," + 
				"                \"Time\": \""+this.a_time+":00"+"\"" + 
				"            }," + 
				"            \"MarketingAirline\": \"KN\"," + 
				"            \"OperatingAirline\": \"KN\"," + 
				"            \"FlightNumber\": \""+this.flightNumber+"\"," + 
				"            \"AirEquipType\": \""+this.airEquipType+"\"," + 
				"            \"Duration\": \""+this.duration+"\"," + 
				"            \"checkInBaggageRule\": {" + 
				"                \"J\": 2," + 
				"                \"C\": 2," + 
				"                \"W\": 1," + 
				"                \"Y\": 1," + 
				"                \"M\": 1," + 
				"                \"E\": 1," + 
				"                \"H\": 1," + 
				"                \"K\": 1," + 
				"                \"L\": 1," + 
				"                \"N\": 1," + 
				"                \"R\": 0," + 
				"                \"S\": 0," + 
				"                \"V\": 0," + 
				"                \"D\": 0," + 
				"                \"T\": 0," + 
				"                \"I\": 0," + 
				"                \"Z\": 0," + 
				"                \"U\": 0" + 
				"            }," + 
				"            \"handBaggageRule\": {" + 
				"                \"J\": 1," + 
				"                \"C\": 1," + 
				"                \"W\": 1," + 
				"                \"Y\": 1," + 
				"                \"M\": 1," + 
				"                \"E\": 1," + 
				"                \"H\": 1," + 
				"                \"K\": 1," + 
				"                \"L\": 1," + 
				"                \"N\": 1," + 
				"                \"R\": 1," + 
				"                \"S\": 1," + 
				"                \"V\": 1," + 
				"                \"D\": 1," + 
				"                \"T\": 1," + 
				"                \"I\": 0," + 
				"                \"Z\": 0," + 
				"                \"U\": 0" + 
				"            }," + 
				"            \"checkInBaggage\": true," + 
				"            \"StopOver\": false," + 
				"            \"redPackets\": \"\"," + 
				"            \"FareFamilyCode\": \"HLQ\"," + 
				"            \"FareFamilyName\": \"欢乐抢\"," + 
				"            \"CabinClass\": \""+this.cabinClass+"\"" + 
				"        }" + 
				"    ]," + 
				"    \"Passengers\": {" + 
				"        \"PassengerInfo\": [" + 
				"            {" + 
				"                \"Type\": \"ADT\"," + 
				"                \"Index\": \"1\"," + 
				"                \"lastDate\": \"2021-07-30\"," + 
				"                \"FareBreakdown\": {" + 
				"                    \"BaseFare\": {" + 
				"                        \"Amount\": \""+this.baseFare+"\"," + 
				"                        \"Currency\": \"￥\"," + 
				"                        \"AmountOld\": \""+this.baseFare+"\"" + 
				"                    }," + 
				"                    \"TotalFare\": {" + 
				"                        \"Adjusted\": \""+this.totalFare+"\"," + 
				"                        \"Amount\": \""+this.totalFare+"\"," + 
				"                        \"Currency\": \"￥\"" + 
				"                    }," + 
				"                    \"Taxes\": {" + 
				"                        \"CN\": 50," + 
				"                        \"YQ\": 0," + 
				"                        \"Currency\": \"￥\"" + 
				"                    }," + 
				"                    \"Type\": \"ADT\"," + 
				"                    \"Quantity\": \"1\"," + 
				"                    \"PTCSubType\": \"\"" + 
				"                }," + 
				"                \"age\": \"≥12岁\"," + 
				"                \"Typetext\": \"成人\"," + 
				"                \"plusType\": \"COMMON\"," + 
				"                \"priceType\": \"COMMON\"," + 
				"                \"flightDate\": \"2021-07-30\"," + 
				"                \"name\": \"滕飞白\"" + 
				"            }" + 
				"        ]," + 
				"        \"Total\": {" + 
				"            \"Amount\": "+this.Amount+"," + 
				"            \"Currency\": \"￥\"" + 
				"        }" + 
				"    }," + 
				"    \"uuid\": \"" + uuid + "\"" +
				"}";
		return bookPostData.trim().replaceAll(" ", "");
	}
}
