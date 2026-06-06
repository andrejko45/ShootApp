# ShootApp

## Prehľad

Strelecká aplikácia ktorá umožňuje používateľom sledovať výsledky zúčastnených v streleckej súťaži.

## Technológie
 
    - Java 17
    - Spring Boot 
    - Spring Data JPA
    - PostgreSQL
    - Docker 
    - Maven

## Business pravidlá

### Používateľ

    - Používateľ musí zadať meno a priezvisko inak aplikácia nedovolí vytvoriť používateľa
    - Email musí byť unikátny a v správnom formáte
    - Telefońne číslo musí byť unikátne
    - Zakázané mazanie používateľa ktorý má účasť aspoň v jednej súťaži

### Súťaž

    - Súťaž musí mať unikátne meno a nemôže byt prázdne
    - Dátum nemôže byť v minulosti a musí byt v správnom formáte (YYYY-MM-DD)
    - Zakázané mazanie súťaže ktorá má zúčastnených 

