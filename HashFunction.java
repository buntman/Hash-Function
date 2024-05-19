import java.util.Scanner;

public class HashFunction {
    String [] table = new String[50];

     public String wordToAscii(String word) {
        StringBuilder asciiValues = new StringBuilder();
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
         int key;
         long values = Long.parseLong(asciiValues);
         long dummy = (values % 47);
         key = (int) dummy;
         return key;
    }

    public int digitSelection(String asciiValues) { //First digit is the key
         char x = asciiValues.charAt(0);
         return Integer.parseInt(String.valueOf(x));
    }

    public int midSquare(String asciiValues) {
         int left = 0;
         int right = asciiValues.length() - 1;
         int middle = (left + right) / 2;
         char x = asciiValues.charAt(middle);
         int key = Integer.parseInt(String.valueOf(x));
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
        while (table[index] != null) {
            long values = Long.parseLong(asciiValues);
            long dummy = (values % 43);
            index = (int) dummy;
        }
        return index;
    }

    public int collisionMethod(String asciiValues, int index, int resolution) {
         if(table[index] == null) {
             return index; //no resolution, just return the original index
         }
        if(table[index] != null && resolution == 2) {
            index = linearProbing(index);
        } else if(table[index] != null && resolution == 3) {
            index = secondHashing(asciiValues, index);
        }
        return index;
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


        for (int i = 0; i < numWords; i++) {
            System.out.print("Entry #: ");
            String word = scan.next();
            String asciiValues = wordToAscii(word);

            if(choice == 1) {
                int index = foldingAdding(asciiValues);
                int key = collisionMethod(asciiValues, index, resolution);
                table[key] = word;
            } else if(choice == 2) {
                int index = moduloFunction(asciiValues);
                int key = collisionMethod(asciiValues, index, resolution);
                table[key] = word;
            } else if (choice == 3) {
                int index = digitSelection(asciiValues);
                int key = collisionMethod(asciiValues, index, resolution);
                table[key] = word;
            } else if(choice == 4) {
                int index = midSquare(asciiValues);
                int key = collisionMethod(asciiValues, index, resolution);
                table[key] = word;
            }
        }
        printTable();
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            if(table[i] == null) {
                System.out.println(i);
            } else {
                System.out.println(table[i]);
            }
        }
    }

    public static void main(String[] args) {
        HashFunction user = new HashFunction();

        user.addElements();
    }
}
