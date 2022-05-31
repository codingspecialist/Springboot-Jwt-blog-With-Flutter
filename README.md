# 스프링부트 JWT 블로그 서버
- 플러터와 연동하기 위해 만듬.
- H2 데이터베이스가 내장되어 있음. (따로 설치 필요 없음)
- Tomcat 서버가 내장되어 있음. (따로 설치 필요 없음)
- 포트번호 8080 -> react의 3000번 포트로 요청하면 reverse-proxy 작동하게 개발모드에서만 세팅되어있음.

### 로그인, 회원정보보기 사용법
- 로그인하면 redux로 로그인 정보 상태 관리함
- localStorage에 JWT 담고
- sessionStorage가 아닌 Redux에 로그인 정보 상태 관리중 (useSelector로 호출가능)

### 초기 데이터
```json
// 20210710075825
// http://localhost:8080/init/user

{
  "code": 1,
  "msg": "목록보기완료",
  "data": [
    {
      "id": 2,
      "username": "cos",
      "password": "1234",
      "email": "cos@nate.com",
      "created": "2021-07-10T07:56:50.198496",
      "updated": "2021-07-10T07:56:50.198496"
    },
    {
      "id": 1,
      "username": "ssar",
      "password": "1234",
      "email": "ssar@nate.com",
      "created": "2021-07-10T07:56:50.18751",
      "updated": "2021-07-10T07:56:50.18751"
    }
  ]
}
```

```json
// 20210710075847
// http://localhost:8080/init/post

{
  "code": 1,
  "msg": "목록보기완료",
  "data": [
    {
      "id": 5,
      "title": "제목5",
      "content": "내용5",
      "user": {
        "id": 2,
        "username": "cos",
        "password": "1234",
        "email": "cos@nate.com",
        "created": "2021-07-10T07:56:50.198496",
        "updated": "2021-07-10T07:56:50.198496"
      },
      "created": "2021-07-10T07:56:50.207467",
      "updated": "2021-07-10T07:56:50.207467"
    },
    {
      "id": 4,
      "title": "제목4",
      "content": "내용4",
      "user": {
        "id": 2,
        "username": "cos",
        "password": "1234",
        "email": "cos@nate.com",
        "created": "2021-07-10T07:56:50.198496",
        "updated": "2021-07-10T07:56:50.198496"
      },
      "created": "2021-07-10T07:56:50.207467",
      "updated": "2021-07-10T07:56:50.207467"
    },
    {
      "id": 3,
      "title": "제목3",
      "content": "내용3",
      "user": {
        "id": 1,
        "username": "ssar",
        "password": "1234",
        "email": "ssar@nate.com",
        "created": "2021-07-10T07:56:50.18751",
        "updated": "2021-07-10T07:56:50.18751"
      },
      "created": "2021-07-10T07:56:50.206474",
      "updated": "2021-07-10T07:56:50.206474"
    },
    {
      "id": 2,
      "title": "제목2",
      "content": "내용2",
      "user": {
        "id": 1,
        "username": "ssar",
        "password": "1234",
        "email": "ssar@nate.com",
        "created": "2021-07-10T07:56:50.18751",
        "updated": "2021-07-10T07:56:50.18751"
      },
      "created": "2021-07-10T07:56:50.206474",
      "updated": "2021-07-10T07:56:50.206474"
    },
    {
      "id": 1,
      "title": "제목1",
      "content": "내용1",
      "user": {
        "id": 1,
        "username": "ssar",
        "password": "1234",
        "email": "ssar@nate.com",
        "created": "2021-07-10T07:56:50.18751",
        "updated": "2021-07-10T07:56:50.18751"
      },
      "created": "2021-07-10T07:56:50.205475",
      "updated": "2021-07-10T07:56:50.205475"
    }
  ]
}
```

### 사용법 및 API 문서
- https://blog.naver.com/getinthere/222426449415

