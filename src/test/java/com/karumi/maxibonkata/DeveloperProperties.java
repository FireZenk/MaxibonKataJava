package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.runner.RunWith;

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

}
