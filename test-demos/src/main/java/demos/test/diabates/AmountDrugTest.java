package demos.test.diabates;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2023/5/11 21:45
 */
public class AmountDrugTest {
    public static void main(String[] args) {
        String path = "/Users/xiedong/IdeaProjects/java-learn-demos/amount_drug.json";
        String destiDataPath = "/Users/xiedong/IdeaProjects/java-learn-demos/amount_drug1.json";
        String fileContent = FileUtil.readString(path, Charset.defaultCharset());

        JSONObject jsonObject = JSON.parseObject(fileContent);
        List<String> datas = new ArrayList<>();
        for (String s : jsonObject.keySet()) {

            List<String> split = StrUtil.split(s, "|");
            String s1 = split.get(0);
            List<String> s1Ls = StrUtil.split(s1, ":");
            String ss1 = s1Ls.get(0);
            String ss2 = s1Ls.get(1);


//            String s2 = split.get(1);

            DiseaseTest.TrainData trainData = new DiseaseTest.TrainData();
            trainData.setIntent("amount_drug");
            trainData.setText(ss2 + "怎么吃，怎样的用药剂量？");
            trainData.setId(0l);
            Map<String, String> map = new HashMap<>();

            map.put("Drug", ss2);
            trainData.setSlots(map);
            datas.add(JSON.toJSONString(trainData, SerializerFeature.WriteMapNullValue));
        }

        FileUtil.appendUtf8Lines(datas, new File(destiDataPath));
    }
}
