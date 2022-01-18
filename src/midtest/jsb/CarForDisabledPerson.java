package midtest.jsb;

public class CarForDisabledPerson extends Car {

	private String disnumber;
	private String degree;
	
	public CarForDisabledPerson(String name, String number, String disnumber, String degree) {
		super(name, number);
		this.disnumber=disnumber;
		this.degree=degree;
	}

	

	@Override
	public String toString() {
		String str=super.toString();
		str+="\t장애인등록번호 : "+this.disnumber+","+" 장애정도 : "+this.degree;
		return str;
	}



	public String getDegree() {
		return degree;
	}



	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	

}
