import java.util.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
public class Binarytree
{
// creating a tree==================================================================================    
public static class Node{
    int data=0;
    Node left=null;
    Node right=null;
    Node(int data,Node left,Node right)
    {
        this.data=data;
        this.left=left;
        this.right=right;
    }
}
static Node root=null;
public Binarytree(int[] arr){
    root=construct(arr);

}
 static int idx=0;
 public static Node construct(int []arr)
 {
     if(idx>= arr.length || arr[idx]==-1)
     {
         idx++;
         return null;
     }
     Node node= new Node(arr[idx],null,null);
     idx++;
     node.left=construct(arr);
     node.right=construct(arr);
     return node;
 }


 public static void display(Node node)
 {
   if(node==null)
   return;

StringBuilder sb=new StringBuilder();

sb.append(node.left!=null?node.left.data+"":".");
sb.append("<--"+node.data+"-->");
sb.append(node.right!=null?node.right.data+"":".");
System.out.println(sb.toString());
   display(node.left);
   display(node.right);

 }
 //functions on tree================================================================================
 public static int size(Node node)         // total no. of nodes in a tree
 {
     if (node==null)
     return 0;
     return size(node.left)+size(node.right)+1;

 }
 public static int height(Node node)    // length of max number of nodes in a tree.
 {
     if (node==null)
     return 0;
     return Math.max(height(node.left),height(node.right))+1;
 }
 public static void postoder(Node node)
 {
    if(node==null) return ;
    preoder(node.left);
    preoder(node.right);
    System.out.print(node.data+" ");
 }
 public static void preoder(Node node)
 {
  if(node==null) return ;
  System.out.print(node.data+" ");
  preoder(node.left);
  preoder(node.right);
 }
 public static void inoder(Node node)
 {
    if(node==null) return ;
    preoder(node.left);
    System.out.print(node.data+" ");
    preoder(node.right);
 }
 
 public static boolean find(Node node,int data)
 {
     if(node==null)
     return false;
     if(node.data==data)
     {
         return true;
     }

     return (find(node.left,data)||find(node.right,data));
 }
 public static int max(Node node)
 {
  if(node==null)
  return 0;
  return  Math.max((node.left.data),Math.max((node.data),(node.left.data)));
 }/*
 public static int min(Node node)
 {
    if(node==null)
    return 0;
    int left=node.left;
    int right=node.right;
    return Math.min(left,right);
 }*/
// questionst01=====================================================================================
public static ArrayList <Node> RootToNodePath(Node node,int data)
{
    if(node== null) return null;

    if(node.data==data)
    {
        ArrayList<Node> base = new ArrayList<>();
        base.add(node);
        return base;
    }
    ArrayList<Node> leftans=RootToNodePath(node.left,data);
    if(leftans != null)
    {
        leftans.add(node);
        return leftans;
    }
    ArrayList<Node> rightans=RootToNodePath(node.right,data);
    if(rightans != null)
    {
        rightans.add(node);
        return rightans;
    }
    return null;
}

 public static boolean RootToNodePath_01(Node root,int data,ArrayList<Node> path)  //find a data in tree
 {
     if(root==null)
     return false;
     if(root.data==data)
     {
      path.add(root);
      return true;
     }
     if(RootToNodePath_01(root.left,data,path)){
         path.add(root);
         return true;
     }
     if(RootToNodePath_01(root.right,data,path)){
        path.add(root);
        return true;
    }

  return false;
 }
 public static void leafnodes(Node node)
 {
  if(node==null) return ;
  if(node.left==null && node.right==null)
  {
      System.out.print(node.data+" ");
      return ;
  }
  leafnodes(node.left);
  leafnodes(node.right);
 }
 // zig zag level order===========================================================================
 //simple
 public static void leveloder_01(Node root){
 LinkedList<Node> que=new LinkedList<>();
 que.addLast(root);
 while(!que.isEmpty())
 {
     Node node= que.removeFirst();
     System.out.print(node.data+" ");

    if(node.left!=null) que.addLast(node.left);
    if(node.right!=null) que.addLast(node.right); 
 }
 System.out.println();
 }

 //line wise
 public static void leveloder_02(Node root){
    LinkedList<Node> que=new LinkedList<>();
    que.addLast(root);
    while(!que.isEmpty())
    {
        int size=que.size();
        while(size-- >0)
        {
            Node node= que.removeFirst();
            System.out.print(node.data+" ");

            if(node.left!=null) que.addLast(node.left);
            if(node.right!=null) que.addLast(node.right);
        }
        System.out.println();
    }
}
public static void leveloder_03(Node node){
    LinkedList<Node> que1=new LinkedList<>();
    LinkedList<Node> que2=new LinkedList<>();
    que1.addLast(node);
    while(!que1.isEmpty())
    {
        Node proc= que1.removeFirst();
        System.out.print(proc.data+" ");

        if(proc.left!=null) que2.addLast(proc.left);
        if(proc.right!=null) que2.addLast(proc.right);
        if(que1.size()==0)
        {
            System.out.println();
            LinkedList<Node> temp=que1;
            que1=que2;
            que2=temp;
        }
    }   
}
//LCA===============================================================================================
 public static Node LCA_001(Node node,int data1,int data2)
{
    ArrayList<Node> path1=RootToNodePath(node, data1);
    ArrayList<Node> path2=RootToNodePath(node, data2);
    
    if(path1==null || path2==null)
    {
        return null;
    }
    Node ans=null;
    int i=path1.size()-1;
    int j=path2.size()-1;
    while(i>=0 && j>=0)
    {
        if(path1.get(i).data== path2.get(j).data)
        {
            ans=path1.get(i);
        }
        else{
            break;
        }
        i--;
        j--;
    }
  return ans;
}
static Node LCA=null;
public static  boolean LCA_002(Node node,int data1,int data2)
{
if(node==null)  return false;
boolean selfdone= node.data==data1|| node.data==data2;

boolean left=LCA_002(node.left,data1,data2);
if(LCA !=null) return true;                     //for no need of extra calls to right if we get lca inleft regin
boolean right=LCA_002(node.right,data1,data2);

if((left&&right) || (right && selfdone) || (left && selfdone))
LCA=node;

return (left || right ||selfdone);
}
//BST=============================================================================================

public class bstpair{
    int min=INT_MAXVALUE;
    int max=INT_MINVALUE;
    boolean isBST=true;
    int nobst;
    bstHelper(int min,int max,boolean isbst,int nobst)
    {
        this.min=min;
        this.max=max;
        this.isBST=isbst;
        this.nobst=nobst;
    }

}
static int prev=Integer.MIN_VALUE;
public static boolean isBST(Node curr)
{
if(curr==null)  return true;

boolean left=isBST(curr.left);
if(!left)  return false;

if(prev<curr.data)
prev=curr.data;
else
return false;

boolean right=isBST(curr.right);
if(!right) return false;

return true;
}

public static int diameter_01(Node node)
{
 if(node==null)  return 0;

 int lh=height(node.left);
 int rh=height(node.right);

 int ld=diameter_01(node.left);
 int rd=diameter_01(node.right);

 return Math.max(Math.max(ld,rd),lh+rh+1);
}
public static class diapair{
    int height=0;
    int dia=0;
}
public static diapair diameter_02(Node node)
{
    if(node==null)
    return new diapair();

    diapair left=diameter_02(node.left);
    diapair right=diameter_02(node.right);

    diapair mypair=new diapair();
    mypair.height=Math.max(left.height,right.height)+1;
}
public static void deleteLeaf(Node node,int leaf)
{
    if(node==null) return ;
     
}
//main==============================================================================================
public static void main(String [] args)
{
 // int [] arr={10,20,40,80,-1,-1,90,-1,-1,50,100,-1,-1,-1,30,60,-1,110,-1,-1,70,120,-1,-1,-1};
 int [] arr={10,30,10,-1,20,40,-1,-1,80,50,60,-1,-1,-1,90}; //post order
  Node node=construct(arr);
//   display(node);
 // System.out.println();
 // size(node);
 // System.out.println();
 // height(node);
 // System.out.println();
 // max(node);
 // System.out.println();
 // postoder(node);
 // System.out.println();
 // inoder(node);
 // System.out.println();
 // preoder(node);
 // System.out.println();
 // leafnodes(node);
  //System.out.println();
 // find(node,30);
 // System.out.println();
//   System.out.print(RootToNodePath(node,80));
  ArrayList <Node> ans=new ArrayList<>();
  ArrayList <Node> an=RootToNodePath(node,50);
  for(Node n :an)
  {
  //    System.out.print(n.data+" ");
  }
 // Collections.reverse(ans);
  for(Node n :ans)
  {
  //    System.out.print(n.data+" ");
  }
 // System.out.println();
  // leveloder_01(node);
 // System.out.println();
 //  leveloder_02(node);
 // System.out.println();
 // leveloder_03(node);
 //System.out.print(LCA_001(node,80,110).data);
 if(LCA_002(node,80,110))
 System.out.print(LCA.data);
}
}