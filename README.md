# El proyecto es creado con java 8 y springboot
# El proyecyo require dos tablas en la base de datos en este caso postgres estos son los querys
CREATE TABLE public.catering
(
    nmsala numeric,
    nmcatering integer NOT NULL DEFAULT nextval('catering_nmcatering_seq'::regclass),
    nmreserva integer NOT NULL DEFAULT nextval('catering_nmreserva_seq'::regclass),
    dspedido character varying(300) COLLATE pg_catalog."default",
    CONSTRAINT catering_pkey PRIMARY KEY (nmcatering)
)
CREATE TABLE public.reservas
(
    nmsala numeric NOT NULL,
    nmreserva integer NOT NULL DEFAULT nextval('reservas_nmreserva_seq'::regclass),
    fereserva date NOT NULL,
    fehacereserva date NOT NULL,
    horareserva time with time zone NOT NULL,
    finhorareserva time with time zone NOT NULL,
    febaja date,
    CONSTRAINT reservas_pkey PRIMARY KEY (nmreserva)
)
La disponibilidad horario la maneje en lista quemada(Comprendo que debia ser una tabla adicional)

# Tener en cuenta:
Si se desea cambiar informacion de la coneccion a la bd ingresar al archivo aplication-dev.xml
datasource:
  host: localhost
  port: 5432
  username: postgres
  password: admin
  db: reserva
Si se ejecuta en un servido, o docker el aplicativo se debe cambiar en todo lugar el localhost por la ip

# El proyecto requiere gradle
Para ejecutar solo es click derecho en pruebabackaplication y en run apllication

# Para crear reservas
http://localhost:8080/api/v1/reservas/create
Metodo post
Se consume con la url anterior enviado en el body un json como este 
{
    "nmSala": 1,
    "feReserva":"25/01/2021",
    "feHaceReserva": "25/01/2021",
    "horaReserva":"17:00",
    "finHoraReserva":"18:00",
    "nmReserva":"0"
}
si funciona retorna el mismo json con un numero de reserva,
Si exite una reserva en el horario solicita retorna mensaje con error
# Para actualizar una reserva
http://localhost:8080/api/v1/reservas/update
metodo post
Se consume con la url anterior enviado en el body un json como este 

{
    "nmSala": 1,
    "feReserva":"25/01/2021",
    "feHaceReserva": "25/01/2021",
    "horaReserva":"18:00",
    "finHoraReserva":"19:00",
    "nmReserva":7
}
Si funciona retorna mensaje, 
Si existe una reserva en el horario a actualizar o si falla el sistema retorna mensaje comunicaciondo que hubo un error

# Para obtener la disponibilidad horaria de una sala
http://localhost:8080/api/v1/reservas/disponibilidad?nmsala=1&feReserva=25/01/2021
metodo get
Se cambian los campos nmsala = ? y feReserva = ?

# Para eliminar una reserva 
http://localhost:8080/api/v1/reservas/delete?nmReserva=7
metodo get
Se cambia el campo nmReserva

# Para catering se expone una api rest
http://localhost:8080/api/v1/catering/create
metodo post
Se consume con la url anterior enviado en el body un json como este 
{
    "nmCatering":0,
    "nmSala":1,
    "dsPedido":"1 Hot dog with cheese",
    "nmReserva":6

}

En el archivo adjuntare los archivos postman
