public class App
{
    public static void main(String args[])
    {
        int[] arr = {4,7,6,14,199,66,43,2,665,4,33,7,65,912,164};
        printArray(sortArray(arr));
    }
    public static int[] sortArray(int[] arr)
    {
        int[] result = arr.clone();
        for(int i = 0; i < arr.length-1 ; i++)
        {
            for(int j = i+1; j < arr.length; j++)
            {
                if(result[j] < result[i])
                {
                    int temp = result[j];
                    result[j] = result[i];
                    result[i] = temp;
                }
            }
        }
        return result;
    }
    public static void printArray(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }
    }
}