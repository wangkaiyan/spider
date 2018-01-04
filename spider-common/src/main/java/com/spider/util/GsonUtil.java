package com.spider.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.Map;

public class GsonUtil {

    public static <T> T fromJson(String json, Class<T> clasz) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, clasz);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, typeOfT);
    }

    public static String toJson(Object src) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(src);
    }

    public static String toJsonSerializeNulls(Object src) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(src);
    }

    public static String get(String key, String snapshot) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(snapshot)) {
            return null;
        }
        Gson gson = new GsonBuilder().create();
        Map<String, String> map = null;
        try {
            map = gson.fromJson(snapshot, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        return map.get(key);
    }

    public static String put(String snapshot, String key, String value) {
        Gson gson = new GsonBuilder().create();
        Map<String, String> map = gson.fromJson(snapshot, new TypeToken<Map<String, Object>>() {
        }.getType());
        map.put(key, value);
        return gson.toJson(map);
    }

    public static Map<String, Object> jsonToMap(String jsonStr) {
        Gson gson = new Gson();
        Type type = new com.google.gson.reflect.TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> objMap = gson.fromJson(jsonStr, type);
        return objMap;
    }

    public static JSONObject stringToJSONOBject(String jsonStr) {
        return JSONObject.parseObject(jsonStr);
    }

    public static Map<String, Object> objToMap(Object src) {
        return jsonToMap(toJson(src));
    }

    public static void main(String[] args) {
//		Gson gson = new GsonBuilder().create();
//		List<Map<String, String>> list=new ArrayList();
//		Map<String, String> map=new HashMap();
//		map.put("key", "xxxxxxx");
//		map.put("value", "xxxxxxx");
//		Map<String, String> map2=new HashMap();
//		map2.put("key", "退货说明");
//		map2.put("value", "zzz");
//		list.add(map);
//		list.add(map2);
        String content = "{\"total\":68,\"count\":68,\"data\":{\"openid\":[\"oFOtTuAEOyqvx1uPQIm4LrFqRdiU\",\"oFOtTuKuop2XtLZe-RMdirW6IWvM\",\"oFOtTuFa4Yzs6uhTX4OAZTcZXbao\",\"oFOtTuB8JRu638EQyruVS55rUD3s\",\"oFOtTuKZA_H1wDPwl_Gxh_0VPk5c\",\"oFOtTuAn5EW41AMZg5Wu05qu-IfI\",\"oFOtTuMBX7xosmbivFjq2-yUpazc\",\"oFOtTuOYPqLdbda89uwz3VPVEruw\",\"oFOtTuAvO4uBeKkQBgwGA57H_NM8\",\"oFOtTuJ8mgp1BUcSXcbND1AAsT9M\",\"oFOtTuCx6zBTgzSuJYf-7MUVXX_E\",\"oFOtTuJNx5lCzDAJiGa-02GZHpRs\",\"oFOtTuDCLucdAf01g-A5CNh_P5fw\",\"oFOtTuLIhivtQM7BGBPFtCtipDIw\",\"oFOtTuCFke2OsyOtJnnCLmB6M8k4\",\"oFOtTuMxVoMz971_U7xv5PKDJqAs\",\"oFOtTuMQG615biXYLfvNY4LXJvF4\",\"oFOtTuGiqsTTOoTKgQV7y658bMpE\",\"oFOtTuBTPMVq7ocOlZTWIAoqBCLY\",\"oFOtTuO59OezL98n4FMAmRQmn-Tc\",\"oFOtTuLxLKWMA6m6DobnEta2ZZe0\",\"oFOtTuIL3-y58JbsQNIYCce89rxI\",\"oFOtTuCfIcz9k-QTEsKSG215_vFA\",\"oFOtTuAqHse1BmHU9iqAdsaUqWkk\",\"oFOtTuIOhikiKzb1R_tguTaxua9A\",\"oFOtTuOKdpri8GzBcYXIsocqk1CQ\",\"oFOtTuGmZZPiOieW0ZZR81D8kjfU\",\"oFOtTuLaS3zZEJsWT_LWwpy0D05A\",\"oFOtTuF2x35yBjzE2FiN4WLVSEi8\",\"oFOtTuEtNJGuq2WHtsTtiPqiSjaE\",\"oFOtTuNZn9iXN_8m-fqEzI0CGa90\",\"oFOtTuFZUtGDSjN0A6hLeVuIhZYc\",\"oFOtTuCuQspAszkaChE8cj8z47bM\",\"oFOtTuFVfw41WXUnONEHEiE8QCqU\",\"oFOtTuKGFUh-rKATJVMMDxDm3dg8\",\"oFOtTuNgGYE02zShuBFWwA7Xl0Zk\",\"oFOtTuIErFN-bQFiqoZjHT2zs4gc\",\"oFOtTuILE54AdwEwLZ8LyedE1xes\",\"oFOtTuHAB7zm-peBkC6hSKBdjolo\",\"oFOtTuIrLlPk8O09ApjGU8zEh7qI\",\"oFOtTuDEIn1trIIMTOObUy-zMaZ8\",\"oFOtTuGU_9uMF6Ev0RT_ZAvmJ-RM\",\"oFOtTuM6UTaV7ta5cs7Ujs9CrT1M\",\"oFOtTuNJtkP6vf1skV99wPktiWDI\",\"oFOtTuD1rhTc3Viip9CXfxNgjZwU\",\"oFOtTuPJ2X159Mtz9sWuSIR314Wg\",\"oFOtTuAmy_LRViolnXzBwAWoPsnI\",\"oFOtTuHwfovDRrbrlY5i2nleFbFs\",\"oFOtTuLSB0k6ycXvq9Y5R90wc6qM\",\"oFOtTuHpZQwFH69ME9gZzJ7qkM5Y\",\"oFOtTuHD1ma0AYeTzcVwK180qsXw\",\"oFOtTuAoDPGbdGgCWof3BajVC_lU\",\"oFOtTuMkrCFjpN0fqlqsrE3ry79c\",\"oFOtTuFKIdTr0_0GZYrPR10JdSiw\",\"oFOtTuMNyAoL9bHc-vX0bdGN12Ts\",\"oFOtTuBRAxfXDGfxHB0u7KJ3PiH4\",\"oFOtTuF5CPdIT9iz5dnc44zpSKxo\",\"oFOtTuGMWQQDkMf6TXxTBITYJd5I\",\"oFOtTuLBERadWWjUpbxCIbKwj23w\",\"oFOtTuGAJGb7xceyOGssPyh9wlfc\",\"oFOtTuMlBcBhHvAUggSwOPjPvBHg\",\"oFOtTuMbaGIMBSVAnuRS08d1x6Yc\",\"oFOtTuNYdbrQKcwZqZJ60ufYwuSs\",\"oFOtTuDavENAv0tzFaX6FotFvseQ\",\"oFOtTuA7154d1_cIzMP-Ag7XFtiA\",\"oFOtTuPjbVI2NnEcrrZl5_6CuiMA\",\"oFOtTuO0fDkxvJ_ma_fUd_dHViL8\",\"oFOtTuAxrKYfbGRLmcK4pqw4tmpU\"]},\"next_openid\":\"oFOtTuAxrKYfbGRLmcK4pqw4tmpU\"}";

        JSONObject dataJson = JSONObject.parseObject(content);
        JSONObject data = dataJson.getJSONObject("data");
        JSONArray openid = data.getJSONArray("openid");
        String info = openid.getString(0);
        String province = openid.getString(1);
        System.out.println(info + province);
    }
}
