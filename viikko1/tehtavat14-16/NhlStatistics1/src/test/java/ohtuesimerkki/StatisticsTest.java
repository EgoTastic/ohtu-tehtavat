package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }

    };


    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }

    @Test
    public void etsiOlematonta() {
        assertEquals(null, stats.search("Matti"));
    }

    @Test
    public void etsiOlemassaOlevaa() {
        assertEquals("Kurri                EDM 37 + 53 = 90", stats.search("urr").toString());
    }

    @Test
    public void etsiTiiminPelaajat() {
        assertEquals(3, stats.team("EDM").size());
    }

    @Test
    public void etsiParasPelaaja() {
        assertEquals("Gretzky              EDM 35 + 89 = 124", stats.topScorers(0).get(0).toString());
    }

    @Test
    public void etsiOikeaMaara() {
        assertEquals(4, stats.topScorers(3).size());
    }

}