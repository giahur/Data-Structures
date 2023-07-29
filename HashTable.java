/** CSDS 233 Assignment 4
 * @author Gia Hur
 * worked with Chris Wu
 **/

// class to create a hashtable with separate chaining
 class HashTable {

    public Entry[] table;
    public int tableSize;
    public int loadFactor;

    // constructor requiring size for hash table
    public HashTable(int tableSize) {
        this.table = new Entry[tableSize];
        this.tableSize = tableSize;
    }

    // array for hash table entry
    public class Entry {
        StringNode bucket;
        
        // constructor requiring a bucket
        public Entry(StringNode bucket) {
            this.bucket = bucket;
        }
    }

    // bucket for each hash table entry
    public class StringNode {
        public String word;
        StringNode next;
        int frequency = 1;
        
        // constructor requiring word for each bucket
        public StringNode(String word) {
            this.word = word;
        }
    }
    
    // method to find frequency of words in string
    public String wordCount(String str) {
        String[] splitString = str.split("\\P{Alpha}+");
        String current;
        for(int i=0; i < splitString.length; i++) {
            current = splitString[i];
            if(current != null)
                insert(current);
        }
        return returnWordCount(str);
    }
    
    // helper method to put words into table and count frequency
    public void insert(String current) {
        int hash = Math.abs(current.hashCode()) % tableSize;
        // creates new bucket in entry if no previous
        if(table[hash] == null) {
            table[hash] = new Entry(new StringNode(current));
            loadFactor++;
        }
        // checks if previous entry is a repeat word or not
        else {
            StringNode myBucket = table[hash].bucket;
            while(myBucket.next != null && !current.equals(myBucket.word))
                myBucket = myBucket.next;
            if(current.equals(myBucket.word))
                myBucket.frequency++;
            else
                myBucket.next = new StringNode(current);
        }
        if(loadFactor / tableSize > .8)
            rehash();
    }

    // helper method to create and return a table with words in a string and their frequency
    public String returnWordCount(String str) {
        String wordFreq = "Your String \"" + str + "\" contains:\n";
        for(int i=0; i < tableSize; i++) {
            if(table[i] != null)
                wordFreq += table[i].bucket.word + ": " + table[i].bucket.frequency + "\n";
        }
        return(wordFreq);
    }

    // expands and rehashes when more than half of array is filled 
    public void rehash() {
        int previousSize = tableSize;
        Entry[] previousTable = table;
        tableSize = 2*previousSize;
        table = new Entry[tableSize];
        for(int i=0; i < previousSize; i++) {
            if(previousTable[i] != null) {
                StringNode newBucket = previousTable[i].bucket;
                while(newBucket != null) {
                    insert(newBucket.word);
                    newBucket = newBucket.next;
                }
            } 
        }
    }

    public static void main(String[] args) {
        HashTable myTable1 = new HashTable(11);
        System.out.println(myTable1.wordCount("test sentence"));
        HashTable myTable2 = new HashTable(11);
        System.out.println(myTable2.wordCount("test sentence test"));
        HashTable myTable3 = new HashTable(11);
        System.out.println(myTable3.wordCount("CSDS 233, Assignment 4 by: Gia Hur"));
        HashTable myTable4 = new HashTable(11);
        System.out.println(myTable4.wordCount("i can't hehe it's too late, it's over"));
    }
}