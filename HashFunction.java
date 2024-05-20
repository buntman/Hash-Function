import java.util.Scanner;

public class HashFunction {

    static class Node {
        String word;
        Node next;

        Node(String word) {
            this.word = word;
            this.next = null;
        }

    }
    Node [] table = new Node[50];



     public String wordToAscii(String word) {
        StringBuilder asciiValues = new StringBuilder();
        word = word.replaceAll("\\s+","");
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            asciiValues.append((int) ch);
        }
        return asciiValues.toString();
    }

    public int foldingAdding(String asciiValues) {
         int key = 0;
        for (int i = 0; i < asciiValues.length(); i++) {
            key += Character.getNumericValue(asciiValues.charAt(i));
        }
        if(key >= 50 && key <= 99) { //two digit addition
           key =  key / 10 + key % 10; //if it is outside of range, just add the numbers
        }
        return key;
    }
    
    public int moduloFunction(String asciiValues) {
         int prime = 47;
         long values = Long.parseLong(asciiValues);
         return (int) (values % prime);
    }

    public int digitSelection(String asciiValues) { //First digit is the key
         char x = asciiValues.charAt(0);
         return Integer.parseInt(String.valueOf(x));
    }

    public int midSquare(String asciiValues) {
         int middle = (asciiValues.length() - 1) / 2;
         int key = Integer.parseInt(String.valueOf(asciiValues.charAt(middle)));
         key *= key;
         if(key >= 50) {  //if the squared value is outside of range, get the left side value
             key = key / 10;
         }
         return key;
    }


    public int linearProbing(int index) {
         while (table[index] != null) {
             index+=1; //move one step until there's an available slot
         }
         return index;
    }

    public int secondHashing(String asciiValues, int index) {
         int secondPrime = 43;
        while (table[index] != null) {
            long values = Long.parseLong(asciiValues);
            index = (int) (values % secondPrime);
        }
        return index;
    }



    public void collisionMethod(String word, String asciiValues, int index, int resolution) {
         if(table[index] == null) {
             return; //no resolution, just return the original index
         }
         if(resolution == 1) {
             Node value = table[index];
             while (value.next != null) {
                 value = value.next;
             }
             value.next = new Node(word);
         }
         else if(table[index] != null && resolution == 2) {
            index = linearProbing(index);
            table[index] = new Node(word);
        } else if(table[index] != null && resolution == 3) {
            index = secondHashing(asciiValues, index);
            table[index] = new Node(word);
        }
    }

    public void insertWords(String word, String asciiValues, int hashChoice, int resolution) {
         if(hashChoice == 1) {
             int index = foldingAdding(asciiValues);
             if(table[index] == null) {
                 table[index] = new Node(word);
             } else {
                 collisionMethod(word, asciiValues, index, resolution);
             }
         } else if(hashChoice == 2) {
             int index = moduloFunction(asciiValues);
             if(table[index] == null) {
                 table[index] = new Node(word);
             } else {
                 collisionMethod(word, asciiValues, index, resolution);
             }
         } else if(hashChoice == 3) {
             int index = digitSelection(asciiValues);
             if(table[index] == null) {
                 table[index] = new Node(word);
             } else {
                 collisionMethod(word, asciiValues, index, resolution);
             }
         } else if(hashChoice == 4) {
             int index = midSquare(asciiValues);
             if(table[index] == null) {
                 table[index] = new Node(word);
             } else {
                 collisionMethod(word, asciiValues, index, resolution);
             }
         }
    }


    public void addElements() {
         Scanner scan = new Scanner(System.in);
         System.out.println("Hash Function Method");
         System.out.println("1. Add and Fold\n2. Modulo Function\n3. Digit Selection\n4. Midsquare\n");
        System.out.print("Enter hash function choice: ");
         int choice = scan.nextInt();

        System.out.println();

        System.out.println("Collision Resolution Method");
        System.out.println("1. Bucket Chaining\n2. Linear Probing\n3. Second Hash Function\n");
        System.out.print("Enter Collision Resolution: ");
        int resolution = scan.nextInt();

        System.out.print("How many words do you want to enter? (1-50 only): ");
        int numWords = scan.nextInt();
        scan.nextLine();


        for (int i = 0; i < numWords; i++) {
            System.out.print("Word #" + (i + 1) + ": ");
            String word = scan.nextLine();
            String asciiValues = wordToAscii(word);
            insertWords(word, asciiValues, choice, resolution);
        }
        printTable();
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.printf("%02d:%n", i);
            Node current = table[i];
            while (current != null) {
                System.out.print(current.word + " ");
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        HashFunction user = new HashFunction();

        user.addElements();
    }
}
