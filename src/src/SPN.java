import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class SPN {
    private int r;
    private int n;
    private int m;

    private byte[] k;


    public SPN (int r, int n, int m, int sbox, int bitpermutation, String k) {
        this.k = new BigInteger(k, 2).toByteArray();

        System.out.println(this.k);
        System.out.println(Arrays.toString(this.k));


    }

    private void enchiffre() {
        // berechne Rundenschlüssel
        calculateK();


        for (int i = 0; i < r-1; i++ ) {
            // 1.
            weisschritt();

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

    public void dechiffre() {
        // berechne K'


    }

    private void calculateK() {
        // K(k, i) bestehe aus den 16 aufeinanderfolgenden Bits von k beginnend bei Position 4i

    }

    private void rundschlüssel() {
    }

    private void bitpermutation() {
    }

    private void sbox() {
    }

    private void weisschritt() {
    }

}
