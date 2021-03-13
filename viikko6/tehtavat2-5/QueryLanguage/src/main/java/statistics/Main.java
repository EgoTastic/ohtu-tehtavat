package statistics;


import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        Matcher m = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }


        System.out.println("2.1");

        Matcher m21 = new And(
            new Not( new HasAtLeast(1, "goals")),
            new PlaysIn("NYR")
        );

        for (Player player : stats.matches(m21)) {
            System.out.println(player);
        }

        System.out.println("2.2");

        Matcher m22 = new And(
            new HasFewerThan(1, "goals"),
            new PlaysIn("NYR")
        );

        for (Player player : stats.matches(m22)) {
            System.out.println(player);
        }
        
        
        

        System.out.println("3.1");
        Matcher m31 = new Or(
            new HasAtLeast(40, "goals"),
            new HasAtLeast(60, "assists")
        
        );

        for (Player player : stats.matches(m31)) {
            System.out.println(player);
        }

        System.out.println("3.2");
        Matcher m32 = new And(
            new HasAtLeast(50, "points"),
            new Or(
                new PlaysIn("NYR"),
                new PlaysIn("NYI"),
                new PlaysIn("BOS")
            )
        );

        for (Player player : stats.matches(m32)) {
            System.out.println(player);
        }

        System.out.println("4.1");

        QueryBuilder query41 = new QueryBuilder();
        Matcher m41 = query41.build();

        for (Player player : stats.matches(m41)) {
            System.out.println(player);
        }

        System.out.println("4.2");

        QueryBuilder query42 = new QueryBuilder();
        Matcher m42 = query42.playsIn("NYR").build();

        for (Player player : stats.matches(m42)) {
            System.out.println(player);
        }

        System.out.println("4.3");

        QueryBuilder query43 = new QueryBuilder();
        Matcher m43 = query43.playsIn("NYR").hasAtLeast(5, "goals").hasFewerThan(10, "goals").build();

        for (Player player : stats.matches(m43)) {
            System.out.println(player);
        }

        System.out.println("5.1");

        QueryBuilder query51 = new QueryBuilder();
        Matcher m511 = query51.playsIn("PHI").hasAtLeast(10, "assists").hasFewerThan(5, "goals").build();
        Matcher m512 = query51.playsIn("EDM").hasAtLeast(40, "points").build();

        Matcher m51 = query51.oneOf(m511, m512).build();

        for (Player player : stats.matches(m51)) {
            System.out.println(player);
        }

        System.out.println("5.2");

        QueryBuilder query52 = new QueryBuilder();
        Matcher m52 = query52.oneOf( query52.playsIn("PHI").hasAtLeast(10, "assists").hasFewerThan(5, "goals").build(), 
                                        query52.playsIn("EDM").hasAtLeast(40, "points").build()).build();

        for (Player player : stats.matches(m52)) {
            System.out.println(player);
        }

    }


}
