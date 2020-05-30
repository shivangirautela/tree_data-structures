import java.util.ArrayList;
import java.util.LinkedList;
// import java.util.StringBuilder;
public class generictree{
    public static void main(String[] args){
    int[] arr={10,20,30,-1,40,-1,-1,50,60,70,-1,80,-1,-1,90,100,-1,110,-1,-1,120,-1,-1,30,-1,-1};
    Node root=Constructor(arr);
  //  display(root);
  //  if(find(root,30))
  //  System.out.println("found");
 //   else
  //  System.out.println(" not found");
  //  flattern_01(root);    display(root);
 //if(isfoldable(root,root))     
 //System.out.println("foldable");
 //else
 //System.out.println(" not foldable");
 maximum(root);  display(root);
    }
    public static class Node{
        int data=0;
        ArrayList<Node> childs=new ArrayList<>(); 
        Node(int data){
            this.data=data;
        }
    }
    public static Node Constructor(int[] arr){
      LinkedList<Node> st=new LinkedList<>();
      for(int i=0;i<arr.length-1;i++){
          if(arr[i]!=-1){
            Node node=new Node(arr[i]);
            st.addFirst(node);
        }else{
            Node rchild=st.removeFirst();
            st.getFirst().childs.add(rchild);
          }
        }
        return st.removeFirst();
    }
    public static void display(Node node){
        StringBuilder sb=new StringBuilder();
        sb.append(node.data + " -> [");
        for(Node child:node.childs){
            sb.append(child.data + ",");
        }
        sb.append("]");
        System.out.println(sb.toString());
        for(Node child:node.childs){
            display(child);
        }
    }
    public static boolean find(Node node,int data)
    {
        if(node.data==data)
        return true;
        boolean res=false;
        for(Node child: node.childs)
        {
            res=res|| find(child,data);
        }
        return res;
    }
    public static int maximum(Node node)
    {
        int max=node.data;
        for(Node child: node.childs)
        {
            max=Math.max(maximum(child),max);
        }
       return max; 
    }
    public static boolean isfoldable(Node node1,Node node2)
    {
        if((node1==null && node2!=null) || (node1!=null && node2==null))
        return false;
        if(node1.childs.size() != node2.childs.size())
        return false;

        if(node1.data!=node2.data) return false;
        for(int i=0,j=node1.childs.size()-1;i<node1.childs.size();i++)
        {
            Node child1=node1.childs.get(i);
            Node child2=node2.childs.get(j);

            boolean res=isfoldable(child1,child2);
            if(!res) return res;
        }
        return true;
    }
    public static void flattern_01(Node node)
    {
        ArrayList<Node> ch=new ArrayList<>();

        for(Node n:node.childs)
        {
            flattern_01(n);
            ch.add(n);
            for(int i=n.childs.size()-1;i>=0;i--)
            {
                ch.add(n.childs.remove(n.childs.size()-1));
            }
            //n.child=new Arraylist<>();
        }
        node.childs=ch;
    }
    public static Node linearize(Node node)
    {
        if(node.size()==0)
        return node;

        Node lasttail=linearize()
    }

}