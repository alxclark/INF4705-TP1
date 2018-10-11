import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns
import sys
import os
import ntpath


#puissance
df = pd.read_csv(sys.argv[1]).groupby(['algo','dimension']).mean().reset_index()

g = sns.FacetGrid(df, hue='algo', height=4, aspect=1)
g = g.map(plt.plot, 'dimension', 'time')
g.set(xscale='log')
g.set(yscale='log')
g.add_legend()
plt.savefig('resultats/test_puissance')