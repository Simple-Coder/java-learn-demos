package org.example.tp.demo5;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        File file = new File("E:\\ideaProjects\\java-learn-demos\\boot-demos\\threadpool-demo\\src\\main\\java\\org\\example\\tp\\demo5\\seq.in");
        List<String> strings = FileUtil.readLines(file, Charset.defaultCharset());
//        List<Bean> result = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            Bean bean = new Bean();
            bean.setText(s);
            if (i >= 0 && i < 37) {
                bean.setIntent("greet");
            }

            if (i >= 37 && i < 52) {
                bean.setIntent("goodbye");
            }
            if (i >= 37 && i < 52) {
                bean.setIntent("goodbye");
            }
            if (i >= 52 && i < 71) {
                bean.setIntent("deny");
            }
            if (i >= 71 && i < 100) {
                bean.setIntent("isbot");
            }
            if (i >= 100 && i < 133) {
                bean.setIntent("accept");
            }
            if (i >= 133) {
                bean.setIntent("symptom_disease");
            }
            bean.setSlots(new JSONObject());
            result.add(JSON.toJSONString(bean));
        }
        FileUtil.writeLines(result, new File("aa.json"), Charset.defaultCharset());
        System.out.println("kkkk");
    }
}

@Data
class Bean {
    private String text;
    private String domain = "app";
    private String intent;
    private JSONObject slots;
}