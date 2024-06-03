package com.edu.smartpersionsys.utils;


import okhttp3.*;
import java.io.*;
public class FaceUtil {
    public FaceUtil() {
    }
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public String face ( String name, String faceImgBase64 ) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        // image 可以通过 getFileContentAsBase64("C:\fakepath\WIN_20240521_16_03_54_Pro.jpg") 方法获取,如果Content-Type是application/x-www-form-urlencoded时,第二个参数传true
        RequestBody body = RequestBody.create(mediaType, "{\"group_id_list\":\"smart_test\",\"image\":\"" +
                faceImgBase64 +
                "\",\"image_type\":\"BASE64\"}");

        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=24.97be67986f9c1d737581e058cb0252ed.2592000.1719392898.282335-72905880")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        String jsonData= response.body().string();
        System.out.println(jsonData);
        return jsonData;
    }
}
//
//{
//        "error_code": 0,
//        "error_msg": "SUCCESS",
//        "log_id": 786107967,
//        "timestamp": 1716952386,
//        "cached": 0,
//        "result": {
//        "face_token": "6f2a7cffa444d88b0eda5e7f13a3bfed",
//        "user_list": [
//        {
//        "group_id": "smart_test",
//        "user_id": "lin",
//        "user_info": "",
//        "score": 33.822971343994
//        }
//        ]
//        }
//        }