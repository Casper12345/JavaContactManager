package main.java.impl;

import main.java.spec.Contact;

/**
 * Implements interface main.java.spec.Contact. Is written with TDD.
 */
public class ContactImpl implements Contact {
    /**
     * holds the id of the given contact.
     */
    private int id;
    /**
     * holds the name of the given contact.
     */
    private String name;
    /**
     * holds the nodes of the given contact.
     */
    private String notes;

    /**
     * Primary class constructor
     * @param idToSet is set by constructor.
     * @param nameToSet is set by constructor
     * @param notesToSet is set by constructor
     * @throws IllegalArgumentException is thrown if id is negative
     * @throws NullPointerException is thrown if either name or notes is an empty string
     */

    public ContactImpl(final int idToSet, final String nameToSet, final String notesToSet)
            throws IllegalArgumentException, NullPointerException {

        if (idToSet < 0){
            throw new IllegalArgumentException();
        }
        if (nameToSet == null || notesToSet == null){
            throw new NullPointerException();
        }
        this.id = idToSet;
        this.name = nameToSet;
        this.notes = notesToSet;
    }

    /**
     * Overloaded class constructor
     * @param idToSet is set by constructor
     * @param nameToSet is set by constructor
     */
    public ContactImpl(final int idToSet, final String nameToSet){
        if (idToSet < 0){
            throw new IllegalArgumentException();
        }
        if (nameToSet == null){
            throw new NullPointerException();
        }
        this.id = idToSet;
        this.name = nameToSet;
    }


    /**
     * Implements getId
     * Getter for id
     */
    @Override
    public int getId(){
        return id;
    }

    /**
     * Implements getName
     * Getter for name
     * @return name
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * Implements getNotes
     * Getter for notes
     * @return notes
     */
    @Override
    public String getNotes(){
        return notes;
    }

    /**
     * Implements notesToAdd
     * @param noteToAdd is notes that can be added to contact
     */
    @Override
    public void addNotes(final String noteToAdd){
        this.notes += "\n" + noteToAdd;
    }
}
