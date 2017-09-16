package com.aal.sekihan.viewpagertest;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

/*
 * Created by sekihan on 2017/09/05.
 */

public class SyllabusUtil {
    HashMap<String, String> initPostParams;
    HashMap<String, String> postParams;

    View view;
    String keys = "";
    String html = "";
    CookieManager msCookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);

    enum State{
        BUSY,
        SEARCH_READY,
        SEARCH_FINISHED
    }

    private State state = State.BUSY;

    public SyllabusUtil(View view) {
        this.view = view;
    }

    public String getInitPostParams(){
        Iterator i = initPostParams.keySet().iterator();
        String key = i.next().toString();
        String params = "";
        if(!(key == "" || initPostParams.get(key) == ""))
            params += key + "=" + initPostParams.get(key);
        while(i.hasNext()){
            key = i.next().toString();
            if(key == "" || initPostParams.get(key) == "") continue;
            params += "&" + key + "=" + initPostParams.get(key);
        }
        return params;
    }

    public String initialize(){
        asyncNetworkTask("https://gs.okayama-u.ac.jp/campusweb/campussquare.do?_flowId=SYW4101100-flow&locale=ja_JP","",State.SEARCH_READY);
        return html;
    }

    public void printParams() {
        //Toast.makeText((Activity)view.getContext(),html,Toast.LENGTH_SHORT).show();
        Document doc = Jsoup.parse(html);
        Element form = doc.getElementById("jikanwariSearchForm");
        Elements inputs = form.select("input");
        Elements selects = form.select("select");

        //最低限必要なキー群を取得する（ _status, s_no, _eventId, _flowExecutionKey）
        initPostParams = new HashMap<String,String>();
        for(Element input : inputs){
            initPostParams.put(input.attr("name"),input.attr("value"));
        }
        for(Element select : selects){
            initPostParams.put(select.attr("name"),select.attr("value"));
        }
        postParams = new HashMap<String, String>(initPostParams);
    }

    public String gethtml(){
        return html;
    }


    public void setSearchParam(String key, String data){
        initPostParams.put(key,data);
    }

    public String search(){
        asyncNetworkTask("https://gs.okayama-u.ac.jp/campusweb/campussquare.do",getInitPostParams(),State.SEARCH_FINISHED);
        return html;
    }

    public CookieManager getCookieManager() {
        return msCookieManager;
    }

    public ArrayList<SyllabusListItem> getTable(){
        Document doc = Jsoup.parse(html);
        if(doc.select("tbody") == null) return null;
        Element body = doc.select("tbody").first();
        Elements rows = body.select("tr");
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        for(int i = 0; i < rows.size(); i++){
            Element row = rows.get(i);
            Elements cols = row.select("td");
            HashMap<String,String> item = new HashMap<>();
            item.put("code",cols.get(1).text());
            item.put("quarter",cols.get(2).text());
            item.put("day",cols.get(3).text());
            item.put("subNumber",cols.get(4).text());
            item.put("name",cols.get(5).text());
            item.put("teacher",cols.get(6).text());
            item.put("address",cols.get(7).toString());
            list.add(item);
        }

        String result = "";
        for(HashMap<String,String> map : list){
            String sub = "";
            for(String key : map.keySet()) {
                if(key.equals("address")) continue;
                sub += key + ":" + map.get(key) + ", ";
            }
            Log.d("getTableToKey",sub);
            result += sub + "\n";
        }

        ArrayList<SyllabusListItem> slist = new ArrayList<>();
        for(int i = 0; i < rows.size(); i++){
            Element row = rows.get(i);
            Elements cols = row.select("td");
            SyllabusListItem item = new SyllabusListItem();
            item.setCode(cols.get(1).text());
            item.setQuarter(cols.get(2).text());
            item.setDay(cols.get(3).text());
            item.setSubNumber(cols.get(4).text());
            item.setName(cols.get(5).text());
            item.setTeacher(cols.get(6).text());
            item.setAddress(cols.get(7).toString());
            slist.add(item);
        }

        return slist;

    }

    public State getState(){
        return state;
    }

    private void asyncNetworkTask(String url, String postParam, final State postState){
        state = State.BUSY;
        AsyncTask<String,Integer,String> task = new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                publishProgress(30);
                SystemClock.sleep(3000);
                publishProgress(60);
                StringBuilder builder = new StringBuilder();
                try{
                    CookieHandler.setDefault(getCookieManager());
                    URL url = new URL(params[0]);
                    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
                    con.setRequestMethod("POST");
                    con.setInstanceFollowRedirects(true);
                    con.setDoOutput(true);

                    OutputStream os = con.getOutputStream();
                    PrintStream ps = new PrintStream(os);
                    ps.print(params[1]);
                    ps.close();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
                    String line;
                    while((line = reader.readLine()) != null) {
                        builder.append(line);
                    }

                } catch (IOException e){
                    e.printStackTrace();
                }
                publishProgress(100);
                return builder.toString();
            }

            @Override
            protected void onPostExecute(String result) {
                html = result;
                state = postState;
            }

            @Override
            protected void onProgressUpdate(Integer... values)
            {
                Log.d("url",values[0].toString());
            }
        };
        task.execute(url,postParam);
    }
}
