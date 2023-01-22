# Thermostat (via JavaFX)

:information_source: Réalisation d'une application qui permet de visualiser (et contrôler) des capteurs de température

Plus précisément, on retrouve :
>  <u>La fenêtre principale</u> : affiche la liste de tous les capteurs et lorsqu'on clique sur un capteur en particulier ces informations s'affichent.
<br>
<u>Une fenêtre avec un thermostat</u> : un spinner qui affiche et changer la température du capteur.
<br>
<u>Une fenêtre avec une image</u> : représentant la température du capteur (pour les valeurs < 0°c on a une image de neige/glace, entre 0°c et 25°c une image de nuageux, etc.).
<br>
De plus, si la fenêtre principale se ferme, toutes les autres fenêtres se ferment.

- ### Comment lancer le projet ? 

:information_source: *Si vous ne disposez pas d'Intellij IDEA, allé sur le site [jetbrains](https://www.jetbrains.com/idea/download/#section=windows) pour pouvoir le télécharger !!!*

Comme nous allons devoir utiliser la bibliothèque JavaFX, il va falloir l'installer, pour cela rendez-vous sur le site [Download JavaFX](https://gluonhq.com/products/javafx/) et installer sur le système d'exploitation que vous souhaitez (Windows, Linux, etc.), cependant veuillez à choisir l'architecture "x64" et le type "SDK" !

Lorsque que tout est installé, cloner le dépôt et configurer l'idea (mais aussi n'oubliez de configurer le lancement de l'application via le "launcher.main").
<br>
:information_source: *Pour vous aider à la configurer, vous pouvez utiliser le site [Doc JavaFX](https://openjfx.io/openjfx-docs/) !*

Lorsque tout est bon, vous pouvez lancer et profiter de l'application. :thumbsup:

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
    -{static}Images : NavigableMap<Double,Image>

    +imageWindow(captor : Captor)
    +update()
}

class ThermostatWindow {
    -spinner : Spinner

    +ThermostaWindow(captor : Captor)
    +update()
}

class CaptorWindow {
    +openWindow(type : CaptorMonitorWindow)
}

}

package model {

class VisitorCaptor {
    +visit(captor : CaptorArea) : TreeItem<Captor>
    +visit(captor : CaptorBasic) : TreeItem<Captor>
    +details(captor : CaptorArea) : HBox
    +details(captor : CaptorBasic) : HBox
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
    +details(visitorCaptor VisitorCaptor) : HBox
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
CaptorArea ..|> Observer
ImageWindow --|> CaptorMonitorWindow
ThermostatWindow --|> CaptorMonitorWindow
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
# Répartition du Gitlab

La racine de mon gitlab est composée de deux doissers essentiels au projet :

[**src**](src) : **Toute la partie codage de l'application**

[**doc**](doc) : **Documentation de l'application**

## Environnement de Travail

L'environnement de travail se base sur plusieurs outils :

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