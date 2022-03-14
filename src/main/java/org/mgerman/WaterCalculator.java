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

    //preprocess landscape
    int[] leftBorderHeight = new int[landscape.length];
    int[] rightBorderHeight = new int[landscape.length];
    calculateBorders(landscape, leftBorderHeight, rightBorderHeight);

    return calculateAmount(landscape, leftBorderHeight, rightBorderHeight);
  }

  /**
   * Для каждого элемента входящего массива вычисляем количество воды, суммируя итог
   * @param landscape - массив ландшафта
   * @param leftBorderHeight - массив левых границ
   * @param rightBorderHeight - массив правх границ
   * @return количество воды
   */
  private int calculateAmount(int[] landscape, int[] leftBorderHeight, int[] rightBorderHeight) {
    int amount = 0;
    for (int i = 0; i < landscape.length; i++) {
      amount += Math.min(leftBorderHeight[i], rightBorderHeight[i]) - landscape[i];
    }
    return amount;
  }

  /**
   * Для каждого элемента массива landscape вычисляем максимальную границу слева и справа
   * @param landscape - массив ландшафта
   * @param leftBorderHeight - массив левых границ
   * @param rightBorderHeight - массив правых границ
   */
  private void calculateBorders(int[] landscape, int[] leftBorderHeight, int[] rightBorderHeight) {
    // инициализируем текущую левую и правую границу крайним левым и крайним правым элементом
    int currentLeftHeight = landscape[0];
    int currentRightHeight = landscape[landscape.length - 1];

    //проходим по массиву двумя указателями с начала и с конца и обновляем массивы левых и правых границ
    for (int i = 0; i < landscape.length; i++) {
      if (currentLeftHeight < landscape[i]) {
        currentLeftHeight = landscape[i];
        leftBorderHeight[i] = landscape[i];
      } else {
        leftBorderHeight[i] = currentLeftHeight;
      }
      final int rightIndex = landscape.length - 1 - i;
      if (currentRightHeight < landscape[rightIndex]) {
        currentRightHeight = landscape[rightIndex];
        rightBorderHeight[rightIndex] = landscape[rightIndex];
      } else {
        rightBorderHeight[rightIndex] = currentRightHeight;
      }
    }
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
