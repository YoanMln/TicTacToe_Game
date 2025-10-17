# Mémo Programmation Java - POO

## Table des matières

- [1. Programmation Orientée Objet (POO)](#1-programmation-orientée-objet-poo)
- [2. Variables en Java](#2-variables-en-java)
- [3. Classes et Objets](#3-classes-et-objets)
- [4. Méthodes](#4-méthodes)
- [5. Entrée clavier](#5-entrée-clavier)
- [6. Interfaces](#6-interfaces)
- [7. Architecture MVC](#7-architecture-mvc)

---

## 1. Programmation Orientée Objet (POO)

### 🏗️ Concepts de base

#### Classe

- **Définition** : Modèle qui définit les propriétés et méthodes d'un objet
- 🔧 Sert de plan de construction

```php
class Voiture {
    public $marque;
    public function klaxonner() {
        echo "Tuut !";
    }
}
```

#### Objet

- **Définition** : Instance concrète d'une classe
- Créée par instanciation avec `new`

```php
$maVoiture = new Voiture();
```

#### Attributs

```java
class Warrior extends LocalWarrior {
    public string $name;
    public int $speed;
    public int $life;
    public int $shield;
    public string $imageUrl;
}
```

#### Propriétés

- Variables déclarées dans une classe
- 📦 Représentent les caractéristiques de l'objet

```php
public $marque;
```

#### Méthodes

- Fonctions déclarées dans une classe
- ⚙ Représentent les actions de l'objet

```php
public function demarrer() {}
```

### 🔧 Concepts avancés

#### Méthodes Statiques

- Peuvent être appelées directement sans créer d'instance de la classe
- **Déclaration** : mot-clé `static`

```php
class ClassName {
    public static function staticMethod() { }
}

// Utilisation
ClassName::staticMethod();
```

#### Getters / Setters

```php
private $marque;

public function getMarque() {
    return $this->marque;
}

public function setMarque($marque) {
    $this->marque = $marque;
}
```

#### Encapsulation

- Protéger les données en les rendant `private` ou `protected`
- Utilisation de getters/setters pour y accéder

#### Instanciation

- Créer un objet à partir d'une classe
- **Mot-clé** : `new`

```php
$voiture = new Voiture();
```

#### Héritage

- Permet à une classe de réutiliser le code d'une classe parent

```php
class Moto extends Vehicule {}
```

#### Polymorphisme

- Plusieurs classes utilisent la même méthode avec un comportement différent

#### Abstraction

- Classe abstraite = classe non instanciable, avec méthodes à implémenter dans les classes enfants

```php
abstract class Animal {
    abstract public function parler();
}
```

#### Surcharge de méthodes

- PHP ne permet pas la surcharge classique
- ➡ Utilise les méthodes magiques comme `__call`, `__get`, etc.

#### Propriétés / Méthodes statiques

- Accessibles sans créer d'objet

```php
class Outils {
    public static function utilitaire() {}
}

Outils::utilitaire();
```

#### Interfaces

- Définissent des contrats
- Une classe qui implémente une interface doit définir toutes ses méthodes

```php
interface Vehicule {
    public function rouler();
}
```

#### Trait

- Bloc de code réutilisable dans plusieurs classes sans héritage

```php
trait Klaxon {
    public function klaxonner() {}
}
```

#### Namespace

- Organisation du code pour éviter les conflits
- Très utile dans de gros projets

```php
namespace App\Models;
```

#### Enumération (enum)

- Type qui définit un ensemble limité et sécurisé de valeurs
- Disponible depuis PHP 8.1

```php
enum Couleur {
    case Rouge;
    case Bleu;
}
```

### 🔗 Relations principales

| Élément    | Lié à...                           |
| ---------- | ---------------------------------- |
| Classe     | Objet, Propriétés, Méthodes        |
| Objet      | Instanciation, Classe              |
| Propriétés | Getters/Setters, Encapsulation     |
| Méthodes   | Objets, Classe                     |
| Héritage   | Polymorphisme, Abstraction         |
| Interface  | Polymorphisme, Contrat             |
| Trait      | Réutilisation sans héritage        |
| Static     | Accès sans objet                   |
| Namespace  | Organisation, Évite les conflits   |
| Enum       | Type de données sécurisé et strict |

---

## 2. Variables en Java

🎲 **Ressource** : https://www.codecademy.com/learn/learn-java/modules/learn-java-variables/cheatsheet

### Convention de nommage

```java
timeUntilLaunch // camelCase
```

### Types de données

#### int

- Type de donnée qui sert à stocker des nombres entiers (positifs ou négatifs, sans virgule)

```java
public class CountComment {
    public static void main(String[] args) {
        int numComments = 6;
        System.out.println(numComments);
    }
}
```

#### double

- Type de données qui sert à stocker des nombres réels (valeurs avec virgule)

```java
public class MarketShare {
    public static void main(String[] args) {
        double androidShare = 81.7;
        System.out.println(androidShare);
    }
}
```

#### boolean

- Utilisé dans les conditions `if` et les boucles `while`
- Contient deux types de valeurs :
  - `true` → vrai
  - `false` → faux

```java
public class Booleans {
    public static void main(String[] args) {
        boolean intsCanHoldDecimals = false;
        System.out.println(intsCanHoldDecimals);
    }
}

// Exemple d'utilisation
int age = 17;
boolean estMajeur = age >= 18;
if (estMajeur) {
    System.out.println("Tu es majeur.");
} else {
    System.out.println("Tu es mineur.");
}
// Sortie : Tu es mineur.
```

#### char

- (character) Sert à stocker un seul caractère (une lettre, un chiffre, un symbole, etc.)
- Entouré de guillemets simples `' '`

```java
char lettre = 'A';
System.out.println("La lettre est : " + lettre);

// Exemples
char lettre = 'Z';
char chiffre = '7';
char symbole = '?';
char espace = ' ';
```

#### String

- Type de données permettant de stocker du texte (lettres, chiffres, symboles)
- Entouré de guillemets doubles `" "`

```java
public class Song {
    public static void main(String[] args) {
        String openingLyrics = "Yesterday, all my troubles seemed so far away";
        System.out.println(openingLyrics);
    }
}
```

### Concepts avancés

#### Static Checking

- Vérification statique → analyse du code à la compilation pour repérer les erreurs

#### final

| Utilisation de final | Signification                  |
| -------------------- | ------------------------------ |
| final variable       | La valeur ne peut plus changer |
| final méthode        | Ne peut pas être redéfinie     |
| final classe         | Ne peut pas être héritée       |

```java
public class Final {
    public static void main(String[] args) {
        final double pi = 3.14;
        System.out.println(pi);
    }
}
```

---

## 3. Classes et Objets

### Définitions de base

#### Classe

- **classe = plan**

```java
public class Voiture {
    String marque;
    int annee;
}
// Voiture est une classe.
```

#### Instance

- **instance = objet créé à partir de la classe**

```java
Voiture maVoiture = new Voiture();
// maVoiture est une instance de la classe Voiture.
```

### Constructeur

- Méthode qui s'exécute quand on crée une instance

```java
public class Voiture {
    String marque;
    int annee;

    // Constructeur
    public Voiture(String marqueChoisie, int anneeChoisie) {
        marque = marqueChoisie;
        annee = anneeChoisie;
    }
}
```

### Créer une nouvelle instance avec le constructeur

```java
// Pour instancier (créer un objet)
Voiture maVoiture = new Voiture("Porsche", 2021);

// • new = crée un nouvel objet en mémoire
// • Voiture(...) = appelle le constructeur, en lui donnant "Porsche" et 2021

System.out.println(maVoiture.marque); // Porsche
System.out.println(maVoiture.annee);  // 2021
```

### Résumé

1. **Classe** = le plan (ex : Voiture)
2. **Instance** = l'objet créé à partir du plan (ex : maVoiture)
3. **Constructeur** = la méthode spéciale pour donner des valeurs à l'instance lors de sa création
4. **Créer une instance** = `NomClasse nomObjet = new NomClasse(...);`

### Exemple complet

```java
public class Voiture {
    String marque;
    int annee;

    // Constructeur
    public Voiture(String marque, int annee) {
        this.marque = marque; // "this" = l'objet en cours
        this.annee = annee;
    }
}

public class Main {
    public static void main(String[] args) {
        // Création de deux instances différentes
        Voiture voiture1 = new Voiture("Porsche", 2021);
        Voiture voiture2 = new Voiture("Ferrari", 2023);

        System.out.println(voiture1.marque); // Porsche
        System.out.println(voiture2.marque); // Ferrari
    }
}
```

---

## 4. Méthodes

### Définition

- **Méthode** = action qu'un objet ou une classe peut exécuter
- Regroupe du code que l'on peut appeler plusieurs fois

```java
public void aboyer() {
    System.out.println("Wouaf !");
}
```

### Définir une méthode

#### Structure générale

```java
public void afficherInfos() {
    System.out.println("Je suis un chien");
}
```

**Détails :**

- `public` → qui peut l'utiliser (visibilité)
- `void` → le type de retour (ici, rien ne revient)
- `afficherInfos` → nom de la méthode
- `()` → paramètres (peuvent être vides)

#### Méthode avec paramètres

```java
public void manger(String nourriture) {
    System.out.println("Le chien mange : " + nourriture);
}

// Appel :
dog1.manger("croquettes");
```

---

## 5. Entrée clavier

### Import et initialisation

```java
import java.util.Scanner;
Scanner scanner = new Scanner(System.in);
```

### Méthodes de lecture

#### nextInt()

- Lit uniquement le prochain entier dans l'entrée
- Retourne un `int`
- S'arrête quand il rencontre un espace, saut de ligne, tabulation

#### nextLine()

- Lit toute la ligne jusqu'au caractère de fin de ligne
- Retourne un `String` (même si la ligne contient des chiffres)

---

## 6. Interfaces

### Définition

- **Contrat** que les classes peuvent implémenter
- Elle définit des méthodes, sans les coder
- Interface = méthode déclarée mais non définie
- Classe qui implémente = méthode définie

### Syntaxe

#### Création d'une interface

```java
public interface Interactable {
    void interact(Character player);
}
```

#### Classe qui implémente une interface

```java
public class EquipmentCell extends Cell implements Interactable {
    @Override
    public void interact(Character player) {
        // logique de l'interaction
    }
}
```

#### Utilisation dans le code

```java
if (cell instanceof Interactable) {
    ((Interactable) cell).interact(player);
}
```

### Pourquoi utiliser une interface ?

- Pour garantir que certaines classes auront un comportement commun
- **Exemple** : toutes les cases interactives du plateau (ennemis, équipements...) peuvent être "interactées" de la même manière

### Avantages

| Avantage          | Description                                                       |
| ----------------- | ----------------------------------------------------------------- |
| **Flexibilité**   | Plusieurs classes peuvent partager un comportement commun         |
| **Réutilisation** | Le même traitement fonctionne pour des objets différents          |
| **Lisibilité**    | Moins de conditions instanceof, code plus propre                  |
| **Extensibilité** | Facile d'ajouter de nouveaux types sans modifier le code existant |
| **Testabilité**   | Le code est plus modulaire, donc plus facile à tester             |

### Mots-clés

| Mot-clé      | Description                                             |
| ------------ | ------------------------------------------------------- |
| `interface`  | Déclare une interface                                   |
| `implements` | Une classe s'engage à coder les méthodes de l'interface |
| `@Override`  | Indique qu'on redéfinit une méthode d'interface         |

---

## 7. Architecture MVC

### Vue d'ensemble

| Partie                      | Rôle                                                 |
| --------------------------- | ---------------------------------------------------- |
| **Model (Modèle)**          | Gère les données et la logique métier                |
| **View (Vue)**              | Affiche les informations à l'utilisateur             |
| **Controller (Contrôleur)** | Gère les interactions utilisateur et la coordination |

### Détails par composant

#### Modèle (Model)

- Contient les classes qui représentent l'état du jeu : le plateau, les cases, les joueurs, les règles pour gagner...
- Contient les données et la logique métier
- Assure l'accès, la mise à jour et la validation des données
- **Ne doit jamais connaître directement la vue** ou comment les données sont affichées

#### Vue (View)

- Affiche la grille, les tours, les messages... mais ne prend aucune décision de jeu
- Interface avec l'utilisateur (affichage et entrée)
- Récupère les données depuis le modèle (souvent via le contrôleur) et les affiche
- Notifie le contrôleur des actions de l'utilisateur (clic, saisie, etc.), mais **ne modifie pas directement le modèle**

#### Contrôleur (Controller)

- Reçoit les entrées (clavier, IA...), dit au modèle de se mettre à jour, et demande à la vue d'afficher le résultat
- Reçoit les événements de la vue
- Applique la logique nécessaire et met à jour le modèle si besoin
- Peut demander à la vue de se mettre à jour pour refléter les changements du modèle
