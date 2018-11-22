Programmazione Concorrente e Distribuita 2018

Questo repository contiene le slide ed il codice di esempio discusso a lezione per il corso di Programmazione Concorrente e Distribuita per l'A.A. 2018 dell'Università di Padova, Facoltà di Matematica, Corso di Laurea di Informatica (Ordinamento 2011).

Il repository è così organizzato:

    README.md : questo file
    src : codice sorgente presentato a lezione
    build.gradle, settings.gradle : istruzioni per la compilazione con lo strumento (Gradle)[https://gradle.org]
    config : configurazioni per gli strumenti di analisi statica del codice
    slides : slide delle lezioni
    papers : documenti citati a lezione liberamente distribuibili o link interessanti.

Per predisporre l'esecuzione dei programmi d'esempio con l'IDE Eclipse, usare il comando `gradle eclipse`. Per usare l'IDE JIdea usare il comando `gradle idea`.

La maggior parte dei programmi eseguibili direttamente è richiamabile come un task specifico, per es. `gradle singleThreadPool`.

Le slide si possono consultare anche ai seguenti indirizzi:

    Lezione 13: http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson1.html
                http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson1-nb.html
    Lezione 14: http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson2.html
                http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson2-nb.html

La versione `-nb` è priva dello sfondo, per una più facile stampa. Si può ottenere il layout per la stampa aggiungendo all'URL il parametro `?print-pdf`. Aggiungendo `?print-pdf&showNotes=true` si ottiene anche la sovraimpressione delle note per il presentatore.