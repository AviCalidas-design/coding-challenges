public class App
{
    public static void main(String[] args)
    {
        System.out.println(fizzbuzz(100));
    }
    public static String fizzbuzz(int x)
    {
        StringBuilder result = new StringBuilder();
        for(int i = 1; i < x+1 ; i++)
        {
            if(i % 3 == 0)
            {
                result.append("Fizz");
            }
            if(i % 5 == 0)
            {
                result.append("Buzz");
            }
            if(!(i%3 == 0 || i%5 == 0))
            {
                result.append(i);
            }
            if(i != x)
            {
                result.append(", ");
            }
        }
        return result.toString();
    }
}