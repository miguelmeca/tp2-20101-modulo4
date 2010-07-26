-----------------------------------------------------------
-- Export file for user FIDELIZACION                     --
-- Created by Administrador on 26/07/2010, 03:15:58 a.m. --
-----------------------------------------------------------

spool base_Datos.log

prompt
prompt Creating table UBIGEO
prompt =====================
prompt
create table FIDELIZACION.UBIGEO
(
  UBIGEO_ID            NUMBER(6) not null,
  DEPARTAMENTO         CHAR(2) not null,
  PROVINCIA            CHAR(2) not null,
  DISTRITO             CHAR(2) not null,
  NOMBRE               VARCHAR2(50) not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_CREACION       DATE not null,
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null,
  USUARIO_CREACION     VARCHAR2(35) not null
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.UBIGEO
  is 'TABLA DE UBIGEO DEL PERÚ';
comment on column FIDELIZACION.UBIGEO.UBIGEO_ID
  is 'CÓDIGO DEL UBIGEO';
comment on column FIDELIZACION.UBIGEO.DEPARTAMENTO
  is 'CÓDIGO DEL DEPARTEMANETO';
comment on column FIDELIZACION.UBIGEO.PROVINCIA
  is 'CÓDIGO DE PROVINCIA';
comment on column FIDELIZACION.UBIGEO.DISTRITO
  is 'CÓDIGO DE DISTRITO';
comment on column FIDELIZACION.UBIGEO.NOMBRE
  is 'NOMBRE DEL UBIGEO';
comment on column FIDELIZACION.UBIGEO.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.UBIGEO.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.UBIGEO.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.UBIGEO.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.UBIGEO.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
alter table FIDELIZACION.UBIGEO
  add constraint PK_UBIGEO primary key (UBIGEO_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CLIENTE
prompt ======================
prompt
create table FIDELIZACION.CLIENTE
(
  CLIENTE_ID           NUMBER(6) not null,
  NOMBRE               VARCHAR2(100),
  APELLIDO_PATERNO     VARCHAR2(100),
  APELLIDO_MATERNO     VARCHAR2(100),
  NUMERO_DOCUMENTO     VARCHAR2(10),
  TELEFONO             VARCHAR2(20),
  TELEFONO_DOS         VARCHAR2(20),
  UBIGEO_ID            NUMBER(6),
  TIPO_CLIENTE_ID      NUMBER,
  USUARIO_CREACION     VARCHAR2(35) not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_CREACION       DATE not null,
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null,
  DIRECCION            VARCHAR2(200),
  CORREO               VARCHAR2(100)
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.CLIENTE
  is 'TABLA DE CLIENTES DEL LOS PLANES DE FIDELIZACIÓN DEL SUPERMERCADO UPZ.';
comment on column FIDELIZACION.CLIENTE.CLIENTE_ID
  is 'CÓDIGO DEL CLIENTE';
comment on column FIDELIZACION.CLIENTE.NOMBRE
  is 'NOMBRE DEL CLIENTE';
comment on column FIDELIZACION.CLIENTE.APELLIDO_PATERNO
  is 'APELLIDO PATERNO DEL CLIENTE';
comment on column FIDELIZACION.CLIENTE.APELLIDO_MATERNO
  is 'APELLIDO MATERNO DEL CLIENTE';
comment on column FIDELIZACION.CLIENTE.NUMERO_DOCUMENTO
  is 'NÚMERO DEL DOCUMENTO DE IDENTIDAD';
comment on column FIDELIZACION.CLIENTE.TELEFONO
  is 'NÚMERO DEL TELEFONO';
comment on column FIDELIZACION.CLIENTE.TELEFONO_DOS
  is 'NÚMERO DE SEGUNDO TELEFONO';
comment on column FIDELIZACION.CLIENTE.UBIGEO_ID
  is 'CÓDIGO DEL UBIGEO DEL CLIENTE';
comment on column FIDELIZACION.CLIENTE.TIPO_CLIENTE_ID
  is 'TIPO DE CLIENTE';
comment on column FIDELIZACION.CLIENTE.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.CLIENTE.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.CLIENTE.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.CLIENTE.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.CLIENTE.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.CLIENTE.DIRECCION
  is 'DESCRICPCIÓN DE LA DIRECCIÓN DEL CLIENTE';
comment on column FIDELIZACION.CLIENTE.CORREO
  is 'CORREO ELECTRÓNICO DL CLIENTE';
alter table FIDELIZACION.CLIENTE
  add constraint PK_CLIENTE primary key (CLIENTE_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.CLIENTE
  add constraint FK_CLIENTE_UBIGEO foreign key (UBIGEO_ID)
  references FIDELIZACION.UBIGEO (UBIGEO_ID)
  disable;

prompt
prompt Creating table SUCURSAL
prompt =======================
prompt
create table FIDELIZACION.SUCURSAL
(
  SUCURSAL_ID          NUMBER(6) not null,
  DESCRIPCION          VARCHAR2(100) not null,
  DIRECCION            VARCHAR2(200),
  TELEFONO             VARCHAR2(15),
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_CREACION       DATE not null,
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null,
  USUARIO_CREACION     VARCHAR2(35) not null
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.SUCURSAL
  is 'TABLA DE SUCURSALES DEL SUPERMERCADO';
comment on column FIDELIZACION.SUCURSAL.SUCURSAL_ID
  is 'CÓDIGO DE SUCURSAL';
comment on column FIDELIZACION.SUCURSAL.DESCRIPCION
  is 'DESCRIPCIÓN DE SUCURSAL';
comment on column FIDELIZACION.SUCURSAL.DIRECCION
  is 'DIRECCIÓN DE SUCURSAL';
comment on column FIDELIZACION.SUCURSAL.TELEFONO
  is 'NÚMERO DE TELÉFONO DE SUCURSAL';
comment on column FIDELIZACION.SUCURSAL.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.SUCURSAL.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.SUCURSAL.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.SUCURSAL.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.SUCURSAL.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
alter table FIDELIZACION.SUCURSAL
  add constraint PK_SUCURSAL primary key (SUCURSAL_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CUENTA
prompt =====================
prompt
create table FIDELIZACION.CUENTA
(
  CUENTA_ID            NUMBER(6) not null,
  PUNTOS_ACUMULADOS    NUMBER(18) not null,
  PUNTOS_VENCIDOS      NUMBER(18) not null,
  PUNTOS_CANJEADOS     NUMBER(18) not null,
  FECHA_CREACION       DATE not null,
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null,
  USUARIO_CREACION     VARCHAR2(35) not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  SUCURSAL_CREACION    NUMBER(6),
  SUCURSAL_BAJA        NUMBER(6)
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.CUENTA
  is 'TABLA DE CUENTAS DE FIDELIZACIÓN';
comment on column FIDELIZACION.CUENTA.CUENTA_ID
  is 'CÓDIGO DE LA CUENTA';
comment on column FIDELIZACION.CUENTA.PUNTOS_ACUMULADOS
  is 'TOTAL DE PUNTOS ACUMULADOS';
comment on column FIDELIZACION.CUENTA.PUNTOS_VENCIDOS
  is 'TOTAL DE PUNTOS VENCIDOS';
comment on column FIDELIZACION.CUENTA.PUNTOS_CANJEADOS
  is 'TOTAL DE PUNTOS CANJEADOS';
comment on column FIDELIZACION.CUENTA.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.CUENTA.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.CUENTA.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.CUENTA.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.CUENTA.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.CUENTA.SUCURSAL_CREACION
  is 'CÓDIGO DE SUCURSAL DE CREACIÓN';
comment on column FIDELIZACION.CUENTA.SUCURSAL_BAJA
  is 'CÓDIGO DE SUCURSAL DE ELIMINACIÓN';
alter table FIDELIZACION.CUENTA
  add constraint PK_CUENTA primary key (CUENTA_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.CUENTA
  add constraint FK_CUENTA_SUCURSAL foreign key (SUCURSAL_CREACION)
  references FIDELIZACION.SUCURSAL (SUCURSAL_ID);
alter table FIDELIZACION.CUENTA
  add constraint FK_CUENTA_SUCURSAL_BAJA foreign key (SUCURSAL_BAJA)
  references FIDELIZACION.SUCURSAL (SUCURSAL_ID);

prompt
prompt Creating table PEDIDO
prompt =====================
prompt
create table FIDELIZACION.PEDIDO
(
  PEDIDO_ID            NUMBER(6) not null,
  FECHA_PEDIDO         DATE default SYSDATE not null,
  USUARIO_CREACION     VARCHAR2(35) not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_CREACION       DATE not null,
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null,
  SUCURSAL_ID          NUMBER(6),
  TIPO_MOVIMIENTO      NUMBER(1)
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.PEDIDO
  is 'TABLA DE MOVIMIENTOS DE PRODUCTOS';
comment on column FIDELIZACION.PEDIDO.PEDIDO_ID
  is 'CÓDIGO DEL PEDIDO';
comment on column FIDELIZACION.PEDIDO.FECHA_PEDIDO
  is 'FECHA DEL PEDIDO';
comment on column FIDELIZACION.PEDIDO.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.PEDIDO.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.PEDIDO.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.PEDIDO.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.PEDIDO.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.PEDIDO.SUCURSAL_ID
  is 'CÓDIGO DE LA SUCURSAL DEL PEDIDO';
comment on column FIDELIZACION.PEDIDO.TIPO_MOVIMIENTO
  is '1 PEDIDO, 2 PEDIDO REALIZADO, 3 SALIDA';
alter table FIDELIZACION.PEDIDO
  add constraint PK_PEDIDO primary key (PEDIDO_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.PEDIDO
  add constraint FK_PEDIDO_SUCURSAL foreign key (SUCURSAL_ID)
  references FIDELIZACION.SUCURSAL (SUCURSAL_ID);

prompt
prompt Creating table TIPO_PRODUCTO
prompt ============================
prompt
create table FIDELIZACION.TIPO_PRODUCTO
(
  TIPO_PRODUCTO_ID     NUMBER(6) not null,
  DESCRIPCION          VARCHAR2(150) not null,
  USUARIO_CREACION     VARCHAR2(35) not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_CREACION       DATE not null,
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.TIPO_PRODUCTO
  is 'TABLA DE TIPO DE PRODUCTO';
comment on column FIDELIZACION.TIPO_PRODUCTO.TIPO_PRODUCTO_ID
  is 'CÓDIGO DE TIPO DE PRODUCTO';
comment on column FIDELIZACION.TIPO_PRODUCTO.DESCRIPCION
  is 'DESCRICPCIÓN DE TIPO DE PRODUCTO';
comment on column FIDELIZACION.TIPO_PRODUCTO.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.TIPO_PRODUCTO.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.TIPO_PRODUCTO.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.TIPO_PRODUCTO.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.TIPO_PRODUCTO.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
alter table FIDELIZACION.TIPO_PRODUCTO
  add constraint PK_TIPO_PRODUCTO primary key (TIPO_PRODUCTO_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRODUCTO
prompt =======================
prompt
create table FIDELIZACION.PRODUCTO
(
  PRODUCTO_ID          NUMBER(6) not null,
  TIPO_PRODUCTO_ID     NUMBER(6) not null,
  DESCRIPCION          VARCHAR2(200) not null,
  STOCK                INTEGER not null,
  USUARIO_CREACION     VARCHAR2(35) not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_CREACION       DATE not null,
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null,
  NOMBRE               VARCHAR2(100),
  CODIGO_IMAGEN        NUMBER(6)
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.PRODUCTO
  is 'TABLA DE PRODUCTOS DEL PROGRAMA DE FIDELIZACIÓN';
comment on column FIDELIZACION.PRODUCTO.PRODUCTO_ID
  is 'CÓDIGO DEL PRODUCTO';
comment on column FIDELIZACION.PRODUCTO.TIPO_PRODUCTO_ID
  is 'CÓDIGO DEL TIPO DE PRODUCTO';
comment on column FIDELIZACION.PRODUCTO.DESCRIPCION
  is 'DESCRIPCIÓN LARGA DEL PRODUCTO';
comment on column FIDELIZACION.PRODUCTO.STOCK
  is 'CANTIDAD DE STOCK';
comment on column FIDELIZACION.PRODUCTO.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.PRODUCTO.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.PRODUCTO.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.PRODUCTO.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.PRODUCTO.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.PRODUCTO.NOMBRE
  is 'NOMBRE DEL PRODUCTO';
comment on column FIDELIZACION.PRODUCTO.CODIGO_IMAGEN
  is 'CODIGO DE LA IMAGEN.';
alter table FIDELIZACION.PRODUCTO
  add constraint PK_PRODUCTO3 primary key (PRODUCTO_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.PRODUCTO
  add constraint FK_PRODUCTO_TIPO_PRODUCTO foreign key (TIPO_PRODUCTO_ID)
  references FIDELIZACION.TIPO_PRODUCTO (TIPO_PRODUCTO_ID);

prompt
prompt Creating table DETALLE_PEDIDO
prompt =============================
prompt
create table FIDELIZACION.DETALLE_PEDIDO
(
  PEDIDO_ID            NUMBER(6) not null,
  PRODUCTO_ID          NUMBER(6) not null,
  CANTIDAD             NUMBER not null,
  USUARIO_CREACION     VARCHAR2(35) not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_CREACION       DATE not null,
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.DETALLE_PEDIDO
  is 'DETALLE DEL MOVIMIENTO DE PEDIDO.';
comment on column FIDELIZACION.DETALLE_PEDIDO.PEDIDO_ID
  is 'CÓDIGO DEL PEDIDO EN MOVIMIENTO';
comment on column FIDELIZACION.DETALLE_PEDIDO.PRODUCTO_ID
  is 'CÓDIGO DEL PRODUCTO';
comment on column FIDELIZACION.DETALLE_PEDIDO.CANTIDAD
  is 'CANTIDAD DEL MOVIMIENTO';
comment on column FIDELIZACION.DETALLE_PEDIDO.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.DETALLE_PEDIDO.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.DETALLE_PEDIDO.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.DETALLE_PEDIDO.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.DETALLE_PEDIDO.ESTADO
  is 'ESTADO 1 ACTIVI 0 INACTIVO';
alter table FIDELIZACION.DETALLE_PEDIDO
  add constraint PK_DETALLE_PEDIDO primary key (PRODUCTO_ID, PEDIDO_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.DETALLE_PEDIDO
  add constraint FK_DETALLE_PEDIDO_PEDIDO foreign key (PEDIDO_ID)
  references FIDELIZACION.PEDIDO (PEDIDO_ID);
alter table FIDELIZACION.DETALLE_PEDIDO
  add constraint FK_DETALLE_PEDIDO_PRODUCTO foreign key (PRODUCTO_ID)
  references FIDELIZACION.PRODUCTO (PRODUCTO_ID);

prompt
prompt Creating table EQUIVALENCIA
prompt ===========================
prompt
create table FIDELIZACION.EQUIVALENCIA
(
  MONTO_UNO            FLOAT not null,
  CANTIDAD_PUNTO_UNO   INTEGER not null,
  MONTO_DOS            FLOAT,
  CANTIDAD_PUNTO_DOS   INTEGER,
  MONTO_TRES           FLOAT,
  CANTIDAD_PUNTO_TRES  INTEGER,
  FECHA_CREACION       DATE not null,
  USUARIO_CREACION     VARCHAR2(35) not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null,
  EQUIVALENCIA_ID      NUMBER(6) not null,
  PRODUCTO_ID          NUMBER(6) not null
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.EQUIVALENCIA
  is 'TABLA DE EQUIVALENCIA DE PUNTAJE PARA LOS PRODUCTOS';
comment on column FIDELIZACION.EQUIVALENCIA.MONTO_UNO
  is 'MONTO EN SOLES UNO';
comment on column FIDELIZACION.EQUIVALENCIA.CANTIDAD_PUNTO_UNO
  is 'CANTIDAD DE PUNTOS UNO';
comment on column FIDELIZACION.EQUIVALENCIA.MONTO_DOS
  is 'MONTO EN SOLES DOS';
comment on column FIDELIZACION.EQUIVALENCIA.CANTIDAD_PUNTO_DOS
  is 'CANTIDAD DE PUNTOS DOS';
comment on column FIDELIZACION.EQUIVALENCIA.MONTO_TRES
  is 'MONTO EN SOLES TRES';
comment on column FIDELIZACION.EQUIVALENCIA.CANTIDAD_PUNTO_TRES
  is 'CANTIDAD DE PUNTOS TRES';
comment on column FIDELIZACION.EQUIVALENCIA.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.EQUIVALENCIA.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.EQUIVALENCIA.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.EQUIVALENCIA.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.EQUIVALENCIA.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.EQUIVALENCIA.EQUIVALENCIA_ID
  is 'CÓDIGO DE EQUIVALENCIA';
comment on column FIDELIZACION.EQUIVALENCIA.PRODUCTO_ID
  is 'CÓDIGO DEL PRODUCTO';
alter table FIDELIZACION.EQUIVALENCIA
  add constraint PK_EQUIVALENCIA primary key (EQUIVALENCIA_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.EQUIVALENCIA
  add constraint FK_EQUIVALENCIA_PRODUCTO foreign key (PRODUCTO_ID)
  references FIDELIZACION.PRODUCTO (PRODUCTO_ID);

prompt
prompt Creating table IMAGEN_PRODUCTO
prompt ==============================
prompt
create table FIDELIZACION.IMAGEN_PRODUCTO
(
  CODIGO               NUMBER(6) not null,
  NOMBRE_IMAGEN        VARCHAR2(200),
  ARCHIVO              VARCHAR2(200),
  ESTADO               NUMBER(1),
  FECHA_CREACION       DATE not null,
  USUARIO_CREACION     VARCHAR2(30) not null,
  FECHA_MODIFICACION   DATE,
  USUARIO_MODIFICACION VARCHAR2(30)
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column FIDELIZACION.IMAGEN_PRODUCTO.CODIGO
  is 'CODIGO DE LA IMAGEN';
comment on column FIDELIZACION.IMAGEN_PRODUCTO.NOMBRE_IMAGEN
  is 'NOMBRE DE LA IMAGEN.';
comment on column FIDELIZACION.IMAGEN_PRODUCTO.ARCHIVO
  is 'NOMBRE DEL ARCHIVO.';
comment on column FIDELIZACION.IMAGEN_PRODUCTO.ESTADO
  is 'ESTADO';
comment on column FIDELIZACION.IMAGEN_PRODUCTO.FECHA_CREACION
  is 'FECHA DE CREACION';
comment on column FIDELIZACION.IMAGEN_PRODUCTO.USUARIO_CREACION
  is 'USUARIO DE CREACION';
comment on column FIDELIZACION.IMAGEN_PRODUCTO.FECHA_MODIFICACION
  is 'FECHA DE MODIFICACION';
comment on column FIDELIZACION.IMAGEN_PRODUCTO.USUARIO_MODIFICACION
  is 'USUARIO DE MODIFICACION';
alter table FIDELIZACION.IMAGEN_PRODUCTO
  add constraint FK_IMAGEN_PRODUCTO primary key (CODIGO)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OPCION
prompt =====================
prompt
create table FIDELIZACION.OPCION
(
  ID_OPCI              NUMBER(6) not null,
  DE_OPCI              VARCHAR2(50),
  DE_RUTA              VARCHAR2(200),
  ST_VIGE              NUMBER(1),
  ID_OPCI_PADR         NUMBER(18),
  NU_NIVEL             INTEGER,
  NU_ORDE              INTEGER,
  USUARIO_CREACION     VARCHAR2(35),
  FECHA_CREACION       DATE,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_MODIFICACION   DATE
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.OPCION
  is 'TABLA DE OPCIONES DEL SISTEMA';
comment on column FIDELIZACION.OPCION.ID_OPCI
  is 'CODIGO DE LA OPCIÓN';
comment on column FIDELIZACION.OPCION.DE_OPCI
  is 'DESCRICPIÓN DE LA OPCIÓN';
comment on column FIDELIZACION.OPCION.DE_RUTA
  is 'DESCRIPCIÓN DE LA RUTA DE LA OPCIÓN';
comment on column FIDELIZACION.OPCION.ST_VIGE
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.OPCION.ID_OPCI_PADR
  is 'CODIGO DE LA OPCIÓN PADRE';
comment on column FIDELIZACION.OPCION.NU_NIVEL
  is 'NÚMERO DE NIVEL 1 O 2';
comment on column FIDELIZACION.OPCION.NU_ORDE
  is 'NÚMERO DEL ORDEN EN QUE SE MOSTRARÁ OPCIÓN';
comment on column FIDELIZACION.OPCION.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.OPCION.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.OPCION.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.OPCION.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
alter table FIDELIZACION.OPCION
  add constraint PK_OPCION primary key (ID_OPCI)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRODUCTO_SUCURSAL
prompt ================================
prompt
create table FIDELIZACION.PRODUCTO_SUCURSAL
(
  SUCURSAL_ID          NUMBER(6) not null,
  PRODUCTO_ID          NUMBER(6) not null,
  STOCK                NUMBER(6) default 0 not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_CREACION       DATE not null,
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null,
  USUARIO_CREACION     VARCHAR2(35) not null
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.PRODUCTO_SUCURSAL
  is 'TABLA DE PRODUCTO ATENDIDO EN UNA SUCURSAL';
comment on column FIDELIZACION.PRODUCTO_SUCURSAL.SUCURSAL_ID
  is 'CÓDIGO DE SUCURSAL';
comment on column FIDELIZACION.PRODUCTO_SUCURSAL.PRODUCTO_ID
  is 'CÓDIGO DE PRODUCTO';
comment on column FIDELIZACION.PRODUCTO_SUCURSAL.STOCK
  is 'STOCK EN SUCURSAL DEL PRODUCTO';
comment on column FIDELIZACION.PRODUCTO_SUCURSAL.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.PRODUCTO_SUCURSAL.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.PRODUCTO_SUCURSAL.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.PRODUCTO_SUCURSAL.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.PRODUCTO_SUCURSAL.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
alter table FIDELIZACION.PRODUCTO_SUCURSAL
  add constraint PK_PRODUCTO_SUCURSAL primary key (SUCURSAL_ID, PRODUCTO_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.PRODUCTO_SUCURSAL
  add constraint FK_PRODUCTO_SUCURSAL_PRODUCTO foreign key (PRODUCTO_ID)
  references FIDELIZACION.PRODUCTO (PRODUCTO_ID);
alter table FIDELIZACION.PRODUCTO_SUCURSAL
  add constraint FK_PRODUCTO_SUCURSAL_SUCURSAL foreign key (SUCURSAL_ID)
  references FIDELIZACION.SUCURSAL (SUCURSAL_ID);

prompt
prompt Creating table ROL
prompt ==================
prompt
create table FIDELIZACION.ROL
(
  ID_ROL       NUMBER(6) not null,
  DE_ROL       VARCHAR2(50),
  ID_USUA_CREA VARCHAR2(50),
  ID_USUA_MODI VARCHAR2(30) not null,
  FE_USUA_CREA DATE,
  FE_USUA_MODI DATE not null,
  ST_VIGE      NUMBER(1)
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.ROL
  is 'TABLA DE ROLES DEL SISTEMA';
comment on column FIDELIZACION.ROL.ID_ROL
  is 'CÓDIGO DEL ROL';
comment on column FIDELIZACION.ROL.DE_ROL
  is 'DESCRCIPCIÓN DEL ROL';
comment on column FIDELIZACION.ROL.ID_USUA_CREA
  is 'USUARIO DE CREACIÓN DEL ROL';
comment on column FIDELIZACION.ROL.ID_USUA_MODI
  is 'USUARIO DE MODIFICACIÓN DEL ROL';
comment on column FIDELIZACION.ROL.FE_USUA_CREA
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.ROL.FE_USUA_MODI
  is 'FECHA DE MODIFICACIÓN';
comment on column FIDELIZACION.ROL.ST_VIGE
  is 'ESTADO DE VIGENCIA 1 ACTIVO 0 INACTIVO';
alter table FIDELIZACION.ROL
  add constraint PK_ROL primary key (ID_ROL)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ROL_OPCION
prompt =========================
prompt
create table FIDELIZACION.ROL_OPCION
(
  ID_OPCI              NUMBER(6) not null,
  ID_ROL               NUMBER(6) not null,
  ST_VIGE              NUMBER(1),
  FECHA_CREACION       DATE,
  FECHA_MODIFICACION   DATE,
  USUARIO_CREACION     VARCHAR2(35),
  USUARIO_MODIFICACION VARCHAR2(35)
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.ROL_OPCION
  is 'TABLA DE REACIÓN ROL - OPCIÓN';
comment on column FIDELIZACION.ROL_OPCION.ID_OPCI
  is 'CÓDIGO DE LA OPCIÓN';
comment on column FIDELIZACION.ROL_OPCION.ID_ROL
  is 'CÓDIGO DEL ROL';
comment on column FIDELIZACION.ROL_OPCION.ST_VIGE
  is 'ESTADO DE VIGENCIA 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.ROL_OPCION.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.ROL_OPCION.FECHA_MODIFICACION
  is 'FECHA DE MODIFICACIÓN';
comment on column FIDELIZACION.ROL_OPCION.USUARIO_CREACION
  is 'USUARIO DE  CREACIÓN';
comment on column FIDELIZACION.ROL_OPCION.USUARIO_MODIFICACION
  is 'USUARIO DE MODIFICACIÓN';
alter table FIDELIZACION.ROL_OPCION
  add constraint PK_ROL_OPCION primary key (ID_OPCI, ID_ROL)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.ROL_OPCION
  add constraint FK_ROL_OPCION_OPCION foreign key (ID_OPCI)
  references FIDELIZACION.OPCION (ID_OPCI);
alter table FIDELIZACION.ROL_OPCION
  add constraint FK_ROL_OPCION_ROL foreign key (ID_ROL)
  references FIDELIZACION.ROL (ID_ROL);

prompt
prompt Creating table TARJETA_FIDELIZACION
prompt ===================================
prompt
create table FIDELIZACION.TARJETA_FIDELIZACION
(
  CUENTA_ID            NUMBER(6) not null,
  CLIENTE_ID           NUMBER(6) not null,
  NUMERO               VARCHAR2(50) not null,
  USUARIO_CREACION     VARCHAR2(35) not null,
  FECHA_CREACION       DATE not null,
  USUARIO_MODIFICACION VARCHAR2(35),
  FECHA_MODIFICACION   DATE,
  ESTADO               NUMBER(1) not null,
  TIPO_CLIENTE         NUMBER(1)
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.TARJETA_FIDELIZACION
  is 'TABLA DE TARJETAS DEL PROGRAMA DE FIDELIZACION';
comment on column FIDELIZACION.TARJETA_FIDELIZACION.CUENTA_ID
  is 'CÓDIGO DE LA CUENTA';
comment on column FIDELIZACION.TARJETA_FIDELIZACION.CLIENTE_ID
  is 'CÓDIGO DEL CLIENTE';
comment on column FIDELIZACION.TARJETA_FIDELIZACION.NUMERO
  is 'NÚMERO DE LA TARJETA';
comment on column FIDELIZACION.TARJETA_FIDELIZACION.USUARIO_CREACION
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.TARJETA_FIDELIZACION.FECHA_CREACION
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.TARJETA_FIDELIZACION.USUARIO_MODIFICACION
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.TARJETA_FIDELIZACION.FECHA_MODIFICACION
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.TARJETA_FIDELIZACION.ESTADO
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.TARJETA_FIDELIZACION.TIPO_CLIENTE
  is 'TIPO DE CLIENTE 1 PRONCIPAL, 2 ASOCIADO';
alter table FIDELIZACION.TARJETA_FIDELIZACION
  add constraint PK_TARJETA_FIDELIZACION primary key (CUENTA_ID, CLIENTE_ID)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.TARJETA_FIDELIZACION
  add constraint FK_TARJETA_FIDELI_CLIENTE foreign key (CLIENTE_ID)
  references FIDELIZACION.CLIENTE (CLIENTE_ID);
alter table FIDELIZACION.TARJETA_FIDELIZACION
  add constraint FK_TARJETA_FIDELI_CUENTA foreign key (CUENTA_ID)
  references FIDELIZACION.CUENTA (CUENTA_ID);

prompt
prompt Creating table USUARIO
prompt ======================
prompt
create table FIDELIZACION.USUARIO
(
  ID_USUA      NUMBER(6) not null,
  DE_USUA      VARCHAR2(50) not null,
  DE_CLAV      VARCHAR2(30) not null,
  NO_USUA      VARCHAR2(100),
  AP_PATE_USUA VARCHAR2(100),
  AP_MATE_USUA VARCHAR2(100),
  ST_VIGE      NUMBER(1),
  ID_USUA_CREA VARCHAR2(35),
  FE_USU_CREA  DATE,
  ID_USUA_MODI VARCHAR2(35),
  FE_USUA_MODI DATE
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.USUARIO
  is 'TABLA DE USUARIOS DEL SISTEMA';
comment on column FIDELIZACION.USUARIO.ID_USUA
  is 'CÓDIGO DEL USUARIO';
comment on column FIDELIZACION.USUARIO.DE_USUA
  is 'LOGIN DEL USUARIO';
comment on column FIDELIZACION.USUARIO.DE_CLAV
  is 'CONTRASEÑA DEL USUARIO';
comment on column FIDELIZACION.USUARIO.NO_USUA
  is 'NOMBRE DEL USUARIO';
comment on column FIDELIZACION.USUARIO.AP_PATE_USUA
  is 'APELLIDO PATERNO DEL  USUARIO';
comment on column FIDELIZACION.USUARIO.AP_MATE_USUA
  is 'APELLIDO MATERNO DEL  USUARIO';
comment on column FIDELIZACION.USUARIO.ST_VIGE
  is 'ESTADO DE VIGENCIA';
comment on column FIDELIZACION.USUARIO.ID_USUA_CREA
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.USUARIO.FE_USU_CREA
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.USUARIO.ID_USUA_MODI
  is 'USUARIO DE MODIFICACIÓN';
comment on column FIDELIZACION.USUARIO.FE_USUA_MODI
  is 'FECHA DE MODIFICACIÓN';
alter table FIDELIZACION.USUARIO
  add constraint PK_USUARIO primary key (ID_USUA)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table USUARIO_ROL
prompt ==========================
prompt
create table FIDELIZACION.USUARIO_ROL
(
  ID_USUA      NUMBER(6) not null,
  ID_ROL       NUMBER(6) not null,
  ST_VIGE      NUMBER(1),
  ID_USUA_CREA VARCHAR2(35),
  FE_USUA_CREA DATE,
  ID_USUA_MODI VARCHAR2(35),
  FE_USUA_MODI DATE
)
tablespace TS_FIDELIZACION
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FIDELIZACION.USUARIO_ROL
  is 'TABLA DE REALACIÓN USUARIO - ROL';
comment on column FIDELIZACION.USUARIO_ROL.ID_USUA
  is 'CÓDIGO DE USUARIO';
comment on column FIDELIZACION.USUARIO_ROL.ID_ROL
  is 'CÓDIGO DE ROL';
comment on column FIDELIZACION.USUARIO_ROL.ST_VIGE
  is 'ESTADO 1 ACTIVO 0 INACTIVO';
comment on column FIDELIZACION.USUARIO_ROL.ID_USUA_CREA
  is 'USUARIO DE CREACIÓN';
comment on column FIDELIZACION.USUARIO_ROL.FE_USUA_CREA
  is 'FECHA DE CREACIÓN';
comment on column FIDELIZACION.USUARIO_ROL.ID_USUA_MODI
  is 'USUARIO DE ÚLTIMA MODIFICACIÓN';
comment on column FIDELIZACION.USUARIO_ROL.FE_USUA_MODI
  is 'FECHA DE ÚLTIMA MODIFICACIÓN';
alter table FIDELIZACION.USUARIO_ROL
  add constraint PK_USUARIO_ROL primary key (ID_ROL, ID_USUA)
  using index 
  tablespace TS_FIDELIZACION
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FIDELIZACION.USUARIO_ROL
  add constraint FK_USUARIO_ROL_ROL foreign key (ID_ROL)
  references FIDELIZACION.ROL (ID_ROL);
alter table FIDELIZACION.USUARIO_ROL
  add constraint FK_USUARIO_ROL_USUARIO foreign key (ID_USUA)
  references FIDELIZACION.USUARIO (ID_USUA);


spool off
