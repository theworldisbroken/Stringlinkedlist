package AuD;

public class StringLinkedList implements StringList {
	private ListElem head;
	private ListElem tail;

	/**
	 * Ein Konstruktor dient zur Erzeugung eine neue Linkedlist mit
	 * DummyElementen(Head und tail)
	 */
	public StringLinkedList() {
		ListElem headElem = new ListElem();
		head = headElem;
		ListElem tailElem = new ListElem();
		tail = tailElem;

		headElem.prev = null;
		headElem.next = tail;
		tailElem.prev = head;
		tailElem.next = null;
	}

	/**
	 * Eine innere Klasse zur Erzeugung der einzelnen Listenelemente einer
	 * Linkedlist mit value, prev und next
	 */
	class ListElem {
		private String value;
		private ListElem prev;
		private ListElem next;

		// Konstruktor zur Erzeugung einzelne Elemente ohne bestimmtes Value
		public ListElem() {
		}

		// Konstruktor zur Erzeugung einzelne Elemente mit bestimmtem Value
		public ListElem(String value) {
			this.value = value;
		}
	}

	@Override
	public void add(String value) {
		// geh die Liste durch falls .next nicht leer ist(!= null)
		ListElem tmpCurrent = head;
		ListElem tmpElem = new ListElem(value);
		while (tmpCurrent.next != null) {
			if (tmpCurrent.next == tail) {
				break;
			}
			tmpCurrent = tmpCurrent.next;
		}
		// Addiere ein neues Element am Ende der List
		tmpElem.prev = tmpCurrent;
		tmpElem.next = tail;
		tail.prev = tmpElem;
		tmpCurrent.next = tmpElem;
	}

	@Override
	public void add(int index, String value) {
		// Exception bei ungüligem Index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		// Geh die Liste durch bis zum letzten Index vor dem gewünschtem Index
		ListElem tmpCurrent = head;
		ListElem tmpElem = new ListElem(value);
		for (int i = 0; i < index; i++) {
			tmpCurrent = tmpCurrent.next;
		}
		// Füge das neue Element bei dem gewünschten Index hinzu(mit dem bestimmten
		// value)
		tmpElem.prev = tmpCurrent;
		tmpElem.next = tmpCurrent.next;
		tmpCurrent.next.prev = tmpElem;
		tmpCurrent.next = tmpElem;
	}

	@Override
	public boolean contains(String value) {
		// Während die Liste Elemente enthält, geh sie durch und vergleiche beiden
		// values
		ListElem tmpCurrent = head.next;
		while (tmpCurrent.next != null) {
			if (tmpCurrent.value != null && tmpCurrent.value.equals(value)) {
				return true;
			}
			if (tmpCurrent.next == tail) {
				break;
			}
			tmpCurrent = tmpCurrent.next;
		}
		return false;
	}

	@Override
	public int indexOf(String value) {
		// Während die Liste Elemente enthält, geh sie durch, vergleiche beiden
		// values und gib das Index zurück falls sie gleich sind
		int i = 0;
		ListElem tmpCurrent = head.next;
		while (tmpCurrent.next != null) {
			if (tmpCurrent.value != null && tmpCurrent.value.equals(value)) {
				return i;
			}
			if (tmpCurrent.next == tail) {
				break;
			}
			i++;
			tmpCurrent = tmpCurrent.next;
		}
		return -1;
	}

	@Override
	public boolean remove(String value) {
		// Während die Liste Elemente enthält, geh sie durch, vergleiche beiden
		// values und wenn sie gleich sind, "lösche das momentane Index" und return
		// true;
		ListElem tmpCurrent = head;
		while (tmpCurrent.next != null) {
			if (tmpCurrent.value != null && tmpCurrent.value.equals(value)) {
				// falls das letzte Element gelöscht werden muss, mach folgendes
				if (tmpCurrent.next == tail) {
					tmpCurrent.prev.next = tail;
					tail.prev = tmpCurrent.prev;
					return true;
					// falls das erste Element gelöscht werden muss, mach folgendes
				} else if (tmpCurrent.prev == head) {
					tmpCurrent.next.prev = head;
					head.next = tmpCurrent.next;
					return true;
					// verbinde letztes Element mit dem nächsten
				} else {
					tmpCurrent.prev.next = tmpCurrent.next;
					tmpCurrent.next.prev = tmpCurrent.prev;
					return true;
				}
			}
			tmpCurrent = tmpCurrent.next;
		}
		return false;
	}

	@Override
	public String remove(int index) {
		// Exception bei ungüligem Index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		// Während die Liste Elemente enthält, geh sie durch, vergleiche beiden
		// values und wenn sie gleich sind, "lösche das momentane Index" und return
		// den Wert(String) von dem gelöschten Index;
		ListElem tmpCurrent = head.next;
		if (head.next != null) {
			for (int i = 0; i < index; i++) {
				tmpCurrent = tmpCurrent.next;
			}
			// verbinde letztes Element mit dem nächsten
			tmpCurrent.prev.next = tmpCurrent.next;
			tmpCurrent.next.prev = tmpCurrent.prev;
			return tmpCurrent.value;
		}
		return null;
	}

	@Override
	public String get(int index) {
		// Exception bei ungüligem Index
		ListElem tmpCurrent = head.next;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		// Geh die Liste durch bis zum genannten Index und gib das Value davon zurück
		if (tmpCurrent != null) {
			for (int i = 0; i < index && tmpCurrent.next != null; i++) {
				if (tmpCurrent.next == tail) {
					break;
				}
				tmpCurrent = tmpCurrent.next;
			}
			return tmpCurrent.value;
		}
		return null;
	}

	@Override
	public int size() {
		// Falls die Liste ist leer, return 0
		ListElem tmpCurrent = head.next;
		if (tmpCurrent == tail) {
			return 0;
		}
		// Geh die Liste durch, zähle jedes Element und gib den Zähler am Ende zurück
		int i = 1;
		while (tmpCurrent.next != null) {
			if (tmpCurrent.next == tail) {
				break;
			}
			tmpCurrent = tmpCurrent.next;
			i++;
		}
		return i;
	}

	/**
	 * Zeigt alle Elemente in der Liste
	 * @return -
	 * @throws IndexOutOfBoundsException wenn die Liste noch leer ist.
	 */
	public void print() {
		ListElem tmpCurrent = head.next;
		if (tmpCurrent == tail) {
			throw new IndexOutOfBoundsException();
		}
		while (tmpCurrent != null) {
			System.out.print(tmpCurrent.value + ",");
			if (tmpCurrent.next == tail) {
				break;
			}
			tmpCurrent = tmpCurrent.next;
		}
		System.out.println();
	}

	/**
	 * Eine Test-Methode mit allen möglichen Tests
	 * @return -
	 */
	public static void test() {
		StringLinkedList list = new StringLinkedList();
		list.add("Element 0");
		list.add("Element 1");
		list.add("Element 2");
		list.add(null);
		list.add("Element 4");
		list.add("Element 5");
		list.add("Element 6");
		System.out.println("List 1 test mit get(0): " + list.get(0));
		System.out.println("List 1 test mit get(2): " + list.get(2));
		System.out.println("List 1 test mit get(3): " + list.get(3));
		System.out.println("List 1 test mit get(6): " + list.get(6));
		System.out.println("List 1 test mit size(): " + list.size());
		System.out.println("List 1 test mit contains(Element 4): " + list.contains("Element 4"));
		System.out.println("List 1 test mit contains(554): " + list.contains("554"));
		System.out.println("List 1 test mit indexOf(Element 5): " + list.indexOf("Element 5"));
		System.out.println("List 1 test mit indexOf(33): " + list.indexOf("33"));

		System.out.println();
		list.add(1, "Neues Element");
		System.out.println("List 1 test mit get(1) nach Addierung von neuem Element beim Index(1): " + list.get(1));

		System.out.println();
		System.out.print("List 1 test mit print(): ");
		list.print();
		System.out.println();

		list.remove(0);
		System.out.println("List 1 test mit size() nach Löschung von index(0): " + list.size());
		System.out.println("List 1 test mit get(0): " + list.get(0));
		System.out.println("List 1 test mit get(letztes Element): " + list.get(list.size() - 1));
		System.out.println();

		System.out.println("List 1 test mit remove(Element 4): " + list.remove("Element 4"));
		System.out.println("List 1 test mit size() nach Löschung von String(Element 4) : " + list.size());
		System.out.println("List 1 test mit get(4): " + list.get(4));
		
		// Eine neue leere Liste
		StringLinkedList list1 = new StringLinkedList();
		try {
		System.out.println("List 2 eine leere Liste, test mit get(0): ");
		System.out.println(list1.get(0));
		} catch (IndexOutOfBoundsException e) {
		}

		System.out.println("-----------------------------------");
		System.out.println("List 2 eine leere Liste, test mit size(): " + list1.size());
		System.out.println("List 2 eine leere Liste, test mit indexOf(Hehe): " + list1.indexOf("Hehe"));
		System.out.println("List 2 eine leere Liste, test mit contrains(love): " + list1.contains("love"));
		try {
			System.out.println("List 2 eine leere Liste, test mit remove(0): ");
			System.out.println(list1.remove(0));
			} catch (IndexOutOfBoundsException e) {
			}
		
		System.out.println("List 2 eine leere Liste, test mit remove(hallo): " + list1.remove("hallo"));
		System.out.println();
		try {
			System.out.print("List 2 test mit print(): ");
			list1.print();
			} catch (IndexOutOfBoundsException e) {
			}
		
	}

	public static void main(String[] args) {
		test();
	}
}