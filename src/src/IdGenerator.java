package src;
import java.util.Set;
/**
 * Interface for IDGenerator that generates unique id's for contacts, futureMeetings
 * and pastMeetings.
 *
 */
public interface IdGenerator<T> {

    /**
     * Main method that generates a unique id for the given object.
     * @param toGenerate is the object that needs a generated id.
     * @param objectsInSystem is a set of existing objects in the system.
     * @return the generated unique id.
     */
    int genId(Object toGenerate, Set<T> objectsInSystem);
}
