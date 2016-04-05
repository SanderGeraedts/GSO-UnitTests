/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import fontys.time.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sander
 */
public class TimeSpan2Test {
    
    private Time t1;
    private Time t2;
    private Time t3;
    private Time t4;
    
    private TimeSpan2 ts1;
    private TimeSpan2 ts2;
    
    public TimeSpan2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t1 = new Time(2012,2,10,12,21);
        t2 = new Time(2012,2,10,12,22);
        t3 = new Time(2012,2,10,12,23);
        t4 = new Time(2012,2,10,12,24);
        
        ts1 = new TimeSpan2(t1, 1);
        ts2 = new TimeSpan2(t3, 1);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    (expected=IllegalArgumentException.class)
    public void testTimeSpan() {
        ts1 = new TimeSpan2(t1, 0);
        ts1 = new TimeSpan2(t3, 1);
        ts1 = new TimeSpan2(t2, -1);
    }

    /**
     * Test of getBeginTime method, of class TimeSpan.
     */
    @Test
    public void testGetBeginTime() {
        System.out.println("getBeginTime");
        ITime expResult = t1;
        ITime result = ts1.getBeginTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndTime method, of class TimeSpan.
     */
    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
        int expResult = t2.getMinutes();
        int result = ts1.getEndTime().getMinutes();
        assertEquals(expResult, result);
    }

    /**
     * Test of length method, of class TimeSpan.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        int expResult = t1.difference(t2);
        int result = ts1.length();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBeginTime method, of class TimeSpan.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testSetBeginTime() {
        System.out.println("setBeginTime");
        Time tempTime = new Time(2012,1,10,12,21);
        ts1.setBeginTime(tempTime);
        assertEquals(tempTime, ts1.getBeginTime());
        
        ts1.setBeginTime(t3);
    }

    /**
     * Test of setEndTime method, of class TimeSpan.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testSetEndTime() {
        System.out.println("setEndTime");
        Time tempTime = new Time(2012,3,10,12,22);
        ts1.setEndTime(tempTime);
        assertEquals(tempTime, ts1.getEndTime());
        
        ts2.setEndTime(t2);
    }

    /**
     * Test of move method, of class TimeSpan.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        int tempMinutes1 = ts1.getBeginTime().getMinutes();
        int tempMinutes2 = ts1.getEndTime().getMinutes();
        int minutes = 30;
        ts1.move(minutes);
//      Both times will increase by 1440 = 1 day
        assertEquals(tempMinutes1+30, ts1.getBeginTime().getMinutes());
        assertEquals(tempMinutes2+30, ts1.getEndTime().getMinutes());
    }

    /**
     * Test of changeLengthWith method, of class TimeSpan.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testChangeLengthWith() {
        System.out.println("move");
        int tempMinutes1 = ts1.getBeginTime().getMinutes();
        int tempMinutes2 = ts1.getEndTime().getMinutes();
        int minutes = 30;
        ts1.changeLengthWith(minutes);
//      Both times will increase by 1440 = 1 day
        assertEquals(tempMinutes1, ts1.getBeginTime().getMinutes());
        assertEquals(tempMinutes2+30, ts1.getEndTime().getMinutes());
        
        ts1.changeLengthWith(-30);
    }

    /**
     * Test of isPartOf method, of class TimeSpan.
     */
    @Test
    public void testIsPartOf() {
        System.out.println("isPartOf");
        assertTrue(ts1.isPartOf(ts1));
        
        assertFalse(ts1.isPartOf(ts2));
        assertFalse(ts2.isPartOf(ts1));
        assertTrue(ts2.isPartOf(ts2));
    }

    /**
     * Test of unionWith method, of class TimeSpan.
     */
    @Test
    public void testUnionWith() {
        System.out.println("unionWith");
        TimeSpan ts3 = new TimeSpan(t1, t2);
        t1= new Time(2012,2,10,12,20); t2= new Time(2012,2,10,12,21); t3= new Time(2012,2,10,12,22); t4= new Time(2012,2,10,12,23);
        ts1 = new TimeSpan2(t1,1);
        ts2 = new TimeSpan2(t3,1);
        ts3 = (TimeSpan)ts1.unionWith(ts2);
        assertEquals(null, ts3);
        
        t1= new Time(2012,2,10,12,21); t2= new Time(2012,2,10,12,23); t3= new Time(2012,2,10,12,20); t4= new Time(2012,2,10,12,22);
        ts1 = new TimeSpan2(t1, 1);
        ts2 = new TimeSpan2(t3, 1);
        ts3 = (TimeSpan)ts1.unionWith(ts2);
        assertEquals(21, ts3.getBeginTime().getMinutes());
        assertEquals(22, ts3.getEndTime().getMinutes());
        
        t1= new Time(2012,2,10,12,20); t2= new Time(2012,2,10,12,21); t3= new Time(2012,2,10,12,22); t4= new Time(2012,2,10,12,23);
        ts1 = new TimeSpan2(t1, 2);
        ts2 = new TimeSpan2(t3, 1);
        ts3 = (TimeSpan)ts1.unionWith(ts2);
        assertEquals(ts1.getEndTime().getMinutes(), ts2.getBeginTime().getMinutes());
    }

    /**
     * Test of intersectionWith method, of class TimeSpan.
     */
    @Test
    public void testIntersectionWith() {
        System.out.println("intersectionWith");
        TimeSpan ts3 = new TimeSpan(t1, t2);
        t1= new Time(2012,2,10,12,20); t2= new Time(2012,2,10,12,21); t3= new Time(2012,2,10,12,22); t4= new Time(2012,2,10,12,23);
        ts1 = new TimeSpan2(t1,1);
        ts2 = new TimeSpan2(t3,1);
        ts3 = (TimeSpan)ts1.intersectionWith(ts2);
        assertEquals(null, ts3);
        
        ts1 = new TimeSpan2(t1,3);
        ts2 = new TimeSpan2(t2,1);
        ts3 = (TimeSpan)ts1.intersectionWith(ts2); 
        assertEquals(21, ts3.getBeginTime().getMinutes());
        assertEquals(22, ts3.getEndTime().getMinutes());        
        
        ts1 = new TimeSpan2(t2,2);
        ts2 = new TimeSpan2(t1,2);
        ts3 = (TimeSpan)ts1.intersectionWith(ts2); 
        assertEquals(21, ts3.getBeginTime().getMinutes());
        assertEquals(22, ts3.getEndTime().getMinutes());
        
        ts1 = new TimeSpan2(t1,3);
        ts2 = new TimeSpan2(t1,2);
        ts3 = (TimeSpan)ts1.intersectionWith(ts2); 
        assertEquals(20, ts3.getBeginTime().getMinutes());
        assertEquals(22, ts3.getEndTime().getMinutes());
        
        ts1 = new TimeSpan2(t1,2);
        ts2 = new TimeSpan2(t2,2);
        ts3 = (TimeSpan)ts1.intersectionWith(ts2); 
        assertEquals(21, ts3.getBeginTime().getMinutes());
        assertEquals(22, ts3.getEndTime().getMinutes());          
        
//        t1= new Time(2012,2,10,12,20); t2= new Time(2012,2,10,12,21); t3= new Time(2012,2,10,12,22); t4= new Time(2012,2,10,12,23);
//        ts1 = new TimeSpan(t1, t4);
//        ts2 = new TimeSpan(t2, t3);       
//        ts3 = (TimeSpan)ts2.intersectionWith(ts1);
//        System.out.println("TestTs3: " + ts3.getBeginTime().getMinutes() + " + " + ts3.getEndTime().getMinutes());
//        assertEquals(20, ts3.getBeginTime().getMinutes());
//        assertEquals(23, ts3.getEndTime().getMinutes());
//        
//        t1= new Time(2012,2,10,12,20); t2= new Time(2012,2,10,12,21); t3= new Time(2012,2,10,12,22); t4= new Time(2012,2,10,12,23);
//        ts1 = new TimeSpan(t1, t3);
//        ts2 = new TimeSpan(t3, t4);
//        ts3 = (TimeSpan)ts1.intersectionWith(ts2);
//        assertEquals(ts1.getEndTime(), ts2.getBeginTime());
    }
    
}
