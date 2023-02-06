### **LINDA PROYECT**

> Summary:
> This project consists of 4 servers and infinite clients. We will have a Main Server called Linda and 3 data servers which store the information. The client only communicates with Linda and Linda distributes the information as a balancing server to the data servers.

### **PROYECTO LINDA**

> ```
> Esta última práctica de la asignatura consiste en diseñar e implementar un sistema de
> coordinación distribuido LINDA. El sistema específico deberá implementar las tres
> operaciones básicas que se han explicado en las clases teóricas de la asignatura.
> Estas operaciones serán las siguientes:
> ● PostNote (PN)
> ● RemoveNote (RN)
> ● ReadNote (ReadN)
> Las tuplas del sistema a desarrollar tendrán un tamaño variable, que nunca superará la
> longitud máxima de seis elementos, y siempre todos ellos serán de tipo String.
> Un patrón será un tipo especial de tupla en el que pueden aparecer variables, siempre
> mediante una operación RN o ReadN. Por ejemplo:
> ● [“Alberto”, “20”, “Suspenso”] es una tupla plana de tres elementos.
> ● [“Alberto”, “?X”, “?Y”] es una tupla plana de tres elementos con dos variables X e
> Y.
> Una variable en una tupla dentro del sistema siempre se identificará con el símbolo “?”
> seguido de una letra mayúscula de la A a la Z.
> A continuación se muestra una esquema de la arquitectura que debe tener el sistema
> de coordinación distribuido LINDA. Los clientes que se conecten a él, podrán realizar
> cualquiera de las siguientes cinco operaciones:
> ● Conectarse al servicio.
> ● Desconectarse del servicio.
> ● Cualquiera de las tres operaciones básicas para trabajar con tuplas.
> 
> El servidor LINDA estará formado por tres servidores, que se ejecutan en distintas
> máquinas, responsables de almacenar y gestionar las operaciones que se realicen
> sobre las tuplas que cumplan sus condiciones. El primer servidor, se encargará de
> gestionar las operaciones sobre tuplas de longitud 1 a 3, el segundo con las tuplas de
> longitud 4 a 5 y el último para tuplas con longitud 6. Internamente, en cada uno de
> estos servidores, las tuplas se almacenan en memoria. Cada uno de los grupos de
> trabajo deberá elegir la estructura de datos más óptima para su almacenamiento.
> Vamos a suponer que el servidor encargado de almacenar las tuplas de longitud 1 a 3
> requiere de un nivel extra de seguridad, ya que no queremos, por ninguno de los casos,
> perder la información que este almacena. Para ello, se va a montar un servidor réplica
> que, en caso de que el servidor primario caiga, recibirá las peticiones correspondientes
> y mantendrá el sistema en funcionamiento.
> ```
