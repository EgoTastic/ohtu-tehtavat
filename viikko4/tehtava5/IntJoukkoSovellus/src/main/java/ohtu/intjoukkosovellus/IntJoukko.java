
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukonLuvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        
        alustaJoukko(OLETUSKAPASITEETTI, OLETUSKASVATUS);
        
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }

        alustaJoukko(kapasiteetti, OLETUSKASVATUS);

    }
    

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }

        alustaJoukko(kapasiteetti, kasvatuskoko);

    }

    private void alustaJoukko(int kapasiteetti, int kasvatuskoko) {
        this.joukonLuvut = new int[kapasiteetti];
        for (int i = 0; i < joukonLuvut.length; i++) {
            joukonLuvut[i] = 0;
        }
        this.kasvatuskoko = kasvatuskoko;
        this.alkioidenLkm = 0;
    }

    public boolean lisaa(int lisattavaLuku) {

        if (alkioidenLkm == 0) {
            joukonLuvut[0] = lisattavaLuku;
            alkioidenLkm++;
            return true;
        } else {
        }
        if (!kuuluu(lisattavaLuku)) {
            joukonLuvut[alkioidenLkm] = lisattavaLuku;
            alkioidenLkm++;
            if (alkioidenLkm % joukonLuvut.length == 0) {

                int[] uusiTaulukko = new int[this.joukonLuvut.length + this.kasvatuskoko];
                kopioiTaulukko(this.joukonLuvut, uusiTaulukko);
                this.joukonLuvut = uusiTaulukko;
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int haettavaLuku) {
        for (int i = 0; i < this.alkioidenLkm; i++) {
            if (haettavaLuku == joukonLuvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int poistettavaLuku) {

        for (int i = 0; i < alkioidenLkm; i++) {
            if (poistettavaLuku == joukonLuvut[i]) {
                joukonLuvut[i] = 0;
                siirraJoukonLukujaKohdastaEteenpain(i);
                return true;
            }
        }

        return false;

    }

    private void siirraJoukonLukujaKohdastaEteenpain(int lahtoIndeksi) {
        
        for (int i = lahtoIndeksi; i < this.alkioidenLkm - 1; i++) {
            this.joukonLuvut[i] = this.joukonLuvut[i + 1];
        }

        this.alkioidenLkm--;

    }

    private void kopioiTaulukko(int[] vanhaTaulukko, int[] uusiTaulukko) {
        for (int i = 0; i < vanhaTaulukko.length; i++) {
            uusiTaulukko[i] = vanhaTaulukko[i];
        }

    }

    public int mahtavuus() {
        return this.alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            
            String palautettavaString = "{" + this.joukonLuvut[0];
            for (int i = 1; i < this.alkioidenLkm; i++) {
                palautettavaString += ", " + this.joukonLuvut[i];
            }

            return palautettavaString + "}";
        }
    }

    public int[] toIntArray() {

        int[] uusiTaulu = new int[this.alkioidenLkm];
        for (int i = 0; i < uusiTaulu.length; i++) {
            uusiTaulu[i] = this.joukonLuvut[i];
        }
        return uusiTaulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko ensimmainenTaulukko, IntJoukko toinenTaulukko) {
        IntJoukko yhdisteTaulukko = new IntJoukko();
        for (int i = 0; i < ensimmainenTaulukko.alkioidenLkm; i++) {
            yhdisteTaulukko.lisaa(ensimmainenTaulukko.joukonLuvut[i]);
        }
        for (int i = 0; i < toinenTaulukko.alkioidenLkm; i++) {
            yhdisteTaulukko.lisaa(toinenTaulukko.joukonLuvut[i]);
        }
        return yhdisteTaulukko;
    }

    public static IntJoukko leikkaus(IntJoukko ensimmainenTaulukko, IntJoukko toinenTaulukko) {
        IntJoukko leikkausTaulukko = new IntJoukko();

        for (int i = 0; i < ensimmainenTaulukko.alkioidenLkm; i++) {
            for (int j = 0; j < toinenTaulukko.alkioidenLkm; j++) {
                if (ensimmainenTaulukko.joukonLuvut[i] == toinenTaulukko.joukonLuvut[j]) {
                    leikkausTaulukko.lisaa(toinenTaulukko.joukonLuvut[j]);
                }
            }
        }
        return leikkausTaulukko;

    }
    
    public static IntJoukko erotus ( IntJoukko ensimmainenTaulukko, IntJoukko toinenTaulukko) {
        IntJoukko erotusTaulukko = new IntJoukko();

        for (int i = 0; i < ensimmainenTaulukko.alkioidenLkm; i++) {
            erotusTaulukko.lisaa(ensimmainenTaulukko.joukonLuvut[i]);
        }
        for (int i = 0; i < toinenTaulukko.alkioidenLkm; i++) {
            erotusTaulukko.poista(toinenTaulukko.joukonLuvut[i]);
        }
 
        return erotusTaulukko;
    }
        
}
