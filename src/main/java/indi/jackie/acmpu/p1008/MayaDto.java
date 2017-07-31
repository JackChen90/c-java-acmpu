package indi.jackie.acmpu.p1008;

import indi.jackie.acmpu.util.Constants;

/**
 * @author jackie chen
 * @create 2017/7/31
 * @description MayaDto
 */
public class MayaDto {

    private Haab haab;
    private Tzolkin tzolkin;

    public class Haab {
        private Integer year;
        private HaabMonth haabMonth;
        private Integer day;

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public HaabMonth getHaabMonth() {
            return haabMonth;
        }

        public void setHaabMonth(HaabMonth haabMonth) {
            this.haabMonth = haabMonth;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }
    }

    public class Tzolkin {
        private Integer number;
        private TzolinkDay tzolinkDay;
        private Integer year;

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public TzolinkDay getTzolinkDay() {
            return tzolinkDay;
        }

        public void setTzolinkDay(TzolinkDay tzolinkDay) {
            this.tzolinkDay = tzolinkDay;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.getNumber());
            sb.append(Constants.WHITE_SPACE);
            sb.append(this.getTzolinkDay());
            sb.append(Constants.WHITE_SPACE);
            sb.append(this.getYear());
            return sb.toString();
        }
    }

    public Haab getHaab() {
        return haab;
    }

    public void setHaab(Haab haab) {
        this.haab = haab;
    }

    public Tzolkin getTzolkin() {
        return tzolkin;
    }

    public void setTzolkin(Tzolkin tzolkin) {
        this.tzolkin = tzolkin;
    }
}
