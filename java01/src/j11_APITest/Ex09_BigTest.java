package j11_APITest;

import java.math.BigDecimal;
import java.math.BigInteger;

//** 기본 자료형
//=> 정수 : byte(1), short(2), int(4), long(8)
//=> 실수 : float(4), double(8)
//=> 기타 : boolean(1), char(2)

public class Ex09_BigTest {

	public static void main(String[] args) {
		 
		System.out.println("기본자료형의 최대정수 => "+ Long.MAX_VALUE); // 19자리
		System.out.println("기본자료형의 최소정수 => "+ Long.MIN_VALUE);
		long lnum = Long.MAX_VALUE ;
		
		// 컴파일, 런타인 오류는 발생하지 않지만 결과는 틀림 => 논리적(Logical) 오류
		// => Overflow 발생
		lnum += 1; 
		System.out.println("lnum => "+ lnum);
		
		BigInteger b1 = new BigInteger("12345678900000000000");
		BigInteger b2 = new BigInteger("33333333300000000000");
		BigInteger r = b1.add(b2);
		System.out.println("result 1 => "+ r);
		r = b1.multiply(b2) ;
		System.out.println("result 2 => "+ r);
		
		// ** Decimal
		// => 1.십진법의,  2.소수
		BigDecimal d1 = new BigDecimal("1.23456") ;
		BigDecimal d2 = new BigDecimal("0.0005") ;
		System.out.println("BigDecimal add => "+ d1.add(d2));
		System.out.println("BigDecimal multiply => "+ d1.multiply(d2));

	} //main

} //class
