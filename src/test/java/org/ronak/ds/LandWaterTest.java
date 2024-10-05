package org.ronak.ds;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandWaterTest {

    @ParameterizedTest
    @MethodSource("provideRegions")
    public void testGetLargestRegion(int[][] region, int expected) {
        LandWater landWater = new LandWater(region);
        assertEquals(expected, landWater.getLargestRegion());
    }

    private static Stream<Arguments> provideRegions() {
        return Stream.of(
                // Test Case 1: Empty Region
                Arguments.of(new int[][]{}, 0),

                // Test Case 2: Single Land Cell
                Arguments.of(new int[][]{{1}}, 1),

                // Test Case 3: Single Water Cell
                Arguments.of(new int[][]{{0}}, 0),

                // Test Case 4: Two Separate Land Cells
                Arguments.of(new int[][]{{1, 0}, {0, 1}}, 1),

                // Test Case 5: Two Connected Land Cells
                Arguments.of(new int[][]{{1, 1}, {0, 0}}, 2),

                // Test Case 6: Larger Island
                Arguments.of(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}, 4),

                // Test Case 7: Multiple Islands
                Arguments.of(new int[][]{{1, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 0, 0}, {1, 1, 1, 1}}, 4)
        );
    }
}