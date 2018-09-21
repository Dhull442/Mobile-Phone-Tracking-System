import java.util.*;
import java.io.*;
public class RoutingMapTree{
	Exchange root;
	MobilePhoneSet off = new MobilePhoneSet();
	public RoutingMapTree() {
		root = new Exchange(0);
	}
	public RoutingMapTree(int id){
		root = new Exchange(id);
	}
	public RoutingMapTree(Exchange a)
	{
		root=a;
	}
	public Boolean containsNode(Exchange a)
	{
		if(root.hasNode(a)||root.isEqual(a))
			return true;
		for(int i=0;i<root.numChildren();i++)
		{
			RoutingMapTree temp =root.subtree(i);
			if(temp.containsNode(a))
				return true;
		}
		return false;
	}

	public void switchOn(MobilePhone a, Exchange b)
	{
		try{
		// MobilePhone newa = a;
		// newa.switchOn();
		// newa.home = b;
			if(root.phones.IsMember(a))
			{
				return;
			}
			if(off.IsMember(a))
				off.Delete(a);
		if(this.containsNode(b))
		{	
			// if(root.hasNumber(a))
			// {root.phones.Delete(a);}
			root.phones.Insert(a);
		if(root.isEqual(b))
			{
			return;
			}
		for(int i=0;i<root.numChildren();i++)
		{
			RoutingMapTree temp =root.subtree(i);
			temp.switchOn(a,b);
		}}
		}
		catch(Exception Switchoff)
		{
			System.out.println("Error - can't switch on!");
		}
	}
	
	public void switchOff(MobilePhone a)
	{	//MobilePhone newa = a;
		// newa.switchOff();
		off.Insert(a);
		if(root.phones.IsMember(a))
		{
			root.phones.Delete(a);
			// root.phones.Insert(newa);
		for(int i=0;i<root.numChildren();i++)
		{
			RoutingMapTree temp =new RoutingMapTree(root.child(i));
			temp.switchOff(a);
		}
		}
// 	: This method only works
// on mobile phones that are currently switched on. It switches the
// phone a off. The entire routing map tree has to be updated ac-
// cordingly.
	}
	public Exchange findPhone(MobilePhone m)
	{
// 		Given a mobile phone
// m it returns the level 0 area exchange with which it is registered or
// throws an exception if the phone is not found or switched off.
		if(root.phones.IsMember(m))
		{	
			{if(root.baseforphone(m))
			return root;
		for(int i=0;i<root.numChildren();i++)
		{
			RoutingMapTree temp =new RoutingMapTree(root.child(i));
			Exchange x = temp.findPhone(m);
			if(x!=null)
				return x;
		}}
		}
		return null;
	}
		public Exchange lowestRouter(Exchange a, Exchange b)
	{
// Given
// two level 0 area exchanges a and b this method returns the level i
// exchange with the smallest possible value of i which contains both a
// and b in its subtree. If a = b then the answer is a itself.
		if(a.isEqual(b))
			return a;
		if(this.containsNode(a) && this.containsNode(b))
		{	boolean thisone = true;
			int i=0;
			for(i=0;i<root.numChildren();i++)
			{
				RoutingMapTree temp =new RoutingMapTree(root.child(i));
				if(temp.containsNode(a) && temp.containsNode(b))
					{thisone=false;break;}
			}
			if(thisone)
				return root;
			else
			{
				RoutingMapTree hasthelowest=new RoutingMapTree(root.child(i));
				return hasthelowest.lowestRouter(a,b);
			}
		}
		return null;
	}
		public ExchangeLinkedList routeCall(MobilePhone a, MobilePhone b)
	{
// 		This method helps initiate a call from phone a to phone b. It returns
// a list of exchanges. This list starts from the base station where a is
// registered and ends at the base station where b is registered and repre-
// sents the shortest route in the routing map tree between the two base
// stations. It goes up from the initiating base station all the way to
// the lowestRouter connecting the initiating base station to the final
// base station and then down again. The method throws exceptions as
// appropriate.
		try{
		ExchangeLinkedList list = new ExchangeLinkedList();
				list.initialize();
		Exchange aEx = this.findPhone(a);
		Exchange bEx = this.findPhone(b);
		if(aEx == null)
		{
			System.out.print("The number from which you're calling is unindentifiable!");
			return null;
		}
		if(bEx==null)
		{
			System.out.print("Dialled number is either switched off or doesn't exist!");
			return null;
		}
		Exchange mid = this.lowestRouter(aEx,bEx);
		this.routecallertolowestroute(list,aEx,mid);
		this.routelowestroutetoreceiver(list,bEx,mid);
		return list;}
		catch(Exception error)
		{
			System.out.print("Error - Can't find route!");
		}
		return null;
	}


	public void routecallertolowestroute(ExchangeLinkedList list, Exchange a, Exchange b)
	{
		Exchange x =new Exchange(b.getID());
		// ExchangeLinkedList list = new ExchangeLinkedList();
		if( a.isEqual(b) )
		{	
			list.add(x);
			return;
		}
		RoutingMapTree tmp = new RoutingMapTree(b);
		if(tmp.containsNode(a))
		{
			for(int i=0;i<b.numChildren();i++)
			{
				// RoutingMapTree tmpx = new RoutingMapTree(b.child(i));
				this.routecallertolowestroute(list,a,b.child(i));
			}
			list.add(x);
		}
			
 	}
 	public void routelowestroutetoreceiver(ExchangeLinkedList list, Exchange a, Exchange b)
	{
		Exchange x =new Exchange(b.getID());
		// ExchangeLinkedList list = new ExchangeLinkedList();
		if( a.isEqual(b) )
		{
			list.add(x);
			return;
		}
		RoutingMapTree tmp = new RoutingMapTree(b);
		if(tmp.containsNode(a))
		{
			list.add(x	);
			for(int i=0;i<b.numChildren();i++)
			{
				// RoutingMapTree tmpx = new RoutingMapTree(b.child(i));
				this.routelowestroutetoreceiver(list,a,b.child(i));
			}
		}
			
 	}

	public String performAction(String actionMessage) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
		if(actionMessage.contains("addExchange"))
		{	int i=0;
			for(i=12;i<actionMessage.length();i++)
			{
				if(actionMessage.charAt(i)==' ')
				{
					break;
				}
			}
			int a=Integer.parseInt(actionMessage.substring(12,i));
			int b=Integer.parseInt(actionMessage.substring(i+1));
			try{Exchange exB = new Exchange(b);
				if(a == 0)
				{	
					root.addNextlevel(exB);
					
				}
				else{
					Exchange exA = new Exchange(a);
			Exchange x = root.excontainingex(exA);
			x.child(x.exindex(exA)).addNextlevel(exB);
			System.out.print("");
			}
		}
			catch(Exception NodeNotPresent)
			{
				System.out.print(actionMessage+ ": Node " +a+ " is not present!" );
			}
		}
		else if(actionMessage.contains("switchOnMobile"))
		{
			int i=0;
			for(i=15;i<actionMessage.length();i++)
			{
				if(actionMessage.charAt(i)==' ')
				{
					break;
				}
			}
			System.out.print("");
			int a=Integer.parseInt(actionMessage.substring(15,i));
			int b=Integer.parseInt(actionMessage.substring(i+1));
			Exchange x= new Exchange(b);
			if(this.containsNode(x))
			{MobilePhone ap = new MobilePhone(a);
			this.switchOn(ap,x);}
			else
			{
				System.out.print(actionMessage+": Error - Exchange "+a+" is not present");
			}

		}
		else if(actionMessage.contains("switchOffMobile"))
		{
			int a=Integer.parseInt(actionMessage.substring(16));
            System.out.print("");
			MobilePhone ap = new MobilePhone(a);
			if(root.hasNumber(ap))
			this.switchOff(ap);
			else
			{
				System.out.print(actionMessage+": Error - No mobile phone with identifier " + a+ " found in the network");
			}
		}
		else if(actionMessage.contains("queryNthChild"))
		{
			int i=0;
			for(i=14;i<actionMessage.length();i++)
			{
				if(actionMessage.charAt(i)==' ')
				{
					break;
				}
			}
			System.out.print(actionMessage+": ");
			int a=Integer.parseInt(actionMessage.substring(14,i));
			int b=Integer.parseInt(actionMessage.substring(i+1));
			Exchange x = root.returnExchangebyID(a);
			try{
			System.out.print(x.child(b).getID());}
			catch(Exception IndexOutOfBoundsException)
			{
				System.out.print("Error Index out of Bound");
			}
		}
		else if(actionMessage.contains("queryMobilePhoneSet"))
		{	System.out.print(actionMessage+": ");
			int a=Integer.parseInt(actionMessage.substring(20)); 
			root.returnExchangebyID(a).residentSet().print();
		}

		//Assignment 2

		else if(actionMessage.contains("findPhone"))
		{
			// System.out.print("queryFindPhone "+a+": ");
			int a=Integer.parseInt(actionMessage.substring(10));
			System.out.print("queryFindPhone "+a+": ");
			MobilePhone ph = new MobilePhone(a);
			if(root.phones.IsMember(ph))
			{	
					System.out.print(this.findPhone(ph).getID());
			}
			else if(off.IsMember(ph))
				System.out.print("Error - MobilePhone with identifier " + a+" is currently switched off");
			else
			{
				System.out.print("Error - No mobile phone with identifier "+a+" found in the network");
			}
		}
		else if(actionMessage.contains("lowestRouter"))
		{
			int i=0;
			for(i=13;i<actionMessage.length();i++)
			{
				if(actionMessage.charAt(i)==' ')
				{
					break;
				}
			}
			
			int a=Integer.parseInt(actionMessage.substring(13,i));
			int b=Integer.parseInt(actionMessage.substring(i+1));
			Exchange aEx = new Exchange(a);
			Exchange bEx = new Exchange(b);
			System.out.print("queryLowestRouter "+a+" "+b+": ");
			if(this.containsNode(aEx))
			{
				if(this.containsNode(bEx))
				{
					Exchange ans = this.lowestRouter(aEx,bEx);
					System.out.print(ans.getID());
				}
				else
				{
					System.out.print("Error - Exchange "+b+" is not present!");
				}
			}
			else
				System.out.print("Error - Exchange "+a+" is not present!");
				
		}
		else if(actionMessage.contains("findCallPath"))
		{
			int i=0;
			for(i=13;i<actionMessage.length();i++)
			{
				if(actionMessage.charAt(i)==' ')
				{
					break;
				}
			}
			
			int a=Integer.parseInt(actionMessage.substring(13,i));
			int b=Integer.parseInt(actionMessage.substring(i+1));
			MobilePhone aPhone = new MobilePhone(a);
			MobilePhone bPhone = new MobilePhone(b);
			System.out.print("queryFindCallPath "+a+" "+b+": ");
			if(root.phones.IsMember(aPhone))
			{
				if(root.phones.IsMember(bPhone))
				{
					ExchangeLinkedList list = this.routeCall(aPhone,bPhone);
					list.print();
				}
				else
				{
					if(off.IsMember(bPhone))
					{
						System.out.print("Error - Mobile phone with identifier "+b+" is currently switched off");
					}
					else
					{
						System.out.print("Error - Mobile phone with identifier "+b+" doesn't exist");
					}
				}
			}
			else if(off.IsMember(aPhone))
			{
				System.out.print("Error - Mobile phone with identifier "+a+" is currently switched off");
			}
			else
					{
						System.out.print("Error - Mobile phone with identifier "+a+" doesn't exist");
					}
		}
		else if(actionMessage.contains("movePhone"))
		{
			int i=0;
			for(i=10;i<actionMessage.length();i++)
			{
				if(actionMessage.charAt(i)==' ')
				{
					break;
				}
			}
			int a=Integer.parseInt(actionMessage.substring(10,i));
			int b=Integer.parseInt(actionMessage.substring(i+1));
			MobilePhone ap = new MobilePhone(a);
			Exchange x= new Exchange(b);
			if(root.hasNumber(ap))
			{	
				if(this.containsNode(x))
				{this.switchOff(ap);
				this.switchOn(ap,x);}
			else
			{
				System.out.print(actionMessage+": Error - Exchange "+b + " doesn't exist");
			}}
			else if(off.IsMember(ap))
			{
				System.out.print(actionMessage+": "+"Error - Mobile phone with identifier "+a+" is already switched off");
			}
			else
			{
				System.out.print(actionMessage+": Error - Mobile phone with identifier " + a+ " doesn't exist");
			}
		}
		    System.out.flush();
    System.setOut(old);
    return baos.toString();
	}
	public static void main(String[] args) {
		RoutingMapTree test = new RoutingMapTree();
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		while(n>=0)
		{String actionMessage= s.nextLine();
			String newx =  test.performAction(actionMessage);
			if(newx != "")
			System.out.println(newx);
			n--;}
	}
}
