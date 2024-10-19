# Drawing-app
Ova aplikacija je osmišljena za crtanje različitih oblika kao što su krug, heksagon, pravougaonik, tačka, krofna i linija, sa mogućnošću podešavanja unutrašnje boje i boje ivice svakog oblika. Aplikacija podržava funkcionalnosti za vraćanje (undo) i ponavljanje (redo) prethodnih komandi, kao i komande "bring to front" i "to front" koje omogućavaju pomeranje selektovanog oblika na vrh sloja, te "bring to back" i "to back" koje pomeraju selektovani oblik na dno sloja. Korisnici mogu modifikovati oblik ili ga izbrisati. Aplikacije sadrži log koji omogućava korisnicima da prate sve akcije na crtežu, pa se crtež može učitati i sačuvati zajedno sa logom koji beleži sve prethodne operacije. Takođe, aplikacija omogućava i klasično čuvanje i učitavanje crteža bez loga.

Aplikacija je razvijena u Javi i koristi različite dizajnerske obrasce. Među implementiranim obrascima su Command za izvršavanje komandi kao što su undo i redo, Adapter za prilagođavanje različitih oblika, Strategy za cuvanje i ucitavanje crteza, Observer za ažuriranje korisničkog interfejsa pri promenama, Prototype za kloniranje oblika, i MVC (Model-View-Controller) za razdvajanje poslovne logike od prikaza.

Uz osnovne funkcije crtanja, aplikacija sadrži i mini-aplikacije za simulaciju steka, kao i alat za sortiranje elemenata.

Nikola Ljubičić
