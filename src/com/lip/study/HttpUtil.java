package com.lip.study;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.util.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpUtil {
	public static String session_id = null;
	public static final String HOST = "localhost:8085";

	static {
		// getSessionId();
	}

	public static void main(String[] args) throws Exception{
		Map<Integer,Integer>result=new HashMap<>();
		for(int i=0;i<10000;i++)
		{
			URI uri = new URIBuilder().setHost(HOST).setScheme("http").setPath("/user/test.html")
					.build();
			JSONObject object= JSON.parseObject(sendRequest(uri,RequestMethod.POST));
			Integer code=object.getInteger("code");
			if(result.get(code)!=null)
				result.put(code,result.get(code)+1);
			else
				result.put(code,1);
		}
		System.out.println(result);
	}



	public static void fuck() {
		final Map<String, String> param = new HashMap();
		for (int i = 0; i < 5000; i++) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					try {
						modifyHeader();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
			thread.start();
		}
	}

	public static void modifyHeader() {
		try {
			URI uri = new URIBuilder().setHost(HOST).setScheme("https")//.setPath("/user/login.html")
					//.addParameter("userId","18071040417")
					.build();
			sendRequest(uri, RequestMethod.GET);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到图形验证码
	 *
	 * @param num
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static void getValiCodeImage(int num) throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = new URIBuilder().setHost(HOST).setScheme("https").setPath("/security/securityCodeImage.html").build();
		for (int i = 0; i < num; i++) {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpRequestBase http = null;
			http = new HttpGet(uri);
			http.addHeader("X-Forwarded-For", getRandomIp());
			http.addHeader("User-Agent",
					"12345");
			http.addHeader("Connection", "keep-alive");
			HttpResponse response = client.execute(http);
			InputStream inStream = response.getEntity().getContent();
			OutputStream out = new FileOutputStream(new File("./pic/" + i + ".png"));
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = inStream.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			inStream.close();
		}
	}
	/// security/securityCodeImage.html

	/**
	 * 发送一个请求
	 *
	 * @param uri
	 * @throws URISyntaxException
	 */
	public static String sendRequest(URI uri, RequestMethod type) {
		StringBuilder strber = new StringBuilder();
		try {
			HttpClient client = HttpClients.createDefault();
			HttpRequestBase http = null;
			if (type == RequestMethod.GET) {
				http = new HttpGet(uri);
			} else {
				http = new HttpPost(uri);
			}
			http.addHeader("X-Forwarded-For", getRandomIp());
			http.addHeader("Cookie", "JSESSIONID=" + session_id);
			http.addHeader("User-Agent",
					"bot");
			http.addHeader("Connection", "keep-alive");
			// http.addHeader("X-Forwarded-For", "I am come");
			// http.addHeader("Cookie",
			// "JSESSIONID=2FBB81AB6CDB9197449E5DFBE49607C3");
			HttpResponse response = client.execute(http);
			InputStream inStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null)
				strber.append(line + "\n");
			inStream.close();
			System.out.println(strber.toString());
			Header header[] = response.getAllHeaders();
			for (Header header2 : header) {
				System.out.println(header2.getName() + ":" + header2.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strber.toString();
	}

	/**
	 * 得到一个连接的JSESSIONID
	 *
	 * @return
	 */
	public static String getSessionId() {
		System.out.println("get session id");
		try {
			if (session_id != null)
				return session_id;
			List<Cookie> cookies = getCookie();
			for (Cookie cookie : cookies) {
				if ("JSESSIONID".equalsIgnoreCase(cookie.getName())) {
					session_id = cookie.getValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session_id;
	}

	/**
	 * 得到一个连接所有的cookie
	 *
	 * @return
	 */
	public static List<Cookie> getCookie() {
		List<Cookie> cookies = null;
		try {
			URI uri = new URIBuilder().setHost(HOST).setScheme("https").build();
			HttpGet http = new HttpGet(uri);
			HttpClient client = HttpClients.createDefault();
			http.addHeader("X-Forwarded-For", getRandomIp());
			HttpResponse response = client.execute(http);
			if (response.getStatusLine().getStatusCode() == 200) {
				CookieStore cookieStore = ((AbstractHttpClient) client).getCookieStore();
				cookies = cookieStore.getCookies();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cookies;
	}

	/**
	 * 得到一个随机的Ip
	 *
	 * @return
	 */
	public static String getRandomIp() {
		StringBuilder sb = new StringBuilder();
		int first = 10;
		while (first == 10 || first == 172 || first == 192) {
			first = (int) (Math.random() * 256);
		}
		sb.append(first);
		for (int i = 1; i < 4; i++) {
			sb.append(".").append((int) (Math.random() * 256));
		}
		return sb.toString();
	}
}

enum RequestMethod {
	POST(1), GET(0);
	private int index;

	private RequestMethod(int index) {
		this.setIndex(index);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}