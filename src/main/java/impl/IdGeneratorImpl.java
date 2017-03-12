package main.java.impl;

import main.java.spec.Contact;
import main.java.spec.FutureMeeting;
import main.java.spec.IdGenerator;
import main.java.spec.PastMeeting;

import java.util.Set;

/**
 * Implements IDGenerator.
 * @param <T> takes a set of generic type
 *
 */
public class IdGeneratorImpl<T> implements IdGenerator<T> {

    /**
     *
     * @param toGenerate is the object that needs a generated id.
     * @param objectsInSystem is a set of existing objects in the system.
     * @return unique generated id, based on hashcode.
     */
    @Override
    public int genId(final Object toGenerate, final Set<T> objectsInSystem) {
        return returnID(hashGenerator(toGenerate), objectsInSystem);
    }

    /**
     * Auxiliary function for genID().
     * @param generated is generated hash code
     * @param objectsInSystem
     * are objects in the system to check uniqueness against
     * @return generated id
     */
    private int returnID(final int generated, final Set<T> objectsInSystem) {

        if (checkUniqueness(generated, objectsInSystem)) {
            return generated;
        } else {
            return returnID(numericIdGen(generated / 2), objectsInSystem);
        }
    }

    /**
     * Auxiliary function for genID().
     * @param toGenerate is object to generate hash code on
     * @return generated id
     */
    private int hashGenerator(final Object toGenerate) {
        int generated = toGenerate.hashCode();
        if (generated < 0) {
           generated = generated * -1;
        }
        return generated;
    }

    /**
     * Auxiliary function for genID().
     * @param objectsInSystem is objects in system to check uniqueness on
     * @param hashCode is generated hash code
     * @return boolean for uniqueness
     */
    private boolean checkUniqueness(final int hashCode,
                                    final Set<T> objectsInSystem) {
        for (T i: objectsInSystem) {

            if (hashCode < 0) {
                return false;
            }

            if (i instanceof Contact && hashCode == ((Contact) i).getId()) {
                return false;
            }
            if (i instanceof FutureMeeting && hashCode == ((FutureMeeting) i).getId()) {
                return false;
            }
            if (i instanceof PastMeeting && hashCode == ((PastMeeting) i).getId()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Auxiliary function for genID().
     * Gets called when uniqueness is not found
     * Increments value by one
     * @param gen gets incremented by one
     * @return generated id incrementeds by one
     */
    private int numericIdGen(final int gen) {
         return gen + 1;
    }
}
