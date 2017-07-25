package indi.jackie.acmpu.p1003;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jackie chen
 * @create 2017/7/25
 * @description P1003
 */
public class P1003 {

    private static final Float FLOAT_ZERO = 0f;

    @Test
    public void p1003() {
        String data = "1.00\n" +
                "3.71\n" +
                "0.04\n" +
                "5.19\n" +
                "0.00";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner cin = new Scanner(System.in);
            List<Float> cinList = new ArrayList<>();
            Float temp;
            while (cin.hasNext()) {
                temp = cin.nextFloat();
                if (temp.equals(this.FLOAT_ZERO)) {
                    break;
                } else {
                    cinList.add(temp);
                }
            }
            if (cinList.size() > 0) {
                dealList(cinList);
            }
        } finally {
            System.setIn(stdin);
        }

    }

    /**
     * 处理P1003输入
     *
     * @param cinList
     */
    private void dealList(List<Float> cinList) {
        List<Integer> result = new ArrayList<>();
        Integer num;//记录所需卡片张数
        for (Float item :
                cinList) {
            //根据长度获取所需的卡片张数,张数为n-1
            num = getNumByLength(item) - 1;
            result.add(num);
        }
        //打印结果
        printResult(result);
    }

    /**
     * 打印结果
     *
     * @param result
     */
    private void printResult(List<Integer> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            System.out.println("\u0020card(s)");
        }
    }

    /**
     * 计算(1/2+1/3+1/4+1/5+...+1/n)>length时，n的值
     * 注意：张数为n-1
     *
     * @param length
     * @return
     */
    private Integer getNumByLength(Float length) {
        Integer i = 2;//分母
        Float sum = 0F;
        while (true) {
            sum += 1F / i;
            if (sum > length) {
                break;
            }
            i++;
        }
        return i;
    }
}
