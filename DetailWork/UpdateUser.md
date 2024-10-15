```mermaid
sequenceDiagram
    participant User as 사용자
    participant Browser as 브라우저
    participant Server as 서버
    participant DB as 데이터베이스
    
    User->>Browser: 유저 수정 정보 입력
    Browser->>Server: 유저 수정 요청
    Server->>DB: 유저 수정 요청
    alt 유저 존재 X
        Server->>Browser: 유저 수정 실패
        Browser->>User: 유저 수정 실패
    else 유저 검증 X
        Server->>Browser: 유저 수정 실패
        Browser->>User: 유저 수정 실패
    else 유저 존재 O
        DB->>Server: 저장 완료
        Server->>Browser: 수정 응답
        Browser->>User: 화면 리다이렉트
    end
``` 
### 제한사항
#### email:
- 공백일 수 없음.
- test@email.com 형식의 이메일 주소여야 함.
#### beforePassword (현재 비밀번호):
- 공백일 수 없음.
- 비밀번호를 반드시 입력해야 함.
#### changePassword (바꿀 비밀번호):
- 공백일 수 없음.
- 비밀번호를 반드시 입력해야 함.
#### userName (사용자 이름):
- 공백일 수 없음.
- 사용하실 이름을 반드시 작성해야 함.
#### userId (유저 아이디):
- 공백일 수 없음.
- 값이 반드시 있어야 하며, 1 이상의 정수여야 함 

## 변경예정
- [ ] 인증/인가 구현 시 적용 