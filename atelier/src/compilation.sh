#!/bin/bash

echo Compilation du code source
javac destinations\destination.java
javac destinations\destinationFinale.java
javac destinations\destinationInterface.java
javac information\Information.java
javac information\InformationNonConforme.java
javac sources\Source.java
javac sources\SourceAleatoire.java
javac sources\SourceFixe.java
javac sources\SourceInterface.java
javac transmetteurs\Transmetteur.java
javac transmetteurs\TransmetteurParfait.java
javac visualisations\Sonde.java
javac visualisations\SondeAnalogique.java
javac visualisations\SondeLogique.java
javac visualisations\SondePuissance.java
javac visualisations\SondeTextuelle.java
javac visualisations\Vue.java
javac visualisations\VueCourbe.java
javac visualisations\VueValeur.java
javac ArgumentsException.java
javac Simulateur.java

echo Génération du javadoc
javadoc  destinations\destination.java -d doc
javadoc  destinations\destinationFinale.java -d doc
javadoc  destinations\destinationInterface.java -d doc
javadoc  information\Information.java -d doc
javadoc  information\InformationNonConforme.java -d doc
javadoc  sources\Source.java -d doc
javadoc  sources\SourceAleatoire.java -d doc
javadoc  sources\SourceFixe.java -d doc
javadoc  sources\SourceInterface.java -d doc
javadoc  transmetteurs\Transmetteur.java -d doc
javadoc  transmetteurs\TransmetteurParfait.java -d doc
javadoc  visualisations\Sonde.java -d doc
javadoc  visualisations\SondeAnalogique.java -d doc
javadoc  visualisations\SondeLogique.java -d doc
javadoc  visualisations\SondePuissance.java -d doc
javadoc  visualisations\SondeTextuelle.java -d doc
javadoc  visualisations\Vue.java -d doc
javadoc  visualisations\VueCourbe.java -d doc
javadoc  visualisations\VueValeur.java -d doc
javadoc  ArgumentsException.java -d doc
javadoc  Simulateur.java -d doc
