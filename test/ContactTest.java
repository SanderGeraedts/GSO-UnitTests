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
public class ContactTest {    
    private Contact c1;
    
    private Contact c2;
    
    private Appointment a1;
    
    private Appointment a2;
    
    private TimeSpan ts;
    
    private Time t1;
    
    private Time t2;
    
    private Iterator<Appointment> c;
    
    private ArrayList<Appointment> a;
    
    
    
    public ContactTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        c1 = new Contact("Piet");
        c2 = new Contact("Henk");
        t1 = new Time(2015,10,3,12,0);
        t2 = new Time(2015,10,3,12,15);
        ts = new TimeSpan(t1,t2);
        a1 = new Appointment("Afspraak 1", ts);
        a2 = new Appointment("Afspraak 2", ts);
        a = new ArrayList<Appointment>();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Contact.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Contact instance = c1;
        String expResult = "Piet";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of addAppointment method, of class Contact.
     */
    @Test
    public void testAddAppointment() {
        System.out.println("addAppointment");        

        c1.addAppointment(a1);
        a.add(a1);
        c = a.iterator();
        assertEquals(c.next(),c1.appointments().next());
        

        c1.addAppointment(a2);
        a.add(a2);
        c = a.iterator();
        Iterator<Appointment> con = c1.appointments();
        while (c.hasNext()){
            assertSame(c.next(),con.next());
        }
    }

    /**
     * Test of removeAppointment method, of class Contact.
     */
    @Test
    public void testRemoveAppointment() {
        System.out.println("removeAppointment");
        c1.addAppointment(a1);
        a.add(a1);
        
        c1.addAppointment(a2);
        a.add(a2);
        
        c1.removeAppointment(a1);
        a.remove(a1);
        c = a.iterator();
        assertSame(c.next(),c1.appointments().next());
    }    
}
