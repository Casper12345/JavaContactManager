/**
 * Implements IDGenerator
 */
public class IdGeneratorImpl implements IdGenerator {
    public int genId(Object toGenerate){
        return Math.abs(toGenerate.hashCode());

    }
}
