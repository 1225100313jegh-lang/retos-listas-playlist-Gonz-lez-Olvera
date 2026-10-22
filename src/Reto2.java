/**
 *Autor Juan Eduardo
 * Reto 2 (Versión B) - Lista doble: Reproductor con anterior y siguiente.
 */
package src;
class NodoDoble {
    String cancion;
    NodoDoble siguiente, anterior;

    NodoDoble(String cancion) { this.cancion = cancion; }
}

class ListaDoble {
    private NodoDoble cabeza, cola;

    /**
     * Inserta una canción al final usando la referencia a la cola (O(1)).
     */
    public void insertarFinal(String cancion) {
        NodoDoble nuevo = new NodoDoble(cancion);
        if (cola == null) {
            // TODO 1: lista vacía, el nuevo es cabeza y cola a la vez
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;   // TODO 2: la cola actual apunta al nuevo
            nuevo.anterior = cola;    // TODO 3: el nuevo apunta de regreso a la cola
            cola = nuevo;             // TODO 4: el nuevo es la nueva cola
        }
    }

    /**
     * Inserta 'nueva' justo después de 'existente'.
     * regresa falso si 'existente' no está en la lista
     */
    public boolean insertarDespuesDe(String existente, String nueva) {
        NodoDoble actual = cabeza;
        while (actual != null && !actual.cancion.equals(existente)) {
            actual = actual.siguiente;
        }
        if (actual == null) return false;

        NodoDoble nuevo = new NodoDoble(nueva);
        // TODO 5: los 4 enlaces (se hacen en este orden para no perder referencias)
        nuevo.anterior = actual;                 // 1) nuevo mira hacia atrás a 'actual'
        nuevo.siguiente = actual.siguiente;      // 2) nuevo mira hacia adelante al que seguía
        if (actual.siguiente != null) {
            actual.siguiente.anterior = nuevo;   // 3) el que seguía mira hacia atrás al nuevo
        }
        actual.siguiente = nuevo;                // 4) 'actual' mira hacia adelante al nuevo

        // TODO 6: si 'actual' era la cola, la nueva cola es 'nuevo'
        if (actual == cola) {
            cola = nuevo;
        }
        return true;
    }

    /** Recorre de la cabeza hasta null. */
    public void mostrarAdelante() {
        NodoDoble actual = cabeza;
        while (actual != null) {
            System.out.print(actual.cancion + " <-> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    /** Recorre desde la cola hasta null usando 'anterior'. */
    public void mostrarInverso() {
        NodoDoble actual = cola;
        while (actual != null) {
            System.out.print(actual.cancion + " <-> ");
            actual = actual.anterior;
        }
        System.out.println("null");
    }
}

public class Reto2 {
    public static void main(String[] args) {
        ListaDoble lista = new ListaDoble();

        // Datos propios (cámbienlos por los de su bina)
        lista.insertarFinal("Laurita Garza");
        lista.insertarFinal("quien como tu");
        lista.insertarFinal("Africa");
        lista.insertarFinal("mujeres divinas");

        System.out.println("Hacia adelante:");
        lista.mostrarAdelante();
        System.out.println("Hacia atras:");
        lista.mostrarInverso();

        System.out.println("\nInsertar 'quien como tu' despues de 'Billie Jean' (intermedio): "
                + lista.insertarDespuesDe("quien como tu", "ese loco soy yo"));
        System.out.println("Insertar 'Creep' despues de 'mujeres divinas' (era la cola): "
                + lista.insertarDespuesDe("mujeres divinas", "la vaca lola"));
        System.out.println("Insertar despues de 'No existe': "
                + lista.insertarDespuesDe("No existe", "X"));

        System.out.println("\nHacia adelante:");
        lista.mostrarAdelante();
        System.out.println("Hacia atras:");
        lista.mostrarInverso();
    }
}
