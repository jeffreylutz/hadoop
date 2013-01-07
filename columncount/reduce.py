#!/usr/bin/env python

import string;
import sys;
colnum = -1;
sum = 0;
map = {};
for rec in sys.stdin:
    cols = string.split(rec);
    colnum = cols[0];
    sum = sum + int(cols[1]);

print "COL: " + colnum + "   SUM: " + str(sum);