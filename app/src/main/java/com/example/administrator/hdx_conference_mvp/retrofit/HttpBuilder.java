package com.example.administrator.hdx_conference_mvp.retrofit;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.administrator.hdx_conference_mvp.retrofit.interfaces.Fair;
import com.example.administrator.hdx_conference_mvp.retrofit.interfaces.Progress;
import com.example.administrator.hdx_conference_mvp.retrofit.interfaces.Success;
import com.example.administrator.hdx_conference_mvp.retrofit.utils.NetUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.administrator.hdx_conference_mvp.retrofit.HttpUtil.checkHeaders;
import static com.example.administrator.hdx_conference_mvp.retrofit.HttpUtil.checkParams;
import static com.example.administrator.hdx_conference_mvp.retrofit.HttpUtil.putCall;


/**
 * Created by 戴尔 on 2017/11/9.
 */

public class HttpBuilder {
    Map<String, String> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    String url;
    String path;
    Fair mErrorCallBack;
    Success mSuccessCallBack;
    Progress mProgressListener;
    Object tag;
    Context mContext;
    boolean checkNetConnected = false;

    private String body;



    public HttpBuilder(@NonNull String url) {
        this.setParams(url);
    }

    public HttpBuilder() {

    }

    private void setParams(String url) {
        if (HttpUtil.getmInstance() == null) {
            throw new NullPointerException("HttpUtil has not be initialized");
        }
        this.url = url;
        this.params = new HashMap<>();
        this.mErrorCallBack = new Fair() {
            @Override
            public void Error(Object... values) {

            }
        };
        this.mSuccessCallBack = new Success() {
            @Override
            public void Success(String model) {

            }
        };
        this.mProgressListener = new Progress() {
            @Override
            public void progress(float p) {

            }
        };
    }

    /**
     * 是否允许缓存，传入时间如：1*3600 代表一小时缓存时效
     *
     * @param time 缓存时间 单位：秒
     * @author gengqiquan
     * @date 2017/3/25 下午3:27
     */
    public HttpBuilder cacheTime(int time) {
        header("Cache-Time", time + "");
        return this;
    }

    public HttpBuilder path(@NonNull String path) {
        this.path = path;
        return this;
    }

    public HttpBuilder tag(@NonNull Object tag) {
        this.tag = tag;
        return this;
    }
    public HttpBuilder setBody(String body) {
        this.body = body;
        return this;
    }
    public HttpBuilder params() {
        this.params.putAll(params);
        return this;
    }

    public HttpBuilder params(@NonNull String key, String value) {
        this.params.put(key, value);
        return this;
    }

    public HttpBuilder headers(@NonNull Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public HttpBuilder header(@NonNull String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    @CheckResult
    public HttpBuilder success(@NonNull Success success) {
        this.mSuccessCallBack = success;
        return this;
    }

    public HttpBuilder progress(@NonNull Progress progress) {
        this.mProgressListener = progress;
        return this;
    }

    @CheckResult
    public HttpBuilder error(@NonNull Fair error) {
        this.mErrorCallBack = error;
        return this;
    }

    /**
     * 检查网络是否连接，未连接跳转到网络设置界面
     *
     * @author gengqiquan
     * @date 2017/3/25 下午3:27
     */
    public HttpBuilder isConnected(@NonNull Context context) {
        checkNetConnected = true;
        mContext = context;
        return this;
    }




    @CheckResult
    private String checkUrl(String url) {
        if (HttpUtil.checkNULL(url)) {
            throw new NullPointerException("absolute url can not be empty");
        }
        return url;
    }

    @CheckResult
    public String message(String mes) {
        if (HttpUtil.checkNULL(mes)) {
            mes = "服务器异常，请稍后再试";
        }

        if (mes.equals("timeout") || mes.equals("SSL handshake timed out")) {
            return "网络请求超时";
        } else {
            return mes;
        }

    }

    /**
     * 请求前初始检查
     *
     * @author gengqiquan
     * @date 2017/3/25 下午4:12
     */
    boolean allready() {
        if (!checkNetConnected || mContext == null) {
            return true;
        }
        if (!NetUtils.isConnected(mContext)) {
            Toast.makeText(mContext, "检测到网络已关闭，请先打开网络", Toast.LENGTH_SHORT).show();
            NetUtils.openSetting(mContext);//跳转到网络设置界面
            return false;
        }
        return true;
    }





    /*public Observable<ResponseBody> Obdownload() {
        this.url = checkUrl(this.url);
        this.params = checkParams(this.params);
        this.headers.put(Constant.DOWNLOAD, Constant.DOWNLOAD);
        this.headers.put(Constant.DOWNLOAD_URL, this.url);
        return HttpUtil.getService().download(checkHeaders(headers), url, checkParams(params));
    }
*/

    //get请求
    public void get() {
        if (!allready()) {
            return;
        }

        Call call = HttpUtil.getService().get(checkUrl(this.url), checkParams(params), checkHeaders(headers));
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else {
                    mErrorCallBack.Error(message(response.message()));
                }
                if (tag != null)
                    HttpUtil.removeCall(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                mErrorCallBack.Error(message(t.getMessage()));
                if (tag != null)
                    HttpUtil.removeCall(url);
            }
        });
    }


    //post请求
    public void post() {
        if (!allready()) {
            return;
        }
        Call call = HttpUtil.getService().post(checkUrl(this.url), checkParams(params), checkHeaders(headers));
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else {
                    mErrorCallBack.Error(message(response.message()));
                }
                if (tag != null)
                    HttpUtil.removeCall(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                mErrorCallBack.Error(message(t.getMessage()));
                if (tag != null)
                    HttpUtil.removeCall(url);
            }
        });
    }





   /* @CheckResult
    public Observable<String> obget() {
        if (!allready()) {
            return null;
        }
        Observable call = HttpUtil.getService().post2(checkUrl(this.url), checkParams(params), checkHeaders(headers));

    }


*//* *//**//*   @CheckResult
    public Observable<String> Obpost() {
        return HttpUtil.getService().post(checkUrl(this.url), checkParams(params), checkHeaders(headers));
    }
*//*
    @CheckResult
    public Observable<String> Obput() {
        return HttpUtil.getService().put(checkUrl(this.url), checkParams(params), checkHeaders(headers))
                ;
    }*/




    //文件上传 ，参数为文件路径名称
    public void upLoadImg(ArrayList<String> media) {
        if (media == null) {
            return ;
        }

        MultipartBody.Part[] parts = new MultipartBody.Part[media.size()];
        int cnt = 0;
        for (String m : media) {
            File file = new File(m);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("headimg[]", file.getName(), requestFile);
            parts[cnt] = filePart;
            cnt++;
        }
        Call call = HttpUtil.getService().upLoadImg(checkUrl(this.url), parts, checkHeaders(headers));
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else {
                    mErrorCallBack.Error(message(response.message()));
                }
                if (tag != null)
                    HttpUtil.removeCall(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                mErrorCallBack.Error(message(t.getMessage()));
                if (tag != null)
                    HttpUtil.removeCall(url);
            }
        });


    }





    //下载
    public void download() {
        this.url = checkUrl(this.url);
        this.params = checkParams(this.params);
        this.headers.put(Constant.DOWNLOAD, Constant.DOWNLOAD);
        this.headers.put(Constant.DOWNLOAD_URL, this.url);
        Call call = HttpUtil.getService().download(checkHeaders(headers), url, checkParams(params));
        putCall(tag, url, call);
        Observable<ResponseBody> observable = Observable.create(new ObservableOnSubscribe<ResponseBody>() {
            @Override
            public void subscribe(ObservableEmitter<ResponseBody> e) throws Exception {

            }
        });

        observable.observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                               @Override
                               public void onSubscribe(Disposable d) {

                               }

                               @Override
                               public void onNext(ResponseBody value) {

                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }




}