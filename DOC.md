# KATA API - *CERVEZA*
## GUÍA DE USO
###### *Por Patricio Cifredo*
***
![foto](src/main/resources/photos/george-bakos-83HwuZirc-c-unsplash.jpg)
***
0 - Enlace a las pruebas
=
> Aquí está disponible el archivo
> JSON con las pruebas realizadas en Insomnia
>: 

1 - Introducción
=
***
En este trabajo hemos implementado un
proyecto Spring Boot para desarrollar una API sobre
cervezas.

Para ello hemos realizado un fork del repositorio base
https://github.com/IES-Rafael-Alberti/kata-API y
hemos creado nuestra rama de trabajo *Patcire/java*.
***

2 - Guía de uso de los endpoints
=

El endpoint global por el que accedemos
a todos los recursos de la API es:
> **"/api"**

##### Cerveza
1.  Conseguir la información de todas las cervezas
disponibles - GET
* > Para ello debemos acceder mediante la siguiente ruta:
  > **http://localhost:8080/api/beers**

  > Esta dirección incluye la petición para conseguir la información
  > de todas las cervezas de las que disponemos. Nos debe devolver todas las cervezas
  > con su información y el código **200 OK**.

  > En caso de que ocurra un fallo y no encuentre la información
  > nos devolverá la siguiente frase: *"No data were found"*

2. Conseguir la información de una cerveza en particular - GET
* > En este caso debemos conocer el id de la cerveza 
  > de la que queremos obtener información y sustituirla
  > en la siguiente ruta:

  > **http://localhost:8080/api/beer/{id}**

  > Si la id existe deberíamos recibir un código **200 OK**
  > y como cuerpo de la petición la cerveza pedida con su información.

  > Si el id es incorrecto o no existe recibiremos:
  > **"Beer with id "{id}" didn´t found"**

3. Crear una nueva cerveza - POST
* > **http://localhost:8080/api/beer**

  > En este caso deberemos incluir en el body de 
  > la petición un JSON con toda la información que almacena
  > una cerveza en nuestra base de datos 
  > excepto el id que se genera de forma automática.
  > La petición nos devolverá un código **201 Created**
  > y en su cuerpo el JSON del objeto creado.

  > Si el nombre de la cerveza ya existe
  > nos dará el siguiente error: *This beer already exist*"

4. Eliminar una cerveza - DELETE
* > **http://localhost:8080/api/beer/{id}**
  > Debemos usar la ruta anterior en una acción DELETE
  > con el id que deseemos.
  > Si conseguimos borrar la cerveza nos devuelve
  > un código: **204 No Content**
  
  > En caso de no encontrar ese id nos dará el siguiente error:
  > *"Beer with id {id} didn´t found"*

5. Modificar una cerveza - PUT

* > **http://localhost:8080/api/beer/{id}**
  > Debemos usar la ruta anterior en una acción PUT
  > con el id que deseemos y en el cuerpo 
  > un JSON con la cerveza y los campos que queremos
  > modificar.
  >  En caso de modificarlo la petición nos devuelve
  > un código **200 OK**.
   
  > En caso de error recibiremos *"Beer with id {id} didn´t found"*

##### Breweries

1. Recuperar la información de todas las breweries - GET
* > **http://localhost:8080/api/breweries**
  > Con esta dirección en modo GET 
  > conseguiremos toda la información
  > de las breweries existentes en nuestr BBDD
  > y un código **200 OK**

  > En caso de error, recibiremos
  > *"No data for breweries were found"*

2. Recuperar un Brewery por id - GET
* > **http://localhost:8080/api/brewerie/{id}**
  > Si la petición es correcta la petición
  > nos devolverá la información de la brewerie
  > y el código **200 OK** .

  > En caso de error: "The brewery with id " {id} " wasn't found"

##### Categories

Para categorías tenemos dos peticiones GET
(all y by_id)que siguen la estructura de las que hemos descrito en puntos anteriores.
Sus endpoints son:

* > Get all: **http://localhost:8080/api/categories**
* > Get by id: **http://localhost:8080/api/categorie/{id}**

##### Styles

Lo mismo para Styles:

* > Get all: **http://localhost:8080/api/styles**
* > Get by id: **http://localhost:8080/api/style/{id}**