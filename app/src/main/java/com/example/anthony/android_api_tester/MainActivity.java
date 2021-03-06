package com.example.anthony.android_api_tester;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import android.app.AlertDialog;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{

    Spinner methods_spinner;
    TextView host;
    TextView body;
    Button submit;
    String method_type;
    String host_name;
    String body_string;

    private AsyncHttpClient client;
    private RequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new AsyncHttpClient();
        params = new RequestParams();

        methods_spinner = (Spinner) findViewById(R.id.methods_spinner);
        host = (TextView) findViewById(R.id.host_name);
        body = (TextView) findViewById(R.id.request_body);
        submit = (Button) findViewById(R.id.submit_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.methods_array, android.R.layout.simple_spinner_item);
        methods_spinner.setAdapter(adapter);
        methods_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        method_type = (String) parent.getItemAtPosition(pos);

    }

    public void onNothingSelected(AdapterView<?> parent){

    }

    public void submitClicked(View target){
        String response = "";
        host_name = "http://skateipsum.com/get/1/1/text"; //host.getText().toString();
        body_string = body.getText().toString();
        SubmitHelper s = new SubmitHelper();
        Log.i("APP", "Doing a " + method_type);
        //response = s.execute("http://skateipsum.com/get/1/1/text", "", method_type);
        //response = s.execute(host_name, body_string, method_type);
        switch(method_type){
            case "GET":
                client.get(host_name, params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String s = new String(responseBody);
                        Log.i("SUBMITHELPER", "Returned: " + s);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getParent());
                        builder.setMessage(s);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.i("SUBMITHELPER", "FAILURE");
                    }
                });
                break;
            case "POST":
                client.post(host_name, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                break;
            default:
        }
    }
}

