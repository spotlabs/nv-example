package com.spotlabs.example.messaging;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.spotlabs.example.R;
import com.spotlabs.messaging.FormDataMessage;
import com.spotlabs.messaging.MessageService;

public class FormMessage extends Fragment {

	private View mView;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// grab the submit button
		Button submit = (Button) mView.findViewById(R.id.form_message_submit);
		
		// add an onClick listener
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// when someone presses submit call sendMessage with the name and email
				// entered on screen
				EditText name = (EditText) mView.findViewById(R.id.form_message_name);
				EditText email = (EditText) mView.findViewById(R.id.form_message_email);
				sendMessage(name.getText().toString(), email.getText().toString());
			}
		});
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.example_form_message, container, false);
		return mView;
	}
	
	public void sendMessage(String name, String email) {
		
		// add our name and email to a list of name value pairs
		List<NameValuePair> formBody = new ArrayList<NameValuePair>();
		formBody.add(new BasicNameValuePair("name", name));
		formBody.add(new BasicNameValuePair("email", email));

		// create our message
		FormDataMessage message = new FormDataMessage(URI.create("http://www.example.com/postEndpoint"), formBody);
		
		// grab a message service and queue the message we created
		MessageService messageService = new MessageService(this.getActivity());
		messageService.queue(message);
		
		Toast toast = Toast.makeText(this.getActivity(), "Message Queued", Toast.LENGTH_LONG);
		toast.show();
	}

}
