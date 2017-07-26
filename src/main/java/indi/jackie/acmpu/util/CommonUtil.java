package indi.jackie.acmpu.util;

import java.util.List;

/**
 * @author jackie chen
 * @create 2017/7/25
 * @description CommonUtil
 */
public class CommonUtil {

    /**
     * 批量输出列表结果
     *
     * @param result
     * @param <T>
     */
    public static <T> void printListResult(List<T> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
