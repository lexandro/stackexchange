public class Main {

    public static void main(String[] args) {
        System.out.println(clumpsInArray(new int[]{1, 2, 2, 3, 4, 4}));
        System.out.println(clumpsInArray(new int[]{1, 1, 2, 1, 1}));
        System.out.println(clumpsInArray(new int[]{1, 1, 1, 1, 1}));
    }

    public static int clumpsInArray(int[] numbers) {
        int lastNumber = numbers[0] - 1;  // make sure we are not equal with the first element
        int clumpCount = 0;
        boolean isClumpAlreadyCounted = false;
        for (int number : numbers) {
            if (!isClumpAlreadyCounted && number == lastNumber) {
                isClumpAlreadyCounted = clumpCount++ > 0; // Two in one :) I found it funny
            } else if (number != lastNumber) {
                lastNumber = number;
                isClumpAlreadyCounted = false;
            }
        }
        return clumpCount;
    }
}
