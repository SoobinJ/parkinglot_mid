package midtest.jsb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.IOException;

public class ParkingLot{
	
	public static Scanner scan=new Scanner(System.in);

	private static int ROW = 0;
	private static int COL=0;
	private int count=0;
	private int num;
	private double price;
	private String filename;
	private int [][] map;
	private String[][] parking;
	private double finalprice;

	public ParkingLot(String filename, double price) {
		this.price = price;
		this.filename = filename;
		this.map=readMap(filename);
		this.parking=change();
		//this.finalprice=price;
	}

	public String[][] change() {

		StringTokenizer st=new StringTokenizer(read(filename),"\s");
		String token=st.nextToken();
		String token2=st.nextToken();
		int a=Integer.parseInt(token);
		int b=Integer.parseInt(token2);
		parking=new String[a][b];
	
		return parking;
	}
	
	public void Parking(Car c) {
		do{
			count=0;
			showMap(map);
			System.out.print("주차할 자리를 선택하세요(예, 1 1) ==> ");
			int location1=scan.nextInt();
			int location2=scan.nextInt();
			search(c);
			//System.out.println(map[location1-1][location2-1]);
			switch(map[location1-1][location2-1]) {
			case 2: {
				test(c,location1, location2);
			}break;
			case 0:{
				if(num==0) {
					test(c,location1, location2);
				}else {
					System.out.println("장애인 전용 주차 구역입니다. 다른 자리를 선택해 주세요.");
					break;
				}
			}break;
			case 1:{
				if(num==1) {
					test(c,location1, location2);
				}else {
					System.out.println("경차 전용 주차 구역입니다. 다른 자리를 선택해 주세요.");
					break;
				}

			}break;
			default:
				System.out.println("이미 주차되어있습니다. 다른 자리를 선택해 주세요.");
			}
		}while(count!=1);
	}

	public int test(Car c,int a, int b) {
		System.out.println("주차가 완료되었습니다.");
		switch(num) {
		case 0: {
			CarForDisabledPerson car=(CarForDisabledPerson)c;
			if(car.getDegree().equals("중증")) {
				map[a-1][b-1]=6;
				break;
			}
			else if(car.getDegree().equals("경증")) {
				map[a-1][b-1]=7;
				break;
			}
				
			break;
		}
		case 1: map[a-1][b-1]=4;break;
		case 2: map[a-1][b-1]=5;break;
		}
		showMap(map);
		parking[a-1][b-1]=c.number;
		count=1;
		return count;
	}
	
	public int search(Car c) {
		if(c instanceof Car)
			this.num=2;
		if(c instanceof SmallCar)
			this.num=1;
		if(c instanceof CarForDisabledPerson)
			this.num=0;
		return num;
		
	}
	public void PullOut() {
		int t=0;
		do {
			System.out.println("출차할 차량번호를 입력하세요 : ");
			String l=scan.next();
			int d=0;
			for(int i=0;i<parking.length;i++) {
				for(int j=0;j<parking[i].length;j++) {
					if(parking[i][j]!=null) {
						if(parking[i][j].equals(l)) {
							System.out.println(l+"차량이 출차되었습니다.");
							System.out.println();
							parking[i][j]=null;
							switch(map[i][j]) {
							case 6: {
								map[i][j]=0;
								System.out.println("주차가격은 "+this.price*0.3+"입니다. 안녕히 가세요.");
								finalprice+=this.price*0.3;
								break;
							}
							case 7: {
								map[i][j]=0;
								System.out.println("주차가격은 "+this.price*0.4+"입니다. 안녕히 가세요.");
								finalprice+=this.price*0.4;
								break;
							}
							case 4: {
								map[i][j]=1;
								System.out.println("주차가격은 "+this.price*0.5+"입니다. 안녕히 가세요.");
								finalprice+=this.price*0.5;
								break;
							}
							case 5: {
								map[i][j]=2;
								System.out.println("주차가격은 "+this.price+"입니다. 안녕히 가세요.");
								finalprice+=this.price;
								break;
							}
							}
							showMap(map);
							t=1;
							break;
						}
						else {
							d++;
						}

					}
					else
						d++;
				}
			}
			if(d==ROW*COL) {
				System.out.println("차량 번호를 확인해 주세요.");
				break;
			}
		}while(t!=1);
	}
	public void showMap(int[][] map) {
		System.out.println("===========================================");
		System.out.println("\t1\t2\t3\t4\t5");
		int n=1;//앞에 숫자 1234 만들어주기 위함 
		for(int[] row: map) {
			System.out.print(n);
			n++;
			for(int col: row) {
				switch(col) {
				case 0:System.out.print("\t☆");break;
				case 1:System.out.print("\t○");break;
				case 2:System.out.print("\t◇");break;
				case 6:case 7:System.out.print("\t★");break;
				case 4:System.out.print("\t●");break;
				case 5:System.out.print("\t◆");break;
				}
			}
			System.out.println();
		}
		System.out.println("===========================================");
	}
	public String read(String filename){
	
		String line="";
		try {
			File file =new File(filename);
			FileReader filereader=new FileReader(file);
			BufferedReader bufReader=new BufferedReader(filereader);
			
			line = bufReader.readLine();
			bufReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return line;
	}
	public int[][] readMap(String filename){
		int[][] map=null;
		File file=new File(filename);
		
		
		try {
			Scanner fileScan=new Scanner(file);
			ROW=fileScan.nextInt();
			COL =fileScan.nextInt();
			map=new int[ROW][COL];
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map.length;j++) {
					map[i][j]=fileScan.nextInt();
				}
			}
			fileScan.close();
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public String toString() {
		String str="기본 주차료 : "+this.price+"원\n";
		str+="전체 수입 : "+this.finalprice+"원";
		return str;
	}
	
}
