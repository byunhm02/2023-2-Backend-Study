# backend-week3

## Bean

Bean은 스프링 IoC컨네이너가 관리하는 자바 객체. 사용자가 Bean으로 관리하는 객체들의 생성과 소멸에 관련된 작업을 자동적으로 수행해주는데 객체가 생성되는 곳을 스프링에서는 Bean컨테이너라고 부름. 

스프링이 모든 의존성 객체를 스프링이 실행될 때 다 만들어주고 필요한 곳에 주입시켜줌으로써 Bean들은 싱글된 패턴의 특징을 가지며 제어의 흐름을 사용자가 컨트롤 하는 것이 아니라 스프링에게 맡겨 작업을 처리하게 됨. 

### 빈 스코프(Bean Scope?)

<aside>
💡 빈이 존재할 수 있는 범위를 의미. 빈의 스코프는 @Scope 어노테이션을 통해 지정 가능. 스프링은 기본적으로 별다른 설정을 하지 않으면 내부에서 생성하는 빈 오브젝트를 모두 싱글톤으로 만듦.

</aside>

```java
@Scope("prototype")
@Compoenet
public class Prototype{
...}
```

- 싱글톤(Singleton)
    - 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프
    
    ![싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 빈을 반환함.](https://user-images.githubusercontent.com/128574532/272135149-8087ba04-602d-411c-8bca-80f46b950419.png)
    
    싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 빈을 반환함.
    
    - 싱글톤 빈은 컨테이너 **생성 시점**에 같이 생성되고 초기화됨.
    - 스프링의 싱글톤
        
        > 스프링은 싱글톤 패턴을 사용함. 애플리케이션이 시작 될 때, 인스턴스를 메모리에 딱 하나 할당하고, 뒤의 호출 시 마다 해당 인스턴스를 반환해주는 디자인 패턴. 이를 적용하면 이미 만들어진 객체를 공유해서 효율적인 사용해 가능해짐. 스프링에서는 이를 위해 IoC 컨테이너를 사용함.
        > 
- 프로토타입(Prototype)
    - 스프링 컨테이너는 프로토타입 빈의 생성과 의존 관계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프
    
    ![프로토타입 스코프의 빈을 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서 반환함. ](https://user-images.githubusercontent.com/128574532/272135278-ae15ae8a-3ec7-41ab-97d5-0c74a830c259.png)
    
    프로토타입 스코프의 빈을 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서 반환함. 
    
    - 프로토타입 빈은 스프링 컨테이너에서 **빈을 조회할 때**  생성되고 초기화 메서드도 실행됨.
    - 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리함. 클라이언트에게 빈을 반환한 이후에는 생성된 프로토타입 빈을 관리하지 않음. 프로토타입 빈을 관리할 책임은 클라이언트에게 있음. 따라서 @PreDestory와 같은 종료 콜백 메서드가 호출되지 않음.
- 웹 관련 스코프
    - request : 웹 요청이 들어오고 나갈 때까지 유지되는 스코프
    - section : 웹 세션이 생성되고 종료될 때까지 유지되는 스코프
    - application : 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프

### 스프링 IoC컨테이너가 빈의 라이프사이클을 관리하는 방식

> *IoC Container은 Bean의 관리를 도와주는 컨테이너.*
> 
1. 객체 생성+property설정
    
    ![Untitled](https://user-images.githubusercontent.com/128574532/272135298-a1b12022-68e4-47a2-a2e8-10bf04791661.png)
    
    먼저 Spring IoC 컨테이너가 생성이 되면 빈 스코프가 싱글톤인 객체를 생성.
    
    이때 빈으로 등록하기 위해서 다양한 Configuration 메타데이터를 이용하여 통일된 Bean Definition을 생성.
    
    그리고 빈으로 등록할 POJO와 Bean Definition 정보를 이용하여 빈을 생성
    
    이 과정에서 싱글톤 패턴을 사용하는 것이 아닌 평범한 자바 클래스를 이용하여 객체를 생성
    
2. 의존 설정
    
    ![Untitled](https://user-images.githubusercontent.com/128574532/272135328-1ef04519-1fd9-44d9-9d58-842bfa467afb.png)
    
    빈 객체가 생성되면 IoC 컨테이너는 의존 설정을 함
    
    이때 의존성 자동 주입이 일어나게 됨
    
3. 객체 초기화 → 사용 → 소멸
    
    ![Untitled](https://user-images.githubusercontent.com/128574532/272135363-29cc5594-dc0e-4e66-91ea-67774ee17345.png)
    
    모든 객체가 다 초기화가 필요한 것은 아니지만 사용 전에 초기화 과정이 필요한 객체들은 초기화 과정을 진행함. 
    
    초기화가 끝나면 빈 사용 가능해짐
    
    그리고 스프링 컨테이너가 종료될 때 빈 스코프가 싱글톤인 객체들도 함께 소멸됨
    

## 스프링 빈 등록 방식

### application.xml파일에 Bean직접 등록

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	
    // Bean을 등록하는 과정
    <bean id="bookService" class="com.ex.forblog.book.BookService">
        <property name="bookRepository" ref="bookRepository"/>
    </bean>
	
    // Bean을 의존성 주입(DI)하는 과정
    <bean id="bookRepository" class="com.ex.forblog.book.BookRepository"/>
</beans>
```

### Component Scan

<aside>
💡 컴포넌트 스캔은 @Component를 명시하여 빈을  추가하는 방식. 클래스 위에 @Component를 붙이면 스프링이 알아서 스프링 컨테이너에 빈을 등록함

</aside>

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Compone{}
```

@Component는 위와 같이 `ElementType.TYPE` 설정이 있으므로 class 혹은 interface 에만 붙일 수 있음. 

### 컴포넌트 스캔의 대상

@Component 외에 @Controller, @Service, @Repository, @Configuration는 @Component의 상속을 받고 있으므로 모두 컴포넌트 스캔의 대상.

- @Controller
    - 스프링 MVC 컨트롤러로 인식됨
- @Repository
    - 스프링 데이터 접근 계층으로 인식하고 해당 계층에서 발생하는 예외는 모두 DataAccessException으로 변환함.
- @Service
    - 특별한 처리는 하지 않으나, 개발자들이 핵심 비즈니스 계층을 인식하는데 도움을 줌
- @Configuration
    - 스프링 설정 정보로 인식하고 스프링 빈이 **싱글톤을 유지하도록** 추가 처리를 함. (물론 스프링 빈 스코프가 싱글톤이 아니라면 추가 처리를 하지 않음)

### Java코드로 등록

<aside>
💡 클래스를 생성하고 @Configuration 어노테이션을 활용함.

 @Configuration 어노테이션을 클래스 선언부 앞에 추가한후, 특정타입을 리턴하는 메서드 앞에 @Bean어노테이션을 붙여주면 됨. 

</aside>

```java
@Configuration
public class AppConfig{
	@Bean
	public MemberRepository memberRepository(){
		return new MemoryMemberRepository();
	}
	@Bean
	public MemberService memberService(){
		return new MemberServiceImpl(memberRepository());
	}
}
```

```java
@Target({ElemenetType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean{

```

참고로 @Bean은 위와 같이 `ElementType` 설정이 METHOD 혹은 ANNOTATION_TYPE이므로 메소드나 어노테이션에만 붙일 수 있음. (클래스에 붙일 수는 없음)

> @Bean 어노테이션의 세부 내용
> 
> - *@Configuartion설정된 클래스의 메서드에서 사용 가능*
> - *메서드의 리턴 객체를 IoC의 Bean으로 등록*
> - *@Bean 의 이름은 기본적으로 메서드 이름으로 등록됨*
> - *@Bean(name=”name”) 으로 이름 변경*
> - *@Scope를 통해 객체 생성을 조정 가능*
> - *@Configuration에는 @Component가 있으므로 컴포넌트 스캔이 대상이 되어 자동 스캔을 통해 빈 등록이 가능.*
> - *Bean에 init(), destory()등 라이프사이클 메서드를 추가한 후 @Bean에 지정 가능*

Bean 등록을 위한 어노테이션을 추가할 때 **@Primary 어노테이션을 통해 Bean의 우선권을 부여**할 수도 있고, **@Qualifier("name") 어노테이션을 통해 특정 이름을 가지는 Bean을 찾을 수 있음.**

이 두가지 어노테이션을 동시에 사용했을 경우 @Qualifier 어노테이션을 우선적으로 적용.

| @Bean | @Component |
| --- | --- |
| 개발자가 컨트롤이 불가능한 외부 라이브러리들을 Bean으로 등록하고 싶은 경우 사용됨. | 개발자가 직접 컨트롤이 가능한 클래스들의 경우 사용됨. |
| 메소드 또는 어노테이션 단위에 붙일 수 있음 | 클래스 또는 인터페이스 단위에 붙일 수 있음.  |
