/*******************************************************************************
 * Copyright 2013 Spot Labs Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
