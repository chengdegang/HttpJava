package address;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

//import org.junit.Test;
import org.testng.annotations.Test;

public class test_junit_address {
	
	major junit = new major();
	/*
	 * 以下为地址回归用例（主要为标准化预处理）
	 */
	@Test
	public void Health_check() {
		String result = junit.matchGET("10.100.1.220:17002","/v1/actuator/health","");
		assertEquals("{	\"details\":{},	\"status\":{		\"code\":\"UP\",		\"description\":\"\"	}}",result);
	}
	@Test
	public void Traditional_character() {
		String result = junit.standardPOST("/v1/standard2/address","浙江省杭州市西湖区西斗門路3號");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江省杭州市西湖区西斗门路3号\",\"autoDeep\":5,\"section\":[{\"len\":6,\"level\":1,\"name\":\"浙江省\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"西湖区\"},{\"len\":8,\"level\":4,\"name\":\"西斗门路\"},{\"len\":3,\"level\":5,\"name\":\"3号\"}],\"dictID\":330106}}]}",result);
	}
	@Test
	public void Angle_character() {
		String result = junit.standardPOST("/v1/standard2/address","杭州市下城区环城北路47号武林广场云河北侧（近体育场路）");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市下城区环城北路47号武林广场云河北侧(近体育场路)\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"下城区\"},{\"len\":8,\"level\":4,\"name\":\"环城北路\"},{\"len\":4,\"level\":5,\"name\":\"47号\"},{\"len\":8,\"level\":9,\"name\":\"武林广场\"},{\"len\":8,\"level\":9,\"name\":\"云河北侧\"},{\"len\":12,\"level\":9,\"name\":\"(近体育场路)\"}],\"dictID\":330103}}]}",result);
	}
	@Test
	public void Angle_character2() {
		String result = junit.standardPOST("/v1/standard2/address","杭州市下城区环城北路４７号武林广场云河北侧（近体育场路）");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市下城区环城北路47号武林广场云河北侧(近体育场路)\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"下城区\"},{\"len\":8,\"level\":4,\"name\":\"环城北路\"},{\"len\":4,\"level\":5,\"name\":\"47号\"},{\"len\":8,\"level\":9,\"name\":\"武林广场\"},{\"len\":8,\"level\":9,\"name\":\"云河北侧\"},{\"len\":12,\"level\":9,\"name\":\"(近体育场路)\"}],\"dictID\":330103}}]}",result);
	}
	@Test
	public void Field_compensation() {
		String result = junit.standardPOST("/v1/standard2/address","杭州市余杭区文一西路999号");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市余杭区文一西路999号\",\"autoDeep\":5,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"余杭区\"},{\"len\":8,\"level\":4,\"name\":\"文一西路\"},{\"len\":5,\"level\":5,\"name\":\"999号\"}],\"dictID\":330110}}]}",result);
	}
	@Test
	public void Province_duplicate() {
		String result = junit.standardPOST("/v1/standard2/address","上海市上海市上海市浦东新区靖海南路567弄38号101") ;
	    assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"上海浦东新区靖海南路567弄38号101\",\"autoDeep\":6,\"section\":[{\"len\":4,\"level\":1,\"name\":\"上海\"},{\"len\":8,\"level\":3,\"name\":\"浦东新区\"},{\"len\":8,\"level\":4,\"name\":\"靖海南路\"},{\"len\":5,\"level\":4,\"name\":\"567弄\"},{\"len\":4,\"level\":5,\"name\":\"38号\"},{\"len\":3,\"level\":6,\"name\":\"101\"}],\"dictID\":310115}}]}",result);
	}
	@Test(timeOut=3000)
	public void Parameter_null() {
		String result = junit.standardPOST("/v1/standard2/address","") ;
	    assertEquals("{\"code\":1010,\"message\":\"parameters verify error\",\"status\":400}",result);
	}
	@Test
	public void Special_character_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区#雍容华庭2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区#雍容华庭2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":1,\"level\":4,\"name\":\"#\"},{\"len\":8,\"level\":9,\"name\":\"雍容华庭\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_character2_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区-雍容华庭2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区雍容华庭2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":8,\"level\":9,\"name\":\"雍容华庭\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_character3_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区_雍容华庭2-1-2701");
		assertEquals("{\\\"type\\\":\\\"1\\\",\\\"dataList\\\":[{\\\"metadata\\\":{\\\"type\\\":\\\"1\\\"},\\\"sections\\\":{\\\"standAddr\\\":\\\"浙江杭州市拱墅区雍容华庭2-1-2701\\\",\\\"autoDeep\\\":9,\\\"section\\\":[{\\\"len\\\":4,\\\"level\\\":1,\\\"name\\\":\\\"浙江\\\"},{\\\"len\\\":6,\\\"level\\\":2,\\\"name\\\":\\\"杭州市\\\"},{\\\"len\\\":6,\\\"level\\\":3,\\\"name\\\":\\\"拱墅区\\\"},{\\\"len\\\":8,\\\"level\\\":9,\\\"name\\\":\\\"雍容华庭\\\"},{\\\"len\\\":8,\\\"level\\\":6,\\\"name\\\":\\\"2-1-2701\\\"}],\\\"dictID\\\":330105}}]}",result);
	}
	@Test
	public void Special_character4_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区～雍容华庭2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区～雍容华庭2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":2,\"level\":4,\"name\":\"～\"},{\"len\":8,\"level\":9,\"name\":\"雍容华庭\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_character5_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区、雍容华庭2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区、雍容华庭2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":2,\"level\":4,\"name\":\"、\"},{\"len\":8,\"level\":9,\"name\":\"雍容华庭\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_brackets_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区<雍容华庭>2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区(雍容华庭)2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":10,\"level\":9,\"name\":\"(雍容华庭)\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_brackets2_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区《雍容华庭》2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区(雍容华庭)2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":10,\"level\":9,\"name\":\"(雍容华庭)\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_brackets3_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区【雍容华庭】2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区(雍容华庭)2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":10,\"level\":9,\"name\":\"(雍容华庭)\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_brackets4_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区[雍容华庭]2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区(雍容华庭)2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":10,\"level\":9,\"name\":\"(雍容华庭)\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_brackets5_retain() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区{雍容华庭}2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区(雍容华庭)2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":10,\"level\":9,\"name\":\"(雍容华庭)\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_character_delete() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区 雍容华庭2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区雍容华庭2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":8,\"level\":9,\"name\":\"雍容华庭\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_character2_delete() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区@雍容华庭2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区雍容华庭2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":8,\"level\":9,\"name\":\"雍容华庭\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	@Test
	public void Special_character3_delete() {
		String result = junit.standardPOST("/v1/standard2/address", "浙江省杭州市拱墅区|雍容华庭2-1-2701");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"1\"},\"sections\":{\"standAddr\":\"浙江杭州市拱墅区雍容华庭2-1-2701\",\"autoDeep\":9,\"section\":[{\"len\":4,\"level\":1,\"name\":\"浙江\"},{\"len\":6,\"level\":2,\"name\":\"杭州市\"},{\"len\":6,\"level\":3,\"name\":\"拱墅区\"},{\"len\":8,\"level\":9,\"name\":\"雍容华庭\"},{\"len\":8,\"level\":6,\"name\":\"2-1-2701\"}],\"dictID\":330105}}]}",result);
	}
	
}
