# Recruitment task - OpenX

# PL

Repozytorium ukazuje rozwiązane zadanie rekrutacyjne.

## Stosowanie

### Połączone dane

Wynik operacji złączenia danych o [użytkownikach](https://jsonplaceholder.typicode.com/users) i [postach](https://jsonplaceholder.typicode.com/posts).
```
https://openxrecruitmenttask.herokuapp.com/summary
```
[Kliknij tutaj aby przejść do endpointu /summary](https://openxrecruitmenttask.herokuapp.com/summary)


### Sprawdzanie ile postów napisał dany użytkownik
Rezultat ukazuje ile każdy użytkownik napisał postów z podanego linku o postach.
```
https://openxrecruitmenttask.herokuapp.com/count
```
[Kliknij tutaj aby przejść do endpointu  /count ](https://openxrecruitmenttask.herokuapp.com/count)

#### Oczekiwane dane wyjściowe:
``` javascript
[
  "Leanne Graham napisał 10 postów.",
  "Ervin Howell napisał 10 postów.",
  "Clementine Bauch napisał 10 postów.",
  "Patricia Lebsack napisał 10 postów.",
  "Chelsey Dietrich napisał 10 postów.",
  "Mrs. Dennis Schulist napisał 10 postów.",
  "Kurtis Weissnat napisał 10 postów.",
  "Nicholas Runolfsdottir V napisał 10 postów.",
  "Glenna Reichert napisał 10 postów.",
  "Clementina DuBuque napisał 10 postów."
]
```

### Sprawdzanie czy tytuły postów się powtarzają
Wynik operacji sprawdzjącej czy występują duplikaty.
```
https://openxrecruitmenttask.herokuapp.com/unique
```
[Kliknij tutaj aby przejść do endpointu  /unique ](https://openxrecruitmenttask.herokuapp.com/unique)

#### Oczekiwane dane wyjściowe:
``` javascript
[
  "Lista duplikatów jest pusta, jej rozmiar wynosi 0. Wszystkie tytuły są więc unikalne."
]
```

### Sprawdzanie, który użytkownik mieszka najbiżej
Ten endpoint bazuje na obliczeniu odległości na podstawie danych geolokalizacyjnych, a następnie zwraca użytkownika, który mieszka najbliżej niego.
```
https://openxrecruitmenttask.herokuapp.com/nearest
```
[Kliknij tutaj aby przejść do endpointu  /nearest ](https://openxrecruitmenttask.herokuapp.com/nearest)

#### Oczekiwane dane wyjściowe:
``` javascript
[
  "Użytkownik o imieniu Leanne Graham mieszka najbliżej użytkownika Chelsey Dietrich",
  "Użytkownik o imieniu Ervin Howell mieszka najbliżej użytkownika Kurtis Weissnat",
  "Użytkownik o imieniu Clementine Bauch mieszka najbliżej użytkownika Ervin Howell",
  "Użytkownik o imieniu Patricia Lebsack mieszka najbliżej użytkownika Nicholas Runolfsdottir V",
  "Użytkownik o imieniu Chelsey Dietrich mieszka najbliżej użytkownika Mrs. Dennis Schulist",
  "Użytkownik o imieniu Mrs. Dennis Schulist mieszka najbliżej użytkownika Chelsey Dietrich",
  "Użytkownik o imieniu Kurtis Weissnat mieszka najbliżej użytkownika Ervin Howell",
  "Użytkownik o imieniu Nicholas Runolfsdottir V mieszka najbliżej użytkownika Patricia Lebsack",
  "Użytkownik o imieniu Glenna Reichert mieszka najbliżej użytkownika Clementina DuBuque",
  "Użytkownik o imieniu Clementina DuBuque mieszka najbliżej użytkownika Glenna Reichert"
]
```

# EN 

This repository represents code and output for recruitment task.

## Usage

### Merged data

To retrieve merged data from the given endpoints [users](https://jsonplaceholder.typicode.com/users) and [posts](https://jsonplaceholder.typicode.com/posts).
```
https://openxrecruitmenttask.herokuapp.com/summary
```
[Access this endpoint /summary by clicking here ](https://openxrecruitmenttask.herokuapp.com/summary)


### Count of user posts
To retrieve the output which represents how many posts have been added by particular user.
```
https://openxrecruitmenttask.herokuapp.com/count
```
[Access this endpoint /count  by clicking here ](https://openxrecruitmenttask.herokuapp.com/count)

#### Expected output:
``` javascript
[
  "Leanne Graham napisał 10 postów.",
  "Ervin Howell napisał 10 postów.",
  "Clementine Bauch napisał 10 postów.",
  "Patricia Lebsack napisał 10 postów.",
  "Chelsey Dietrich napisał 10 postów.",
  "Mrs. Dennis Schulist napisał 10 postów.",
  "Kurtis Weissnat napisał 10 postów.",
  "Nicholas Runolfsdottir V napisał 10 postów.",
  "Glenna Reichert napisał 10 postów.",
  "Clementina DuBuque napisał 10 postów."
]
```

### Check if post titles are duplicated
To retrieve the output which represents if there are titles that are duplicated.
```
https://openxrecruitmenttask.herokuapp.com/unique
```
[Access this endpoint /unique by clicking here ](https://openxrecruitmenttask.herokuapp.com/unique)

#### Expected output:
``` javascript
[
  "Lista duplikatów jest pusta, jej rozmiar wynosi 0. Wszystkie tytuły są więc unikalne."
]
```

### Show user which is the nearest neigbour
To retrieve the output which represents user which live the nearest to each other, calculated by geolocation parameters.
```
https://openxrecruitmenttask.herokuapp.com/nearest
```
[Access this endpoint /nearest by clicking here ](https://openxrecruitmenttask.herokuapp.com/nearest)

#### Expected output:
``` javascript
[
  "Użytkownik o imieniu Leanne Graham mieszka najbliżej użytkownika Chelsey Dietrich",
  "Użytkownik o imieniu Ervin Howell mieszka najbliżej użytkownika Kurtis Weissnat",
  "Użytkownik o imieniu Clementine Bauch mieszka najbliżej użytkownika Ervin Howell",
  "Użytkownik o imieniu Patricia Lebsack mieszka najbliżej użytkownika Nicholas Runolfsdottir V",
  "Użytkownik o imieniu Chelsey Dietrich mieszka najbliżej użytkownika Mrs. Dennis Schulist",
  "Użytkownik o imieniu Mrs. Dennis Schulist mieszka najbliżej użytkownika Chelsey Dietrich",
  "Użytkownik o imieniu Kurtis Weissnat mieszka najbliżej użytkownika Ervin Howell",
  "Użytkownik o imieniu Nicholas Runolfsdottir V mieszka najbliżej użytkownika Patricia Lebsack",
  "Użytkownik o imieniu Glenna Reichert mieszka najbliżej użytkownika Clementina DuBuque",
  "Użytkownik o imieniu Clementina DuBuque mieszka najbliżej użytkownika Glenna Reichert"
]
```

## Author

* **Kamila Nowak** - *Initial work* - [KamilaNowak](https://github.com/KamilaNowak)
