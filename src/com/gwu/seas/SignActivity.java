package com.gwu.seas;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends Activity implements Properties{
	private EditText mNameText = null;
	private EditText mPasswdText = null;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_register);
		
		mNameText = (EditText) findViewById(R.id.uname);
		mPasswdText = (EditText) findViewById(R.id.passwd);
		
	}
	
    public void welcome_login(View v) { 
    	String uname = mNameText.getText().toString();
    	String passwd = mPasswdText.getText().toString();
    	NameValuePair username = new BasicNameValuePair("username", uname);
        NameValuePair password = new BasicNameValuePair("password", passwd);
        //NameValuePair signTag = new BasicNameValuePair("signtag", "signin");
        
        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
        pairList.add(username);
        pairList.add(password);
        //pairList.add(signTag);
        
        try{
        	HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
        	HttpPost httpPost = new HttpPost(userBaseURL+"login");
        	httpPost.setEntity(requestHttpEntity);
        	HttpClient httpClient = new DefaultHttpClient();
        	httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
        	httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
        	HttpResponse response = httpClient.execute(httpPost);
        	String xml = EntityUtils.toString(response.getEntity(), "UTF-8");
        	//mNameText.setText(xml);
        	
        	XMLParser parser = new XMLParser();
        	Document doc = parser.getDomElement(xml); // getting DOM element
        	String success = parser.getValue(doc.getDocumentElement(),UNAME);
        	Log.i("XMLContentText", success);
        	if("do not exist".equals(success)){
        		Toast toast = Toast.makeText(SignActivity.this, "Wrong username or password", Toast.LENGTH_SHORT);
        		toast.show();
        	} else {
        		Intent intent=new Intent().setClass(this,MyTabActivity.class);
            	intent.putExtra("username", uname);
            	intent.putExtra("xml", xml);
            	startActivity(intent);
        	}
        } catch (Exception e)
        {
        	Log.i("HttpExp", "Exception!");
        	e.printStackTrace();
        }
        //showResponseResult(response);
      }  
    
    public void welcome_register(View v) {  
    	String uname = mNameText.getText().toString();
    	String passwd = mPasswdText.getText().toString();
    	NameValuePair username = new BasicNameValuePair("username", uname);
        NameValuePair password = new BasicNameValuePair("password", passwd);
        //NameValuePair signTag = new BasicNameValuePair("signtag", "signin");
        
        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
        pairList.add(username);
        pairList.add(password);
       // pairList.add(signTag);
        
        try{
        	HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
        	HttpPost httpPost = new HttpPost(userBaseURL+"register");
        	httpPost.setEntity(requestHttpEntity);
        	HttpClient httpClient = new DefaultHttpClient();
        	httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
        	httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
        	HttpResponse response = httpClient.execute(httpPost);
        	String xml = EntityUtils.toString(response.getEntity(), "UTF-8");
        	//mNameText.setText(xml);
        	
        	XMLParser parser = new XMLParser();
        	Document doc = parser.getDomElement(xml); // getting DOM element
        	String success = parser.getValue(doc.getDocumentElement(),CONTENT);
        	Log.i("XMLContentText", success);
        	if("false".equals(success)){
        		Toast toast = Toast.makeText(SignActivity.this, "Username exists", Toast.LENGTH_SHORT);
        		toast.show();
        	} else if("true".equals(success)){
        		welcome_login(v);
        	}
        } catch (Exception e)
        {
        	Log.i("HttpExp", "Exception!");
        	e.printStackTrace();
        }
      }
    
    public void welcome_exit(View view){
		this.finish();
		System.exit(0);
    }
}
