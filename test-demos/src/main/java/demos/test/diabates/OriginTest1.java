package demos.test.diabates;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2023/5/27 12:57

 #!/bin/bash

 source_file="$1"  # 通过第一个输入参数指定包含 traceId 的文件路径
 output_file="$2"  # 通过第二个输入参数指定输出文件路径
 shift 2  # 将输入参数左移，忽略前两个参数

 while IFS= read -r trace_id; do
 for target_file in "$@"; do
 if [ "$target_file" != "$output_file" ]; then
 grep "$trace_id" "$target_file" | awk -F'feedInfo:' '{print $2}'
 fi
 done
 done < "$source_file" | sort -u | awk '{ORS="|"; print}' >> "$output_file"


 ./extract_info.sh source.log /result.txt /data/java-feed-service/logs/otherdays/stdout.2023-06-12.*





 */
public class OriginTest1 {
    public static void main(String[] args) {
        String path = "/Users/xiedong/IdeaProjects/java-learn-demos/test-demos/src/main/java/demos/test/diabates/result.txt";
        String path1 = "/Users/xiedong/IdeaProjects/java-learn-demos/test-demos/src/main/java/demos/test/diabates/result1.txt";


        Map<String, String> map = new HashMap<>();
        String fileContent = FileUtil.readString(path, Charset.defaultCharset());
        List<String> split = StrUtil.split(fileContent, "|");
        List<String> result = new ArrayList<>();
        for (String s : split) {
            if (JSONUtil.isJson(s)) {
                JSONObject json = JSON.parseObject(s);
                String id = json.getString("id");
                if (!map.containsKey(id)) {
                    map.put(id, id);
                    String userId = json.getString("userId");
                    String passport = json.getString("passport");
                    int type = json.getIntValue("type");
                    String sourceId = json.getString("sourceId");

                    int finalType = 0;
                    if (type == 1) {
                        //视频
                        System.out.println("type==1");
                        finalType = 0;
                    } else {
                        //帖子
                        finalType = 1;
                        System.out.println("type==4");
                    }

                    String res = userId + StrUtil.COMMA + passport + StrUtil.COMMA + finalType + StrUtil.COMMA + sourceId;

                    result.add(res);
                }
            }
        }

        FileUtil.writeLines(result, new File(path1), Charset.defaultCharset());
        System.out.println();


    }
}
