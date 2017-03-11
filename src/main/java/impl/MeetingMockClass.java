package main.java.impl;

import java.util.Calendar;
import java.util.Set;

/**
 * MockUp class for main.java.impl.MeetingImpl.
 * This class serves for testing purposes.
 */
public class MeetingMockClass extends MeetingImpl {

    public MeetingMockClass(int id, Calendar date, Set contacts){
        super(id, date, contacts);
    }
}
