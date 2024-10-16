
```mermaid
sequenceDiagram
    participant User as 사용자
    participant Browser as 브라우저
    participant Server as 서버
    participant DB as 데이터베이스
    
    User->>Browser: 북마크 삭제 요청
    Browser->>Server: 북마크 삭제 요청
    Server->>DB: 북마크 삭제 요청
    alt 유저 존재 X
        Server->>Browser: 북마크 삭제 실패
        Browser->>User: 북마크 삭제 실패
    else 유저 존재 O
        DB->>Server: 삭제 완료
        Server->>Browser: 삭제 응답
        Browser->>User: 화면 리다이렉트
    end
``` 
### 제한사항

#### categoryId 제한사항:
- 값이 비어있으면 안 됨 (@NotEmpty): 값이 반드시 존재해야 하며, 빈 값이나 null은 허용되지 않음.
- 1 이상의 값이어야 함 (@Min(value = 1)): 최소 값이 1이어야 하며, 1 미만의 값은 허용되지 않음.
#### userId 제한사항:
- 값이 비어있으면 안 됨 (@NotEmpty): 값이 반드시 존재해야 하며, 빈 값이나 null은 허용되지 않음.
- 1 이상의 값이어야 함 (@Min(value = 1)): 최소 값이 1이어야 하며, 1 미만의 값은 허용되지 않음.


## 변경예정
- [ ] 인증/인가 구현시 로직 변경