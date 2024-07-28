package TDA;

import TDA.NodeTree;

public class Tree<T> {
  
    private NodeTree<T> root; //root

   
    //Tree with specified root content value
    public Tree(T rootContent){
        this.root = new NodeTree<>(rootContent);
    }

    
    //Tree with a node
    public Tree(NodeTree<T> root){
        this.root = root;
    }

    
    //Empty Tree
    public Tree(){
        this((T) null);
    }

   
    //Sets root
    public void setRoot(NodeTree<T> root) {
        this.root = root;
    }

    //Gets root
    public NodeTree<T> getRoot() {
        return root;
    }

    //Gets the first children ( Queue sorted by utility )
    public Tree<T> getChildrenByUtility(){
        return root.getChildren().peek();
    }


	

}
