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

## *Quick Start*
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