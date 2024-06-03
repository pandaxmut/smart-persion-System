
import okhttp3.*;
import org.json.JSONObject;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.net.URLEncoder;

class Sample {

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public static void main(String []args) throws IOException{
        String filePath = "C:\\Users\\orange\\Pictures\\Camera Roll\\WIN_20240521_16_03_54_Pro.jpg";

        MediaType mediaType = MediaType.parse("application/json");
        // image 可以通过 getFileContentAsBase64("C:\fakepath\WIN_20240521_16_03_54_Pro.jpg") 方法获取,如果Content-Type是application/x-www-form-urlencoded时,第二个参数传true
        RequestBody body = RequestBody.create(mediaType, "{\"group_id_list\":\"smart_test\",\"image\":\"" +
                        getFileContentAsBase64(filePath,false) +
                        "\",\"image_type\":\"BASE64\"}");

        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=24.97be67986f9c1d737581e058cb0252ed.2592000.1719392898.282335-72905880")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println(response.body().string());

    }

    /**
     * 获取文件base64编码
     *
     * @param path      文件路径
     * @param urlEncode 如果Content-Type是application/x-www-form-urlencoded时,传true
     * @return base64编码信息，不带文件头
     * @throws IOException IO异常
     */
    static String getFileContentAsBase64(String path, boolean urlEncode) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(path));
        String base64 = Base64.getEncoder().encodeToString(b);
        if (urlEncode) {
            base64 = URLEncoder.encode(base64, "utf-8");
        }
        return base64;
    }
}
