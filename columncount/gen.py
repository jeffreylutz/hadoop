#!/usr/bin/env python

import string;
import sys;
totalbytes = (64 * 1024 *1024) + 2;
#totalbytes = 30;
totalcolumns = 1000;
#totalcolumns = 10;
odd = 1;

while(totalbytes > 0):
	column = 0;
	rec = "";
	while(column < totalcolumns):
		column = column + 1;
		if(column > 1):
			rec = rec + " ";
		if(odd == 1):
			rec = rec + "0";
			odd = 0;
		else:
			rec = rec + "1";
			odd = 1;
	totalbytes = totalbytes - (len(rec) + 1);
	print rec;
