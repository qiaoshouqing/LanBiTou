package com.lanbitou.thread;

import android.os.Handler;
import android.os.Message;

import com.lanbitou.net.GetPostUtil;

/**
 * Post线程
 * creat by joyce
 */

public class HttpPostThread implements Runnable{
	
	private Handler handler;
	private String url = null;
	private String value;
	private String img = "";
	int what = 0x124;
	Message msg = new Message();
	
	public HttpPostThread(Handler handler, String url, String value)
	{
		this.handler = handler;
		this.url = url;
		this.value = value;
	}
	
	public HttpPostThread(Handler handler, String url, String value, String img)
	{
		this.handler = handler;
		this.url = url;
		this.value = value;
		this.img = img;
	}
	public HttpPostThread(Handler handler, String url, String value, int what)
	{
		this.handler = handler;
		this.url = url;
		this.value = value;
		this.what = what;
	}

	@Override
	public void run() {
		
		String result = null;
		if(img.equals(""))
		{
			result = GetPostUtil.doPost(url, value);
		}
		else
		{
			result = GetPostUtil.doPost(url, value);
		}
		
		msg.what = what;//表示post请求
		msg.arg1 = 1;//表示是从网络获取的数据
		msg.obj = result;
		handler.sendMessage(msg);
	}

}
