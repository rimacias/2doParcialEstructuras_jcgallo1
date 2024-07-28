package TDA;

import java.util.PriorityQueue;

public class NodeTree<T> {
    private T content;
    private PriorityQueue<Tree<T>> children;

    public NodeTree(T value){
        this.content = value;
    }
    public NodeTree(){}

    public PriorityQueue<Tree<T>> getChildren() {
        return children;
    }

    public T getContent() {
        return content;
    }

    public void setChildren(PriorityQueue<Tree<T>> children) {
        this.children = children;
    }

    public void setContent(T content) {
        this.content = content;
    }

}
