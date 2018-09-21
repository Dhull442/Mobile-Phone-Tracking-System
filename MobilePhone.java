public class MobilePhone{
	int phnumber;
	Boolean stateison;
	Exchange home;
	public boolean isEqual( MobilePhone b)
	{
		return (phnumber == b.phnumber);
	}
	public MobilePhone(int number){
		phnumber=number;
		stateison=true;
	}
public int number()
{
	return phnumber;
}
public Boolean status()
{
	//returns 1 when ON;
	return stateison;
}
public void switchOn()
{
	stateison=true;
}
public void switchOff()
{
	stateison=false;
}
public Exchange location()
{
// : returns the base station with which
// the phone is registered if the phone is switched on and an excep-
// tion  if  the  phone  is  off.   The  class
// Exchange
// will  be  described
// next.
	if(this.status())
	return home;
	throw new RuntimeException("Phone is OFF!");
	
}
}	