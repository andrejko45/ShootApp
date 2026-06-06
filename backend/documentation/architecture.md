# ShootApp

## Prehľad

Strelecká aplikácia ktorá umožňuje používateľom sledovať výsledky zúčastnených v streleckej súťaži.

Systém umožňuje:  
    - Registrovať užívateľov v súťaži  
    - Priradiť im role   
    - Starat sa o súťaže a kolá  
    - Manažovanie výsledkov v jednotlivých kolách

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

### Rola
    - Rola musí mať unikátne meno a nemôže byť prázdne
    - Môže sa pridať popis role ako voliteľný vstup
    - Zakázané mazanie ak vystupuje aspoň v jednej účasti

### Účasť

    - Slúži ako spojovacia tabuľka pre používateľov, súťaže a role
    - Reprezentuje účasť používateľa v súťaži a taktiež jeho rolu
    - Pred vytvorením podmienky že musí používateľ, súťaž a rola existovať
    - Zakázané mazanie v prípade že existujú výsledky


