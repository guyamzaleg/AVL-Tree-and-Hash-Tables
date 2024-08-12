import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AVLTree {

    private Node root;
    private int size;
    private String category;
	
	// private Node class for the AVL Tree nodes
    private class Node
    {
        private Spell spell;//spell.powerlevel=key of node
        private Node left;
        private Node right;
        private int height;
        private Node(Spell spell)
        {
            this.left=null;
            this.right=null;
            this.spell=spell;
            this.height=0;
        }
    }

    // Constructor, getters, setters
    public AVLTree(Spell spell)
    {
        this.category= spell.getCategory();
        this.root=new Node(spell);
        this.size=1;
    }

    /**
     *
     * @return the tree height
     */
    public int getTreeHeight()
    {
        return this.root.height;
    }

    /**
     *
     * @return the tree size
     */
    public int getSize()
    {
        return this.size;
    }

    /**
     *
     * @return the tree category
     */
    public String getCategory()
    {
        return this.category;
    }


    /**
     * search for a cretain spell in the tree
      * @param spellName the name of the spell we are search to
     * @param powerLevel-the power level of the spell
     * @return the spell itself
     */
    public Spell search(String spellName, int powerLevel)
    {
        Node current=this.root;
        boolean found=false;
        while(current!=null)
        {
            int curr_pow=current.spell.getPowerLevel();
            if(curr_pow==powerLevel&&current.spell.getName()==spellName)
            {
                found=true;
                break;
            }
            if(curr_pow>powerLevel)
            {
                current=current.left;
            }
            else
            {
                current=current.right;
            }
        }
        if(!found){return null; }
        return current.spell;
    }


    /////----------Helping functions for insert----------/////

    /**
     * height update for a node
     * @param node-the node we will update its height.
     */
    private void updateHeight(Node node)
    {
        node.height = 1 + Integer.max(height_of_node(node.left),height_of_node(node.right));
    }

    /**
     * return the height of a node-
     * defiyed as the height of the heighest son +1
     * if its null than -1
     * @param node-the node we will check its height
     * @return its height
     */
   private int height_of_node(Node node)
   {
        if(node == null)
        {
            return -1;
        }
       else
       {
           return node.height;
       }
    }

    /**
     * return the blanced of a node-left-right
     * if node is null than its balanced-0
     * @param node -the nodde we want to check if balanced
     * @return the balance(left height-right height)
     */
   private int Balance_of_node(Node node)
   {
       if (node == null)
       {
           return 0;
       }
       else
       {
           return height_of_node(node.left) - height_of_node(node.right);
       }
    }
    /**
     * execute the left rotation on a node.
     * @param y-the node rotate around
     * @return the node replaced it,balanced
     */
    private Node left_rotate(Node y)//rotate around y to the right-if y was the root ,x is the new root
      //           Y                       X
      //         /  \                    /   \
      //             X       --->       Y
      //           /  \               /  \
      //          Z                       Z
    {
        Node x = y.right;
        Node z = x.left;
        if(this.root==y){this.root=x;}
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;

    }

    /**
     * execute the right rotation on a node.
     * @param y-the node rotate around
     * @return the node replaced it,balanced
     */
   private Node right_rotate(Node y) //rotate around y to the right-if y was the root ,x is the new root
    //             Y                     X
    //            /  \                  /  \
    //           X           --->           Y
    //         /  \                       /  \
    //             Z                     Z
    {

        Node x = y.left;
        Node z = x.right;
        if(this.root==y){this.root=x;}
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;

    }

    /**
     * analyze the balance situation and match every case a solution.
     * using right and left rotations helping methods
     * implementation as learnt in class.
     * @param z-the node we want to balance using rotations
     */
    private Node balance_node(Node z)
    {
        updateHeight(z);
        int balance=Balance_of_node(z); ;
        if (balance <-1) //right child size is bigger and node is  unbalanced
        {
            //case of RR
            if (height_of_node(z.right.right) > height_of_node(z.right.left))
            {
                z = left_rotate(z);
            }
            else
            //case of RL
            {
                //turn it into RR
                z.right = right_rotate(z.right);
                //case of RR
                z = left_rotate(z);
            }
        }
        else if (balance >1)//balance>1 -left child size is bigger and node is  unbalanced
        {
            //case of LL
            if (height_of_node(z.left.left) > height_of_node(z.left.right))
            {
                z = right_rotate(z);
            }
            //case of LR
            else
            {
                //turn it into LL
                z.left = left_rotate(z.left);
                //case of LL
                z = right_rotate(z);
            }
        }
        //if the node was balanced-than we will return itself without changing.
        return z;
    }

    /**
     * execute the insert but the tree can get unbalanced.
     * using helping method called balance_node to balance the deepest unbalanced node
     * by doing that it will balance the whole tree- because it was balanced before insertion
     * @param node-current node in the recursion insert route
     * @param spell-the spell we want to insert
     * @return the next Node in the route to work on.
     */
    private Node the_insert(Node node, Spell spell)
    {

        if (node == null) //then it's a leaf-we need to create here new node with spell.
        {
            return new Node(spell);
        }

        else
        {
            node.height++;
            if (node.spell.getPowerLevel() > spell.getPowerLevel())//we need to go left
            {
                node.left = the_insert(node.left, spell);
            } else if (node.spell.getPowerLevel() < spell.getPowerLevel())//we need to go right
            {
                node.right = the_insert(node.right, spell);
            }
            //at the route to the root-fix the balances in those who need it(the first fix should fix all the others above)
//        int curr_key=node.spell.getPowerLevel();
//        int balance_of_node=Balance_of_node(node);

        }
        return balance_node(node);
    }

    /** insert method in avl-calls a recursive helping function and updated size
     * @param spell-the inserted spell to the tree
     */
    public void insert(Spell spell)
    {
        this.size++;
        the_insert(root,spell);
    }

    /**
     * helping function-
     * @param node
     * @return the max under a certain node
     */
    private Node max_under_node(Node node)
    {
        Node curr_max=node;
        while(curr_max.right!=null)//get the biggest key
        {
            curr_max=curr_max.right;
        }
        return curr_max;

    }

    /**
     * helping function-find the predecessor of a node in a tree
     * @param node
     * @return the biggest node which is smaller than node
     */
    private Node Predecessor(Node node)
    {
        if(node.left!=null)//biggest on the left subtree is the predecessor
        {
            return max_under_node(node.left);
        }
        else
        {
            Node temp=null;//defying node
            Node curr_root=this.root;//start from the root
            while(curr_root!=node)//on the route to node
            {

                if (node.spell.getPowerLevel() > curr_root.spell.getPowerLevel())//we need to go right
                {
                    temp = curr_root; //update temp to the curr_root and go right
                    curr_root = curr_root.right;
                }
                else
                    curr_root = curr_root.left;//just go left
            }
            return temp;
        }
    }

    /**
     * using helping funcrions prdecessor and max_under_node to find the greatest k spells
     * @param k
     * @return the biggest k spells as a list
     */
    public List<Spell> getTopK(int k)
    {
        Node curr_max=this.root;
        List L=new ArrayList();//list of the spells
        curr_max=max_under_node(this.root);
        for( int i=0;i<Math.min(k,this.size);i++)
        {
            L.add(curr_max.spell);
            //System.out.println(curr_max.spell);
            curr_max=Predecessor(curr_max);
        }
        return L;
    }


}


