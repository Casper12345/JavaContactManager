package src;

/**
 * Implements interface src.Contact. Is written with TDD.
 */
public class ContactImpl implements Contact {

    private int id;
    private String name;
    private String notes;


    public ContactImpl(int id, String name, String notes) throws IllegalArgumentException, NullPointerException {

        if(id < 0){
            throw new IllegalArgumentException();
        }
        if(name == null || notes == null){
            throw new NullPointerException();
        }
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public ContactImpl(int id, String name){
        if(id < 0){
            throw new IllegalArgumentException();
        }
        if(name == null){
            throw new NullPointerException();
        }
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getNotes(){
        return notes;
    }

    @Override
    public void addNotes(String noteToAdd){
        this.notes += "\n" + noteToAdd;
    }
}
