package j11_APITest;

/* ** 배정현
 
class Person {

   private String idNo;
   private String name;
   private int age;
   private char gender;

   public Person() {
   }

   public Person(String idNo, String name) {
      this.idNo = idNo;
      this.name = name;
   }

   public void sort() {

      LocalDate date = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");

      int year = Integer.parseInt(date.format(formatter));

      String[] birth = getIdNo().split("-"); // 주민등록번호 분리
      String birthYear = "";
      if (birth[1].charAt(0) == '1' || birth[1].charAt(0) == '2') {
         birthYear = "19" + birth[0].substring(0, 2); // 87
      } else {
         birthYear = "20" + birth[0].substring(0, 2);
      }
      int birthdayYear = Integer.parseInt(birthYear);
      setAge(year - birthdayYear);

      char sortingGender = birth[1].charAt(0);

      if (sortingGender == '1' || sortingGender == '3')
         setGender('남');
      else
         setGender('여');
   }// sort()

   public StringBuffer invisibility() {
      StringBuffer sb = new StringBuffer();
      String result = getIdNo();
      String temp[] = new String[result.length()];
      for (int i = 0; i < result.length(); i++) {
         if (i < 7)
            temp[i] = String.valueOf(result.charAt(i));
         else
            temp[i] = "*";
      }
      for (int i = 0; i < temp.length; i++) {
         sb.append(temp[i]);
      }
      return sb;
   }

   public void infoPrint() {
      sort();
      System.out.printf("**info\n이름 : %s\n주민번호 : %s\n나이 : %d세\n성별 : %c\n\n", getName(), invisibility(), getAge(),
            getGender());
   }

   public String getIdNo() {
      return idNo;
   }

   public void setIdNo(String idNo) {
      this.idNo = idNo;
   }

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

   public char getGender() {
      return gender;
   }

   public void setGender(char gender) {
      this.gender = gender;
   }

}

public class Ex07_PersonTest {

   public static void main(String[] args) {
      Person naruto = new Person("870816-1012345", "나루토");
      naruto.infoPrint();
      Person sasuke = new Person("010102-3012345", "사스케");
      sasuke.infoPrint();
      Person hinata = new Person("220620-4012345", "히나타");
      hinata.infoPrint();
   }

} 
 
 */

/*
** 최승호 **
* 
class Person {
   private String idNo;
   private String name;
   private int age;
   private char gender;

   Person() {
   }

   Person(String idNo, String name) {
      this.idNo = idNo;
      this.name = name;
      setAge(idNo);
      setGender(idNo);
   }

   public void setIdNo(String idNo) {
      this.idNo = idNo;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setAge(String idNo) {
      String[] idNo1 = idNo.split("-");

      if (Integer.parseInt(idNo1[0].substring(0, 2)) < 30)
         this.age = 2023 - (Integer.parseInt(idNo.substring(0, 2)) + 2000);
      else
         this.age = 2023 - (Integer.parseInt(idNo.substring(0, 2)) + 1900);
   }

   public void setGender(String idNo) {
      String[] idNo2 = idNo.split("-");

      if (Integer.parseInt(idNo2[1].substring(0, 1)) % 2 == 0)
         this.gender = 'F';
      else
         this.gender = 'M';

   }

   public String getIdNo() {
      String[] idNo = this.idNo.split("-");
      String res = String.format("%s-%s*****", idNo[0], idNo[1].substring(0, 1));

      return res;
   }

   public String getName() {
      return this.name;
   }

   public int getAge() {
      return this.age;
   }

   public char getGender() {
      return this.gender;
   }

   public void infoPrint() {
      System.out.printf("주민번호 : %s 이름 : %s 나이 : %d세, 성별 : %c\n", getIdNo(), getName(), getAge(), getGender());
   }

}

// persontest1
// person 5명 생성 후 배열에 넣고 나이순 출력, 출력은 infoPrint()로
// 나이순 정렬은 메서드로
public class Ex07_PersonTest {


   static public void sortAge(Person[] p) {
      for (int i = 0; i < p.length - 1; i++) {
         for (int j = i + 1; j < p.length; j++) {
            if (p[i].getAge() < p[j].getAge()) {
               Person tmp = p[i];
               p[i] = p[j];
               p[j] = tmp;
            }
         }
      }
   } //sortAge

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      Person per1 = new Person("900101-112345", "person1");
      Person per2 = new Person("600101-112345", "person2");
      Person per3 = new Person("700101-212345", "person3");
      Person per4 = new Person("100101-212345", "person4");
      Person per5 = new Person("800101-312345", "person5");

      per1.infoPrint();
      per2.infoPrint();
      per3.infoPrint();
      per4.infoPrint();
      per5.infoPrint();

      Person[] per = new Person[] { per1, per2, per3, per4, per5 };
      sortAge(per);
      for (int i = 0; i < per.length; i++) {
         System.out.printf("%d : %s\n", i + 1, per[i].getName());
      }
   }

}   
 
*/







public class Ex07_PersonTtest_Choi {

} // class
