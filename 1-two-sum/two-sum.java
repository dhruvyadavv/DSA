class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int[] result = new int[2];
        
        for (int j = 1; j < nums.length; j++) { 
            if (nums[i] + nums[j] == target) { 
                result[0] = i;
                result[1] = j;
                return result;             }
            if (j == nums.length - 1) { 
                i++;
                j = i; 
            }
        }
        return result; 
    }
}