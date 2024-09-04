# 10장 상속과 코드 재사용

객체지향 장점 - 코드 재사용에 용이 - 상속 
: 기존 인스턴스 변수와 메서드를 클래스에 자동 추가하는 것
(합성도 있다.)

## 01 상속과 중복 코드
### DRY(Don't Repeat Yourself) 원칙
중복의 단점 
- 중복은 변경에 방해된다.
- 유사한 코드인데 새로 만든 이유가 뭘까? 의심하게 된다.

변경이 일어날 때, 같이 고쳐야 한다면 두 코드는 중복된 것이다.

### 중복과 변경
예제 : 통화한 단위 시간당 요금 계산하는 계산기를 만들어보자. 

```java
import java.time.LocalDateTime;

public class call {
    private LocalDateTime from;
    private LocalDateTime to;
}
```

```java
import java.util.ArrayList;

public class Phone {
    private Money amount; // 시간당 금액
    private Duratoin seconds; // 단위시간
    private List<Call> calls = new ArrayList<>(); // 통화 내역

    public Money calculateFee() {
        Money result = Money.zero;
        for (Call call : calls) {
            result = result.plus(amount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
        }
    }
}
```
이렇게 코드를 작성할 수 있다.

이제 요구사항이 추가되서, 심야 할인 요금제 휴대폰이 추가되었다고 해보자.

밤 10시 이후 통화에 대해서는 요금을 할인해주는 휴대폰인 것이다.
이를 구현하기 위해서 nightDiscountPhone이라는 클래스를 기존 Phone에서 복사해서 만들어보자, 
그리고 요금 계산에서만 분기처리해주자.

이렇게 되면 거의 중복되기 때문에, 나중에 요구사항 변경에 대처할 때, 놓칠 여지가 생긴다.

### 중복코드 수정하기
이제 통화 요금에 세금을 부과하는 코드를 추가한다고 해보자. 만약 그냥 phone은 수정했지만, 심야할인 휴대폰은 수정하지 않는다면 버그가 될 것이다.

### 타입코드 활용하기
두 클래스를 합치고 타입을 넘겨받아서 if문으로 분기처리를 해주자. 
하지만 이렇게 하면 높은 결합도와 낮은 응집도의 코드가 된다.

### 상속을 이용해서 코드 중복 줄이기
야간 할인 폰은 기존 폰과 거의 유사하기 때문에 그냥 폰을 상속받아보자.

```java
public class NightDiscountPhone extends Phone {
    private static final int LATE_NIGHT_HOUR = 22;

    private Money nightAmount;

    @Override
    public Money calculateFee() {
        // 부모 클래스의 calculateFee 호출
        Money result = super.calculateFee();
        Money nightlyFee = Money.zero;
        for (Call call : calls) {
            if (call.getFrom().getHours() >= LATE_NIGHT_HOUR) {
                nightlyFee = nightlyFee.plus(
                        getAmount.minus(nightAmount).times(
                                call.getDuration().getSeconds() / getSeconds().getSeconds()
                        )
                );
            }
        }
        return result.minus(nightlyFee);
    }
}
```

이 코드는 상속을 활용했지만, 왜 저렇게 계산한건지 이해하기 위해서는 생각을 좀 해야하는 단점이 있다?
즉, 자식 클래스의 작성자는 부모 클래스의 구현 방법에 대해서 정확한 이해를 해야 한다. - 상속은 결합도를 높인다.

### 강하게 결합된 phone 과 nightDiscountPhone
super 참조를 이용해서 메서드 오버라이딩을 했기 때문에, 부모가 변경되면 자식의 메서드가 어떻게 영향을 받을지 모른다.

> ### 상속을 위한 경고 1
> super 호출을 제거할 수 있는 방법을 찾아 결합도를 낮춰라.

## 02 취약한 기반 클래스 문제
상속 사용하면 피할 수 없는 객체지향 프로그래밍의 근본적인 취약점

객체지향은 캡슐화를 통해 변경지점을 통제하는데, 상속은 캡슐화를 저해시킨다.

### 불필요한 인터페이스 상속 문제
java 초기 stack은 vector를 상속받았는데, 이로 인해 후인선출의 원칙이 깨졌다.
> ### 상속을 위한 경고 2
> 상속받은 부모 클래스의 메서드가 자식 클래스의 내부 구조에 대한 규칙을 깨트릴 수 있다.

### 메서드 오버라이딩의 오작용 문제
상속을 문서화해야한다. 그렇지 않으면 금지해야한다. 

### 부모 클래스와 자식 클래스의 동시 수정 문제
노래를 저장하는 playlist에는 append메서드가 있다. 그런데 개인적인 수정이 가능한 personalPlaylist에는 삭제 기능도 있다.
personalPlaylist는 playlist를 상속받아서 사용한다.
만약 이 때, playlist가 append할 때, 가수 이름도 같이 저장한다고 하면, personalPlaylist의 삭제하는 메서드도 같이 변경되어야 한다.

## 03 phone 다시 살펴보기
피해 최소화 방법에 대해서 고민해보자.
자식 클래스가 부모 클래스가 아닌 추상화에 의존하도록 하는 것이다.
부모, 자식 클래스 모두 추상화에 의존하도록 하는 것이다.

1) 차이나는 메서드 빼기
다른 부분을 메서드로 빼라. calculateFee에서 phone, nightDiscountPhone의 요금 계산 방법이 다르다.
이걸 calculateCallFee로 메서드로 빼라. 
그럼 calculateFee()는 같아졌다.

2) 추상클래스 제작
AbstractPhone이라고 추상 클래스를 만들자.
```java
public abstract class AbstractPhone {
    private List<Call> calls = new ArrayList<>();

    public Money calculateFee() {
        Money result = Money.Zero;
        for (Call call : calls) {
            result = result.plus(calculateCallFee(call));
        }
        return result;
    }

    abstract protected Money calculateCallFee(Call call);
}
```
3) 이렇게 해놓고, 각각의 상속받은 클래스에서 calculateCallFee만 오버라이딩 해주면 된다.

> 이렇게 해놓으면 코드를 잘못 짜더라도, 구조가 좋기 때문에 추상화하기 쉽다. 

### 추상화가 핵심이다. 
이렇게 해놓으니깐 부모 코드가 변경이 되도, 자식 코드에 영향을 못미친다. 
시그니처가 변경되지 않는 이상!

개방폐쇠 원칙도 준수한다. 새로운 요금제 폰을 추가하고 싶다면 상속받고 요금계산만 작성하면 된다.

### 의도를 드러내는 작명을 해라
abstractPhone -> phone
phone -> regularPhone

세금 추가하는 요구사항 ? 공통 변경이기 때문에 phone의 calculateCallFee를 수정하면 된다!

하지만 부모클래스에 taxRate라는 인스턴스 변수가 추가되면, 자식 초기화시에도 같이 추가해줘야한다. 
부모의 인스턴스 변수 추가는 자식에게 영향을 미칠 수밖에 없다.  - 하지만 크리티컬하진 않다.

## 04 차이에 의한 프로그래밍




