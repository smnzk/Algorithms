import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("Numbers.txt");

        Scanner scanner = new Scanner(file);

        int same = 0;
        int add = 0;
        int length = 1;
        int maxLength = 0;
        int bigger = 5;

        int sum = 0;
        int maxSum = 0;
        int temp = 0;


        int x = scanner.nextInt();

        if(scanner.hasNextInt()) {

            int y = scanner.nextInt();

            if (y > x) {
                length++;
                bigger = 1;
                maxLength = length;
            } else if (y < x) {
                length++;
                bigger = 0;
                maxLength = length;
            } else if (y == x) {
                length++;
                maxLength = length;
                same = 1;
            }

            sum = x + y;
            maxSum = sum;

            while (scanner.hasNextLine()) {
                x = scanner.nextInt();
                if (same == 1 && x > y) {
                    bigger = 1;
                    length++;
                    same = 0;
                    maxLength = length;
                    sum = sum + x;
                    maxSum = sum;
                } else if (same == 1 && x < y) {
                    bigger = 0;
                    length++;
                    same = 0;
                    maxLength = length;
                    sum = sum + x;
                    maxSum = sum;
                } else if (same == 1 && x == y) {
                    length++;
                    maxLength = length;
                    sum = sum + x;
                    maxSum = sum;
                } else if (bigger == 1 && x > y) {
                    temp = 0;
                    add = 0;
                    length++;
                    sum = sum + x;
                    if (length > maxLength) {
                        maxLength = length;
                        maxSum = sum;
                    }
                } else if (bigger == 1 && x < y) {
                    bigger = 0;
                    length = 2 + add;
                    add = 0;
                    sum = x + y + temp;
                    temp = 0;
                } else if (bigger == 0 && x < y) {
                    temp = 0;
                    add = 0;
                    length++;
                    sum = sum + x;
                    if (length > maxLength) {
                        maxLength = length;
                        maxSum = sum;
                    }
                } else if (bigger == 0 && x > y) {
                    bigger = 1;
                    length = 2 + add;
                    add = 0;
                    sum = x + y + temp;
                    temp = 0;
                } else if (bigger == 1 && (x == y)) {
                    add++;
                    length++;
                    sum = sum + x;
                    if (length > maxLength) {
                        maxLength = length;
                        maxSum = sum;
                    }
                    temp = temp + x;
                } else if (bigger == 0 && (x == y)) {
                    add++;
                    length++;
                    sum = sum + x;
                    if (length > maxLength) {
                        maxLength = length;
                        maxSum = sum;
                    }
                    temp = temp + x;
                }
                y = x;
            }
        } else {
            maxLength = 1;
            maxSum = x;
        }
        System.out.print(maxLength + " " + maxSum);
    }
}