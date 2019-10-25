package com.tong.lib.net.retrofit.api;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by hjt on 2019/10/9.
 */

public interface ApiService {

    @GET("{url}")
    Observable<ResponseBody> executeGet(@Path(value = "url", encoded = true) String url);

    @GET("{url}")
    Observable<ResponseBody> executeGet(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, String> map);

    @GET("{url}")
    Observable<ResponseBody> executeGet(@Path(value = "url", encoded = true) String url, @Body RequestBody requestBody);

    @POST("{url}")
    Observable<ResponseBody> executePost(@Path(value = "url", encoded = true) String url);

    @POST("{url}")
    Observable<ResponseBody> executePost(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<ResponseBody> executePost(@Path(value = "url", encoded = true) String url, @Body RequestBody requestBody);

    @GET("{url}")
    Observable<ResponseBody> executeGetWithHeader(@HeaderMap Map<String, String> headMap, @Path(value = "url", encoded = true) String url);

    @GET("{url}")
    Observable<ResponseBody> executeGetWithHeader(@HeaderMap Map<String, String> headMap, @Path(value = "url", encoded = true) String url, @QueryMap Map<String, String> map);

    @GET("{url}")
    Observable<ResponseBody> executeGetWithHeader(@HeaderMap Map<String, String> headMap, @Path(value = "url", encoded = true) String url, @Body RequestBody requestBody);

    @POST("{url}")
    Observable<ResponseBody> executePostWithHeader(@HeaderMap Map<String, String> headMap, @Path(value = "url", encoded = true) String url);

    @POST("{url}")
    Observable<ResponseBody> executePostWithHeader(@HeaderMap Map<String, String> headMap, @Path(value = "url", encoded = true) String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<ResponseBody> executePostWithHeader(@HeaderMap Map<String, String> headMap, @Path(value = "url", encoded = true) String url, @Body RequestBody requestBody);

    @GET
    Observable<ResponseBody> executeGetUseUrl(@HeaderMap Map<String, String> headMap, @Url String url, @QueryMap Map<String, String> map);

    @POST
    Observable<ResponseBody> executePostUseUrl(@HeaderMap Map<String, String> headMap, @Url String url, @QueryMap Map<String, String> map);

    @POST
    Observable<ResponseBody> executePostUseUrl(@HeaderMap Map<String, String> headMap, @Url String url, @Body RequestBody requestBody);

    @Multipart
    @POST
    Observable<ResponseBody> executePostFile(@HeaderMap Map<String, String> headMap, @Url String url, @Part MultipartBody.Part part);

    @Streaming //大文件下载(如>10M)
    @GET
    Observable<ResponseBody> executeDownloadFile(@HeaderMap Map<String, String> headMap, @Url String url, @QueryMap Map<String, String> map);
}
