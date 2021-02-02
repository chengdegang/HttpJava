package address;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class test_with_Parameterized {
	String org;
	String expect;
	major junit = new major();
	
	public test_with_Parameterized(String orgnow,String expectnow) {
		this.expect = expectnow;
		this.org = orgnow;
	}
	
	@Parameters
	public static Collection<String[]> testData() {
        String[][] data = new String[][] {
        	{ "中海石油【中国】有限公司","中海石油(中国)有限公司" },
			{ "中海石油{中国}有限公司","中海石油(中国)有限公司" },
			{ "中海石油《中国》有限公司","中海石油(中国)有限公司"},
			{ "中海石油<中国>有限公司","中海石油(中国)有限公司"}
        };
        return Arrays.asList(data);
	}
	
	@Test
	public void Org_Special_brackets_retain()
	{   
		String result = junit.standardPOST("/v1/standard2/organization",org);
		assertTrue(result.contains(expect));
    }


}
