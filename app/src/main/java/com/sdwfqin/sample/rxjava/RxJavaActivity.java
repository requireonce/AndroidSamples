package com.sdwfqin.sample.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.rxjava.rxjava1.RxJava1Activity;
import com.sdwfqin.sample.rxjava.rxjava2.RxJava2Activity;
import com.sdwfqin.sample.rxjava.rxjava3.RxJava3Activity;
import com.sdwfqin.sample.rxjava.rxjava4.RxJava4Activity;
import com.sdwfqin.sample.rxjava.rxjava5.RxJava5Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RxJavaActivity extends AppCompatActivity {

    @BindView(R.id.rx_java_list)
    ListView rxJavaList;

    private String[] title = new String[]{"基础入门", "线程调度", "Map与FlatMap",
            "zip", "Flowable"};
    private Class[] classes = new Class[]{RxJava1Activity.class, RxJava2Activity.class,
            RxJava3Activity.class, RxJava4Activity.class, RxJava5Activity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);

        rxJavaList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, title));

        rxJavaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(RxJavaActivity.this, classes[position]));
            }
        });
    }
}
