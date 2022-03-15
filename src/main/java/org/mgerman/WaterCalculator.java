package org.mgerman;

public class WaterCalculator {

  private static final int MAX_NUMBER_OF_POSITIONS = 32000;
  private static final int MIN_HEIGHT = 0;
  private static final int MAX_HEIGHT = 32000;

  public long calculateWaterAmount(int[] landscape) {
    validate(landscape);

    if (landscape.length == 0) {
      return 0;
    }

    return calculateAmount(landscape);
  }

  /**
   * Вычисляем количество воды.
   * @param landscape - массив ландшафта
   * @return количество воды
   */
  private int calculateAmount(int[] landscape) {
    int currentLeftMaxBorder = landscape[0];
    int currentRightMaxBorder = landscape[landscape.length - 1];
    int leftIndex = 1;
    int rightIndex = landscape.length - 2;
    int amount = 0;
    while (leftIndex <= rightIndex) {
      if (landscape[leftIndex] < landscape[rightIndex]) {
        if (landscape[leftIndex] < currentLeftMaxBorder) {
          amount += currentLeftMaxBorder - landscape[leftIndex];
        } else {
          currentLeftMaxBorder = landscape[leftIndex];
        }
        leftIndex++;
      } else {
        if (landscape[rightIndex] < currentRightMaxBorder) {
          amount += currentRightMaxBorder - landscape[rightIndex];
        } else {
          currentRightMaxBorder = landscape[rightIndex];
        }
        rightIndex--;
      }
    }

    return amount;
  }

  /**
   * Валидация входного массива на соответствие граничным условиям
   * @param landscape - массив ландшафта
   */
  private void validate(int[] landscape) {
    if (landscape.length > MAX_NUMBER_OF_POSITIONS) {
      throw new IllegalArgumentException("Too many numbers of positions in landscape");
    }
    for (int height : landscape) {
      if (height > MAX_HEIGHT) {
        throw new IllegalArgumentException(
            String.format("Too high height in landscape: %d", height));
      }
      if (height < MIN_HEIGHT) {
        throw new IllegalArgumentException(
            String.format("Invalid height in landscape: %d", height));
      }
    }
  }
}
