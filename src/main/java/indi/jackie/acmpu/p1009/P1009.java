package indi.jackie.acmpu.p1009;

import indi.jackie.acmpu.util.CommonUtil;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @author jackie chen
 * @create 2017/7/31
 * @description P1009
 */
public class P1009 {

    private String[] tempArray = new String[2];
    private List<String[]> tempList = new ArrayList<>();

//    @Test
//    public void p1009() {
//
//        String data = "7\n" +
//                "15 4\n" +
//                "100 15\n" +
//                "25 2\n" +
//                "175 2\n" +
//                "25 5\n" +
//                "175 2\n" +
//                "25 5\n" +
//                "0 0\n" +
//                "10\n" +
//                "35 500000000\n" +
//                "200 500000000\n" +
//                "0 0\n" +
//                "3\n" +
//                "255 1\n" +
//                "10 1\n" +
//                "255 2\n" +
//                "10 1\n" +
//                "255 2\n" +
//                "10 1\n" +
//                "255 1\n" +
//                "0 0\n" +
//                "0";
//        InputStream stdin = System.in;
//
//        try {
//            System.setIn(new ByteArrayInputStream(data.getBytes()));
//            Scanner cin = new Scanner(System.in);
//            Integer columnCount;
//            List<ImageDto> images = new ArrayList<>();
//            if (cin.hasNext()) {
//                columnCount = Integer.parseInt(cin.nextLine());//第一条为第一个地图的列数
//                String temp;
//                while (cin.hasNext()) {
//                    temp = cin.nextLine();
//                    this.tempArray = temp.split("\u0020");
//                    if (tempArray.length == 1) {
//                        if (tempArray[0].equals("0")) {//一个0，输入结束
//                            break;
//                        } else {//不是0，则为新地图列数
//                            columnCount = Integer.valueOf(tempArray[0]);
//                            continue;
//                        }
//                    } else if (tempArray[0].equals("0") && tempArray[1].equals("0")) {//两个0，单个地图数据结束
//                        //将输入数据转为地图数据
//                        ImageDto imageDto = convertToImage(this.tempList, columnCount);
//                        //插入地图列表
//                        images.add(imageDto);
//                        this.tempList = new ArrayList<>();
//                    } else {//记录输入数据
//                        this.tempList.add(this.tempArray);
//                    }
//                }
//                //处理输入列表
//                dealList(images);
//            }
//        } finally {
//            System.setIn(stdin);
//        }
//    }

    /**
     * 输入的数据转为图信息
     *
     * @param tempList    输入数据
     * @param columnCount 列数
     * @return 图信息
     */
    private ImageDto convertToImage(List<String[]> tempList, Integer columnCount) {
        if (columnCount == null || columnCount < 1) {
            return null;
        }
        ImageDto dto = new ImageDto(columnCount);
        Integer[] temp = new Integer[columnCount];//记录地图行数据
        Integer[] tempInt = new Integer[2];//分割每行输入的值

        Integer residue = 0;
        for (int i = 0; i < tempList.size(); i++) {
            tempInt[0] = Integer.valueOf(tempList.get(i)[0]);
            tempInt[1] = Integer.valueOf(tempList.get(i)[1]);

            /*几种特殊情形，提高效率*/
            //输入数据为一行时
            if (tempList.size() == 1) {
                dto.setSpecialData(true);
                String result = new String("0" + "\u0020" + tempInt[1]);
                dto.setResult4SpecialData(new ArrayList<>());
                dto.getResult4SpecialData().add(result);
                return dto;
            }

            //大数据列单独处理（此题大数据是大段相同值的形式出现，不会出现大数据且数据值离散的情形）
            if (tempInt[1] / columnCount > 10000) {
                dto.setSpecialData(true);
                List<String> result4Special = new ArrayList<>();
                String result;
                for (int p = 0; p < tempList.size(); p++) {
                    /*若不为第一行，加2*columnCount的绝对差值*/
                    if (p != 0) {
                        result = Math.abs(Integer.valueOf(tempList.get(p - 1)[0]) - Integer.valueOf(tempList.get(p)[0])) +
                                "\u0020" + columnCount * 2;
                        result4Special.add(result);
                    }
                    //结果加上tempCount-columnCount的0
                    Integer tempCount = Integer.valueOf(tempList.get(p)[1]);
                    result = "0" + "\u0020" + (tempCount - columnCount);
                    result4Special.add(result);
                }
                dto.setResult4SpecialData(result4Special);
                return dto;
            }

            Integer tempResidue;//减去之前的剩余量得到此次的总数
            Integer row;
            Integer col;
            if (tempInt[1] + residue > columnCount) {
                tempResidue = tempInt[1] - (columnCount - residue);//减去之前的剩余量弥补一行所需数据个数，得到此次的总数
                row = tempResidue / columnCount;//行数
                col = tempResidue % columnCount;//剩余列
            } else {
                row = 0;
                col = 0;
            }

            if (row != 0 || col != 0) {//该输入行数据足够直接形成地图新行
                //先把上一循环的剩余数组补上
                for (int r = residue; r < columnCount; r++) {
                    temp[r] = tempInt[0];
                }
                dto.getImageLines().add(temp);

                //大于列数，整行赋值
                for (int r = 0; r < row; r++) {
                    temp = new Integer[columnCount];
                    Arrays.fill(temp, tempInt[0]);
                    dto.getImageLines().add(temp);
                }

                temp = new Integer[columnCount];
                //小于列数，每列赋值
                for (int c = 0; c < col; c++) {
                    temp[c] = tempInt[0];
                }
                //赋值新的剩余量
                residue = col;
            } else {//此行输入数据+剩余量不足以直接形成地图的一行
                for (int j = residue; j < residue + tempInt[1]; j++) {
                    temp[j] = tempInt[0];
                }

                if (residue + tempInt[1] == columnCount) {//恰好够地图一行
                    dto.getImageLines().add(temp);
                    temp = new Integer[columnCount];
                    //赋值剩余量
                    residue = 0;
                } else {
                    //赋值剩余量
                    residue += tempInt[1];
                }
            }
        }
        return dto;
    }


    /**
     * 处理数据
     *
     * @param cinList
     */
    private void dealList(List<ImageDto> cinList) {
        List<String> result = new ArrayList<>();
        ImageDto dto;

        //遍历每个地图
        for (int i = 0; i < cinList.size(); i++) {
            dto = cinList.get(i);
            result.add(dto.getCOLUMN_COUNT().toString());

            /*判断是否为特殊数据*/
            if (dto.getSpecialData()) {
                //直接加上特殊结果集合
                result.addAll(dto.getResult4SpecialData());
                result.add(0 + "\u0020" + 0);
                continue;
            }

            Integer maxValue = -1;//当前位置与周围8个位置的绝对差最大值
            Integer tempMaxValue = 0;//临时最大值
            Integer maxValueCount = 0;//最大值的计数
            //行遍历
            for (int r = 0; r < dto.getImageLines().size(); r++) {
                //列遍历
                for (int c = 0; c < dto.getCOLUMN_COUNT(); c++) {
                    tempMaxValue = getMaxValue(dto, r, c);
                    if (maxValue == -1) {
                        maxValue = tempMaxValue;
                        maxValueCount++;
                    } else if (maxValue.equals(tempMaxValue)) {
                        maxValueCount++;
                    } else {
                        result.add(maxValue + "\u0020" + maxValueCount);
                        maxValue = tempMaxValue;
                        maxValueCount = 1;
                    }
                }
            }
            result.add(maxValue + "\u0020" + maxValueCount);
            result.add(0 + "\u0020" + 0);
        }
        result.add("0");

//        CommonUtil.printListResult(result);
        printListResult(result);
    }

    /**
     * 计算当前位置与八个方位绝对差的最大值
     *
     * @param dto 地图dto
     * @param r   所处行
     * @param c   所处列
     * @return 绝对差的最大值
     */
    private Integer getMaxValue(ImageDto dto, int r, int c) {

        Integer maxValue = 0;
        if (r < 0 || c < 0) {
            return maxValue;
        }

        //计算8个方位的值，并取最大值
        Integer dValueLU = r == 0 || c == 0 ? 0 : Math.abs(dto.getImageLines().get(r - 1)[c - 1] - dto.getImageLines().get(r)[c]);
        maxValue = dValueLU > maxValue ? dValueLU : maxValue;
        Integer dValueU = r == 0 ? 0 : Math.abs(dto.getImageLines().get(r - 1)[c] - dto.getImageLines().get(r)[c]);
        maxValue = dValueU > maxValue ? dValueU : maxValue;
        Integer dValueRU = r == 0 || c == dto.getCOLUMN_COUNT() - 1 ?
                0 : Math.abs(dto.getImageLines().get(r - 1)[c + 1] - dto.getImageLines().get(r)[c]);
        maxValue = dValueRU > maxValue ? dValueRU : maxValue;
        Integer dValueR = c == dto.getCOLUMN_COUNT() - 1 ?
                0 : Math.abs(dto.getImageLines().get(r)[c + 1] - dto.getImageLines().get(r)[c]);
        maxValue = dValueR > maxValue ? dValueR : maxValue;
        Integer dValueRD = r == dto.getImageLines().size() - 1 || c == dto.getCOLUMN_COUNT() - 1 ?
                0 : Math.abs(dto.getImageLines().get(r + 1)[c + 1] - dto.getImageLines().get(r)[c]);
        maxValue = dValueRD > maxValue ? dValueRD : maxValue;
        Integer dValueD = r == dto.getImageLines().size() - 1 ?
                0 : Math.abs(dto.getImageLines().get(r + 1)[c] - dto.getImageLines().get(r)[c]);
        maxValue = dValueD > maxValue ? dValueD : maxValue;
        Integer dValueLD = r == dto.getImageLines().size() - 1 || c == 0 ?
                0 : Math.abs(dto.getImageLines().get(r + 1)[c - 1] - dto.getImageLines().get(r)[c]);
        maxValue = dValueLD > maxValue ? dValueLD : maxValue;
        Integer dValueL = c == 0 ? 0 : Math.abs(dto.getImageLines().get(r)[c - 1] - dto.getImageLines().get(r)[c]);
        maxValue = dValueL > maxValue ? dValueL : maxValue;

        //返回最大值
        return maxValue;
    }

    public static void main(String[] args){
        P1009 p1009= new P1009();
        Scanner cin = new Scanner(System.in);
        Integer columnCount;
        List<ImageDto> images = new ArrayList<>();
        if (cin.hasNext()) {
            columnCount = Integer.parseInt(cin.nextLine());//第一条为第一个地图的列数
            String temp;
            while (cin.hasNext()) {
                temp = cin.nextLine();
                p1009.tempArray = temp.split("\u0020");
                if (p1009.tempArray.length == 1) {
                    if (p1009.tempArray[0].equals("0")) {//一个0，输入结束
                        break;
                    } else {//不是0，则为新地图列数
                        columnCount = Integer.valueOf(p1009.tempArray[0]);
                        continue;
                    }
                } else if (p1009.tempArray[0].equals("0") && p1009.tempArray[1].equals("0")) {//两个0，单个地图数据结束
                    //将输入数据转为地图数据
                    ImageDto imageDto = p1009.convertToImage(p1009.tempList, columnCount);
                    //插入地图列表
                    images.add(imageDto);
                    p1009.tempList = new ArrayList<>();
                } else {//记录输入数据
                    p1009.tempList.add(p1009.tempArray);
                }
            }
            //处理输入列表
            p1009.dealList(images);
        }
    }

    public static <T> void printListResult(List<T> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}