#!/bin/bash

echo Compilation du code source
javac *\*.java


echo Génération du javadoc
javadoc  *\*.java -d doc
