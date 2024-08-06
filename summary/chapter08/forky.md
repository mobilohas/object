# 01 의존성 이해하기

## 변경과 의존성

어떤 객체가 협력하기 위해 다른 객체를 필요로 할 때 두 객체 사이에 의존성이 존재

- 실행 시점: 의존하는 객체가 정상 동작하기 위해 실행 시에 의존 대상 객체가 필요
- 구현 시점: 의존 대상 객체 변경 시 의존 객체 함께 변경

의존성 = 변경에 의한 영향의 전파 가능성

- 두 요소 사이의 의존성 → 의존되는 요소가 변경될 때 의존하는 요소도 함께 변경될 수 있다

## 의존성 전이

의존성 = 의존하고 있는 대상의 변경에 영향을 받을 수 있는 *가능성*

의존성은 전이될 수 있다

- 변경의 방향과 캡슐화의 정도에 따라
- 직접 의존성: 한 요소가 다른 요소에 직접 의존
- 간접 의존성: 직접적인 관계는 없으나 의존성 전이에 의해 영향 전파되는 경우

## 런타임 의존성과 컴파일타임 의존성

런타임: 실행 시점

- 런타임 의존성 = 객체(인스턴스) 간의존성

컴파일타임: 컴파일 시점 또는 코드 그 자체

- 컴파일 타임 의존성 = 클래스 간 의존성

컴파일타임 구조와 런타임 구조 사이의 거리가 멀수록 설계가 유연, 재사용 가능

- 동일한 소스코드 구조로 다양한 실행구조 만들 수 있어야
- 실제로 협력할 구체 인스턴스는 런타임에 해결되어야

## 컨텍스트 독립성

컨텍스트 독립성: 각 객체가 해당 객체를 실행하는 시스템(문맥)에 대해 알지 못하는 것

- 클래스가 사용될 문맥에 대해 최소한의 가정만으로 이뤄져 있을 때  특정 문맥에 결합되지 않고 다른 문맥에서 재사용하기 수월

## 의존성 해결하기

의존성 해결: 컴파일 타임 의존성을 실행 컨텍스트에 맞는 적절한 런타임 의존성으로 교체하는 것

- 객체를 생성하는 시점에 생성자를 통해 의존성 해결
    - 생성자 parameter를 추상 타입으로
- 객체 생성 후 setter 메서드를 통해 의존성 해결
    - 추상 타입으로 setter 메서드 제공
    - 실행 시점에 의존 대상 변경 가능
    - 객체 상태 불완전할 수 있음 → 생성자 방식과 혼합
- 메서드 실행 시 인자를 이용해 의존성 해결
    - 일시적 의존 관계만 필요 + 실행될 때 마다 의존 대상 달라지는 경우
    - 클래스의 대부분의 메서드가 동일한 객체를 전달받는다면 지속적 의존 유지하도록 변경

# 02 유연한 설계

## 의존성과 결합도

모든 의존성이 나쁜 것은 아니다

- 문제는 의존성의 존재가 아닌 정도

바람직한 의존성은 재사용성과 관련있다

- 바람직한 의존성 = 컨텍스트 독립적인 의존성

결합도

- 느슨한 결합도, 약한 결합도: 두 요소 사이의 의존성이 바람직할 때
- 단단한 결합도, 강한 결합도: 두 요소 사이의 의존성이 바람직하지 못할 때

## 지식이 결합을 낳는다

결합도 = 서로에 대해 알고있는 지식의 양

- 협력하는 대상에 대해 더 적게 알아야 한다

## 추상화에 의존하라

추상화: 특정 절차, 물체를 의도적으로 생략하거나 감춰 복잡도를 극복하는 방법

의존하는 대상이 더 추상적일수록 결합도는 낮아진다

- 실행 컨텍스트에 대해 알아야 하는 정보를 줄일수록 결합도가 낮아진다
- 구체 클래스보다 추상 클래스에, 추상 클래스보다 인터페이스에 의존하도록

## 명시적인 의존성

명시적인 의존성: 퍼블릭 인터페이스에 노출되는 의존성

- 생성자, setter, 메서드 인자 등으로 의존성을 해결한 경우

숨겨진 의존성: 퍼블릭 인터페이스에 표현되지 않는 의존성

- 의존성 파악을 위해 내부 구현 살펴봐야
- 다른 컨텍스트에서 재사용 하기 위해 내부 구현 변경해야

의존성은 명시적이어야 한다

- 퍼블릭 인터페이스를 통해 적절한 런타임 의존성으로 교체할 수 있도록
- 경계해야 할 것은 의존성 자체가 아닌 숨겨진 의존성

## new는 해롭다

new는 결합도를 높인다

- 구체 클래스 결합
- 인스턴스 생성 인자 노출 → 인자로 사용되는 구체 클래스 의존성 추가

사용과 생성의 책임을 분리하라

- 사용할 인스턴스 생성하는 책임을 클라이언트로 이동

## 가끔은 생성해도 무방하다

협력하는 기본 객체를 설정하고 싶은 경우 → 생성자, 메서드 오버로딩 활용

- 결합도와 사용성 트레이드오프
- 그러나 가급적 구체 클래스에 대한 의존 제거하라 (factory)

## 표준 클래스에 대한 의존은 해롭지 않다

변경될 확률이 거의 없는 클래스라면 의존성이 문제 되지 않음

- java라면 JDK 표준 클래스
- 이런 경우에도 추상화에 의존하고 의존성을 드러내는 것이 좋은 습관

## 컨텍스트 확장하기

낮은 결합도 → 컨텍스트 확장

- 추상화에 의존
- 명시적 의존성
- 구체 클래스에 대한 책임은 외부로

예시

```java
public class Movie {
	private DiscountPolicy discountPolicy;
	private Money fee;

	...

	public Money calculateFee(Screening screening) {
		...
		return fee.minus(discountPolicy.calculateDiscountAmount(screening));
	}
}
```

할인 하지 않는 영화

```java
public class NoneDiscountPolicy extends DiscountPolicy {
	@Override
	protected Money getDiscountAmount(Screening screening) {
		return Money.ZERO;
	}
}

Movie movie = new Movie(Money.wons(10000), new NoneDiscountPolicy());
```

중복 할인 정책이 적용된 영화

```java
public class OverlappedDiscountPolicy extends DiscountPolicy {
	private List<DiscountPolicy> discountPolicies = new ArrayList<>();

	public OverlappedDiscountPolicy(DiscountPolicy... discountPolicies) {
		this.discountPolicies = Arrays.asList(discountPolicies);
	}

	@Override
	protected Money getDiscountAmount(Screening screening) {
		Money result = Money.ZERO;
		for(DiscountPolicy each: discountPolicies) {
			result = result.plus(each.calculateDiscountAmount(screening));
		}
		return result;
	}
}

Movie movie = new Movie(Money.wons(10000), new OverlappedDiscountPolicy(...));
```

## 조합 가능한 행동

유연하고 재사용가능한 설계 = 작은 객체들의 행동 조합으로 새로운 행동을 이끌어낼 수 있는 설계

훌륭한 객체지향 설계 = 객체들의 조합을 선언적으로 표현하여 객체들이 무엇을 하는지 표현하는 설계

- 응집도 높은 책임을 가진 작은 객체들을 다양한 방식으로 연결해 기능을 쉽게 확장
- 객체의 행동을 선언적으로 정의: 객체가 어떤 객체와 연결되었는지만 봐도 객체행동을 예상, 이해할 수 있다
