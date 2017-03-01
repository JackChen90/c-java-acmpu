package indi.jackie.acmpu.p1001;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author jackie chen
 * @create 2017-03-01
 * @description Exponentiation
 */
public class P1001 {
    @Test
    public void p1001() {
        String data = "95.123 12\n" +
                "0.4321 20\n" +
                "5.1234 15\n" +
                "6.7592  9\n" +
                "98.999 10\n" +
                "1.0100 12\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner cin = new Scanner(System.in);
            while (cin.hasNext()) {
                BigDecimal r = cin.nextBigDecimal();
                int n = cin.nextInt();
                r = r.pow(n).stripTrailingZeros();//去掉小数点后面的零
                String m_string = r.toPlainString();//不带指数段的字符串表示形式
                if (m_string.charAt(0) == '0')
                    m_string = m_string.substring(1);
                System.out.println(m_string);
            }
        } finally {
            System.setIn(stdin);
        }
    }
}
