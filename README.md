> **Note**
> Si alguien quiere usar docker, docker-compose, adelante.
> Pero ahora mismo no es nuestro objetivo.
> Los objetivos son los que aparecen tras ### Objetivos del módulo

# Kata API
Kata : crear una [API](https://github.com/OAI/OpenAPI-Specification)
funcional [CRUD](https://www.codecademy.com/articles/what-is-crud) sobre
varias tablas en MySQL.

## Objetivo
Creación y utilización de una API y aprender y usar diferentes [métodos
HTTP](https://developer.mozilla.org/es/docs/Web/HTTP/Methods).
Además de trabajar con Docker en un proyecto.

## Prerrequisitos
En la Kata se asume familiaridad con las siguientes tecnologías
[Git](https://git-scm.com/), [Docker](https://www.docker.com/) y
[Docker-compose](https://docs.docker.com/compose/).

## Procedimiento
[Hacer un fork](https://github.com/epfl-dojo/kata-api/#fork-destination-box) del repositorio,
crear una rama (`git checkout -b username/langage` por ejemplo `git checkout
-b nicolasreymond/php`, desde vuestro fork). Haced un pull request para añadirlo a este 
repo y añadiéndoos al final de este fichero como autores. 

## Puesta en marcha
En cada cambio de un fichero en el contenedor tenemos que hacer:
```bash
docker-compose up --build
```
Y para lanzar los contenedores:
```bash
docker-compose up -d
```

## Objetivos de la Kata
- [ ] Hacer un *fork* del repositorio Git
- [ ] Crear una nueva  rama (ex. : `usermame/language`)
- [ ] Añadir un contenedor docker (para el lenguage elegido) al  docker-compose (NO ES NECESARIA ESTA PARTE)
### Objetivos del módulo  
- [ ] Comprender cómo detectar las diferentes  [peticiones HTTP](https://developer.mozilla.org/es/docs/Web/HTTP/Methods)
  (GET, POST, PUT, PATCH, DELETE)
- [ ] Implementación del CRUD sobre el endpoint `/beer*`
   - [ ] Implementar la creación       **`C`**`reate`
   - [ ] Implementar la lectura        **`R`**`ead`
   - [ ] Implementar la actualilzación **`U`**`pdate`
   - [ ] Implementar el borrado        **`D`**`elete`
- [ ] Implementar la lectura de los endpoints `/brewerie*`, `/categorie*` et `/style*`
- [ ] Probar la API con
  - [postman](https://www.postman.com/),
  - [insomnia](https://insomnia.rest), 
  - [curl](https://curl.haxx.se/), 
  - [httpie](https://httpie.org/), etc…  
  y guardar esas peticiones en un fichero de resultados.
- [ ] Hacer un documento que explique cómo utilizar la API
  (con ejemplos de cada una de las consultas)

Ir más lejos
- [ ] Implementar la paginación para consultar que devuelven muchos datos 
    por ejemplo con el método HTTP  HEAD.
  [HEAD](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/HEAD)
- [ ] Integración con [swagger](https://swagger.io/tools/open-source/open-source-integrations/) 
  del lenguaje escogido.
- [ ] Modificaciones para poder subir imágenes de las cervezas  


## Descripción de rutas a utilizar

| Endpoint         | Resultado                               | Método   |
|----------------- |-----------------------------------------|:--------:|
|`/beers`          | Muestra todas las cervezas              | GET      |
|`/beer`           | Añadir una cerveza                      | POST     |
|`/beer/{id}`      | Mostrar la cerveza con el id `{id}`     | GET      |
|`/beer/{id}`      | Eliminar una cerveza                    | DELETE   |
|`/beer/{id}`      | Modificar una cerveza                   | PUT      |
|`/beer/{id}`      | Modificar parcialmente una cerveza      | PUT o PATCH    |
|`/breweries`      | Listar todas las cerveceras             | GET      |
|`/brewerie/{id}`  | Mostrar la cervecera `{id}`             | GET      |
|`/categories`     | Listar todas las categorías             | GET      |
|`/categorie/{id}` | Mostrar la categoría `{id}`             | GET      |
|`/styles`         | Listar todos los estilos -style-        | GET      |
|`/style/{id}`     | Mostrar el estilo -style- `{id}`        | GET      |


## Colaboradores (idiomas en orden alfabético)

[Laravel](https://github.com/SaphireVert/Kata-API/tree/saphirevert/laravel) → [![saphirevert-repos][saphirevert-shield]][saphirevert-url]


[saphirevert-shield]: https://badgen.net/badge/Github/SaphireVert/green?icon=https://svgshare.com/i/Srf.svg
[saphirevert-url]: https://github.com/saphirevert/

