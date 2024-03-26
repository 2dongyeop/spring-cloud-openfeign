# spring-cloud-openfeign

## *Overview.*
Spring 애플리케이션에서 사용할 수 있는 HTTP Client에 대해 정리합니다.

아래에 나오는 각 HTTP Client 별 상세 설명은 [노션](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803?pvs=4)에서 확인하실 수 있습니다.

<br/>

> HTTP Client 목록
> - {클래스명} : {의존성}
> - RestTemplate : spring-web
> - WebClient : spring-webflux
> - RestClient : spring-web (Spring Boot 3.1 이상부터 지원)
> - HttpInterface
>   - Spring Boot 3.1 이상일 경우 spring-web (내부 구현체 : RestClient)
>   - Spring Boot 3.0 이하일 경우 spring-webflux (내부 구현체 : WebClient)
> - OpenFeign : spring-cloud

<br/>

## *Quick Start.*
### 0. Pre-Setting
- required
  - openjdk-21 install
- optional
  - IntelliJ Ultimate install

<br/>

### 1. Git Repository Clone
```bash
git clone https://github.com/2dongyeop/spring-cloud-openfeign.git
```

<br/>

### 2. Start Server

이 프로젝트는 **요청 서버와 응답 서버를 모두 실행**해야 따라하실 수 있습니다.

```bash
# 1. 요청 서버 프로젝트로 이동
$ cd spring-cloud-openfeign/request-server

# 2. Open in IDE
# 3. Project Setting (JDK 21, etc...)
# 4. Run Spring Application

# 5. 응답 서버도 1~4를 반복
```

<br/>

### 3. Call API
모든 HTTP Client 별 요청은 REST API를 호출하면서 동작을 확인할 수 있도록 작성하였습니다.

- [request-server/http](https://github.com/2dongyeop/spring-cloud-openfeign/tree/main/request-server/http) 디렉토리에 생성된 .http 파일을 이용하면 편리하게 API를 호출할 수 있습니다.

<br/>

### 4. Key Point
- 각 HTTP Client 별 Retry(재시도), 타임아웃(Timeout), 에러 핸들링에 초점을 맞추어 보시길 바랍니다.
- 각 HTTP Client 마다 장단점을 비교해보시길 바랍니다.
  - 의존성을 필요로 하는게 부담스럽지는 않은지
  - 비동기로 동작을 지원하는/지원하지 않는게 현재 자신의 상황에 적합한지
  - JDK/Spring Boot 특정 버전 이상만 사용 가능한 점이 단점은 아닌지
  - 애너테이션으로 선언형을 이용할 경우 얼마나 많은 코드를 줄이고 유연하게 작성할 수 있는지

<br/>

## *Table of Contents.*
- RestTemplate
  - [RestTemplate 소개](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#7b15248ef9444df7a8b52c0edeec84ee)
  - [RestTemplate 기능 및 특징](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#48b14208aca74c6486a701afefca0373)
  - [RestTemplate 활용 코드](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#d325da8293b34e2ab1624d3c7f769b91)
- WebClient
  - [RestTemplate 비교](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#ea9d1caf3156426088b8905a6c168319)
  - [WebClient 특징](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#c865afbf43534041b2a3c4238f2bf132)
  - [WebClient 활용 코드](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#ba54484284564b329c498e2c7986caac)
    - 의존성 추가
    - 로그 레벨 수정
    - 설정 클래스 작성
    - 실제 사용 코드
    - WebClient 단점
- RestClient
  - [RestClient 소개 및 특징](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#3b795f9fd62c4b58b76f1cc21e3a62ff)
  - [RestClient 활용 코드](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#e1d6f4b75ae24468ad2c9431a62c081b)
    - 의존성 추가
    - 로그 레벨 수정
    - 재시도(Retry) 구현
    - 설정 클래스 작성
    - 실제 사용 코드
    - RestClient 단점
- HttpInterface
  - [HttpInterface 소개 및 특징](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#21c7c94b23bd41f1b7da0051bb8a4d9a)
  - [HttpInterface 활용 코드](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#3841f6c4152e40a79ad27901293c2461)
    - 의존성 추가
    - 로그 설정
    - HttpInterface 정의
    - HttpInterface Custom Builder 작성
    - 실제 사용 코드
    - HttpInterface 단점
- OpenFeign
  - [OpenFeign 소개](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#aa940c22717845a0bc822f0bc1c9ffa1)
  - [OpenFeign 기능](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#3401e152bffa4494814cf9a966209cf9)
  - [OpenFeign 활용 코드](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#27947a2e53c048a78ed9c56ed8601043)
    - 의존성 추가
    - Feign Client 활성화 및 정의
    - 타임아웃, 로그 설정
    - 재시도(Retry) 설정
    - RequestInterceptor 작성
    - Error Handling 설정
  - [OpenFeign 장점 및 기능 정리](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#f54ab5794b9f4d35b62a0f4bbc9380d7)
- [참고 자료](https://www.notion.so/leedongyeop/Spring-Boot-OpenFeign-with-Rest-Client-1637623dff4447f3907d59f9811f8803#75aef747be8148238d784dd2937dd01e)