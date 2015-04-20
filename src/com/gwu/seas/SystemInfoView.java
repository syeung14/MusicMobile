package com.gwu.seas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SystemInfoView extends Activity {
	private static final String TRTURN_MARK="\r\n";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.systemtab);
		
		TextView textView=(TextView) this.findViewById(R.id.systeminfo);
		StringBuilder info=new StringBuilder();
		
		String androidVersion=android.os.Build.VERSION.RELEASE;
		info.append("Android Version is: "+androidVersion).append(TRTURN_MARK);
		String modelVersion=android.os.Build.MODEL;
		info.append("Model is: "+modelVersion).append(TRTURN_MARK);
		String osVersion=System.getProperty("os.version");
		info.append("OS Version is: "+osVersion).append(TRTURN_MARK);
		String device=android.os.Build.DEVICE;
		info.append("Device is: "+device).append(TRTURN_MARK);
		String product=android.os.Build.PRODUCT;
		info.append("Product is: "+product).append(TRTURN_MARK);
		String serial=android.os.Build.SERIAL;
		info.append("Serial is: "+serial).append(TRTURN_MARK);
		String hardware=android.os.Build.HARDWARE;
		info.append("Hardware is: "+hardware).append(TRTURN_MARK);
		String tag=android.os.Build.TAGS;
		info.append("Tag is: "+tag).append(TRTURN_MARK);
		String type=android.os.Build.TYPE;
		info.append("Type is: "+type).append(TRTURN_MARK);
		String manufacture=android.os.Build.MANUFACTURER;
		info.append("Manufacture is: "+manufacture).append(TRTURN_MARK);
		
		//String fingerprint=android.os.Build.FINGERPRINT;
		//info.append("Fingerprint is: "+fingerprint).append(TRTURN_MARK);
		
		textView.setText(info.toString());
	}
}