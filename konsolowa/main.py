import datetime

class Notatka:
    def __init__(self, tytul, tresc="", priorytetowa=False, dataUtworzenia=datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")):
        self.tytul = tytul
        self.tresc = tresc
        self.priorytetowa = priorytetowa
        self.dataUtworzenia = dataUtworzenia

#    **********************************************
#    nazwa funkcji: wypiszNotatke
#    opis funkcji: funkcja wypisuje tytuł, treść i datę notatki. W przypadku notatki priorytetowej informuje o swojej ważności
#    parametry: parametr self - self jest parametrem automatycznie określanym przez program odpowiadającym aktualnemu obiektowi, nie należy go podawać przy wywołaniu funkcji
#    zwracany typ i opis: brak
#    autor: 000000000000
#    ***********************************************
    def wypiszNotatke(self):
        print("_" * 15)
        if self.priorytetowa == True:
            print(f"Notatka WAŻNA! {self.tytul}")
        else:
            print(f"Notatka {self.tytul}")
        print(self.tresc)
        print(self.dataUtworzenia)
        print("_" * 15)

notatka = Notatka("Lekarz", "Wizyta u laryngologa", True, "12.08.2025 14:01:25")
tytul = input("Podaj tytuł notatki:")
notatkaUzytkownika = Notatka(tytul)
notatka.wypiszNotatke()
notatkaUzytkownika.wypiszNotatke()
        