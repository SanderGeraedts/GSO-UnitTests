/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

/**
 *
 * @author Roy
 * 
 * LET OP: De klasse TimeSpan bevat enkele fouten.
 * 
 */
public class TimeSpan implements ITimeSpan {

    /* class invariant: 
     * A stretch of time with a begin time and end time.
     * The end time is always later then the begin time; the length of the time span is
     * always positive
     * 
     */
    private ITime bt, et;
    
    /**
     * 
     * @param bt must be earlier than et
     * @param et 
     */
    public TimeSpan(ITime bt, ITime et) {
        if (bt.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        this.bt = bt;
        this.et = et;
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        return et;
    }

    @Override
    public int length() {
        return bt.difference(et);
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) >= 0) {
            throw new IllegalArgumentException("end time "
                    + et + " must be later then begin time " + bt);
        }

        et = endTime;
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
        et = et.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }
        
        et = et.plus(minutes);
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) >= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) <= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        if (bt.compareTo(timeSpan.getEndTime()) < 0 || et.compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }
//        If the first part of the if statement turns out to be true, then the second part will always be true as well, as the other way around
//        Therefore, the second part of the if statement will never be covered by JaCoCo because it'll never reach this part.
//        else if (et.compareTo(timeSpan.getBeginTime()) < 0) {
//            return null;
//        }
        //21-23 . 20-22
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) > 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) < 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }

        return new TimeSpan(begintime, endtime);
    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {
// 20 - 23, 21- 22
        ITime begintime, endtime;
        //System.out.println("TestbtA :" + bt.getMinutes() + " - TestbtB :" + timeSpan.getBeginTime().getMinutes() + " - uitkomst: " + bt.compareTo(timeSpan.getBeginTime()));
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) > 0) {
            endtime = et;        
        } else {
            endtime = timeSpan.getEndTime();  
        }

        if (begintime.compareTo(endtime) <= 0) {
            return null;
        }

        return new TimeSpan(begintime, endtime);
    }
}
