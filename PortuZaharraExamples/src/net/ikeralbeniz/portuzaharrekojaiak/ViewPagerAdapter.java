package net.ikeralbeniz.portuzaharrekojaiak;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.viewpagerindicator.TitleProvider;
import android.webkit.WebView;

public class ViewPagerAdapter extends PagerAdapter implements TitleProvider{
	
	private static String[] titles = new String[] { "Jueves 9", "Viernes 10", "Sabado 11", "Domingo 12", "Lunes 13" };
	
	private final Context context;
	private static String language = "es";
	
	private int[] scrollPosition = new int[titles.length];

	public ViewPagerAdapter( Context context )
	{
		titles[0] = context.getString(R.string.day9);
		titles[1] = context.getString(R.string.day10);
		titles[2] = context.getString(R.string.day11);
		titles[3] = context.getString(R.string.day12);
		titles[4] = context.getString(R.string.day13);
		
		language = context.getString(R.string.languagetag);
		
		this.context = context;
		for ( int i = 0; i < titles.length; i++ )
		{
			scrollPosition[i] = 0;
		}
	}

	@Override
	public String getTitle( int position )
	{
		return titles[position];
	}

	@Override
	public int getCount()
	{
		return titles.length;
	}

	@Override
	public Object instantiateItem( View pager, final int position )
	{
		WebView webview  = new WebView(context);
		
		webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setGeolocationEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        
		((ViewPager)pager ).addView( webview, 0 );
		
		webview.loadUrl("file:///android_asset/"+language+"/day_"+ Integer.toString(position) +".html");
		
		return webview;
	}

	@Override
	public void destroyItem( View pager, int position, Object view )
	{
		( (ViewPager) pager ).removeView( (WebView) view );
	}

	@Override
	public boolean isViewFromObject( View view, Object object )
	{
		return view.equals( object );
	}

	@Override
	public void finishUpdate( View view )
	{
	}

	@Override
	public void restoreState( Parcelable p, ClassLoader c )
	{
		if ( p instanceof ScrollState )
		{
			scrollPosition = ( (ScrollState) p ).getScrollPos();
		}
	}

	@Override
	public Parcelable saveState()
	{
		return new ScrollState( scrollPosition );
	}

	@Override
	public void startUpdate( View view )
	{
	}

}
