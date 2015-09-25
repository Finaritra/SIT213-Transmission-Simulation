#!/bin/bash
echo "Test avec Source fixe"
echo "Envoie de 10011001"
java Simulateur -mess 10011001

echo "Test avec Source Aleatoire 1 "
echo "Envoie d'un signal aléatoire de 100 bits"
java Simulateur 


echo "Test avec Source Aleatoire 2 "
echo "Envoie d'un signal aléatoire de 200 bits"
java Simulateur -mess 000200

echo "Test avec Source Aleatoire 3 "
echo "Envoie d'un signal aléatoire de 300 bits"
java Simulateur -mess 000300