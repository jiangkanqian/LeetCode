package leetcode1_10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode3 {
	
	public static int lengthOfLongestSubstring(String s) {
		
		int len = s.length();
		if(s == null || "".equals(s)){
			return 0;
		}
		if(len == 1){
			return 1;
		}
		int max=1;
		int subMax=1;
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put(Character.toString(s.charAt(0)),0);
		for(int i=1;i<len;i++){			
			String curStr = Character.toString(s.charAt(i));
			if(map.containsKey(curStr)){
				int pos = map.get(curStr);
				subMax = 0;
				map.clear();
				for(int j=pos;j<=i;j++){
					subMax++;
					map.put(Character.toString(s.charAt(j)), j);
				}
			}
			else{
				subMax++;
				map.put(curStr,i);
			}
			if(subMax>max){
				max=subMax;
			}
		}
		return max;
		
    }
	
	public static void main(String[] args){
		String test = "abcabcbb";
		int max = lengthOfLongestSubstring(test);
		System.out.println(max);
	}
	
}
