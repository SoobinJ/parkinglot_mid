# parkinglot_mid
### 주차관리시스템/자바프로그래밍 중간고사 기출문제
-------------------------
- Car, CarForDisabledPerson, SmallCar, ParkingLot 클래스를 만들고 객체를 생성함
- ParkingLot 클래스 객체 생성시 주어진 파일을 읽어 들여, 주차 구역에 관한 배열을 생성하여 저장함
    - 주차 크기가 다른 파일이 들어와도 실행될 수 있도록 구현함
- 장애인전용구역, 경차전용 구역, 일반 주차구역을 구분하여 주차장을 보여줄 수 있게 함
- 주차하기
    - 차량이 입고될 때마다 주차된 곳과 빈공간을 구분하여 보여줌
    - 주차시 경차, 일반, 장애 차량이 각각 맞는 구역에만 주차할 수 있게함
- 출차하기
    - 차량번호를 입력하면 해당 차량이 출차됨
    - 차량이 출차되면 주차공간에 해당 자리를 빈공간으로 나타나게 함
    - 주차비는 각 차량에맞는 활인율이 적용됨
