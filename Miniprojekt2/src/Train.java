public class Train {

	// Kann für die Ausgabe verwendet werden
	private static final String LOCOMOTIVE = "<___|o|";

	private Waggon head;

	public int getSize() {
		int size = 0;
		Waggon train0 = head;
		while (train0 != null) {
			train0.getNext();
			size += 1;
		}
		
		return size;
	}

	public int getPassengerCount() {
		int passengers = 0;
		Waggon train0 = head;
		while (train0 != null ) {
			passengers += train0.getPassengers();
		}
		return passengers;
	}

	public int getCapacity() {
		int capacity = 0;
		Waggon train0 = head;
		while (train0 != null) {
			capacity += train0.getCapacity();
		}
		return capacity;
	}

	public void appendWaggon(Waggon waggon) {
		if (head == null) {
			head = waggon;
		}
		else {
			Waggon helper = head;
			while (helper.getNext() != null) {
				helper = helper.getNext();
			}
			helper.setNext(waggon);
		}
	}
	

	public void boardPassengers(int numberOfPassengers) {
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
		return null;
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
