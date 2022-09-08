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
        char end = input.charAt(input.length()-1);
        String[] realInput = input.substring(0,input.length()-1).split(" ");
        if(Character.isLetterOrDigit(end))
        {
            realInput = input.substring(0,input.length()).split(" ");
        }
        String result = "";
        for(int i = 0; i < realInput.length ; i++)
        {
            result += (pigLatin(realInput[i]) + " ");
        }
        if(!Character.isLetterOrDigit(end))
        {
            result += end;
        }
        for(int i = 0; i < input.length()-1; i++)
        {
            if(!(Character.isLetterOrDigit(input.charAt(i)) || input.charAt(i) == ' '))
            {
                System.out.println("Invalid input");
                System.exit(0);
            }
        }
        System.out.println(result);
        // int val = (int)Integer.parseInt(input);
        // if(val % 2 == 0)
        // {
        //     System.out.println( "Even" );
        // }
        // else
        // {
        //     System.out.println( "Odd" );
        // }
        // boolean prime = true;
        // for(int i = 2; i <= val / 2.0 ; i++)
        // {
        //     if(val % i == 0)
        //     {
        //         prime = false;
        //     }
        // }
        // if(prime)
        // {
        //     System.out.println( "Prime" );
        // }
        // else
        // {
        //     System.out.println( "Not prime" );
        // }
        scan.close();
    }
    public static String pigLatin(String input)
    {
        char first = input.charAt(0);
        String cut = input.substring(1);
        return ((cut + first) + "ay");
    }
}
