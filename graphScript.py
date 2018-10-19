import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns
import sys
import math
import os
import ntpath


#data
data = pd.read_csv(sys.argv[1]).groupby(['algo','dimension']).mean().reset_index()

#puissance
p = sns.FacetGrid(data, hue='algo', height=4, aspect=1)
p = p.map(plt.plot, 'dimension', 'time')
p.set(xscale='log')
p.set(yscale='log')
p.add_legend()
plt.savefig('resultats/test_puissance')

#rapport
p = sns.FacetGrid(data, hue='algo', height=4, aspect=1)
p = p.map(plt.plot, 'dimension', 'rapport')
p.add_legend()
plt.savefig('resultats/test_rapport')

#constantes
p = sns.FacetGrid(data, hue='algo', height=4, aspect=1)
p = p.map(plt.plot, 'f(x)', 'time')
p.add_legend()
plt.savefig('resultats/test_constantes')