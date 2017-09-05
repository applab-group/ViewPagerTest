package com.aal.sekihan.viewpagertest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sekihan on 2017/09/04.
 */

public class AsyncNetworkTask extends AsyncTask<String, Integer, String>{
    private TextView txtResult;
    private ProgressBar progress;


    public AsyncNetworkTask(Context context){
        super();
        MainActivity activity = (MainActivity) context;
        txtResult = (TextView)activity.findViewById(R.id.txtResult);
        progress = (ProgressBar)activity.findViewById(R.id.progress);
    }

    public AsyncNetworkTask(View view){
        super();
        txtResult = (TextView) view.findViewById(R.id.txtResult);
        progress = (ProgressBar)view.findViewById(R.id.progress);
    }

    @Override
    protected String doInBackground(String... params) {
        publishProgress(30);
        SystemClock.sleep(3000);
        publishProgress(60);
        StringBuilder builder = new StringBuilder();
        try{
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line);
            }
            //String content = builder.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        publishProgress(100);
        return builder.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        txtResult.setText(result);
        progress.setVisibility(ProgressBar.GONE);
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        Log.d("url",values[0].toString());
        progress.setProgress(values[0]);
    }

    @Override
    protected void onCancelled() {
        txtResult.setText("キャンセルされました。");
        progress.setVisibility(ProgressBar.GONE);
    }

    @Override
    protected void onPreExecute(){
        progress.setVisibility(ProgressBar.VISIBLE);
    }
}
