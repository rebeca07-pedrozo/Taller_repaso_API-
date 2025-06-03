# Proyecto de Comunicación y Concurrencia Distribuida en Java

Este proyecto implementa una arquitectura cliente-servidor en Java usando sockets TCP, manejo multicliente con hilos, sincronización concurrente, productor-consumidor, bloqueo distribuido y simulación de concurrencia en escritura de base de datos.

---

##  Estructura
```
src/
├── servidor/ # Lógica del servidor TCP
│ ├── Servidor.java
│ └── ManejadorCliente.java
├── cliente/ # Lógica del cliente TCP
│ └── Cliente.java
├── log/ # Configuración de logs
│ └── LoggerConfig.java
├── concurrencia/ # Concurrencia: productor, consumidor, locks, simulaciones
│ ├── Productor.java
│ ├── Consumidor.java
│ ├── RecursoCompartido.java
│ ├── SimulacionBloqueoDistribuido.java
│ ├── SeccionCritica.java
│ └── SimuladorBD.java

```
1. Desde la raíz del proyecto:

```bash
javac -d out src/**/*.java
```
2. Ejecutar el Servidor TCP
```
   java -cp out servidor.Servidor
```
3. Ejecutar un Cliente TCP 
```
    java -cp out cliente.Cliente

```
4. Ejecutar la estructura Productor-Consumidor
```
    java -cp out concurrencia.RecursoCompartido

```
5. Ejecutar la Simulación de Bloqueo Distribuido (2 procesos accediendo)
```
    java -cp out concurrencia.SimulacionBloqueoDistribuido

```
6. Ejecutar sección crítica protegida con ReentrantLock
```
    java -cp out concurrencia.SeccionCritica

```
7.  Simular concurrencia de escritura en base de datos (simulada con archivo)
```
    java -cp out concurrencia.SimuladorBD

```
Los registros Logs se crean automaticamente en un archivo registro.log

