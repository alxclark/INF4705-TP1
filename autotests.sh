#!/bin/bash

# Ce script automatise l'exécution d'une batterie de tests
# Notez la structure des fichiers et répertoires pour cet exemple

for algo in {"conv","strassen","strassenSeuil"}; do
    for serie in $(ls "exemplaires"); do
	echo ${serie///}
	counter=4
	for ex1 in $(ls "exemplaires/$serie"); do
	    for ex2 in $(ls "exemplaires/$serie" | tail -n $counter); do
		t=$(./tp.sh -a $algo -e "$(pwd)/exemplaires/$serie/$ex1" "$(pwd)/exemplaires/$serie/$ex2" -t)
	    done
	    ((counter--))
	done
    done
done
$(python.exe src/graphScript.py "C:\Users\fagagnier\Documents\School stuff\inf4705\INF4705-TP1\scripts\resultats\results.csv")

