package retorfit;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * @author heshufan
 * @date 2021-01-19
 */
public interface GetRequest_Interface {

    @GET("translate?q=apple&from=en&to=zh&appid=2015063000000001&salt=1435660288&sign=f89f9594663708c1605f3d736d01d2d4")
    Call<Translation> getCall();

    @GET("translate?q")
    Call<Translation> getCalls(@Query("q") String a, @Query("from") String from, @Query("to") String to,
                               @Query("appid") String appid, @Query("salt") String salt, @Query("sign") String sign);

    @POST("/form")
    Call<ResponseBody> testFileUpload(@Body MultipartBody multipartBody);


    /**
     * 上传多张图片
     * @param images
     * @return
     */
    @Multipart
    @POST("/form")
    Call<ResponseBody> testFilesUpload(@PartMap Map<String,RequestBody> images);
    /**
     * 1、创建RequestBody
     * 2、创建Part
     * 3、然后将
     * @param multipartBody
     * @return
     */
    @POST("/form")
    @Multipart
    Call<ResponseBody> testFileUpload2(@Part MultipartBody.Part multipartBody);


    /**
     * 表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
     */
    @POST("/form")
    @FormUrlEncoded
    Call<ResponseBody> testFormUrlEncoded1(@Field("username") String name, @Field("age") int age);

    @GET("/")
    Call<String> cate(@Query("cate") String cate);


    /**
     * 普通post请求 表单提交
     *
     * @param name
     * @return
     */
    @FormUrlEncoded
    @POST("w/index.php")
    Call<String>
    postForm(@Field("search") String name);

    /**
     * 普通post请求 表单提交多参数
     *
     * @return
     */
    @FormUrlEncoded
    @POST("w/index.php")
    Call<String> postForm(@FieldMap Map<String, String> map);


}
