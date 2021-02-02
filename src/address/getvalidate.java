package address;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.commons.codec.digest.HmacUtils;

public class getvalidate {
	static protected String toUrlCode(String param) {
		try {
			return URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	public static String httpget_example(String urlPath, String param, String APP_SECRET) {
		String date = String.valueOf(new Date().getTime());
		String signString = urlPath + "\n" + param + "\n" + date;
		String validate = HmacUtils.hmacMd5Hex(APP_SECRET.getBytes(), signString.getBytes(Charset.forName("UTF-8")));
		return validate + "\t" + date;
	}

	public static String httpget_example(String urlPath, String param, String APP_SECRET, int minutes) {
		String date = String.valueOf(new Date().getTime() - (minutes * 60 * 1000));
		String signString = urlPath + "\n" + param + "\n" + date;
		String validate = HmacUtils.hmacMd5Hex(APP_SECRET.getBytes(), signString.getBytes(Charset.forName("UTF-8")));
		return validate + "\t" + date;
	}
}
