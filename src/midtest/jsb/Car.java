package midtest.jsb;

public class Car {

	protected String name;
	protected String number;
	

	public Car(String name, String number) {
		this.name = name;
		this.number = number;
	}
	
	@Override
	public String toString() {
		String str="차량 소유주 : "+this.name+","+" 차량번호 : "+this.number;
		return str;
	}
	
} 
