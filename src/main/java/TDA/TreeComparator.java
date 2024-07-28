package TDA;

import java.util.Comparator;

/**
 * A comparator for tree to compare its types
 * @param <T>
 */

//Comparator for tree to compare it's types that depends on the use in certain class.
@FunctionalInterface
public interface TreeComparator<T> extends Comparator<Tree<T>> {
    
    int compareContent(T o1, T o2);
    
    @Override
    default int compare(Tree<T> t1, Tree<T> t2){
        return compareContent(t1.getRoot().getContent(), t2.getRoot().getContent());
    }
   
}
