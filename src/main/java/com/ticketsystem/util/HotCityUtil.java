package com.ticketsystem.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.GetSender;
import com.ticketsystem.test.GetPostTest2;
import com.ticketsystem.util.DemoData;

public class HotCityUtil {
	public static JSONArray hotCityArr = new JSONArray();
	public static String[] letterArr = new String[] {"A","B","C","D","E","F","G","H","J","K","L","M","N","O","P","Q","R","S","T","W","X","Y","Z"};
	public static int letterIndex = 0;
	
	public static String getCityCode(String iata) {
		String cityCode = "";
		if (hotCityArr.size()<=0) {
			String hotCityUrl = DemoData.hotCityUrl;
			String param = "_="+String.valueOf(Math.random()).substring(2, 15);
			String cookieStr = "session=s~b6028870-9cb9-4d8c-904c-ff8153f8c2c7.7753f68224abe5cee1455135f509f360; " ;
			JSONObject hotCityJson = new GetSender().hotCityGet(hotCityUrl, param, cookieStr);
			if(hotCityJson.size()>0) {
				String cityStr = hotCityJson.getString("responseBody");
				if (cityStr.length() >0) {
					letterIndex = 0;
					JSONObject originJson = JSONObject.parseObject(cityStr);
					JSONArray abcd = originJson.getJSONArray("ABCD");
					for (int i=0;i<abcd.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = abcd.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("airCode").startsWith("AIR_"+iata)
									|| letterJson.getString("airCode").startsWith("AirCn"+iata)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
				}
			}
		} else {
			for (int i=0;i<hotCityArr.size();i++) {
				if (hotCityArr.getJSONObject(i).getString("K").startsWith("AIR_"+iata)
						|| hotCityArr.getJSONObject(i).getString("K").startsWith("AirCn"+iata)) {
					cityCode = hotCityArr.getJSONObject(i).getString("V");
				}
			}
		}
		
		return cityCode;
	}
	
	public static String getCityCodeByName(String cityName) {
		String cityCode = "";
		if (hotCityArr.size()<=0) {
			
			String hotCityUrl = DemoData.hotCityUrl;
			String param = "_="+String.valueOf(Math.random()).substring(2, 15);
			String cookieStr = "X-LB=2.728.fc1ba9b5.50; session=s~4d102c1b-c461-480a-a89a-58d6c1f315cd.87845c1efe5675145fbda63041c56404; " ;
			JSONObject hotCityJson = new GetSender().hotCityGet(hotCityUrl, param, cookieStr);
			if(hotCityJson.size()>0) {
				String cityStr = hotCityJson.getString("responseBody");
				if (true) {
					letterIndex = 0;
					JSONObject originJson = JSONObject.parseObject(cityStr);
					String ss = "{" + 
							"    \"ABCD\": [" + 
							"        {" + 
							"            \"A\": [" + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AirCnAERSHAN859\"," + 
							"                    \"cityCode\": \"CitCnAERSHAN267\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnANSHUNH376\"," + 
							"                    \"cityCode\": \"CitCnANSHUN182\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnANKANGF896\"," + 
							"                    \"cityCode\": \"CitCnANKANG153\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"B\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_BAV_CN\"," + 
							"                    \"cityCode\": \"CITY_BAV_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnCHANGAN245\"," + 
							"                    \"cityCode\": \"CitCnBAICHEN346\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AirCnBAYANNA538\"," + 
							"                    \"cityCode\": \"CitCnBAYANNA585\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_BHY_CN\"," + 
							"                    \"cityCode\": \"CITY_BHY_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCn39Beiji931\"," + 
							"                    \"cityCode\": \"CITY_BJS_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnFEIXION382\"," + 
							"                    \"cityCode\": \"CitCnBIJIE044\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnBAZHONG912\"," + 
							"                    \"cityCode\": \"CitCnBAZHONG274\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"C\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnLONGJIA531\"," + 
							"                    \"cityCode\": \"CITY_CGQ_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_CSX_CN\"," + 
							"                    \"cityCode\": \"CITY_CSX_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnCHANGZH124\"," + 
							"                    \"cityCode\": \"CitCnCHANGZH373\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_CZX_CN\"," + 
							"                    \"cityCode\": \"CITY_CZX_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_CTU_CN\"," + 
							"                    \"cityCode\": \"CITY_CTU_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_CIF_CN\"," + 
							"                    \"cityCode\": \"CITY_CIF_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnCHIZHOU359\"," + 
							"                    \"cityCode\": \"CitCHIZHOU082\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AIR_CKG_CN\"," + 
							"                    \"cityCode\": \"CITY_CKG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AirCnCHONGQI263\"," + 
							"                    \"cityCode\": \"CITY_CKG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"CitCnCHANGBA768\"," + 
							"                    \"cityCode\": \"CitCnCHANGBA768\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnCHANBAI661\"," + 
							"                    \"cityCode\": \"CitCnCHENGDE247\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"D\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_DLU_CN\"," + 
							"                    \"cityCode\": \"CITY_DLU_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_DLC_CN\"," + 
							"                    \"cityCode\": \"CITY_DLC_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnDAOCHEN986\"," + 
							"                    \"cityCode\": \"CitCnDAOCHEN354\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_DAT_CN\"," + 
							"                    \"cityCode\": \"CITY_DAT_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnDAZHOUH786\"," + 
							"                    \"cityCode\": \"CitCnDAZHOU180\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_DIG_CN\"," + 
							"                    \"cityCode\": \"CITY_DIG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_DOY_CN\"," + 
							"                    \"cityCode\": \"CITY_DOY_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_DNH_CN\"," + 
							"                    \"cityCode\": \"CITY_DNH_CN\"" + 
							"                }" + 
							"            ]" + 
							"        }" + 
							"    ]," + 
							"    \"EFGHJ\": [" + 
							"        {" + 
							"            \"E\": [" + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AirCnEERDUOS606\"," + 
							"                    \"cityCode\": \"CitCnEERDUOS393\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"F\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_FUG_CN\"," + 
							"                    \"cityCode\": \"CITY_FUG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnFOSHANS685\"," + 
							"                    \"cityCode\": \"CitCnFOSHAN168\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_FOC_CN\"," + 
							"                    \"cityCode\": \"CITY_FOC_CN\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"G\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_KOW_CN\"," + 
							"                    \"cityCode\": \"CITY_KOW_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_CAN_CN\"," + 
							"                    \"cityCode\": \"CITY_CAN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_KWL_CN\"," + 
							"                    \"cityCode\": \"CITY_KWL_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_KWE_CN\"," + 
							"                    \"cityCode\": \"CITY_KWE_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnLIUPANS600\"," + 
							"                    \"cityCode\": \"CitCnGUYUAN194\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"H\": [" + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AIR_HRB_CN\"," + 
							"                    \"cityCode\": \"CITY_HRB_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_HAK_CN\"," + 
							"                    \"cityCode\": \"CITY_HAK_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AIR_HLD_CN\"," + 
							"                    \"cityCode\": \"CITY_HLD_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnHEBEIHA652\"," + 
							"                    \"cityCode\": \"CitCnHANDAN147\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_HGH_CN\"," + 
							"                    \"cityCode\": \"CITY_HGH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_HZG_CN\"," + 
							"                    \"cityCode\": \"CITY_HZG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_HFE_CN\"," + 
							"                    \"cityCode\": \"CITY_HFE_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnHENGYAN319\"," + 
							"                    \"cityCode\": \"CitCnHENGYAN378\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnABAHONG690\"," + 
							"                    \"cityCode\": \"CitCnHONGYUA402\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnHUAIHUA118\"," + 
							"                    \"cityCode\": \"CitCnHUAIHUA270\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnHUAIANL016\"," + 
							"                    \"cityCode\": \"CitCnHUAIAN159\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AIR_HET_CN\"," + 
							"                    \"cityCode\": \"CITY_HET_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnHUIZHOU058\"," + 
							"                    \"cityCode\": \"CitHUIZHOU100\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnHUANGSH050\"," + 
							"                    \"cityCode\": \"CitCnHUANGSH486\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"J\": [" + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AIR_JGN_CN\"," + 
							"                    \"cityCode\": \"CITY_JGN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_SWA_CN\"," + 
							"                    \"cityCode\": \"CITY_SWA_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_TNA_CN\"," + 
							"                    \"cityCode\": \"CITY_TNA_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnJINCHAN359\"," + 
							"                    \"cityCode\": \"CitCnJINCHAN363\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_JHG_CN\"," + 
							"                    \"cityCode\": \"CITY_JHG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_JNG_CN\"," + 
							"                    \"cityCode\": \"CITY_JNG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AirCnJIUZHAI515\"," + 
							"                    \"cityCode\": \"CitCnJIUZHAI616\"" + 
							"                }" + 
							"            ]" + 
							"        }" + 
							"    ]," + 
							"    \"KLMNOP\": [" + 
							"        {" + 
							"            \"K\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnKANGDIN360\"," + 
							"                    \"cityCode\": \"CitCnKANGDIN364\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_KHG_CN\"," + 
							"                    \"cityCode\": \"CITY_KHG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_KMG_CN\"," + 
							"                    \"cityCode\": \"CITY_KMG_CN\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"L\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_LHW_CN\"," + 
							"                    \"cityCode\": \"CITY_LHW_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_LXA_CN\"," + 
							"                    \"cityCode\": \"CITY_LXA_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnLIANCHE462\"," + 
							"                    \"cityCode\": \"AirCnLIANCHE462\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AIR_LYG_CN\"," + 
							"                    \"cityCode\": \"CITY_LYG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_LJG_CN\"," + 
							"                    \"cityCode\": \"CITY_LJG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_LYI_CN\"," + 
							"                    \"cityCode\": \"CITY_LYI_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AirCnLVLIANG707\"," + 
							"                    \"cityCode\": \"CitCnLVLIANG278\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnLINZHI179\"," + 
							"                    \"cityCode\": \"CitCn485\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnLIULIAN378\"," + 
							"                    \"cityCode\": \"CitCnLIULIAN382\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnLIUZHOU029\"," + 
							"                    \"cityCode\": \"CitCnLIUZHOU313\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnLIANCHE462\"," + 
							"                    \"cityCode\": \"CitCnLIANCHE466\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_LZO_CN\"," + 
							"                    \"cityCode\": \"CITY_LZO_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnCHENGXI474\"," + 
							"                    \"cityCode\": \"CitCnLONGNAN278\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"M\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_LUM_CN\"," + 
							"                    \"cityCode\": \"CITY_LUM_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AirCnWULUNHA495\"," + 
							"                    \"cityCode\": \"CitCnMANZHOU512\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnMIANYAN113\"," + 
							"                    \"cityCode\": \"CitCnMIANYAN381\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AirCnMUDANJI303\"," + 
							"                    \"cityCode\": \"CitCnMUDANJI583\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"N\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_KHN_CN\"," + 
							"                    \"cityCode\": \"CITY_KHN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_NKG_CN\"," + 
							"                    \"cityCode\": \"CITY_NKG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_NNG_CN\"," + 
							"                    \"cityCode\": \"CITY_NNG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_NTG_CN\"," + 
							"                    \"cityCode\": \"CITY_NTG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_NNY_CN\"," + 
							"                    \"cityCode\": \"CITY_NNY_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_NGB_CN\"," + 
							"                    \"cityCode\": \"CITY_NGB_CN\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"O\": []" + 
							"        }," + 
							"        {" + 
							"            \"P\": [" + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AirCnPANZHIH445\"," + 
							"                    \"cityCode\": \"CitCnPANZHIH497\"" + 
							"                }" + 
							"            ]" + 
							"        }" + 
							"    ]," + 
							"    \"QRSTW\": [" + 
							"        {" + 
							"            \"Q\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnQIANJIA471\"," + 
							"                    \"cityCode\": \"CitCnQIANJIA475\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_TAO_CN\"," + 
							"                    \"cityCode\": \"CITY_TAO_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_IQN_CN\"," + 
							"                    \"cityCode\": \"CITY_IQN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AirCnQIQIHAE234\"," + 
							"                    \"cityCode\": \"CitCnQIQIHAE381\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnQUANZHO690\"," + 
							"                    \"cityCode\": \"CitCnQUANZHO420\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnQUZHOU209\"," + 
							"                    \"cityCode\": \"CitCnQUZHOU213\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"R\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnRIZHAOS578\"," + 
							"                    \"cityCode\": \"CitCnRIZHAO182\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"S\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_SYX_CN\"," + 
							"                    \"cityCode\": \"CITY_SYX_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnSHANGHA013\"," + 
							"                    \"cityCode\": \"CitCnSHANGHA364\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnSANQING704\"," + 
							"                    \"cityCode\": \"CitCnSHANGRA380\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_SHE_CN\"," + 
							"                    \"cityCode\": \"CITY_SHE_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_SZX_CN\"," + 
							"                    \"cityCode\": \"CITY_SZX_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AirCnSHIJIAZ768\"," + 
							"                    \"cityCode\": \"CitCnSHIJIAZ814\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnCHAGANH356\"," + 
							"                    \"cityCode\": \"CitCnSONGYUA413\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnWUDANGS597\"," + 
							"                    \"cityCode\": \"CitCnSHIYAN181\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnSANMING022\"," + 
							"                    \"cityCode\": \"CitCnSANMING278\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"T\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_TYN_CN\"," + 
							"                    \"cityCode\": \"CITY_TYN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnTAIZHOU101\"," + 
							"                    \"cityCode\": \"CitCn047\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnSANNVHE280\"," + 
							"                    \"cityCode\": \"CitCnTANGSHA381\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_TSN_CN\"," + 
							"                    \"cityCode\": \"CITY_TSN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_TGO_CN\"," + 
							"                    \"cityCode\": \"CITY_TGO_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnTONGREN237\"," + 
							"                    \"cityCode\": \"CitCnTONGREN294\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"W\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_WEH_CN\"," + 
							"                    \"cityCode\": \"CITY_WEH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_WNZ_CN\"," + 
							"                    \"cityCode\": \"CITY_WNZ_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AirCnJINING164\"," + 
							"                    \"cityCode\": \"CitCnWULANCH595\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnWUHAI067\"," + 
							"                    \"cityCode\": \"CitCnWUHAI071\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_WUH_CN\"," + 
							"                    \"cityCode\": \"CITY_WUH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AIR_HLH_CN\"," + 
							"                    \"cityCode\": \"CITY_HLH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"???????????????\"," + 
							"                    \"airCode\": \"AirCnSHIYANW249\"," + 
							"                    \"cityCode\": \"CitCnWUDANGS601\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AIR_URC_CN\"," + 
							"                    \"cityCode\": \"CITY_URC_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AirCnWUTAISH505\"," + 
							"                    \"cityCode\": \"CitCnWUTAISH509\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnWUXISHU845\"," + 
							"                    \"cityCode\": \"CitCnWUXI990\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnGUANGXI245\"," + 
							"                    \"cityCode\": \"CitCnWUZHOU219\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnWUHUXUA943\"," + 
							"                    \"cityCode\": \"CitCnWUHU986\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnCHONGQI581\"," + 
							"                    \"cityCode\": \"CitCnWULONG197\"" + 
							"                }" + 
							"            ]" + 
							"        }" + 
							"    ]," + 
							"    \"XYZ\": [" + 
							"        {" + 
							"            \"X\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_XIY_CN\"," + 
							"                    \"cityCode\": \"CITY_SIA_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnGANNANX679\"," + 
							"                    \"cityCode\": \"CitCnXIAHE056\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_XMN_CN\"," + 
							"                    \"cityCode\": \"CITY_XMN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_XFN_CN\"," + 
							"                    \"cityCode\": \"CITY_XFN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnXICHANG120\"," + 
							"                    \"cityCode\": \"CitCnXICHANG267\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AIR_XIL_CN\"," + 
							"                    \"cityCode\": \"CITY_XIL_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnGUIYANG945\"," + 
							"                    \"cityCode\": \"CitCnXINGYI193\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"????????????\"," + 
							"                    \"airCode\": \"AirCnXINYANG131\"," + 
							"                    \"cityCode\": \"CitCnXINYANG295\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_XNN_CN\"," + 
							"                    \"cityCode\": \"CITY_XNN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_XUZ_CN\"," + 
							"                    \"cityCode\": \"CITY_XUZ_CN\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"Y\": [" + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_ENY_CN\"," + 
							"                    \"cityCode\": \"CITY_ENY_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_YNZ_CN\"," + 
							"                    \"cityCode\": \"CITY_YNZ_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnYANGZHO182\"," + 
							"                    \"cityCode\": \"CitCnYANGZHO414\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_YNJ_CN\"," + 
							"                    \"cityCode\": \"CITY_YNJ_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_YNT_CN\"," + 
							"                    \"cityCode\": \"CITY_YNT_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnYINGKOU832\"," + 
							"                    \"cityCode\": \"CitCnYINGKOU303\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnYUEYANG822\"," + 
							"                    \"cityCode\": \"CitCnYUEYANG299\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnLINGLIN377\"," + 
							"                    \"cityCode\": \"CitCnYONGZHO428\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_YBP_CN\"," + 
							"                    \"cityCode\": \"CITY_YBP_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_YIH_CN\"," + 
							"                    \"cityCode\": \"CITY_YIH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnYICHUNL721\"," + 
							"                    \"cityCode\": \"CitCnYICHUN185\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_INC_CN\"," + 
							"                    \"cityCode\": \"CITY_INC_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_YIW_CN\"," + 
							"                    \"cityCode\": \"CITY_YIW_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_UYN_CN\"," + 
							"                    \"cityCode\": \"CITY_UYN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"?????????????????????\"," + 
							"                    \"airCode\": \"AirCn277\"," + 
							"                    \"cityCode\": \"CitCnYUSHUZA786\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnYUNCHEN359\"," + 
							"                    \"cityCode\": \"CitCnYUNCHEN394\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"Z\": [" + 
							"                {" + 
							"                    \"Name\": \"?????????\"," + 
							"                    \"airCode\": \"AIR_DYG_CN\"," + 
							"                    \"cityCode\": \"CITY_DYG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_CGO_CN\"," + 
							"                    \"cityCode\": \"CITY_CGO_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnZHONGWE504\"," + 
							"                    \"cityCode\": \"CitCnZHONGWE404\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnZHOUSHA473\"," + 
							"                    \"cityCode\": \"CitCnZHOUSHA409\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AIR_ZUH_CN\"," + 
							"                    \"cityCode\": \"CITY_ZUH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnZUNYIXI889\"," + 
							"                    \"cityCode\": \"CitCnZUNYI104\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnZHANGYE047\"," + 
							"                    \"cityCode\": \"CitCnZHANGYE287\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"??????\"," + 
							"                    \"airCode\": \"AirCnZHANJIA479\"," + 
							"                    \"cityCode\": \"CitCnZHANJIA483\"" + 
							"                }" + 
							"            ]" + 
							"        }" + 
							"    ]" + 
							"}";
					originJson = JSONObject.parseObject(ss);
					JSONArray abcd = originJson.getJSONArray("ABCD");
					for (int i=0;i<abcd.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = abcd.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					//EFGHJ
					JSONArray EFGHJ = originJson.getJSONArray("EFGHJ");
					for (int i=0;i<EFGHJ.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = EFGHJ.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					//KLMNOP
					JSONArray KLMNOP = originJson.getJSONArray("KLMNOP");
					for (int i=0;i<KLMNOP.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = KLMNOP.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					//QRSTW
					JSONArray QRSTW = originJson.getJSONArray("QRSTW");
					for (int i=0;i<QRSTW.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = QRSTW.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					//XYZ
					JSONArray XYZ = originJson.getJSONArray("XYZ");
					for (int i=0;i<XYZ.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = XYZ.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					
				}
			}
		} else {
			for (int i=0;i<hotCityArr.size();i++) {
				if (hotCityArr.getJSONObject(i).getString("K").contains(cityName)) {
					cityCode = hotCityArr.getJSONObject(i).getString("V");
				}
			}
		}
		return cityCode;
	}
	
	public static String getCityCodeByAVH(String avh) {
		String cityCode = "";
		String cityName = "";
		switch(avh) {
		case "PKX": cityName = "??????"; break;
		case "SHA": cityName = "??????"; break;
		default : cityName="??????";
		}
		cityCode = getCityCodeByName(cityName);
		return cityCode;
	}
}
