// 데이터를 표현하기 위한 DTO 클래스 >> 별도로 실행을 위한 main 메서드 생성 x
package mvcTest;

// ** 객체 초기화
// 1) setter 사용하는 법
// 2) 생성자를 통해 초기화

public class StudentDTO extends JoDTO {

	// 1) private 멤버 변수 정의 : 자바에서 기본적으로 지정하는 int, double로~
	private int sno;
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String cname; // 필요시 사용할 조장이름 추가선언 
	// setCName() ..멤버 변수명의 2번째 알파벳도 대문자 사용 금지
	
	// ** 생성자 : 우클릭 > source > generate Construct Using field
	// defalut 생성자 : 모든 값을 초기화하는 생성자가 필요하다
	// defalut 생성자를 통해 기본 생성자를 만들어 놓아야한다.
	public StudentDTO() {}
	
	public StudentDTO(int sno, String name, int age, int jno, String info, double point, String cname) {
		this.sno = sno;
		this.name = name;
		this.age = age;
		this.jno = jno;
		this.info = info;
		this.point = point;
		this.cname = cname;
	}
	
	public StudentDTO(String name, int age, int jno, String info) {
		this.name = name;
		this.age = age;
		this.jno = jno;
		this.info = info;
	}

	public StudentDTO(String name, int jno, int sno) {
		this.name = name;
		this.jno = jno;
		this.sno = sno;
	}
	
	public StudentDTO(int sno, String name, int age, int jno) {
		this.sno=sno;
		this.name=name;
		this.age=age;
		this.jno=jno;
	}

	// 2) getter / setter : 마우스 우클릭 후 source > generate getter setter 자동완성
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	} // this : 현재 객체의 인스턴스 의미

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	// 3) toString : 우클릭 source > generate toString 
	// 최상위 클래스인 Object 클래스의 상속을 받은 상태이기 때문에 오버라이딩이 진행되는 것
	// Object에 정의된 toString() 메서드를 활용해서
	// 객체의 값을 편리하게 확인하기 위해 사용
	@Override
	public String toString() {
		return "StudentDTO [sno=" + sno + ", name=" + name + ", age=" + age + ", jno=" + jno + ", info=" + info
				+ ", point=" + point + ", cname=" + cname + ", getJname()=" + getJname() + ", getCaptain()="
				+ getCaptain() + ", getProject()=" + getProject() + ", getSlogan()=" + getSlogan() + "]";
	}
}
