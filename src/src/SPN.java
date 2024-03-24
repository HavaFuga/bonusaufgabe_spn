import java.util.ArrayList;

public class SPN {
    private int r = 4;
    private int n = 4;
    private int m = 4;
    private int s = 32;
    private int l = 16;
    private String[] keys = new String[r+1];
    private ArrayList<String> y = new ArrayList();
    private char[] sbox = {'E', '4', 'D', '1', '2', 'F', 'B', '8', '3', 'A', '6', 'C', '5', '9', '0', '7'};
    private int[] bitpermutation = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};

    public static void main(String[] args) {
        String chiffretext = "000001001101001000001011101110000000001010001" +
                "111100011100111111101100000010100010100001110" +
                "10000000010011011001110010101110110000";
        String key = "00111010100101001101011000111111";

        new SPN(chiffretext, key);
    }


    public SPN (String chiffretext, String key) {
        //System.out.println(snp("0001001010001111", "00010001001010001000110000000000"));
        //snp("0001001010001111", "00010001001010001000110000000000");
        decrypt(chiffretext, key);
    }

    private void decrypt(String chiffretext, String key) {
        String text = "";

        // y-1 die ersten 16
        calculateY(chiffretext);
        String iv = y.get(0);

        for (int i = 0; i < chiffretext.length()/l -1; i++) {
            String mod  = String.valueOf((int) ((Integer.parseInt(iv, 2) + i) % Math.pow(2, l)));
            mod = Integer.toBinaryString(Integer.parseInt(mod));
            while (mod.length() < l) {
                mod = "0" + mod; // erweitere auf 8 bit
            }

            String e = snp(mod, key);
            text += xor(e, y.get(i+1));
        }
        System.out.println("Text: " + decryptBitString(text));
    }


    public String decryptBitString(String bitString) {
        // entferne 1 und Nullen
        int endIndex = bitString.lastIndexOf("1");
        String trimmedBitString = bitString.substring(0, endIndex);

        String text = "";
        for (int i = 0; i < trimmedBitString.length(); i += 8) {
            String block = trimmedBitString.substring(i, Math.min(i + 8, trimmedBitString.length()));
            int asciiValue = Integer.parseInt(block, 2);
            text += (char) asciiValue;
        }
        return text;
    }

    // berechne y's vom chiffretext
    private void calculateY(String c) {
        for (int i = 0; i < c.length()/l; i++) {
            this.y.add(i, c.substring(16*i, 16*i + 16));
        }
    }


    // snp gemäss Folie 1.29
    protected String snp(String x, String key) {
        // berechne Rundenschlüssel
        calculateK(key);

        // 1. weisschritt
        x = xor(x, keys[0]);

        for (int i = 0; i < r-1; i++ ) {
            // 2.
            // a.
            x = sbox(x);

            // b.
            x = bitpermutation(x);

            // c. rundschlüssel();
            x = xor(x, keys[i+1]);
        }

        // 3. letzte Runde
        // a.
        x = sbox(x);
        // c. rundschlüssel();
        x = xor(x, keys[r]);

        return x;
    }

    // schlüssel ausrechnen
    private void calculateK(String key) {
        // K(k, i) bestehe aus den 16 aufeinanderfolgenden Bits von k beginnend bei Position 4i
        for (int i = 0; i < r+1; i++) {
            keys[i] = key.substring(4*i, 4*i + 16);
        }
    }

    // bitpermutation wie auf folien
    private String bitpermutation(String x) {
        char[] array = x.toCharArray();
        char[] permutedArray = new char[array.length];
        for (int i = 0; i < array.length; i++ ) {
            permutedArray[i] = array[bitpermutation[i]];
        }

        return new String(permutedArray);
    }

    // sbox wie auf folien
    private String sbox(String x) {
        String result = "";
        for (int i = 0; i < x.length(); i +=4 ) {
            String substring = x.substring(i, i + 4);

            String binaryValue = Integer.toBinaryString(Character.digit(sbox[Integer.parseInt(substring, 2)], 16));
            while (binaryValue.length() < 4) {
                binaryValue = "0" + binaryValue; // binary erweitern auf 4
            }

            result += binaryValue;
        }
        return result;
    }

    // xor mit strings
    private String xor(String a, String b)  {
        if (a.length() != b.length()) throw new RuntimeException("XOR a und b sind nicht gleichlang");

        String result = "";
        for (int i = 0; i < a.length(); i++) {
            result += (a.charAt(i) == b.charAt(i)) ? '0' : '1';
        }
        return result;
    }

    private void encrypt() {
        //tbd
    }
}
