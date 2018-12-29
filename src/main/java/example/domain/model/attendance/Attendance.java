package example.domain.model.attendance;

import example.domain.model.worker.WorkerNumber;
import example.domain.model.workrecord.WorkDate;
import example.domain.model.workrecord.WorkMonth;
import example.domain.model.workrecord.WorkRecord;
import example.domain.model.workrecord.WorkRecords;

import java.util.List;

/**
 * 勤怠
 */
public class Attendance {

    WorkerNumber workerNumber;
    WorkMonth month;
    WorkRecords workRecords;

    public Attendance(WorkerNumber workerNumber, WorkMonth month, WorkRecords workRecords) {
        this.workerNumber = workerNumber;
        this.month = month;
        this.workRecords = workRecords;
    }

    public WorkMonth month() {
        return month;
    }

    public List<WorkDate> listWorkDates() {
        return month.days();
    }

    public WorkRecord at(WorkDate workDate) {
        return workRecords.at(workDate);
    }

    public AttendanceStatus statusOf(WorkDate workDate) {
        return AttendanceStatus.from(workRecords.recordedAt(workDate));
    }

    public boolean notWorking() {
        return workRecords.list().isEmpty();
    }

    public WorkDate firstWorkDate() {
        List<WorkRecord> list = workRecords.list();
        return list.get(0).workDate();
    }

    public List<WorkRecord> listAvailableWorkRecord() {
        return workRecords.list();
    }
}