package com.nett.play_console;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.nett.play_console.jank.Item;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class JankMain {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        File jsonFile = null;
        if (args == null || args.length == 0) {
            System.err.println("可以直接传入json文件路径");
        } else {
            String jsonPath = args[0];
            if (jsonPath == null || jsonPath.length() == 0) {
                System.err.println("参数无效");
            } else {
                File localFile = new File(jsonPath);
                if (!localFile.exists()) {
                    System.err.println("指定文件不存在");
                } else {
                    jsonFile = localFile;
                }
            }
        }

        if (jsonFile == null) {
            System.err.println("未指定文件，解析默认路径");
        } else if (jsonFile.exists()) {
            System.out.println("-------解析：" + jsonFile + "--------");
            parseJsonFile(jsonFile);
            System.out.println("----------------------");
            return;
        }
        System.out.println("----------------------");
        parseFromRes(7);

        System.out.println("-------30天数据--------");
        jsonFile = new File("res/test-30day.json");
        if (jsonFile.exists()) {
            parseJsonFile(jsonFile);
        } else {
            System.out.println("30天数据不存在");
        }
        System.out.println("----------------------");
        System.out.println();
        System.out.println("-------90天数据--------");
        jsonFile = new File("res/test-90day.json");
        if (jsonFile.exists()) {
            parseJsonFile(jsonFile);
        } else {
            System.out.println("90天数据不存在");
        }
        System.out.println("----------------------");
    }

    private static void parseFromRes(int nDay) {
        try {
            File jsonFile;
            System.out.println();
            System.out.println("-------" + nDay + "天数据--------");
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL resource = classloader.getResource("test-" + nDay + "day.json");
            if (resource == null) {
                System.out.println("resource = null");
                return;
            }
            jsonFile = new File(resource.getPath());
            if (jsonFile.exists()) {
                parseJsonFile(jsonFile);
            } else {
                System.out.println(nDay + "天数据不存在");
            }
            System.out.println("----------------------");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseJsonFile(File file) {
        JsonReader jsonReader = null;
        try {
            jsonReader = new JsonReader(new FileReader(file));
            jsonReader.setLenient(true);
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                JsonToken peek = jsonReader.peek();
                if (peek == JsonToken.BEGIN_OBJECT && "13".equals(nextName)) {
                    jsonReader.beginObject();
                    String _13Name = jsonReader.nextName();
                    JsonToken _13token = jsonReader.peek();
                    if (_13token == JsonToken.BEGIN_ARRAY && "1".equals(_13Name)) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            Item item = gson.fromJson(jsonReader, Item.class);
                            System.out.println(item.toString());
                        }
                        jsonReader.endArray();
                    }
                    break;
                } else {
                    jsonReader.skipValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jsonReader != null) {
                try {
                    jsonReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
