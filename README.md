# FinanceHUB

FinanceHUB est une application de finance déstinée à l'utilisation mobile pour avoir des informations sur les entreprises et titres côtés en bourse.

L'application a été réalisée pendant les mois de Mars et Avril 2020.


### Détails techniques


```
Langage utilisé : Java

Environnement de développement : Android Studio

Outil de compilation : Gradle
```



## Notion abordées

```
  - Programmation Orientée Objet
  - Recyclerview + List Adapter
  - Appel Webservice à une API Rest
  - Stockage des données en cache
  - Architecture MVC
  - Principes SOLID (inversion des dépendances)
  - Design Patterns (Singleton)
  - Design :
            - Images url (Picasso)
            - Animations (Yoyo)
            - Research bar
            - Filter
            - Spinner
            - NavigationView
            - Bottom NavigationView    
  - Notifications Push (Firebase Messaging)
```

### Ressources et liens

```
  - Github
  - FireBase
  - API
  
Lien vers API Documentation : https://financialmodelingprep.com/developer/docs/ (Financial Modeling Prep)
API utilisées : 
  - https://financialmodelingprep.com/api/v3/company/stock/list (affichée dans la Recyclerview)
  - https://financialmodelingprep.com/images-New-jpg/{ticker}.jpg (pour les images)

```

L'application affiche une liste de type Recyclerview qui permet à l'utilisateur de voir à l'écran la totalité des entreprises et titres côtée avec leur nom et leur Ticker (symbole en finance pour l'identification). En appuyant sur une des entreprises, l'utilisateur accède à un autre écran qui lui permet de voir les détails de cette dernière (nom, symbole, prix, bourse d'echange). Lorsque l'application est fermée, l'utilisteur reçoit quotidiennement une notification Push grâce au Cloud Messaging FireBase. Le message l'invite à acheter des actions :D .
La première activité montre un écran d'accueil, où l'on peut choisir d'accéder à la liste des entreprises [Image 1] ou afficher les langues disponibles grâce au spinner [Image 2].
La seconde activité montre la liste des éléments [Image3]. On peut aussi filtrer en insérant un ticker [Image4].
Lorsqu'on clique sur un élément, on peut en observer le détail [Image5] avec nom, symbole, prix, bourse d'echange et logo de l'entreprise.
