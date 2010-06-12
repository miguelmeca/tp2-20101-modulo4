SET NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

SQLPLUS system/rcpjdesa@desrcpj @carga1.sql
SQLPLUS system/rcpjdesa@desrcpj @carga2.sql

IMP system/rcpjdesa@desrcpj PARFILE=carga.par

PAUSE