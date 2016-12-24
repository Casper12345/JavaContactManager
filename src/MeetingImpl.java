import java.util.Date;
import java.util.Set;

/**
 * Created by Casper on 23/12/2016.
 */
public abstract class MeetingImpl {

    private int id;
    private Date date;
    private Set contacts;

    public MeetingImpl(int id, Date date, Set contacts){
        this.id = id;
        this.date = date;
        this.contacts = contacts;
    }


}

