# Technická dokumentácia

### Štruktúra projektu 
    
    /backend/controller
            /dto
            /entity
            /exceptions
            /mapper
            /repository
            /service
            ./Dockerfile

### Controller

    - Stará sa o požiadavky voči systému
    - HTTP requesty GET, POST, PUT, DELETE
    
### DTO(Data Transfer Object)
    
    - Objekty ktoré reprezentujú dáta medzi jedntolivými vrstvami
    
### Entity

    - JPA Entity
    - Relácie ktoré vzniknú v databáze
    
### Exceptions

    - Výnimky ktoré reprezentujú chybové stavy aplikácie
    - Globálne riešenie výnimiek

### Mapper

    - Mapovanie DTO objektov na entity v databáze a entity na DTO objekty 

### Repository

    - Prístup k databáze, rieši query
    - Komunikuje so Service vrstvou

### Service

    - Rieši business logiku aplikácie
    - Volá ju Controller


## Request flow

### Požiadavka prichádzajúca od klienta

    1. Controller
    2. Service
    3. Repository
    4. Database

### Odpoveď databázy

    1. Repository
    2. Service
    3. Mapper
    4. DTO
    5. Controller

## Validácia dát

    Aplikácia validuje dáta pomocou anotácií Jakarta Validation. Príklad: @NotNull, @NotBlank a @Min
    Všetky chybové stavy rieši trieda GlobalExceptionHandler. 

## Integrita dát

    Ošetrenie stavov ako napríklad mazanie používateľa ak má účasť v súťaži. 
    Používateľ môže v každej súťaži vystupovať len raz. Súťaž zakazuje vymazať ak figuruje aspoň v jednej účasti.
    Zakázané mazanie role ktorá je v účasti. Účasť je zakázané mazať v prípade ak existujé výsledky.
    Kolo sa zakazuje mazať ak existuje aspoň jeden výsledok.

## Stratégia mapovania

### Obyčajné entity:

    Používateľ
    Súťaž 
    Rola
    Kolo

    Používateľ pošle požiadavku a mapper prevedie DTO==>Entity. Mapper komunikuje priamo so Service vrstvou.

### Relačné entity:

    Účasť
    Výsledok

    Entity ktoré existujú len kvôli prepájaniu dómenových entít. Požaduju viacej business logiky kvôli závislosti na viacerých existujúcich dómenových entít.
    
## Databáza

    - PostgreSQL
    - ORM Spring Data JPA / Hibernate
    - Kontajnerizácia Docker
    
## Git flow

    Vytváranie funkcionalít na separátnych vetvách.

# Typický workflow:

    Vetva funkcionalít 
        ==> git add .
        ==> git commit -m "Správa"
        ==> git push origin názovVetvy
        ==> git switch main
        ==> git pull
        ==> git merge názovVetvy
        ==> git push origin main
