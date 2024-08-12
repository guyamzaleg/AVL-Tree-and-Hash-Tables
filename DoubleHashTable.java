public class DoubleHashTable {
    private SpellSimple[] table;
    private int capacity;
    private int size;
    private int steps=0;

    public DoubleHashTable(int capacity)
    {
        this.capacity=capacity;
        table=new SpellSimple[capacity];
    }
    public boolean put(SpellSimple spell)
    {
        this.steps=0;
            while(this.steps<this.capacity-1)
            {
                int index=(hash1(spell.getName())+this.steps*hash2(spell.getName()))%this.capacity;//defying Hash function
                if(table[index]==null)//empty cell in the putting order-we can put this spell here
                {
                    table[index]=spell;
                    this.size++;
                    return true;
                }
                else{this.steps++;}
            }
            return false;//reached m-1 tries to put the spell unsucssesfully.
    }

    public String getCastWords(String name)
    {
        int i=0;
        while(i<this.capacity-1)
        {
            int index = (hash1(name) + i * hash2(name)) % this.capacity;//executing the Hash functions
            if (table[index].getName()==name)
            {
                return table[index].getWords();
            } //return the words of the requested spell
            i++;
        }
            return null;
    }

    public int getSize()
    {
        return this.size;
    }

    public int getLastSteps()
    {
        return this.steps;
    }

    private int hash1(String name)
    {
        int hash=0;
        for (int i=0;i<name.length();i++) {hash=hash+name.charAt(i)*31;}
        return hash%this.capacity;
    }
    private int hash2(String name)
    {
        int hash=0;
        for (int i=0;i<name.length();i++) {hash=hash+name.charAt(i)*13;}
        return (1+hash%(this.capacity-2));
    }

}
