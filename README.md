# Thermostat (via JavaFX)

:information_source: Une application qui permet de visualiser (et contrôler) un capteur de température

>  <u>Une fenêtre avec un thermostat</u> : un spinner qui affiche et changer la température du capteur 
<br>
<u>Une fenêtre avec une image</u> : représentant la température du capteur (pour les valeurs < 0°c on a une image de neige/glace, entre 0°c et 25°c une image de nuageux etc.)
<br>
De plus, si la fenêtre principale se ferme, toutes les autres fenêtres se ferment.

## Fonctionnement


```plantuml
@startuml
skinparam defaultFontName Tahoma
skinparam classAttributeIconSize 0
skinparam monochrome true
skinparam shadowing false
skinparam linetype ortho
skinparam class {
    BackgroundColor transparent
}
skinparam package {
    BackgroundColor transparent
}
hide circle

class Stage {}

class Thread {}

package view {

class FXMLWindow {
    +FXMLWindow(title : String, pathFxmlRessource : String)
}

class CaptorMonitorWindow {
    +CaptorMonitorWindow(title : String, pathFxmlRessource : String, captor : Captor)
    +getCaptor()
    +setCaptor(captor : Captor)
}

class ImageWindow {
    - IMAGES : NavigableMap<Double,image>

    +imageWindow(captor : Captor)
    +update()
}

class ThermostatWindow {
    -spinner : Spinner

    +ThermostaWindow(captor : Captor)
    +update()
}

class CaptorWindow {
    +openWindow(type : String, captor : Captor)
}

}

package model {

class VisitorCaptor {
    +visit(captor : CaptorArea) : TreeItem<Captor>
    +visit(captor : CaptorBasic) : TreeItem<Captor>
}

class CaptorStationStub {
    +getCaptors()
}

class Captor {
    -id : UUID
    -name : string
    -value : double
    -time : double

    +Captor(name : String)
    +getId() : UUID
    +getTime() : double
    +getName() : String
    +getStrategy() : GenerationStrategy
    +setStrategy(strategy : GenerationStrategy)
    +addCaptor(captor : Captor) 
    +getTemperature() : double
    +accept(visitorCaptor : VisitorCaptor) : TreeItem<Captor>
}

interface GenerationStrategy {
    +generate()
}

class GenBoundedRandom {
    +genRandom()
    +GenBoundedRandom(min : double, max : double)
}

class GenFloatingBound {}

class GenCPU {}

interface Observable {
    +attach(observer : Observer<T>)
    +detach(observer : Observer<T>)
    +notify()
}

interface Observer {
    +update()
}

class CaptorBasic {
    +CaptorBasic(name : String, genStrategy : GenerationStrategy)
    +addCaptor(captor : Captor) <<exception>>
}

class CaptorArea {
    +Captor(name : String, captors : Map<Captor,Double>)
    +getTemperature() : double
    +addCaptor(captor : Captor)
}

class Double{}

CaptorWindow --> "-captors" CaptorStationStub
CaptorWindow ..> "Use" CaptorMonitorWindow
FXMLWindow --|> "Extends" Stage
CaptorMonitorWindow --|> FXMLWindow
CaptorMonitorWindow --> "-captor" Captor
CaptorMonitorWindow ..|> Observer
ImageWindow --|> CaptorMonitorWindow
ThermostaWindow --|> CaptorMonitorWindow
CaptorStationStub --> "*-captors" Captor
GenBoundedRandom ..|> GenerationStrategy
GenFloatingBound ..|> GenerationStrategy
GenCPU ..|> GenerationStrategy
Captor --|> Observable
Captor --> "-generationStrategy" GenerationStrategy
CaptorBasic --|> Thread
CaptorBasic --|> Captor
CaptorArea --|> Captor
CaptorArea --> "dictionnary<Captor,poids>" Double 
Observable --> "-observers" Observer


}

@enduml
```

## Répartition du Gitlab

La racine de mon gitlab est composée de deux doissers essentiels au projet :

[**src**](src) : **Toute la partie codage de l'application**

[**doc**](doc) : **Documentation de l'application**

## Environnement de Travail

L'environnement de travail se base sur un outil en particulier :👇

<div align = center>

---

&nbsp; ![Java](https://img.shields.io/badge/Java-000?style=for-the-badge&logo=j&logoColor=white&color=orange)


---

</div>


<div align = center>
Emre KARTAL
<br>
© Groupe 4
</div>