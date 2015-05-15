package com.example.anthony.android_api_tester;

import android.app.AlertDialog;
import android.util.Log;
import android.app.Activity;

import com.loopj.android.http.*;

import org.apache.http.Header;

public class SubmitHelper extends Activity{

    private AsyncHttpClient client;
    private RequestParams params;



    public SubmitHelper(){
        client = new AsyncHttpClient();
        params = new RequestParams();
    }

    public String execute(String host, String body, String method_type){
        String response = "";
        Log.i("SUBMITHELPER", "In execute " + method_type);
        switch(method_type){
            case "GET":
                client.get(host, params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String s = new String(responseBody);
                        Log.i("SUBMITHELPER", "Returned: " + s);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.i("SUBMITHELPER", "FAILURE");
                    }
                });
                break;
            case "POST":
                client.post(host, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                break;
            default:
                return null;
        }

        return response;
    }
}
