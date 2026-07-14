class Solution {
    private static final int MOD = 1000000007;

    public int subsequencePairCount(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        Integer[][][] dp = new Integer[nums.length][max + 1][max + 1];
        return solve(nums, 0, 0, 0, dp);
    }

    private int solve(int[] nums, int index, int g1, int g2, Integer[][][] dp) {
        if (index == nums.length) {
            if (g1 > 0 && g1 == g2) {
                return 1;
            }
            return 0;
        }

        if (dp[index][g1][g2] != null) {
            return dp[index][g1][g2];
        }

        int skip = solve(nums, index + 1, g1, g2, dp);
        int first = solve(nums, index + 1, gcd(g1, nums[index]), g2, dp);
        int second = solve(nums, index + 1, g1, gcd(g2, nums[index]), dp);

        dp[index][g1][g2] = (int) (((long) skip + first + second) % MOD);
        return dp[index][g1][g2];
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}