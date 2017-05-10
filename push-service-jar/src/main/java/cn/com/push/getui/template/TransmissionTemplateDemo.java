package cn.com.push.getui.template;

import cn.com.push.config.GeTuiConfig;
import cn.com.push.getui.Service.impl.GeTuiServiceImpl;
import com.gexin.fastjson.JSONObject;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.template.TransmissionTemplate;

import java.util.Map;

public class TransmissionTemplateDemo {

	/**
	 * 透传封装方法,请不要删除
	 * @param appId
	 * @param appkey
	 * @param map
	 * @return
	 */
	public static TransmissionTemplate transmissionTemplateDemo(String appId, String appkey,Map map) {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		//安卓的透传内容
		String title = map.get("title").toString();
		String message = map.get("text").toString();
		JSONObject object = new JSONObject();
		object.put("title",title);
		object.put("message",message);
		template.setTransmissionContent(object.toJSONString());
		//等待应用启动
		template.setTransmissionType(2);
		APNPayload payload = new APNPayload();
		payload.setAutoBadge("+1");
		payload.setContentAvailable(1);
		payload.setSound("default");
		payload.setCategory("$由客户端定义");
		//简单模式APNPayload.SimpleMsg
		//payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));
		//字典模式使用下者
		payload.setAlertMsg(getDictionaryAlertMsg(title,message));
		//苹果系统的推送设置
		template.setAPNInfo(payload);
		return template;
	}

	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String title,String message){
		APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
		alertMsg.setBody(message);
		alertMsg.setActionLocKey("ActionLockey");
		alertMsg.setLocKey(message);
		alertMsg.addLocArg("loc-args");
		alertMsg.setLaunchImage("launch-image");
		// iOS8.2以上版本支持
		alertMsg.setTitle(title);
		alertMsg.setTitleLocKey(title);
		alertMsg.addTitleLocArg("TitleLocArg");
		return alertMsg;
	}

	public static void main(String[] args) {

		GeTuiConfig hhh = new GeTuiConfig("11111","222222","333333","2222222");
		GeTuiServiceImpl iii = new GeTuiServiceImpl();
		iii.AliasBinDingFunction("123","45612332222");
	}

}
