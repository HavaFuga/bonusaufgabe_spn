import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class SPN {
    private int r = 4;
    private int n = 4;
    private int m = 4;
    private int s = 32;
    private int l = 16;
    private String[] keys = new String[r+1];
    //private ArrayList x = new ArrayList();
    private char[] sbox = {'E', '4', 'D', '1', '2', 'F', 'B', '8', '3', 'A', '6', 'C', '5', '9', '0', '7'};
    private int[] bitpermutation = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};
    private String x = "";





    public static void main(String[] args) {
        System.out.println("Hello world!");
        new SPN("HELLo", "00010001001010001000110000000000");



    }


    public SPN (String text, String key) {
        // snp("0001001010001111", "00010001001010001000110000000000"); // test


        // decrypt(createBitstring(text), key);
        decrypt("00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000", key);
    }

    private void decrypt(String chiffretext, String key) {
        String text = "";

        String random = generateRandomBitString();

        System.out.println("random " + random);

        for (int i = 0; i < chiffretext.length(); i+= l) {
            String block = chiffretext.substring(i, Math.min(i + l, chiffretext.length()));
            String bitAddition = Integer.toBinaryString(Integer.parseInt(random, 2) + i / l);

            while (bitAddition.length() < l) {
                bitAddition = "0" + bitAddition;
            }

            String encryptedBlock = xor(snp(bitAddition, key), block);
            text += encryptedBlock;
        }

        System.out.println("text " + text);
    }

    // help https://docs.oracle.com/javase/7/docs/api/java/util/Random.html#nextInt%28int%29
    private String generateRandomBitString() {
        int randomNumber = new Random().nextInt((int) (Math.pow(2, l)-1));
        String binaryString = Integer.toBinaryString(randomNumber);
        while (binaryString.length() < l) {
            binaryString = "0" + binaryString; // erweitere dass binary gleiche Länge
        }
        return binaryString;
    }

    private String snp(String x, String key) {
        // berechne Rundenschlüssel
        calculateK(key);
        this.x = x;


        // 1. weisschritt
        xor(x, keys[0]);

        for (int i = 0; i < r-1; i++ ) {
            // 2.
            // a.
            sbox();

            // b.
            bitpermutation();

            // c. rundschlüssel();
            xor(x, keys[i+1]);
        }

        // 3. letzte Runde
        // a.
        sbox();
        // c. rundschlüssel();
        xor(x, keys[r]);

        return this.x;
    }

    private String createBitstring(String text) {
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

        return bitString;
    }

    private void calculateK(String key) {
        // K(k, i) bestehe aus den 16 aufeinanderfolgenden Bits von k beginnend bei Position 4i
        for (int i = 0; i < r+1; i++) {
            keys[i] = key.substring(4*i, 4*i + 16);
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
                binaryValue = "0" + binaryValue; // erweitere dass binary gleiche Länge
            }

            result += binaryValue;
        }
        x = result;
    }

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
