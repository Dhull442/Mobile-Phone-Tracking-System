public class Exchange{
	private int uuid;
	public Exchange parent = null;
	private Exchange next;
	ExchangeLinkedList elements = new ExchangeLinkedList();
	int size =0;
	MobilePhoneSet phones = new MobilePhoneSet();
	public Exchange(int id)
	{
		this(id,null);
	}

	public Exchange(int id, Exchange node)
	{
		this.uuid = id;
		next = node;
	}

    // @return the data
    public int getID() {
        return uuid;
    }

    public void setID(int data) {
        this.uuid = data;
    }

    // @return next
    public Exchange getNext() {
        return next;
    }

    // @param Node next set to this.next
    public void setNext(Exchange next) {
        this.next = next;
    }

    public Boolean isEqual(Exchange a)
    {
    	return (a.getID()==uuid);
    }
    public Boolean isEqualID(int a)
    {
    	return (a == uuid);
    }
    public int exindex(Exchange a)
    {
    	return elements.getIndex(a);
    }
    public Exchange excontainingex(Exchange a)
    {
   		if(hasNode(a))
   			return this;
   		else
   		{
   			for(int i=0;i<numChildren();i++)
   			{
   				Exchange x = child(i).excontainingex(a);
   				if(x!=null)
   					return x;
   			}
   		}
   		return null;
    }
    public void addNextlevel(Exchange a)
    {	if( size < 1)
    	{
    		elements.initialize();

    	}
    	size++;
    	a.parent = this;
    	elements.add(a);
    }
        public Boolean baseforphone(MobilePhone m)
    {
        Boolean has = true;
        if(this.hasNumber(m))
        {
            for(int i =0;i<this.numChildren();i++)
            {
                if(this.child(i).hasNumber(m))
                {
                    has = false;
                    break;
                }
            }
        }
        else
            has = false;
        return has;
    }
    public void regNumber(MobilePhone number)
    {	
    	number.home = this;
    	phones.Insert(number);
    	Exchange tmp = this.parent();
    	do
    	{
    		tmp.phones.Insert(number);
    		tmp = tmp.parent();
    	}
    	while(tmp!=null);

    }
//     All usual Node methods for a general tree like public Exchange
// parent(), public Exchange numChildren() (for number of chil-
// dren), public Exchange child(int i) (returns the ith child),
// public Boolean isRoot(), public RoutingMapTree subtree(int
// i) (returns the ith subtree) and any other tree methods you need.
// The class definition RoutingMapTree will be defined later.
// – public MobilePhoneSet residentSet(): This returns the resi-
// dent set of mobile phones of the exchange.

    public Exchange parent()
    {
    	return this.parent;
    }
    public int numChildren()
    {
    	// if(size == 0)
    		// return 0;
    	return elements.size();
    }
    public Exchange child(int i)
    {
    	return elements.getExchange(i);
    }
    public Boolean isRoot()
    {
    	return (this.parent()==null);
    }
    public RoutingMapTree subtree(int i)
    {
    	return new RoutingMapTree(this.child(i));
    }
    public MobilePhoneSet residentSet()
    {
    	return phones;
    }
    public Boolean hasNode(Exchange a)
    {
    	return elements.contains(a);
    }
    public Boolean hasNumber(MobilePhone a)
    {
    	return phones.IsMember(a);
    }
    public Exchange returnExchangebyID(int a)
    {
    	if(uuid == a)
    		return this;
    	else
    	{
    		for(int i =0; i<numChildren();i++)
    		{
    			Exchange x = child(i).returnExchangebyID(a);
    			if(x!= null)
    				return x; 
    		}
    	}
    	// throw new Exceptionception("No such base station!");
    	return null;
    }
}
class ExchangeLinkedList{
	// fields
    private Exchange head;
    private Exchange last;
    private int size = 0;

    // constructor, used when the class is first called
    public ExchangeLinkedList() {
        // head = last = new Exchange(-1);
    }
    public void initialize(){
    	head = last = new Exchange(-1);
    }

    // add method
    public void add(Exchange s) {
    	if(!contains(s))
        {last.setNext(s);
        last = last.getNext();
        size++;}
    }

    // remove method, if it returns false then the specified index element doens not exist
    // otherwise will return true
    public void remove(int data) {
    	try{
        Exchange current = head.getNext();
    Exchange last = null;
    while (current != null) {
        int dataOld = current.getID();
        if ((dataOld == -1 && data == -1) || (dataOld != -1 && dataOld==data)) {
            Exchange afterRemoved = current.getNext();
            if (last == null) {
                head.setNext(afterRemoved);
            } else {
                last.setNext(afterRemoved);
            }
            if (afterRemoved.getNext() == null) {
                last = afterRemoved;
            }
            size--;
            return;
        } else {
            last = current;
            current = current.getNext();
        }
    }}
    catch(Exception Nothere)
    	{System.out.println("element not in set!");}
    }
    //will return the size of the list - will return -1 if list is empty
    public int size() {
        return size;
    }

    // will check if the list is empty or not
    public boolean isEmpty() {
        return true;
    }

    // @param (index) will get the data at specified index
    public Exchange getExchange(int index) {
        try{
        Exchange current = head.getNext();
        for(int i = 0;i < index;i++) {
            if(current.getNext() == null) {
                return null;
            }
            current = current.getNext();
        }

        return current;
    }
    catch(Exception IndexOutOfBoundsException)
    	{
    		System.out.println("Index out of range!");
    		return null;
    	}
	}
	public int getIndex(Exchange a)
	{
		for(int i = 0;i<size();i++) {
            if(getExchange(i).isEqual(a)) {
                return i;
            }
        }
        return -1;
	}

    //@param will check if the arguement passed is in the list
    // will return true if the list contains arg otherwise false
    public boolean contains(Exchange s) {
        for(int i = 0;i<size();i++) {
            if(getExchange(i).isEqual(s)) {
                return true;
            }
        }
        return false;
    }

    //@return first node
    public Exchange getHead() {
        return head;
    }

    // @return (recursively) list
    public void print() {
        Exchange current = head.getNext();int i =0;
        while(current!=null)
        {
            if(i!=0)
            {
                System.out.print(", ");
            }
            i++;
            System.out.print(current.getID());
            current=current.getNext();
        }
    }
}
