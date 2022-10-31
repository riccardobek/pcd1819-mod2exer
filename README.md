# pcd1819-mod2exer
Esercizio per il corso di Programmazione concorrente e distribuita (P3) presso l'Università degli studi di Padova.

L’applicazione prevede l’individuazione delle chiavi segrete dell’algoritmo Diffie-Hellman key exchange. Le uniche informazioni a nostra disposizione sono che la coppia di chiavi da individuare (a,b) sono:

- 0 <= a <= 65535;
- 0 <= b <= 65535.

L’esercizio richiede che venga eseguito tale ricerca attraverso un attacco di forza bruta ovvero provare ogni possibile combinazione e salvare tutte le coppie che soddisfano la seguente condizione:

    B^a mod p = A^b mod p

Dove p e g sono dati di pubblico dominio e A = g^a mod p, B = g^b mod p sono i messaggi che vengono scambiati. Quando la condizione è soddisfatta si prendono i valori a e b e li si inseriscono nella lista res.
