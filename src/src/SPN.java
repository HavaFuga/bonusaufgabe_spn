import java.math.BigInteger;
import java.util.Arrays;

public class SPN {
    private int r = 4;
    private int n = 4;
    private int m = 4;
    private int s = 32;
    private String[] keys;
    private String[] x;
    private int[] sbox = {'E', '4', 'D', '1', '2', 'F', 'B', '8', '3', 'A', '6', 'C', '5', '9', '0', '7'};
    private int[] bitpermutation = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};





    public static void main(String[] args) {
        System.out.println("Hello world!");
        new SPN("", "HELLo");

    }


    public SPN (String key, String text) {
        snp(key, text);




    }

    private void snp(String key, String text) {
        // Dabei wird ein Text zunächt ASCII kodiert
        createBitstring(text);


        // berechne Rundenschlüssel
        //calculateK(key);


        for (int i = 0; i < r-1; i++ ) {
            // 1. weisschritt
            //xor()

            // 2.
            // a.
            sbox();

            // b.
            bitpermutation();

            // c.
            rundschlüssel();
        }

        // 3. letzte Runde
        weisschritt();
        rundschlüssel();

        return;
    }

    private void createBitstring(String text) {

        for (int i = 0; i < text.length(); i++) {

            StringBuilder bitString2 = new StringBuilder();
            char character = text.charAt(i);
            bitString2.append(Integer.toBinaryString((int) character)); // asciivalue ist dann (int) character

            // ... eine 1 drangehängt + so viele Nullen, bis Gesamtlänge durch 16 teilbar ist.
            bitString2.insert(0,1);
            while (bitString2.length() % 16 != 0) {
                bitString2.insert(0,0);
            }
            System.out.println(bitString2);
            System.out.println(bitString2.toString());
            x[i] = bitString2.toString();
        }

    }

    public void dechiffre() {
        // berechne K'


    }

    private void calculateK(String key) {
        // K(k, i) bestehe aus den 16 aufeinanderfolgenden Bits von k beginnend bei Position 4i
        for (int i = 0; i < r; i++) {
            keys[i] = key.substring(4*i, 4*1 + 16);
        }
    }

    private void rundschlüssel() {
    }

    private void bitpermutation() {
    }

    private void sbox() {
    }

    private void weisschritt() {
    }

    /**
     * help from
     * https://stackoverflow.com/questions/5126616/xor-operation-with-two-strings-in-java
     */
    private byte[] xor(byte[] a, byte[] b)  {
        if (a.length != b.length) throw new RuntimeException("XOR a and b have not same lenght");
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ b[i]);
        }
        return out;
    }

}
