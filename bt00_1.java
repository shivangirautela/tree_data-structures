import java.util.*;
public class bt00_1{
    public static class Node{
        int data=0;
        Node left;
        Node right;
        Node()
        {

        }
        Node(int data)
        {
            this.data=data;
        }
        Node(int data,Node left,Node right)
        {
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }
    public static int idx=0;
    public static Node createTree(int[] arr)
    {
        if(idx==arr.length || arr[idx]==-1)
        {
            idx++;
            return null;
        }
        Node node=new Node(arr[idx]);
        idx++;

        node.left=createTree(arr);
        node.right=createTree(arr); 
        return node;
    }
    public static void display(Node node)
    {
        if(node==null)
        return ;
        String ans=" ";
        ans+= (node.left!=null?node.left.data:".") +" -> " ;
        ans+= node.data +" <- ";
        ans+= (node.right!=null?node.right.data:".");
        System.out.println(ans);
        display(node.left);
        display(node.right);
    }
    public static int size(Node node)
    {
        if(node==null)
        return 0;
        int s=0;
        s+=size(node.left);
        s+=size(node.right);
        return s+1;
    }
    public static int height(Node node)
    {
        if(node==null)
        return 0;

        return Math.max(height(node.left),height(node.right))+1;
    }
    public static boolean findData(Node node,int data)
    {
        if(node==null)
         return false;
        if(node.data==data)
         return true;

         boolean res=false;
         res=res|| findData(node.left,data);
         res=res|| findData(node.right,data);
         
         return res;
    }
    public static ArrayList<Node> NodeToRootPath(Node node,int data)
    {
        if(node==null)
        return null;
        
        if(node.data==data)
        {
            ArrayList<Node> base=new ArrayList<Node> ();
            base.add(node);
            return base; 
        }

        ArrayList<Node> left=NodeToRootPath(node.left,data);
        if(left!=null)
        {
            left.add(node);
            return left;
        }
        
        ArrayList<Node> right=NodeToRootPath(node.right,data);
        if(right!=null)
        {
            right.add(node);
            return right;
        }
        return null;
    }
    public static Node LCA_01(Node node, int data1, int data2) {
        ArrayList<Node> list1 = NodeToRootPath(node, data1);
        ArrayList<Node> list2 = NodeToRootPath(node, data2);
        if (list1 == null || list2 == null)
            return null;
        int i = list1.size() - 1;
        int j = list2.size() - 1;
        Node prev = null;
        while (i >= 0 && j >= 0) {
            if (list1.get(i).data != list2.get(j).data)
                break;
            prev = list1.get(i);
            i--;
            j--;
        }
        return prev;
    }

    public static int diameter_01(Node node)
    {
        if(node==null)
        return 0;  
        
        int ld=diameter_01(node.left);
        int rd=diameter_01(node.right);

        int lh=height(node.left);
        int rh=height(node.right);

        return Math.max(Math.max(ld,rd),lh+rh+1);    //+1 for nodes +2 for edges
    }

    public static int[] diameter_02(Node node) {
        if (node == null)
            return new int[] { 0, -1 };
        int[] ld = diameter_02(node.left);
        int[] rd = diameter_02(node.right);
        int[] ans = new int[2];
        ans[0] = Math.max(Math.max(ld[0], rd[0]), ld[1] + rd[1] + 2);
        ans[1] = Math.max(ld[1], rd[1]) + 1;
        return ans;
    }

     static int maxDia = 0;

    public static int diameter_03(Node node) {
        if (node == null)
            return -1;
        int lh = diameter_03(node.left);
        int rh = diameter_03(node.right);
        maxDia = Math.max(maxDia, lh + rh + 2);
        return Math.max(lh, rh) + 1;
    }

    static int maxSum=(int) -1e7; 
    public static int leafToLeafPathMaxSum(Node node)
    {
        if(node==null)
        return (int) -1e7;
        if(node.left==null&&node.right==null)
        return node.data;

        int lsum=leafToLeafPathMaxSum(node.left);
        int rsum=leafToLeafPathMaxSum(node.right);
         
        if(node.left != null && node.right != null)
            maxSum=Math.max(maxSum,lsum+rsum+node.data);
    
        return (node.left==null? rsum: node.right ==null ? lsum :Math.max(lsum,rsum))+node.data;
    }
    public static int NodeToNodePathMaxSum(Node node)
    {
        if(node==null)
        return 0;
        int lsum=NodeToNodePathMaxSum(node.left);
        int rsum=NodeToNodePathMaxSum(node.right);

        int sideMax=Math.max(lsum,rsum)+node.data;
        
        maxSum=Math.max(Math.max(maxSum,sideMax),Math.max(lsum+rsum+node.data,node.data));

        return Math.max(sideMax,node.data);
    }
    public static boolean leafToNodeGivenSum(Node node,int tar) {
            
            boolean res=false;
            
            if(tar-node.data==0 && node.left==null && node.right==null)
            return true;

            if(node.left!=null)
            res=res|| leafToNodeGivenSum(node.left,tar-node.data);

            if(node.right!=null)
            res=res|| leafToNodeGivenSum(node.right,tar-node.data);

            return res;
    
    }
    static int ans = 0;
    public static void pathSumIII(Node node, int tar, int prefixSum, HashMap<Integer, Integer> map) {
        if (node == null)
            return;
        prefixSum += node.data;
        ans += map.getOrDefault(prefixSum - tar, 0);
        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        pathSumIII(node.left, tar, prefixSum, map);
        pathSumIII(node.right, tar, prefixSum, map);
        map.put(prefixSum, map.getOrDefault(prefixSum, 1) - 1);
    }
    // -1 : need camera.
    // 0 : im a camera.
    // 1 : not need camera.
    static int cameraCount= 0;
    public static int camera_(Node node) {
        if (node == null)
            return 1;
        int left = camera_(node.left);
        int right = camera_(node.right);
        if (left == -1 || right == -1) {
            cameraCount++;
            return 0;
        }
        if (left == 0 || right == 0) {
            return 1;
        }
        return -1;
    }
    public static Node ConstructBST(int[] arr, int si, int ei) {
        if (si > ei)
            return null;
        int mid = (si + ei) >> 1;
        Node node = new Node(arr[mid]);
        node.left = ConstructBST(arr, si, mid - 1);
        node.right = ConstructBST(arr, mid + 1, ei);
        return node;
    }
    public static boolean findInBST(Node node, int data) {
        while (node != null) {
            if (node.data == data) {
                return true;
            } else if (data < node.data) {
                node = node.left;
            } else
                node = node.right;
        }
        return false;
    }
    public static class pair {
        int size = 0;
        int height = 0;
        boolean find = false;
        int ceil = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;
        Node pred = null;
        Node succ = null;
        Node prev = null;
    }
    public static void allSolution(Node node, int level, int data, pair p) {
        if (node == null)
            return;
        p.height = Math.max(p.height, level);
        p.size++;
        p.find = p.find || node.data == data;
        if (node.data < data) {
            p.floor = Math.max(p.floor, node.data);
        }
        if (data < node.data)
            p.ceil = Math.min(p.ceil, node.data);
        if (node.data == data && p.pred == null) {
            p.pred = p.prev;
        }
        if (p.prev != null && p.prev.data == data && p.succ == null) {
            p.succ = node;
        }
        p.prev = node;
        allSolution(node.left, level + 1, data, p);
        allSolution(node.right, level + 1, data, p);
    }
    public static void BSTPredSucc(Node node, int data) {
        Node pred = null;
        Node succ = null;
        while (node != null) {
            if (node.data > data) {
                succ = node;
                node = node.left;
            } else if (node.data < data) {
                pred = node;
                node = node.right;
            } else {
                Node temp = node;
                if (node.right != null) {
                    node = node.right;
                    while (node.left != null) {
                        node = node.left;
                    }
                    succ = node;
                }
                node = temp;
                if (node.left != null) {
                    node = node.left;
                    while (node.right != null) {
                        node = node.right;
                    }
                    pred = node;
                }
                break;
            }
        }
    }
    // view Set.==================================
    public static void lineWiseLevelOrder(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print("Level: " + level + " -> ");
            while (size-- > 0) {
                Node rnode = que.removeFirst();
                System.out.print(rnode.data + ", ");
                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }
                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }
            }
            level++;
            System.out.println();
        }
        System.out.println();
    }
    public static void leftView(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        while (que.size() != 0) {
            int size = que.size();
            System.out.print(que.getFirst().data + " ");
            while (size-- > 0) {
                Node rnode = que.removeFirst();
                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }
                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }
            }
        }
        System.out.println();
    }
    public static void rightView(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        while (que.size() != 0) {
            int size = que.size();
            Node prev = null;
            while (size-- > 0) {
                Node rnode = que.removeFirst();
                prev = rnode;
                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }
                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }
            }
            System.out.print(prev.data + " ");
        }
        System.out.println();
    }
    public static void topView(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        while (que.size() != 0) {
            int size = que.size();
            System.out.print(que.getFirst().data + " ");
            Node prev = null;
            int count = 0;
            while (size-- > 0) {
                Node rnode = que.removeFirst();
                prev = rnode;
                count++;
                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }
                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }
            }
            if (count > 1)
                System.out.print(prev.data + " ");
            System.out.println();
        }
        System.out.println();
    }
    public static void verticalOrderPrint(Node node) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();
        queN.addLast(node);
        queI.addLast(0);
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int min = 0;
        int max = 0;
        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer verticalLevel = queI.removeFirst();
                min = Math.min(verticalLevel, min);
                max = Math.max(verticalLevel, max);
                if (!map.containsKey(verticalLevel))
                    map.put(verticalLevel, new ArrayList<>());
                map.get(verticalLevel).add(rnode.data);
                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queI.addLast(verticalLevel - 1);
                }
                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(verticalLevel + 1);
                }
            }
        }
        for (int i = min; i <= max; i++) {
            System.out.println(map.get(i));
        }
        System.out.println();
    }
    public static void verticalOrderPrint_02(Node node) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();
        int[] widthA = new int[2];
        width(node, 0, widthA);
        queN.addLast(node);
        queI.addLast(-widthA[0]);
        ArrayList<Integer>[] ans = new ArrayList[widthA[1] - widthA[0] + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = new ArrayList<>();
        }
        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer verticalLevel = queI.removeFirst();
                ans[verticalLevel].add(rnode.data);
                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queI.addLast(verticalLevel - 1);
                }
                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(verticalLevel + 1);
                }
            }
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
        System.out.println();
    }
    public static void verticalOrderSum_02(Node node) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();
        int[] widthA = new int[2];
        width(node, 0, widthA);
        queN.addLast(node);
        queI.addLast(-widthA[0]);
        int[] ans = new int[widthA[1] - widthA[0] + 1];
        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer verticalLevel = queI.removeFirst();
                ans[verticalLevel] += rnode.data;

                if (rnode.left != null) { queN.addLast(rnode.left);
                    queI.addLast(verticalLevel - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(verticalLevel + 1);
                }
            }
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
        System.out.println();
    }
    public static void BottomView(Node node) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();
        int[] widthA = new int[2];
        width(node, 0, widthA);
        queN.addLast(node);
        queI.addLast(-widthA[0]);
        int[] ans = new int[widthA[1] - widthA[0] + 1];
        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer verticalLevel = queI.removeFirst();
                ans[verticalLevel] = rnode.data;
                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queI.addLast(verticalLevel - 1);
                }
                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(verticalLevel + 1);
                }
            }
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
        System.out.println();
    }
    public static void diagonalOrder(Node node, int level, ArrayList<Integer>[] ans) {
        if (node == null)
            return;
        ans[level].add(node.data);
        diagonalOrder(node.left, level - 1, ans);
        diagonalOrder(node.right, level, ans);
    }
    public static void diagonalSum_01(Node node, int level, int[] ans) {
        if (node == null)
            return;

        ans[level] += node.data;
        diagonalSum_01(node.left, level - 1, ans);
        diagonalSum_01(node.right, level, ans);
    }
    public static void diagonalOrder(Node node) {
        int[] widthAns = new int[2];
        width(node, 0, widthAns);
        ArrayList<Integer>[] ans = new ArrayList[-widthAns[0] + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = new ArrayList();
        }
        diagonalOrder(node, -widthAns[0], ans);
        int[] ansSum = new int[-widthAns[0] + 1];
        diagonalSum_01(node, -widthAns[0], ansSum); }
        public static class LLNode {
            int data = 0;
            LLNode next = null;
            LLNode prev = null;
            LLNode(int data) {
                this.data = data;
            }
        }
        static LLNode head = null;
        static LLNode tail = null;
        public static void verticalSum_03(Node node, LLNode lnode) {
            lnode.data += node.data;
            if (node.left != null) {
                if (lnode.prev == null) {
                    lnode.prev = new LLNode(0);
                    lnode.prev.next = lnode;
                    head = lnode.prev;
                }
                verticalSum_03(node.left, lnode.prev);
            }
            if (node.right != null) {
                if (lnode.next == null) {
                    lnode.next = new LLNode(0);
                    lnode.next.prev = lnode;
                    tail = lnode.next;
    
                }
                verticalSum_03(node.right, lnode.next);
            }
        }
        public static void verticalSum_03(Node node) {
            LLNode lnode = new LLNode(0);
            head = lnode;
            tail = lnode;
            verticalSum_03(node, lnode);
            LLNode curr = head; 
           while (curr != null) {
                System.out.print(curr.data + " ");
                    curr = curr.next;

            }
            System.out.println();    
        }
        public static void width(Node node, int level, int[] ans) {
            if (node == null)
                return;
            ans[0] = Math.min(ans[0], level);
            ans[1] = Math.max(ans[1], level);
            width(node.left, level - 1, ans);
            width(node.right, level + 1, ans);
        }
    public static void viewSet(Node node) {
       // lineWiseLevelOrder(node);
       //  leftView(node);
       //  rightView(node);
        // topView(node); // verticalOrderPrint(node);
        // verticalOrderPrint_02(node);
        verticalOrderSum_02(node);
        // BottomView(node);
        verticalSum_03(node);
    }
    //bstiterator==================================================================================================
    class BSTIterator {
        Stack<Node> st = new Stack<>();

        public BSTIterator(Node root) {
            rightAndLeft(root);
        }
        public void rightAndLeft(Node node) {
            while (node != null) {
                st.push(node);
                node = node.left;
            }
        }
        public int next() {
            Node rn = st.pop();
            rightAndLeft(rn.right);
            return rn.data;
        }
        public boolean hasNext() {
            return st.size() != 0;
        }
    }
    public static Node prev = new Node(-10000);
    public static boolean isBST(Node node) {
        if (node == null)
            return true;
        if (!isBST(node.left))
            return false;
        if (prev.data > node.data)
            return false;
        prev = node;
        if (!isBST(node.right))
            return false;
        return true;
    }
    Node a = null;
    Node b = null;
    public boolean recoverTree_(Node root) {
        if (root == null)
            return false;
        if (recoverTree_(root.left))
            return true;
        if (prev.data > root.data) {
            b = root;
            if (a == null) {
                a = prev;
            } else {
                return true;
            }
        }
        prev = root;
        if (recoverTree_(root.right))
            return true;
        return false;
    }
    public void recoverTree(Node root) {
        recoverTree_(root);
        if (a != null) {
            int temp = a.data;
            a.data = b.data;
            b.data = temp;
        }
    }
    public static class itrPair {
        Node node = null;
        boolean sd = false;
        boolean ld = false;
        boolean rd = false;
        itrPair(Node node) {
            this.node = node;
        }
    }
    public static void itrOrders(Node node) {
        Stack<itrPair> st = new Stack<>();
        st.push(new itrPair(node));
        while (st.size() != 0) {
            itrPair pair = st.peek();
            if (!pair.ld) {
                pair.ld = true;
                if (pair.node.left != null)
                    st.push(new itrPair(pair.node.left));
            } else if (!pair.sd) {
                pair.sd = true;
                System.out.print(pair.node.data + " ");
            } else if (!pair.rd) {
                pair.rd = true;
                if (pair.node.right != null)
                    st.push(new itrPair(pair.node.right));
            } else {
                st.pop();
            }
        }
    }
    public static Node rightMostOfLeft(Node left, Node curr) {
        while (left.right != null && left.right != curr) {
            left = left.right;
        }
        return left;
    }
    public static void preOrderMorris(Node node) {
        Node curr = node;
        while (curr != null) {
            Node left = curr.left;
            if (left == null) {
                System.out.print(curr.data + " ");
                curr = curr.right;
            } else {
                Node rightMost = rightMostOfLeft(left, curr);
                if (rightMost.right == null) {
                    rightMost.right = curr; // thread create
                    curr = curr.left;
                } else {
                    System.out.print(curr.data + " ");
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }
    }
    public static void main(String[] args) 
    {
        int[] arr={10,20,40,60,-1,-1,70,-1,-1,50,80,-1,-1,-1,30,90,-1,110,150,-1,-1,-1,100,120,-1,-1,-1};
        Node node=createTree(arr);
       // display(node)
       viewSet(node);
       // System.out.println(size(node));
       // System.out.println(height(node));
       // System.out.println(findData(node,70));
       // Node n=new Node(70);
      //  rootTONodePath(node,n);
       // System.out.println(diameter_01(node));
      // System.out.println(leafToLeafPathMaxSum(node));
     // System.out.println(NodeToNodePathMaxSum(node));
     System.out.println(leafToNodeGivenSum(node,22));
    }
}