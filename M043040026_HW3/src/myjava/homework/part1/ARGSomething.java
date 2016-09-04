package myjava.homework.part1;

public class ARGSomething {

	public static void main(String[] args) {
		// You cannot modify anything in the main function!!!!
		ARGSomething t = new ARGSomething();
		System.out.println(t.avg(10, 20));
		System.out.println(t.avg(20, 60, 120));
		System.out.println(t.avg(10, 20, 30, 40));

		System.out.println(t.avg(0.1, 0.2));
		System.out.println(t.avg(20, 60.0, 120));
		System.out.println(t.avg(10, 20, new Integer(30), 40.0));

		System.out.println(avg("Apple"));
		System.out.println(avg("Apple", "Cat"));
		System.out.println(avg("Apple", "Cat", new String("Bee")));

		System.out.println(avg(new Apple(10, 59.5)));
		System.out.println(avg(new Apple(10, 59.5), new Apple("Washington", 30, 80.3)));
		Apple green = new Apple("Granny Smith", 30, 88);
		System.out.println(avg(new Apple(10, 59.5), new Apple("Washington", 30, 80.3), green));
	}

	public int avg(int... a1) {
		int average = 0;
		for (int i = 0; i < a1.length; i++) {
			average = average + a1[i];
		}
		average = average / a1.length;
		return average;
	}

	public double avg(double... d1) {
		double average = 0;
		for (int i = 0; i < d1.length; i++) {
			average = average + d1[i];
		}
		average = average / d1.length;
		return average;
	}

	public static String avg(String... strings) {
		double average = 0;
		
		for (int i = 0; i < strings.length; i++) {
			 average =  average+ strings[i].length();
		}
		average = average / strings.length;
		if (strings.length == 1) {
			return "The number of this word is " + (int) average;
		} else {
			return "The avg number of these words is " + average;
		}

	}

	public static String avg(Apple... apples) {
		double average;
		int price_all = 0;
		double weight_all = 0;
		for (Apple i:apples) {
			price_all = price_all + i.getPrice();
			weight_all = weight_all + i.getWeight();
		}
		average = weight_all / (double)price_all;
		return "The price per gram is " + average;
	}

}
