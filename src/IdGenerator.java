/**
 * Created by Casper on 31/12/2016.
 */

import java.util.Set;

public interface IdGenerator<T> {

    int genId(Object toGenerate, Set<T> objectsInSystem);
}
