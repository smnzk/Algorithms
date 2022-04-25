import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        int k = 6;

        File file = new File("irisTraining.txt");

        ArrayList<Flower> flowersTraining = new ArrayList<>();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            flowersTraining.add(new Flower(scanner.nextLine()));
        }

        for (Flower f : flowersTraining) {
            f.setType(f.attributes[f.attributes.length - 1]);
        }


        ArrayList<Flower> flowersTest = new ArrayList<>();
        File file2 = new File("irisTest.txt");
        Scanner scanner2 = new Scanner(file2);
        while (scanner2.hasNextLine()) {
            flowersTest.add(new Flower(scanner2.nextLine()));
        }

        int total = 0;
        int correct = 0;

        for(Flower flower : flowersTest) {

            HashMap<Double, Flower> distances = new HashMap<>();
            ArrayList<Double> distancesList = new ArrayList<>();


            for (Flower f : flowersTraining) {
                distances.put(calculateDistance(flower, f), f);
                distancesList.add(calculateDistance(flower, f));
            }

            ArrayList<Double> smallestDistances = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                double smallest = distancesList.get(0);
                for (Double dist : distancesList) {
                    if (dist < smallest) {
                        smallest = dist;
                    }
                }
                int remover = 0;
                for (Double dist : distancesList) {
                    if (dist == smallest) {
                        distancesList.remove(remover);
                        break;
                    }
                    remover++;
                }
                smallestDistances.add(smallest);
            }

            ArrayList<Flower> closestFlowers = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                closestFlowers.add(distances.get(smallestDistances.get(i)));
            }


            ArrayList<String> types = new ArrayList<>();
            for (Flower f : closestFlowers) {
                types.add(f.type);
            }


            HashSet<TypeAmount> typeAmounts = new HashSet<>();

            for(String s : types) {
                int amount = 0;
                boolean add = true;
                for(String str : types) {
                    if (s.equals(str)) {
                        amount++;
                    }
                }
                for(TypeAmount typeAmount : typeAmounts) {
                    if (s.equals(typeAmount.type)) {
                        add = false;
                        break;
                    }
                }
                if (add) {
                    typeAmounts.add(new TypeAmount(s, amount));
                }
            }

            System.out.println(typeAmounts);


            int largestAmount = 0;
            for (TypeAmount i : typeAmounts) {
                if (i.amount > largestAmount) {
                    largestAmount = i.amount;
                }
            }

            int howManyTimesThisAmount = 0;

            for (TypeAmount i : typeAmounts) {
                if (i.amount == largestAmount) {
                    howManyTimesThisAmount++;
                }
            }


            if (howManyTimesThisAmount == 1) {
                for(TypeAmount typeAmount : typeAmounts) {
                    if (typeAmount.amount == largestAmount) {
                        flower.type = typeAmount.type;
                    }
                }
            } else {
                ArrayList<TypeAmount> closestTypes = new ArrayList<>();
                for (TypeAmount typeAmount : typeAmounts) {
                    if (typeAmount.amount == largestAmount) {
                        closestTypes.add(typeAmount);
                    }
                }
                flower.type = closestTypes.get(0).type;
            }

            System.out.println("Typ wg programu: " + flower.type);
            System.out.println("Poprawny typ: " + flower.attributes[flower.attributes.length - 1]);
            System.out.println("*****************************************");
            if(flower.type.equals(flower.attributes[flower.attributes.length - 1])) {
                correct++;
            }
            total++;
        }

        System.out.println("Correct: " + correct);
        System.out.println("Total: " + total);

        System.out.println("===================");


        Scanner scanner3 = new Scanner(System.in);

        System.out.println("Podaj atrybuty: ");
        String atrs = scanner3.nextLine();

        Flower userFlower = new Flower(atrs);

        HashMap<Double, Flower> distances = new HashMap<>();
        ArrayList<Double> distancesList = new ArrayList<>();


        for (Flower f : flowersTraining) {
            distances.put(calculateDistance(userFlower, f), f);
            distancesList.add(calculateDistance(userFlower, f));
        }

        ArrayList<Double> smallestDistances = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            double smallest = distancesList.get(0);
            for (Double dist : distancesList) {
                if (dist < smallest) {
                    smallest = dist;
                }
            }
            int remover = 0;
            for (Double dist : distancesList) {
                if (dist == smallest) {
                    distancesList.remove(remover);
                    break;
                }
                remover++;
            }
            smallestDistances.add(smallest);
        }

        ArrayList<Flower> closestFlowers = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            closestFlowers.add(distances.get(smallestDistances.get(i)));
        }


        ArrayList<String> types = new ArrayList<>();
        for (Flower f : closestFlowers) {
            types.add(f.type);
        }


        HashSet<TypeAmount> typeAmounts = new HashSet<>();

        for(String s : types) {
            int amount = 0;
            boolean add = true;
            for(String str : types) {
                if (s.equals(str)) {
                    amount++;
                }
            }
            for(TypeAmount typeAmount : typeAmounts) {
                if (s.equals(typeAmount.type)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                typeAmounts.add(new TypeAmount(s, amount));
            }
        }

        System.out.println(typeAmounts);


        int largestAmount = 0;
        for (TypeAmount i : typeAmounts) {
            if (i.amount > largestAmount) {
                largestAmount = i.amount;
            }
        }

        int howManyTimesThisAmount = 0;

        for (TypeAmount i : typeAmounts) {
            if (i.amount == largestAmount) {
                howManyTimesThisAmount++;
            }
        }


        if (howManyTimesThisAmount == 1) {
            for(TypeAmount typeAmount : typeAmounts) {
                if (typeAmount.amount == largestAmount) {
                    userFlower.type = typeAmount.type;
                }
            }
        } else {
            ArrayList<TypeAmount> closestTypes = new ArrayList<>();
            for (TypeAmount typeAmount : typeAmounts) {
                if (typeAmount.amount == largestAmount) {
                    closestTypes.add(typeAmount);
                }
            }
            userFlower.type = closestTypes.get(0).type;
        }

        System.out.println("Typ wg programu: " + userFlower.type);
        System.out.println("Poprawny typ: " + userFlower.attributes[userFlower.attributes.length - 1]);
        System.out.println("*****************************************");
    }


    public static double calculateDistance(Flower flower1, Flower flower2) {
        double distance = 0;
        double atr1 = 0;
        double atr2 = 0;
        double temp = 0;
        for(int i = 0; i < flower1.attributes.length - 1; i ++) {
            atr1 = Double.parseDouble(flower1.attributes[i]);
            atr2 = Double.parseDouble(flower2.attributes[i]);
            temp = Math.pow((atr2-atr1), 2);
            distance = distance + temp;
        }
        return distance;
    }


}
