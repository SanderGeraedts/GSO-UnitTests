/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;


import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Roy
 */
public class Appointment {
    
    private String subject;
    
    private ITimeSpan timeSpan;
    
    private ArrayList<Contact> invitees;
    
    public Appointment (String subj, TimeSpan timeSp) {
        this.subject = subj;
        this.timeSpan = timeSp;
        invitees = new ArrayList<Contact>();
    }
    
        public String getSubject(){
        return this.subject;
    }
    
    public ITimeSpan getPeriod(){
        return this.timeSpan;
    }
    
    public Iterator<Contact> invitees(){
        return invitees.iterator();
    }
    
    public boolean addContact(Contact c){
        Iterator<Contact> invitees = invitees();
        while(invitees.hasNext()){
            if (c == invitees.next())
            {
               return false;
            }    
        }
        this.invitees.add(c);
        c.addAppointment(this);
        return true;
    }
    
    public void removeContact(Contact c){
        Iterator<Contact> invitees = invitees();
        while(invitees.hasNext()){
            if (c == invitees.next())
            {                
                for (Contact con : this.invitees){
                    if (c == con)
                    {
                        this.invitees.remove(con);
                    }
            }
        }
        
    }
    }
}
