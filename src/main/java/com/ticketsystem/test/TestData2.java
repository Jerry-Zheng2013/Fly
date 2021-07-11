package com.ticketsystem.test;

public class TestData2 {
	
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
	public static String getBookCookieData(String session, String tokenId, String tokenUUID) {
		String bookCookieData = 
				"X-LB=2.727.65daae9e.50;" + 
				"_ga=GA1.2.1942100065.1625058587;" + 
				"_gid=GA1.2.180656689.1625290602;" + 
				"flycua_user_cookie=true;" + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634;" + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625381282;" + 
				"session="+session+";" + 
				"tokenId="+tokenId+";" + 
				"tokenUUID="+tokenUUID+";" +
				"_gat=1";
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
	public static String getCheckCookie(String tokenId, String tokenUUID) {
		String accountCheckCookie = 
				"_ga=GA1.2.1942100065.1625058587; " + 
				"_gid=GA1.2.2104287381.1625058587; " + 
				"flycua_user_cookie=true; " + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634; " + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625381282; " + 
				"tokenId="+tokenId+"; " + 
				"tokenUUID="+tokenUUID+"; " + 
				" ";
		return accountCheckCookie;
	}
	public static String getLoginCookie(String session) {
		String accountCheckCookie = 
				"_ga=GA1.2.1942100065.1625058587;" + 
				"_gid=GA1.2.2104287381.1625058587;" + 
				"flycua_user_cookie=true;" + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634;" + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625381282;" + 
				"session="+session+"";
		return accountCheckCookie;
	}
	public static String getLoginCookie(String tokenId, String tokenUUID, String session) {
		String accountCheckCookie = 
				"_ga=GA1.2.1942100065.1625058587; " + 
				"_gid=GA1.2.2104287381.1625058587; " + 
				"flycua_user_cookie=true; " + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634; " + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625381282; " + 
				"tokenId="+tokenId+"; " + 
				"tokenUUID="+tokenUUID+"; " +
				"session="+session+"; " + 
				" ";
		return accountCheckCookie;
	}
	

	public static String getCheckCookie(String tokenId, String tokenUUID, String session) {
		String checkCookie = 
				"_ga=GA1.2.1942100065.1625058587; " + 
				"_gid=GA1.2.2104287381.1625058587; " + 
				"flycua_user_cookie=true; " + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634; " + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625381282; " + 
				"tokenId="+tokenId+"; " + 
				"tokenUUID="+tokenUUID+"; " +
				"session="+session+"; " + 
				" ";
		return checkCookie;
	}
	
	public static String getBookPostData3(String uuid, String flightNo) {
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
				"    \"flightDate\": \"2021-07-30\"," + 
				"    \"tripInfo\": [" + 
				"        {" + 
				"            \"Departure\": {" + 
				"                \"IATA\": \"PKX\"," + 
				"                \"Airport\": \"北京大兴国际\"," + 
				"                \"TS_CityCode\": \"PKX\"," + 
				"                \"Terminal\": \"--\"," + 
				"                \"DateTime\": \"2021-07-30T07:15:00\"," + 
				"                \"Date\": \"2021-07-30\"," + 
				"                \"Time\": \"07:15:00\"" + 
				"            }," + 
				"            \"Arrival\": {" + 
				"                \"IATA\": \"SHA\"," + 
				"                \"Airport\": \"上海虹桥\"," + 
				"                \"TS_CityCode\": \"SHA\"," + 
				"                \"Terminal\": \"T2\"," + 
				"                \"DateTime\": \"2021-07-30T09:25:00\"," + 
				"                \"Date\": \"2021-07-30\"," + 
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
				"                        \"Amount\": 568," + 
				"                        \"Currency\": \"￥\"," + 
				"                        \"AmountOld\": 568" + 
				"                    }," + 
				"                    \"TotalFare\": {" + 
				"                        \"Adjusted\": 618," + 
				"                        \"Amount\": 618," + 
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
				"            \"Amount\": 618," + 
				"            \"Currency\": \"￥\"" + 
				"        }" + 
				"    }," + 
				"    \"uuid\": \"" + uuid + "\"" +
				"}";
		return bookPostData.trim().replaceAll(" ", "");
	}
	
	
	public static String getBookPostData2(String uuid) {
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
				"    \"flightDate\": \"2021-07-30\"," + 
				"    \"tripInfo\": [" + 
				"        {" + 
				"            \"Departure\": {" + 
				"                \"IATA\": \"PKX\"," + 
				"                \"Airport\": \"北京大兴国际\"," + 
				"                \"TS_CityCode\": \"PKX\"," + 
				"                \"Terminal\": \"--\"," + 
				"                \"DateTime\": \"2021-07-30T07:15:00\"," + 
				"                \"Date\": \"2021-07-30\"," + 
				"                \"Time\": \"07:15:00\"" + 
				"            }," + 
				"            \"Arrival\": {" + 
				"                \"IATA\": \"SHA\"," + 
				"                \"Airport\": \"上海虹桥\"," + 
				"                \"TS_CityCode\": \"SHA\"," + 
				"                \"Terminal\": \"T2\"," + 
				"                \"DateTime\": \"2021-07-30T09:25:00\"," + 
				"                \"Date\": \"2021-07-30\"," + 
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
				"                        \"Amount\": 568," + 
				"                        \"Currency\": \"￥\"," + 
				"                        \"AmountOld\": 568" + 
				"                    }," + 
				"                    \"TotalFare\": {" + 
				"                        \"Adjusted\": 618," + 
				"                        \"Amount\": 618," + 
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
				"            \"Amount\": 618," + 
				"            \"Currency\": \"￥\"" + 
				"        }" + 
				"    }," + 
				"    \"uuid\": \"" + uuid + "\"" +
				"}";
		return bookPostData.trim().replaceAll(" ", "");
	}
	
	public static String getBookPostData3(String tokenUUID2) {
		String bookPostData = 
				"{\"passengers\":[{\"index\":\"1\",\"type\":\"ADT\",\"personName\":{\"surname\":\"石竟革\",\"Surname\":\"石\",\"givenName\":\"竟革\",\"namePrefix\":\"Mr\"},\"passport\":{\"docType\":\"5\",\"docId\":\"511823198401101378\"},\"AddiDoc\":{\"PTCSubType\":\"\"},\"save4Freq\":false,\"birthday\":\"\",\"phoneNumber\":\"17351122661\",\"insurance\":[{\"data\":\"[[{\\\"FlightSegmentRPH\\\":\\\"1\\\",\\\"OriginDestinationRPH\\\":\\\"1\\\",\\\"InsuranceRPH\\\":\\\"1\\\"}]]\",\"num\":0}]}],\"customer\":{\"personName\":{\"givenName\":\"郑继青\",\"surname\":\"郑继青\",\"name\":\"郑继青\"},\"email\":\"\",\"telephone\":{\"phoneNumber\":\"17656175477\",\"phoneType\":\"\",\"areaCityCode\":\"\",\"fixedPhoneNumber\":\"\"}},\"hasFrequentPassenger\":\"false\",\"productNum\":\"1000\",\"ref\":\"1000\",\"flightDate\":\"2021-07-30\",\"tripInfo\":[{\"Departure\":{\"IATA\":\"PKX\",\"Airport\":\"北京大兴国际\",\"TS_CityCode\":\"PKX\",\"Terminal\":\"--\",\"DateTime\":\"2021-07-30T07:15:00\",\"Date\":\"2021-07-30\",\"Time\":\"07:15:00\"},\"Arrival\":{\"IATA\":\"SHA\",\"Airport\":\"上海虹桥\",\"TS_CityCode\":\"SHA\",\"Terminal\":\"T2\",\"DateTime\":\"2021-07-30T09:25:00\",\"Date\":\"2021-07-30\",\"Time\":\"09:25:00\"},\"MarketingAirline\":\"KN\",\"OperatingAirline\":\"KN\",\"FlightNumber\":\"5737\",\"AirEquipType\":\"33L\",\"Duration\":\"2:10\",\"checkInBaggageRule\":{\"J\":2,\"C\":2,\"W\":1,\"Y\":1,\"M\":1,\"E\":1,\"H\":1,\"K\":1,\"L\":1,\"N\":1,\"R\":0,\"S\":0,\"V\":0,\"D\":0,\"T\":0,\"I\":0,\"Z\":0,\"U\":0},\"handBaggageRule\":{\"J\":1,\"C\":1,\"W\":1,\"Y\":1,\"M\":1,\"E\":1,\"H\":1,\"K\":1,\"L\":1,\"N\":1,\"R\":1,\"S\":1,\"V\":1,\"D\":1,\"T\":1,\"I\":0,\"Z\":0,\"U\":0},\"checkInBaggage\":false,\"StopOver\":false,\"redPackets\":\"\",\"FareFamilyCode\":\"HLQ\",\"FareFamilyName\":\"欢乐抢\",\"CabinClass\":\"D\"}],\"Passengers\":{\"PassengerInfo\":[{\"Type\":\"ADT\",\"Index\":\"1\",\"lastDate\":\"2021-07-30\",\"FareBreakdown\":{\"BaseFare\":{\"Amount\":\"568\",\"Currency\":\"￥\",\"AmountOld\":\"568\"},\"TotalFare\":{\"Adjusted\":\"618\",\"Amount\":\"618\",\"Currency\":\"￥\"},\"Taxes\":{\"CN\":50,\"YQ\":0,\"Currency\":\"￥\"},\"Type\":\"ADT\",\"Quantity\":\"1\",\"PTCSubType\":\"\"},\"age\":\"≥12岁\",\"Typetext\":\"成人\",\"plusType\":\"COMMON\",\"priceType\":\"COMMON\",\"flightDate\":\"2021-07-30\",\"name\":\"石竟革\"}],\"Total\":{\"Amount\":618,\"Currency\":\"￥\"}},\"uuid\":\"6cb30024-fc35-45ea-9a81-11e64f5f147d\"}";
		return bookPostData.trim().replaceAll(" ", "");
	}
	
	public static String getBookCookieData2(String session, String tokenId, String tokenUUID) {
		String accountCheckCookie = 
				"_ga=GA1.2.1942100065.1625058587; " + 
				"_gid=GA1.2.180656689.1625290602; " + 
				"flycua_user_cookie=true; " + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634,1625617382; " + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625754024; " + 
				"tokenId="+tokenId+"; " + 
				"tokenUUID="+tokenUUID+"; " +
				"session="+session+"; " + 
				"X-LB=2.729.6fd82452.50; " + 
				"_gat=1; " + 
				" ";
		return accountCheckCookie;
	}
	public static String getConfirmCookie(String tokenId2, String tokenUUID2, String session) {
		return "_ga=GA1.2.1942100065.1625058587; flycua_user_cookie=true; _gid=GA1.2.180656689.1625290602; Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634,1625617382; session="+session+
				"; X-LB=2.729.6fd82452.50; Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625680272; tokenId=BE1127D539D81720DE551A329BD0EB920F53CEF1B83B90A238E9F93B378F94E13C04EF0E6A3D6689DFEFAE8B619E0F77CF22DD22BAE1863013F4674EFEA524E3; tokenUUID=8bca6d13a3f24268b6732f629c86479c; _gat=1";
	}
	public static String getChooseFlightCookie(String tokenId, String tokenUUID, String session) {
		String accountCheckCookie = 
				"_ga=GA1.2.1942100065.1625058587; " + 
				"_gid=GA1.2.180656689.1625290602; " + 
				"flycua_user_cookie=true; " + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634,1625617382; " + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625754024; " + 
				"tokenId="+tokenId+"; " + 
				"tokenUUID="+tokenUUID+"; " +
				"session="+session+"; " + 
				"X-LB=2.729.6fd82452.50; " + 
				"_gat=1; " + 
				" ";
		return accountCheckCookie;
	}
	
	
	
	
	
}
