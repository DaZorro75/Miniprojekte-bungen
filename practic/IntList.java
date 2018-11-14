public class IntList {

	// Das erste Element der Liste.
	private IntListElement first;

	/*
	 * BEGINN des zu bearbeitenden Codes
	 */
	public int getNode(int number) {
		if (first == null) {
			return 0;
		}
		else {
		IntListElement node = first;
		int pos = 0;
		while (number > node.getNumber() && node.getNext() != null) {
			node = node.getNext();
			pos++;
		}
		return pos;
		}
	}
	// Aufgabe 1
	public void append(int number) {
		IntListElement a = new IntListElement();
		a.setNumber(number);
		if (first == null) {
			first = a;
		}
		else if (number < 0) {
			a.setNext(first);
			first = a;
		}
		else {
			IntListElement b = first;
			while (b.getNext() != null || number > b.getNumber()) {
				if (number > b.getNumber() && b.getNext() == null) {
					b.setNext(a);
				}
				else if (b.getNext() != null && number < b.getNext().getNumber()) {
					IntListElement c = b.getNext();
					b.setNext(a);
					b = b.getNext();
					b.setNext(c);
				}
			b = b.getNext();
			}
		}
	}

	// Aufgabe 2
	public int size() {
		IntListElement a = first;
		int size = 1;
		if (first == null) {
		return 0;
		}
		else {
			while(a.getNext() != null) {
				a = a.getNext();
				size++;
			}
			return size;
		}
	}

	// Aufgabe 3
	public int get(int index) {
		IntListElement a = this.first;
		int num = 0;
		if (index > size() || index < size()) {
			return num;
		}
		else {
		for (int i = 0; i < index; i++) {
			a = a.getNext();
		}
		num = a.getNumber();
		return num;
		}
	}

	// Aufgabe 4
	public void reverse() {
		
	}

	/*
	 * ENDE des zu bearbeitenden Codes
	 */

	public static void main(String[] args) {
		IntList l = new IntList();
		l.append(10);
		l.append(20);
		l.append(42);
		l.append(-10);
		l.append(30);
		System.out.println("So sieht die Liste aus: " + l);
		System.out.println("Das Element an Index 0: " + l.get(0));
		System.out.println("Das Element an Index -10 (Gibt es nicht, sollte also 0 sein): " + l.get(-10));
		System.out.println("Das Element an Index 100 (Gibt es nicht, sollte also 0 sein): " + l.get(100));
		System.out.println("Das Element an Index 2: " + l.get(2));
		System.out.println("Die Liste hat " + l.size() + " Elemente.");
		l.reverse();
		System.out.println("Liste wurde gedreht");
		System.out.println("Nun sieht die Liste so aus: " + l);
	}

	public String toString() {
		String liste = "first --> ";
		IntListElement element = first;
		while (element != null) {
			liste = liste + element.getNumber();
			liste = liste + " --> ";
			element = element.getNext();
		}
		liste = liste + "null";
		return liste;
	}
}