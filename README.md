# TzUsersAppRentaTeam

## Description
Получение юзеров с интернета,отображение их в списке,сохранение их в кеш для быстрой и автономной работы

## Main Stack : Retrofit,RxJava2,Room,LiveData,Mvvm,Dagger2,View binding <br/>
Image loader: Picasso <br/>
SOLID,OOP,Clean architecture,JUnit tests<br/><br/>
Разбиение приложения на data, domain, presentation слои<br/>
Маппинг обЪектов через слои<br/><br/>
В качестве предоставления зависимостей(di) я использовал "Dagger2"<br/><br />
Для навигации приложения была использована библиотека "Navigation component"

### Tests
Необходимые классы покрыты юнит - тестами

#### package core
- [UserMapperTest](https://github.com/KostyaGig/TzUsersAppRentaTeam/blob/master/app/src/test/java/com/ketodiet/plan/com/tzusersapprentateam/core/UserMapperTest.kt)<br/>
- [UsersMapperTest](https://github.com/KostyaGig/TzUsersAppRentaTeam/blob/master/app/src/test/java/com/ketodiet/plan/com/tzusersapprentateam/core/UsersMapperTest.kt)<br/>
- [ResourceProviderTest](https://github.com/KostyaGig/TzUsersAppRentaTeam/blob/master/app/src/test/java/com/ketodiet/plan/com/tzusersapprentateam/core/ResourceProviderTest.kt)<br/>

#### package data
- [UsersRepositoryTest](https://github.com/KostyaGig/TzUsersAppRentaTeam/blob/master/app/src/test/java/com/ketodiet/plan/com/tzusersapprentateam/data/UsersRepositoryTest.kt)<br/>
- [ExceptionMapperTest](https://github.com/KostyaGig/TzUsersAppRentaTeam/blob/master/app/src/test/java/com/ketodiet/plan/com/tzusersapprentateam/data/ExceptionMapperTest.kt)<br/>

### Разбиение приложение на фичи

### Feature UA01 show users (данную фичу можно протестировать согласно тесткейсам)

[UA01TestCases](https://github.com/KostyaGig/TzUsersAppRentaTeam/blob/master/app/src/main/java/com/ketodiet/plan/com/tzusersapprentateam/testcases/UA01_show_users)<br/>

### Feature UA02 cache users (данную фичу можно протестировать согласно тесткейсам)

[UA02TestCases](https://github.com/KostyaGig/TzUsersAppRentaTeam/blob/master/app/src/main/java/com/ketodiet/plan/com/tzusersapprentateam/testcases/UA02_cache_users)<br/>

### Feature UA03 user detail (данную фичу можно протестировать согласно тесткейсам)

[UA03TestCases](https://github.com/KostyaGig/TzUsersAppRentaTeam/blob/master/app/src/main/java/com/ketodiet/plan/com/tzusersapprentateam/testcases/UA03_user_detail)<br/>

### Rest API
 - [BASE](https://reqres.in/api/)
 - [GET](https://reqres.in/api/users)