import java.util.Scanner;

/**
 * AUTHOR: Giuseppe Guerini
 * 
 * Tests ExpressionTree.
 */
public class Main
{
    public static void main(
        final String[] args)
    {
        final Scanner sc = new Scanner(System.in);
        String expression = "";
        
        System.out.println("Expression Tree Program");
        System.out.println("Type N to Quit");
        
        do
        {
            System.out.println();
            System.out.print("Enter Infix Expression: ");
            expression = sc.nextLine();
            
            try
            {
                final ExpressionTree tree = new ExpressionTree(expression);
                System.out.println("GRAPH");
                System.out.println(tree);
                System.out.println("Infix: " + tree.infix());
                System.out.println("Prefix: " + tree.prefix());
                System.out.println("Postfix: " + tree.postfix());
                System.out.println("Value: " + tree.evaluate());
                System.out.println("Height: " + tree.binaryTree().height());
            }
            catch(final Exception e)
            {
                System.out.println("Invalid Expression.");
            }
        }while(!expression.equalsIgnoreCase("N"));
    }
}
