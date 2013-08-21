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
package com.spotlabs.example.behavior;

import com.spotlabs.example.R;
import com.spotlabs.idle.IdleService;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Idle happens by default after a period of inactivity,
 * but there are many times where you may want to explicit
 * active a device's idle state such as the end of a specific
 * user path.
 * 
 * @author asax
 *
 */
public class ActivateIdle extends Fragment {

	private View mView;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// grab the idle button from our layout
		Button submit = (Button) mView.findViewById(R.id.activate_idle_button);
		
		// add an onClick listener
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Dispatch an intent using the Activate action from the IdleService
				Intent i = new Intent(IdleService.ACTION_ACTIVATE);
				getActivity().startService(i);
			}
		});
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.example_activate_idle, container, false);
		return mView;
	}

	
}
