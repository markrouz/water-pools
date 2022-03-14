package org.mgerman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WaterCalculatorTest {

  WaterCalculator waterCalculator = new WaterCalculator();

  @Test
  @DisplayName("Should throw IllegalArgumentException if input landscape length > 32000")
  public void soLongLandscapeTest() {
    IllegalArgumentException ex = assertThrows(
        IllegalArgumentException.class,
        () -> waterCalculator.calculateWaterAmount(new int[32001]));

    assertEquals("Too many numbers of positions in landscape", ex.getMessage());
  }

  @Test
  @DisplayName("Should throw IllegalArgumentException if input landscape contains too high height")
  public void tooHighHeightInLandscapeTest() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
        () -> waterCalculator.calculateWaterAmount(new int[]{1, 2, 3, 1000000}));

    assertEquals("Too high height in landscape: 1000000", ex.getMessage());
  }

  @Test
  @DisplayName("Should throw IllegalArgumentException if input landscape contains high < 0")
  public void invalidHeightInLandscapeTest() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
        () -> waterCalculator.calculateWaterAmount(new int[]{1, 0, -1, 1}));

    assertEquals("Invalid height in landscape: -1", ex.getMessage());
  }

  /**
   *🟫🟦🟦🟦🟫
   *🟫🟦🟦🟫🟫🟫
   *🟫🟦🟫🟫🟫🟫🟦🟫
   *🟫🟫🟫🟫🟫🟫🟦🟫
   *🟫🟫🟫🟫🟫🟫🟦🟫🟫
   */
  @Test
  public void inputFromTaskTest() {
    int[] landscape = new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1};
    assertEquals(9, waterCalculator.calculateWaterAmount(landscape));
  }

  /**
   *       🟫
   *     🟫🟫🟫
   *   🟫🟫🟫🟫🟫
   * 🟫🟫🟫🟫🟫🟫🟫
   */
  @Test
  public void pyramidTest() {
    int[] landscape = new int[]{1, 2, 3, 4, 3, 2, 1};
    assertEquals(0, waterCalculator.calculateWaterAmount(landscape));
  }

  /**
   *🟫🟦🟦🟦🟦🟦🟦🟦🟫
   *🟫🟫🟦🟦🟦🟦🟦🟫🟫
   *🟫🟫🟫🟦🟦🟦🟫🟫🟫
   *🟫🟫🟫🟫🟦🟫🟫🟫🟫
   *🟫🟫🟫🟫🟫🟫🟫🟫🟫
   */

  @Test
  public void invertPyramidTest() {
    int[] landscape = new int[] {5,4,3,2,1,2,3,4,5};
    assertEquals(16, waterCalculator.calculateWaterAmount(landscape));
  }

  @Test
  public void oneLengthLandscapeTest() {
    int[] landscape = new int[]{5};
    assertEquals(0, waterCalculator.calculateWaterAmount(landscape));
  }

  @Test
  public void zeroLengthLandscapeTest() {
    int[] landscape = new int[] {};
    assertEquals(0, waterCalculator.calculateWaterAmount(landscape));
  }


  /**
   *🟫🟫🟫🟫🟫🟫🟦🟦🟫
   *🟫🟫🟦🟦🟫🟫🟦🟫🟫
   *🟫🟫🟫🟦🟦🟦🟦🟫🟫
   *🟫🟫🟫🟫🟦🟫🟫🟫🟫
   *🟫🟫🟫🟫🟫🟫🟫🟫🟫
   */
}
