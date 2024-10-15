import requests
import json
base = "http://localhost:8080"
userUrl = base + "/user"
categoryUrl = base + "/category"
bookmarkUrl = base + "/bookmark"
email = "email@naver.com"
userName = "test"
password = "1234"
def 회원가입():
    global userUrl
    global email
    global userName
    global password

    response = requests.post(userUrl, json={
        "userName" : userName,
        "password" : password,
        "email" : email
    })
    if(response.status_code != 200):
        json = response.json()
        print('실패')
        return
    print(response.json())

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
    print(response.json())

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
    print(response.json())

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

def 내_북마크들_생성(userId, categoryId):
    for _ in range(1, 50):
        내_북마크_생성(userId, categoryId)

def 출력(methodName):
    print("========= "+ methodName + " ===========")
def start():
    userId = 1
    categoryId = 1
    bookmarkId = 1
    # 회원가입()
    # 로그인()

    print("========= 내_카테고리_생성 ===========")
    출력(내_카테고리_생성)
    내_카테고리_생성(userId)
    print("========= 내_카테고리들_가져오기 ===========")
    내_카테고리들_가져오기(userId)
    print("========= 내_카테고리_수정 ===========")
    내_카테고리_수정(userId, categoryId)
    print("========= 내_북마크들_생성 ===========")
    내_북마크들_생성(userId, categoryId)
    print("========= 내_북마크들_읽기 ===========")
    내_북마크들_읽기(userId, categoryId)
    print("========= 내_북마크_수정 ===========")
    내_북마크_수정(userId, bookmarkId)

start()
