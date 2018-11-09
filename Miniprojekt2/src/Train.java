public class Train {

	// Kann für die Ausgabe verwendet werden
	private static final String LOCOMOTIVE = "<___|o|";

	private Waggon head;

	public int getSize() {
		if (head == null) {
			return 0;
		}
		else {
			int size = 1;
			Waggon temp = head;
			while (temp.getNext() != null) {
				size += 1;
				temp = temp.getNext();
			}
			return size;
		}
	}

	public int getPassengerCount() {
		if (head == null) {
			return 0;
		}
		else {
			int passengers = 0;
			Waggon temp = head;
			while (temp != null) {
				passengers += temp.getPassengers();
				temp = temp.getNext();
			}
			return passengers;
		}
	}

	public int getCapacity() {
		if (head == null) {
			return 0;
		}
		else {
			int capacity = 0;
			Waggon temp = head;
			while (temp != null) {
				capacity += temp.getCapacity();
				temp = temp.getNext();
			}
		return capacity;
		}
	}

	public void appendWaggon(Waggon waggon) {
		if (head == null) {
			head = waggon;
		}
		else {
			Waggon temp = head;
			while(temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(waggon);
		}
	}

	public void boardPassengers(int numberOfPassengers) {
		Waggon temp = head;
		int waiting = numberOfPassengers;
		while (temp != null) {
			int capacity = temp.getCapacity();
			int boarded = temp.getPassengers();
			int free = capacity - boarded;
			
			if(free == 0) {
				temp = temp.getNext();
				continue;
			}
			else if (free >= waiting) {
					temp.setPassengers(boarded + waiting);
				break;
				}
				else  if (free < waiting) {
					int remaining = waiting - free;
					temp.setPassengers(capacity);
					temp = temp.getNext();
					waiting = remaining;
			}
		}
	}

	public Train uncoupleWaggons(int index) {
		return new Train();
	}

	public void insertWaggon(Waggon waggon, int index) {
	}

	public void turnOver() {
	}

	public void removeWaggon(Waggon waggon) {
	}

	public Waggon getWaggonAt(int index) {
		Waggon temp = head;
		if(index < 0) {
			return null;
		}
		else if (index > getSize()) {
			return null;
		}
		else {
		for (int i = 0; i < index; i++) {
			temp = temp.getNext();
		}
		return temp;
		}
	}

	@Override
	public String toString() {
		if (getSize() == 0) {
			return LOCOMOTIVE;
		}

		StringBuilder str = new StringBuilder(LOCOMOTIVE);

		Waggon pointer = head;
		while (pointer != null) {
			str.append(" <---> ");
			str.append(pointer.getName());
			pointer = pointer.getNext();
		}

		return str.toString();
	}

}
