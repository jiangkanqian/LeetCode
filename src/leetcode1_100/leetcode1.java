package leetcode1_100;

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
	
	 public static void main(String[] args){
		 int[] nums = {3,2,4};
		 int target = 6;
		 int[] res = twoSum(nums,target);
		 for(int i=0;i<res.length;i++){
			 System.out.println(res[i]);
		 }
	 }
	 
}
