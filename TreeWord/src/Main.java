import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("Data.txt");

        Scanner scanner = new Scanner(file);
        Scanner scanner2 = new Scanner(file);
        int length = getPlacement(scanner.nextLine());
        while (scanner.hasNextLine()) {
            int length2 = getPlacement(scanner.nextLine());
            if (length2 > length) {
                length = length2;
            }
        }

        String[] supportArray = new String[length + 1];

        while (scanner2.hasNextLine()) {
            String a = scanner2.nextLine();
            supportArray[getPlacement(a)] = String.valueOf(a.charAt(0));
        }

        scanner.close();
        scanner2.close();



        int[] leaves = getLeaves(supportArray);


        int index = getLowestAlphabetically(supportArray, leaves);


        String endString = concatString(index, supportArray);

        System.out.println(endString);


    }


    static int getLowestAlphabetically(String[] supportArray, int[] leaves) {
        int lowestAlphabetically = 0;
        int tempLowest = 0;
        int index = 0;
        for(int i =0; i < leaves.length; i++) {

            tempLowest = supportArray[leaves[i]].charAt(0);
            int tempLowestBackup = tempLowest;
            int lowestAlphabeticallyBackup = lowestAlphabetically;
            int a = leaves[i];
            int b = index;
            while (true) {
                if (tempLowest > lowestAlphabetically) {
                    tempLowest = tempLowestBackup;
                    lowestAlphabetically = tempLowest;
                    index = leaves[i];
                    break;
                } else if (tempLowest == lowestAlphabetically) {
                    a = (a-1)/2;
                    b = (b-1)/2;
                    if(a == 0 && b == 0) {
                        tempLowest = tempLowestBackup;
                        lowestAlphabetically = tempLowest;
                        index = leaves[i];
                        break;
                    }
                    if(b == 0 && a != 0 && tempLowest == supportArray[0].charAt(0)) {
                        tempLowest = tempLowestBackup;
                        lowestAlphabetically = tempLowest;
                        index = leaves[i];
                        break;
                    }
                    tempLowest = supportArray[a].charAt(0);
                    lowestAlphabetically = supportArray[b].charAt(0);
                } else {
                    lowestAlphabetically = lowestAlphabeticallyBackup;
                    break;
                }
            }
        }
        return index;
    }

    public static int[] getLeaves(String[] supportArray){
        int leaves = 0;
        for(int i = 0; i < supportArray.length; i++) {
            if((((i*2) + 1) >= supportArray.length) && (supportArray[i] != null)) {
                leaves++;
            } else if (supportArray[i] != null &&
                    (((i*2) + 1 < supportArray.length && supportArray[(i*2) + 1] == null)
                            && ((i*2) + 2 < supportArray.length && supportArray[(i*2) + 2] == null))
            ) {
                leaves++;
            }
        }
        int[] leavesArray = new int[leaves];
        int count = 0;
        for(int i = 0; i < supportArray.length; i++) {
            if((i*2) + 1 >= supportArray.length && supportArray[i] != null) {
                leavesArray[count] = i;
                count++;
            } else if (supportArray[i] != null &&
                    (((i*2) + 1 < supportArray.length && supportArray[(i*2) + 1] == null)
                            && ((i*2) + 2 < supportArray.length && supportArray[(i*2) + 2] == null))
            ) {
                leavesArray[count] = i;
                count++;
            }
        }
        return leavesArray;
    }

    public static String concatString(int i, String[] supportArray) {
        int a = i;
        String finalString = "";
        while (i >= 0) {
            finalString = finalString + supportArray[a];
            if (a == 0) {
                break;
            }
            a = (a-1)/2;
            i--;
        }
        return finalString;
    }

    public static int getPlacement(String s) {
        int placement = 0;
        int temp = 0;
        for(int i = 0; i <= s.length() - 3; i++) {
            if(i == 0) {
                temp = 0;
            } else if (i == 1) {
                temp = 2;
            } else {
                temp = temp * 2;
            }
            placement = placement + temp;
        }
        if(s.charAt(s.length() -1) == 'L' && !(s.equals("L"))) {
            placement = placement + 1;
        }
        if(s.charAt(s.length() - 1) == 'R' && !(s.equals("R"))) {
            placement = placement + 2;
        }

        int howMany = s.length() - 1;

        for(int i = 2; i <= howMany; i++){
            if(s.charAt(s.length() - i) == 'R') {
                placement = (int) (placement + Math.pow(2, i-1));
            }
        }
        return placement;
    }


}
