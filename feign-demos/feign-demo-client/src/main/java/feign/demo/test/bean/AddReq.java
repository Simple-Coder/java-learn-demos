package feign.demo.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/7/12 21:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddReq implements Serializable {
    private int a;
    private int b;
}
