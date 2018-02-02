package com.sdwfqin.sample.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.sdwfqin.sample.R;
import com.sdwfqin.sample.retrofit.api.RequestPostApi;
import com.sdwfqin.sample.retrofit.model.RequestModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 描述：Post请求
 *
 * @author zhangqin
 */
public class Retrofit3Activity extends AppCompatActivity {

    @BindView(R.id.retrofit3_btn_post)
    Button mRetrofit3BtnPost;
    @BindView(R.id.retrofit3_tv)
    TextView mRetrofit3Tv;

    private Retrofit mRetrofit;
    private RequestPostApi searchApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit3);
        ButterKnife.bind(this);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://test.sdwfqin.com/")
                .addConverterFactory(GsonConverterFactory.create())
                //RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        searchApi = mRetrofit.create(RequestPostApi.class);

        mRetrofit3BtnPost.setOnClickListener(v -> {
            // 调用请求方法，并得到Observable实例
            Observable<RequestModel> observable = searchApi.PostData("码农Mrz", "www.sdwfqin.com");
            //在io线程进行网络请求
            observable.subscribeOn(Schedulers.io())
                    // 在主线程处理返回的数据
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RequestModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            LogUtils.e("onSubscribe: ");
                        }

                        @Override
                        public void onNext(@NonNull RequestModel postModel) {
                            LogUtils.e("onNext: " + postModel.toString());
                            setText(postModel.toString());
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            LogUtils.e("onError: ", e);
                        }

                        @Override
                        public void onComplete() {
                            LogUtils.e("onComplete: " + "所有事件完成");
                        }
                    });
        });
    }

    /**
     * 设置TextView
     *
     * @param s
     */
    private void setText(String s) {
        try {
            mRetrofit3Tv.setText(s);
        } catch (Exception e) {
            LogUtils.e("setText: ", e);
        }
    }
}
