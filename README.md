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
- `points : Set<Player, int>`
   Punkty zdobyte przez wszystkich graczy. Gra może mieć wiele rund; każda wygrana to jeden punkt dla gracza.
- `players : List<Player>`
   Lista graczy. Indeksy na liście odpowiadają kolejności rozgrywek.
- `board : Board`
   Plansza. Określa położenie żółwi.

##### Metody
+ `newGame() : void`
   Nowa gra. Zbiera wszystkie karty (z rąk graczy i ze stosu `trashCards`) i je tasuje (`shuffleCards`). Czyści całą planszę tj. ustawia żółwie na pierwszym polu. Losuje kolejność rozgrywek graczy.
+ `resetGame() : void`
   Funkcjonalności `newGame()`, ale też czyści punkty zdobyte przez graczy.
+ `newRound() : Player`
   Tworzy nową rundę dla kolejnego gracza. Dobiera mu kartę (`getCard(player : Player)`). Sprawdza czy trzeba potasować karty ze stosu. Zwraca gracza by wyświetlić jego aktualne karty.
- `getCard(player : Player) : Card`
   Daj kartę – zwraca graczowi, jeśli nie ma 5 kart w ręce, kartę ze stosu.
- `increasePoints(player : Player) : void`
   Dodaj punkty graczowi - gdy gracz wygrywa.
- `shuffleCards() : void`
   Potasuj karty - wykonywane na początek gry albo w razie braku kart w grze (stos `availableCards` jest pusty, a trzeba coś dobrać).
+ `makeMove(player : Player, card : Card) : Board`
   Wykonaj ruch – wykonuje ruch gracza. Musi brać po uwagę, że więcej niż jeden żółw mogą się poruszyć przez rzucenie jednej karty. Korzysta z metody `throwCard`. Zwraca planszę do wyświetlenia na GUI.
- `throwCard(player : Player, card : Card) : void`
   Przyjmij zużytą kartę - gdy gracz odkłada/wykorzystuje kartę. Musi trafiać ona na stos zużytych (`trashCards`). Też zostaje ona zabrana z ręki gracza. Uaktualnia planszę.

_Metody publiczne są wylistowane za pomocą "+", a prywatne "-"_

## **Modele:**


## Player
Gracz posiadający kolorowego żółwia.
##### Atrybuty
- `card : List<Card>`
   Lista pięciu kart w ręce gracza. Karty mogą się powtarzać.
- `turtle : Turtle`
   Żółw gracza.


## Board
Plansza.
##### Atrybuty
- `fields : List<List<Turtle>>`
   Lista wszystkich pól na planszy; każde pole ma listę żółwi, które na nim stoją. Trzeba ustalić ich sztywną liczbę np. 8. Musi też być pole początkowe, na którym żółtwi się nie stackuje.


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
