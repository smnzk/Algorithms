import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        File file = new File("Data.txt");
        Scanner scanner = new Scanner(file);
        int operations = Integer.parseInt(scanner.nextLine());
        String [] startingNumbers = scanner.nextLine().split("\\s+");


        int placement = 0;
        int length = 0;

        SimpleTree simpleTree = new SimpleTree();

        for(int i = 0; i < startingNumbers.length; i++) {
            simpleTree.initiateTree(Integer.parseInt(startingNumbers[i]), placement);
            placement++;
        }

        int pointer = 0;
        length = startingNumbers.length;
        int tempPointer = pointer;


        Node currentNode;

        int empty = 0;

        while (operations > 0 ) {

            try {
                currentNode = simpleTree.findNode(pointer);
            } catch (NullPointerException e) {
                break;
            }


            if (currentNode.value % 2 == 1) {

                simpleTree.addNode(currentNode.value - 1, pointer + 1);
                length++;
                pointer = changePointerAdd(pointer, length, currentNode.value);

            } else if (currentNode.value % 2 == 0) {

                tempPointer = pointer;

                if(length == 1) {
                    empty = 1;
                    break;
                }

                if (pointer == length - 1) {
                    pointer = changePointerDelete(pointer, length, simpleTree.findNode(0).value);
                    simpleTree.deleteNode(0);
                    simpleTree.decrementPlacement(0);
                } else {
                    pointer = changePointerDelete(pointer, length, simpleTree.findNode(pointer + 1).value);
                    simpleTree.deleteNode(tempPointer + 1);
                    simpleTree.decrementPlacement(tempPointer + 1);
                }
                length--;
            }
            operations--;
        }




        if(empty == 1) {
            System.out.println();
        } else {
            int[] numbers = new int[length];
            Node node = simpleTree.root;
            while (node != null) {
                numbers[node.placement] = node.value;
                node = node.child;
            }

            for (int i = pointer; i < numbers.length; i++) {
                System.out.print(numbers[i] + " ");
            }
            for (int i = 0; i < pointer; i++) {
                System.out.print(numbers[i] + " ");
            }

        }

    }

    public static int changePointerAdd (int pointer, int length, int nodeValue) {
        int tempPointer = pointer;
        pointer = (nodeValue % length) + tempPointer;
        if (pointer >= length) {
            pointer = pointer - length;
        }
        return pointer;
    }


    public static int changePointerDelete (int pointer, int length, int nodeValue) {
        int tempPointer = pointer;
        if (pointer == length - 1) {
            if (length == 2) {
                pointer = 0;
            }
            if (length == 1) {
                pointer = 0;
            }
            else {
                if(nodeValue % (length - 1) == 0) {
                    pointer = pointer - 1;
                } else {
                    pointer = (nodeValue % (length - 1)) - 1;
                }
            }
        } else {
            pointer = (nodeValue % (length - 1) + tempPointer);
            if (pointer >= length - 1) {
                pointer = pointer - (length - 1);
            }
        }
        if (pointer < 0) {
            pointer = 0;
        }
        return pointer;
    }

}



class SimpleTree {

    Node root;
    Node lastNode;

    public void initiateTree(int value, int placement) {

        Node nodeToAdd = new Node(value, placement);

        if(this.root == null) {
            this.root = nodeToAdd;
            lastNode = nodeToAdd;
        }

        else {
            lastNode.child = nodeToAdd;
            lastNode = nodeToAdd;
        }

    }

    public void deleteNode (int placement) {

        Node parent = root;
        Node node;

        if(root.placement == placement) {

            Node node1 = root;

            Node laterNode = node1;

            while (node1.child != null) {
                laterNode = node1;
                node1 = node1.child;
            }

            int a = node1.value;
            int b = node1.placement;

            //this.findNode(node1.placement).placement = -1;

            root.value = a;
            root.placement = b;

            //Node no = root;
            Node no = laterNode;

            while (true) {
                try {
                    if(no.child.placement == b) { //= -1
                        no.child = null;
                        break;
                    }
                } catch (NullPointerException e) {
                    break;
                }
                no = no.child;
            }

            return;
        }

        while (true) {

            if(parent.child.placement == placement) {

                //Node node1 = root;
                Node node1 = parent.child;
                Node laterNode = node1;

                while (node1.child != null) {
                    laterNode = node1;
                    node1 = node1.child;
                }


                int a = node1.value;
                int b = node1.placement;

                if(parent.child.placement == b) {
                    parent.child = null;
                    return;
                }


                //this.findNode(node1.placement).placement = -1;

                parent.child.value = a;
                parent.child.placement = b;


                //Node no = root;
                Node no = laterNode;

                while (true) {
                    try {
                        if(no.child.placement == b) { //= -1
                            no.child = null;
                            break;
                        }
                    } catch (NullPointerException e) {
                        break;
                    }
                    no = no.child;
                }
                return;
            }
            parent = parent.child;
        }

    }


    public void incrementPlacement(int placement) {

        Node node = root;

        while (node != null) {
            if(node.placement >= placement) {
                node.placement ++;
            }
            node = node.child;
        }
    }

    public void decrementPlacement(int placement) {

        Node node = root;

        while (node != null) {
            if(node.placement > placement) {
                node.placement --;
            }
            node = node.child;
        }

    }


    public void addNode(int value, int placement) {

        Node nodeToAdd = new Node(value, placement);

        this.incrementPlacement(placement);

        if(root == null) {

            root = nodeToAdd;

        } else if(root.value == -1 && root.placement == -1) {

            root = nodeToAdd;

        } else {

            Node parent = root;

            while (true) {
                if (parent.child == null) {
                    parent.child = nodeToAdd;
                    return;
                } else if (parent.child.value == -1 && parent.child.placement == -1) {
                    parent.child.value = value;
                    parent.child.placement = placement;
                    return;
                }
                parent = parent.child;
            }

        }

    }


    public Node findNode(int placement) {

        Node node = root;

        while (node.placement != placement) {
            node = node.child;
        }

        return node;

    }

}



class Node {

    int value;
    int placement;

    Node child;

    public Node (int value, int placement) {
        this.value = value;
        this.placement = placement;
    }

    @Override
    public String toString() {
        return value + " " + placement;
    }
}
