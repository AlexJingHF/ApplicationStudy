package com.example.myapplication;

import java.util.HashMap;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application
{
	private HashMap<String, Object> mCache = null;
	private HashMap<String, Object> mData = null;
	public static Context CONTEXT;
	@Override
	public void onCreate()
	{
		super.onCreate();
		CONTEXT = this.getApplicationContext();
		mCache = new HashMap<String, Object>();
		mData = new HashMap<String, Object>();
	}
	
	public boolean setCache(String key,Object object)
	{
		mCache.put(key, object);
		return true;
	}
	
	public boolean setData(String key,Object object)
	{
		mData.put(key, object);
		return true;
	}
	
	public Object getData(String key)
	{
		if (mData.containsKey(key))
		{
			return mData.get(key);
		}
		return null;
	}
	
	public Object getCache(String key)
	{
		if (mCache.containsKey(key))
		{
			return mCache.get(key);
		}
		return null;
	}
	
	public void removeData(String key)
	{
		if (mData.containsKey(key))
		{
			mData.remove(key);
		}
	}
	
	public void removeCache(String key)
	{
		if (mCache.containsKey(key))
		{
			mCache.remove(key);
		}
	}
	
	public void clearData()
	{
		mData.clear();
	}
	
	public void clearCache()
	{
		mCache.clear();
	}
	
}
