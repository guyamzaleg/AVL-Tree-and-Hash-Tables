import java.util.List;

public class Tester
{
    private static boolean testPassed = true;
    private static int testNum = 0;

    /**
     * This entry function will test all classes created in this assignment.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        //call the classes testing each thing apart...
        test_part_1();test_part_2();
        Avl_test();

        // Notifying the user that the code have passed all tests.
        if (testPassed)
        {
            System.out.println("All " + testNum + " tests passed!");
        }
    }
    private static void test(boolean exp)
    {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: ");
        }
    }
    private static void test_part_1()
    {
        DoubleHashTable table = new DoubleHashTable(7);

        // Add some spells to the table
        table.put(new SpellSimple("Abracadabra", "Avada Kedavra"));
        table.put(new SpellSimple("Expecto Patronum", "I’m gonna stand here like a unicorn"));
        table.put(new SpellSimple("Wingardium Leviosa", "Get up, stand up"));
        table.put(new SpellSimple("Shazam", "24K Magic in the air"));

        // Get the spells by name

        /*1*/ test(table.getCastWords("Shazam")== "24K Magic in the air"); // prints "24K Magic in the air"
        /*2*/ test(table.getCastWords("Abracadabra")=="Avada Kedavra"); // prints "Avada Kedavra"
        // Get the size of the table
        int size = table.getSize();
        /*3*/test( size==4); // prints "Table size: 4"
    }
    private static void Avl_test()
    {
     AVLTree T=new AVLTree(new Spell("fireball", "fire", 10, "fireball!"));//10
        test(T.getTreeHeight()==0);
     T.insert(new Spell("flamethrower min", "fire", 6, "foo"));
        test(T.getTreeHeight()==1);
     T.insert(new Spell("flamethrower", "fire", 8, "foo better"));
        test(T.getTreeHeight()==1);
     T.insert(new Spell("fireball II", "fire", 12,"fireball!!"));
        test(T.getTreeHeight()==2);
        T.insert(new Spell("flamethrower II", "fire", 15, "foooooooo!"));
        test(T.getTreeHeight()==2);
     T.insert(new Spell("A", "fire", 5, "a"));
        test(T.getTreeHeight()==2);
        T.insert(new Spell("B", "fire", 7, "b"));
        test(T.getTreeHeight()==2);
        T.insert(new Spell("C", "fire", 4, "c"));
        test(T.getTreeHeight()==3);
     test(T.getCategory()=="fire");
     List<Spell> L=T.getTopK(3);
     test(L.get(2).getPowerLevel()==10);

    }
    private static void test_part_2()
    {
        HashAVLSpellTable table = new HashAVLSpellTable(10);

        // create some spells
        Spell spell1 = new Spell("fireball", "fire", 10, "fireball!");
        Spell spell2 = new Spell("frostbolt", "ice", 7, "freeze please");
        Spell spell3 = new Spell("thunderstorm", "lightning", 9, "I`m going to shock you");
        Spell spell4 = new Spell("poison spray", "poison", 5, "sssss");
        Spell spell5 = new Spell("shockwave", "lightning", 8, "go pikachu!");

        // add the spells to the hash AVL spell table
        table.addSpell(spell1);
        table.addSpell(spell2);
        table.addSpell(spell3);
        table.addSpell(spell4);
        table.addSpell(spell5);

        // add more spells to an existing category
        table.addSpell(new Spell("flamethrower min", "fire", 6, "foo"));
        table.addSpell(new Spell("flamethrower", "fire", 8, "foo better"));
        table.addSpell(new Spell("fireball II", "fire", 12,"fireball!!"));
        table.addSpell(new Spell("flamethrower II", "fire", 15, "foooooooo!"));
        table.addSpell(new Spell("shockwave II", "lightning", 10,"be useful pikachu."));
        table.addSpell(new Spell("frost nova", "ice", 4, "chill dude"));

        //check searchspell method
        /*4*/test(table.searchSpell( "fire","fireball", 10)==spell1);
        /*5*/test(table.searchSpell( "fire","fireball", 9)==null);
        /*6*/test(table.searchSpell("lightning", "shockwave", 8)==spell5);
        /*7*/test(table.searchSpell("lightning", "shockwave123", 8)==null);
        /*8*/test(table.searchSpell("lightning","thunderstorm",  9)==spell3);
        /*9*/test(table.searchSpell("lightning+1+2","thunderstorm",  9)==null);




        // check for the size in total table
        /*10*/test(table.getNumberSpells()==11);

        //check for size in each category
        /*11*/test(table.getNumberSpells("fire")==5);
        /*12*/test(table.getNumberSpells("lightning")==3);
        /*13*/test(table.getNumberSpells("ice")==2);
        /*14*/test(table.getNumberSpells("poison")==1);

        // get the top 3 spells in the "fire" category
        List<Spell> fireSpells = table.getTopK("fire", 3);
        /*15*/ test(fireSpells.get(0).getPowerLevel() == 15);
        /*16*/test(fireSpells.get(1).getPowerLevel() == 12);
        /*17*/ test(fireSpells.get(2).getPowerLevel() == 10);

        // get the top 100 spells in the "lightning" category-there are only 3 in the structure-we can handle this
        List<Spell> lightningSpells = table.getTopK("lightning", 100);
        /*18*/ test(lightningSpells.get(0).getName() =="shockwave II" );
        /*19*/test(lightningSpells.get(2).getName() == "shockwave");
        /*20*/ test(lightningSpells.get(1).getName() == "thunderstorm");
        /*21*/test(lightningSpells.size()==3);

        // spell that exists in the table
        Spell searchedSpell = table.searchSpell("fire","fireball",  10);
        /*22*/test(searchedSpell!=null);
        searchedSpell = table.searchSpell("poison","poison spray",  5);
        /*23*/test(searchedSpell!=null);
        // search for a spell that does not exist in the table

        searchedSpell = table.searchSpell("fire", "fireball", 11);
        /*24*/test(searchedSpell==null);
        searchedSpell = table.searchSpell("poison","poison spray!!",  5);
        /*25*/test(searchedSpell==null);

        // search for a spell that does not exist in the table
        searchedSpell = table.searchSpell("ice", "fireball", 10);
        /*26*/test(searchedSpell==null);




        }
    }
