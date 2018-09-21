import java.util.*;
 
class ll {

    // fields
    private Node head;
    private Node last;
    private int size = 0;

    // constructor, used when the class is first called
    public ll() {
        head = last = new Node(null);
    }

    // add method
    public void add(MobilePhone s) {
    	if(!contains(s))
        {
            Node current=head;
            while(current.getNext()!=null)
            {
                current=current.getNext();
            }
            current.setNext(new Node(s));
        size++;}
    }

    public void remove(MobilePhone data) {
    	try{
            if(size==1)
            {
                if(getData(0).isEqual(data))
                {
                    size = 0;
                    head = last = new Node(null);
                }
                return;
            }
        Node current = head;
    // Node last = null;
    // while (current != null) {
    //     MobilePhone dataOld = current.getData();
    //     if ((dataOld == null && data == null) || (dataOld != null && dataOld.isEqual(data))) {
    //         Node afterRemoved = current.getNext();
    //         if (last == null) {
    //             head.setNext(afterRemoved);
    //         } else {
    //             last.setNext(afterRemoved);
    //         }
    //         if (afterRemoved.getNext() == null) {
    //             last = afterRemoved;
    //         }
    //         size--;
    //         return;
    //     } else {
    //         last = current;
    //         current = current.getNext();
    //     }
    // }
        while(current!=null)
        {
            if(current.getNext().getData().isEqual(data))
            {
                current.setNext(current.getNext().getNext());
                size--;
                break;
            }
            current=current.getNext();
        }
}
    catch(Exception Nothere)
    	{System.out.println("element not in set!");}
    }
    public int size() {
        return size;
    }

    // will check if the list is empty or not
    public boolean isEmpty() {
        return ( size== 0);
    }

    // @param (index) will get the data at specified index
    public MobilePhone getData(int index) {
        try{
        Node current = head.getNext();
        for(int i = 0;i < index;i++) {
            current = current.getNext();
        }

        return current.getData();
    }
    catch(Exception IndexOutOfBoundsException)
    	{
    		System.out.println("Index out of range!");
    		return null;
    	}				
	}

    //@param will check if the arguement passed is in the list
    // will return true if the list contains arg otherwise false
    public boolean contains(MobilePhone s) {
        Node current=head.getNext();
        while(current != null)
        {
            if(current.getData().isEqual(s))
                return true;
            current=current.getNext();
        }
        return false;
    }
    public boolean containsinON(MobilePhone s) {
        for(int i = 0;i<size();i++) {
            if(getData(i).isEqual(s) && getData(i).status()) {
                return true;
            }
        }
        return false;
    }
    public MobilePhone returnthis(MobilePhone s)
    {	
    	for(int i = 0;i<size();i++) {
            if(getData(i).isEqual(s)) {
                return getData(i);
            }
        }
        return null;
    }
    public void print()
    {
        Node current = head.getNext();
        int i =0;
        while(current!=null)
        {   if(i!=0)
            System.out.print(", ");
            System.out.print(current.getData().number());
            i++;
            current=current.getNext();
        }
    }
    //@return first node
    public Node getHead() {
        return head;
    }

    // @return (recursively) list
    // public void print(Node n) {
    //     if(n == null) {
    //         return;
    //     }else {
    //         System.out.println(n.getData());
    //         // print(n.getNext());
    //     }
    // }
}
class Node{

    // Fields
    private MobilePhone data;
    private Node next;

    // constructor
    public Node(MobilePhone data) {
        this(data,null);
    }

    // constructor two with Node parameter
    public Node(MobilePhone data, Node node) {
        this.data = data;
        next = node;
    }

    /**
     * Methods below return information about fields within class
     * */

    // @return the data
    public MobilePhone getData() {
        return data;
    }

    // @param int data to this.data
    public void setData(MobilePhone data) {
        this.data = data;
    }

    // @return next
    public Node getNext() {
        return next;
    }
    // @param Node next set to this.next
    public void setNext(Node next) {
        this.next = next;
    }

}
public class MobilePhoneSet{ 
	public ll list=new ll();
public Boolean IsEmpty()
{
	return !list.isEmpty();
}
public Boolean IsMember(MobilePhone o)
{
	return list.contains(o);	
}
public Boolean memberIsOn(MobilePhone o)
{
    return list.containsinON(o);
}
public MobilePhone returnbyID(MobilePhone a){
		return list.returnthis(a);
}
public void Insert(MobilePhone o){
	list.add(o);
}
public void Delete(MobilePhone o){
	list.remove(o);
}
public int size()
{
    return list.size();
}
public Myset Union(Myset a){
	Myset union=new Myset();
	int sizethis=list.size();
	int sizea=a.list.size();
	for(int i=0;i<sizea;i++)
	{
		union.list.add(a.list.getData(i));
	}
	for(int i=0;i<sizethis;i++)
	{
		if(!a.list.contains(list.getData(i)))
			union.list.add(list.getData(i));
	}
	return union;
}
public Myset Intersection(Myset a){
	Myset intersection=new Myset();
	int sizethis=list.size();
	int sizea=a.list.size();
	for(int i=0;i<sizethis;i++)
	{
		if(a.list.contains(list.getData(i)))
			intersection.list.add(list.getData(i));

	}
	return intersection;
}
public void print(){
// 	int xi=0;
// 	for(int i =0;i<list.size();i++)
// 		{
// 			if(xi != 0)
// 				System.out.print(", ");
// 			System.out.print(list.getData(i).number());xi++;

// }
    list.print();
}

}