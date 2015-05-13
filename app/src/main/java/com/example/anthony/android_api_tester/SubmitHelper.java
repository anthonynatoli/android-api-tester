package com.example.anthony.android_api_tester;

import com.loopj.android.http.*;

import org.apache.http.Header;

public class SubmitHelper {

    private AsyncHttpClient client;
    private RequestParams params;



    public SubmitHelper(){
        client = new AsyncHttpClient();
        params = new RequestParams();
    }

    public String execute(String host, String body, String method_type){
        String response = "";

        switch(method_type){
            case "GET":
                client.get(host, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
