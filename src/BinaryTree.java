import java.util.ArrayList;

/**
 * AUTHOR: Giuseppe Guerini
 * 
 * Represents a binary tree.
 */
public class BinaryTree
{
    private Node root;
    
    /**
     * Constructs a binary tree.
     */
    public BinaryTree(
        final Node root)
    {
        this.root = root;
    }
    
    /**
     * Returns the root of the tree.
     */
    public Node root()
    {
        return root;
    }
    
    /**
     * Evaluates the height of the expression tree
     * using recursion.
     */
    public int height()
    {
        return height(root, 0);
    }
    
    /**
     * A recursive method to help evaluate the height
     * of the tree.
     */
    private static int height(
        final Node node,
        final int height)
    {
        if(node == null)
        {
            return height - 1;
        }
        
        final int leftHeight = height(node.left, height + 1);
        final int rightHeight = height(node.right, height + 1);
        
        return Math.max(leftHeight, rightHeight);
    }
    
    /**
     * Returns the nodes at the specified level using
     * recursion.
     */
    public ArrayList<Node> nodesAtLevel(
        final int level)
    {
        final ArrayList<Node> nodes = new ArrayList<>();
        
        nodesAtLevel(nodes, root, 0, level);

        return nodes;
    }
    
    /**
     * A recursive method to help find the nodes at the
     * specified level.
     */
    private static void nodesAtLevel(
        final ArrayList<Node> nodes,
        final Node node,
        final int current,
        final int level)
    {
        if(current == level)
        {
            nodes.add(node);
            return;
        }
        
        if(node != null)
        {
            nodesAtLevel(nodes, node.left, current + 1, level);
            nodesAtLevel(nodes, node.right, current + 1, level);
        }
        else
        {
            nodesAtLevel(nodes, null, current + 1, level);
            nodesAtLevel(nodes, null, current + 1, level);
        }
    }
    
    /**
     * Returns the inorder arrangement of the tree.
     */
    public ArrayList<Node> inorder()
    {
        final ArrayList<Node> inorder = new ArrayList<>();
        
        inorder(inorder, root);
        
        return inorder;
    }
    
    /**
     * A recursive method to arrange the nodes
     * in inorder notation.
     */
    public static void inorder(
        final ArrayList<Node> inorder,
        final Node node)
    {
        if(node != null)
        {
            inorder(inorder, node.left);
            inorder.add(node);
            inorder(inorder, node.right);
        }
    }
    
    /**
     * Returns the preorder arrangement of the tree.
     */
    public ArrayList<Node> preorder()
    {
        final ArrayList<Node> preorder = new ArrayList<>();
        
        preorder(preorder, root);
        
        return preorder;
    }
    
    /**
     * A recursive method to arrange the nodes
     * in preorder notation.
     */
    public static void preorder(
        final ArrayList<Node> preorder,
        final Node node)
    {
        if(node != null)
        {
            preorder.add(node);
            preorder(preorder, node.left);
            preorder(preorder, node.right);
        }
    }
    
    /**
     * Returns the postorder arrangement of the tree.
     */
    public ArrayList<Node> postorder()
    {
        final ArrayList<Node> postorder = new ArrayList<>();
        
        postorder(postorder, root);
        
        return postorder;
    }
    
    /**
     * A recursive method to arrange the nodes
     * in postorder notation.
     */
    public static void postorder(
        final ArrayList<Node> postorder,
        final Node node)
    {
        if(node != null)
        {
            postorder(postorder, node.left);
            postorder(postorder, node.right);
            postorder.add(node);
        }
    }
    
    /**
     * Represents the binary tree as a String.
     */
    public String toString()
    {
        final StringBuilder result = new StringBuilder();
        final int height = height();
        
        for(int i = 0; i <= height; i++)
        {
            result.append('[');
            
            final ArrayList<Node> nodes = nodesAtLevel(i);
            final int size = nodes.size();
            
            for(int j = 0; j < size; j++)
            {
                final Node node = nodes.get(j);
                
                if(node != null)
                {
                    result.append(node.value);
                }
                else
                {
                    result.append((String)null);
                }
                
                if(j < size - 1)
                {
                    result.append(',');
                }
            }
            
            result.append(']');
            
            if(i < height)
            {
                result.append('\n');
            }
        }
        
        return result.toString();
    }
    
    /**
     * Represents a binary tree node.
     */
    static class Node
    {
        String value;
        Node left, right;
    }
}
