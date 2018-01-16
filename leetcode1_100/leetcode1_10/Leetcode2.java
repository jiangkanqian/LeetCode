package leetcode1_10;

import java.util.Stack;

public class Leetcode2 {
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        		
		int add = 0;
		int first[] = addBit(l1.val,l2.val,0);
		ListNode addTwo = new ListNode(first[0]);
		add = first[1];
		ListNode current = addTwo;
		int cur[];
		while(l1.next!=null || l2.next!=null){
			if(l1.next==null){
				cur = addBit(0,l2.next.val,add);
				ListNode next = new ListNode(cur[0]);
				current.next = next;
				current = next;
				add = cur[1];
				l2=l2.next;
			}
			else if(l2.next==null){
				cur = addBit(l1.next.val,0,add);
				ListNode next = new ListNode(cur[0]);
				current.next = next;
				current = next;
				add = cur[1];
				l1=l1.next;
			}
			else{
				cur = addBit(l1.next.val,l2.next.val,add);
				ListNode next = new ListNode(cur[0]);
				current.next = next;
				current = next;
				add = cur[1];
				l1=l1.next;
				l2=l2.next;
			}
		}
		if(add>0){
			ListNode next = new ListNode(add);
			current.next = next;
			current = next;
		}
		return addTwo;
    }
	
	public static int[] addBit(int a,int b,int c){
		int[] res = new int[2];
		int add = a + b + c;
		if(add < 10){
			res[1] = 0;
			res[0] = add;
		}
		else{
			res[1] = 1;
			res[0] = add-10;
		}
		return res;
	}
	
	public static void print(ListNode a){
		System.out.print(a.val);
		while(a.next!=null){
			a = a.next;
			System.out.print("->");
			System.out.print(a.val);
		}
	}
	
	//思路清晰，更小的空间复杂度
	public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
	    ListNode dummyHead = new ListNode(0);
	    ListNode p = l1, q = l2, curr = dummyHead;
	    int carry = 0;
	    while (p != null || q != null) {
	        int x = (p != null) ? p.val : 0;
	        int y = (q != null) ? q.val : 0;
	        int sum = carry + x + y;
	        carry = sum / 10;
	        curr.next = new ListNode(sum % 10);
	        curr = curr.next;
	        if (p != null) p = p.next;
	        if (q != null) q = q.next;
	    }
	    if (carry > 0) {
	        curr.next = new ListNode(carry);
	    }
	    return dummyHead.next;
	}
	
	public static void main(String[] args){
		
		ListNode l1 = new ListNode(1);
		ListNode l11 = new ListNode(8);
		l1.next = l11;
		ListNode l2 = new ListNode(0);
	
		ListNode res = addTwoNumbers1(l1,l2);
		print(res);
	}
	
}
