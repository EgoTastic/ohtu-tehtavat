package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    protected String toisenSiirto(String ekanSiirto) {
        return scanner.nextLine();
    }

}
