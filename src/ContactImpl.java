/**
 * Created by Casper on 22/12/2016.
 */
public class ContactImpl implements Contact {

    private int id;
    private String name;
    private String notes;

    public ContactImpl(int id, String name, String notes){
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public ContactImpl(int id, String name){
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

    public void addNotes(String note){
        this.notes = notes;
    }
}
