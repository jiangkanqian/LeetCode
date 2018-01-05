package leetcode1_10;

import java.util.HashMap;
import java.util.Map;

public class leetcode1 {
	
	 public static int[] twoSum(int[] nums, int target) {
	      int[] res = new int[2];
	      int len = nums.length;
	      for(int i=0;i<len-1;i++){
	    	  for(int j=i+1;j<len;j++){
	    		  if(nums[i] + nums[j] == target){
	    			  res[0] = i;
	    			  res[1] = j;
	    			  break;
	    		  }
	    	  }
	      }
	      return res;
	 }
	 
	 //利用hashmap,空间换时间，时间复杂度将为O(n),空间复杂度增为O(n)
	 public int[] twoSum1(int[] nums, int target) {
		    Map<Integer, Integer> map = new HashMap<>();
		    for (int i = 0; i < nums.length; i++) {
		        map.put(nums[i], i);
		    }
		    for (int i = 0; i < nums.length; i++) {
		        int complement = target - nums[i];
		        if (map.containsKey(complement) && map.get(complement) != i) {
		            return new int[] { i, map.get(complement) };
		        }
		    }
		    throw new IllegalArgumentException("No two sum solution");
		}
	 
	 //与上一种方法相比，只需要一次循环即可
	 public int[] twoSum2(int[] nums, int target) {
		    Map<Integer, Integer> map = new HashMap<>();
		    for (int i = 0; i < nums.length; i++) {
		        int complement = target - nums[i];
		        if (map.containsKey(complement)) {
		            return new int[] { map.get(complement), i };
		        }
		        map.put(nums[i], i);
		    }
		    throw new IllegalArgumentException("No two sum solution");
		}
	 
	 public static void main(String[] args){
		 int[] nums = {3,2,4};
		 int target = 6;
		 int[] res = twoSum(nums,target);
		 for(int i=0;i<res.length;i++){
			 System.out.println(res[i]);
		 }
	 }
	 
}
