package indi.jackie.acmpu.p1007;

/**
 * @author jackie chen
 * @create 2017/7/26
 * @description DNADto
 */
public class DNADto implements Comparable {
    /**
     * DNA序列值
     */
    private String value;
    /**
     * DNA排序权值
     */
    private Integer sortWeight;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSortWeight() {
        return sortWeight;
    }

    public void setSortWeight(Integer sortWeight) {
        this.sortWeight = sortWeight;
    }

    @Override
    public int compareTo(Object target) {
        Integer result = this.getSortWeight() - ((DNADto) target).getSortWeight();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getValue());
        return sb.toString();
    }
}
