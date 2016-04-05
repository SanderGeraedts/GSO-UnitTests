
import fontys.time.DayInWeek;
import fontys.time.Time;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sander Geraedts - Code Panda
 */
public class TimeTest {
    private int year;
    private int normalMonth;
    private int normalDay;
    private int normalHour;
    private int normalMin;
    private int low;
    private int high;
    
    private Time t;
    
    @Before
    public void setUp() {
        year = 2016;
        
        normalMonth = 3;
        normalDay = 21;
        normalHour = 10;
        normalMin = 30;
        
        low = -1;
        high = 80;
        
        t = new Time(year, normalMonth, normalDay, normalHour, normalMin);
    }
    
    @Test
    public void testGoodConstructor() {
        /**
         * creation of a time-object with year y, month m, day d, hours h and
         * minutes m; if the combination of y-m-d refers to a non-existing date 
         * the value of this Time-object will be not guaranteed 
         * @param y 
         * @param m 1≤m≤12
         * @param d 1≤d≤31
         * @param h 0≤h≤23
         * @param min 0≤m≤59
         */
        Time time = new Time(year, normalMonth, normalDay, normalHour, normalMin);
        assertNotNull("Object time was not created", time);
    }
    
    @Test
    (expected=IllegalArgumentException.class)
    public void testHighMonthConstructor() {
        Time time = new Time(year, high, normalDay, normalHour, normalMin);
    }
    
    @Test
    (expected=IllegalArgumentException.class)
    public void testLowMonthConstructor() {
        Time time = new Time(year, low, normalDay, normalHour, normalMin);
    }
    
    @Test
    (expected=IllegalArgumentException.class)
    public void testHighDayConstructor() {
        Time time = new Time(year, normalMonth, high, normalHour, normalMin);
    }
    
    @Test
    (expected=IllegalArgumentException.class)
    public void testLowDayConstructor() {
        Time time = new Time(year, normalMonth, low, normalHour, normalMin);
    }
    
    @Test
    (expected=IllegalArgumentException.class)
    public void testHighHourConstructor() {
        Time time = new Time(year, normalMonth, normalDay, high, normalMin);
    }
    
    @Test
    (expected=IllegalArgumentException.class)
    public void testLowHourConstructor() {
        Time time = new Time(year, normalMonth, normalDay, low, normalMin);
    }
    
    @Test
    (expected=IllegalArgumentException.class)
    public void testHighMinConstructor() {
        Time time = new Time(year, normalMonth, normalDay, normalHour, high);
    }
    
    @Test
    (expected=IllegalArgumentException.class)
    public void testLowMinConstructor() {
        Time time = new Time(year, normalMonth, normalDay, normalHour, low);
    }
    
    @Test
    public void testGetDayOfWeekMonday() {
        Time monday = new Time(2016, 3, 25, 15, 15);        
        assertEquals("MON should be returned. Instead " + monday.getDayInWeek().toString() + " was returned.", monday.getDayInWeek(), DayInWeek.MON);
    }
    
    @Test
    public void testGetDayOfWeekTuesday() {
        Time tuesday = new Time(2016, 3, 26, 15, 15);
        assertEquals("TUE should be returned. Instead " + tuesday.getDayInWeek().toString() + " was returned.", tuesday.getDayInWeek(), DayInWeek.TUE);
    }
    
    @Test
    public void testGetDayOfWeekWednesday() {
        Time wednesday = new Time(2016, 3, 27, 15, 15);
        assertEquals("WED should be returned. Instead " + wednesday.getDayInWeek().toString() + " was returned.", wednesday.getDayInWeek(), DayInWeek.WED);
    }
    
    @Test
    public void testGetDayOfWeekThursday() {
        Time thursday = new Time(2016, 3, 28, 15, 15);
        assertEquals("THU should be returned. Instead " + thursday.getDayInWeek().toString() + " was returned.", thursday.getDayInWeek(), DayInWeek.THU);
    }
    
    @Test
    public void testGetDayOfWeekFriday() {
        Time friday = new Time(2016, 3, 29, 15, 15);
        assertEquals("FRI should be returned. Instead " + friday.getDayInWeek().toString() + " was returned.", friday.getDayInWeek(), DayInWeek.FRI);
    }
    
    @Test
    public void testGetDayOfWeekSaturday() {
        Time saturday = new Time(2016, 3, 30, 15, 15);
        assertEquals("SAT should be returned. Instead " + saturday.getDayInWeek().toString() + " was returned.", saturday.getDayInWeek(), DayInWeek.SAT);
    }
    
    @Test
    public void testGetDayOfWeekSunday() {
        Time sunday = new Time(2016, 3, 31, 15, 15);
        assertEquals("SUN should be returned. Instead " + sunday.getDayInWeek().toString() + " was returned.", sunday.getDayInWeek(), DayInWeek.SUN);
    }
    
    @Test
    public void testGetYear() {
        assertEquals("getYear() should return 2016. Instead " + t.getYear() + " was returned", t.getYear(), 2016);
    }
    
    @Test
    public void testGetMonth() {
        assertEquals("getMonth() should return 3. Instead " + t.getMonth() + " was returned", t.getMonth(), 3);
    }
    
    @Test
    public void testGetDay() {
        assertEquals("getDAY() should return 21. Instead " + t.getDay() + " was returned", t.getDay(), 21);
    }
    
    @Test
    public void testGetHours() {
        assertEquals("getHours() should return 10. Instead " + t.getHours() + " was returned", t.getHours(), 10);
    }
    
    @Test
    public void testGetMinutes() {
        assertEquals("getMinutes() should return 30. Instead " + t.getMinutes() + " was returned", t.getMinutes(), 30);
    }
    
    @Test
    public void testPlus() {
        t = (Time) t.plus(10);
        assertEquals("getMinutes() should return 40. Instead " + t.getMinutes() + " was returned", t.getMinutes(), 40);
    }
    
    @Test
    public void testPlusAbove60() {
        t = (Time) t.plus(40);
        assertEquals("getMinutes() should return 10. Instead " + t.getMinutes() + " was returned", t.getMinutes(), 10);
        assertEquals("getHours() should return 11. Instead " + t.getHours() + " was returned", t.getHours(), 11);
    }
    
    @Test
    public void testDifference() {
        Time diff = new Time(year, normalMonth, normalDay, normalHour, 40);
        
        int minDifference = t.difference(diff);
        
        assertEquals("minDifference should be 10. Instead " + minDifference + " was returned.", minDifference, 10);
    }
    
    @Test
    public void testCompareToSelf() {
        int i = t.compareTo(t);
        
        assertEquals(i, 0);
    }
    
    @Test
    public void testCompareToHigher() {
        Time later = (Time) t.plus(10);
        int i = t.compareTo(later);
        
        assertEquals(i, 1);
    }
    
    @Test
    public void testCompareToLower() {
        Time later = (Time) t.plus(-10);
        int i = t.compareTo(later);
        
        assertEquals(i, -1);
    }
}
