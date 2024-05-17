public class HashFunction {

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

    public char digitSelection(String asciiValues) {
        return asciiValues.charAt(0);
    }

    public char midSquare(String asciiValues) {
         int left = 0;
         int right = asciiValues.length() - 1;
         int middle = (left + right) / 2;
         return asciiValues.charAt(middle);
    }

    public static void main(String[] args) {
        HashFunction user = new HashFunction();
        String input = "ranmie";
        String value = user.wordToAscii(input);
        System.out.println("ASCII value: " + value);
        int fold = user.foldingAdding(value);
        System.out.println("Folding and Adding: " + fold);
        int modulo = user.moduloFunction(value);
        System.out.println("Modulo: " + modulo);
        char digit = user.digitSelection(value);
        System.out.println("Digit: " + digit);
        char mid = user.midSquare(value);
        System.out.println("Midsquare: " + mid);
    }
}
