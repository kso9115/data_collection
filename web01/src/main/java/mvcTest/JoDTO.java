package mvcTest;

// 상속을 받아 확장
public class JoDTO {
	// 1) 멤버 변수 정의 : 오버라이딩?
	private int jno;
	private String jname;
	private int captain;
	private String project;
	private String slogan;

	public JoDTO() {
	}

	// 1) 생성자 정의 : 모든값을 초기화하는 생성자
	public JoDTO(int jno, String jname, int captain, String project, String slogan) {
		this.jno = jno;
		this.jname = jname;
		this.captain = captain;
		this.project = project;
		this.slogan = slogan;
	}

	// 2) getter & setter

	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public int getCaptain() {
		return captain;
	}

	public void setCaptain(int captain) {
		this.captain = captain;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	// 3) toString
	// toString 문자열 자동완성 시 inherited method 선택 시 자동으로 상속된 요소까지 가져옴
	@Override
	public String toString() {
		return "JoDTO [jno=" + jno + ", jname=" + jname + ", captain=" + captain + ", project=" + project + ", slogan="
				+ slogan + "]";
	}
}


