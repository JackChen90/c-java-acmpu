package indi.jackie.acmpu.p1009;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jackie chen
 * @create 2017/7/31
 * @description 图像DTO
 */
public class ImageDto {
    private final Integer COLUMN_COUNT;//二维数据列数

    private List<Integer[]> imageLines = new ArrayList<>();//地图行列表

    private Boolean isSpecialData = false;

    private List<String> result4SpecialData = new ArrayList<>();//大数据结果集合

    public ImageDto(Integer columnCount){
        this.COLUMN_COUNT = columnCount;
    }

    public Integer getCOLUMN_COUNT() {
        return COLUMN_COUNT;
    }

    public List<Integer[]> getImageLines() {
        return imageLines;
    }

    public void setImageLines(List<Integer[]> imageLines) {
        this.imageLines = imageLines;
    }

    public Boolean getSpecialData() {
        return isSpecialData;
    }

    public void setSpecialData(Boolean specialData) {
        isSpecialData = specialData;
    }

    public List<String> getResult4SpecialData() {
        return result4SpecialData;
    }

    public void setResult4SpecialData(List<String> result4SpecialData) {
        this.result4SpecialData = result4SpecialData;
    }
}
