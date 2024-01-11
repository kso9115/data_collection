package jdbc02;

import java.util.List;

//** Controller
//=> 요청 : 요청분석 -> 담당 Service -> Service 는 DAO에게 요청을 보냄(DAO에게 바로 Controller로 요청하는게 X)
//=> 결과 : DAO -> Service -> Controller
//=> View : Controller -> View 담당 (Java:Console // Web:JSP, Html.., React) 

public class StudentController {
	
	// ** StudentSerive 전역변수 선언
	StudentService service = new StudentService();
	
	// ** View 의 역할을 하는 메서드 정의
	// 1.selectList - printList
	
	// 출력받을 내용을 매개변수로 전달 받아야함 > 최종적으로 list를 전달받아 한다
	public void printList(List<StudentDTO> list) {
		System.out.println("** Student List **");
		// 출력 자료의 존재를 확인
		if(list!=null) {
			// list 데이터 확인하여 출력할 수 있는 방법 : forEach문
			// List 출력
			for (StudentDTO s:list) {
				// StudentDTO toString 메서드 생성해놓았으므로
				// 인스턴스 이름만 출력 시 메서드 실행되어 별도로 .getSno...등등 사용하지 않아도 됨
				System.out.println(s);
			}
			
		} else {
			System.out.println("** printList 결과가 1건도 없음 **");
		}
	} // printList
	
	
	//----------------------------------------------------------------//
	// printList : printOne
	public void printDetail(StudentDTO sno) {
		System.out.println("** printOne **");
		
		if(sno!=null) {
			System.out.println(sno);
		} else {
			System.out.println("** printOne 결과가 1건도 없음 **");
		}
	}
	

    
	//----------------------------------------------------------------//
	// printList : printInsert
	public void printList(int n) {
		System.out.println("** printInsert **");
		
		if(n>0) {
			System.out.println("데이터 입력 성공");
		} else {
			System.out.println("데이터 입력 실패");
		}
	}
	
	//----------------------------------------------------------------//
	// printUpdate : printUpdate
	public void printUpdate(int n) {
		System.out.println("** printUpdate **");
		if(n>0) {
			System.out.println("업데이트 성공");
		} else {
			System.out.println("업데이트 성공");
		}
	}
	
	//----------------------------------------------------------------//
	// printList : printDelete
	public void printDelete(int n) {
		System.out.println("** printDelete **");
		if(n>0) {
			System.out.println("데이터 삭제 성공");
		} else {
			System.out.println("데이터 삭제 성공");
		}
	}
	
	public static void main(String[] args) {
		
		
		// 1.selectList(printList로 변경하여 진행)
		// 1) 요청에 해당하는 Service 메서드 호출
		// 2) 처리 결과를 View에 전달해서 출력하도록함
		
		// 메서드 호출을 위한 인스턴스 생성
		StudentController sc = new StudentController();
		
//		sc.printList(sc.service.selectList());
//		sc.printDetail(sc.service.selectOne(2));
//		sc.printList(sc.service.insert(new StudentDTO("김삐뽀", 21, 7,"테스트삐삐")));
//		sc.printUpdate(sc.service.update(new StudentDTO("김삐삐",7,23)));
//		sc.printDelete(sc.service.delete(21));
		
//-----------------------------------------------------
		// selectOne2
//		sc.print sc.service.selectOne2(1);
		// ** 참조자료형 Test : 객체 생성 후 
//				StudentDTO dto2 = new StudentDTO();
//			    dto2.setSno(18);
//			    sc.service.selectOne2(dto2);
//			    sc.printDetail(dto2);
		
//-------------------------------------------------------
		//** Join Test
		sc.service.transactionTest();
		
	} // main
} // class
