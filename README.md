# Proyecto de Hospitalizaciones - La Esperanza

Este proyecto, realizado en conjunto con [Julio](https://github.com/lladux), forma parte del sistema de gestión hospitalaria "La Esperanza". Su objetivo es permitir la administración de hospitalizaciones de pacientes, control de camas disponibles, médicos asignados y otros recursos, mediante una interfaz de línea de comandos (CLI) utilizando Java y MySQL.

## Tecnologías utilizadas

- **Java 17+**
- **MySQL**
- Terminal / Línea de comandos

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/lladux/Hospitalizaciones-Esperanza.git
cd hospitalizaciones-esperanza
```
### 2. Crear la base de datos
```bash
mysql -u root -p < sql/hospitalizaciones.sql
```
### 3. Descargar la dependencia (descargar el conector de MySQL)

[En pagina la pagina de oracle](https://dev.mysql.com/downloads/connector/j/?os=26)  descargar el archivo ZIP *(Platform Independent)*, extraerlo y mover el archivo mysql-connector-j-\*.jar a la carpeta lib


### 4. verifica si la conexion es correcta
Asegúrate de que los datos de conexión en tu código sean correctos:
```java
    String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
    String username = "root";
    String password = "";
```

### 5. compilalo y pruebalo
```bash
javac -d bin src/**/*.java
java -cp bin hosp.MenuMain
```
