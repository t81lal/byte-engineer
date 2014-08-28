package eu.bibl.banalysis.storage.classes.matching;

/**
 * @author sc4re
 */
public interface ContainerMatcher<T, K> {
    public K match(T object);
}
