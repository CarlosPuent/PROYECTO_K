# Sistema Gestor de Inventario
## Descripción del Proyecto
El Sistema Gestor de Inventario es una aplicación integral diseñada para gestionar productos, categorías, marcas y modelos en tiendas. Permite realizar compras a proveedores, recibir productos en tiendas y realizar ventas. El sistema está estructurado siguiendo las mejores prácticas y patrones de diseño, garantizando una implementación robusta y mantenible.

## Características
Gestión de Productos, Categorías, Marcas y Modelos
Relaciones de muchos a muchos entre Productos y Categorías, Productos y Marcas
API RESTful con endpoints detallados para cada operación
Manejo de errores con excepciones personalizadas y manejo global de excepciones
Validación de datos usando Hibernate Validator
Documentación Swagger para fácil prueba e interacción con la API

## Tecnologías Utilizadas
Java 21
Spring Boot 3.3.0
Spring Data JPA
Hibernate
MySQL
MapStruct
Lombok
Swagger
Maven

## Comenzando

### Requisitos Previos
JDK 21
Maven
Base de datos MySQL

### Instalación
Clona el repositorio:

1. Clone the repository:
    ```bash
    git clone https://github.com/CarlosPuent/PROYECTO_KODIGO.git
    cd PROYECTO_KODIGO
    ```

2. Configure the MySQL database in `application.yml`:
    ```yaml
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/your_database_name
        username: your_username
        password: your_password
    ```

3. Build and run the application:
    ```bash
    mvn clean install
    mvn spring-boot:run
     ```
    
### Documentación de la API
Swagger UI está disponible para facilitar la prueba e interacción con la API.

- [Swagger UI](http://localhost:8080/swagger-ui.html)
- [API Docs](http://localhost:8080/v3/api-docs)

### Ejemplos de Uso
#### Crear una Compra
**Endpoint:** 'POST /api/v1/purchases'

**Ejemplo de URL de Solicitud:**
```plaintext
http://localhost:8080/api/v1/purchases
Ejemplo de Cuerpo de Solicitud:

json
Copiar código
{
    "supplier_id": 1,
    "details": [
        {
            "name": "Martillo",
            "description": "Descripción para Martillo",
            "price": 25,
            "category_ids": [1],
            "brand_ids": [1],
            "models": [
                {
                    "name": "Modelo A"
                },
                {
                    "name": "Modelo B"
                },
                {
                    "name": "Modelo C"
                }
            ],
            "quantity": 30,
            "status": "ACTIVO"
        }
    ]
}
```
#### Actualizar una Compra
**Endpoint:** 'PUT /api/v1/purchases/{id}'

**Ejemplo de URL de Solicitud:**
```plaintext
http://localhost:8080/api/v1/purchases/12
Ejemplo de Cuerpo de Solicitud:

json
Copiar código
{
    "supplier_id": 2,
    "details": [
        {
            "name": "Serpiente",
            "description": "Descripción para luces Serpiente",
            "price": 150,
            "category_ids": [2],
            "brand_ids": [1, 2],
            "models": [
                {
                    "name": "Modelo SA"
                },
                {
                    "name": "Modelo SB"
                },
                {
                    "name": "Modelo SC"
                }
            ],
            "quantity": 40,
            "status": "ACTIVO"
        }
    ]
}
```
### Licencia
Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo LICENSE para más detalles.

### Contacto
Para cualquier consulta o comentario, por favor contactame.

### Lista de Endpoints Disponibles

#### Gestión de Compras (Purchase)

| Método | Endpoint | Descripción |
| --- | --- | --- |
| `POST` | `/api/v1/purchases` | Crear una Compra |
| `PUT` | `/api/v1/purchases/{id}` | Actualizar una Compra |
| `DELETE` | `/api/v1/purchases/{id}` | Eliminar una Compra |

#### Gestión de Recepciones (Receive)

| Método | Endpoint | Descripción |
| --- | --- | --- |
| `POST` | `/api/v1/receive/store/{storeId}/supplier/{supplierId}` | Recepción de Productos en Tienda |
| `PUT` | `/api/v1/receive/store/{storeId}/supplier/{supplierId}/movement/{movementId}` | Actualizar Recepción de Productos |
| `DELETE` | `/api/v1/receive/store/{storeId}/supplier/{supplierId}/movement/{movementId}` | Eliminar Recepción de Productos |

#### Gestión de Ventas (Sale)

| Método | Endpoint | Descripción |
| --- | --- | --- |
| `POST` | `/api/v1/sales` | Crear una Venta |
| `PUT` | `/api/v1/sales/{id}` | Actualizar una Venta |
| `DELETE` | `/api/v1/sales/{id}` | Eliminar una Venta |

#### Gestión de Productos, Categorías, Marcas y Modelos

| Método | Endpoint | Descripción |
| --- | --- | --- |
| `POST` | `/api/products` | Crear Producto |
| `PUT` | `/api/products/{id}` | Actualizar Producto |
| `DELETE` | `/api/products/{id}` | Eliminar Producto |
| `POST` | `/api/categories` | Crear Categoría |
| `PUT` | `/api/categories/{id}` | Actualizar Categoría |
| `DELETE` | `/api/categories/{id}` | Eliminar Categoría |
| `POST` | `/api/brands` | Crear Marca |
| `PUT` | `/api/brands/{id}` | Actualizar Marca |
| `DELETE` | `/api/brands/{id}` | Eliminar Marca |
| `POST` | `/api/models` | Crear Modelo |
| `PUT` | `/api/models/{id}` | Actualizar Modelo |
| `DELETE` | `/api/models/{id}` | Eliminar Modelo |

### Pruebas
Descripción de las Pruebas Realizadas
Se realizaron pruebas exhaustivas de cada endpoint utilizando Postman para verificar la funcionalidad de CRUD en productos, categorías, marcas y modelos, así como las operaciones de compra, recepción y venta.

### Ejemplos de Casos de Prueba

#### Crear una Categoría

**Endpoint:** `POST /api/categories`
**URL:** `http://localhost:8080/api/categories`

**Ejemplo de Cuerpo de Solicitud:**
```json
{
    "code": "CATG0010",
    "name": "Lubricantes"
}
```
Actualizar una Marca
Endpoint: PUT /api/brands/{id}

```plaintext
http://localhost:8080/api/brands/3
Ejemplo de Cuerpo de Solicitud:

json
Copiar código
{
    "code": "BRN0003",
    "name": "Honda"
}
```

Posibles Mejoras Futuras
Implementación de un sistema de notificaciones para alertar sobre niveles bajos de inventario.
Integración con servicios externos para actualización automática de precios.
Mejora de la interfaz de usuario para una mejor experiencia de usuario.
Optimización de consultas y rendimiento del sistema para manejar grandes volúmenes de datos.
