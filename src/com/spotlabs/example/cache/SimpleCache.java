package com.spotlabs.example.cache;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.spotlabs.cache.CacheService;
import com.spotlabs.example.R;

public class SimpleCache extends Fragment {

	private View mView;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Create a cache url for the HTTP resource you want to cache
		Uri cacheUri = CacheService.getCacheUri("http://www.spotlabs.com/logo.png");
		
		// Create an ImageView or another view you can load an Uri into
		ImageView imageView = (ImageView) mView.findViewById(R.id.simple_cache_image);
		
		// Load the uri
		imageView.setImageURI(cacheUri);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.example_simple_cache, container, false);
		return mView;
	}

			
}
