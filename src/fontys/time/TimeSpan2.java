/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import fontys.time.ITime;
import fontys.time.ITimeSpan;
import fontys.time.Time;
import fontys.time.TimeSpan;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Sander
 */
public class TimeSpan2 implements ITimeSpan {

    private ITime bt;
    
    
    
    private long duur;
    
    public TimeSpan2(ITime bt, long duur) {
        if (duur < 0) {
            throw new IllegalArgumentException("Duur can not be smaller than 0!");
        }
        
        this.bt = bt;
        this.duur = duur;
        if (this.duur >= 0) {
            GregorianCalendar date = new GregorianCalendar(bt.getYear(), bt.getMonth(), bt.getDay(), bt.getHours(), bt.getMinutes());
            date.add(Calendar.MINUTE, (int)duur);
            this.et = new Time(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE));
        }
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
        
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
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
        System.out.println("TestbtA :" + bt.getMinutes() + " - TestbtB :" + timeSpan.getBeginTime().getMinutes() + " - uitkomst: " + bt.compareTo(timeSpan.getBeginTime()));
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
