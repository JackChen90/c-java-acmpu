package indi.jackie.acmpu.p1010;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jackie chen
 * @create 2017/8/15
 * @description P1010 STAMPS
 */
public class P1010 {

    @Test
    public void p1010() {
        String data = "4 4 3 2 1 0\n" +
                "1 9 10 11 15 20 0\n" +
                "4 3 2 1 1 0\n" +
                "1 9 10 11 15 20 0";
        InputStream in = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner sc = new Scanner(System.in);
            Boolean isStampData = true;//输入数据是否为邮票
            List<String> stampList = new ArrayList<>();
            List<String> customerList = new ArrayList<>();
            while (sc.hasNext()) {
                if (isStampData) {
                    stampList.add(sc.nextLine());
                    isStampData = !isStampData;
                } else {
                    customerList.add(sc.nextLine());
                    isStampData = !isStampData;
                }
            }
            dealList(stampList,customerList);
        } finally {
            System.setIn(in);
        }
    }

    private void dealList(List<String> stampList, List<String> customerList) {

    }
}
