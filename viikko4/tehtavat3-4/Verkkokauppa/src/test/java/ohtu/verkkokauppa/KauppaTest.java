package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import jdk.jfr.Timestamp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
    public void setUo() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {


        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());

    } 

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikea() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void ostoksenPaatyttyaKahdellaTuotteellaPankinMetodiaTilisiirtoKutsutaanOikein() {
        when(viite.uusi()).thenReturn(36);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "leipä", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kala", 6));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("miika", "112233");

        verify(pankki).tilisiirto(eq("miika"), eq(36), eq("112233"), anyString(), eq(11));
    }

    @Test
    public void ostoksenPaatyttyaKahdellaSamallaTuotteellaPankinMetodiaTilisiirtoKutsutaanOikein() {
        when(viite.uusi()).thenReturn(19);
        when(varasto.saldo(1)).thenReturn(2);

        Tuote makkara = new Tuote(1, "makkara", 4);

        when(varasto.haeTuote(1)).thenReturn(makkara);

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("seppo", "5678");

        verify(pankki).tilisiirto(eq("seppo"), eq(19), eq("5678"), anyString(), eq(8));
    }

    @Test
    public void ostoksenPaatyttyaYhdelläRiittavallaJaYhdellaLoppuneellaTuotteellaPankinMetodiaTilisiirtoKutsutaanOikein() {
        when(viite.uusi()).thenReturn(44);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "limu", 3));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olut", 4));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("hemuli", "110011");

        verify(pankki).tilisiirto(eq("hemuli"), eq(44), eq("110011"), anyString(), eq(3));
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        when(viite.uusi()).thenReturn(64).thenReturn(65);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "limu", 3));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olut", 4));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("miika", "111111");

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("ville", "222222");

        verify(pankki).tilisiirto(eq("ville"), eq(65), eq("222222"), anyString(), eq(4));

        
    }

    @Test
    public void aloitaAsiointiTuottaaUudenViitteen() {
        when(viite.uusi()).thenReturn(64).thenReturn(65).thenReturn(99);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 2));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("miika", "111111");

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("hemuli", "222222");

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("hattivatti", "333333");

        verify(pankki).tilisiirto(eq("hattivatti"), eq(99), eq("333333"), anyString(), eq(6));
    }
}