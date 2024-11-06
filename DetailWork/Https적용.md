### 사이트
https://riverandeye.tistory.com/entry/4-Nginx%EC%97%90-HTTPSSSL-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0

### certbot 설치 Lets Encrypt
```bash
sudo apt-get update
sudo apt-get install software-properties-common
sudo add-apt-repository universe
sudo add-apt-repository ppa:certbot/certbot
sudo apt-get update
sudo apt-get install certbot -y
```
### 인증서 발급
sudo certbot certonly --standalone

실제 코드


- 파일명: 도메인.conf[kbookmark.co.kr.conf]
- 저장경로: /etc/nginx/conf.d/
- 모든경로: /etc/nginx/conf.id/kbookmark.co.kr.conf

```
server {
    listen 443 ssl;

    root /var/www/html/frontui;
    index index.html index.htm;

    server_name kbookmark.co.kr www.kbookmark.co.kr;
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    ## 인증서
    ssl_certificate /etc/letsencrypt/live/kbookmark.co.kr/fullchain.pem;
    ## 인증서 키
    ssl_certificate_key /etc/letsencrypt/live/kbookmark.co.kr/privkey.pem;

    location / {
        proxy_pass http://127.0.0.1:3000/;  # 슬래시 추가
    }
    location /api/ {
        proxy_pass http://127.0.0.1:8080/;  # 백엔드 서버의 8080번 포트로 리버스 프록시
        #뒤에 포트 이후의 Path를 포함하여 서버에게 요청합니다. 그렇기에 다른 정보를 추가하지 않으셔도 됩니다.
    }
}

server {
    listen 80;

    server_name kbookmark.co.kr www.kbookmark.co.kr;
    location / {
        return 301 https://$host$request_uri;
    }
}

```
conf로 확장자를 붙히는 이유는 /etc/nginx/nginx.conf 파일에서 /etc/nginx/conf.d/*.conf 파일을 모두 불러오기 때문입니다.

그리고 위에는 프론트, 백엔드 모두 SSL을 적용해주었습니다. 그러다 보니
url로 분리해줄 필요가 있었습니다. 그래서 api 경로를 통해 분리해주었습니다. 해당 경로를 나타내는 /api/는
요청 url에서 api를 제거해주고 다시 proxy_pass로 요청을 전달해주는 것입니다. 그렇기에 실제 백엔드 서버에서 맵핑해줄때는 api코드를 경로에 안적어주어도 됩니다.
### 프론트 엔드 정적 파일 요청할 때
```commandline
https://kbookmark.co.kr
```

### 프론트에서 백엔드 서버로 요청해야할 때

```
https://kbookmark.co.kr/api/xxxx
```
conf파일에서 api경로를 통해 분리해주었기 때문에 서버에 요청할때는 위와같이 요청해야합니다.
또한 Cors에러가 뜰 수 있는데 이것을 백엔드 서버에서 https://kbookmark.co.kr 도메인을 허용해주어야 합니다.


## 전체적인 플로우

### 브라우저에서 프론트 서버로 요청:
사용자가 https://kbookmark.co.kr로 접속을 시도하면, 브라우저는 DNS 서버에 요청해 kbookmark.co.kr의 IP 주소를 가져옵니다.

### 도메인 서버에서 IP 변환:
도메인 네임 시스템(DNS) 서버는 kbookmark.co.kr을 IP 주소로 변환하여 브라우저에 제공합니다.

### Nginx의 443번 포트 리다이렉트:
만약 브라우저가 http로 요청을 보낸다면, Nginx는 HTTP 요청을 HTTPS로 리다이렉트(301 Redirect) 하여 https://kbookmark.co.kr로 유도합니다.

### SSL 인증 처리:
HTTPS로 연결 시도 시 Nginx는 SSL 인증서와 인증키를 사용해 연결을 암호화합니다. 이후, 프록시 설정을 통해 프론트엔드 서버로 요청을 전달합니다.

### 프론트 서버에서 정적 파일 반환:
프론트엔드 서버는 정적 리소스(HTML, CSS, JavaScript)를 포함한 필요한 응답을 브라우저로 반환합니다.

### 프론트 서버에서 백엔드 서버로 API 요청:
클라이언트의 자바스크립트가 https://kbookmark.co.kr/api/bookmark 같은 API 요청을 프론트 서버에 보냅니다.

### Nginx에서 API 경로 처리:
Nginx는 /api/bookmark에 대한 요청을 백엔드 서버로 전달합니다. 	url은 https://kbookmark.co.kr/api/bookmark 요청을 -> https://kbookmark.co.kr/bookmark로 변환해서 다시 요청합니다.


### 백엔드 서버에서 데이터 처리 후 응답 반환:
백엔드 서버는 클라이언트 요청을 처리하고, 결과를 JSON 등으로 응답합니다. Nginx는 백엔드의 응답을 그대로 프론트엔드로 전달하여 최종적으로 사용자가 결과를 보게 됩니다.
