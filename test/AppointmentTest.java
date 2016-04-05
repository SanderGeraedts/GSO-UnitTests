/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fontys.time.*;
import java.util.ArrayList;
import java.util.Iterator;
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
public class AppointmentTest {
    
    private Time t1;
    
    private Time t2;
    
    private Time t3;
    
    private Time t4;
    
    private ITimeSpan ts1;
     
    private ITimeSpan ts2;
    
    private Appointment a1;
    
    private Appointment a2;
    
    private String subject1 = "subject1";
    
    private String subject2 = "subject2";
    
    private Contact c1;
    
    private Contact c2;
    
    ArrayList<Contact> a;
    
    Iterator<Contact> c;
    
    public AppointmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t1 = new Time(2015, 10, 3, 12, 00);
        t2 = new Time(2015, 10, 3, 12, 15);
        t3 = new Time(2015, 10, 3, 12, 30);
        t4 = new Time(2015, 10, 3, 12, 45);
        ts1 = new TimeSpan(t1,t2);
        ts2 = new TimeSpan(t3,t4);
        a1 = new Appointment(subject1, (TimeSpan)ts1);
        a2 = new Appointment(subject2, (TimeSpan)ts2);
        c1 = new Contact("Piet");
        c2 = new Contact("Henk");
        a = new ArrayList<Contact>();
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of getSubject method, of class Appointment.
     */
    @Test
    public void testGetSubject() {
        System.out.println("getSubject");
        assertEquals(subject1, a1.getSubject());
    }

    /**
     * Test of getPeriod method, of class Appointment.
     */
    @Test
    public void testGetPeriod() {
        System.out.println("getPeriod");
        assertEquals(ts1, a1.getPeriod());
    }
    
    /**
     * Test of addContact method, of class Appointment.
     */
    @Test
    public void testAddContact() {
        System.out.println("addContact");

        a1.addContact(c1);
        assertEquals(c1,a1.invitees().next()); 
    }

    /**
     * Test of removeContact method, of class Appointment.
     */
    @Test
    public void testRemoveContact() {
        System.out.println("removeContact");
        a1.addContact(c1);
        a1.addContact(c2);
        a1.removeContact(c1);
        assertSame(c2,a1.invitees().next());
    }
    
}
