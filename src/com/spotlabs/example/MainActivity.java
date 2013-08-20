package com.spotlabs.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.spotlabs.example.behavior.ActivateIdle;
import com.spotlabs.example.cache.SimpleCache;
import com.spotlabs.example.messaging.FormMessage;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Fragment;


public class MainActivity extends Activity implements OnItemClickListener {

	private static final List<Example> examples = new ArrayList<Example>() {{
			add(new MainActivity.Example("Simple Caching", SimpleCache.class));
			add(new MainActivity.Example("Simple Form Message", FormMessage.class));
			add(new MainActivity.Example("Force Idle", ActivateIdle.class));
	}};
	
	private ArrayAdapter<Example> mAdapter;
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, examples);
		
		mListView = (ListView) findViewById(R.id.exampleList);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		try {
			Example example = mAdapter.getItem(position);
			Fragment fragment = example.getFragment().newInstance();
			FrameLayout holder = (FrameLayout) findViewById(R.id.exampleArea);
			holder.removeAllViews();
			FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
			fragTrans.add(R.id.exampleArea, fragment).commit();
		} catch (Exception e) {}
	}
	
	private static class Example {
		
		private String mName;
		private Class<? extends Fragment> mFragment;
		
		public Example(String name, Class<? extends Fragment> fragment) {
			mName = name;
			mFragment = fragment;
		}
		
		public String getName() {
			return mName;
		}
		
		public Class<? extends Fragment> getFragment() {
			return mFragment;
		}
		
		public String toString() {
			return mName;
		}
	}


}
