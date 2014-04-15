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
package com.spotlabs.example.nvtracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.spotlabs.nvlogcattest.NSLogcatTest;
import com.spotlabs.nvtracker.api.INVTrackerShadowService;
import com.spotlabs.nvtracker.api.NVTrackerAPIFactory;
import com.spotlabs.nvtracker.api.NVTrackerEventApi;
import com.spotlabs.nvtracker.api.PiwikMessageRequest;
import com.spotlabs.nvtracker.common.NVApiMethod;
import com.spotlabs.nvtracker.common.NVSites;
import com.spotlabs.nvtrackerdb.NVLogRecordState;
import com.spotlabs.nvtrackerdb.NVTrackerLogRecord;
import com.spotlabs.nvtrackershadow.NVTrackerShadowService;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class NVTrackerEvent extends Fragment {

	INVTrackerShadowService mINVTrackerShadowService;
	boolean cBound = false;

	/* (non-Javadoc)
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	
		// Obtain the unique location identifier for this device. 					
		NVTrackerEventApi eventApi = NVTrackerAPIFactory.getEventAPI(mINVTrackerShadowService);
	
		// ---
		// Log a single event by type and detail
		eventApi.logEvent("test01", "Something about this event goes here");

		// ---
		// Log a single event using an event log record object
		PiwikMessageRequest logRecord =
				new PiwikMessageRequest( NVSites.NVEVENT.getValue(), NVApiMethod.ADD.getValue(),
						"nvevent-test02", "event details goes here", new Date().getTime());	
		eventApi.logEvent(logRecord);

		// ---
		// Log a list of event objects
		List<PiwikMessageRequest> logRecords = new ArrayList<PiwikMessageRequest>();

		// Create two log record objects 
		for (int i=0; i< 2; i++) {
			PiwikMessageRequest logRecord =
					new PiwikMessageRequest( NVSites.NVEVENT.getValue(), NVApiMethod.ADD.getValue(),
							"test"+i, "event details goes here", new Date().getTime());	
			logRecords.add(logRecord);
		}
		eventApi.logEvents(logRecords);

	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onStart()
	 */
	@Override
	public void onStart() {
		super.onStart();
		bindService(new Intent(NVTrackerEvent.this, NVTrackerShadowService.class), mConnection, BIND_AUTO_CREATE);
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onStop()
	 */
	@Override
	public void onStop() {
		super.onStop();
		if(cBound) {
			unbindService(mConnection);
			cBound = false; 
		}
	}

	ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mINVTrackerShadowService = INVTrackerShadowService.Stub.asInterface(service);
			cBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mINVTrackerShadowService = null;
			cBound = false;
		}
	};

}
