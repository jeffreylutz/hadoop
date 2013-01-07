#!/usr/bin/env python

import string;
import sys;

colMap = {};
firstpass = 1;
for rec in sys.stdin.readlines():
    cols = string.split(rec);
    if(firstpass == 1):
        col = 0;
        for colval in cols:
            col = col + 1;
            colMap[col] = 0;
        firstpass = 0;
    col = 0;
## mini reducer / combiner
    for colval in cols:
        col = col + 1;
        colMap[col] = colMap[col] + int(colval);

for k in colMap:
    val = str(colMap[k]);
    print str(k) + '\t' + str(val);

