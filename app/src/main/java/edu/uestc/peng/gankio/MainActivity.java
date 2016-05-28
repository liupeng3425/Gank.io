package edu.uestc.peng.gankio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.uestc.peng.gankio.adapter.NewsAdapter;
import edu.uestc.peng.gankio.item.News;
import edu.uestc.peng.gankio.item.ServerResponse;
import edu.uestc.peng.gankio.view.DividerLine;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    //    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

//        layoutManager = new LinearLayoutManager(MainActivity.this);

        final NewsAdapter newsAdapter = new NewsAdapter();
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                newsAdapter.setData((ArrayList<News>) msg.obj);
                System.out.println("on handle message");
            }
        };

        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request mRequest = new Request.Builder().url("http://gank.io/api/data/Android/10/1").build();
        final Call mCall = mOkHttpClient.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(recyclerView, "request failed", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("on response");
                String json = response.body().string();
                Gson gson = new Gson();
                ServerResponse serverResponse = gson.fromJson(json, ServerResponse.class);
                ArrayList<News> newsList = serverResponse.getResults();
                Message message = Message.obtain();
                message.obj = newsList;
                handler.sendMessage(message);
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                Snackbar.make(v, ((TextView) v).getText().toString() + position, Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ViewNewsActivity.class);
                intent.putExtra("url", newsAdapter.getData().get(position).getUrl());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(newsAdapter);
//        recyclerView.addItemDecoration(new DividerLine());
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }

    }
}
