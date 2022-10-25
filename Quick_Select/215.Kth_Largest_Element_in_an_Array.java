class Solution {
  private final Random random = new Random();

  public int findKthLargest(int[] nums, int k) {
    int targetIdx = nums.length - k;
    int lo = 0;
    int hi = nums.length - 1;
    while (true) {
      int pivotIdx = partition(nums, lo, hi);
      if (pivotIdx == targetIdx) {
        return nums[targetIdx];
      } else if (pivotIdx > targetIdx) {
        hi = pivotIdx - 1;
      } else {
        lo = pivotIdx + 1;
      }
    }
  }

  private int partition(int[] nums, int lo, int hi) {
    if (lo == hi) {
      return lo;
    }
    int randIdx = lo + random.nextInt(hi - lo + 1);
    swap(nums, lo, randIdx);
    
    int pivot = nums[lo];
    int i = lo;
    int j = hi + 1;
    while (true) {
      while (nums[++i] < pivot) {
        if (i == hi) {
          break;
        }
      }
      while (nums[--j] > pivot) {
        if (j == lo) {
          break;
        }
      }
      if (i >= j) {
        break;
      }
      swap(nums, i, j);
    }
    swap(nums, j, lo);
    return j;
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}