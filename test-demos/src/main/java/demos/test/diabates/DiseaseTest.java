package demos.test.diabates;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiedong
 * 2023/5/10 16:56
 */
public class DiseaseTest {
    public static void main(String[] args) {
        String path = "D:\\dev\\IdeaProjects\\java-learn-demos\\b.json";
        String destiDataPath = "D:\\dev\\IdeaProjects\\java-learn-demos\\c.json";

        String fileContent = FileUtil.readString(path, Charset.defaultCharset());

        JSONObject jsonObject = JSON.parseObject(fileContent);
        List<String> datas = new ArrayList<>();
        for (String s : jsonObject.keySet()) {
            TrainData trainData = new TrainData();
            trainData.setIntent("others");
            trainData.setText(s + "怎么治疗？");
            trainData.setId(0l);
            datas.add(JSON.toJSONString(trainData, SerializerFeature.WriteMapNullValue));
        }
        FileUtil.appendUtf8Lines(datas, new File(destiDataPath));
    }

    @Data
    public static class TrainData {
        private long id;
        private String text;
        private String intent;
        private String slots;
    }
}


