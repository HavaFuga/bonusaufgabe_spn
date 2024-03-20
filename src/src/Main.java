public class Main {
    private int r;
    private int n;
    private int m;
    
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    
    private void spn_algorith() {
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

    private void calculateK() {
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