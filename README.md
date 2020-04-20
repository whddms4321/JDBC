  
JDBC
-----    
    
## OJDBC H2    

  - 오라클에서 제공하는 오라클 DBMS와 자바를 연결하기 위한 라이브러리
  - 자바 11버전에서는 OJDBC 6을 사용

  - 자바는 유니코드, 오라클은 UTF를 사용해 한글을 인식함   
  
  - 설정을 총 6개 바꾸기    
      - general(workspace,editor의spelling) 2 json(jason file) 1개 web(jsp,css,html) 3개   


- **jdbc의 종류**   

  1. **dirvermanager**   
  
     - 데이터 원본에 jdbc 드라이버를 통하여 커넥션을 만드는 역할
     - class.forname() 메소드를 통해 생성되며, 반드시 예외처리 해야함
     - 직접 객체 생성이 불가능하고,getconnection()메소드    

  2. **Connection**   
     - 특정 데이터 원본과 연결된 커넥션   
     - Statement 객체를 생성할 때도 Connection 객체를 이용해야 함   
     - SQL 문장을 실행시키기 전 우선 Connection 객체를 필요    

  3. **Statement**    
     - Cnnection 객체에 의해 프로그램에 리턴 되는 객체에 의해 구현되는 일종의 메소드 집합    쉽게 말해 connectino이 연결되는데 사용되는 매개체  
     - Connection 클래스의 createStatement() 메소드를 호출하여 객체 생성
     - Statement 객체로 SQL문을 String 객체에 담아 인자로 전달하여 질의를 수행    

  4. **PreparedStatement**    
     - Statement클래스를 상속하여 만들어진 클래스로 기본적인 수행역할은 동일
     - SQL 문장이 미리 컴파일 되고, 실행시간 동안 인수 값을 위한 공간을 확보        할 수 있다는 점에서 Statement 와 다름
     - Connection 클래스의 preparedStatement() 메소드를 호출하여 객체 생성    

        - Statement와 PreparedStatement의 차이점   
          - statement는 캐시 사용x     
          ```
          select * from member where member_id='입력값'
          : 일일이 대조
          ```    

          - P.Statement는 캐시 사용
          ```
          select * from member where member_id=?
          user01,user02 : user02부턴 select문을 캐시에 저장해 입력문만 받아서 사용,     
          그래서 속도가 빠름.
          ```   


  5. **ResultSet**   
     - SELECT문을 사용한 질의 성공 시 반환되는 객체
     - ResultSet은 SQL 질의에 의해 생성된 결과를 담고 있으며 '커서(cursor)'를       이용하여 특정 행에 대한 참조를 조작       
        
- **jdbc의 작성 절차**   

   1. 드라이버 등록
   2. Connection객체생성
   3. Statement객체생성 - >preparedStatement 일때 위치홀더 사용으로     sql을 정의 캐쉬를 사용해서 연산속도 상승,보안성 높아짐 
   4. 쿼리문 전송 및 결과 수신
   5. 결과 처리
   6. 자원 반환    

- **single ton(싱글톤) 패턴**  
  - 싱글톤은 클래스에 대한 객체가 프로그램 구동 내내 한개만 작성되어 사용되게     하는 디자인 패턴을 의미한다.   
  - 코드는 동일하며 프로그램 시작 후, 1번만 선언한 뒤 계속 불러다 사용.   
  - 이때 만들어진 객체를 불러다 쓰기위해 **Static** 형태를 사용한다.
  ```
  public static Connection getConnection() {
  }
  ```   
  - Connection 객체 생성, commit과 rollback 등을 위해 해당 패턴을 사용 한다.   
  - Member m = new Member();선언과 다른 이유는 Member객체는 필요할 때마다    만들어 쓰는 반면 싱글톤 패턴은 하나를 선언하고 계속 불러사용하기 떄문이다.   

- **MVC**    
   - model - 데이터 형태(vo -Member.java)
      - vo : value object
      - dao : data access object    	  
   - View  - 사용자 화면
   - Controller - 데이터 처리    
     
- 데이터 모델링 정의    

  - 정보 구조의 실체와 관계를 중심으로 명확하고 체계적으로 표현하여 문서화 하는 기법
    ERD(entity+relation+diagmram) 
    
- 논리 데이터 모델링에서 제 1정규화로 이뤄어지는 3가지     

  1. 반복되는 속성이나 GROUP속성 제거
  2. 새로운 실체와 1:N의 관계가 이루어짐
  3. 모든 속성은 반드시 하나의 값을 가져야함(반복X)
  

