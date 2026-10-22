# retos-listas-playlist-Gonz-lez-Olvera

# Retos con listas simples, dobles y circulares

## Integrantes
- Juan Eduardo González Hernández 
- Jose Alejandro Olvera Lugo

## Datos de la actividad
- Asignatura: Estructuras de datos
- Docente: Gabriel Barron
- Fecha: 22/10/26
- Versión asignada: **B** (Lista de reproducción / playlist)

## Conceptos aprendidos

**Lista simple.** Es una cadena de nodos donde cada uno guarda un dato y una referencia al siguiente. Es como una fila de personas donde cada una solo conoce a la de adelante: se recorre en un solo sentido, de la cabeza hasta `null`. Ventajas: es sencilla, gasta poca memoria (un puntero por nodo) y insertar al inicio es O(1). Desventajas: no se puede regresar y, para insertar o eliminar al final, hay que recorrer toda la lista (O(n)). Ejemplo real: la pila de "deshacer" de un editor o una cola de canciones por reproducir.

**Lista doble.** Cada nodo guarda dato, `siguiente` y `anterior`, como una fila donde cada persona conoce al de adelante y al de atrás. Se puede recorrer en ambos sentidos y eliminar un nodo conocido es más fácil porque no hay que buscar al previo. Desventajas: usa más memoria y hay que mantener bien dos punteros por cada enlace; si se olvida uno, la lista queda corrupta. Ejemplo real: el historial del navegador (atrás y adelante) o un reproductor con botones "anterior" y "siguiente".

**Lista circular.** El último nodo apunta de vuelta al primero, así que no existe `null` al final; es como una mesa redonda donde después del último vuelve a tocar el primero. Ventajas: ideal para turnos, rotaciones y bucles, y se puede llegar a cualquier nodo dando vueltas. Desventajas: si se recorre mal hay riesgo de ciclo infinito y hay que cuidar los casos de un solo nodo. Ejemplo real: reproducción en bucle de una playlist, turnos de un juego o planificación round-robin en un sistema operativo.

## Reto 1 – Cola de canciones (lista simple)
**Qué se pidió:** completar `insertarInicio`, `contar` e `invertir` (sin crear nodos nuevos).

- **TODO 1 y 2 (insertarInicio):** primero `nuevo.siguiente = cabeza`, para que el nuevo apunte a toda la lista existente y no se pierda; después `cabeza = nuevo`. El orden importa: si se hace al revés, se pierde la lista.
- **TODO 3 y 4 (contar):** se recorre con `while (actual != null)`; en cada vuelta se suma 1 a `total` y se avanza con `actual = actual.siguiente`.
- **TODO 5 (invertir):** `actual.siguiente = anterior` voltea el enlace hacia atrás. Antes ya se guardó `proximo`, porque al romper el enlace se perdería el resto.
- **TODO 6 y 7:** `anterior = actual` y `actual = proximo` para avanzar un paso.
- **TODO 8:** al terminar, `actual` es `null` y `anterior` quedó en el último nodo original, que ahora es la nueva cabeza: `cabeza = anterior`.

Probamos lista vacía, 5 canciones, inversión y un caso de un solo nodo.

**Capturas:** `capturas/reto1-compilacion.png` y `capturas/reto1-ejecucion.png`

## Reto 2 – Reproductor con anterior y siguiente (lista doble)
**Qué se pidió:** completar `insertarFinal`, `insertarDespuesDe` y el recorrido inverso.

- **TODO 1:** si la lista está vacía, `cabeza = nuevo` y `cola = nuevo`.
- **TODO 2, 3 y 4 (insertarFinal):** `cola.siguiente = nuevo`, `nuevo.anterior = cola` y `cola = nuevo`. Son dos enlaces (ida y vuelta) más la actualización de la cola.
- **TODO 5 (insertarDespuesDe):** los 4 enlaces: `nuevo.anterior = actual`, `nuevo.siguiente = actual.siguiente`, `actual.siguiente.anterior = nuevo` (solo si existe) y `actual.siguiente = nuevo`. Se hacen en ese orden para no perder la referencia a `actual.siguiente`.
- **TODO 6:** si `actual` era la cola, la nueva cola es `nuevo`.
- **mostrarInverso:** se empieza en `cola` y se retrocede con `actual = actual.anterior` hasta `null`.

Probamos insertar en medio, insertar después de la cola y buscar una canción que no existe (devuelve `false`).

**Capturas:** `capturas/reto2-compilacion.png` y `capturas/reto2-ejecucion.png`

## Reto 3 – Reproducción en bucle (lista circular)
**Qué se pidió:** completar `insertar`, `rotar` y `eliminarActual`.

- **TODO 1:** con un solo nodo, `nuevo.siguiente = nuevo` (apunta a sí mismo) y `actual` y `cola` son ese nodo.
- **TODO 2 y 3 (insertar):** `nuevo.siguiente = cola.siguiente` (apunta a la cabeza), `cola.siguiente = nuevo` y `cola = nuevo`, con lo que el círculo queda cerrado.
- **TODO 4 (rotar):** `actual = actual.siguiente`. No hace falta comprobar `null` para "llegar al final", porque en una lista circular no hay final; solo se protege el caso de lista vacía.
- **TODO 5:** si `actual == actual.siguiente` era el único nodo, así que `actual = null` y `cola = null`.
- **TODO 6:** se busca el nodo anterior dando la vuelta con `while (anterior.siguiente != actual)`.
- **TODO 7:** `anterior.siguiente = actual.siguiente` salta el nodo; si `actual` era la cola, `cola = anterior`; por último `actual = actual.siguiente`.

Probamos eliminar un nodo intermedio, la cola y hasta dejar la lista con un nodo y vacía.

## Reflexión final

**1. ¿Qué punteros tuvieron que actualizar en la eliminación de una lista doble y por qué son más que en la simple?**
En una lista doble cada enlace existe en dos direcciones, así que al quitar un nodo hay que arreglar ambos lados. Si el nodo tiene anterior, `anterior.siguiente` debe apuntar al siguiente del nodo; si no tiene anterior, era la cabeza y cambia `cabeza`. Del otro lado, si tiene siguiente, `siguiente.anterior` debe apuntar al anterior del nodo; si no tiene, era la cola y cambia `cola`. En la lista simple solo se corrige un puntero (el del nodo previo), porque nadie apunta hacia atrás. La ventaja es que en la doble no hace falta buscar al previo, porque el propio nodo lo conoce. El costo es que olvidar uno de los dos punteros deja la lista inconsistente: hacia adelante se ve bien y hacia atrás se rompe.

**2. ¿Qué error cometieron (o evitaron) por no considerar el caso de lista vacía o de un solo nodo?**
Evitamos errores gracias a revisar esos casos desde el principio. Por ejemplo, en `insertarFinal` de la lista doble, si no se verifica `cola == null` se intenta hacer `cola.siguiente` y aparece un `NullPointerException`.

**3. ¿Qué pasaría si en la lista circular se recorre con `while (actual != null)`?**
Sería un ciclo infinito. En una lista circular ningún nodo apunta a `null`, porque el último regresa a la cabeza, así que `actual` nunca valdría `null` y el programa seguiría imprimiendo canciones sin parar hasta que lo detengamos o se llene la salida. Por eso se usa `do { ... } while (actual != cabeza)`, que da exactamente una vuelta: imprime el nodo inicial, avanza y se detiene cuando regresa al punto de partida. Usamos `do-while` y no `while` porque el primer nodo debe procesarse al menos una vez; con `while`, al empezar en la cabeza la condición ya sería falsa y no se imprimiría nada. Además hay que revisar antes que la lista no esté vacía.

**4. ¿En qué situación elegirían cada tipo de lista?**
La lista simple cuando solo necesitamos ir hacia adelante y queremos ahorrar memoria, por ejemplo una pila de acciones o una cola de canciones que se reproducen una sola vez. La lista doble cuando hace falta moverse en ambos sentidos o eliminar nodos conocidos con rapidez, como el historial del navegador o un reproductor con botones anterior y siguiente. La lista circular cuando los elementos se repiten en ciclo, por ejemplo turnos de jugadores, tareas de un procesador con round-robin o una playlist en bucle. Elegimos según el recorrido que más se hace: si nunca se regresa, simple; si se va y viene, doble; si no hay un "final" natural, circular.

**5. ¿Qué fue lo más difícil y cómo lo resolvieron como bina?**
Lo más difícil fue seguir los punteros en `insertarDespuesDe` y `eliminarActual`, porque el orden de las asignaciones cambia el resultado: si se reasigna un puntero antes de guardar la referencia necesaria, se pierde parte de la lista.

## Cómo compilar y ejecutar
```bash
cd src
javac Reto1.java
java Reto1

javac Reto2.java
java Reto2

javac Reto3.java
java Reto3
```

## adiossss profe
