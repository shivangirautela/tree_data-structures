#include <iostream>
#include <vector>
using namespace std;
class Node
{
public:
    int data = 0;
    int freq;
    Node *left = NULL;
    Node *right = NULL;
    
    Node(int data)
    {
        this->data = data;
    }
    Node(int data, Node *left, Node *right,int freq)
    {
        this->data = data;
        this->left = left;
        this->right = right;
        this->freq=freq;
    }
};
Node *create(vector<int> &arr, int si, int ei)
{
    if (si > ei)
        return NULL;
    int mid = (si + ei) >> 1;
    Node *node = new Node(arr[mid]);
    node->left = create(arr, si, mid - 1);
    node->right = create(arr, mid + 1, ei);
    return node;
}
void display(Node *node)
{
    if (node == NULL)
        return;
    string str = "";
    str += node->left != NULL ? to_string(node->left->data) : ".";
    str += " => " + to_string(node->data) + " <= ";
    str += node->right != NULL ? to_string(node->right->data) : ".";
    cout << str << endl;
    display(node->left);
    display(node->right);
}
int maximum(Node *node)
{
    Node *curr = node;
    while (curr->right != NULL)
    {
        curr = curr->right;
    }
    return curr->data;
}
int minimum(Node *node)
{
    Node *curr = node;
    while (curr->left != NULL)
    {
        curr = curr->left;
    }
    return curr->data;
}
int height(Node *node)
{
    return (node == NULL) ? 0 : max(height(node->left), height(node->right)) + 1;
}
int size(Node *node)
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     {
    return (node == NULL) ? 0 : size(node->left) + size(node->right) + 1;
}
bool find(Node *node, int data, int level, string ans)
{
    if (node == NULL)
        return false;
    if (node->data == data)
    {
        cout << ans << " " << data << endl;
        cout << "Height of data in tree: " << level << endl;
        return true;
    }
    else if (node->data < data)
        return find(node->right, data, level + 1, ans + to_string(node->data) + " ");
    else
        return find(node->left, data, level + 1, ans + to_string(node->data) + " ");
}
int LCA(Node *node, int n1, int n2)
{
    if (node == NULL)
        return -1;
    if (node->data < n1)
        return LCA(node->right, n1, n2);
    else if (node->data > n2)
        return LCA(node->left, n1, n2);
    else
        return node->data;
}
Node* pred=NULL;
Node* succ=NULL;
Node* pre = NULL;
void predSucc_01(Node* node,int data){
  if(node==NULL) return;
   predSucc_01(node->left,data);
     if(node->data == data)
         pred=pre;
     if(pre!=NULL && pre->data==data) succ=node;
     pre=node;

   predSucc_01(node->right,data);
}
void  addNode_01(Node *node,Node *par,int data,bool isLeft)
{
    if(node==NULL)
    {
    Node *child = new Node(data);
    if(isLeft) 
    par->left=child;
    else
    {
        par->right=child;
    }
    return ;
    }
    if(node->data ==data)
    {
        node->freq++;
        return ;
    }
    else if(node->data < data)
    {
        addNode_01(node->right,node,data,false);
    }
    else
    {
        addNode_01(node->left,node,data,true);
    }

}

Node*  addNode_02(Node *node,int data)
{
    if(node==NULL)
    {
    Node *child = new Node(data);
    return child;
    }

    if(node->data ==data)
    {
        node->freq++;
        return node;
    }
    else if(node->data < data)
    {
     node->right= addNode_02(node->right,data);
    }
    else
    {
      node->left=  addNode_02(node->left,data);
    }
return node;
}
Node * deletenode(Node * node, int data)
{  if(node->data==data) return NULL;

    if(node->data <data)
    {
    node->right=deletenode(node->right,data);
    }
    else if( node->data > data)
    {
        node->left=deletenode(node->left,data);
    }
    else
    {
     if(node->freq >1)
    {
        node->freq--;
    }
    else
    {
        if(node->left==NULL || node->right==NULL)
        {
            Node *n=node->left!=NULL? node->left:node->right;
            delete node;
            return n;
        }
        else
        {
            int max=maximum(node->left);
            node->data=max;

            Node* n1=deletenode(node->left,max);
            return n1;
        }
        
    }
    }
    
}
void solve()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120};
    Node *root = create(arr, 0, arr.size() - 1);
   // display(root);
    // cout<<maximum(root)<<endl;
    // cout<<minimum(root)<<endl;
    // cout<<height(root)<<endl;
    // cout<<size(root)<<endl;
    // cout<<(boolalpha)<<find(root,80,0,"")<<endl;
    // cout << LCA(root, 100, 120) << endl;
 //   predSucc_01(root,120);
    
 //   cout<<(pred!=NULL?pred->data:-1)<<" -> " << 120 << " <- "<< (succ!=NULL?succ->data:-1)<<endl;
  //  addNode_01(root,NULL,35,true);
  //  display(root);
 // root=  addNode_02(root,35);
 //   display(root);
   //root= deletenode(root,40); 
 //  display(root); 
}
int main(int args, char **argv)
{
    solve();
    return 0;
}