/**
 * Created by Casper on 31/12/2016.
 */
public class IdGeneratorImpl implements IdGenerator {
    public int genId(Object toGenerate){
        return Math.abs(toGenerate.hashCode());

    }
}
