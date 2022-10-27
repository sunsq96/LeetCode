class NumArray {
  private int[] segTree;
  private int n;

  public NumArray(int[] nums) {
    n = nums.length;
    segTree = new int[n * 4];
    build(0, 0, n - 1, nums);
  }

  public void update(int index, int val) {
    change(index, val, 0, 0, n - 1);
  }

  public int sumRange(int left, int right) {
    return range(left, right, 0, 0, n - 1);
  }
  
  private void build(int node, int s, int e, int[] nums) {
    if (s == e) {
      segTree[node] = nums[s];
      return;
    }
    int m = s + (e - s) / 2;
    build(node * 2 + 1, s, m, nums);
    build(node * 2 + 2, m + 1, e, nums);
    segTree[node] = segTree[node * 2 + 1] + segTree[node * 2 + 2];
  }
  
  private void change(int index, int val, int node, int s, int e) {
    if (s == e) {
      segTree[node] = val;
      return;
    }
    int m = s + (e - s) / 2;
    if (index <= m) {
      change(index, val, node * 2 + 1, s, m);
    } else {
      change(index, val, node * 2 + 2, m + 1, e);
    }
    segTree[node] = segTree[node * 2 + 1] + segTree[node * 2 + 2];
  }
  
  private int range(int left, int right, int node, int s, int e) {
    if (left == s && right == e) {
      return segTree[node];
    }
    int m = s + (e - s) / 2;
    if (right <= m) {
      return range(left, right, node * 2 + 1, s, m);
    } else if (left > m) {
      return range(left, right, node * 2 + 2, m + 1, e);
    } else {
      return range(left, m, node * 2 + 1, s, m) + range(m + 1, right, node * 2 + 2, m + 1, e);
    }
  }
}