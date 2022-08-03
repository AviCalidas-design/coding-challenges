package com.revature;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int val = (int)Integer.parseInt(input);
        if(val % 2 == 0)
        {
            System.out.println( "Even" );
        }
        else
        {
            System.out.println( "Odd" );
        }
        boolean prime = true;
        for(int i = 2; i <= val / 2.0 ; i++)
        {
            if(val % i == 0)
            {
                prime = false;
            }
        }
        if(prime)
        {
            System.out.println( "Prime" );
        }
        else
        {
            System.out.println( "Not prime" );
        }
    }
}
