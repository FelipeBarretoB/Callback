# Callback 
<br/>
<p>Este trabajo es la implementación del patron de diseño Callback para un chat entre maquinas, todo desde la consola</p> <br/>

## Ramas

### rama 1
<p>Este proyecto tiene 3 ramas, la parte 1 es la saturación del programa usando el hello world orginal</p>
<br/>
<p>Esta rama solo funciona de manera local, puedes correr los clientes usando java -jar client.jar o server.jar</p>
<br/>
<p>Cuando esten corriendo tanto el servidor y cliente, en el cliente pon cualquier entrada (por ejemplo 1) un espacio y enter, esto empieza un loop infinito con la idea de saturar el cliente, puedes correr cuantos clientes quieras</p>
<br/>
<p>El config del servidor solo tiene 1 thread, así que es probable que se sature rapido el servidor, además, la implementación del fibonacci es recursiva, lo cual no es lo más optino</p>

### rama 2
<p>La segunda es el callback solo con el fibonacci implementado</p>
<br/>
<p>Este rama tambien solo funciona de manera local pero tiene 3 threads, y corre al usar una entrada cualquiera seguido de : y un espacio (por ejemplo: "1: ")</p>
<br/>
<p>Por la implemetanción del callback, el cliente no tendra un timeout, dado que este no espera la respuesta del servior</p>

## Rama principal

<p>La rama master, esta es la implementación del callback con el chat</p>
<br/>
<p>Este callback esta desplegado en hgrid7 (servidor), hgrid9 (cliente) y hgrid10(cliente)</p>
<br/>
<p>Para correr el servidor, usa el comando: java -jar server.jar</p>
<br/>
<p>Para correr el cliente, usa el comando: java -jar client.jar</p>
<br/>
<p>Si quieres desplegar otro cliente debes modificar el config.client de la siguiente forma:</p>
<br/>
<p>Callback.Endpoints = tcp -h [dirección ip de maquina donde esta el cliente o nombre de la maquina] -p [puerto donde quieres exponer el servicio]</p>
<br/>
<p>Ice.Default.Host = [dirección ip de maquina donde esta el servidor o nombre de la maquina]</p>
<br/>
<br>
<p>En el caso de que quieras desplegar otro servidor, solo tienes que modificar el Ice.Default.Host, asignandole la maquina donde se encuentra el servidor</p>
<br/>
<p>Si tienes dudas puedes revisar el config del servidor o cliente de este proyecto</p>
<br/>
<p>Para usar el callback debes desplegar el servidor y al menos 1 cliente, de ahi puede usar el comando "Help" para ver los posibles comandos y que hacen, en todo caso aqui esta la lista</p>
<br/>
<ol>
  <li>BC [msg] : envia un mensaje a todos los clientes conectados</li>
  <li>list clients : lista los clientes conectados</li>
  <li>to [hostname]:[msg] : envia un mensaje a un cliente especifico</li>
  <li>fibonacci: [numero] : retorna la serie de fibonacci hasta [numero]</li>
</ol>
<p>cabe resaltar que esta implementación del fibonacci es con un loop, así que es más rapidas que las de las otras ramas</p>

