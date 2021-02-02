package address;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSONPath;

public class major{
  
  /**
   * 接口调用 GET(初始化)
   */
   String matchGET(String new_url,String path,String da) {
	  final String GET_URL = new_url+path+da;
	  StringBuilder sb = new StringBuilder();
      try {
          URL url = new URL(GET_URL);    // 把字符串转换为URL请求地址
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
          connection.connect();// 连接会话
          // 获取输入流
          BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
          String line;
          while ((line = br.readLine()) != null) {// 循环读取流
              sb.append(line);
          }
          br.close();// 关闭流
          connection.disconnect();// 断开连接
          System.out.println(sb.toString());
      } catch (Exception e) {
          e.printStackTrace();
          System.out.println("失败!");
      }
      return sb.toString();
  }
  
  /**
   * 接口调用  POST(增量匹配、其他)
   */
  String matchPOST (String new_url,String path,String address) {
	  String result = "";
	  String parm = null;
	  final String POST_URL = "http://"+new_url+path;
      try {
          URL url = new URL(POST_URL);
          // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
          // 此时cnnection只是为一个连接对象,待连接中
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
          connection.setDoOutput(true);
          // 设置连接输入流为true
          connection.setDoInput(true);
          // 设置请求方式为post
          connection.setRequestMethod("POST");
          // post请求缓存设为false
          connection.setUseCaches(false);
          // 设置该HttpURLConnection实例是否自动执行重定向
//          connection.setInstanceFollowRedirects(true);
          // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
          connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
          if(path.contains("standardized")) {
        	   parm = "{\"dataList\":[{\"srcAddr\":\""+address+"\"}]}";
          }else {
        	   parm = "{\"uniqueCode\":\"123456\",\"period\":\"1\",\"limit\":\"5\",\"dataList\":[{\"address\":\""+address+"\",\"metadata\":{\"type\":\"\"}}]}";
          }
          // 设置接收数据的格式
//          connection.setRequestProperty("Accept","application/json");
          // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
          connection.connect();
          // 创建输入输出
          PrintWriter dataout = new PrintWriter(connection.getOutputStream());
          // 将参数输出到连接
          dataout.print(parm);
          // 输出完成后刷新并关闭流
          dataout.flush();
          dataout.close(); // 重要且易忽略步骤 (关闭流,切记!) 
          // 处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
          BufferedReader in = null;
          if (connection.getResponseCode() == 200) {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				result = in.readLine();
			} else {
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				result = in.readLine();
			}
          String line;
          while ((line = in.readLine()) != null) {
				result += line;
			}
          in.close();    // 重要且易忽略步骤 (关闭流,切记!) 
          connection.disconnect(); // 销毁连接
          System.out.println(result.replace("\t", ""));
      } catch (Exception e) {
          e.printStackTrace();
      }
      return result.replace("\t", "");
}
  /**
   * 接口调用  POST 标准化
 * @return 
   */
  String standardPOST (String path,String add_org) {
	  String type = "1";
	  String shuju = add_org;
	  final String POST_URL = "http://10.100.1.190:10100"+path;
	  URL url =null;
	  HttpURLConnection connection =null;
	  BufferedReader in = null;
	  String result = "";
	  String parm = null;
      try {
           url = new URL(POST_URL);
          // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
          // 此时cnnection只是为一个连接对象,待连接中
           connection = (HttpURLConnection) url.openConnection();
          // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
          connection.setDoOutput(true);
          // 设置连接输入流为true
          connection.setDoInput(true);
          // 设置请求方式为post
          connection.setRequestMethod("POST");
          // post请求缓存设为false
          connection.setUseCaches(false);
          // 设置该HttpURLConnection实例是否自动执行重定向
//          connection.setInstanceFollowRedirects(true);
          // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
          connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
          // 设置接收数据的格式
//          try {      connection.setRequestProperty("Accept","application/json");
          // 入参
          if(path.contains("organization")) {
        	  parm = "{\"type\":\""+type+"\",\"dataList\": [{\"metadata\": {\"type\": \"A1\"},\"organization\": \""+shuju+"\"}]}";
          }else {
        	  parm ="{\"type\":\""+type+"\",\"dataList\":[{\"address\":\""+shuju+"\",\"metadata\":{\"type\":\"1\"}}]}";
          }
          String data = getvalidate.httpget_example( path, parm ,"zG8ze7Ji727uwWAu4xyWcEpIyhJftSDoWhuMTnEb");
          String validate = data.split("\t")[0];
          String time = data.split("\t")[1];
          connection.setRequestProperty("X-BS-App-Key", "nLcHghAWghFbYhg91xp0");
          connection.setRequestProperty("X-BS-Date", time);
          connection.setRequestProperty("X-BS-Validate", validate);
          // 创建输入输出
          PrintWriter dataout = new PrintWriter(connection.getOutputStream());
          // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
          connection.connect();
          // 将参数输出到连接
          dataout.print(parm);;
          // 输出完成后刷新并关闭流
          dataout.flush();
          dataout.close(); // 重要且易忽略步骤 (关闭流,切记!) 
          // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
          if (connection.getResponseCode() == 200) {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				result = in.readLine();
			} else {
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				result = in.readLine();
			}
          String line;
          while ((line = in.readLine()) != null) {
				result += line;
			}
          in.close();    // 重要且易忽略步骤 (关闭流,切记!) 
            } catch (Exception e) {
          e.printStackTrace();
      }
          connection.disconnect(); // 销毁连接
          if(result.contains("standAddr")) {
          System.out.println("standAddr："+JSONPath.read(result,"$.dataList[0].sections.standAddr"));
          }else {
        	  System.out.println("standOrg："+JSONPath.read(result,"$.dataList[0].sections.standOrg"));
          }
          return result;
}
  
  
  String standardGET(String path,String da) {
	  final String GET_URL = "http://10.100.1.220:10060"+path+"?"+da;
	  urlchange c = new urlchange();
	  StringBuilder sb = new StringBuilder();
	  String temp = c.getURLDecoderString(da);
	  String parm = temp;
      try {
         URL url = new URL(GET_URL);    // 把字符串转换为URL请求地址
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
         connection.setRequestProperty("X-BS-App-Key", "nLcHghAWghFbYhg91xp0");
         String data = getvalidate.httpget_example( path, parm ,"zG8ze7Ji727uwWAu4xyWcEpIyhJftSDoWhuMTnEb");
         String validate = data.split("\t")[0];
         String time = data.split("\t")[1];
         connection.setRequestProperty("X-BS-Date", time);
         connection.setRequestProperty("X-BS-Validate", validate);
         connection.connect();// 连接会话
         // 获取输入流
         BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
         String line;	
         while ((line = br.readLine()) != null) {// 循环读取流
             sb.append(line);
         }
         br.close();// 关闭流
         connection.disconnect();// 断开连接
         System.out.println(sb.toString());
     } catch (Exception e) {
         e.printStackTrace();
         System.out.println("失败!");
     }
     return sb.toString();
 }
}
