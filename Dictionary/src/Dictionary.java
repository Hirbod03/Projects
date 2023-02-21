/*
Hirbod Hosseini
Monday, October 10th
Description: A dictionary object implemented using separate chaining
 */
public class Dictionary implements DictionaryADT{
    // instance variable declarations
    private final int SIZE;
    private int numOfRecords;
    private RecordNode[] table;
    // construction header
    public Dictionary(int size){
        // storing size as an instance variable
        this.SIZE = size;
        // initializing hash table
        table = new RecordNode[size];
        // initializing records counter
        numOfRecords = 0;
    }

    @Override
    // adds a record to the hash table
    public int put(Record rec) throws DuplicatedKeyException {
        // initializing collision flag
        int res = 0;
        // storing hash index
        int index = hash(rec.getKey());
        // checking for collision
        if (table[index] != null){
            // checking for duplicate keys
            if (table[index].getRecord().getKey().compareTo(rec.getKey())==0)
                throw new DuplicatedKeyException("duplicate keys");
            // raising collision flag
            res = 1;
            // creating new node to put record in and store the original node as its next
            table[index] = new RecordNode(rec, table[index]);
            // storing prev pointer
            table[index].getNext().setPrev(table[index]);
            // incrementing records counter
            numOfRecords++;
            // returning flag
            return res;
        }
        // adding record to table
        table[index] = new RecordNode(rec);
        // incrementing records counter
        numOfRecords++;
        return res;
    }

    @Override
    // removes record from table
    public void remove(String key) throws InexistentKeyException {
        // storing hashed key
        int index = hash(key);
        // checking for nonexistent key
        if (table[index] == null)
            throw new InexistentKeyException("Error");
        if (table[index].getRecord().getKey().compareToIgnoreCase(key) == 0) {
            if (!table[index].hasNext()) table[index] = null;
            else if (table[index].hasNext()) {
                table[index] = table[index].getNext();
                table[index].setPrev(null);
            }
        }
    }

    @Override
    // returns inquired entry
    public Record get(String key) {
        // storing hashed key
        int k = hash(key);
        // checking for missing entry
        if (table[k]==null)
            return null;
        // copying reference to table[k]
        RecordNode n = table[k];
        while (n!=null && n.getRecord().getKey().compareToIgnoreCase(key)!=0){
            n = n.getNext();
        }
        if (n==null)
            return null;
        return n.getRecord();
    }

    @Override
    // returns the number of records
    public int numRecords() {
        return numOfRecords;
    }

    private int hash(String key){
        // converting string input into char array
        char[] keyArray = key.toCharArray();
        // initialization result and starting formula
        long res = keyArray[0];
        for(int i = 1; i<keyArray.length; i++){
            // accumulative formula
            // large constant was chosen at random: 343242143
            res += (res*343242143+keyArray[i]);
        }
        // casting long to int and returning absolute value of has
        return (int)Math.abs(res%SIZE);
    }
}