package main.java.impl;

import main.java.spec.Contact;
import main.java.spec.FutureMeeting;
import main.java.spec.IdGenerator;
import main.java.spec.PastMeeting;

import java.util.Set;

/**
 * Implements IDGenerator
 */
public class IdGeneratorImpl<T> implements IdGenerator<T> {

    @Override
    public int genId(Object toGenerate, Set<T> objectsInSystem){
        return returnID(hashGenerator(toGenerate), objectsInSystem);
    }

    /**
     * Auxiliary function for genID().
     *
     */
    private int returnID(int generated, Set<T> objectsInSystem){

        if(checkUniqueness(generated, objectsInSystem)){
            return generated;
        }else{
            return returnID(numericIdGen(generated/2), objectsInSystem);
        }
    }

    /**
     * Auxiliary function for genID().
     *
     */
    private int hashGenerator(Object toGenerate){
        int generated = toGenerate.hashCode();
        if (generated < 0){
           generated = generated * -1;
        }
        return generated;
        //return Math.abs(toGenerate.hashCode());
    }

    /**
     * Auxiliary function for genID().
     *
     */
    private boolean checkUniqueness(int hashCode, Set<T> objectsInSystem){
        for(T i: objectsInSystem){

            if(hashCode < 0){
                return false;
            }

            if(i instanceof Contact) {
                if (hashCode == ((Contact) i).getId()) {
                    return false;
                }
            }
            if(i instanceof FutureMeeting) {
                if (hashCode == ((FutureMeeting) i).getId()) {
                    return false;
                }
            }
            if(i instanceof PastMeeting){
                if (hashCode == ((PastMeeting) i).getId()){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Auxiliary function for genID().
     *
     */
    private int numericIdGen(int gen){
         return gen + 1;
    }
}
