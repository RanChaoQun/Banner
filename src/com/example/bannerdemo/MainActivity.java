package com.example.bannerdemo;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.rxx.banner.BannerManager;

public class MainActivity extends Activity {

	private View mRootView;
	private BannerManager mBannerManager;
	private List<String> mUrls;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRootView=View.inflate(this, R.layout.activity_main, null);
		setContentView(mRootView);
		mUrls=new ArrayList<String>();
		mUrls.add("http://img2.3lian.com/2014/c7/13/d/1.jpg");
		mUrls.add("http://img3.3lian.com/2014/c2/61/d/2.jpg");
		mUrls.add("http://img.dapixie.com/uploads/allimg/110517/1-11051G33041.jpg");
		mBannerManager=new BannerManager(mRootView, mUrls, FinalBitmap.create(this));
	}
	
}
