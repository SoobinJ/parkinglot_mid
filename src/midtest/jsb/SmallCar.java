package midtest.jsb;

public class SmallCar extends Car {

	private int engine;

	public SmallCar(String name, String number, int engine) {
		super(name, number);
		this.engine=engine;
		
	}

	@Override
	public String toString() {
		String str=super.toString();
		str+="\t배기량 ; "+this.engine;
		return str;
	}	
	
}
