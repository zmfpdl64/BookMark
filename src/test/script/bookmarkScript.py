import webbrowser, requests, random, json, sys
input = sys.stdin.readline


base = "http://localhost:8080"
userUrl = base + "/user"
categoryUrl = base + "/category"
bookmarkUrl = base + "/bookmark"
googleUrl = "https://accounts.google.com/o/oauth2/v2/auth"
redirectUrl = "http://localhost:8080/user/google/callback"
clientId = "227716410232-dkede9udod9f1vs33tbk6g360dmpegv6.apps.googleusercontent.com"

email = "email@naver.com"
userName = "test"
password = "1234"
changePassword = "change1234"
rand = ""

flag = False
oAuthEmail = ""
OAuthuserName = ""
OAuthpassword = ""


def 회원가입():
    global userUrl
    global email
    global userName
    global password
    global rand
    rand = str(random.random())
    response = requests.post(userUrl, json={
        "userName" : userName,
        "password" : password,
        "email" : email + rand
    })
    if(response.status_code != 200):
        json = response.json()
        print('실패')
        return
    print(response.json())
    return response.json().get("id") 

def 로그인():

    global email
    global password

    response = requests.get(userUrl, params = {

        "email" : email,
        "password" : password
    })
    if(response.status_code != 200):
        return
    print(response.json())

def 구글로그인테스트():
    # 요청에 필요한 URL 및 파라미터

    global googleUrl, redirectUrl, clientId

    # 파라미터 설정
    params = {
        "scope": "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email",
        "access_type": "online",
        "include_granted_scopes": "true",
        "response_type": "code",
        "redirect_uri": redirectUrl,
        "client_id": clientId
    }

    # 브라우저로 요청 URL 열기
    request_url = requests.Request('GET', googleUrl, params=params).prepare().url
    print("브라우저에서 로그인:", request_url)

    # 브라우저에서 열기
    webbrowser.open(request_url)

def 회원정보_수정(userId):
    global userName
    global userUrl
    global email
    global password
    global rand
    global changePassword
    response = requests.put(userUrl, json = {
        "userId": userId,
        "email" : email + rand,
        "userName": userName,
        "beforePassword" : password,
        "changePassword": changePassword,
    })
    if(response.status_code != 200):
        return
    print(response.json())

def 회원탈퇴(userId):
    global userUrl
    global email
    global userName
    global changePassword
    global rand
    response = requests.delete(userUrl, json={
        "userId": userId,
        "email" : email + rand,
        "password": changePassword
    })
    if(response.status_code != 200):
        json = response.json()
        print('실패')
        return
    print(response.json())

def 내_카테고리들_가져오기(userId):
    global categoryUrl
    url = categoryUrl + "/" + str(userId)
    response = requests.get(url)
    data = response.json()
    pretty_json = json.dumps(data, indent=4, ensure_ascii=False)
    print(pretty_json)

def 내_카테고리_생성(userId):
    global categoryUrl
    title = "인터넷"
    data = {
        "title": title,
        "userId": userId
    }
    response = requests.post(categoryUrl, json=data)
    if (response.status_code != 200):
        print('실패')
        return
    dataJson = response.json()
    print(dataJson)
    return dataJson.get("id")

def 내_카테고리_수정(userId, categoryId):
    global categoryUrl
    title = "인터넷 수정"
    data = {
        "title": title,
        "categoryId": categoryId,
        "userId": userId
    }
    response = requests.put(categoryUrl, json=data)
    if (response.status_code != 200):
        print('실패')
        return
    print(response.json())

def 내_카테고리_삭제(userId, categoryId):
    global categoryUrl
    title = "인터넷 수정"
    data = {
        "userId": userId,
        "categoryId": categoryId
    }
    response = requests.delete(categoryUrl, json=data)

    if (response.status_code != 200):
        print('실패')
        return
    print(response.json())

def 내_북마크_생성(userId, categoryId):
    global bookmarkUrl
    title = "구글"
    link = "https://google.co.kr"
    data = {
        "title": title,
        "link": link,
        "userId": userId,
        "categoryId": categoryId
    }
    response = requests.post(bookmarkUrl, json=data)
    if (response.status_code != 200):
        print('실패')
        return
    data = response.json()
    print(data)
    return data.get("id")

def 내_북마크들_읽기(userId, categoryId):
    global bookmarkUrl
    userId = 1
    categoryId = 1
    response = requests.get(bookmarkUrl, params={
        "userId": userId,
        "categoryId": categoryId
    })
    if (response.status_code != 200):
        print('실패')
        return
    print(json.dumps(response.json(), indent=2, ensure_ascii=False))

def 내_북마크_수정(userId, bookmarkId):
    global bookmarkUrl
    title = "수정된 구글"
    link = "https://google.com"
    response = requests.put(bookmarkUrl, json={
        "userId": userId,
        "bookmarkId": bookmarkId,
        "title" : title,
        "link" : link
    })
    if (response.status_code != 200):
        print('실패')
        return
    print(json.dumps(response.json(), indent=2, ensure_ascii=False))

def 내_북마크_삭제(userId, bookmarkId):
    global bookmarkUrl
    data = {
        "userId": userId,
        "bookmarkId": bookmarkId
    }
    response = requests.delete(bookmarkUrl, json=data)
    if (response.status_code != 200):
        print('실패')
        return
    print(json.dumps(response.json(), indent=2, ensure_ascii=False))

def 내_북마크들_생성(userId, categoryId):
    data = []
    for _ in range(1, 10):
        data.append(int(내_북마크_생성(userId, categoryId)))
    return data

def 출력(method):
    methodName = method.__name__
    print("\n\n========= "+ methodName + " ===========")

def 구글유저테스트데이터주입(userId):
    출력(내_카테고리_생성)
    categoryId = int(내_카테고리_생성(userId))
    내_북마크들_생성(userId, categoryId)
    categoryId2 = int(내_카테고리_생성(userId))
    내_북마크들_생성(userId, categoryId2)
    categoryId3 = int(내_카테고리_생성(userId))
    내_북마크들_생성(userId, categoryId3)


def start():
    # global userId
    출력(회원가입)
    userId = int(회원가입())
    # 로그인()
    print(f'유저 id: {userId}')
    출력(회원정보_수정)
    회원정보_수정(userId)

    출력(내_카테고리_생성)
    categoryId = int(내_카테고리_생성(userId))
    categoryId2 = int(내_카테고리_생성(userId))
    내_북마크들_생성(userId, categoryId2)
    categoryId3 = int(내_카테고리_생성(userId))
    내_북마크들_생성(userId, categoryId3)


    출력(내_카테고리들_가져오기)
    내_카테고리들_가져오기(userId)

    출력(내_카테고리_수정)
    내_카테고리_수정(userId, categoryId)

    출력(내_북마크들_생성)
    bookmarkIdList = 내_북마크들_생성(userId, categoryId)
    bookmarkId = bookmarkIdList[0]

    출력(내_북마크들_읽기)
    내_북마크들_읽기(userId, categoryId)

    출력(내_북마크_수정)
    내_북마크_수정(userId, bookmarkId)

    출력(내_북마크_삭제)
    내_북마크_삭제(userId, bookmarkId)

    출력(내_카테고리_삭제)
    내_카테고리_삭제(userId, categoryId)

    # 출력(회원탈퇴)
    # 회원탈퇴(userId)

while True:
    print("시나리오 선택지를 입력하시오")
    print("1. Google OAuth 시나리오 선택시 1을 입력해주세요.")
    print("2. 일반 인증/인가 시나리오 선택 시 2를 입력해주세요")
    print("3. 유저 목데이터 주입 선택 시 3를 입력해주세요")
    print("4. 종료 시 q를 입력해주세요")
    num = input().rstrip()
    if num=="1":
        구글로그인테스트()
        print("실행")
    elif num=="2":
        start()
        print("실행")
    elif num=="3":
        print("유저의 ID를 입력해주세요: ")
        userId = input().rstrip()
        구글유저테스트데이터주입(userId)
    elif num=='q':
        print("종료")
        break
    else:
        print("다시 입력해주세요")


