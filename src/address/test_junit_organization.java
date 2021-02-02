package address;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class test_junit_organization {
	major junit = new major();
	
	/*
	 * 以下为单位回归用例（主要为标准化预处理）
	 */
	@Test
	public void Parameter_null2() {
		String result = junit.standardPOST("/v1/standard2/organization","") ;
	    assertEquals("{\"code\":1010,\"message\":\"parameters verify error\",\"status\":400}",result);
	}
	@Test
	public void Org_standard_normal()
	{   
		String result = junit.standardPOST("/v1/standard2/organization","王府井集团股份有限公司");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"A1\"},\"sections\":{\"standOrg\":\"王府井集团股份有限公司\",\"section\":[{\"wordAttr\":\"U\",\"name\":\"王府井\"},{\"wordAttr\":\"II\",\"name\":\"集团\"},{\"wordAttr\":\"O\",\"name\":\"股份有限公司\"}]}}]}",result);
    }
	@Test
	public void Org_Special_brackets_retain()
	{   
		String result = junit.standardPOST("/v1/standard2/organization","中海石油【中国】有限公司");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"A1\"},\"sections\":{\"standOrg\":\"中海石油(中国)有限公司\",\"section\":[{\"wordAttr\":\"U\",\"name\":\"中海\"},{\"wordAttr\":\"I\",\"name\":\"石油\"},{\"wordAttr\":\"II\",\"name\":\"(中国)\"},{\"wordAttr\":\"O\",\"name\":\"有限公司\"}]}}]}",result);
    }
	@Test
	public void Org_Special_brackets2_retain()
	{   
		String result = junit.standardPOST("/v1/standard2/organization","中海石油{中国}有限公司");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"A1\"},\"sections\":{\"standOrg\":\"中海石油(中国)有限公司\",\"section\":[{\"wordAttr\":\"U\",\"name\":\"中海\"},{\"wordAttr\":\"I\",\"name\":\"石油\"},{\"wordAttr\":\"II\",\"name\":\"(中国)\"},{\"wordAttr\":\"O\",\"name\":\"有限公司\"}]}}]}",result);
    }
	@Test
	public void Org_Special_brackets4_retain()
	{   
		String result = junit.standardPOST("/v1/standard2/organization","中海石油《中国》有限公司");
		assertTrue(result.contains("中海石油(中国)有限公司"));
    }
	@Test
	public void Org_Special_brackets5_retain()
	{   
		String result = junit.standardPOST("/v1/standard2/organization","中海石油<中国>有限公司");
		assertTrue(result.contains("中海石油(中国)有限公司"));
    }
	@Test
	public void Special_character_delete() {
		String result = junit.standardPOST("/v1/standard2/organization","中海石油中国=有限公司");
		assertTrue(result.contains("中海石油中国有限公司"));
	}
	@Test
	public void Special_character2_delete() {
		String result = junit.standardPOST("/v1/standard2/organization","中海石油中国有限公司`");
		assertTrue(result.contains("中海石油中国有限公司"));
	}
	@Test
	public void Special_character3_delete() {
		String result = junit.standardPOST("/v1/standard2/organization","中海 石油中国`有 限公司");
		assertTrue(result.contains("中海石油中国有限公司"));
	}
	@Test
	public void Special_delete() {
		String result = junit.standardPOST("/v1/standard2/organization","中海石油中国有限公司(test)");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"A1\"},\"sections\":{\"standOrg\":\"中海石油中国有限公司\",\"section\":[{\"wordAttr\":\"U\",\"name\":\"中海\"},{\"wordAttr\":\"I\",\"name\":\"石油\"},{\"wordAttr\":\"II\",\"name\":\"中国\"},{\"wordAttr\":\"O\",\"name\":\"有限公司\"}]}}]}",result);
	}
	@Test
	public void Special2_delete() {
		String result = junit.standardPOST("/v1/standard2/organization","中海石油中国有限公司（测试）");
		assertEquals("{\"type\":\"1\",\"dataList\":[{\"metadata\":{\"type\":\"A1\"},\"sections\":{\"standOrg\":\"中海石油中国有限公司\",\"section\":[{\"wordAttr\":\"U\",\"name\":\"中海\"},{\"wordAttr\":\"I\",\"name\":\"石油\"},{\"wordAttr\":\"II\",\"name\":\"中国\"},{\"wordAttr\":\"O\",\"name\":\"有限公司\"}]}}]}",result);
	}
	@Test
	public void Traditional_character() {
		String result = junit.standardPOST("/v1/standard2/organization","上海攜程商務有限公司");
		assertTrue(result.contains("上海携程商务有限公司"));
	}
	@Test
	public void revice1() {
		String result = junit.standardPOST("/v1/standard2/organization","杭州测试技术责任有限公司");
		assertTrue(result.contains("杭州测试技术有限责任公司"));
	}
	@Test
	public void revice2() {
		String result = junit.standardPOST("/v1/standard2/organization","杭州测试技术有限股份公司");
		assertTrue(result.contains("杭州测试技术股份有限公司"));
	}
	@Test
	public void revice3() {
		String result = junit.standardPOST("/v1/standard2/organization","杭州测试技术责任公司");
		assertTrue(result.contains("杭州测试技术有限责任公司"));
	}
	@Test
	public void revice4() {
		String result = junit.standardPOST("/v1/standard2/organization","杭州测试技术发展限公司");
		assertTrue(result.contains("杭州测试技术发展有限公司"));
	}
	@Test
	public void revice5() {
		String result = junit.standardPOST("/v1/standard2/organization","杭州测试技术有限总公司");
		assertTrue(result.contains("杭州测试技术有限公司"));
	}
	@Test
	public void revice6() {
		String result = junit.standardPOST("/v1/standard2/organization","交行");
		assertTrue(result.contains("交通银行"));
	}
	@Test
	public void revice7() {
		String result = junit.standardPOST("/v1/standard2/organization","进出口行");
		assertTrue(result.contains("进出口银行"));
	}
	@Test
	public void revice_orgshort() {
		String result = junit.standardPOST("/v1/standard2/organization","万科");
		assertTrue(result.contains("万科企业股份有限公司"));
	}
	@Test
	public void revice_orgshort2() {
		String result = junit.standardPOST("/v1/standard2/organization","深大");
		assertTrue(result.contains("深圳大学"));
	}
	@Test
	public void revice_orgshort3() {
		String result = junit.standardPOST("/v1/standard2/organization","成都银行");
		assertTrue(result.contains("都银行股份有限公司"));
	}
}
