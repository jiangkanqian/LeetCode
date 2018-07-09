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
				for(int j=pos+1;j<=i;j++){
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
	
	/*
	 * 滑动窗口法：
	 * 如果子字符串Sij从下标i到j-1已经检测了是否存在重复字符，那么只要检测s[j]是否存在于子字符串Sij即可
	 * 为了检测字符是否在子字符串中，可以扫描整个字符串，这样导致了O(n²)的时间复杂度
	 * 如果把HashSet作为滑动窗口，那么检测当前字符的时间复杂度能减少到O(1)
	 * 滑动窗口作为一个抽象概念经常被运用到数组/字符串问题中。在这个问题中，使用HashSet来在当前窗口[i,j)中
	 * 存储字符。接着向右滑动下标j,如果它不在HashSet中，那么继续滑动j,直到s[j]已经在HashSet中为止。
	 * 到目前为止，非重复最大子字符串从下标i开始,如果对所有下标i执行此操作，能得到最终答案
	 * 
	 * 复杂度分析：
	 * 时间复杂度：O(2n)=O(n),最坏情况需要在i和j之间访问两次
	 * 空间复杂度：O(min(m,n)),我们需要O(k)的空间作为滑动窗口，k是Set的大小。Set的大小由字符串长度n和字符大小m动态决定
	 * */
	public static int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
	
	/*
	 * 优化后的滑动窗口算法：
	 * 滑动窗口算法最多需要2n步。实际上，可以优化为只需要n步。可以用map来记录字符和它的下标而
	 * 不是用Set来记录字符是否存在。这样当出现重复字符时可以立即跳过。
	 * 
	 * 时间复杂度：O(n)
	 * 空间复杂度：O(min(m,n))
	 * */
	public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
	
	/*
	 * ASCII 128算法：
	 * 以上的算法都没有假想字符串s的取值范围
	 * 如果已知字符取值比较小，则可以直接用table来取代Map
	 * 通常的table包括：
	 * int[26]:字母'a'-'z'或者'A'-'Z'
	 * int[128]:ASCII码
	 * int[256]:扩展的ASCII码
	 * 
	 * 时间复杂度：O(n)
	 * 空间复杂度：O(m)
	 * */
	public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;                      
        }
        return ans;
    }
	
	public static void main(String[] args){
		String test = "abcabcbb";
		int max = lengthOfLongestSubstring3(test);
		System.out.println(max);
	}
	
}
