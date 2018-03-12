# PĘDZĄCE ŻÓŁWIE
----------------

Zarys klas
==========

## Game
Logika gry. Interfejs+klasa.
Zarządza grą i nie pozwala graczom oszukiwać. Udostępnia ona swoje metody pakietowi z GUI.
##### Atrybuty
- `availableCards : List<Card>`
   Karty dostępne w grze; takie, które mogą jeszcze zostać rozdane. Leżą dostępne na stosie.
- `trashCards : List<Card>`
   Karty zużyte. Takie, które zostały wykorzystane przez jakiegoś gracza.
- `points : Map<Integer, Integer>`
   Punkty zdobyte przez wszystkich graczy. Gra może mieć wiele rund; każda wygrana to jeden punkt dla gracza.
- `players : List<Player>`
   Lista graczy. Indeksy na liście odpowiadają kolejności rozgrywek.
- `board : Board`
   Plansza. Określa położenie żółwi.
- `playingOrder : List<Integer>`
   Kolejność rozgrywek graczy. Przechowuje ID graczy. Pierwszy w kolejności jest gracz na indeksie 0.
- `currentPlaymaker : int`
   Gracz, który zarządza obecną rozgrywką. Ustawiony jest indeks z listy playingOrder. 
- `winnerId : int`
   Id gracza, który wygrał ostatnią rundę.


##### Metody
+ `newGame() : Board`
   Nowa gra. Tworzy karty, tworzy obiekt planszy (`Board`). Rozdaje każdemu z graczy po pięć kart do ręki.
   Czyści całą planszę tj. ustawia żółwie na pierwszym polu. Losuje kolejność rozgrywek graczy.
   Zwraca planszę by móc ją wyświetlić (jej pola) na GUI.
+ `newRound() : Player`
   Tworzy nową rundę dla kolejnego gracza. Jeśli gracz z kolejki nie ma pięciu kart, to mu ją dobiera.
   Zwraca gracza, któremu dobrano (lub nie) kartkę. Sprawdza czy trzeba potasować karty ze stosu.
   Zwraca gracza by wyświetlić jego aktualne karty i żółwia, którym operuje.
+ `makeMove(card : Card) : Board`
   Wykonaj ruch – wykonuje ruch gracza.
   Dostaje kartę od obecnie rozgrywającego gracza. Wykonuje jego ruch, zwraca zmodyfikowaną plansze po ruchu.
   Inkrementuje kolejkę graczy (następne wywołanie `newRound` będzie dla kolejnego gracza) 
   Bierze pod uwagę, że więcej niż jeden żółw mogą się poruszyć przez rzucenie jednej karty.
+ `getResult() : Map<Player, Integer>`
   Zwraca strukturę przechowującą gracza oraz jego wynik w punktach.
+ `winGame() : void` 
   Pozwala na aktualizację wyniku gracza. Dodaje punkt obecnie ostatnio rozgrywającemu graczowi. 
   Trzeba ręcznie wywoływać pod stronie GUI przy każdej wygranej gracza, po wywołaniu metody `makeMove`.


## **Modele:**


## Player
Gracz posiadający kolorowego żółwia.
##### Atrybuty
- `id : Integer`
   Identyfikator gracza.
- `card : List<Card>`
   Lista pięciu kart w ręce gracza. Karty mogą się powtarzać.
- `turtle : Turtle`
   Żółw gracza.


## Board
Plansza.
##### Atrybuty
- `fields : List<List<Turtle>>`
   Lista wszystkich pól na planszy; każde pole ma listę żółwi, które na nim stoją. 
   Trzeba ustalić ich sztywną liczbę np. 8. Musi też być pole początkowe, na którym żółtwi się nie stackuje.


## Card
##### Atrybuty
- `turtle : Turtle`
   Żółw, którego dotyczy karta.
- `move : int`
  O ile żółw ma się ruszyć i w którą stronę – dla ujemnych liczb się cofa. Ruch może być jedną z tych liczb: -2,-1,1,2.


## Turtle       
Dostępne kolorki dla żółwi. Żółwie nie mogą się powtarzać; każdy gracz ma unikatowego.
Enum:
+ `YELLOW, BLUE, RED, GREEN, PURPLE`
