package com.ticketsystem.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.alibaba.fastjson.JSONObject;

public class PostSender {
	
	
	
	public JSONObject sendHttpPost(String url, String postDataStr) {
		return sendHttpPost(url, postDataStr, null);
	}
	
	public JSONObject sendHttpPost(String url, String postDataStr, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +=" " + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	public JSONObject queryPost(String url, String postDataStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +=" " + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	public JSONObject pointsPost(String url, String postDataStr, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +=" " + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	
	public JSONObject choosePost(String url, String postDataStr) {
		return choosePost(url, postDataStr, null);
	}
	
	private int chooseCount = 0;
	
	public JSONObject choosePost(String url, String postDataStr, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            //conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            //conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            //conn.setRequestProperty("Connection", "keep-alive");
            //conn.setRequestProperty("Content-Length", "151");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //conn.setRequestProperty("Host", "www.flycua.com");
            //conn.setRequestProperty("Origin", "http://www.flycua.com");
            //conn.setRequestProperty("Referer", "http://www.flycua.com/booking/search.html?flightType=oneway&radio-1-set=on&Origin=CITY_BJS_CN&Destination=CitCnSHANGHA364&departDate=2021-07-28&adults=4&children=0&militaryDisability=0&policeRemnants=0");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
            //conn.setRequestProperty("X-Referrer", "http://www.flycua.com/");
            //conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +=" " + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);
			if (!result.contains("uuid") && this.chooseCount <3) {
				this.chooseCount++;
	        	try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
				}
				choosePost(url, postDataStr, cookieStr);
			}

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
            if (!result.contains("uuid") && this.chooseCount <3) {
				this.chooseCount++;
            	try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
				}
				choosePost(url, postDataStr, cookieStr);
			}
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}

	public JSONObject sendHttpsPost(String url, String postDataStr) throws Exception {
		return sendHttpsPost(url, postDataStr, null);
	}
	
	public JSONObject sendHttpsPost(String url, String postDataStr, String cookieStr) throws Exception {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            //https的可信声明
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new PostSender().hv);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +="\n" + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	

	public JSONObject loginPost(String url, String postDataStr) throws Exception {
		return loginPost(url, postDataStr, null);
	}
	
	public JSONObject loginPost(String url, String postDataStr, String cookieStr) throws Exception {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            //https的可信声明
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new PostSender().hv);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json;");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +="\n" + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	public static JSONObject bookPost(String bookUrl, String bookDataStr, String bookCookie) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(bookUrl);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			if (!"null".equalsIgnoreCase(bookCookie)) {
				conn.setRequestProperty("Cookie", bookCookie);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(bookDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +=" " + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	public static void main(String[] args) {
		String ss = "{\"resultType\":\"1001\",\"resultMsg\":\"success\",\"code\":\"fail\",\"uuid\":\"c334bb67-fcfd-41c5-8557-eac144846b61\",\"res\":{\"PaymentDetails\":{\"PageInfo\":{\"Language\":\"zh\",\"Locale\":\"zh_CN\",\"Currency\":\"CNY\",\"CurrencyRoundTo\":\"1\",\"FromServicing\":\"false\",\"ConversationID\":\"OJ1626194894217\",\"SessionID\":\"A9CCC162FB642080EA5DE1BDF907FDC6.KNIBEServer1Server4\",\"SessionPageRandom\":\"6077710\",\"ReadOnly\":\"false\",\"skin\":\"hainan\",\"LayoutType\":\"\",\"NavSelect\":\"\",\"UserIP\":\"10.220.44.117\",\"POS\":{\"CompanyCode\":\"ibe\"}},\"UserProfileSummary\":{\"UserProfile\":{\"Name\":{\"Prefix\":\"\",\"GivenName\":\"\",\"Surname\":\"纪文池\",\"Verified\":\"false\"},\"MemberDay\":null,\"Email\":\"13409246697\",\"Loyalty\":[{\"Tier\":\"\",\"CustomerId\":\"\"}],\"UnPayBillCount\":\"0\",\"Document\":{\"DocID\":\"113048880\",\"DocType\":\"KN_ID\"},\"NewDocument\":{\"DocID\":\"113048880\",\"DocType\":\"NEW_KN_ID\"},\"Token\":{\"DocID\":\"334CE49CCDCC3AB53343E804AD8F1FBDD4B4421C9C4A1A1038E9F93B378F94E19F10D946046903D2E27D18EF3D3450F5CF22DD22BAE1863013F4674EFEA524E3\",\"DocType\":\"KN_TOKEN\"},\"TokenUUID\":{\"DocID\":\"ec36c5035b09440e9c0321b31af39e40\",\"DocType\":\"KN_TOKENUUID\"},\"IsAuth\":{\"DocID\":\"0\",\"DocType\":\"KN_ISAUTH\"},\"Identity\":{\"IdNo\":\"\"},\"PlusInfo\":{\"ifCoupon\":null,\"plusCouponAmount\":\"0\",\"cardType\":null,\"discountcode\":null,\"amount\":null,\"name\":null,\"baggage\":null,\"seat\":null,\"fulldiscout\":null,\"discountticket\":null,\"handbaggage\":null,\"discountexchange\":null,\"discountrefund\":null,\"discountupgrade\":null,\"HBbonus\":null,\"hbnum\":null,\"cardStartDate\":null,\"cardEndDate\":null,\"rCardEndDate\":null},\"UniqueID\":{\"AuditID\":\"\",\"ID_Context\":\"\",\"Type\":\"\"}}},\"RetrieveBookingForm\":{\"name\":\"retrieveBookingForm\",\"action\":\"/bookingManagement/home.do\",\"method\":\"post\",\"ReferenceInput\":{\"type\":\"string\",\"name\":\"bookingSearch/reference\",\"value\":\"202107140047481268\"},\"EmailInput\":{\"type\":\"string\",\"name\":\"bookingSearch/email\",\"value\":\"\"}},\"Booking\":{\"SuperPNR_ID\":\"202107140047481268\",\"StartDate\":\"2021-07-31T07:15:00\",\"EndDate\":\"2021-07-31T09:25:00\",\"LastModifiedDate\":\"2021-07-13T16:47:51\",\"PNRNumber\":\"PD5J93\",\"BookingStatus\":\"pendingPayment\",\"Product\":[{\"Type\":\"Flight\",\"ProductNumber\":\"1002\",\"MasterProductNumber\":\"0\",\"BookingStatus\":\"booked\",\"AllowSelfService\":\"false\",\"BookingChannel\":\"TSDF\",\"BookingDate\":\"2021-07-13T16:47:51\",\"HistoryID\":\"1000\",\"PaymentState\":\"PaymentDue\",\"Flight\":[{\"BookingReference\":\"PD5J93\",\"DocumentationRequired\":\"None\",\"FlightDetails\":[{\"Duration\":\"2:10\",\"DirectionInd\":\"OneWay\",\"JourneyId\":\"1\",\"Summary\":{\"Status\":\"NO\",\"CabinClass\":\"CC\",\"Departure\":{\"IATA\":\"PKX\",\"Airport\":\"北京大兴国际\",\"DateTime\":\"2021-07-31T07:15:00\",\"Date\":\"2021-07-31\",\"Time\":\"07:15:00\"},\"Arrival\":{\"IATA\":\"SHA\",\"Airport\":\"上海虹桥\",\"DateTime\":\"2021-07-31T09:25:00\",\"Date\":\"2021-07-31\",\"Time\":\"09:25:00\"},\"AirlineList\":{\"Airline\":[{\"MarketingAirline\":\"KN\"}]}},\"FlightLeg\":[{\"InfoSource\":\"Domestic\",\"Loyalty\":\"\",\"Status\":\"NO\",\"FlightLegRPH\":\"1\",\"Duration\":\"2:10\",\"CabinClass\":\"C\",\"FlightNumber\":\"5737\",\"OnlineCheckin\":\"false\",\"Departure\":{\"IATA\":\"PKX\",\"Airport\":\"北京大兴国际\",\"TS_CityCode\":\"PKX\",\"Terminal\":\"--\",\"DateTime\":\"2021-07-31T07:15:00\",\"Date\":\"2021-07-31\",\"Time\":\"07:15:00\"},\"Arrival\":{\"IATA\":\"SHA\",\"Airport\":\"上海虹桥\",\"TS_CityCode\":\"SHA\",\"Terminal\":\"T2\",\"DateTime\":\"2021-07-31T09:25:00\",\"Date\":\"2021-07-31\",\"Time\":\"09:25:00\"},\"MarketingAirline\":\"KN\",\"OperatingAirline\":\"KN\",\"Equipment\":{\"AirEquipType\":\"33L\"},\"BookingClassAvails\":{\"BookingClassAvail\":[{\"RPH\":\"000\",\"ResBookDesigCode\":\"J\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"001\",\"ResBookDesigCode\":\"C\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"002\",\"ResBookDesigCode\":\"W\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"003\",\"ResBookDesigCode\":\"Y\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"004\",\"ResBookDesigCode\":\"M\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"005\",\"ResBookDesigCode\":\"E\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"006\",\"ResBookDesigCode\":\"H\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"007\",\"ResBookDesigCode\":\"K\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"008\",\"ResBookDesigCode\":\"L\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"009\",\"ResBookDesigCode\":\"N\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"0010\",\"ResBookDesigCode\":\"R\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"0011\",\"ResBookDesigCode\":\"S\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"0012\",\"ResBookDesigCode\":\"V\",\"ResBookDesigQuantity\":\"A\"},{\"RPH\":\"0013\",\"ResBookDesigCode\":\"D\",\"ResBookDesigQuantity\":\"6\"},{\"RPH\":\"0014\",\"ResBookDesigCode\":\"U\",\"ResBookDesigQuantity\":\"A\"}]}}]}],\"Price\":{\"NegotiatedFare\":\"\",\"FareFamilyCode\":\"CJX\",\"FareFamilyName\":\"\",\"Base\":{\"Amount\":\"2598\",\"Currency\":\"CNY\",\"amountOld\":\"2598\"},\"Taxes\":{\"Tax\":[{\"Amount\":\"50.0\",\"Currency\":\"CNY\",\"Code\":\"CN\"}]},\"SubTotal\":{\"Amount\":\"2648\",\"Currency\":\"CNY\"},\"Total\":{\"Amount\":\"2648\",\"Currency\":\"CNY\"},\"FareBreakdown\":[{\"PassengerTypeQuantity\":{\"Code\":\"ADT\",\"PTCSubType\":\"\",\"Quantity\":\"1\"},\"PassengerFare\":{\"BaseFare\":{\"Amount\":\"2598.0\",\"Currency\":\"CNY\",\"AmountOld\":\"2598\"},\"Taxes\":{\"Tax\":[{\"Amount\":\"50.0\",\"Currency\":\"CNY\",\"TaxCode\":\"CN\"},{\"Amount\":\"0.0\",\"Currency\":\"CNY\",\"TaxCode\":\"YQ\"}]},\"TotalFare\":{\"Adjusted\":\"2648.0\",\"Amount\":\"2648.0\",\"Currency\":\"CNY\"}}}],\"FareInfos\":[{\"FareInfo\":[{\"FlightSegmentRPH\":\"1\",\"FareReference\":{\"ResBookDesigCode\":\"C\"},\"FilingAirline\":{\"Code\":\"KN\"},\"DepartureAirport\":{\"CodeContext\":\"IATA\",\"LocationCode\":\"PKX\"},\"ArrivalAirport\":{\"CodeContext\":\"IATA\",\"LocationCode\":\"SHA\"},\"FareInfo\":[{\"DisCount\":\"0.44808554674025525\",\"FareBasis\":\"C160\",\"Fare\":[{\"BaseAmount\":\"2598.0\",\"TaxAmount\":\"50.0\",\"TotalFare\":\"2648.0\",\"Taxes\":{\"Tax\":[{\"Amount\":\"50.0\",\"CurrencyCode\":\"CNY\",\"OriginalAmount\":\"50.0\",\"OriginalCurrencyCode\":\"CNY\",\"TaxCode\":\"CN\"},{\"Amount\":\"0.0\",\"CurrencyCode\":\"CNY\",\"OriginalAmount\":\"0.0\",\"OriginalCurrencyCode\":\"CNY\",\"TaxCode\":\"YQ\"}]}}],\"TPA_Extensions\":{\"ProductInfo\":{\"EI\":\"不得签转/变更退票收费/AB\",\"Name\":\"超级享\"},\"FareFamilyInfo\":{\"ChangeToOpen\":\"true\",\"Description\":\"自愿变更: 航班规定离站时间前7天（含）之前提出: 免费变更;航班规定离站时间前7天至72小时（含）之内提出: 免费变更; 航班规定离站时间前72小时至4小时（含）之内提出: 收取客票价20%的变更费; 航班规定离站时间前4小时之后提出: 收取客票价40%的变更费。 自愿退票: 航班规定离站时间前7天（含）之前提出: 免费退票; 航班规定离站时间前7天至72小时（含）之内提出: 免费退票; 航班规定离站时间前72小时至4小时（含）之内提出： 收取客票价30%的退票费; 航班规定离站时间前4小时之后提出: 收取客票价50%的退票费。\",\"DomesticSelfCheckin\":\"true\",\"FareBasis\":\"C160\",\"FareFamilyCode\":\"CJX\",\"FareFamilyName\":\"超级享\",\"InternationalSelfCheckin\":\"true\",\"Upgradable\":\"true\",\"UserType\":\"ADT\",\"Username\":\"*\",\"Refund\":{\"BeforeDepartureRate\":\"0.4\",\"Eligible\":\"true\",\"Refund_str\":\"[{\\\"Penalty\\\":[{\\\"Amount\\\":\\\"0.0\\\",\\\"ArriveStatus\\\":\\\"0\\\",\\\"DepartureStatus\\\":\\\"0\\\",\\\"MinAmount\\\":\\\"0.0\\\",\\\"PenaltyType\\\":\\\"1\\\",\\\"Percent\\\":\\\"1\\\",\\\"EcfareRuleTime\\\":[{\\\"MaxTime\\\":\\\"72\\\",\\\"MaxTimeFlag\\\":\\\"1\\\",\\\"MaxTimeUnit\\\":\\\"H\\\",\\\"MinTime\\\":\\\"7\\\",\\\"MinTimeFlag\\\":\\\"0\\\",\\\"MinTimeUnit\\\":\\\"D\\\"}]}]},{\\\"Penalty\\\":[{\\\"Amount\\\":\\\"0.0\\\",\\\"ArriveStatus\\\":\\\"0\\\",\\\"DepartureStatus\\\":\\\"0\\\",\\\"MinAmount\\\":\\\"0.0\\\",\\\"PenaltyType\\\":\\\"1\\\",\\\"Percent\\\":\\\"1\\\",\\\"EcfareRuleTime\\\":[{\\\"MaxTime\\\":\\\"7\\\",\\\"MaxTimeFlag\\\":\\\"1\\\",\\\"MaxTimeUnit\\\":\\\"D\\\",\\\"MinTime\\\":\\\"12\\\",\\\"MinTimeFlag\\\":\\\"1\\\",\\\"MinTimeUnit\\\":\\\"M\\\"}]}]},{\\\"Penalty\\\":[{\\\"Amount\\\":\\\"30.0\\\",\\\"ArriveStatus\\\":\\\"0\\\",\\\"DepartureStatus\\\":\\\"0\\\",\\\"MinAmount\\\":\\\"0.0\\\",\\\"PenaltyType\\\":\\\"1\\\",\\\"Percent\\\":\\\"1\\\",\\\"EcfareRuleTime\\\":[{\\\"MaxTime\\\":\\\"4\\\",\\\"MaxTimeFlag\\\":\\\"1\\\",\\\"MaxTimeUnit\\\":\\\"H\\\",\\\"MinTime\\\":\\\"72\\\",\\\"MinTimeFlag\\\":\\\"0\\\",\\\"MinTimeUnit\\\":\\\"H\\\"}]}]},{\\\"Penalty\\\":[{\\\"Amount\\\":\\\"50.0\\\",\\\"ArriveStatus\\\":\\\"1\\\",\\\"DepartureStatus\\\":\\\"0\\\",\\\"MinAmount\\\":\\\"0.0\\\",\\\"PenaltyType\\\":\\\"1\\\",\\\"Percent\\\":\\\"1\\\",\\\"EcfareRuleTime\\\":[{\\\"MaxTime\\\":\\\"12\\\",\\\"MaxTimeFlag\\\":\\\"1\\\",\\\"MaxTimeUnit\\\":\\\"M\\\",\\\"MinTime\\\":\\\"4\\\",\\\"MinTimeFlag\\\":\\\"0\\\",\\\"MinTimeUnit\\\":\\\"H\\\"}]}]}]\",\"TimeThreshold\":\"-4\",\"Voluntary\":\"true\",\"Penalty\":{\"Amount\":\"50.0\",\"ArriveStatus\":\"1\",\"DepartureStatus\":\"0\",\"MinAmount\":\"0.0\",\"PenaltyType\":\"1\",\"Percent\":\"1\",\"EcfareRuleTime\":{\"MaxTime\":\"12\",\"MaxTimeFlag\":\"1\",\"MaxTimeUnit\":\"M\",\"MinTime\":\"4\",\"MinTimeFlag\":\"0\",\"MinTimeUnit\":\"H\"}}},\"Datechange\":{\"BeforeDepartureRate\":\"0.4\",\"Datechange_str\":\"[{\\\"Penalty\\\":[{\\\"Amount\\\":\\\"0.0\\\",\\\"ArriveStatus\\\":\\\"0\\\",\\\"DepartureStatus\\\":\\\"0\\\",\\\"MinAmount\\\":\\\"0.0\\\",\\\"PenaltyType\\\":\\\"1\\\",\\\"Percent\\\":\\\"1\\\",\\\"EcfareRuleTime\\\":[{\\\"MaxTime\\\":\\\"72\\\",\\\"MaxTimeFlag\\\":\\\"1\\\",\\\"MaxTimeUnit\\\":\\\"H\\\",\\\"MinTime\\\":\\\"7\\\",\\\"MinTimeFlag\\\":\\\"0\\\",\\\"MinTimeUnit\\\":\\\"D\\\"}]}]},{\\\"Penalty\\\":[{\\\"Amount\\\":\\\"0.0\\\",\\\"ArriveStatus\\\":\\\"0\\\",\\\"DepartureStatus\\\":\\\"0\\\",\\\"MinAmount\\\":\\\"0.0\\\",\\\"PenaltyType\\\":\\\"1\\\",\\\"Percent\\\":\\\"1\\\",\\\"EcfareRuleTime\\\":[{\\\"MaxTime\\\":\\\"7\\\",\\\"MaxTimeFlag\\\":\\\"1\\\",\\\"MaxTimeUnit\\\":\\\"D\\\",\\\"MinTime\\\":\\\"12\\\",\\\"MinTimeFlag\\\":\\\"1\\\",\\\"MinTimeUnit\\\":\\\"M\\\"}]}]},{\\\"Penalty\\\":[{\\\"Amount\\\":\\\"20.0\\\",\\\"ArriveStatus\\\":\\\"0\\\",\\\"DepartureStatus\\\":\\\"0\\\",\\\"MinAmount\\\":\\\"0.0\\\",\\\"PenaltyType\\\":\\\"1\\\",\\\"Percent\\\":\\\"1\\\",\\\"EcfareRuleTime\\\":[{\\\"MaxTime\\\":\\\"4\\\",\\\"MaxTimeFlag\\\":\\\"1\\\",\\\"MaxTimeUnit\\\":\\\"H\\\",\\\"MinTime\\\":\\\"72\\\",\\\"MinTimeFlag\\\":\\\"0\\\",\\\"MinTimeUnit\\\":\\\"H\\\"}]}]},{\\\"Penalty\\\":[{\\\"Amount\\\":\\\"40.0\\\",\\\"ArriveStatus\\\":\\\"1\\\",\\\"DepartureStatus\\\":\\\"0\\\",\\\"MinAmount\\\":\\\"0.0\\\",\\\"PenaltyType\\\":\\\"1\\\",\\\"Percent\\\":\\\"1\\\",\\\"EcfareRuleTime\\\":[{\\\"MaxTime\\\":\\\"12\\\",\\\"MaxTimeFlag\\\":\\\"1\\\",\\\"MaxTimeUnit\\\":\\\"M\\\",\\\"MinTime\\\":\\\"4\\\",\\\"MinTimeFlag\\\":\\\"0\\\",\\\"MinTimeUnit\\\":\\\"H\\\"}]}]}]\",\"Limit\":\"*\",\"TimeThreshold\":\"-4\",\"Type\":\"percent\",\"Penalty\":{\"Amount\":\"40.0\",\"ArriveStatus\":\"1\",\"DepartureStatus\":\"0\",\"MinAmount\":\"0.0\",\"PenaltyType\":\"1\",\"Percent\":\"1\",\"EcfareRuleTime\":{\"MaxTime\":\"12\",\"MaxTimeFlag\":\"1\",\"MaxTimeUnit\":\"M\",\"MinTime\":\"4\",\"MinTimeFlag\":\"0\",\"MinTimeUnit\":\"H\"}}}},\"MarketingData\":{\"LowestFare\":{\"Amount\":\"2598\",\"Cabin\":\"C\"}}},\"PTC\":{\"PTCSubType\":\"\",\"PassengerTypeCode\":\"ADT\",\"Quantity\":\"1\"},\"Loyalty\":null}],\"ProductInfo\":{\"CKIN\":\"\",\"EI\":\"\",\"Name\":\"超级享\",\"Type\":\"\"},\"FareFamily\":{\"Name\":\"超级享\",\"Code\":\"CJX\",\"ChangeToOpen\":\"true\",\"Description\":\"自愿变更: 航班规定离站时间前7天（含）之前提出: 免费变更;航班规定离站时间前7天至72小时（含）之内提出: 免费变更; 航班规定离站时间前72小时至4小时（含）之内提出: 收取客票价20%的变更费; 航班规定离站时间前4小时之后提出: 收取客票价40%的变更费。 自愿退票: 航班规定离站时间前7天（含）之前提出: 免费退票; 航班规定离站时间前7天至72小时（含）之内提出: 免费退票; 航班规定离站时间前72小时至4小时（含）之内提出： 收取客票价30%的退票费; 航班规定离站时间前4小时之后提出: 收取客票价50%的退票费。\",\"DomesticSelfCheckin\":\"true\",\"FareBasis\":\"C160\",\"InternationalSelfCheckin\":\"true\",\"Upgradable\":\"true\",\"UserType\":\"COMMON\",\"Username\":\"*\",\"CabinName\":\"\",\"Refund\":{\"BeforeDepartureRate\":\"0.4\",\"Eligible\":\"true\",\"Refund_str\":\"[]\",\"TimeThreshold\":\"-4\",\"Voluntary\":\"true\"},\"Datechange\":{\"BeforeDepartureRate\":\"0.4\",\"Datechange_str\":\"[]\",\"Limit\":\"*\",\"TimeThreshold\":\"-4\",\"Type\":\"percent\"}}}]}]},\"PassengerDetails\":{\"Passenger\":[{\"Type\":\"ADT\",\"RPH\":\"1\",\"TravelerRefNumber\":\"1\",\"Name\":{\"Prefix\":\"\",\"GivenName\":\"\",\"Surname\":\"石竟革\"},\"Documentation\":{\"DocType\":\"5\",\"Nationality\":null,\"Passport\":{\"Number\":\"511823198401101378\",\"Expiry\":{\"Date\":\"\"}}}}]},\"_text\":\"721H70D71D121M41H720H121M40H721H70D71D121M41H720H121M40HBUY_AIR_TICKETPKX KN SHA 2598.00C160 CNY2598.00ENDPKX KN SHA 2598.00C160 CNY2598.00ENDFN/M/FCNY2598.00/SCNY2598.00/C0.00/XCNY50.00/TCNY50.00CN/TEXEMPTYQ/ACNY2648.00/P1石竟革Chinafalsetrue*23*\"}]}],\"ContactDetails\":{\"Name\":{\"Prefix\":\"\",\"GivenName\":\"\",\"Surname\":\"石竟革\"},\"Email\":null,\"Address\":{\"City\":null,\"PostCode\":null,\"State\":null,\"Country\":{\"Code\":\"CN\",\"_text\":\"China\"}},\"Telephone\":[{\"Country\":\"\",\"Area\":\"\",\"Number\":\"15083142384\",\"Type\":\"MOBILE\"}]},\"Total\":{\"Price\":{\"Amount\":\"2648\",\"Currency\":\"CNY\",\"ActualPayment\":\"2648\",\"TotalCCPayment\":\"true\"},\"Balance\":{\"Currency\":\"CNY\",\"Amount\":\"2648\"},\"PricePerPerson\":{\"Amount\":\"2648\",\"Currency\":\"CNY\"}},\"HU_CustomersProductInfo\":{\"Customer\":[{\"CustomerBlock_RPH\":\"1\",\"Type\":\"ADT\",\"ProductInfos\":{\"ProductInfo\":[{\"ProductNumber\":\"1002\",\"FlightLegRPH\":\"1\"}]},\"Details\":{\"Name\":null}}]}},\"Payment\":{\"Total\":{\"Price\":{\"Total\":{\"Amount\":\"2648\",\"Currency\":\"CNY\"}}}},\"PaymentMethods\":{\"LoyaltyCashInput\":{\"type\":\"fixed\",\"name\":\"quantitySliderValue\",\"value\":\"\"},\"LoyaltyMilesInput\":{\"type\":\"fixed\",\"name\":\"amountSliderValue\",\"value\":\"\"},\"PaymentOptions\":{\"PaymentOption\":[{\"value\":\"101\",\"OptionDetails\":{\"Group\":[{\"GroupId\":\"RB\",\"GroupName\":\"网銀支付\"},{\"GroupId\":\"AP\",\"GroupName\":\"第三方支付\",\"Bank\":[{\"BankId\":\"ALIPAY\",\"BankName\":\"支付宝二维码\",\"Image\":\"alipay.gif\"},{\"BankId\":\"YEEPAYSHARE\",\"BankName\":\"易宝支付\",\"Image\":\"yeepay.gif\"}]},{\"GroupId\":\"CC\",\"GroupName\":\"信用卡支付\"},{\"GroupId\":\"MP\",\"GroupName\":\"移动支付\",\"Bank\":[{\"BankId\":\"TENPAYGW_NATIVE\",\"BankName\":\"微信支付\",\"Image\":\"wechatpay.png\"}]}]}},{\"value\":\"105\",\"OptionDetails\":{\"CreditCards\":{\"TimeLimitConfig\":\"7\",\"FlightTimeLimitConfig\":\"2\",\"CreditCard\":[{\"Name\":\"中国银行\",\"Index\":\"4\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{16})$\",\"Bankid\":\"BOC\",\"Remark\":\"限信用卡,卡号为16位数字\"},{\"Name\":\"农业银行\",\"Index\":\"5\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{16})$\",\"Bankid\":\"ABC\",\"Remark\":\"限信用卡,卡号为16位数字\"},{\"Name\":\"招商银行\",\"Index\":\"3\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{16})$\",\"Bankid\":\"CMB\",\"Remark\":\"限信用卡,卡号为16位数字\"},{\"Name\":\"工商银行\",\"Index\":\"1\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{15,16})$\",\"Bankid\":\"ICBC\",\"Remark\":\"适用卡种有牡丹双币种信用卡、单币种信用卡，卡号为15或16位数字\"},{\"Name\":\"建设银行\",\"Index\":\"2\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{16})$\",\"Bankid\":\"CCB\",\"Remark\":\"适用卡种有信用卡和准贷记卡，卡号为16位数字\"},{\"Name\":\"中国银行\",\"Index\":\"4\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{16})$\",\"Bankid\":\"BOC\",\"Remark\":\"限信用卡,卡号为16位数字\"},{\"Name\":\"农业银行\",\"Index\":\"5\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{16})$\",\"Bankid\":\"ABC\",\"Remark\":\"限信用卡,卡号为16位数字\"},{\"Name\":\"招商银行\",\"Index\":\"3\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{16})$\",\"Bankid\":\"CMB\",\"Remark\":\"限信用卡,卡号为16位数字\"},{\"Name\":\"工商银行\",\"Index\":\"1\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{15,16})$\",\"Bankid\":\"ICBC\",\"Remark\":\"适用卡种有牡丹双币种信用卡、单币种信用卡，卡号为15或16位数字\"},{\"Name\":\"建设银行\",\"Index\":\"2\",\"Enabled\":\"true\",\"RegularExpression\":\"^([0-9]{16})$\",\"Bankid\":\"CCB\",\"Remark\":\"适用卡种有信用卡和准贷记卡，卡号为16位数字\"}]},\"Collections\":{\"TimeLimitConfig\":\"7\",\"FlightTimeLimitConfig\":\"2\",\"Collection\":[{\"Index\":\"221\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"（西城区）西单民航大厦\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"010-66569148\",\"Closing\":\"17:00\",\"Opening\":\"08:30\",\"CityCode\":\"PEK\",\"CityName\":\"北京\",\"Name\":\"西单民航大厦\",\"CityRef\":\"B\"},{\"Index\":\"376\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认.\",\"Enabled\":\"true\",\"Address\":\"市北京南路钻石城5号新疆数码港大厦一层\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E\",\"Phone\":\"0991-3667700/3667711\",\"Closing\":\"17:00\",\"Opening\":\"11:00\",\"CityCode\":\"URC\",\"CityName\":\"乌鲁木齐\",\"Name\":\"新疆数码港大厦\",\"CityRef\":\"W\"},{\"Index\":\"415\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"深南东路4002号鸿隆世纪广场A座25层A-1\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0755-83660785\",\"Closing\":\"17:00\",\"Opening\":\"08:30\",\"CityCode\":\"SZX\",\"CityName\":\"深圳\",\"Name\":\"深南东路\",\"CityRef\":\"S\"},{\"Index\":\"319\",\"Memo\":\"\",\"Enabled\":\"true\",\"Address\":\"新航站楼海航售票柜台\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0371-68511226\",\"Closing\":\"17:30\",\"Opening\":\"08:30\",\"CityCode\":\"CGO\",\"CityName\":\"郑州\",\"Name\":\"新郑机场\",\"CityRef\":\"Z\"},{\"Index\":\"317\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"府西街36号盛唐大厦第10层海航售票处\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0351-5282938/978/979\",\"Closing\":\"17:00\",\"Opening\":\"08:30\",\"CityCode\":\"TYN\",\"CityName\":\"太原\",\"Name\":\"太原市府西街\",\"CityRef\":\"T\"},{\"Index\":\"219\",\"Memo\":\"周一至周五0830-1130、1330-1730，星期六、日休息\",\"Enabled\":\"true\",\"Address\":\"商品街东方大厦一楼\",\"Cabin\":\"F|P|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0898-88279489\",\"Closing\":\"17:00\",\"Opening\":\"09:00\",\"CityCode\":\"SYX\",\"CityName\":\"三亚\",\"Name\":\"东方大厦\",\"CityRef\":\"S\"},{\"Index\":\"226\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"广州新白云机场A楼K岛15-16柜台\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"020-86124956,86124957(FAX)\",\"Closing\":\"20:00\",\"Opening\":\"08:00\",\"CityCode\":\"CAN\",\"CityName\":\"广州\",\"Name\":\"新白云机场海航售票处\",\"CityRef\":\"G\"},{\"Index\":\"275\",\"Memo\":\"有海航集团的航班就有人\",\"Enabled\":\"true\",\"Address\":\"机场新楼的二楼候机厅\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0411-86811122/83886955\",\"Closing\":\"22:00\",\"Opening\":\"08:30\",\"CityCode\":\"DLC\",\"CityName\":\"大连\",\"Name\":\"周水子机场\",\"CityRef\":\"D\"},{\"Index\":\"223\",\"Memo\":\"周一至周五0830-1200；1330-1700 节假日不办公\",\"Enabled\":\"true\",\"Address\":\"普陀区江宁路1158号友力国际大厦701BC\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"021-32512100/101/102\",\"Closing\":\"15:00\",\"Opening\":\"08:30\",\"CityCode\":\"SHA\",\"CityName\":\"上海\",\"Name\":\"江宁路\",\"CityRef\":\"S\"},{\"Index\":\"155\",\"Memo\":\"\",\"Enabled\":\"true\",\"Address\":\"国兴大道7号新海航大厦1楼国兴旗舰店\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0898-66710274\",\"Closing\":\"20:00\",\"Opening\":\"08:00\",\"CityCode\":\"HAK\",\"CityName\":\"海口\",\"Name\":\"新海航大厦一楼\",\"CityRef\":\"H\"},{\"Index\":\"278\",\"Memo\":\"有海航集团的航班就有人\",\"Enabled\":\"true\",\"Address\":\"桃仙机场T2航站楼2楼海航柜台（D岛斜对面）\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"024-89398680\",\"Closing\":\"17:00\",\"Opening\":\"08:30\",\"CityCode\":\"SHE\",\"CityName\":\"沈阳\",\"Name\":\"桃仙机场\",\"CityRef\":\"S\"},{\"Index\":\"375\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"扬子江路267号红十月小区旁\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E\",\"Phone\":\"0991-2315195/2315162\",\"Closing\":\"17:00\",\"Opening\":\"11:00\",\"CityCode\":\"URC\",\"CityName\":\"乌鲁木齐\",\"Name\":\"扬子江路267号\",\"CityRef\":\"W\"},{\"Index\":\"221\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"（西城区）西单民航大厦\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"010-66569148\",\"Closing\":\"17:00\",\"Opening\":\"08:30\",\"CityCode\":\"PEK\",\"CityName\":\"北京\",\"Name\":\"西单民航大厦\",\"CityRef\":\"B\"},{\"Index\":\"376\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认.\",\"Enabled\":\"true\",\"Address\":\"市北京南路钻石城5号新疆数码港大厦一层\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E\",\"Phone\":\"0991-3667700/3667711\",\"Closing\":\"17:00\",\"Opening\":\"11:00\",\"CityCode\":\"URC\",\"CityName\":\"乌鲁木齐\",\"Name\":\"新疆数码港大厦\",\"CityRef\":\"W\"},{\"Index\":\"415\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"深南东路4002号鸿隆世纪广场A座25层A-1\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0755-83660785\",\"Closing\":\"17:00\",\"Opening\":\"08:30\",\"CityCode\":\"SZX\",\"CityName\":\"深圳\",\"Name\":\"深南东路\",\"CityRef\":\"S\"},{\"Index\":\"319\",\"Memo\":\"\",\"Enabled\":\"true\",\"Address\":\"新航站楼海航售票柜台\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0371-68511226\",\"Closing\":\"17:30\",\"Opening\":\"08:30\",\"CityCode\":\"CGO\",\"CityName\":\"郑州\",\"Name\":\"新郑机场\",\"CityRef\":\"Z\"},{\"Index\":\"317\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"府西街36号盛唐大厦第10层海航售票处\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0351-5282938/978/979\",\"Closing\":\"17:00\",\"Opening\":\"08:30\",\"CityCode\":\"TYN\",\"CityName\":\"太原\",\"Name\":\"太原市府西街\",\"CityRef\":\"T\"},{\"Index\":\"219\",\"Memo\":\"周一至周五0830-1130、1330-1730，星期六、日休息\",\"Enabled\":\"true\",\"Address\":\"商品街东方大厦一楼\",\"Cabin\":\"F|P|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0898-88279489\",\"Closing\":\"17:00\",\"Opening\":\"09:00\",\"CityCode\":\"SYX\",\"CityName\":\"三亚\",\"Name\":\"东方大厦\",\"CityRef\":\"S\"},{\"Index\":\"226\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"广州新白云机场A楼K岛15-16柜台\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"020-86124956,86124957(FAX)\",\"Closing\":\"20:00\",\"Opening\":\"08:00\",\"CityCode\":\"CAN\",\"CityName\":\"广州\",\"Name\":\"新白云机场海航售票处\",\"CityRef\":\"G\"},{\"Index\":\"275\",\"Memo\":\"有海航集团的航班就有人\",\"Enabled\":\"true\",\"Address\":\"机场新楼的二楼候机厅\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0411-86811122/83886955\",\"Closing\":\"22:00\",\"Opening\":\"08:30\",\"CityCode\":\"DLC\",\"CityName\":\"大连\",\"Name\":\"周水子机场\",\"CityRef\":\"D\"},{\"Index\":\"223\",\"Memo\":\"周一至周五0830-1200；1330-1700 节假日不办公\",\"Enabled\":\"true\",\"Address\":\"普陀区江宁路1158号友力国际大厦701BC\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"021-32512100/101/102\",\"Closing\":\"15:00\",\"Opening\":\"08:30\",\"CityCode\":\"SHA\",\"CityName\":\"上海\",\"Name\":\"江宁路\",\"CityRef\":\"S\"},{\"Index\":\"155\",\"Memo\":\"\",\"Enabled\":\"true\",\"Address\":\"国兴大道7号新海航大厦1楼国兴旗舰店\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"0898-66710274\",\"Closing\":\"20:00\",\"Opening\":\"08:00\",\"CityCode\":\"HAK\",\"CityName\":\"海口\",\"Name\":\"新海航大厦一楼\",\"CityRef\":\"H\"},{\"Index\":\"278\",\"Memo\":\"有海航集团的航班就有人\",\"Enabled\":\"true\",\"Address\":\"桃仙机场T2航站楼2楼海航柜台（D岛斜对面）\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E|V\",\"Phone\":\"024-89398680\",\"Closing\":\"17:00\",\"Opening\":\"08:30\",\"CityCode\":\"SHE\",\"CityName\":\"沈阳\",\"Name\":\"桃仙机场\",\"CityRef\":\"S\"},{\"Index\":\"375\",\"Memo\":\"可刷国内银联卡,请与售票处联系确认\",\"Enabled\":\"true\",\"Address\":\"扬子江路267号红十月小区旁\",\"Cabin\":\"F|A|C|D|Y|B|H|K|L|M|Q|X|U|E\",\"Phone\":\"0991-2315195/2315162\",\"Closing\":\"17:00\",\"Opening\":\"11:00\",\"CityCode\":\"URC\",\"CityName\":\"乌鲁木齐\",\"Name\":\"扬子江路267号\",\"CityRef\":\"W\"}]},\"Deliveries\":{\"TimeLimitConfig\":\"5\",\"FlightTimeLimitConfig\":\"2\"}}}]},\"ProcessedPaymentDetails\":\"false\"},\"RedirectPaymentForm\":{\"name\":\"redirectPaymentForm\",\"method\":\"post\",\"action\":\"/checkout/redirectProcessCheckoutDetails.do\",\"BankIdInput\":{\"name\":\"ThreeDSecure/OnlinePayment/paymentReferences/BankID\",\"type\":\"list\",\"value\":\"\"},\"OpenIDInput\":{\"name\":\"ThreeDSecure/OnlinePayment/paymentReferences/OpenID\",\"type\":\"string\",\"value\":\"\"},\"Delivery\":{\"DeliveryIDInput\":{\"name\":\"delivery\",\"type\":\"list\",\"value\":\"\",\"option\":[{\"value\":\"1\",\"display\":\"不需要行程单\"},{\"value\":\"2\",\"display\":\"机场自取行程单\"},{\"value\":\"4\",\"display\":\"市内售票处自取行程单\"},{\"value\":\"3\",\"display\":\"邮寄行程单\"}]},\"MailDeliveryInformation\":{\"NameInput\":{\"name\":\"surname\",\"type\":\"string\",\"value\":\"\"},\"GenderInput\":{\"name\":\"gender\",\"type\":\"string\",\"value\":\"\"},\"PhoneInput\":{\"name\":\"tel\",\"type\":\"string\",\"value\":\"\"},\"PostalCodeInput\":{\"name\":\"zip\",\"type\":\"string\",\"value\":\"\"},\"AddressInput\":{\"name\":\"address\",\"type\":\"string\",\"value\":\"\"},\"ProvinceInput\":{\"name\":\"province\",\"type\":\"string\",\"value\":\"\"},\"CityInput\":{\"name\":\"city\",\"type\":\"string\",\"value\":\"\"}}}},\"RedirectPaymentCallbackForm\":{\"name\":\"RedirectPaymentCallbackForm\",\"method\":\"post\",\"action\":\"/checkout/redirectPaymentCallback.do\",\"PayAmountInput\":{\"name\":\"payamount\",\"type\":\"string\",\"value\":\"\"},\"OrgIDInput\":{\"name\":\"orgid\",\"type\":\"string\",\"value\":\"\"},\"AppTypeInput\":{\"name\":\"apptype\",\"type\":\"string\",\"value\":\"\"},\"BankIDInput\":{\"name\":\"bankid\",\"type\":\"string\",\"value\":\"\"},\"BillNoInput\":{\"name\":\"billno\",\"type\":\"string\",\"value\":\"\"},\"OrderNoInput\":{\"name\":\"orderno\",\"type\":\"string\",\"value\":\"\"},\"PayDateInput\":{\"name\":\"paydate\",\"type\":\"string\",\"value\":\"\"},\"PayTimeInput\":{\"name\":\"paytime\",\"type\":\"string\",\"value\":\"\"},\"OrderCurtypeInput\":{\"name\":\"ordercurtype\",\"type\":\"string\",\"value\":\"\"},\"OrderTypeInput\":{\"name\":\"ordertype\",\"type\":\"string\",\"value\":\"\"},\"LanInput\":{\"name\":\"lan\",\"type\":\"string\",\"value\":\"\"},\"MsgInput\":{\"name\":\"msg\",\"type\":\"string\",\"value\":\"\"},\"Ext1Input\":{\"name\":\"ext1\",\"type\":\"string\",\"value\":\"\"},\"Ext2Input\":{\"name\":\"ext2\",\"type\":\"string\",\"value\":\"\"},\"PayStatusInput\":{\"name\":\"paystatus\",\"type\":\"string\",\"value\":\"\"},\"ReturnTypeInput\":{\"name\":\"returntype\",\"type\":\"string\",\"value\":\"\"},\"ReturnTransDateInput\":{\"name\":\"returntransdate\",\"type\":\"string\",\"value\":\"\"},\"SignatureInput\":{\"name\":\"signature\",\"type\":\"string\",\"value\":\"\"}},\"LoyaltyPaymentForm\":{\"name\":\"LoyaltyPaymentForm\",\"action\":\"/loyalty/loyaltyPayment.do\",\"method\":\"post\",\"LoyaltyQuantityInput\":{\"type\":\"number\",\"name\":\"loyaltyQuantity\",\"value\":\"0\"},\"AmountRedeemedInput\":{\"type\":\"number\",\"name\":\"amountRedeemed\",\"value\":\"0\"},\"LoyaltyPasswordInput\":{\"type\":\"string\",\"name\":\"loyaltyPassword\",\"value\":\"\"}}},\"userInfoID\":\"113048880\"}}";
		JSONObject json = JSONObject.parseObject(ss);
		String pnrId = json.getJSONObject("res").getJSONObject("PaymentDetails").getJSONObject("Booking").getString("SuperPNR_ID");
		System.out.println(pnrId);
		
		
	}
	
	HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                               + session.getPeerHost());
            return true;
        }
    };
	
	private static void trustAllHttpsCertificates() throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
				.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
				.getSocketFactory());
	}
 
	static class miTM implements javax.net.ssl.TrustManager,
			javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}
 
		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}
 
		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}
 
		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
 
		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}
}
