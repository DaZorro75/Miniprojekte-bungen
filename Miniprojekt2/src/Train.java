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
		Train newTrain = new Train();
		if (index < 0 || index > getSize()) {
			return null;
		}
			else {
				Waggon desire = getWaggonAt(index - 1);
				Waggon lust = desire.getNext();
				desire.setNext(null);
				newTrain.head = lust;
				
		}
		return newTrain;
	}

	public void insertWaggon(Waggon waggon, int index) {
		if (index == 0) {
			Waggon wag0 = head;
			head = waggon;
			head.setNext(wag0);
		}
		else if (index >= getSize()) {
			while (head.getNext() != null) {
				head = head.getNext();
				}
			head.setNext(waggon);
		}
		else {
		Waggon wag1 = getWaggonAt(index - 1);
		Waggon wag2 = wag1.getNext();
		wag1.setNext(waggon);
		waggon.setNext(wag2);
		}
	}

	public void turnOver() {
		Train newTrain = new Train();
		Waggon old = getWaggonAt(getSize() - 1);
		while (getSize() > 0) {
			old = getWaggonAt(getSize() - 1);
			newTrain.appendWaggon(old);
			removeWaggon(old);
		}
		head = newTrain.getWaggonAt(0);
	}

	public void removeWaggon(Waggon waggon) {
		if (waggon == null) {}
		else {
			if(head == waggon) {
				head = head.getNext();
			}
			else {
				Waggon wag0 = head;
				while (wag0.getNext() != waggon && wag0.getNext() != null) {
					wag0 = wag0.getNext();
				}
				
				if (wag0 != null) {
					wag0.setNext(wag0.getNext().getNext());
				}
			}
		}
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
