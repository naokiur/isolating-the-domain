package example.domain.model.workrecord;

import example.domain.type.date.Date;
import example.domain.type.date.DayOfWeek;

/**
 * 勤務日付
 */
public class WorkDate {

    Date value;

    @Deprecated
    public WorkDate() {
    }

    public WorkDate(Date date) {
        value = date;
    }

    public WorkDate(String value) {
        this(new Date(value));
    }

    public Date value() {
        return value;
    }

    public int dayOfMonth() {
        return value.dayOfMonth();
    }

    public DayOfWeek dayOfWeek() {
        return value.dayOfWeek();
    }

    public boolean hasSameValue(WorkDate other) {
        return value.hasSameValue(other.value);
    }

    public WorkMonth month() {
        return new WorkMonth(value.year(), value.month());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
