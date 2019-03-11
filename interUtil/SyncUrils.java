package com.easemob.easeui.interUtil;

import android.os.AsyncTask;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * 异步线程工具类
 */
public class SyncUrils extends AsyncTask<Object, Void, String> {

	private String url;
	private List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
	private Map<String,String> mapParm;
	private String http_request_tag = GET;
	public static final String POST = "POST";
	public static final String GET = "GET";
	private OnResponseListener onResponseListener;

	public void setOnResponseListener(OnResponseListener onResponseListener) {
		this.onResponseListener = onResponseListener;
	}

	public SyncUrils(String url,Map<String,String> mapParm,String request_ype){
		this.mapParm = mapParm;
		this.url = url;
		this.http_request_tag = request_ype;
	}

	@Override
	protected String doInBackground(Object... arg0) {//用于判断后台耗时操作
		String result = null;
		params.clear();
		if(this.url == null || this.params == null){
			return result;
		}
		Set<Entry<String, String>> entrySet = mapParm.entrySet();//把map集合转换为set
		for(Entry<String, String> entryset : entrySet ){//把key和value值添加到
			String key = entryset.getKey();
			String value = entryset.getValue();
			BasicNameValuePair bName = new BasicNameValuePair(key, value);
			params.add(bName);
		}
		if(http_request_tag.equals("GET")){
			result =  HttpClientUtils.get(url, params);
		}else if(http_request_tag.equals("POST")){
			result =  HttpClientUtils.post(url, params);
		}
		return result;
	}

	@Override
	protected void onPostExecute(String result) {//后台任务执行完以后调用
		if(this.onResponseListener != null){
			if(result != null){
				this.onResponseListener.sussce(result);
			}else{
				this.onResponseListener.error(result);
			}
		}
	}

	public interface OnResponseListener {
		public void sussce(Object obj);
		public void error(Object... obj);
	}
}
