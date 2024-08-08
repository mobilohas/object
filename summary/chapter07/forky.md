추상화: 불필요한 정보를 제거하고 현재의 문제 해결에 필요한 핵심만 남기는 작업

분해: 큰 문제를 해결 가능한 작은 문제로 나누는 작업, 가장 일반적인 추상화 방법

추상화와 분해는 복잡성이 존재하는 곳에 함께 존재 = 소프트웨어 개발 영역

# 01 프로시저 추상화와 데이터 추상화

프로시저 추상화: 소프트웨어가 무엇을 해야 하는지를 추상화

- 기능 분해, 알고리즘 분해

데이터 추상화: 소프트웨어가 무엇을 알아야 하는지를 추상화

- 추상 데이터 타입: 데이터를 중심으로 타입을 추상화
- 객체지향: 데이터를 중심으로 프로시저를 추상화

프로그래밍 언어의 관점에서 객체지향 = 데이터를 중심으로 데이터 추상화와 프로시저 추상화를 통합한 객체를 이용하여 시스템을 분해하는 방법

# 02 프로시저 추상화와 기능 분해

## 메인 함수로서의 시스템

알고리즘 분해, 기능 분해의 관점에서 추상화의 단위는 프로시저

하향식 접근법 = 전통적인 기능 분해 방법

- 최상위 기능을 더 작은 하위 기능으로 분해

## 급여 관리 시스템

- 기능 분해의 결과는 최상위 기능 수행에 필요한 절차를 실행 순으로 나열한 것
- 기능 분해에서는 기능을 중심으로 필요한 데이터 종류, 저장 방식 결정

## 급여 관리 시스템 구현

- 하향식 기능 분해 → 시스템이 메인 함수를 root로 하는 tree 구조로 표현됨

## 하향식 기능 분해의 문제점

변경에 취약한 설계

### 하나의 메인 함수라는 비현실적인 아이디어

- 현대적인 시스템은 동등한 수준의 다양한 기능으로 구성
    - 하나의 메인 기능을 선택할 수 없음
- 실제 시스템에 정상이란 존재하지 않는다

### 메인 함수의 빈번한 재설계

- 시스템은 여러개의 정상으로 구성 → 하나의 정상으로 간주했던 main 함수 구현이 수정될 수 밖에

### 비즈니스 로직과 사용자 인터페이스의 결합

- 하향식 접근 → 로직 설계 초기 단계부터 입출력 양식 고민 강요
- 사용자 인터페이스 + 비즈니스 로직 강한 결합 → 관심사 분리 X

### 성급하게 결정된 실행 순서

- 처음부터 how 고민 → 실행 순서부터 결정
    - 시간 제약이 아닌 논리적 제약을 설계 기준으로 삼아야 함(객체 사이 논리적인 관계 중심으로 설계)
- 높은 결합도: 상위 함수가 강요하는 문맥에 결합 → 함께 절차를 구성하는 다른 함수들과 시간적으로 결합

### 데이터 변경으로 인한 파급효과

- 어떤 데이터를 어떤 함수가 사용하고 있는지 알기 어려움
- 데이터 변경 영향 범위 넓음
    - 데이터와 함께 변경되는 부분과 그렇지 않은 부분을 분리해야(= 잘 정의된 퍼블릭 인터페이스로 데이터 접근 통제해야)
    - ⇒ 의존성 관리의 핵심 (정보 은닉, 모듈)

## 언제 하향식 분해가 유용한가?

- 이미 명확한 아이디어를 갖고 그것을 서술할 때
    - 작은 프로그램, 개별 알고리즘

# 03 모듈

## 정보 은닉과 모듈

정보 은닉: 자주 변경되는 부분을 상대적으로 덜 변경되는 안정적인 인터페이스 뒤로 감춰야 한다

- 모듈 단위로 분해하기 위한 기본 원리

모듈과 기능 분해는 상호 배타적인 관계가 아니다

- 모듈 분해로 감춰야 할 것을 선택하고 그 주변에 안정적인 보호막 설치
    - 보호막 = 퍼블릭 인터페이스
- 기능 분해로 하나의 기능 구현을 위해 필요한 기능을 순차적으로 탐색
- 모듈 분해(비밀 결정) → 기능 분해(모듈에 필요한 퍼블릭 인터페이스 구현)

모듈이 감춰야 할 비밀

- 복잡성: 모듈을 추상화할 수 있는 간단한 인터페이스 제공
- 변경 가능성: 변경 가능한 설계 결정을 모듈 내부로 감추고 외부에는 쉽게 변경되지 않을 인터페이스 제공

## 모듈의 장점과 한계

장점

- 모듈 내부의 변수가 변경되더라도 모듈 내부에만 영향을 미친다
    - 모듈 내 변수 참조 위치 제한 가능 → 파급 효과 제어
- 비즈니스 로직과 사용자 인터페이스에 대한 관심사를 분리한다
    - 사용자 인터페이스를 모듈 외부로 분리
- 전역 변수와 전역 함수를 제거함으로써 네임스페이스 오염을 방지한다
    - 전역에서 동일한 이름 사용 → 전역 네임스페이스 오염 방지 + 이름 충돌 위험 완화

핵심

- 변경의 정도에 따라 시스템 분리
- 데이터가 중심
    - 감춰야 하는 데이터를 중심으로 시스템 분해한 뒤 데이터 조작에 필요한 함수 결정

한계

- 변경 관리를 위한 기법 → 인스턴스 개념 X
    - 어떤 개념에 대한 개별 구현체를 독립적인 단위로 다룰 방법 필요 (→ 추상 데이터 타입 등장)

# 04 데이터 추상화와 추상 데이터 타입

## 추상 데이터 타입

타입: 변수에 저장할 수 있는 내용물의 종류 + 변수에 적용될 수 있는 연산의 가짓수

추상 데이터 타입: 추상 객체의 클래스를 정의한 것

- 사용할 수 있는 오퍼레이션으로 규정 = 오퍼레이션으로 정의할 수 있음
- 사용하는 프로그래머는 제공하는 행위에만 관심, 구현 세부 사항 무시

여전히 절차적이다

- 데이터를 이용한 기능 구현 로직은 여전히 타입 외부에 존재
- 데이터와 기능 분리되어 있음

# 05 클래스

## 클래스는 추상 데이터 타입인가?

추상 데이터 타입은 타입 추상화

- 상속, 다형성 지원 X (객체기반 프로그래밍)
- 오퍼레이션 기준으로 타입 추상화
    - 대표적인 하나의 물리적 타입이 다수의 세부적인 타입 감춤
    - 오퍼레이션 기준으로 타입 통합

클래스는 절차 추상화

- 상속, 다형성 지원 (객체지향 프로그래밍)
- 타입을 기준으로 절차 추상화
    - 각 세부 타입 명시적 정의 → 오퍼레이션 각각 구현
    - 다형성 → 절차에 대한 차이 감춤

## 변경을 기준으로 선택하라

타입 변수를 이용한 조건문을 다형성으로 대체하라

- 클래스 내부에 있는 타입에 따른 조건문 → 각 타입을 클래스로 정의한 후 상속 계층 추가
- 새로운 타입 추가를 위해 클라이언트 코드 수정할 필요가 없음
    - 개방-폐쇄 원칙(Open-Closed Principle, OCP): 기존 코드에 영향 없이 새로운 유형과 행위 추가할 수 있는 특성

설계에 요구되는 변경의 압력에 따라 선택하라

- 타입 추가의 압력 → 객체지향
- 오퍼레이션 추가의 압력 → 추상 데이터 타입

> 모듈과 추상 데이터 타입은 데이터 중심적인 관점, 객체지향은 서비스 중심적인 관점
> 

## 협력이 중요하다

- 협력에 필요한 책임을 다양한 방식으로 수행해야 할 때만 타입 계층으로 추상화하라
- 타입 계층과 다형성은 책임 수행 방법에 대한 고민의 결과물, 그 자체가 목적 X