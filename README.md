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
La première activité montre un écran d'accueil, où l'on peut choisir d'activer l'image du taureau pour changer d'activité. ![alt text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/Image%201.PNG). On a aussi un spinner pour afficher les langues disponibles ![alt text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran1b.PNG). ![alt text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran1c.PNG).
Ensuite on accède à un deuxième écran avec des images qui défilent ![alt_text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran2a.PNG) ![alt_text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran2b.PNG). Lorsqu'on appuie sur le bouton, celui-ci tremble et affiche une barre de chargement ![alt_text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran2c.PNG).
On arrive ensuite à la recyclerview ![alt_text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran3a.PNG) avec un menu latéral ![alt_text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran3b.PNG). Grâce à la barre de recherche on peut filtrer les éléments ![alt_text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran3c.PNG).
Lorsqu'on appuie sur un élément, on en voit le détail avec une image qui apparaît avec une animation: ![alt_text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran3d.PNG) On peut en observer le détail avec nom, symbole, prix, bourse d'echange et logo de l'entreprise. ![alt_text](https://github.com/FilippoEsiea/FinanceHUB/blob/master/%C3%A9cran3e.PNG).
