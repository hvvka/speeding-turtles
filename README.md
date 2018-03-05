# PĘDZĄCE ŻÓŁWIE
----------------
very fast turtles running at incredible hihg speed

Zarys klas
==========

## Game
Najznakomitsza klasa korzystająca z innych. Zarządza grą i nie pozwala graczom oszukiwać.
##### Atrybuty
- **availableCards : List<Card>**
   Karty w dostępne w grze; takie, które mogą jeszcze zostać rozdane. Leżą dostępne na stosie.
- **trashCards : List<Card>**
   Karty zużyte. Takie, które zostały wykorzystane przez jakiegoś gracza.
- **points : Set<Player, int>**
   Punkty zdobyte przez wszystkich graczy. Gra może mieć wiele rund; każda wygrana to jeden punkt dla gracza.
##### Metody
+ **newGame() : void**
   Nowa gra. Czyści ekran, tasuje karty, ...?
+ increasePoints(player : Player) : void
   Dodaj punkty graczowi - gdy gracz wygrywa.
+ **getCard(player : Player) : Card**
   Daj kartę – zwraca graczowi, który nie ma 5 kart w ręce, kartę ze stosu.
+ **throwCard(card : Card) : void**
   Przyjmij zużytą kartę - gdy gracz odkłada/wykorzystuje kartę. Musi trafiać ona na stos zużytych (`trashCards`).
- **shuffleCards() : void**
   Potasuj karty - wykonywane na początek gry albo w razie braku kart w grze (stos `availableCards` jest pusty, a trzeba coś dobrać).
- **makeMove(card : Card) : void**
   Wykonaj ruch – wykonuje ruch gracza. Musi brać po uwagę, że więcej niż jeden żółw mogą się poruszyć przez rzucenie jednej karty.


## Player
Biedny gracz, śmiertelnik posiadający kolorowego żółwia w zanadrzu.
##### Atrybuty
- **card : List<Card>**
   Lista pięciu kart w ręce gracza. Karty mogą się powtarzać.
- **turtle : Turtle**
   Żółw gracza.
##### Metody
// todo


## Board
Plansza.
##### Atrybuty
- **fields : List<Field>**
   Wszystkie pola na planszy. Trzeba ustalić ich sztywną liczbę np. 8. Musi też być pole początkowe, na którym żółtwi się nie stackuje.
##### Metody
// todo


## Field       
Może nie być klasą, tyko od razu listą w `Board.fields` – IMO tak by było lepiej.
##### Atrybuty
- **field : List<Turle>**
##### Metody
// todo

## Card
##### Atrybuty
- **turtle : Turtle**
   Żółw, którego dotyczy karta.
- **move : int**
  O ile żółw ma się ruszyć i w którą stronę – dla ujemnych liczb się cofa. Ruch może być jedną z tych liczb: -2,-1,1,2.
##### Metody
// todo


## Turtle       
Dostępne kolorki dla żółwi. Żółwie nie mogą się powtarzać; każdy gracz ma unikatowego.
Enum:
**YELLOW, BLUE, RED, GREEN, PURPLE**
