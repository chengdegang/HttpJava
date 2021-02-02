package address;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONPath;

public class json {
	
	public static void main(String[] args) {
		//云服务4.6标准化响应内容
//		String response = "{\"type\":\"2\",\"dataList\":[{\"metadata\":{\"type\":\"2\"},\"srcAddr\":\"江苏省南京市秦淮区瑞金路7号\",\"sections\":{\"standAddr\":\"江苏省南京市秦淮区瑞金路7号\",\"autoDeep\":6,\"section\":[{\"len\":6,\"level\":1,\"name\":\"江苏省\"},{\"len\":6,\"level\":2,\"name\":\"南京市\"},{\"len\":6,\"level\":3,\"name\":\"秦淮区\"},{\"len\":6,\"level\":5,\"name\":\"瑞金路\"},{\"len\":3,\"level\":6,\"name\":\"7号\"}],\"dictID\":320104}}]}";
		//离线版4.7标准化响应内容
		String response = "{\"result\":[{\"srcAddr\":\"浙江省杭州市下城区 石桥街道   石桥路309号新华经济园13栋5楼\",\"standardAddr\":\"浙江省杭州市下城区石桥街道石桥路309号新华经济园13栋5楼\",\"dictID\":\"330103\",\"autoDeep\":7,\"sections\":[{\"level\":\"1\",\"name\":\"浙江省\",\"len\":\"6\"},{\"level\":\"2\",\"name\":\"杭州市\",\"len\":\"6\"},{\"level\":\"3\",\"name\":\"下城区\",\"len\":\"6\"},{\"level\":\"4\",\"name\":\"石桥街道\",\"len\":\"8\"},{\"level\":\"5\",\"name\":\"石桥路\",\"len\":\"6\"},{\"level\":\"6\",\"name\":\"309号\",\"len\":\"5\"},{\"level\":\"31\",\"name\":\"新华经济园\",\"len\":\"10\"},{\"level\":\"7\",\"name\":\"13栋\",\"len\":\"4\"},{\"level\":\"7\",\"name\":\"5楼\",\"len\":\"3\"}]}]}";
		System.out.println(json.change4_7(response));
	}
	
	public static String change(String data) {
		List<Map<String,Object>> sections = new ArrayList<>();
		sections = (List<Map<String,Object>>) JSONPath.read(data, "$.dataList[0].sections.section");
		StringBuffer am = new StringBuffer();
		for (Map<String,Object> i : sections) {
			am.append(i.get("name") + ",R" + i.get("level") + ",");
		}
		return am.toString();
	}
	
	public static String change4_7(String data2){
		List<Map<String,Object>> sections = new ArrayList<>();
		sections = (List<Map<String,Object>>) JSONPath.read(data2, "$.result[0].sections");
		StringBuffer am = new StringBuffer();
		for (Map<String,Object> i : sections) {
			am.append(i.get("name") + ",R" + i.get("level") + ",");
		}
		return am.toString();
	}

}
