# Como usar la API

Para lanzar la API sera necesario tener Docker iniciado, una vez este iniciado se podra lanzar la ApiCervezasApplication.

Para hacer uso de esta API Los endopoints de las rutas disponibles son los siguientes:

| Endpoints                       | Resultado                                                  |
|-------------------------------- |------------------------------------------------------------|
|`/api/beers/beers`               | Muestra todas las cervezas.                                |
|`/api/beers/beer`                | Añade una cerveza a partir de un JSON.                     |
|`/api/beers/beer/{id}`           | Muestra la cerveza con el id `{id}`.                       |
|`/api/beers/beer/{id}`           | Elimina una cerveza con el id `{id}`.                      |
|`/api/beers/beer/{id}`           | Modifica una cerveza con el id `{id}` a partir de un JSON. |
|`/api/breweries/breweries`       | Lista todas las cervecerias.                               |
|`/api/breweries/brewerie/{id}`   | Muestra la cerveceria con el id `{id}`.                    |
|`/api/categories/categories`     | Lista todas las categorías.                                |
|`/api/categories/categorie/{id}` | Mostrar la categoría con el id `{id}`.                     |
|`/api/styles/styles`             | Lista todos los estilos.                                   |
|`/api/styles/style/{id}`         | Muestra el estilo con el id `{id}`.                        |