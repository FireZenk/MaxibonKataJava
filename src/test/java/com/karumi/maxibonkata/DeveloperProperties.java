package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jorge Garrido Oval, aka firezenk on 25/03/17.
 * Project: com.karumi.maxibonskata
 */

@RunWith(JUnitQuickcheck.class)
public class DeveloperProperties {

    private static final String ANY_NAME = "Jorge";
    private static final int ANY_NUMBER_OF_MAXIBONS = 1;

    @Property public void theNumberOfMaxibonsAssignedIsPositiveOrZero(
            @From(DevelopersGenerator.class) Developer developer) {
        System.out.println(developer);
        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }

    @Property public void theNumberOfMaxibonsAfterOpenTheFridgeIsAlwaysTwoOrHigher(
            @From(KarumiesGenerator.class) Developer developer) {
        KarumiHQs karumiHQs = new KarumiHQs();

        Stream.of(developer, developer, developer)
                .forEach(karumiHQs::openFridge);

        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Test public void theNumberOfMaxibonsPerKarumiDeveloperIsOk() {
        Stream.of(Karumies.PEDRO, Karumies.ALBERTO, Karumies.DAVIDE,
                Karumies.SERGIO, Karumies.JORGE).forEach(System.out::println);

        assertEquals(3, Karumies.PEDRO.getNumberOfMaxibonsToGrab());
        assertEquals(1, Karumies.ALBERTO.getNumberOfMaxibonsToGrab());
        assertEquals(0, Karumies.DAVIDE.getNumberOfMaxibonsToGrab());
        assertEquals(2, Karumies.SERGIO.getNumberOfMaxibonsToGrab());
        assertEquals(1, Karumies.JORGE.getNumberOfMaxibonsToGrab());
    }
}
