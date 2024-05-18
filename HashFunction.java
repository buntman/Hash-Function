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
        return key;
    }
    
    public int moduloFunction(String asciiValues) {
         int key = 0;
         int values = Integer.parseInt(asciiValues);
         key = values % 47;
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
             String k = Integer.toString(key);
             char val = k.charAt(0);
             key = Integer.parseInt(String.valueOf(val));
         }
         return key;
    }

    public void addElements(String word) {
         String asciiVal = wordToAscii(word);

         int index = foldingAdding(asciiVal);
         table[index] = word;
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + " " + table[i]);
        }
    }

    public static void main(String[] args) {
        HashFunction user = new HashFunction();
        String input = "ara";
        String value = user.wordToAscii(input);
        System.out.println("ASCII: " + value);
//        user.addElements(input);
//        user.printTable();



        int digit = user.digitSelection(value);
        System.out.println("Digit key: " + digit);

        int mid = user.midSquare(value);
        System.out.println("Midsquare: " + mid);
    }
}
