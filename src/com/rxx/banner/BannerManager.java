package com.rxx.banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

import com.example.bannerdemo.R;
import com.rxx.banner.utils.CommonUtils;

/**
 * 
 * Bannerview Manager
 * 
 * @author Ran
 *
 * 时间: 2015年6月17日
 */
public class BannerManager implements OnPageChangeListener, OnClickListener {
	private ViewPager mViewPager = null;
	private FrameLayout layout_out;
	private View mRootView = null;
	private BannerRound mBannerRound = null;
	private List<ImageView> mImageViews = null;
	private List<String> mUrls = null;
	BannerAdapter mBannerAdapter = null;
	private FinalBitmap mBitmap;
	private int mCount = 0;
	private Context mContext;
	private Timer timer;
	private int nowSelect;
	private TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			handler.sendEmptyMessage(0);
		}
	};
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			nowSelect++;
			mViewPager.setCurrentItem(nowSelect);
		}
	};
	
	public int getHeight(){
		return layout_out.getHeight();
	}
	
	public interface BannerClickLintener{
		public void click(int position);
	}
	
	BannerClickLintener bannerClickLintener;
	
	public BannerManager(View rootView, List<String> mUrls, FinalBitmap mBitmap) {
		this.mRootView = rootView;
		this.mUrls = mUrls;
		this.mBitmap = mBitmap;
		init();
		initData();
	}
	
	
	public BannerClickLintener getBannerClickLintener() {
		return bannerClickLintener;
	}


	public void setBannerClickLintener(BannerClickLintener bannerClickLintener) {
		this.bannerClickLintener = bannerClickLintener;
	}


	public void update(){
		if (mUrls != null) {
			mCount = mUrls.size();
		}
		if(mCount==0){
			layout_out.setVisibility(View.GONE);
		}else{
			layout_out.setVisibility(View.VISIBLE);
		}
		try {
			mBannerRound.setShowNum(mCount);
			if(mCount!=0){
				mBannerRound.setCircleOn(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initData();
	}

	public void init() {
		mContext = mRootView.getContext();
		layout_out = (FrameLayout) mRootView.findViewById(R.id.layout_out);
		mBannerRound = (BannerRound) mRootView.findViewById(R.id.bannerround);
		mViewPager = (ViewPager) mRootView.findViewById(R.id.viewpaper);
		LayoutParams layoutParams = (LayoutParams) layout_out.getLayoutParams();
		layoutParams.height = (int) (CommonUtils.getWIndowWidth(mRootView
				.getContext()) * 0.4);
		layout_out.setLayoutParams(layoutParams);
		try {
			mBannerRound.setShowNum(mCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mImageViews = new ArrayList<ImageView>();
		mBannerAdapter = new BannerAdapter();
		mViewPager.setAdapter(mBannerAdapter);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setCurrentItem(mCount * 100);
		nowSelect=mCount*100;
		mViewPager.setOffscreenPageLimit(1);
		try {
			mBannerRound.setCircleOn(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (timer == null) {
			timer = new Timer();
			timer.schedule(timerTask, 4000, 4000);
		}
		update();
	}

	public void initData() {
		android.view.ViewGroup.LayoutParams layoutParams = mViewPager
				.getLayoutParams();
		mImageViews.clear();
		for (int i = 0; i < (mCount * 4); i++) {
			ImageView imageView = new ImageView(mRootView.getContext());
			layoutParams.width = LayoutParams.MATCH_PARENT;
			layoutParams.height = LayoutParams.MATCH_PARENT;
			imageView.setLayoutParams(layoutParams);
			imageView.setScaleType(ScaleType.FIT_XY);
			mBitmap.display(imageView, mUrls.get(i % mCount));
			imageView.setOnClickListener(this);
			imageView.setTag(i % mCount);
			mImageViews.add(imageView);
		}
		mBannerAdapter.notifyDataSetChanged();
	}

	class BannerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return mCount==0?mCount:Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup view, int position, Object object) {
			view.removeView(mImageViews.get(position % (mCount * 4)));
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			try {
				view.addView(mImageViews.get(position % (mCount * 4)), 0);
			} catch (Exception ex) {
			}
			return mImageViews.get(position % (mCount * 4));
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		nowSelect=arg0;
		try {
			mBannerRound.setCircleOn(arg0 % mCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		if(bannerClickLintener!=null){
			bannerClickLintener.click((int)v.getTag());
		}
	}
}
