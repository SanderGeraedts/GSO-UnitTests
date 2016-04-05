/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import fontys.time.Appointment;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Sander
 */
public class Contact {

    private String name;
    private ArrayList<Appointment> appointmentList;
    
    public Contact(String name) {
        this.name = name;
        appointmentList = new ArrayList<Appointment>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean addAppointment(Appointment a) {
        Iterator<Appointment> appointments = appointments();
        while (appointments.hasNext()) {
            if (a == appointments.next()) {
                return false;
            }            
        }
        appointmentList.add(a);
        a.addContact(this);
        return true;
    }
    
    public void removeAppointment(Appointment a) {
        Iterator<Appointment> appointments = appointments();
        while (appointments.hasNext()) {
            if (a == appointments.next()) {
                //appointments.remove();
                for (Appointment app : appointmentList) {
                    if (a == app) {
                        appointmentList.remove(app);
                    }
                }
            }
        }
        
    }
    
    public Iterator<Appointment> appointments() {
        return appointmentList.iterator();
    }
}
