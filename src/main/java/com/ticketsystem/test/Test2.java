package com.ticketsystem.test;

public class Test2 {
	
	public static String getQueryPostData(String postOrigin) {
		String queryPostData = postOrigin;
		return queryPostData;
	}
	public static String getQueryCookieData() {
		String queryCookieData = 
				"_ga=GA1.2.1942100065.1625058587;" + 
				"_gid=GA1.2.2104287381.1625058587;" + 
				"flycua_user_cookie=true;" + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634; " + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625058634;" + 
				"JSESSIONID=CE500E251B100F60A4B007D242031F87; " + 
				"__jsluid_s=1186c7396ca894a7fd1adc321d2ffe3e;" + 
				"_gscs_1659128668=25058634dz16df12|pv:1;" + 
				"_gscu_1659128668=25058634c3cpob12;" + 
				"_gscbrs_1659128668=1; ";
		return queryCookieData;
	}
	
	public static String getBookPostData(String uuid) {
		String bookPostData = 
				"{" + 
				"    \"passengers\": [" + 
				"        {" + 
				"            \"index\": 1," + 
				"            \"type\": \"ADT\"," + 
				"            \"personName\": {" + 
				"                \"surname\": \"滕飞白\"," + 
				"                \"Surname\": \"滕\"," + 
				"                \"givenName\": \"飞白\"," + 
				"                \"namePrefix\": \"Mr\"" + 
				"            }," + 
				"            \"passport\": {" + 
				"                \"docType\": 5," + 
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
				"                    \"data\": [[{\"FlightSegmentRPH\":\"1\",\"OriginDestinationRPH\":\"1\",\"InsuranceRPH\":\"1\"}]]," + 
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
				"    \"hasFrequentPassenger\": false," + 
				"    \"productNum\": 1000," + 
				"    \"ref\": 1000," + 
				"    \"flightDate\": \"2021-07-02\"," + 
				"    \"tripInfo\": [" + 
				"        {" + 
				"            \"Departure\": {" + 
				"                \"IATA\": \"PKX\"," + 
				"                \"Airport\": \"北京大兴国际\"," + 
				"                \"TS_CityCode\": \"PKX\"," + 
				"                \"Terminal\": \"--\"," + 
				"                \"DateTime\": \"2021-07-02T07:15:00\"," + 
				"                \"Date\": \"2021-07-02\"," + 
				"                \"Time\": \"07:15:00\"" + 
				"            }," + 
				"            \"Arrival\": {" + 
				"                \"IATA\": \"SHA\"," + 
				"                \"Airport\": \"上海虹桥\"," + 
				"                \"TS_CityCode\": \"SHA\"," + 
				"                \"Terminal\": \"T2\"," + 
				"                \"DateTime\": \"2021-07-02T09:25:00\"," + 
				"                \"Date\": \"2021-07-02\"," + 
				"                \"Time\": \"09:25:00\"" + 
				"            }," + 
				"            \"MarketingAirline\": \"KN\"," + 
				"            \"OperatingAirline\": \"KN\"," + 
				"            \"FlightNumber\": \"5737\"," + 
				"            \"AirEquipType\": \"33L\"," + 
				"            \"Duration\": \"2:10\"," + 
				"            \"checkInBaggageRule\": {" + 
				"                \"J\": \"2\"," + 
				"                \"C\": \"2\"," + 
				"                \"W\": \"1\"," + 
				"                \"Y\": \"1\"," + 
				"                \"M\": \"1\"," + 
				"                \"E\": \"1\"," + 
				"                \"H\": \"1\"," + 
				"                \"K\": \"1\"," + 
				"                \"L\": \"1\"," + 
				"                \"N\": \"1\"," + 
				"                \"R\": \"0\"," + 
				"                \"S\": \"0\"," + 
				"                \"V\": \"0\"," + 
				"                \"D\": \"0\"," + 
				"                \"T\": \"0\"," + 
				"                \"I\": \"0\"," + 
				"                \"Z\": \"0\"," + 
				"                \"U\": \"0\"" + 
				"            }," + 
				"            \"handBaggageRule\": {" + 
				"                \"J\": \"1\"," + 
				"                \"C\": \"1\"," + 
				"                \"W\": \"1\"," + 
				"                \"Y\": \"1\"," + 
				"                \"M\": \"1\"," + 
				"                \"E\": \"1\"," + 
				"                \"H\": \"1\"," + 
				"                \"K\": \"1\"," + 
				"                \"L\": \"1\"," + 
				"                \"N\": \"1\"," + 
				"                \"R\": \"1\"," + 
				"                \"S\": \"1\"," + 
				"                \"V\": \"1\"," + 
				"                \"D\": \"1\"," + 
				"                \"T\": \"1\"," + 
				"                \"I\": \"0\"," + 
				"                \"Z\": \"0\"," + 
				"                \"U\": \"0\"" + 
				"            }," + 
				"            \"checkInBaggage\": false," + 
				"            \"StopOver\": false," + 
				"            \"redPackets\": \"\"," + 
				"            \"FareFamilyCode\": \"HLQ\"," + 
				"            \"FareFamilyName\": \"欢乐抢\"," + 
				"            \"CabinClass\": \"T\"" + 
				"        }" + 
				"    ]," + 
				"    \"Passengers\": {" + 
				"        \"PassengerInfo\": [" + 
				"            {" + 
				"                \"Type\": \"ADT\"," + 
				"                \"Index\": 1," + 
				"                \"lastDate\": \"2021-07-02\"," + 
				"                \"FareBreakdown\": {" + 
				"                    \"BaseFare\": {" + 
				"                        \"Amount\": 488," + 
				"                        \"Currency\": \"￥\"," + 
				"                        \"AmountOld\": 488" + 
				"                    }," + 
				"                    \"TotalFare\": {" + 
				"                        \"Adjusted\": 538," + 
				"                        \"Amount\": 538," + 
				"                        \"Currency\": \"￥\"" + 
				"                    }," + 
				"                    \"Taxes\": {" + 
				"                        \"CN\": 50," + 
				"                        \"YQ\": 0," + 
				"                        \"Currency\": \"￥\"" + 
				"                    }," + 
				"                    \"Type\": \"ADT\"," + 
				"                    \"Quantity\": 1," + 
				"                    \"PTCSubType\": \"\"" + 
				"                }," + 
				"                \"age\": \"≥12岁\"," + 
				"                \"Typetext\": \"成人\"," + 
				"                \"plusType\": \"COMMON\"," + 
				"                \"priceType\": \"COMMON\"," + 
				"                \"flightDate\": \"2021-07-02\"," + 
				"                \"name\": \"滕飞白\"" + 
				"            }" + 
				"        ]," + 
				"        \"Total\": {" + 
				"            \"Amount\": 538," + 
				"            \"Currency\": \"￥\"" + 
				"        }" + 
				"    }," + 
				"    \"uuid\": \"" + uuid + "\"" +
				"}";
		System.out.println("ssss");
		return bookPostData.trim().replaceAll(" ", "");
	}
	public static String getBookCookieData(String hm_lpvt, String session, String tokenId, String tokenUUID) {
		String bookCookieData = 
				"X-LB=2.727.65daae9e.50;" + 
				"_ga=GA1.2.1942100065.1625058587;" + 
				"_gid=GA1.2.2104287381.1625058587;" + 
				"flycua_user_cookie=true;" + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634;" + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7="+hm_lpvt+"; " + 
				"session="+"s~d1fc5d69-90ad-4ddc-9389-8a6ce4919a1e.2ae7c905d258a7f838277696fa2ae1c7"+";" + 
				"tokenId="+tokenId+";" + 
				"tokenUUID="+tokenUUID+"";
		return bookCookieData;
	}
	
	public static String getCancelPostData() {
		String cancelPostData = 
				"{" + 
				"    pNm:1002," + 
				"    mProductNm:0" + 
				"}";
		return cancelPostData;
	}
	public static String getCancelCookieData(String hm_lpvt, String session, String tokenId, String tokenUUID) {
		String cancelCookieData = 
				"X-LB=2.727.65daae9e.50;" + 
				"_ga=GA1.2.1942100065.1625058587;" + 
				"_gid=GA1.2.2104287381.1625058587;" + 
				"flycua_user_cookie=true;" + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634;" + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7="+hm_lpvt+";" + 
				"session="+session+";" + 
				"tokenId="+tokenId+";" + 
				"tokenUUID="+tokenUUID+"; " + 
				"_gat=";
		return cancelCookieData;
	}
	
	
}
