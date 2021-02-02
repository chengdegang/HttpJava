package address;

import org.testng.Assert;
import org.testng.annotations.Test;

public class test {
	public static void main(String[] args) {
      major test = new major();
      urlchange url = new urlchange();
//        test.httpURLConectionGET("/v1/actuator/health","");
      /*
       * 云4.6标准化接口（地址）
       */
//      String add = "山东省青岛市莱西市水集街道办事处展格庄437号 ";
//      String temp = test.standardPOST("/v1/standard2/address", add);
//      System.out.println("pri     ："+json.change(temp));
//      System.out.println("ori_dd  ："+add);
//      System.out.println(temp);
//      System.out.println("-------------------------------------------");
      /*
       * 云4.6真实性接口（地址）
       */
      String add2 = "address="+url.getURLEncoderString("重庆市,重庆市,江北区,金源路董家溪鹅石堡山26号（重庆工商大学江北校区）,段淋芮");
      test.standardGET("/api/address/truth/verify?srcAddr", add2);
      System.out.println("-------------------------------------------");
      /*
         * 云4.6标准化接口（单位）
       */
//    String temp2  = test.standardPOST("/v1/standard2/organization", "重庆农商");
//    System.out.println(temp2);
      /*
       * 最新前置标准化接口(地址)
       */
//      String add3 = "广东省中山市中山市中山火炬开发区";
//      String temp2 = test.matchPOST("10.100.1.220:17003","/v1/address/data/standardized",add3);
//      System.out.println("pri     ："+json.change4_7(temp2));
      
  }
}
