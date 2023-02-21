/*
Hirbod Hosseini
Monday, October 10th
CS 2210
Professor Solis-Oba
Description: Node object implementation that only stores objects of the Record type
 */
public class RecordNode {
    // instance variable declarations
    private Record data;
    private RecordNode next, prev;

    // constructors
    public RecordNode(){}

    public RecordNode(Record data){
        this.data = data;
    }

    public RecordNode(Record data, RecordNode next){
        this.data = data;
        this.next = next;
    }

    public RecordNode(Record data, RecordNode next, RecordNode prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    // record getter method
    public Record getRecord(){
        return data;
    }

    // next getter method
    public RecordNode getNext(){
        return next;
    }

    // has next checker method
    public boolean hasNext(){
        return (next != null);
    }

    // next setter method
    public void setNext(RecordNode next){
        this.next = next;
    }

    // prev setter method
    public void setPrev(RecordNode prev){
        this.prev = prev;
    }

    // prev getter method
    public RecordNode getPrev(){
        return (this.prev);
    }

    // prev checker method
    public boolean hasPrev(){
        return prev!=null;
    }
}
