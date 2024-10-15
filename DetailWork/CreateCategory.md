```mermaid
sequenceDiagram
    participant User as 사용자
    participant Browser as 브라우저
    participant Server as 서버
    participant DB as 데이터베이스

    User->>Browser: 카테고리 생성 정보 입력
    Browser->>Server: 카테고리 생성 요청
    Server->>DB: 카테고리 생성 요청
    alt 유저 존재 X
        Server->>Browser: 인증 실패(미구현)
        Browser->>User: 카테고리 생성 실패
    else 유저 존재 O
        DB->>Server: 저장 완료
        Server->>Browser: 생성 응답
        Browser->>User: 화면 리다이렉트
    end 
``` 

### 제한사항
1. title
   제약 조건:
   - @Size(min = 0, max = 255): 길이는 0에서 255 바이트 사이여야 함.
   메시지: "카테고리명 길이는 0 ~ 255byte로 작성해주세요"
   - @NotBlank: 빈 문자열이 아니어야 함.
   메시지: "카테고리명을 입력해주세요"
2. userId
   제약 조건:
   - @NotEmpty: 값이 비어있으면 안됨.
   메시지: "값을 추가해주세요"
   - @Min(1): 값은 1 이상이어야 함.
   메시지: "1이상의 유저 아이디를 입력해주세요"
## 변경예정
- [ ] 인증/인가 구현 시 적용 