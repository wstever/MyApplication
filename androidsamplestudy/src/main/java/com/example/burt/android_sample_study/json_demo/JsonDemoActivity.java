package com.example.burt.android_sample_study.json_demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.burt.android_sample_study.R;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonDemoActivity extends Activity {
    //访问路径
    private static final String BASEPATH = "http://192.168.1.49:8080";
    private static final String PATH = "http://192.168.1.49:8080/JsonDemo/servlet/GetDataServlet";

    // ListView
    private ListView mListView;
    private List<Map<String, String>> mList = new ArrayList<>();
    private Map<String, String> mMap;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsondemo_main);

        mListView = (ListView) findViewById(R.id.lv);
        // 得到一个RequestQueue对象
        mRequestQueue = Volley.newRequestQueue(this);
        myAsyncTask task = new myAsyncTask();
        task.execute();// 异步线程执行
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
    }

    // MyAdapter继承BaseAdapter用于listview和数据的绑定
    public class MyAdapter extends BaseAdapter {
        public int getCount() {
            return mList.size();
        }

        public Object getItem(int position) {
            return mList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup arg2) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(JsonDemoActivity.this).inflate(
                        R.layout.jsondemo_items, null);
                // 获得ViewHolder对象避免多次findView
                holder = new ViewHolder();
                holder.mImageView = (ImageView) convertView
                        .findViewById(R.id.iv);
                holder.mTextView = (TextView) convertView
                        .findViewById(R.id.tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            // 获得图片并显示在mImageView中
            ImageRequest request = new ImageRequest(BASEPATH
                    + mList.get(position).get("image"), new Listener<Bitmap>() {

                @Override
                public void onResponse(Bitmap response) {
                    holder.mImageView.setImageBitmap(response);
                }
            }, 0, 0, Config.ARGB_8888, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(getApplicationContext(), "error getting pic ", Toast.LENGTH_SHORT).show();
                }
            });
            mRequestQueue.add(request);
            holder.mTextView.setText(mList.get(position).get("name"));
            return convertView;
        }

        public class ViewHolder {
            ImageView mImageView;
            TextView mTextView;
        }
    }

    // myAsyncTask继承AsyncTask
    public class myAsyncTask extends AsyncTask<Void, Void, String> {
        // 用于异步下载数据
        protected String doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            String result = "";
            try {
                HttpGet get = new HttpGet(new URI(PATH));
                result = result.trim();
                HttpClient client = new DefaultHttpClient();
                HttpResponse response = client.execute(get);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = EntityUtils.toString(response.getEntity());
                    // 从后台获得的数据去掉空格
                    Log.v("++++++++++++", result);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return result;
        }

        // 解析result（json格式的字符串）并将之存入到list中
        protected void onPostExecute(String result) {
            String name;
            try {
                JSONArray array = new JSONObject(result).getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    Iterator<?> iterator = object.keys();
                    mMap = new HashMap<>();
                    while (iterator.hasNext()) {
                        name = iterator.next().toString();
                        mMap.put(name, object.getString(name));
                    }
                    mList.add(mMap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
}
