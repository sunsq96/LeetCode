class NumArray {
  private int[] tree;
  private int[] nums;

  public NumArray(int[] nums) {
    int n = nums.length;
    this.nums = nums;
    this.tree = new int[n + 1];  // 1-indexed
    for (int i = 0; i < nums.length; i++) {
      add(i + 1, nums[i]);
    }
  }

  public void update(int index, int val) {
    add(index + 1, val - nums[index]);
    nums[index] = val;
  }

  public int sumRange(int left, int right) {
    return prefixSum(right + 1) - prefixSum(left);
  }
  
  private int lowbit(int x) {
    return x & -x;
  }
  
  private void add(int index, int val) {
    while (index < tree.length) {
      tree[index] += val;
      index += lowbit(index);
    }
  }
  
  private int prefixSum(int index) {
    int sum = 0;
    while (index > 0) {
      sum += tree[index];
      index -= lowbit(index);
    }
    return sum;
  }
}