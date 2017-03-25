package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Jorge Garrido Oval, aka firezenk on 25/03/17.
 * Project: com.karumi.maxibonskata
 */

@RunWith(JUnitQuickcheck.class)
public class KarumiHqProperties {

    private KarumiHQs karumiHQs;
    private Chat chat;

    @Before public void setUp() {
        chat = mock(Chat.class);
        karumiHQs = new KarumiHQs(chat);
    }

    @Property(trials = 1000) public void theNumberOfMaxibonsAfterOpenTheFridgeIsAlwaysTwoOrHigher(
            @From(KarumiesGenerator.class) Developer developer) {
        Stream.of(developer, developer, developer)
                .forEach(developer1 -> {
                    System.out.println(developer.getName() + " opens the fridge");
                    karumiHQs.openFridge(developer);
                    System.out.println("Maxibons left: " + karumiHQs.getMaxibonsLeft() + "\n");
                });

        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }
}
