# AVL Tree and Hash Tables Project

## Introduction
This project is part of the Spring 2023 Introduction to Data Structures course. It focuses on implementing and understanding AVL Trees and Hash Tables using Java and Object-Oriented Programming (OOP) principles.

## Project Structure
```
AVLTreeAndHashTables/
├── AVLTree.java
├── DoubleHashTable.java
├── HashAVLSpellTable.java
├── Spell.java
├── SpellSimple.java
├── Tester.java
└── README.md
```

## Description

### AVL Trees
An AVL tree is a self-balancing binary search tree where the difference in heights between the left and right subtrees cannot be more than one for all nodes. This balancing ensures that basic operations like insertion, deletion, and lookup remain efficient, with time complexity of O(log n).

### Hash Tables
Hash tables are data structures that offer efficient data retrieval using keys. This project implements hash tables with double hashing and chaining using AVL trees.

## Implementations

### Part 1: Double Hash Table
**Classes:**
- `SpellSimple`: Represents a simple spell with a name and casting words.
- `DoubleHashTable`: Implements a hash table using double hashing for collision resolution.

**Key Functions:**
- `hash1(String name)`: Computes the first hash value.
- `hash2(String name)`: Computes the second hash value.
- `put(SpellSimple spell)`: Inserts a new spell into the hash table.
- `getCastWords(String name)`: Retrieves the casting words for a given spell name.

### Part 2: Hash Table with AVL Trees
**Classes:**
- `Spell`: Extends `SpellSimple` to include category and power level.
- `AVLTree`: Implements an AVL tree where nodes store spells and are ordered by their power level.
- `HashAVLSpellTable`: Implements a hash table where each bucket is a linked list of AVL trees.

**Key Functions:**
- `addSpell(Spell s)`: Adds a spell to the hash table.
- `searchSpell(String category, String spellName, int powerLevel)`: Searches for a spell by category, name, and power level.
- `getTopK(String category, int k)`: Retrieves the top k spells in a category based on power level.

### Object-Oriented Principles
The project follows OOP principles, utilizing classes and inheritance to create a modular and maintainable codebase. Key principles include:
- **Encapsulation**: Data members are private, with public getters and setters.
- **Inheritance**: `Spell` extends `SpellSimple`, `AVLTree` nodes use spells, and `HashAVLSpellTable` integrates AVL trees.
- **Polymorphism**: Methods are overridden to provide specific functionalities.

## Example Usage

### Creating and Using Double Hash Table
```java
// Create a new hash table with capacity 11
DoubleHashTable hashTable = new DoubleHashTable(11);

// Create a new spell
SpellSimple spell = new SpellSimple("Fireball", "Incendio");

// Insert the spell into the hash table
hashTable.put(spell);

// Retrieve the casting words for the spell "Fireball"
String words = hashTable.getCastWords("Fireball");
System.out.println("Cast words for Fireball: " + words); // Output: Incendio
```

### Creating and Using AVL Tree and Hash Table
```java
// Create a new spell
Spell spell = new Spell("Fireball", "Fire", 50, "Incendio");

// Create a new AVL tree and insert the spell
AVLTree tree = new AVLTree(spell);
tree.insert(new Spell("Ice Blast", "Fire", 30, "Gelum"));

// Create a new hash table with AVL trees and add spells
HashAVLSpellTable hashTable = new HashAVLSpellTable(10);
hashTable.addSpell(spell);
hashTable.addSpell(new Spell("Ice Blast", "Fire", 30, "Gelum"));

// Search for a spell
Spell foundSpell = hashTable.searchSpell("Fire", "Fireball", 50);
System.out.println(foundSpell); // Output: Fireball (Fire) - Power Level: 50, to cast say: Incendio

// Get top k spells in the "Fire" category
List<Spell> topSpells = hashTable.getTopK("Fire", 1);
for (Spell s : topSpells) {
    System.out.println(s);
}
```

## Testing
Run the `Tester` class to execute all the tests. Ensure that all tests pass to verify the correctness and robustness of the implementations.

## Contributing
Feel free to fork this project and contribute by submitting pull requests. Make sure to follow the coding standards and write comprehensive tests for any new features or bug fixes.
