package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Property public void theNumberOfMaxibonsAfterAGroupOfDevsOpenTheFridgeIsGreaterThanTwo(
            List<@From(KarumiesGenerator.class) Developer> developers) {

        /*Stream.of(developers.toArray()).forEach(developer ->
                System.out.println(developer.getName() + " opens the fridge")
        );*/

        karumiHQs.openFridge(developers);
        System.out.println("Maxibons left: " + karumiHQs.getMaxibonsLeft() + "\n");

        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property public void whenNonHungryGoToTheFrigeAMessageIsSendToTheChat(
            @From(HungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);

        verify(chat, times(1)).sendMessage(anyString());
    }

    @Property public void whenNonHungryGoToTheFrigeNoMessageIsSendToTheChat(
            @From(NotSoHungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);

        verify(chat, never()).sendMessage(anyString());
    }
}
