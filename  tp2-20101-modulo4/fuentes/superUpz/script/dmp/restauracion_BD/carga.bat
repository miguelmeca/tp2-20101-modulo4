SET NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

SQLPLUS system/password@XE @carga1.sql
SQLPLUS system/password@XE @carga2.sql

IMP system/password@XE PARFILE=carga.par

PAUSE