# 11장 합성과 유연한 설계

# 0. 합성은

- 합성은 전체를 표현하는 객체가 부분을 표현하는 객체를 포함해서 부분 객체의 코드를 재사용한다.
- 두 객체 사이의 의존성은 런타임 시점에서 결정된다.
- 내부에 포함되는 객체의 구현이 아닌 퍼블릭 인터페이스에 의존한다
- **코드 재사용을 위해서는 객체 합성이 클래스 상속보다 더 좋은 방법이다**

# 1. 상속을 합성으로 변경하기

## 상속을 사용하였을 때 문제점 3가지

### 1.불필요한 인터페이스 상속 문제

자식 클래스에게 부모의 부적합한 오퍼레이션이 상ㄴ속되어 자식 클래스 인스턴스의 상태가 불안정해 질 수 있다. (stack 과 vector)

### 2.메서드 오버라이딩 오작용 문제

자식 클래스가 부모 클래스의 메서드를 오버라이딩 할 때 자식 클래스가 부모 클래스의 메서드 호출 방법에 영향을 받는다 (HashSet을 상속받는 InstrumentedHashSet)

### 3.부모 클래스와 자식 클래스 동시 수정의 문제

부모 클래스와 자식 클래스 간 개념 결합으로 인해 부모 클래스를 변경해야할 때 자식 클래스도 같이 변경해야한다.

합성을 사용하면 위 세가지 문제점을 해결할 수 있다.

### 상속을 합성으로 바꾸는 방법은

- **자식 클래스에 선언된 상속 관계를 제거한다**
- **부모 클래스의 인스턴스를 자식 클래스의 인스턴스 변수로 선언한다**

변경 예시) Vector를 상속하여 구현한 Stack을 합성으로 변경

```java
    public class Stack<E> {
        private Vector<E> elements = new Vector<>();

        public E push(E item){
            elements.addElement(item);
            return item;
        }
        public E pop(){
            if(elements.isEmpty()){
                throw new EmptyStackException();
            }
            return elements.remove(elements.size() - 1);
        }
    }

```

- 상속 대신 합성을 사용하면 Stack의 퍼블릭 인터페이스는 불필요한 Vecotor 오퍼레이션을 포함하지 않는다

> 몽키패치
현재 실행 중인 환경에만 영향을 미치도록 지역적으로 코드를 수정하거나 확장하는 것을 가리킴
자바에서는 언어차원에서 몽키 패치를 지원하지 않아서 바이트코드를 직접 변환하거나 AOP를 이용해 몽키패치를 구현한다.
>

# 2. 상속으로 인한 조합의 폭발적인 증가

상속을 사용하면 아래와 같은 문제가 있다.

- 하나의 기능을 추가하거나 수정하기 위해 불필요하게 많은 수의 클래스를 추가하거나 수정한다
- 단일 상속만 지원하는 언어에서는 상속으로 인해 오히려 중복 코드의 양이 늘어날 수 있다

다음은 핸드폰 요금에 대한 기본 정첵에 세금 정책을 조합하는 코드이다.

세금 정책을 적용하기 위해 상속을 사용하였다.

```java
public class TaxableRegularPhone extends RegularPhone {
    private double taxRate;

    public TaxableRegularPhone(Money amount, Duration seconds, double taxRate) {
        super(amount, seconds);
        this.taxRate = taxRate;
    }
    @Override
    public Money calculateFee() {
        Money fee = super.calcaulteFee();
        return fee.plus(fee.times(taxRate));
    }
}
```

- 부모 클래스의 메서드를 `super` 키워드로 재사용하기 때문에 결합도가 높다

결합도를 낮추기 위해서는 부모 클래스에서 추상 메서드를 제공하고, 자식 클래스는 추상 클래스를 오버라이딩 해야한다. 부모 클래스에서 추상 메서드를 추가하게 되면 모든 자식 클래스들은 추상 메서드를 오버라이딩해야한다.

```java
public abstract class Phone {
    private List<Call> calls = new ArrayList<>();

    public Money calculateFee() {
        Money result = Money.ZERO;

        for (Call call : calls) {
            result = result.plus(calcaulteCallFee(call));
        }

        return afterCalculated(result);
    }
    protected abstract Moneny calculateCallFee(Call call);

    protected Money afterCalculated(Money fee) {
        return fee;
    }
}

```

추상 메서드의 단점은 상속 계층에 속하는 모든 자식 클래스가 추상 메서드를 오버라이딩해야한다는 것이다. 이에 대한 해결 방법은 메서드 기본 구현을 제공하는 것이다. 이처럼 추상 메서드와 동일하게 자식 클래스에서 오버라이딩할 의도로 메서드를 추가했지만 편의를 위해 기본 구현을 제공하는 메서드를 **`훅 메서드`**라고 한다.

아직 상속 구조의 다른 문제점이 남아있다.

요구사항 중 핸드폰 요금의 부가 정책은 자유롭게 조합할 수 있어야 하고 순서도 임의로 결정 될 수 있어야 한다. 만약 상속으로 부가정책을 구현하게 되면 모든 가능한 조합별로 자식 클래스를 만들어야한다.

즉 상속의 남용으로 하나의 기능을 추가하기 위해 필요 이상으로 많은 수의 클래스를 추가 하는 경우가 존재한다. 이를 `클래스 폭발` 또는 `조합의 폭발`이라고 부른다.

# 3. 합성 관계로 변경하기

## 합성은

- 조합을 구성하는 요소들을 개별 클래스로 구현한 후 실행 시점에 인스턴스를 조립하는 방법이다
- 컴파일 의존성에 속박되지 않고 다양한 방식의 런타임 의존성을 구성할 수 있는 장점이 있다
- 다만 설계의 복잡도가 상승하여 코드를 이해하기 어려워진다

상속을 사용한 설계를 합성으로 바꾸기 위해 가장 먼저 해야 할 일은 각 정책을 별도의 클래스로 구현하는 것이다.

```java
public interface RatePolicy {
    Moneny calculateFee(Phone phone);
}
```

공통 로직을 추상 클래스로 구현한다.

```java
public abstract class BasicRatePolicy implements RatePolicy {

    @Override
    public Money calculateFee() {
        Money result = Money.ZERO;

        for (Call call : calls) {
            result = result.plus(calcaulteCallFee(call));
        }

        return result;
    }
    protected abstract Moneny calculateCallFee(Call call);
}

```

기본 정책은 RegularPolicy 구현체에 구현한다.

```java
    public class RegularPolicy extends BasicRatePolicy {
        private Moneny amount;
        private Duration seconds;

        public RegularPhone(Moneny amount, Duration seconds) {
            this.amount = amount;
            this.seconds = seconds;
        }

        @Override
        protected Moneny calculateCallFee(Call call) {
            return amount.times(call.getDuration().getSEconds() / seconds.getSeconds());
        }
    }

```

심야 할인 정책은 NightlyDiscountPolicy 구현체에 구현한다

```java
public class NightlyDiscountPolicy extends BasicRatePolicy {
    private static final int LATE_NIGHT_HOUR = 22;

    private Moneny nightlyAmount;
    private Money regularAmount;
    private Duration seconds;

    public NightlyDiscountPhone(Moneny nightlyAmount, Money regularAmount, Duration seconds) {
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.seconds = seconds;
    }

    @Override
    protected Moneny calculateCallFee(Call call) {
        if(call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
            return nightlyAmount.times(call.getDeration().getSeconds() / seconds.getSeconds());
        } else {
            return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
        }
    }
}

```

아래 Phone 내부에 RatePolicy에 대한 참조자가 있는 것이 합성이다.

```java
public class Phone {
    private RatePolicy ratePolicy;
    private List<Call> calls = new ArrayList<>();

    public Phone(RatePolicy ratePolicy) {
        this.ratePolicy = ratePolicy;
    }
    
    public List<Call> getCalls () {
        return Collections.unmodifiableList(calls);
    }
    public Money calculateFee() {
        return ratePolicy.calculateFee(this);
    }
}

```

- Phone이 다양한 요금정책과 협력할 수 있어야 하기 때문에 요금정책 타입이 RatePolicy라는 `인터페이스`로 정의되어 있다.
- 컴파일 타임 의존성을 구체적인 런타임 의존성으로 대체하기 위해 생성자로 RatePolicy 인스턴스의 `의존성을 주입`받는다.

이제 일반 요금제 규칙에 따라 통화 요금을 계산하고 싶다면 아래와 같이 Phone과 BasicRatePolicy 인스턴스를 합성한다.

```java
Phone phone = new Phone(
	new RegularPolicy(Money.wons(10), Duration.ofSecond(10))
);
```

만약 심야 할인 요금제의 규칙으로 통화 요금을 계산하고 싶다면 Phone과 NightlyDiscountPolicy의 인스턴스를 합성한다

```java
Phone phone = new Phone(
	new NightlyDiscountPolicy(Money.wons(10), Duration.ofSecond(10))
);
```

부가 정책은 RatePolicy 인터페이스를 구현해야하며 내부에 또다른 RatePolicy 인스턴스를 합성할 수 있어야 한다

```java
public abstract class AdditionalRatePolicy implements RatePolicy {
    private RatePolicy next;

    public AdditionalRatePolicy(RatePolicy next) {
        this.next = next;
    }

    @Override
    public Moneny calculateFee(Phone phone) {
        Money fee = next.calculateFee(phone);
        return afterCalculated(fee);
    }

    abstract protected Money afterCalculated(Money fee);
}

```

- Phone에 입장에서는 AdditionalRatePolicy는 RatePolicy의 역할을 수행한다. 따라서 RatePolicy `인터페이스`를 구현한다.
- 다른 요금 정책과 조합될 수 있도록 `RatePolicy` 타입의 next 인스턴스 변수를 포함한다
- 런타임 의존성으로 쉽게 대체할 수 있도록 RatePolicy 타입 인스턴스를 인자로 받는 생성자를 제공한다
- calculateFee 메서드는 next가 참조하고 있는 인스턴스에게 calculateFee 메세지를 전송한다. 자식 클래스는 afterCalculated 메서드를 오버라이딩해서 적절한 부가 정책을 구현한다

세금 정책은 요금이 계산된 이후 세금 비율을 plus한다.

```java
public class TaxablePolicy extends AdditionalRatePolicy {
    private double taxRatio;
    
    public TaxablePolicy(double taxRate, RatePolicy next) {
        super(next);
        this.taxRatio = taxRate;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return fee.plus(fee.times(taxRatio));
    }
}

```

기본 요금 할인 정책은 요금이 계산된이후 discountAmount만큼 minus한다

```java
public class RateDiscountablePolicy extends AdditionalRatePolicy {
    private Money discountAmount;

    public RateDiscountablePolicy(Money discountAmount, RatePolicy next) {
        super(next);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return fee.minus(discountAmount);
    }
}

```

## 기본 정책과 부가 정책 합성하기

합성을 사용한 방식은 런타임시점에 다양한 방식으로 정책들을 조합할 수 있다.

```java
	Phone phone = new Phone( new TaxablePolicy(0.05,
                                    new RegularPolicy( ... ) );
```

```java
	Phone phone = new Phone( new TaxablePolicy(0.05,
                                    new RateDiscountablePolicy(Money.wons(1000),
                                        new RegularPolicy( ... )));
```

순서를 바꿔도 된다

```java
	Phone phone = new Phone( new RateDiscountablePolicy(Money.wons(1000),
                                new TaxablePolicy(0.05,										
                                    new RegularPolicy( ... )));
```

## 새로운 정책 추가하기

상속은 필요한 조합의 수만큼 클래스를 추가하고 변경했어야 했다. 그에 반해 합성 기반 설계에서는 하나의 클래스만 추가하고 수정하면 된다.

## 객체 합성이 클래스 상속보다 더 좋은 방법이다.

그럼 상속은 사용하면 안 되는 걸까? 상속은 언제 사용하는 걸까? 라는 물음이 남아 있을 수 있다.

상속은 구현 상속과 인터페이스 상속이라는 2가지 종류가 있다.앞서 살펴본 단점은 구현 상속의 단점이다.

즉, 구현 상속은 피하고 인터페이스 상속을 사용해야한다.

# 4. 믹스인

- 객체를 생성할 때 코드 일부를 클래스안에 섞어 재사용하는 기법이다.
- 상속과 달리 믹스인은 유연하게 관계를 재구성할 수 있다.