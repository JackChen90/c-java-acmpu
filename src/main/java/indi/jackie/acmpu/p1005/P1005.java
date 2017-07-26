package indi.jackie.acmpu.p1005;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jackie chen
 * @create 2017/7/25
 * @description P1005
 */
public class P1005 {

    private static final Integer SHRINK_SPEED = 50;//淹没速度
    private static final Float PI = 3.14F;//圆周率

    @Test
    public void p1005() {
        String data = "2\n" +
                "1.0 1.0\n" +
                "25.0 0.0";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner sin = new Scanner(System.in);
            Integer index = 0;
            List<Float> cinList = new ArrayList<>();
            if (sin.hasNext()) {
                int sum = sin.nextInt();
                while (sin.hasNext() && index++ < sum * 2) {
                    cinList.add(sin.nextFloat());
                }
                //处理输入列表
                dealList(cinList);
            }
        } finally {
            System.setIn(stdin);
        }
    }

    /**
     * 处理输入数据
     *
     * @param cinList
     */
    private void dealList(List<Float> cinList) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < cinList.size() / 2; i++) {
            Float x = cinList.get(2 * i);
            Float y = cinList.get(2 * i + 1);
            //根据坐标查询被淹所需年数
            result.add(getYear(x, y));
        }
        //输出结果
        printResult(result);
    }

    /**
     * 输出结果
     *
     * @param result
     */
    private void printResult(List<Integer> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Property " + (i + 1) + ": This property will begin eroding in year " + result.get(i) + ".");
        }
    }

    /**
     * 根据坐标查询被淹的所需年数
     *
     * @param x x轴
     * @param y y轴
     * @return 年数
     */
    private Integer getYear(Float x, Float y) {
        return new Double(this.PI * (x * x + y * y) / (2 * this.SHRINK_SPEED)).intValue() + 1;
    }
}
