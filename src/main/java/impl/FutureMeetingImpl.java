package main.java.impl;

import main.java.spec.FutureMeeting;

import java.util.Calendar;
import java.util.Set;

/**
 * Implements main.java.spec.FutureMeeting and extends main.java.impl.MeetingImpl
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    public FutureMeetingImpl(int id, Calendar date, Set contacts){
        super(id, date, contacts);
    }
}
