/**
 * Created by Casper on 22/12/2016.
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

        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getNotes(){
        return notes;
    }

    public void addNotes(String noteToAdd){
        this.notes += "\n" + noteToAdd;
    }
}
