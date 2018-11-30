# Programmazione Concorrente e Distribuita 2018

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

    Lezione 13: [http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson1.html]
                [http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson1-nb.html]
    Lezione 14: [http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson2.html]
                [http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson2-nb.html]
    Lezione 15: [http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson3.html]
                [http://pcd2018.s3-website.eu-central-1.amazonaws.com/lesson3-nb.html]

La versione `-nb` è priva dello sfondo, per una più facile stampa. Si può ottenere il layout per la stampa aggiungendo all'URL il parametro `?print-pdf`. Aggiungendo `?print-pdf&showNotes=true` si ottiene anche la sovraimpressione delle note per il presentatore.

# Laboratorio 1

Il laboratorio 1 è costituito da un insieme di classi di base che vanno completate per ottenere un risultato.

Il codice iniziale si trova nel package `pcd2018.lab1`. E' costituito da alcune classi prive di implementazione e relativi test.

Il tema dell'esercizio è la realizzazione di un calcolo parallelo che estragga delle statistiche riguardanti i migliori giocatori di Bowling e le migliori sale da Bowling degli USA.
I dati di partenza sono costituiti da circa 280.000 righe divise in sette file compressi con il compressore XZ.

L'obiettivo del sistema è implementare questa sequenza:

* leggere i file
* calcolare i punteggi
* attribuire i punteggi a giocatori e sale
* sommare, ordinare e calcolare le medie di punteggi e strike per partita
* ottenere i dieci migliori giocatori e le dieci migliori sale per media punteggio e strike per partita

Il seguente diagramma illustra la struttura di massima del sistema:

![diagramma](Lab1.png "Lab 1")

Il laboratorio consiste nel superare i test nell'ordine suggerito, per creare infine il programma completo.

## Classi

Le classi da completare sono le seguenti:

### `pcd2018.lab1.bowling.GameRecord`

Record in cui leggere i dati dai file di ingresso.

### `pcd2018.lab1.bowling.BowlingGame`

Classe che calcola il risultato di una partita di bowling per un giocatore. I test di questa classe sono contenuti nel package `pcd2018.lab1.bowling`. Il punteggio del bowling è un (kata classico)[http://codingdojo.org/kata/Bowling/]

### `pcd2018.lab1.Main`

Classe principale che regge tutto il calcolo. Va riempita a partire dal codice esistente, seguendo i suggerimenti dei commenti.

### `pcd2018.lab1.ScoreReader` (test: `pcd2018.lab1.ScoreReaderTest`)

La responsabilità di questa classe è di leggere un file (di chi gli viene dato il nome) e ottenere da ogni riga un record con i dati di gioco. La classe non è thread-safe, nè avvia thread: il controllo dell'esecuzione è a carico del chiamante.
Se il file è esaurito, il metodo `get()` deve ritornare `null`.

### `pcd2018.lab1.GameRecordToData` (test: `pcd2018.lab1.GameRecordToDataTest`)

Questa classe legge un record di dati di gioco ed estrae la chiave da usare per la sommatoria dei risultati, in modo da poter utilizzare lo stesso sommatore per più chiavi diverse. Si comporta come una funzione pura, non necessita di stato.

### `pcd2018.lab1.Decoder` (test: `pcd2018.lab1.DecoderTest`)

Questa classe è un `Runnable` che legge dalla coda sorgente e, attraverso i `GameRecordToData` ottenuti al momento della costruzione, propaga il risultato verso due diverse code di destinazione.

### `pcd2018.lab1.Summarizer` (test: `pcd2018.lab1.SummarizerTest`)

Questa classe è un `Runnable` che legge dalla coda indicata e somma i dati letti, secondo la chiave che contengono. A termine dell'elaborazione si può accedere alla mappa dei risultati.

Punti di attenzione:

* `ScoreReader` va pilotato da parte di un `Thread`. `Decoder` e `Summarizer` sono invece `Runnable`, quindi possono essere eseguiti direttamente da un `ExecutorService`
* Ci si aspetta che più `ScoreReader` vengano avviati contemporaneamente, mentre in un dato momento sarà in esecuzione un `Decoder` e due `Summarizer`.
* Il `Thread` che pilota lo `ScoreReader` dovrebbe chiudersi autonomamente quando lo `ScoreReader` ha esaurito il file.
* `Decoder` e `Summarizer` possono essere interrotti anche direttamente.

## Test

I test sono organizzati in passi, e sono "taggati" con apposite etichette per poterli eseguire singolarmente. Ogni etichetta è richiamabile da un comando `gradle`.

### Step 1: `gradle step1`

Vengono verificate la funzioni statiche di lettura di un `GameRecord` e di parsing di una riga di file di ingresso.

### Step 2: `gradle step2`

Oltre ai test del livello precedente, vengono verificata la lettura di un file di esempio da parte di `ScoreReader`

### Step 3: `gradle step3`

Oltre ai test precedenti, viene verificato il funzionamento della classe `Decoder`

### Step 4: `gradle step4`

Oltre ai test precedenti, viene verificato il funzionamento della classe `Summarizer`

Si consiglia di seguire l'ordine degli step, risolvere i test del livello e poi passare al livello successivo.

## Esecuzione

Il task `gradle lab1` esegue la classe Main e ottiene i risultati.



