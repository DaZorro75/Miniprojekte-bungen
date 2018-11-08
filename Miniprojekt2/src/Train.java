public class Train {

	// Kann für die Ausgabe verwendet werden
	private static final String LOCOMOTIVE = "<___|o|";

	private Waggon head;

	public int getSize() {
	 if (head == null) {
		 return 0;
	 }
	 else {
		 Waggon wag = head;
		 int size = 1;
		 while (wag.getNext() != null) {
			 size += 1;
			 wag = wag.getNext();
		 }
		 return size;
	 }
	}

	public int getPassengerCount() {
		if (head == null) {
			return 0;
		}
		else {
			Waggon wag1 = head;
			int pass = 0;
			while (wag1.getNext() != null) {
				pass += wag1.getPassengers();
				wag1 = wag1.getNext();
			}
			return pass;
		}
	}

	public int getCapacity() {
		if (head == null) {
			return 0;
		}
		else {
			Waggon wag2 = head;
			int cap = 0;
			while (wag2 != null) {
				cap += wag2.getCapacity();
				wag2 = wag2.getNext();
			}
			return cap;
		}
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
		Waggon train = head;
		int wartend = numberOfPassengers;
		while (train != null) {
			int frei = train.getCapacity() - train.getPassengers();
			int anBoard = train.getPassengers();
			
			if (frei == 0) {
				train = train.getNext();
			}
			else {
				if (frei >= wartend) {
					train.setPassengers(wartend);
				}
				else {
					if (frei < wartend) {
						int temp = wartend - frei;
						train.setPassengers(train.getCapacity());
						wartend = temp;
					}
					train = train.getNext();
				}
			}
			train = train.getNext();
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
		if (index == 0 || index > getSize()) {
			return null;
		}
		else {
		Waggon query;
		for (int i = 0; i < index; i++) {
			head = head.getNext();
		}
		query = head;
		return query;
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
