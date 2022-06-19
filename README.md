# HappyHouse
#### 아파트 실거래가 정보와 부동산 뉴스, 주변 상권 등의 정보를 제공하는 서비스

<br/>

### :books: 목차  

👉 [Contributors](#heavy_check_mark-contributors)   
👉 [Tech Stack](#notebook_with_decorative_cover-tech-stack)   
👉 [Usecase Diagram](#closed_book-usecase-diagram)   
👉 [Class Diagram](#green_book-class-diagram)   
👉 [ERD](#blue_book-erd)  
👉 [UI 설계서](#orange_book-ui-설계서)  
👉 [화면 캡쳐](#ledger-화면-캡쳐)  
  
<br>

<!-- 👇  
---
  👉 [메인페이지](#one-메인페이지)  
  👉 [동별실거래가 검색](#two-동별실거래가-검색)  
  👉 [로그인/로그아웃](#three-로그인로그아웃)     
  👉 [회원가입](#four-회원가입)  
  👉 [회원정보](#five-회원정보)  
  👉 [관심지역](#six-관심지역)  
  👉 [공지사항](#seven-공지사항)

 -->


## :heavy_check_mark: Contributors

| 팀원   | 역할                              | GitHub                              |
| ------ | --------------------------------- | --------------------------------- |
| 박주연 | Back-end, Front-end | [juyeoon](https://github.com/juyeoon)  |
| 박유주 | Back-end, Front-end |    |

<div align=right><a href="https://github.com/juyeoon/HappyHouse#books-%EB%AA%A9%EC%B0%A8">목차로 이동</a></div>


## :notebook_with_decorative_cover: Tech Stack
<div align=right><a href="https://github.com/juyeoon/HappyHouse#books-%EB%AA%A9%EC%B0%A8">목차로 이동</a></div>

## :closed_book: Usecase Diagram
<div align=right><a href="https://github.com/juyeoon/HappyHouse#books-%EB%AA%A9%EC%B0%A8">목차로 이동</a></div>

## :green_book: Class Diagram
<div align=right><a href="https://github.com/juyeoon/HappyHouse#books-%EB%AA%A9%EC%B0%A8">목차로 이동</a></div>

## :blue_book: ERD
<div align=right><a href="https://github.com/juyeoon/HappyHouse#books-%EB%AA%A9%EC%B0%A8">목차로 이동</a></div>

## :orange_book: UI 설계서
<div align=right><a href="https://github.com/juyeoon/HappyHouse#books-%EB%AA%A9%EC%B0%A8">목차로 이동</a></div>

## :ledger: 화면 캡쳐
<div align=right><a href="https://github.com/juyeoon/HappyHouse#books-%EB%AA%A9%EC%B0%A8">목차로 이동</a></div>















<!--
---

## :star: 프로젝트 구조

### :one: Usecase Diagram  


<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/-/raw/master/UML/usecaseS.JPG">

##### [목차](#books-목차)

<br>

---

### :two: Class Diagram  

#### 1. HouseMap
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/-/raw/master/UML/houseMap.PNG">

#### 2. UserInfo
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/-/raw/master/UML/userInfo.PNG">

#### 3. InterestArea
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/-/raw/master/UML/interestArea.PNG">

#### 4. Notice
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/-/raw/master/UML/notice.PNG">

##### [목차](#books-목차)

<br>

---

### :three: DB 구조

<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/-/raw/master/DB/HappyHouse_Spring_Modeling.JPG">

##### [목차](#books-목차)

<br>

---

### :four: Controller
+ 경로: \src\main\java\com\ssafy\happyhouse\controller\

#### 1. HomeController.java 
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/0e29a80d394d20c82660513009ce770f/1.png" width="40%">

#### 2. HouseMapController.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/3f9605590174114a5268eb184e721741/2.png">

#### 3. UserController.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/5fb2375150d0cc90656bbf6e84346752/3.png">

#### 4. UserRestController.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/e35af56afb0c67332081f215d5791b06/4.png">

#### 5. InterestAreaController.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/03c25273bf6f5ae39ec2bc18e4ca8baf/5.png">

#### 6. InterestAreaRestController.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/afc866143b9df6e7022600bec36beb51/6.png">

#### 7. NoticeController.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/8b3650a27e99188da118ef8abf7324f3/7.png" width="80%">

#### 8. ExceptionControllerAdvice.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/aee22ac350606780264d00407b0f7279/9.png" width="70%">


##### [목차](#books-목차)

<br>

---

### :five: DTO

+ 경로: \src\main\java\com\ssafy\happyhouse\model\

#### 1. HouseInfoDto.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/a9cac1af19a0e771c68039b3ced8a5f9/1.png" height="400px">

#### 2. SidoGugunCodeDto.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/0a340d6396975e19fb2342d1373b1e95/2.png" height="250px">

#### 3. UserInfoDto.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/4726faa066751b766420faea2075cec6/3.png" height="280px">

#### 4. InterestAreaDto.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/034fdc5cc12a163950bc47c9338e434c/4.png" height="250px">

#### 5. NoticeDto.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/ebddd5c294f07e8b0cb537ff50db8a7a/5.png" height="300px">


##### [목차](#books-목차)

<br>

---

### :six: Service  

+ 경로: \src\main\java\com\ssafy\happyhouse\model\service\  

#### 1. HouseMapService.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/ab52fce955f21528291f25850cbc955e/1.png" width="70%">

#### 2. UserInfoService.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/516f0b491f2717f5bfd5481db8786484/2.png" width="70%">

#### 3. InterestAreaService.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/603de54fbf5c786572f7f670547ecf19/3.png" width="70%">

#### 4. NoticeService.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/481a3d26efeb4f705ed9558b8bd7836e/4.png" width="70%">


##### [목차](#books-목차)

<br>

---

### :seven: Mapper

+ 경로: \src\main\java\com\ssafy\happyhouse\model\mapper\

#### 1. HouseMapMapper.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/e38907a55777a4257b75995955942e6f/1.png" width="70%">

#### 2. UserInfoMapper.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/a5fe1fb47dece439c72085c4130ae566/2.png" width="70%">

#### 3. InterestAreaMapper.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/0ef700c2382ac99c81f2bff66b7e649e/3.png" width="70%">

#### 4. NoticeMapper.java  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/7f22b7b0581519535491fb36ee2366f3/4.png" width="70%">


##### [목차](#books-목차)

<br>

---
### :eight: Mapper xml file(MyBatis)

+ 경로: \src\main\resources\mapper\  

#### 1. apt.xml  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/6ab877be6672fe8ad094a9a695135d7d/1.png">

#### 2. User.xml  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/802e6b582edf206b07b24b9a15ac2b3c/2.png" width="70%">

#### 3. InterestArea.xml  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/35b9f597e5c1be307342ca6cc37f7099/3.png" width="70%">

#### 4. Notice.xml  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/fde0bca97eac41e79037a3a686d94674/4.png" width="70%">

##### [목차](#books-목차)

<br>

---
### :nine: 기타(Properties, JSP)

#### Properties 
+ 경로: \src\main\resources\  
+ 파일명: application.properties  
<img src="https://lab.ssafy.com/18cwceark/happyhouse_spring_bulgeong_4_daejuju/uploads/816b8947a3ec9c7d401722a43a3ca012/5.png" width="70%">

#### JSP  
##### 화면
+ 경로: \src\main\webapp\WEB-INF\views\  
+ 파일명: index.jsp, signup.jsp, login.jsp, mypage.jsp, edit_mypage.jsp, interest_area.jsp, notice.jsp, notice_detail.jsp, notice_register.jsp, edit_notice.jsp, user_list.jsp


##### 에러페이지 
+ 경로: \src\main\webapp\WEB-INF\views\error\  
+ 파일명: 404.jsp, error.jsp


##### Template 
+ 경로: \src\main\webapp\WEB-INF\views\template\  
+ 파일명: navigation.jsp, header.jsp, footer.jsp

##### [목차](#books-목차)

<br>
<br>

---
## :star: 화면 캡쳐

### :one: 메인페이지  

![1-crop](/uploads/9e66e72a4a4cdf0f41fb41118529488e/1-crop.JPG)


##### [목차](#books-목차)

<br>

---
### :two: 동별실거래가 검색 

#### <검색된 동에 맞게 지도 위치 변경>
![2-crop](/uploads/bdf3a666260698cf9c22408d379e87db/2-crop.JPG)


##### [목차](#books-목차)

<br>

---
### :three: 로그인/로그아웃

![4-crop](/uploads/124e4a8acfb37e0dc97adf1b5d8d4a12/4-crop.JPG)

**↓ 로그인시 네비게이션 바 변경**  
![3](/uploads/e356fd86031b332cee1995afee017c9f/3.JPG)


##### [목차](#books-목차)

<br>

---
### :four: 회원가입

![5-crop](/uploads/2b1da3b51affc5ee831b1906429273fa/5-crop.JPG) 


##### [목차](#books-목차)

<br>

---
### :five: 회원정보

#### <사용자 정보 확인 화면>  
![6-crop](/uploads/36114166c075cc1e15965f2cc3e10eca/6-crop.JPG)

#### <관리자 정보 확인 화면>  
![7-crop](/uploads/3bcde5dbe2d8bc09ae3680ffef1aee95/7-crop.JPG)

#### <회원정보 수정 화면>  
![8-crop](/uploads/00e76b1b06dd7c7d3678110870212c3e/8-crop.JPG)


##### [목차](#books-목차)

<br>

---
### :six: 관심지역

#### <관심지역 설정 화면>
![9-crop](/uploads/340c93311dfb50493a77f2cd919fd860/9-crop.JPG)  

#### <관심지역목록 조회 화면>   
![10-crop](/uploads/f7fb9efb4fc9eb93a7da9b12cb844cc2/10-crop.JPG)  


##### [목차](#books-목차)

<br>

---
### :seven: 공지사항

#### <공지사항 메인 화면>
![11-crop](/uploads/bb9576b2cb816b0e3469463d4730ca37/11-crop.JPG)

#### <공지사항 내용 확인 화면>
![12-crop](/uploads/b87043aed56de0b7c2e5918dabe6f459/12-crop.JPG)

#### <관리자 로그인 시 공지사항 메인 화면>   
![13-crop](/uploads/82c94a59e1a81a91da3e2c045a73b00f/13-crop.JPG)

#### <관리자 로그인 시 공지사항 등록 화면>   
![14-crop](/uploads/65b543f83c4ba5804fac317525ba8891/14-crop.JPG)

#### <관리자 로그인 시 공지사항 내용 확인 화면>   
![15-crop](/uploads/1a472fdf427e0c9d51f7edf628dac305/15-crop.JPG)


##### [목차](#books-목차)

<br>



