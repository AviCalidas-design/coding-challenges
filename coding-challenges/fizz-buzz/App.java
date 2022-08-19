public class App
{
    public static void main(String[] args)
    {
        System.out.println(fizzbuzz(100));
    }
    public static String fizzbuzz(int x)
    {
        String result = "";
        for(int i = 1; i < x+1 ; i++)
        {
            if(i % 3 == 0)
            {
                result += "Fizz";
            }
            if(i % 5 == 0)
            {
                result += "Buzz";
            }
            if(!(i%3 == 0 || i%5 == 0))
            {
                result += i;
            }
            if(i != x)
            {
                result += ", ";
            }
        }
        return result;
    }
}