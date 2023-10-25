# validation_toy

### 스프링 인터셉터를 활용해 로그인 기능을 구현해보는 토이 프로젝트. 앞에서 진행한 프로젝트들을 확장하는 형식.

### 앞선 프로젝트들
[1] : [upload_toy](https://github.com/cpu500m/upload_toy)<br>
[2] : [validation_toy](https://github.com/cpu500m/validation_toy)<br>

### 이번 프로젝트에 추가하고자 하는 것들

### 로그인 기능
#### DispatcherServlet과 Controller 사이 인터셉터를 두어 login 기능을 구현할 예정!
#### + login한 사용자만 글을 작성할 수 있게 검증추가 

### 페이지 추가
#### 로그인 및 회원가입 페이지를 추가. login하지 않은 사용자가 글 작성하려 하면 login 화면으로 이동.

## 이후 프로젝트들에서 확장할 사항들
### 게시물 검색기능 -> DB연결  ( + Oauth2도 써보고싶음(spring security를 통해!))

# 결과 
## 뷰 재구성
- 회원가입 폼 <br>
<img width="327" alt="image" src="https://github.com/cpu500m/login_toy/assets/80875680/3ac01a97-4a0e-4c48-8e59-e80ef7e24898"><br>

- 로그인 전<br>
<img width="369" alt="image" src="https://github.com/cpu500m/login_toy/assets/80875680/0ab1df53-d61a-4443-ba55-5eb09d7fbbac"><br>

- 로그인 후 <br>
<img width="331" alt="image" src="https://github.com/cpu500m/login_toy/assets/80875680/664fa4d0-91f9-40f3-b10f-57bc17065b31"><br>
- 스프링 인터셉터 적용 (로그인한 사용자만 게시글 작성 가능 + login시 원래 요청한 경로로 redirect)<br>
<img width="670" alt="image" src="https://github.com/cpu500m/login_toy/assets/80875680/badc1e77-ebaa-4263-837a-a224191a8f61"><br>

- 게시물 작성 ( 작성자는 유저의 닉네임 자동 등록 (readonly) )<br>
 <img width="339" alt="image" src="https://github.com/cpu500m/login_toy/assets/80875680/7b6f4516-b4a2-4269-846d-c3b21e1bfd27"><br>

- 에러 페이지 (스프링이 기본으로 제공하는 BasicErrorController 이용) <br>
<img width="350" alt="image" src="https://github.com/cpu500m/login_toy/assets/80875680/46442e4b-7e6a-4114-bd1b-fd7acc453785">

## 후기
#### 일단 아쉬운 점을 먼저 말해보자면 동시 세션처리방식 중 이전 사용자 세션을 만료하는 방식을 도입하고 싶었는데 한참을 찾아보아도 그에 대한 정보를 얻기가 쉽지않아서 포기함..( 정보는 꽤 찾을 수 있었지만 호환이 안돼서..) 이거는 나중에 Spring Security 공부해서 꼭!!! 다시 도전한다
#### 그리고 관리해야할 디렉터리가 많아짐에 따라 구조 고민이 정말 커졌음.. 이거는 앞으로도 프로젝트를 진행하면서 끊임없이 좋은 구조를 생각해봐야겠음.
#### 느낀 것은 예전에 알고리즘을 공부하면서 항상 생각했었던 설계의 중요성을 다시한번 더 느꼈음. 숲을 짜고 나무를 봐야지 나무먼저 설계하는건 정말 어려운 일임. 앞으로도 시간이 좀 걸리더라도 설계에 시간 투자를 아끼지 않을 계획!
#### 그리고 타임리프를 조금 더 잘 다룰 수 있게되었다....
#### 다음 프로젝트에서는 DB를 연결해서 DB에 회원 정보 및 게시글 정보를 저장할 예정인데 재밌을 것 같다~ (+ 게시글 수정이랑 삭제 기능도 만들어야지! )

## 다음 프로젝트로 생각중인 것
#### DB연동 ( 아직 어떤 DB를 쓸지는 정하지 않았지만 RDB를 사용할 예정이고 , ORM 기술인 JPA와 편의 프레임워크인 Spring Data JPA와 QueryDsl을 함께 사용할 에정!)

## 다음 프로젝트!
