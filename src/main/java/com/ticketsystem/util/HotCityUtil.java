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
							"                    \"Name\": \"阿尔山\"," + 
							"                    \"airCode\": \"AirCnAERSHAN859\"," + 
							"                    \"cityCode\": \"CitCnAERSHAN267\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"安顺\"," + 
							"                    \"airCode\": \"AirCnANSHUNH376\"," + 
							"                    \"cityCode\": \"CitCnANSHUN182\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"安康\"," + 
							"                    \"airCode\": \"AirCnANKANGF896\"," + 
							"                    \"cityCode\": \"CitCnANKANG153\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"B\": [" + 
							"                {" + 
							"                    \"Name\": \"包头\"," + 
							"                    \"airCode\": \"AIR_BAV_CN\"," + 
							"                    \"cityCode\": \"CITY_BAV_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"白城\"," + 
							"                    \"airCode\": \"AirCnCHANGAN245\"," + 
							"                    \"cityCode\": \"CitCnBAICHEN346\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"巴彦淖尔\"," + 
							"                    \"airCode\": \"AirCnBAYANNA538\"," + 
							"                    \"cityCode\": \"CitCnBAYANNA585\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"北海\"," + 
							"                    \"airCode\": \"AIR_BHY_CN\"," + 
							"                    \"cityCode\": \"CITY_BHY_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"北京\"," + 
							"                    \"airCode\": \"AirCn39Beiji931\"," + 
							"                    \"cityCode\": \"CITY_BJS_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"毕节\"," + 
							"                    \"airCode\": \"AirCnFEIXION382\"," + 
							"                    \"cityCode\": \"CitCnBIJIE044\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"巴中\"," + 
							"                    \"airCode\": \"AirCnBAZHONG912\"," + 
							"                    \"cityCode\": \"CitCnBAZHONG274\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"C\": [" + 
							"                {" + 
							"                    \"Name\": \"长春\"," + 
							"                    \"airCode\": \"AirCnLONGJIA531\"," + 
							"                    \"cityCode\": \"CITY_CGQ_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"长沙\"," + 
							"                    \"airCode\": \"AIR_CSX_CN\"," + 
							"                    \"cityCode\": \"CITY_CSX_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"长治\"," + 
							"                    \"airCode\": \"AirCnCHANGZH124\"," + 
							"                    \"cityCode\": \"CitCnCHANGZH373\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"常州\"," + 
							"                    \"airCode\": \"AIR_CZX_CN\"," + 
							"                    \"cityCode\": \"CITY_CZX_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"成都\"," + 
							"                    \"airCode\": \"AIR_CTU_CN\"," + 
							"                    \"cityCode\": \"CITY_CTU_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"赤峰\"," + 
							"                    \"airCode\": \"AIR_CIF_CN\"," + 
							"                    \"cityCode\": \"CITY_CIF_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"池州\"," + 
							"                    \"airCode\": \"AirCnCHIZHOU359\"," + 
							"                    \"cityCode\": \"CitCHIZHOU082\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"重庆江北\"," + 
							"                    \"airCode\": \"AIR_CKG_CN\"," + 
							"                    \"cityCode\": \"CITY_CKG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"重庆万州\"," + 
							"                    \"airCode\": \"AirCnCHONGQI263\"," + 
							"                    \"cityCode\": \"CITY_CKG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"长白山\"," + 
							"                    \"airCode\": \"CitCnCHANGBA768\"," + 
							"                    \"cityCode\": \"CitCnCHANGBA768\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"承德\"," + 
							"                    \"airCode\": \"AirCnCHANBAI661\"," + 
							"                    \"cityCode\": \"CitCnCHENGDE247\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"D\": [" + 
							"                {" + 
							"                    \"Name\": \"大理\"," + 
							"                    \"airCode\": \"AIR_DLU_CN\"," + 
							"                    \"cityCode\": \"CITY_DLU_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"大连\"," + 
							"                    \"airCode\": \"AIR_DLC_CN\"," + 
							"                    \"cityCode\": \"CITY_DLC_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"稻城\"," + 
							"                    \"airCode\": \"AirCnDAOCHEN986\"," + 
							"                    \"cityCode\": \"CitCnDAOCHEN354\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"大同\"," + 
							"                    \"airCode\": \"AIR_DAT_CN\"," + 
							"                    \"cityCode\": \"CITY_DAT_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"达州\"," + 
							"                    \"airCode\": \"AirCnDAZHOUH786\"," + 
							"                    \"cityCode\": \"CitCnDAZHOU180\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"迪庆\"," + 
							"                    \"airCode\": \"AIR_DIG_CN\"," + 
							"                    \"cityCode\": \"CITY_DIG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"东营\"," + 
							"                    \"airCode\": \"AIR_DOY_CN\"," + 
							"                    \"cityCode\": \"CITY_DOY_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"敦煌\"," + 
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
							"                    \"Name\": \"鄂尔多斯\"," + 
							"                    \"airCode\": \"AirCnEERDUOS606\"," + 
							"                    \"cityCode\": \"CitCnEERDUOS393\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"F\": [" + 
							"                {" + 
							"                    \"Name\": \"阜阳\"," + 
							"                    \"airCode\": \"AIR_FUG_CN\"," + 
							"                    \"cityCode\": \"CITY_FUG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"佛山\"," + 
							"                    \"airCode\": \"AirCnFOSHANS685\"," + 
							"                    \"cityCode\": \"CitCnFOSHAN168\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"福州\"," + 
							"                    \"airCode\": \"AIR_FOC_CN\"," + 
							"                    \"cityCode\": \"CITY_FOC_CN\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"G\": [" + 
							"                {" + 
							"                    \"Name\": \"赣州\"," + 
							"                    \"airCode\": \"AIR_KOW_CN\"," + 
							"                    \"cityCode\": \"CITY_KOW_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"广州\"," + 
							"                    \"airCode\": \"AIR_CAN_CN\"," + 
							"                    \"cityCode\": \"CITY_CAN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"桂林\"," + 
							"                    \"airCode\": \"AIR_KWL_CN\"," + 
							"                    \"cityCode\": \"CITY_KWL_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"贵阳\"," + 
							"                    \"airCode\": \"AIR_KWE_CN\"," + 
							"                    \"cityCode\": \"CITY_KWE_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"固原\"," + 
							"                    \"airCode\": \"AirCnLIUPANS600\"," + 
							"                    \"cityCode\": \"CitCnGUYUAN194\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"H\": [" + 
							"                {" + 
							"                    \"Name\": \"哈尔滨\"," + 
							"                    \"airCode\": \"AIR_HRB_CN\"," + 
							"                    \"cityCode\": \"CITY_HRB_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"海口\"," + 
							"                    \"airCode\": \"AIR_HAK_CN\"," + 
							"                    \"cityCode\": \"CITY_HAK_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"海拉尔\"," + 
							"                    \"airCode\": \"AIR_HLD_CN\"," + 
							"                    \"cityCode\": \"CITY_HLD_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"邯郸\"," + 
							"                    \"airCode\": \"AirCnHEBEIHA652\"," + 
							"                    \"cityCode\": \"CitCnHANDAN147\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"杭州\"," + 
							"                    \"airCode\": \"AIR_HGH_CN\"," + 
							"                    \"cityCode\": \"CITY_HGH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"汉中\"," + 
							"                    \"airCode\": \"AIR_HZG_CN\"," + 
							"                    \"cityCode\": \"CITY_HZG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"合肥\"," + 
							"                    \"airCode\": \"AIR_HFE_CN\"," + 
							"                    \"cityCode\": \"CITY_HFE_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"衡阳\"," + 
							"                    \"airCode\": \"AirCnHENGYAN319\"," + 
							"                    \"cityCode\": \"CitCnHENGYAN378\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"红原\"," + 
							"                    \"airCode\": \"AirCnABAHONG690\"," + 
							"                    \"cityCode\": \"CitCnHONGYUA402\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"怀化\"," + 
							"                    \"airCode\": \"AirCnHUAIHUA118\"," + 
							"                    \"cityCode\": \"CitCnHUAIHUA270\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"淮安\"," + 
							"                    \"airCode\": \"AirCnHUAIANL016\"," + 
							"                    \"cityCode\": \"CitCnHUAIAN159\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"呼和浩特\"," + 
							"                    \"airCode\": \"AIR_HET_CN\"," + 
							"                    \"cityCode\": \"CITY_HET_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"惠州\"," + 
							"                    \"airCode\": \"AirCnHUIZHOU058\"," + 
							"                    \"cityCode\": \"CitHUIZHOU100\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"黄山\"," + 
							"                    \"airCode\": \"AirCnHUANGSH050\"," + 
							"                    \"cityCode\": \"CitCnHUANGSH486\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"J\": [" + 
							"                {" + 
							"                    \"Name\": \"嘉峪关\"," + 
							"                    \"airCode\": \"AIR_JGN_CN\"," + 
							"                    \"cityCode\": \"CITY_JGN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"揭阳\"," + 
							"                    \"airCode\": \"AIR_SWA_CN\"," + 
							"                    \"cityCode\": \"CITY_SWA_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"济南\"," + 
							"                    \"airCode\": \"AIR_TNA_CN\"," + 
							"                    \"cityCode\": \"CITY_TNA_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"金昌\"," + 
							"                    \"airCode\": \"AirCnJINCHAN359\"," + 
							"                    \"cityCode\": \"CitCnJINCHAN363\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"景洪\"," + 
							"                    \"airCode\": \"AIR_JHG_CN\"," + 
							"                    \"cityCode\": \"CITY_JHG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"济宁\"," + 
							"                    \"airCode\": \"AIR_JNG_CN\"," + 
							"                    \"cityCode\": \"CITY_JNG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"九寨沟\"," + 
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
							"                    \"Name\": \"康定\"," + 
							"                    \"airCode\": \"AirCnKANGDIN360\"," + 
							"                    \"cityCode\": \"CitCnKANGDIN364\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"喀什\"," + 
							"                    \"airCode\": \"AIR_KHG_CN\"," + 
							"                    \"cityCode\": \"CITY_KHG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"昆明\"," + 
							"                    \"airCode\": \"AIR_KMG_CN\"," + 
							"                    \"cityCode\": \"CITY_KMG_CN\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"L\": [" + 
							"                {" + 
							"                    \"Name\": \"兰州\"," + 
							"                    \"airCode\": \"AIR_LHW_CN\"," + 
							"                    \"cityCode\": \"CITY_LHW_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"拉萨\"," + 
							"                    \"airCode\": \"AIR_LXA_CN\"," + 
							"                    \"cityCode\": \"CITY_LXA_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"连城\"," + 
							"                    \"airCode\": \"AirCnLIANCHE462\"," + 
							"                    \"cityCode\": \"AirCnLIANCHE462\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"连云港\"," + 
							"                    \"airCode\": \"AIR_LYG_CN\"," + 
							"                    \"cityCode\": \"CITY_LYG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"丽江\"," + 
							"                    \"airCode\": \"AIR_LJG_CN\"," + 
							"                    \"cityCode\": \"CITY_LJG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"临沂\"," + 
							"                    \"airCode\": \"AIR_LYI_CN\"," + 
							"                    \"cityCode\": \"CITY_LYI_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"吕梁大武\"," + 
							"                    \"airCode\": \"AirCnLVLIANG707\"," + 
							"                    \"cityCode\": \"CitCnLVLIANG278\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"林芝\"," + 
							"                    \"airCode\": \"AirCnLINZHI179\"," + 
							"                    \"cityCode\": \"CitCn485\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"吕梁\"," + 
							"                    \"airCode\": \"AirCnLIULIAN378\"," + 
							"                    \"cityCode\": \"CitCnLIULIAN382\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"柳州\"," + 
							"                    \"airCode\": \"AirCnLIUZHOU029\"," + 
							"                    \"cityCode\": \"CitCnLIUZHOU313\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"龙岩\"," + 
							"                    \"airCode\": \"AirCnLIANCHE462\"," + 
							"                    \"cityCode\": \"CitCnLIANCHE466\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"泸州\"," + 
							"                    \"airCode\": \"AIR_LZO_CN\"," + 
							"                    \"cityCode\": \"CITY_LZO_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"陇南\"," + 
							"                    \"airCode\": \"AirCnCHENGXI474\"," + 
							"                    \"cityCode\": \"CitCnLONGNAN278\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"M\": [" + 
							"                {" + 
							"                    \"Name\": \"芒市\"," + 
							"                    \"airCode\": \"AIR_LUM_CN\"," + 
							"                    \"cityCode\": \"CITY_LUM_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"满洲里\"," + 
							"                    \"airCode\": \"AirCnWULUNHA495\"," + 
							"                    \"cityCode\": \"CitCnMANZHOU512\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"绵阳\"," + 
							"                    \"airCode\": \"AirCnMIANYAN113\"," + 
							"                    \"cityCode\": \"CitCnMIANYAN381\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"牡丹江\"," + 
							"                    \"airCode\": \"AirCnMUDANJI303\"," + 
							"                    \"cityCode\": \"CitCnMUDANJI583\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"N\": [" + 
							"                {" + 
							"                    \"Name\": \"南昌\"," + 
							"                    \"airCode\": \"AIR_KHN_CN\"," + 
							"                    \"cityCode\": \"CITY_KHN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"南京\"," + 
							"                    \"airCode\": \"AIR_NKG_CN\"," + 
							"                    \"cityCode\": \"CITY_NKG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"南宁\"," + 
							"                    \"airCode\": \"AIR_NNG_CN\"," + 
							"                    \"cityCode\": \"CITY_NNG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"南通\"," + 
							"                    \"airCode\": \"AIR_NTG_CN\"," + 
							"                    \"cityCode\": \"CITY_NTG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"南阳\"," + 
							"                    \"airCode\": \"AIR_NNY_CN\"," + 
							"                    \"cityCode\": \"CITY_NNY_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"宁波\"," + 
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
							"                    \"Name\": \"攀枝花\"," + 
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
							"                    \"Name\": \"黔江\"," + 
							"                    \"airCode\": \"AirCnQIANJIA471\"," + 
							"                    \"cityCode\": \"CitCnQIANJIA475\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"青岛\"," + 
							"                    \"airCode\": \"AIR_TAO_CN\"," + 
							"                    \"cityCode\": \"CITY_TAO_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"庆阳\"," + 
							"                    \"airCode\": \"AIR_IQN_CN\"," + 
							"                    \"cityCode\": \"CITY_IQN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"齐齐哈尔\"," + 
							"                    \"airCode\": \"AirCnQIQIHAE234\"," + 
							"                    \"cityCode\": \"CitCnQIQIHAE381\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"泉州\"," + 
							"                    \"airCode\": \"AirCnQUANZHO690\"," + 
							"                    \"cityCode\": \"CitCnQUANZHO420\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"衢州\"," + 
							"                    \"airCode\": \"AirCnQUZHOU209\"," + 
							"                    \"cityCode\": \"CitCnQUZHOU213\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"R\": [" + 
							"                {" + 
							"                    \"Name\": \"日照\"," + 
							"                    \"airCode\": \"AirCnRIZHAOS578\"," + 
							"                    \"cityCode\": \"CitCnRIZHAO182\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"S\": [" + 
							"                {" + 
							"                    \"Name\": \"三亚\"," + 
							"                    \"airCode\": \"AIR_SYX_CN\"," + 
							"                    \"cityCode\": \"CITY_SYX_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"上海\"," + 
							"                    \"airCode\": \"AirCnSHANGHA013\"," + 
							"                    \"cityCode\": \"CitCnSHANGHA364\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"上饶\"," + 
							"                    \"airCode\": \"AirCnSANQING704\"," + 
							"                    \"cityCode\": \"CitCnSHANGRA380\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"沈阳\"," + 
							"                    \"airCode\": \"AIR_SHE_CN\"," + 
							"                    \"cityCode\": \"CITY_SHE_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"深圳\"," + 
							"                    \"airCode\": \"AIR_SZX_CN\"," + 
							"                    \"cityCode\": \"CITY_SZX_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"石家庄\"," + 
							"                    \"airCode\": \"AirCnSHIJIAZ768\"," + 
							"                    \"cityCode\": \"CitCnSHIJIAZ814\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"松原\"," + 
							"                    \"airCode\": \"AirCnCHAGANH356\"," + 
							"                    \"cityCode\": \"CitCnSONGYUA413\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"十堰\"," + 
							"                    \"airCode\": \"AirCnWUDANGS597\"," + 
							"                    \"cityCode\": \"CitCnSHIYAN181\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"三明\"," + 
							"                    \"airCode\": \"AirCnSANMING022\"," + 
							"                    \"cityCode\": \"CitCnSANMING278\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"T\": [" + 
							"                {" + 
							"                    \"Name\": \"太原\"," + 
							"                    \"airCode\": \"AIR_TYN_CN\"," + 
							"                    \"cityCode\": \"CITY_TYN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"台州\"," + 
							"                    \"airCode\": \"AirCnTAIZHOU101\"," + 
							"                    \"cityCode\": \"CitCn047\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"唐山\"," + 
							"                    \"airCode\": \"AirCnSANNVHE280\"," + 
							"                    \"cityCode\": \"CitCnTANGSHA381\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"天津\"," + 
							"                    \"airCode\": \"AIR_TSN_CN\"," + 
							"                    \"cityCode\": \"CITY_TSN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"通辽\"," + 
							"                    \"airCode\": \"AIR_TGO_CN\"," + 
							"                    \"cityCode\": \"CITY_TGO_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"铜仁\"," + 
							"                    \"airCode\": \"AirCnTONGREN237\"," + 
							"                    \"cityCode\": \"CitCnTONGREN294\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"W\": [" + 
							"                {" + 
							"                    \"Name\": \"威海\"," + 
							"                    \"airCode\": \"AIR_WEH_CN\"," + 
							"                    \"cityCode\": \"CITY_WEH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"温州\"," + 
							"                    \"airCode\": \"AIR_WNZ_CN\"," + 
							"                    \"cityCode\": \"CITY_WNZ_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"乌兰察布\"," + 
							"                    \"airCode\": \"AirCnJINING164\"," + 
							"                    \"cityCode\": \"CitCnWULANCH595\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"乌海\"," + 
							"                    \"airCode\": \"AirCnWUHAI067\"," + 
							"                    \"cityCode\": \"CitCnWUHAI071\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"武汉\"," + 
							"                    \"airCode\": \"AIR_WUH_CN\"," + 
							"                    \"cityCode\": \"CITY_WUH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"乌兰浩特\"," + 
							"                    \"airCode\": \"AIR_HLH_CN\"," + 
							"                    \"cityCode\": \"CITY_HLH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"十堰武当山\"," + 
							"                    \"airCode\": \"AirCnSHIYANW249\"," + 
							"                    \"cityCode\": \"CitCnWUDANGS601\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"乌鲁木齐\"," + 
							"                    \"airCode\": \"AIR_URC_CN\"," + 
							"                    \"cityCode\": \"CITY_URC_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"五台山\"," + 
							"                    \"airCode\": \"AirCnWUTAISH505\"," + 
							"                    \"cityCode\": \"CitCnWUTAISH509\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"无锡\"," + 
							"                    \"airCode\": \"AirCnWUXISHU845\"," + 
							"                    \"cityCode\": \"CitCnWUXI990\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"梧州\"," + 
							"                    \"airCode\": \"AirCnGUANGXI245\"," + 
							"                    \"cityCode\": \"CitCnWUZHOU219\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"芜湖\"," + 
							"                    \"airCode\": \"AirCnWUHUXUA943\"," + 
							"                    \"cityCode\": \"CitCnWUHU986\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"武隆\"," + 
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
							"                    \"Name\": \"西安\"," + 
							"                    \"airCode\": \"AIR_XIY_CN\"," + 
							"                    \"cityCode\": \"CITY_SIA_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"夏河\"," + 
							"                    \"airCode\": \"AirCnGANNANX679\"," + 
							"                    \"cityCode\": \"CitCnXIAHE056\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"厦门\"," + 
							"                    \"airCode\": \"AIR_XMN_CN\"," + 
							"                    \"cityCode\": \"CITY_XMN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"襄阳\"," + 
							"                    \"airCode\": \"AIR_XFN_CN\"," + 
							"                    \"cityCode\": \"CITY_XFN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"西昌\"," + 
							"                    \"airCode\": \"AirCnXICHANG120\"," + 
							"                    \"cityCode\": \"CitCnXICHANG267\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"锡林浩特\"," + 
							"                    \"airCode\": \"AIR_XIL_CN\"," + 
							"                    \"cityCode\": \"CITY_XIL_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"兴义\"," + 
							"                    \"airCode\": \"AirCnGUIYANG945\"," + 
							"                    \"cityCode\": \"CitCnXINGYI193\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"信阳明港\"," + 
							"                    \"airCode\": \"AirCnXINYANG131\"," + 
							"                    \"cityCode\": \"CitCnXINYANG295\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"西宁\"," + 
							"                    \"airCode\": \"AIR_XNN_CN\"," + 
							"                    \"cityCode\": \"CITY_XNN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"徐州\"," + 
							"                    \"airCode\": \"AIR_XUZ_CN\"," + 
							"                    \"cityCode\": \"CITY_XUZ_CN\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"Y\": [" + 
							"                {" + 
							"                    \"Name\": \"延安\"," + 
							"                    \"airCode\": \"AIR_ENY_CN\"," + 
							"                    \"cityCode\": \"CITY_ENY_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"盐城\"," + 
							"                    \"airCode\": \"AIR_YNZ_CN\"," + 
							"                    \"cityCode\": \"CITY_YNZ_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"扬州\"," + 
							"                    \"airCode\": \"AirCnYANGZHO182\"," + 
							"                    \"cityCode\": \"CitCnYANGZHO414\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"延吉\"," + 
							"                    \"airCode\": \"AIR_YNJ_CN\"," + 
							"                    \"cityCode\": \"CITY_YNJ_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"烟台\"," + 
							"                    \"airCode\": \"AIR_YNT_CN\"," + 
							"                    \"cityCode\": \"CITY_YNT_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"营口\"," + 
							"                    \"airCode\": \"AirCnYINGKOU832\"," + 
							"                    \"cityCode\": \"CitCnYINGKOU303\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"岳阳\"," + 
							"                    \"airCode\": \"AirCnYUEYANG822\"," + 
							"                    \"cityCode\": \"CitCnYUEYANG299\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"永州\"," + 
							"                    \"airCode\": \"AirCnLINGLIN377\"," + 
							"                    \"cityCode\": \"CitCnYONGZHO428\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"宜宾\"," + 
							"                    \"airCode\": \"AIR_YBP_CN\"," + 
							"                    \"cityCode\": \"CITY_YBP_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"宜昌\"," + 
							"                    \"airCode\": \"AIR_YIH_CN\"," + 
							"                    \"cityCode\": \"CITY_YIH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"伊春\"," + 
							"                    \"airCode\": \"AirCnYICHUNL721\"," + 
							"                    \"cityCode\": \"CitCnYICHUN185\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"银川\"," + 
							"                    \"airCode\": \"AIR_INC_CN\"," + 
							"                    \"cityCode\": \"CITY_INC_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"义乌\"," + 
							"                    \"airCode\": \"AIR_YIW_CN\"," + 
							"                    \"cityCode\": \"CITY_YIW_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"榆林\"," + 
							"                    \"airCode\": \"AIR_UYN_CN\"," + 
							"                    \"cityCode\": \"CITY_UYN_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"玉树藏族自治州\"," + 
							"                    \"airCode\": \"AirCn277\"," + 
							"                    \"cityCode\": \"CitCnYUSHUZA786\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"运城\"," + 
							"                    \"airCode\": \"AirCnYUNCHEN359\"," + 
							"                    \"cityCode\": \"CitCnYUNCHEN394\"" + 
							"                }" + 
							"            ]" + 
							"        }," + 
							"        {" + 
							"            \"Z\": [" + 
							"                {" + 
							"                    \"Name\": \"张家界\"," + 
							"                    \"airCode\": \"AIR_DYG_CN\"," + 
							"                    \"cityCode\": \"CITY_DYG_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"郑州\"," + 
							"                    \"airCode\": \"AIR_CGO_CN\"," + 
							"                    \"cityCode\": \"CITY_CGO_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"中卫\"," + 
							"                    \"airCode\": \"AirCnZHONGWE504\"," + 
							"                    \"cityCode\": \"CitCnZHONGWE404\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"舟山\"," + 
							"                    \"airCode\": \"AirCnZHOUSHA473\"," + 
							"                    \"cityCode\": \"CitCnZHOUSHA409\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"珠海\"," + 
							"                    \"airCode\": \"AIR_ZUH_CN\"," + 
							"                    \"cityCode\": \"CITY_ZUH_CN\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"遵义\"," + 
							"                    \"airCode\": \"AirCnZUNYIXI889\"," + 
							"                    \"cityCode\": \"CitCnZUNYI104\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"张掖\"," + 
							"                    \"airCode\": \"AirCnZHANGYE047\"," + 
							"                    \"cityCode\": \"CitCnZHANGYE287\"" + 
							"                }," + 
							"                {" + 
							"                    \"Name\": \"湛江\"," + 
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
		case "PKX": cityName = "北京"; break;
		case "SHA": cityName = "上海"; break;
		default : cityName="北京";
		}
		cityCode = getCityCodeByName(cityName);
		return cityCode;
	}
}
