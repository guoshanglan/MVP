package com.example.administrator.hdx_conference_mvp.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


/**
 * Created by Administrator on 2018/8/27.
 *
 * 封装网络请求的方式，
 */

public interface apiservice {


    //get请求
    @GET()
    Call<String> get(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    //post请求，一般会带这两个注解  参数说明： url ：接口地址  map：参数集合 ，header：需要添加的请求头
    @FormUrlEncoded
    @POST()
    Call<String> post(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @Streaming
    @GET()
    Call<ResponseBody> download(@HeaderMap Map<String, String> headers, @Url String url, @QueryMap Map<String, String> params);



    //多文件上传，例如图片等  参数： url：路径名称  part:文件集合
    @POST()
    @Multipart
    Call<String> upLoadImg(@Url String Url, @Part MultipartBody.Part[] parts , @HeaderMap Map<String,String>header);



    //get请求
    @GET
    Observable<String> obget(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    //post请求，一般会带这两个注解  参数说明： url ：接口地址  map：参数集合 ，header：需要添加的请求头
    @FormUrlEncoded
    @POST()
    Observable<String> obpost(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);


    @FormUrlEncoded
    @PUT
    Observable<String> obput(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @Streaming
    @GET()
    Observable<String> obdownload(@HeaderMap Map<String, String> headers, @Url String url, @QueryMap Map<String, String> params);







}
