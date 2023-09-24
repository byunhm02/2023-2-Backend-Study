# backend-week2

## HTTP란?

<aside>
💡 ‘HyperText Transfer Protocol’ , 서로 다른 시스템들 사이에서 통신을 주고 받게 해주는 application계층의 protocol. 즉, 인터넷에서 HTML과 같은 문서를 사용자 컴퓨터에 설치된 웹 브라우저가 웹 서버에 요청할 때의 규칙. HTTP서버가 80번 포트에서 요청을 기다리고 있으며 클라이언트는 80번 포트로 요청을 보내게 됨.

</aside>

- HTTP는 HTTP message 를 통해 데이터를 주고 받을 수 있음.
- HTTP 메시지에는 request(요청)과 response(응답)이 있음.

### HTTP특징

1. Transfer layer
    - TCP/IP기반으로 서버와 클라이언트 간의 요청과 응답을 전송함.
2. 비연결성(connectionless)
    - 클라이언트와 서버가 한번 연결을 맺은 후에 클라이언트의 요청에 대해 서버가 응답을 마치면 연결을 끊어버림.
3. 무상태성(stateless)
    - Connectionless로 인해 서버가 두 요청 간의 어떠한 데이터도 유지하지 않음.
    - 즉, 서버는 클라이언트와 연결에 대한 정보를 저장하지 않음. 따라서 서버는 클라이언트를 식별하지 못함.

### HTTP 동작과정

서버접속→클라이언트→요청→서버→응답→클라이언트→연결 종료

1. 사용자가 웹 브라우저에 URL주소 입력
2. DNS 서버에 웹 서버의 호스트 이름을 IP주소로 변경 요청
3. 웹 서버와 TCP 연결 시도 . TCP연결을 열어줌. 새연결 혹은 기존 연결을 재 사용 가능
4. HTTP메시지를 전송함
    
    ```
    - HTTP Request Message = Request Header + 빈 줄 + Request Body
    - Request Header
        - 요청 메소드 + 요청 URI + HTTP 프로토콜 버전
            - GET /background.png HTTP/1.0 POST / HTTP 1.1
            - Header 정보(key-value 구조)
    - 빈 줄
        - 요청에 대한 모든 메타 정보가 전송되었음을 알리는 용도
    - Request Body
        - GET, HEAD, DELETE, OPTIONS처럼 리소스를 가져오는 요청은 바디 미포함
        - 데이터 업데이트 요청과 관련된 내용 (HTML 폼 콘텐츠 등)
    ```
    
5. 서버가 클라이언트에서 데이터 응답, 서버가 보낸 응답을 읽음
    
    ```
    - HTTP Response Message = Response Header + 빈 줄 + Response Body
    - Response Header
        - HTTP 프로토콜 버전 + 응답 코드 + 응답 메시지
             - ex. HTTP/1.1 404 Not Found.
        - Header 정보(key-value 구조)
    - 빈 줄
        - 요청에 대한 모든 메타 정보가 전송되었음을 알리는 용도
    - Response Body
        - 응답 리소스 데이터
             - 201, 204 상태 코드는 바디 미포함
    ```
    
6. 연결을 닫거나 다른 요청을 위해 재 사용

![연결 및 메시지 전송](https://user-images.githubusercontent.com/128574532/270153103-79d5dcc6-f6e9-4b99-8f9e-d91dfdd7d717.png
)

연결 및 메시지 전송

![연결 종료](https://user-images.githubusercontent.com/128574532/270153096-75cfa33c-33f2-4897-a385-c76621faa49d.png)

연결 종료

### HTTP request(요청)에 들어있는 요소

![Untitled](https://user-images.githubusercontent.com/128574532/270153098-8a5a1cb6-a11d-428e-937f-c75c7d44b6a5.png)

1. HTTP버전 유형
2. URI
3. HTTP Method
    - HTTP요청이 쿼리된 서버에서 기대하는 작업.
    
    ![Untitled](https://user-images.githubusercontent.com/128574532/270153099-9517250e-d62d-4eef-99fe-f73d461f2c25.png)
    
    - GET: 리소스 조회
    - POST: 요청 데이터 처리, 주로 등록에 사용
    - PUT: 리소스를 대체, 해당 리소스가 없으면 생성
    - PATCH: 리소스 부분 변경
    - DELETE: 리소스 삭제
    - HEAD: GET과 동일하지만 메시지 부분을 제외하고, 상태 줄과 헤더만 반환
    - OPTIONS: 대상 리소스에 대한 통신 가능 옵션(메서드)을 설명
    - CONNECT: 대상 리소스로 식별되는 서버에 대한 터널을 설정
    - TRACE: 대상 리소스에 대한 경로를 따라 메시지 루프백 테스트를 수행
4. HTTP 요청 헤더
    - HTTP헤더에는 키 값 쌍에 저장된 텍스트 정보가 포함됨.
    - 헤더는 모든 HTTP요청(및 응답)에 포함됨.
    - 헤더는 클라이언트가 사용하는 브라우저 및 요청되는 데이터와 같은 핵심 정보를 전달함.
    
    ![Google Chrome의 네트워크 탭에 있는 HTTP요청 헤더](https://user-images.githubusercontent.com/128574532/270153100-4ea9b326-b81e-4b99-916a-e122e498116e.png)
    
    Google Chrome의 네트워크 탭에 있는 HTTP요청 헤더
    
5. 선택 사항인 HTTP 본문
    - 요청의 본문은 요청에서 전송되는 정보의 본문을 포함하는 부분
    - 사용자 이름 및 비밀번호 또는 양식에 입력된 기타 데이터와 같이 웹 서버에 제출되는 모든 정보가 포함됨.

### HTTP response(응답)에 들어있는 요소

HTTP응답은 웹 클라이언트(종종 브라우저)에서 HTTP요청에 대한 응답으로 인터넷 서버로부터 수신하는 응답. 

![Untitled](https://user-images.githubusercontent.com/128574532/270153101-09861329-e182-4b3a-b49f-b995eea7b018.png)

1. HTTP상태 코드
    - 서버 측에서 클라이언트로 요청에 대한 응답을 보낼 때, 해당 요청에 관한 처리 상태를 알려주는 기능.
        - 1xx (informational) :요청이 수신되어 처리 중
        - 2xx (successful) : 요청 정상 처리
            - 200: 클라이언트의 요청을 정상적으로 수행함
            - 201: 클라이언트가 어떤 리소스 생성을 요청, 해당 리소스가 성공적으로 생성됨(POST를 통한 리소스 생성 작업시)
            - 204: 클라이언트가 어떤 리소스 삭제를 요청, 해당 리소스가 성공적으로 삭제됨
        - 3xx (redirection) : 요청을 완료하려면 추가 행동 필요
        - 4xx (client error) : 클라이언트 오류, 잘못된 문법 등으로 서버가 요청을 수행 할 수 없음
            - 400: 클라이언트의 요청이 부적절한 경우
            - 401: 클라이언트가 인증되지 않은 상태에서 보호된 리소스를 요청했을 경우
            - 403: 유저 인증 상태와 관계 없이 응답하고 싶지 않은 리소스를 클라이언트가 요청했을 때 사용하는 코드
            - 404: 클라이언트가 요청한 리소스에서는 사용 불가능한 Method를 이용했을 경우
        - 5xx (server error): 서버 오류, 서버가 정상 요청을 처리하지 못함
            - 500: 서버에 문제가 있을 경우
            - 502: 게이트웨이 오류
2. HTTP 응답 헤더
    - HTTP요청과 마찬가지로 HTTP응답에는 응답 본문에서 전송되는 데이터의 언어 및 형식과 같은 중요한 정보를 전달하는 헤더가 함께 제공됨.
    
    ![Google Chrome네트워크 탭에 있는 HTTP응답 헤더의 예](https://user-images.githubusercontent.com/128574532/270153102-eab9a55e-cae5-43ae-a64f-e216f5140929.png)
    
    Google Chrome네트워크 탭에 있는 HTTP응답 헤더의 예
    
3. 선택사항인 HTTP본문
    - ‘GET’요청에 대한 성공적인 HTTP응답에는 일반적으로 요청된 정보가 포함된 본문이 존재함. 대부분의 웹 요청의 경우 이는 웹 브라우저에서 웹 페이지로 변환되는 HTML 데이터.

## HTTPS란?

<aside>
💡 ‘하이퍼텍스트 전송 프로토콜 보안(HTTPS)’은 웹 브라우저와 웹 사이트 간에 데이터를 전송하는 데 사용되는 기본 프로토콜인 HTTP의 보안 버전. 전송의 보안을 강화하기 위해 암호화됨.  SSL(Secure Socket Layer)이라는 프로토콜을 사용해 주고 받는 정보를 암호화함. SSL은 TLS(Transport Layer Security)로 발전해 현재는 SSL/TLS 라는 단어를 혼용해 사용함.  443번 포트를 사용함.

</aside>

- 모든 웹사이트, 특히 로그인 자격 증명이 필요한 웹 사이트는 HTTPS를 사용해야 함.

### 암호화

1. 대칭키 암호화
    - 클라이언트와 서버가 동일한 키를 사용해 암호화/복호화 진행
    - 키가 노출되면 매우 위험하지만 연산 속도가 빠름
2. 비대칭키 암호화
    - 1개의 쌍으로 구성된 공개키와 개인키를 암호화/복호화 하는데 사용
    - 키가 노출되어도 비교적 안전 하지만 연산 속도가 느림
    1. 개인키(비공개키,private key)
        - 웹 사이트 소유자가 관리
        - 비공개로 유지
        - 웹 서버에 있으며 공개 키로 암호화된 정보를 해독하는 데 사용
    2. 공개키(public key)
        - 안전한 방식으로 서버와 상호작용 하고자 하는 모든 사람이 사용할 수 있음.
        - 공개키로 암호화된 정보는 개인 키로만 해독 가능

### HTTPS의 동작 과정

HTTPS는 대칭 키 암호화와 비대칭 키 암호화를 모두 사용하여 빠른 연산 속도와 안정성을 모두 얻고 있음. HTTPS 연결 과정(Hand-Shaking)에서는 먼저 서버와 클라이언트 간에 세션키를 교환함. 세션키는 주고 받는 데이터를 암호화 하기 위해 사용되는 대칭키이며 데이터 간의 교환에는 빠른 연산 속도가 필요하므로 세션키는 대칭키로 만들어짐. 

세션키를 클라이언트와 서버가 교환하는 과정에서 비대칭 키가 사용됨. 

- 실제 HTTPS 연결 과정이 성립되는 흐름
    1. 클라이언트(브라우저)가 서버로 최초 연결 시도 함
    2. 서버는 공개키(인증서) 를 브라우저에게 넘겨줌
    3. 브라우저는 인증서의 유효성을 검사하고 세션키를 발급함
    4. 브라우저는 세션 키를 보관하며 추가로 서버의 공개키로 세션키를 암호화하여 서버로 전송함
    5. 서버는 개인키로 암호화된 세션키를 복호화하여 세션키를 얻음
    6. 클라이언트와 서버는 동일한 세션키를 공유하므로 데이터를 전달 할 때 세션키로 암호화/복호화를 진행함

## HTTP vs HTTPS

- HTTP와 비교했을 때 HTTPS의 장점
    - 보안
        - HTTP메시지는 일반 텍스트인 반면, HTTPS는 모든 데이터를 암호화 된 형태로 전송하기 때문에 제 3자가 네트워크를 통해 해당 데이터를 가로챌 수 없음.
    - 권위
        - 검색 엔진은 HTTP의 신뢰성이 더 낮기 때문에 보통 HTTP웹 사이트 콘텐츠 순위를 HTTPS웹 페이지보다 낮게 지정함. 브라우저는 브라우저 주소 표시줄에서 웹 사이트 URL옆에 있는 자물쇠 아이콘을 배치하여 사용자에게 HTTPS연결을 표시함.
    - 성능 및 분석
        - HTTPS웹 어플리케이션은 HTTP 어플리케이션보다 로드 속도가 더 빠름.
- 차이점 요약
    
    
    |  | HTTP | HTTPS |
    | --- | --- | --- |
    | 본말 | Hyper Transfer Protocol | Hyper Transfer Protocol Secure |
    | 기본 프로토콜 | HTTP/1과 HTTP/2는 TCP/IP를 사용. HTTP/3은 QUIC프로토콜을 사용 | HTTP요청 및 응답을 추가로 암호화 하기 위해 SSL/TLS와 함께 HTTP/2사용 |
    | 포트 | 기본 포트 80 | 기본 포트 443 |
    | 용도 | 이전 텍스트 기반 웹 사이트 | 모든 최신 웹 사이트 |
    | 보안 | 추가 보안 기능 없음 | 퍼블릭 키 암호화에 SSL인증서 사용 |
    | 장점 | 인터넷을 통한 통신 지원 | 웹 사이트에 대한 권위, 신뢰성 및 검색 엔진 순위 개 |

## RESTful한 URI설계

- 이벤트 목록 조회
    
    ```java
    GET /events
    ```
    
- 이벤트 조회
    
    ```java
    GET /events/{eventsId}
    ```
    
- 이벤트 등록
    
    ```java
    POST /events
    ```
    
- 이벤트 수정
    
    ```java
    PUT /events/{eventsId}
    ```
    
- 이벤트 삭제
    
    ```java
    DELETE /events/{eventsId}
    ```
    
- 이벤트 상태 변경
    
    ```java
    PATCH /events/{eventsId}/status
    ```
    
- 특정 이벤트의 주문 목록 조회
    
    ```java
    GET /events/{eventsId}/orders
    ```
    
- 멤버 목록 조회
    
    ```java
    GET /members
    ```
    
- 특정 멤버 권한 변경
    
    ```java
    PATCH /members/{memberId}/permissons
    ```
    
- 특정 멤버 정보 조회
    
    ```java
    GET /members/{memberId}
    ```
    
- 특정 멤버 정보 변경
    
    ```java
    PUT /members/{memberId}
    ```
    
- 멤버 등록
    
    ```java
    POST /members
    ```
