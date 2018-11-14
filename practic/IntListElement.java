public class IntListElement
{
	private int number;//Die Zahl, die in diesem Element gespeichert wird.
	private IntListElement next;//Das naechste Element
	
	//Ab hier sind Getter und Setter Methoden zu finden
	
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
	public IntListElement getNext()
	{
		return next;
	}
	public void setNext(IntListElement next)
	{
		this.next = next;
	}
}