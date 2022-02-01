import java.util.ArrayList;
import java.util.Stack;

/**
 * AUTHOR: Giuseppe Guerini
 * 
 * Represents an infix expression as a binary tree.
 * Proper syntax is necessary, and the expression may
 * only contain characters 0 to 9 and +, -, *, /, ^.
 * Exponentials cannot be stringed.
 */
public class ExpressionTree
{
    private final BinaryTree tree;
    
    /**
     * Constructs an expression tree by converting
     * an infix expression to a binary tree.
     */
    public ExpressionTree(
        final String infix)
    {
        final String postfix = infixToPostfix(infix);
        final Stack<BinaryTree.Node> nodes = new Stack<>();
        
        for(int i = 0; i < postfix.length(); i++)
        {
            final char token = postfix.charAt(i);
            
            if(token >= '0' && token <= '9')
            {
                final BinaryTree.Node node = new BinaryTree.Node();
                
                node.value = "" + token;
                nodes.push(node);
            }
            else
            {
                final BinaryTree.Node node = new BinaryTree.Node();
                
                node.value = "" + token;
                node.right = nodes.pop();
                node.left = nodes.pop();
                nodes.push(node);
            }
        }
        
        tree = !nodes.empty() ? new BinaryTree(nodes.peek()) : null;
    }
    
    /**
     * Converts an infix expression to a postfix
     * expression using Shunting-Yard Algorithm.
     */
    private static String infixToPostfix(
        final String infix)
    {
        final StringBuilder postfix = new StringBuilder();
        final Stack<Character> operators = new Stack<>();
        
        for(int i = 0; i < infix.length(); i++)
        {
            final char token = infix.charAt(i);
            
            if(token == ' ')
            {
                continue;
            }
            
            if(token >= '0' && token <= '9')
            {
                postfix.append(token);
            }
            else
            {
                while(!operators.empty() && priorityOf(operators.peek()) >= priorityOf(token))
                {
                    postfix.append(operators.pop());
                }
                
                operators.push(token);
            }
        }
        
        while(!operators.empty())
        {
            postfix.append(operators.pop());
        }
        
        return postfix.toString();
    }
    
    /**
     * Determines the priority value of an operator.
     * An operator with a priority value higher
     * than another has precedence.
     */
    private static int priorityOf(
        final char op)
    {
        switch(op)
        {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        
        return 0;
    }
    
    /**
     * Returns the internal representation of the
     * expression tree.
     */
    public BinaryTree binaryTree()
    {
        return tree;
    }
    
    /**
     * Evaluates the entire expression tree represented
     * by the class.
     */
    public double evaluate()
    {
        return evaluate(tree.root());
    }
    
    /**
     * Evaluates the expression tree represented by the
     * node and its children. Leaf nodes are evaluated
     * first as the tree is evaluated from bottom to top.
     */
    private static double evaluate(
        final BinaryTree.Node node)
    {
        switch(node.value)
        {
            case "+":
                return evaluate(node.left) + evaluate(node.right);
            case "-":
                return evaluate(node.left) - evaluate(node.right);
            case "*":
                return evaluate(node.left) * evaluate(node.right);
            case "/":
                return evaluate(node.left) / evaluate(node.right);
            case "^":
                return Math.pow(evaluate(node.left), evaluate(node.right));
        }
        
        return Double.parseDouble(node.value);
    }
    
    /**
     * Returns the expression in infix notation.
     */
    public String infix()
    {
        return notationToString(tree.inorder());
    }
    
    /**
     * Returns the expression in prefix notation.
     */
    public String prefix()
    {
        return notationToString(tree.preorder());
    }
    
    /**
     * Returns the expression in postfix notation.
     */
    public String postfix()
    {
        return notationToString(tree.postorder());
    }
    
    private static String notationToString(
        final ArrayList<BinaryTree.Node> order)
    {
        final StringBuilder result = new StringBuilder();
        
        for(final BinaryTree.Node node : order)
        {
            result.append(node.value);
        }
        
        return result.toString();
    }
    
    /**
     * Represents the expression tree as a String.
     */
    public String toString()
    {
        final StringBuilder graph = new StringBuilder();
        final int height = tree.height();
        
        for(int i = 0; i <= height; i++)
        {
            final ArrayList<BinaryTree.Node> nodes = tree.nodesAtLevel(i);
            final int padding = (int)Math.pow(2, height - i) - 1;
            final int separation = (int)Math.pow(2, height - i + 1) - 1;
            
            repeatCharacter(graph, ' ', padding);
            
            for(int j = 0; j < nodes.size(); j++)
            {
                if(nodes.get(j) != null)
                {
                    graph.append(nodes.get(j).value);
                }
                else
                {
                    graph.append(':');
                }
                
                if(j < nodes.size() - 1)
                {
                    if(j % 2 == 0)
                    {
                        repeatCharacter(graph, '`', separation);
                    }
                    else
                    {
                        repeatCharacter(graph, ' ', separation);
                    }
                }
            }
            
            if(i < height)
            {
                graph.append('\n');
            }
        }
        
        return graph.toString();
    }
    
    /**
     * Repeats the specified character n times.
     */
    private static void repeatCharacter(
        final StringBuilder sb,
        final char chr,
        final int times)
    {
        for(int i = 0; i < times; i++)
        {
            sb.append(chr);
        }
    }
}
