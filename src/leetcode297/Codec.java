package leetcode297;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Codec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		List<Integer> list = serialize(root);
		System.out.println(list);
		TreeNode test = deserialize(list);
		midTraverse(test);
		List<Integer> list1 = serialize(test);
		System.out.println();
		System.out.println(list1);
	}
	
	public static List<Integer> serialize(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        list.add(root.val);
        while(!queue.isEmpty()){
        	TreeNode node = queue.poll();
        	if(node.left!=null){
        		queue.add(node.left);
        		list.add(node.left.val);
        	}
        	else{
        		list.add(null);
        	}
        	
        	if(node.right!=null){
        		queue.add(node.right);
        		list.add(node.right.val);
        	}
        	else{
        		list.add(null);
        	}
        	       	
        }
        return list;
    }
	
    public static TreeNode deserialize(List<Integer> data) {
        int len = data.size();
        TreeNode root = null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(len > 0){
        	if(data.get(0) != null){
        		root = new TreeNode(data.get(0));
        		queue.add(root);
        		int index = 1;
        		while(!queue.isEmpty() && index<len){
        			TreeNode node = queue.poll();
        			if(data.get(index) != null){
        				TreeNode left = new TreeNode(data.get(index));
        				node.left = left;
        				queue.add(left);
        			}
        			index++;
        			
        			if(data.get(index) != null){
        				TreeNode right = new TreeNode(data.get(index));
        				node.right = right;
        				queue.add(right);
        			}
        			index++;
        			
        		}
        	}
        }
        return root;
    }
    
    public static void midTraverse(TreeNode root){
    	if(root != null){
    		System.out.print(root.val + " ");
    		midTraverse(root.left);
    		midTraverse(root.right);
    	}
    	else{
    		System.out.print("null ");
    	}
    }
    
	
}
