# 보여줘 왓슨
<div align="center">
<img src="readme_assets/app_icon.png"/>
</div>

### 개발기간  
2023.07.04 - 2023.08.18
### 주제
자취 등 집을 구할 때, 겪는 시,공간적  제약을 벗어나기 위해 부동산 업자가 직접 Live 방송을 통해 매물을 소개해줄 수 있는 부동산 모바일 앱

#### 사용 기술 스택

<div align="center">
<br>
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
</br>

<br>
<img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
<img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">
<img src="https://img.shields.io/badge/firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=white">
</br>
<br>
<img src="https://img.shields.io/badge/flutter-02569B?style=for-the-badge&logo=flutter&logoColor=white">
</br>

<br>
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/mongodb-47A248?style=for-the-badge&logo=mongodb&logoColor=white">
<img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
<img src="https://img.shields.io/badge/apachekafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white">
</br>

<br>
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
</br>

<br>
<img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
<img src="https://img.shields.io/badge/androidstudio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white">
</br>
<div>
<img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white">
<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
</div>
</div>

### 발표자료
[ppt](docs/presentation/공통PJT_서울_8반_A803_허주혁.pdf)

</br>
https://youtu.be/E6jkImXQNa8


## 역할

### 프론트
##### 류정모
    - Figma 제작 
    - DM 
    - chatting 일반 유저 페이지 
    - 구글 맵을 통한 매물 정보 제공
    -  매물 관련 정보 필터
##### 허주혁
    - Figma 제작 
    - 로그인 페이지 (중개사, 일반 유저)
    - 매물 작성 페이지
    - 라이브 등록, 목록
    - 스케줄 등록, 목록

<br/>

### 백엔드
##### 김기헌
    - PM
    - 아키텍쳐, 시퀀스 설계
    - 배포 스크립트 작성 
    - 로그 저장, 분석
    - 지역별 주간 랭킹 선정
    - 깃 관리 
    - 코드 리팩토링
##### 박지영
    - Kafka와 FCM을 이용한 Notice 서버 개발
    - Openvidu 서버 구축
    - Redis 서버 구축
    - S3이미지 서버 구축
##### 오채영
    - 매물 CRUD 
    - 매물 필터링 
    - 관심매물 CRUD 
    - 방송 스케줄 게시판 CRUD
##### 이정민
    - 사용자/중개사 정보 CRUD
    - JWT 토큰을 이용한 로그인 및 인가
    - Spring Security

<br/>
<br/>
<br/>

## 협업 환경

### Git으로 협업하기

Git을 통한 협업 방식은 [우아한 형제들 Git Flow](https://techblog.woowahan.com/2553/)를 기본 베이스로 삼았습니다.

브랜치는 master, develop, dev-front, dev-back, feature 총 5가지를 사용했으며 전략은 다음과 같습니다.

- `master`: 서비스가 출시될 수 있는 브랜치입니다. master 브랜치에 올라온 기능들은 에러 없이 작동하는 상태입니다.
- `dev`: 다음 서비스 출시를 위해 실제 개발이 이루어지는 브랜치입니다.
- `frontend`, `backend`: develop 브랜치에서 분기해서 프론트엔드와 백엔드가 각각 개발하는 브랜치입니다. 프론트엔드와 백엔드 충돌을 최대한 방지하기 위해 만들어졌습니다.
- `feature`: 기능 단위 개발을 위한 브랜치로 dev-front, dev-back에서 분기하여 개발이 끝나면 각각 베이스 브랜치로 병합됩니다.

## 프로젝트 회고
말로만 듣던 MSA구조를 처음 경험하며 설계에서 어려움을 겪어 시간이 많이 지연되기도 했지만 정말 많은 것을 배울 수 있었다. 
Server to Server 통신과 아키텍쳐에서 private과 public 부분을 분리하면서 어떻게 접근하고 통신할지 고려하여 시퀀스를 설계하는 것이 너무 재미있었다.

아쉬웠던 점은 인프라 담당으로서 Jenkins를 통해 CI/CD 계획까지 했지만 Jenkins에서 시간을 너무 많이 소모되어 결국 포기하고 배포 스크립트 파일을 직접 작성해 반자동(?) 배포가 되었다. PM으로서 시간 분배를 잘 하지 못해 시간에 쫒겨 개발하다 보니 기획은 했지만 결국 도입하지 못한 API Gateway나 Load Balancer에 대한 아쉬움이 남는다.
그리고 인원 분배가 잘못되었던 것인지 프론트 담당 2명 모두 Flutter를 처음 접하면서 시간이 너무 부족해 급하게 만들다보니 퀄리티가 부족하게 되었다.
다음에 또 기회가 온다면 그 때는 시간과 인원 분배에 더 신경을 써야 할 것 같다


----
#### ER-Diagram 제작  
![erd](readme_assets/erd.PNG)

#### Architecture
![arch](readme_assets/architecture.png)

#### 명세서
[기능명세서/API명세서](https://docs.google.com/spreadsheets/d/1O8tJik-yb2d7x9Nqm7bvH7MYdZcXFeiq4Sb5f2xZhYU/edit?usp=sharing)
![API1](readme_assets/api1.PNG)

## 정보구조도 
![정보구조도](readme_assets/userIA.png)
![중개사구조도](readme_assets/realtorIA.png)

## Frontend
[figma](https://www.figma.com/file/SOoHTxgMwKQqBppPFFAvHY/%EB%B6%80%EB%8F%99%EC%82%B0-%EC%A4%91%EA%B0%9C-%ED%99%94%EC%83%81-%ED%94%8C%EB%9E%AB%ED%8F%BC-%EC%95%B1-MVP-(1%EC%B0%A8-%EC%99%84%EC%84%B1%EB%B3%B8)-(Copy)?type=design&node-id=111%3A2&mode=design&t=ZmTgbWDQAYHdGAx7-1)

##### 사용자
<div>
<img src="readme_assets/Screenshot_20230818_122901.png" width="200">
<img src="readme_assets/Screenshot_20230818_122937.png" width="200">
<img src="readme_assets/Screenshot_20230818_122957.png" width="200">
<img src="readme_assets/Screenshot_20230818_123012.png" width="200">
<img src="readme_assets/Screenshot_20230818_123020.png" width="200">
</div>

##### 중개자
<div>
<img src="readme_assets/Screenshot_20230818_123219.png" width="200">
<img src="readme_assets/Screenshot_20230818_123101.png" width="200">
<img src="readme_assets/Screenshot_20230818_123126.png" width="200">
<img src="readme_assets/Screenshot_20230818_123200.png" width="200">
<img src="readme_assets/Screenshot_20230818_123210.png" width="200">
</div>



-----



<!-- - MVP 제작
    - Figma MVP 제작
        - MVP (Flutter) 초안 미완성
            - filter
            - 매물 등록 페이지(agent)
            - Like
            - MyPage
            - user
            - agent
            - Live Page
            - Live Page map
            - direct distance
            - Map
            - Index
            - SignUp
            - social login
            - SignIn(우선적 회원가입 위주)
            - social login
            - Licenses
            - payment
            - Details
                - 전화, 메세지 연결하기
                - 구매 정보
                - 방 정보
                - 추가 옵션
            - Chatting
            - DM
            - 알림창
            - 공인중개사 사무소 페이지
            - 공인중개사 등록 매물 페이지
            - live 일정 공지 창
            - 게시판 형식?
            - splash -->


<!-- ## 개발 시작 및 진행 중 -->

<!-- ### Frontend

![splash](readme_assets/splash.PNG)
![main](readme_assets/main.PNG)
![detail](readme_assets/detail.PNG)
![filter](readme_assets/filter.PNG)

- Flutter MVP 제작
    - detail Page
        - 전화 연결
        - 구매정보
        - 방정보
        - 추가 옵션
        - carousel
    - filter
        - list
    - appbar
    - splash
    - navbar
    - router
    - 카카오 소셜 로그인(front)




### Backend

auth swagger(추후 외부 포트 닫을 예정)
http://i9a803.p.ssafy.io:8080/swagger-ui/index.html

business swagger
http://i9a803.p.ssafy.io:8081/swagger-ui/index.html

notice swagger
http://i9a803.p.ssafy.io:8082/swagger-ui/index.html


