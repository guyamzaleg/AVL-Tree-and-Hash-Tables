
import java.util.LinkedList;
import java.util.List;

public class HashAVLSpellTable
{
    private LinkedList<AVLTree>[] buckets;
    private int tableSize;
    private int numSpells;

    /**
     * constructor
     * @param size-the table size
     */
    public HashAVLSpellTable(int size)
    {
        this.tableSize=size;
        this.buckets=new LinkedList[this.tableSize];
    }

    /**
     * create the hash function
     * @param category-the string we will hash into index
     * @return the matching index to the category
     */
    private int hash(String category)
    {
        int hash=0;
        for (int i=0;i<category.length();i++) {hash=hash+category.charAt(i);}
        return hash%this.tableSize;
    }
    /**
     * adding a spell to the hash table in the matching tree by its category
     * @param s-the spell we will add.
     */
    public void addSpell(Spell s) {
        numSpells++;
        AVLTree current = null;
        int i = 0;
        if(this.buckets[hash(s.getCategory())]==null)
        {
            this.buckets[hash(s.getCategory())]=new LinkedList();
            this.buckets[hash(s.getCategory())].addFirst(new AVLTree(s));
            return;
        }
        current =  this.buckets[hash(s.getCategory())].get(i);
        while (current != null) //running through the linked list with the matching hash value to s.category
        {
            if (current.getCategory() == s.getCategory())   //if we found an existed tree matching s category
            {                                               // -we will execute insert method into the current tree
                current.insert(s);
                return;
            }
            //else keep trying by get the next tree in the current list
            i++;
            if(current ==  this.buckets[hash(s.getCategory())].getLast())
            {
                this.buckets[hash(s.getCategory())].add(new AVLTree(s));
                return;
            }
        }
        //reach here means we need to create a new tree for s category.
        this.buckets[hash(s.getCategory())].add(new AVLTree(s));
    }

    /**
     * we will search a certain spell in the hash table,using tree method search.
     * @param category-spells category
     * @param spellName- spells name
     * @param powerLevel-spells power level
     * @return the spell itself
     */
    public Spell searchSpell(String category, String spellName, int powerLevel)
    {
        AVLTree current;
        int i = 0;
        boolean found=false;
        if (this.buckets[hash(category)] == null)
        {
            return null;
        }
        current = this.buckets[hash(category)].get(i);
        while (current != null) //running through the linked list with the matching hash value to category
        {
            if (current.getCategory() == category )   //if we found an existed tree matching  category
            {
                // -we will execute search method in the current tree
                return current.search(spellName, powerLevel);
            }
            i++;
            current =  this.buckets[hash(category)].get(i);
        }
       return null;
    }

    /**
     * @return total spells in the hash table
     */
    public int getNumberSpells()
    {
        return numSpells;
    }

    /** using the tree method getsize.
     * method recieves a category an gives the number of spells in its matching tree
     * @param category-the requested category
     * @return the size of this category tree
     */
    public int getNumberSpells(String category)
    {
        AVLTree current;
        int i = 0;
        boolean found=false;
        if (this.buckets[hash(category)] == null)
        {
            return 0;
        }
        current = this.buckets[hash(category)].get(0);
        while (current != null) //running through the linked list with the matching hash value to category
        {
            if (current.getCategory() == category)   //if we found an existed tree matching  category
            {
                // -we will execute getsize method in the current tree
                return current.getSize();
            }
            i++;
            current =  this.buckets[hash(category)].get(i);
        }
        return 0;
    }

    public List<Spell> getTopK(String category, int k)
    {
        AVLTree current;
        int i = 0;
        boolean found=false;
        if (this.buckets[hash(category)] == null)
        {
            return null;
        }
        current = this.buckets[hash(category)].get(0);
        while (current != null) //running through the linked list with the matching hash value to category
        {
            if (current.getCategory() == category)   //if we found an existed tree matching  category
            {
               // -we will execute search method in the current tree
                return current.getTopK(k);
            }
            i++;
            current =  this.buckets[hash(category)].get(i);
        }
        return null;
    }
}
