package com.revature.util;
import java.util.Scanner;
public class InputUtil {
    Scanner scanner = new Scanner(System.in);

    public String retrieveString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }
    public Integer retrieveInt(String msg) {
        
        Integer x = 0;
        Boolean done = false;
        while (!done) {
            System.out.print(msg);
            try {
                x = Integer.parseInt(scanner.nextLine());
                done = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input.");
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return x;
    }
}
