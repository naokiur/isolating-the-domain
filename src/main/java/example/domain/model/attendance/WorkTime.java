package example.domain.model.attendance;

import example.domain.type.time.HourAndMinute;
import example.domain.type.time.Minute;

import java.util.List;

/**
 * 労働時間
 */
public class WorkTime {

    Minute workTime;
    Minute overWorkTime;
    Minute midnightWorkTime;

    public WorkTime() {
        this(new Minute(0), new Minute(0), new Minute(0));
    }

    public WorkTime(Minute workTime, Minute overWorkTime, Minute midnightWorkTime) {
        this.workTime = workTime;
        this.overWorkTime = overWorkTime;
        this.midnightWorkTime = midnightWorkTime;
    }

    public WorkTime(Attendance attendance) {
        this(
                attendance.workTime().toMinute(),
                attendance.overTime().toMinute(),
                attendance.midnightWorkTime().toMinute()
        );
    }

    public WorkTime addAttendanceOfDay(Attendance attendance) {
        return add(new WorkTime(attendance));
    }

    public WorkTime add(WorkTime workTime) {
        return new WorkTime(
                this.workTime.add(workTime.workTime),
                this.overWorkTime.add(workTime.overWorkTime),
                this.midnightWorkTime.add(workTime.midnightWorkTime)
        );
    }

    public HourAndMinute workTime() {
        return HourAndMinute.from(workTime);
    }

    public HourAndMinute overTime() {
        return HourAndMinute.from(overWorkTime);
    }

    public HourAndMinute midnightWorkTime() {
        return HourAndMinute.from(midnightWorkTime);
    }

    static WorkTime from(List<Attendance> list) {
        return list.stream()
                .reduce(new WorkTime(),
                        (workTime, attendanceOfDay) -> workTime.addAttendanceOfDay(attendanceOfDay),
                        WorkTime::add
                );
    }

    public HourAndMinute totalWorkTime() {
        return HourAndMinute.from(workTime.add(overWorkTime).add(midnightWorkTime));
    }
}
