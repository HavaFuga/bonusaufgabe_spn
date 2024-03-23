public class SPN {
    private int r = 4;
    private int n = 4;
    private int m = 4;
    private int s = 32;
    private String[] keys = new String[r+1];
    //private ArrayList x = new ArrayList();
    private char[] sbox = {'E', '4', 'D', '1', '2', 'F', 'B', '8', '3', 'A', '6', 'C', '5', '9', '0', '7'};
    private int[] bitpermutation = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};
    private String x = "";





    public static void main(String[] args) {
        System.out.println("Hello world!");
        new SPN("0001 0001 0010 1000 1000 1100 0000 0000", "HELLo");



    }


    public SPN (String key, String text) {
        // Dabei wird ein Text zunächt ASCII kodiert
        //createBitstring(text);
        snp("0001001010001111", "00010001001010001000110000000000");



    }

    protected void snp(String x, String key) {


        // berechne Rundenschlüssel
        calculateK(key);
        System.out.println("keys.length " + keys.length);
        this.x = x;


        // 1. weisschritt
        xor(x, keys[0]);
        System.out.println("keys[0] " + keys[0]);

        for (int i = 0; i < r-1; i++ ) {
            // 2.
            // a.
            sbox();

            // b.
            bitpermutation();

            // c. rundschlüssel();
            xor(this.x, keys[i+1]);
            System.out.println("keys[i+1] " + keys[i+1]);
        }

        // 3. letzte Runde
        // a.
        sbox();
        // c. rundschlüssel();
        System.out.println("keys[r] " + keys[r-1]);
        xor(this.x, keys[r]);

        System.out.println(this.x);
        return;
    }

    private void createBitstring(String text) {
        String bitString = "";

        for (int i = 0; i < text.length(); i++) {

            char character = text.charAt(i);
            String binaryString = Integer.toBinaryString((int) character);
            while (binaryString.length() < 8) {
                binaryString = "0" + binaryString;
            }

            //x.add(i, binaryString);
            bitString = bitString + binaryString;
        }

        // ... eine 1 drangehängt + so viele Nullen, bis Gesamtlänge durch 16 teilbar ist.
        bitString = "1" + bitString;

        while (bitString.length() % 16 != 0) {
            bitString = "0" + bitString;
        }
    }

    public void dechiffre() {
        // berechne K'


    }

    private void calculateK(String key) {
        // K(k, i) bestehe aus den 16 aufeinanderfolgenden Bits von k beginnend bei Position 4i
        for (int i = 0; i < r+1; i++) {
            keys[i] = key.substring(4*i, 4*i + 16);
            System.out.println("key.substring(4*i, 4*i + 16) " + key.substring(4*i, 4*i + 16));
        }
    }

    private void bitpermutation() {
        char[] array = x.toCharArray();
        char[] permutedArray = new char[array.length];
        for (int i = 0; i < array.length; i++ ) {
            permutedArray[i] = array[bitpermutation[i]];
        }

        x = new String(permutedArray);
    }

    private void sbox() {
        String result = "";
        for (int i = 0; i < x.length(); i +=4 ) {
            String substring = x.substring(i, i + 4);

            String binaryValue = Integer.toBinaryString(Character.digit(sbox[Integer.parseInt(substring, 2)], 16));
            while (binaryValue.length() < 4) {
                binaryValue = "0" + binaryValue; // Ensure each binary string has length 4
            }

            result += binaryValue;
        }
        x = result;
    }

    private void weisschritt() {
    }

    /**
     * help from
     * https://stackoverflow.com/questions/5126616/xor-operation-with-two-strings-in-java
     */
    private String xor(String a, String b)  {
        if (a.length() != b.length()) throw new RuntimeException("XOR a und b sind nicht gleichlang");

        String result = "";
        for (int i = 0; i < a.length(); i++) {
            result += (a.charAt(i) == b.charAt(i)) ? '0' : '1';
        }
        this.x = result;
        return result;
    }

    // fist add
}
