package indi.jackie.acmpu.p1000;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author jackie chen
 * @create 2017-03-01
 * @description A+B Problem
 */
public class P1000 {

    @Test
    public void p1000() {
        String data = "1\n2\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner cin = new Scanner(System.in);
            int a = cin.nextInt();
            int b = cin.nextInt();
            System.out.println(a + b);
        } finally {
            System.setIn(stdin);
        }
    }
}
