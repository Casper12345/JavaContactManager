import java.util.Set;

/**
 * Implements IDGenerator
 */
public class IdGeneratorImpl<T> implements IdGenerator<T> {


    public int genId(Object toGenerate, Set<T> objectsInSystem){
        return returnID(hashGenerator(toGenerate), objectsInSystem);

    }

    private int returnID(int generated, Set<T> objectsInSystem){

        if(checkUniqueness(generated, objectsInSystem)){
            return generated;
        }else{
            return returnID(numericIdGen(generated/2), objectsInSystem);
        }
    }

    private int hashGenerator(Object toGenerate){
        return Math.abs(toGenerate.hashCode());
    }


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

    private int numericIdGen(int gen){
         return gen + 1;
    }
}
