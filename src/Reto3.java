/**
 * Reto 3 (Versión B) - Lista circular: Reproducción en bucle.
 * Autor Juan Eduardo
 */
package src;
class NodoCircular {
    String cancion;
    NodoCircular siguiente;

    NodoCircular(String cancion) { this.cancion = cancion; }
}

class ListaCircular {
    private NodoCircular actual; // canción en reproducción
    private NodoCircular cola;

    /**
     * Insertamos una canción al final del círculo (entre la cola y la cabeza).
     */
    public void insertar(String cancion) {
        NodoCircular nuevo = new NodoCircular(cancion);
        if (cola == null) {
            // TODO 1: un solo nodo apunta a sí mismo
            nuevo.siguiente = nuevo;
            actual = nuevo;
            cola = nuevo;
        } else {
            nuevo.siguiente = cola.siguiente; // TODO 2: el nuevo apunta a la cabeza (cola.siguiente)
            cola.siguiente = nuevo;           //         y la cola apunta al nuevo
            cola = nuevo;                     // TODO 3: el nuevo es la cola
        }
    }

    /**
     * Avanza n canciones. No hace falta comprobar null "al final":
     * en una lista circular nunca se llega a un final.
     */
    public void rotar(int n) {
        for (int i = 0; i < n; i++) {
            if (actual != null) {          // TODO 4: solo protege el caso de lista vacía
                actual = actual.siguiente;
            }
        }
    }

    /**
     * Elimina la canción en la reproducción y pasa a la siguiente.
     */
    public void eliminarActual() {
        if (cola == null) return;
        if (actual == actual.siguiente) {
            // TODO 5: era el único nodo, la lista queda vacía
            actual = null;
            cola = null;
            return;
        }
        // TODO 6: busca el nodo anterior a 'actual' recorriendo el círculo
        NodoCircular anterior = actual;
        while (anterior.siguiente != actual) {
            anterior = anterior.siguiente;
        }
        // TODO 7: salta 'actual', corrige la cola si hacía falta y avanza
        anterior.siguiente = actual.siguiente;
        if (actual == cola) {
            cola = anterior;
        }
        actual = actual.siguiente;
    }

    /** Muestra una vuelta completa empezando en la canción en reproducción. */
    public void mostrarUnaVuelta() {
        if (actual == null) {
            System.out.println("(lista vacia)");
            return;
        }
        NodoCircular aux = actual;
        do {
            System.out.print(aux.cancion + " -> ");
            aux = aux.siguiente;
        } while (aux != actual);
        System.out.println("(vuelve a " + actual.cancion + ")");
    }
}

public class Reto3 {
    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();

        // Datos propios (cámbienlos por los de su bina)
        lista.insertar("Never say never");
        lista.insertar("la octava maravilla");
        lista.insertar("Luther");
        lista.insertar("Borracho de amor");

        System.out.println("Playlist inicial:");
        lista.mostrarUnaVuelta();

        lista.rotar(2);
        System.out.println("\nDespues de rotar 2:");
        lista.mostrarUnaVuelta();

        lista.eliminarActual();
        System.out.println("\nEliminar la que suena (intermedia):");
        lista.mostrarUnaVuelta();

        // Tras eliminar, 'actual' avanzo y ahora es justo la cola
        lista.eliminarActual();
        System.out.println("\nEliminar la cola:");
        lista.mostrarUnaVuelta();

        lista.eliminarActual();
        System.out.println("\nEliminar hasta quedar con un nodo:");
        lista.mostrarUnaVuelta();

        lista.eliminarActual();
        System.out.println("\nEliminar el unico nodo:");
        lista.mostrarUnaVuelta();
    }
}
