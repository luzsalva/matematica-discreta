import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb el comentari "// TO DO".
 *
 * L'avaluació consistirà en:
 *
 * - Si el codi no compila, la nota del grup serà de 0.
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - Tendrem en compte la neteja i organització del codi. Un estandard que podeu seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html.  Algunes
 *   consideracions importants: indentació i espaiat consistent, bona nomenclatura de variables,
 *   declarar les variables el més aprop possible al primer ús (és a dir, evitau blocs de
 *   declaracions). També convé utilitzar el for-each (for (int x : ...)) enlloc del clàssic (for
 *   (int i = 0; ...)) sempre que no necessiteu l'índex del recorregut.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1: Luz Salvá Castro 
 * - Nom 2: Pablo Ramos Llabrérs 
 * - Nom 3: Carlos Segura Hernando
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital que obrirem abans de la data que se us
 * hagui comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més
 * fàcilment les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat,
 * assegurau-vos de que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
  /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * Els mètodes reben de paràmetre l'univers (representat com un array) i els predicats adients
   * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un element de
   * l'univers, podeu fer-ho com `p.test(x)`, que té com resultat un booleà (true si `P(x)` és
   * cert). Els predicats de dues variables són de tipus `BiPredicate<Integer, Integer>` i
   * similarment s'avaluen com `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats retorneu `true`
   * o `false` segons si la proposició donada és certa (suposau que l'univers és suficientment
   * petit com per poder provar tots els casos que faci falta).
   */
  static class Tema1 {
    /*
     * És cert que ∀x ∃!y. P(x) -> Q(x,y) ?
     */
    static boolean exercici1(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {
      boolean pdexCumplido=true;
        boolean qdexyCumplido=false;
        int contador;
        int contadorGlobal=0;
        
        for(int i=0;i<universe.length;i++){
            if (p.test(universe[i])==false){
                pdexCumplido=false;
            }
            contador=0;
            for(int u=0;u<universe.length;u++){
                if(q.test(universe[i],universe[u])==true){
                    contador++;
                }
            }
            if (contador==1){
                qdexyCumplido=true;
            }
            if (!((pdexCumplido==true)&&(qdexyCumplido==false))){
                contadorGlobal++;
            }
        }
        return contadorGlobal==universe.length;
    }

    /*
     * És cert que ∃!x ∀y. P(y) -> Q(x,y) ?
     */
    static boolean exercici2(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {
       for (int y : universe) {  
        if (p.test(y)) {
            boolean existeUnaX = false;
            for (int x : universe) {
                if (q.test(x, y)) {
                    if (existeUnaX) {
                        return false;
                    } else {
                        existeUnaX = true;
                    }
                }
            }
            if (!existeUnaX) {
                return false;
            }
        }
    }
    return true;
    }

    /*
     * És cert que ∃x,y ∀z. P(x,z) ⊕ Q(y,z) ?
     */
    static boolean exercici3(int[] universe, BiPredicate<Integer, Integer> p, BiPredicate<Integer, Integer> q) {
      // Utilizamos 3 bucles para comprobar todas las combinaciones de x, y y z en el array
    for (int z : universe) {
        // Variable para comprobar si se cumple ∀ z
        boolean zExists = false; 
        for (int x : universe) {
            boolean valorP = true;
            for (int y : universe) {
                boolean valorQ = true;
                if (!p.test(x, z)) {
                    valorP = false;
                }
                if (!q.test(y, z)) {
                    valorQ = false;
                }
                // Verificamos si se cumple P(x, z) ⊕ Q(y, z)
                if (valorP ^ valorQ) {
                    //se verifica que ∀ z se cumple el enunciado
                    zExists = true; 
                    // Salimos del bucle "y" ya que no es necesario seguir probando con otros valores de y
                    break; 
                }
            }

            if (zExists) {
                // Salimos del bucle "x" ya que no es necesario seguir probando con otros valores de x
                break; 
            }
        }
        if (!zExists) {
            System.out.println("Ejercicio 3 Tema 1 - FALSO ");
            return false;
        }
    }
    System.out.println("Ejercicio 3 Tema 1 - VERDADERO ");
    return true;
}
    

    /*
     * És cert que (∀x. P(x)) -> (∀x. Q(x)) ?
     */
    static boolean exercici4(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
        boolean pdexCumplido=true;
        boolean qdexCumplido=true;
        //P(x)
        for(int i=0;i<universe.length;i++){
            if (p.test(universe[i])==false){
                pdexCumplido=false;
                break;
            }
        }
        //Q(x)
        for(int i=0;i<universe.length;i++){
            if(q.test(universe[i])==false){
                qdexCumplido=false;
                break;
            }
        }
        return !((pdexCumplido==true)&&(qdexCumplido==false));
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // ∀x ∃!y. P(x) -> Q(x,y) ?

      assertThat(
          exercici1(
              new int[] { 2, 3, 5, 6 },
              x -> x != 4,
              (x, y) -> x == y
          )
      );

      assertThat(
          !exercici1(
              new int[] { -2, -1, 0, 1, 2, 3 },
              x -> x != 0,
              (x, y) -> x * y == 1
          )
      );

      // Exercici 2
      // ∃!x ∀y. P(y) -> Q(x,y) ?

      assertThat(
          exercici2(
              new int[] { -1, 1, 2, 3, 4 },
              y -> y <= 0,
              (x, y) -> x == -y
          )
      );

      assertThat(
          !exercici2(
              new int[] { -2, -1, 1, 2, 3, 4 },
              y -> y < 0,
              (x, y) -> x * y == 1
          )
      );

      // Exercici 3
      // ∃x,y ∀z. P(x,z) ⊕ Q(y,z) ?

      assertThat(
          exercici3(
              new int[] { 2, 3, 4, 5, 6, 7, 8 },
              (x, z) -> z % x == 0,
              (y, z) -> z % y == 1
          )
      );

      assertThat(
          !exercici3(
              new int[] { 2, 3 },
              (x, z) -> z % x == 1,
              (y, z) -> z % y == 1
          )
      );

      // Exercici 4
      // (∀x. P(x)) -> (∀x. Q(x)) ?

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3, 4, 5, 8, 9, 16 },
              x -> x % 2 == 0, // x és múltiple de 2
              x -> x % 4 == 0 // x és múltiple de 4
          )
      );

      assertThat(
          !exercici4(
              new int[] { 0, 2, 4, 6, 8, 16 },
              x -> x % 2 == 0, // x és múltiple de 2
              x -> x % 4 == 0 // x és múltiple de 4
          )
      );
    }
  }
  


  /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * Per senzillesa tractarem els conjunts com arrays (sense elements repetits). Per tant, un
   * conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam donant el domini
   * int[] a, el codomini int[] b, i f un objecte de tipus Function<Integer, Integer> que podeu
   * avaluar com f.apply(x) (on x és d'a i el resultat f.apply(x) és de b).
   */
  static class Tema2 {
    /*
     * Comprovau si la relació `rel` definida sobre `a` és d'equivalència.
     *
     * Podeu soposar que `a` està ordenat de menor a major.
     */
    static boolean exercici1(int[] a, int[][] rel) {
    // Check if the relation is reflexive
    for (int i = 0; i < a.length; i++) {
        boolean reflexiva = false;
        for (int j = 0; j < rel.length; j++) {
            if (rel[j][0] == a[i] && rel[j][1] == a[i]) {
                reflexiva = true;
                break;
            }
        }
        if (!reflexiva) {
            return false;
        }
    }

    // Check if the relation is symmetric
    for (int i = 0; i < rel.length; i++) {
        boolean simetrica = false;
        for (int j = 0; j < rel.length; j++) {
            if (rel[i][0] == rel[j][1] && rel[i][1] == rel[j][0]) {
                simetrica = true;
                break;
            }
        }
        if (!simetrica) {
            return false;
        }
    }

    // Check if the relation is transitive
    for (int i = 0; i < rel.length; i++) {
        for (int j = 0; j < rel.length; j++) {
            if (rel[i][1] == rel[j][0]) {
                boolean transitiva = false;
                for (int k = 0; k < rel.length; k++) {
                    if (rel[i][0] == rel[k][0] && rel[j][1] == rel[k][1]) {
                        transitiva = true;
                        break;
                    }
                }
                if (!transitiva) {
                    return false;
                }
            }
        }
    }

    return true;
}

    /*
     * Comprovau si la relació `rel` definida sobre `a` és d'equivalència. Si ho és, retornau el
     * cardinal del conjunt quocient de `a` sobre `rel`. Si no, retornau -1.
     *
     * Podeu soposar que `a` està ordenat de menor a major.
     */
static int exercici2(int[] a, int[][] rel) {
    int contador_reflexiva = 0;

    for (int x[] : rel) {
        if ((x[0] == x[1])) {
            contador_reflexiva++;
        }

        if (x[0] != x[1]) {
            for (int y[] : rel) {
                if (!(y[0] == y[1]) && (y[0] == y[1])) {
                    return -1;
                }
            }
        }

        for (int y[] : rel) {
            for (int z[] : rel) {
                if (((x[0] == y[1]) && (y[1] == z[0]))) {
                    if (!(x[0] == z[0])) {
                        return -1;
                    }
                }
            }
        }
    }

    if (contador_reflexiva >= a.length) {
        List<List<Integer>> clasesDeEquivalencia = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            clasesDeEquivalencia.add(new ArrayList<>());
        }
        for (int[] pair : rel) {
            int x = pair[0];
            int y = pair[1];
            clasesDeEquivalencia.get(x).add(y);
            clasesDeEquivalencia.get(y).add(x);
        }

        Set<Set<Integer>> clasesUnicasDeEquivalencia = new HashSet<>();
        for (int x : a) {
            Set<Integer> set = new HashSet<>(clasesDeEquivalencia.get(x));
            clasesUnicasDeEquivalencia.add(set);
        }

        return clasesUnicasDeEquivalencia.size();
    } else {
        return -1;
    }
}

    /*
     * Comprovau si la relació `rel` definida entre `a` i `b` és una funció.
     *
     * Podeu soposar que `a` i `b` estan ordenats de menor a major.
     */
static boolean exercici3(int[] a, int[] b, int[][] rel) {
          for (int valorA : a) {
              boolean relacionEncontrada = false;
              for (int[] parejaRel : rel) {
                  if (parejaRel[0] == valorA) {
                      // Se encontró una relación para el valor en a[i]
                      if (relacionEncontrada) {
                          // Ya se encontró una relación previamente, por lo que no es una función
                          System.out.println("Ejercicio 3 Tema 2 - FALSO ");
                          return false;
                      }
                      relacionEncontrada = true;
                      if (parejaRel[1] != b[valorA]) {
                          // La relación no cumple con la correspondencia en b[i]
                          System.out.println("Ejercicio 3 Tema 2 - FALSO ");
                          return false;
                      }
                  }
              }
              if (!relacionEncontrada) {
                  // No se encontró ninguna relación para el valor en a[i]
                  System.out.println("Ejercicio 3 Tema 2 - FALSO ");
                  return false;
              }
          }
          System.out.println("Ejercicio 3 Tema 2 - VERDADERO ");
          return true;
      }

    /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Retornau:
     * - Si és exhaustiva, el màxim cardinal de l'antiimatge de cada element de `codom`.
     * - Si no, si és injectiva, el cardinal de l'imatge de `f` menys el cardinal de `codom`.
     * - En qualsevol altre cas, retornau 0.
     *
     * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major.
     */
    static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
      int maximo_Valor=0;
        //para asignar un minimo en la primera iteracion
        int minimo_Valor=codom.length;
        
        int[] numeros=new int[codom.length];
        
        for(int i=0;i<codom.length;i++){
            int imagen=codom[i];
            int contador=0;
            for(int u=0;u<dom.length;u++){
                int valor=dom[u];
                if(f.apply(valor)==imagen){
                    contador++;
                }
            }
            numeros[i]=contador;
            
            if(numeros[i]>maximo_Valor){
                maximo_Valor=numeros[i];
            }
            else if(numeros[i]<minimo_Valor){
                minimo_Valor=numeros[i];
            }
        }
        if(minimo_Valor>0) {
            return maximo_Valor;
        }
        else if(maximo_Valor==1){
            return dom.length-codom.length;
        }
        else {
            return 0;
        }
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `rel` és d'equivalencia?

      assertThat(
          exercici1(
              new int[] { 0, 1, 2, 3 },
              new int[][] { {0, 0}, {1, 1}, {2, 2}, {3, 3}, {1, 3}, {3, 1} }
          )
      );

      assertThat(
          !exercici1(
              new int[] { 0, 1, 2, 3 },
              new int[][] { {0, 0}, {1, 1}, {2, 2}, {3, 3}, {1, 2}, {1, 3}, {2, 1}, {3, 1} }
          )
      );

      // Exercici 2
      // si `rel` és d'equivalència, quants d'elements té el seu quocient?

      final int[] int09 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

      assertThat(
          exercici2(
            int09,
            generateRel(int09, int09, (x, y) -> x % 3 == y % 3)
          )
          == 3
      );

      assertThat(
          exercici2(
              new int[] { 1, 2, 3 },
              new int[][] { {1, 1}, {2, 2} }
          )
          == -1
      );

      // Exercici 3
      // `rel` és una funció?

      final int[] int05 = { 0, 1, 2, 3, 4, 5 };

      assertThat(
          exercici3(
            int05,
            int09,
            generateRel(int05, int09, (x, y) -> x == y)
          )
      );

      assertThat(
          !exercici3(
            int05,
            int09,
            generateRel(int05, int09, (x, y) -> x == y/2)
          )
      );

      // Exercici 4
      // el major |f^-1(y)| de cada y de `codom` si f és exhaustiva
      // sino, |im f| - |codom| si és injectiva
      // sino, 0

      assertThat(
          exercici4(
            int09,
            int05,
            x -> x / 4
          )
          == 0
      );

      assertThat(
          exercici4(
            int05,
            int09,
            x -> x + 3
          )
          == int05.length - int09.length
      );

      assertThat(
          exercici4(
            int05,
            int05,
            x -> (x + 3) % 6
          )
          == 1
      );
    }

    /// Genera un array int[][] amb els elements {a, b} (a de as, b de bs) que satisfàn pred.test(a, b)
    static int[][] generateRel(int[] as, int[] bs, BiPredicate<Integer, Integer> pred) {
      ArrayList<int[]> rel = new ArrayList<>();

      for (int a : as) {
        for (int b : bs) {
          if (pred.test(a, b)) {
            rel.add(new int[] { a, b });
          }
        }
      }

      return rel.toArray(new int[][] {});
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 3 (Grafs).
   *
   * Donarem els grafs en forma de diccionari d'adjacència, és a dir, un graf serà un array
   * on cada element i-èssim serà un array ordenat que contendrà els índexos dels vèrtexos adjacents
   * al i-èssim vèrtex. Per exemple, el graf cicle C_3 vendria donat per
   *
   *  int[][] g = {{1,2}, {0,2}, {0,1}}  (no dirigit: v0 -> {v1, v2}, v1 -> {v0, v2}, v2 -> {v0,v1})
   *  int[][] g = {{1}, {2}, {0}}        (dirigit: v0 -> {v1}, v1 -> {v2}, v2 -> {v0})
   *
   * Podeu suposar que cap dels grafs té llaços.
   */
  static class Tema3 {
    /*
     * Retornau l'ordre menys la mida del graf (no dirigit).
     */
    static int exercici1(int[][] g) {
      int orden=g.length;
        int sumaNodos = 0;
        for(int i=0; i<orden; i++){
            sumaNodos = sumaNodos+g[i].length;
        }
        return orden-sumaNodos/2;
    }

    /*
     * Suposau que el graf (no dirigit) és connex. És bipartit?
     */
static boolean exercici2(int[][] g) {
    int n = g.length;
    int[] asignación = new int[n];
    Arrays.fill(asignación, -1);

    for (int i = 0; i < n; i++) {
        if (asignación[i] == -1) {
            asignación[i] = 1;
            List<Integer> lista = new ArrayList<>();
            lista.add(i);

            while (!lista.isEmpty()) {
                int u = lista.remove(0);
                for (int v : g[u]) {
                    if (asignación[v] == -1) {
                        asignación[v] = 1 - asignación[u];
                        lista.add(v);
                    } else if (asignación[v] == asignación[u]) {
                        return false;
                    }
                }
            }
        }
    }
    return true;
}

    /*
     * Suposau que el graf és un DAG. Retornau el nombre de descendents amb grau de sortida 0 del
     * vèrtex i-èssim.
     */
      static int exercici3(int[][] g, int i) {
          //Asignamos al contador de descendientes con grado de salida 0 el método
          // para analizar los descendientes de un vértice dado
          int descendientesGS0= analisisDescendientes(g, i, new boolean[g.length]);; 

          if (descendientesGS0> 0) {
              System.out.println("Ejercicio 3 Tema 3 - "+descendientesGS0);
              return descendientesGS0;
          } else {
              //No hay descendientes con grado de salida 0
              return -1; 
          }
      }

      static int analisisDescendientes(int[][] g, int vertice, boolean[] visitados) {
          //Marca los vértices visitados durante el análisis de los descendientes
          visitados[vertice] = true;
          //Inicialización del contador de descendientes con grado de salida 0
          int contador = 0;

          for (int verticeAdj : g[vertice]) {
              //Nos aseguramos de que un vértice adyacente no se cuente 
              //nuevamente si ya ha sido visitado
              if (!visitados[verticeAdj]) {
                  if (g[verticeAdj].length == 0) {
                      contador++;
                  } else {
                      //Si el vértice adjunto tiene grado de salida no nulo, 
                      //explora sus descendientes de forma recursiva
                      contador += analisisDescendientes(g, verticeAdj, visitados);
                  }
              }
          }
          return contador;
      }

    /*
     * Donat un arbre arrelat (dirigit, suposau que l'arrel es el vèrtex 0), trobau-ne el diàmetre.
     * Suposau que totes les arestes tenen pes 1.
     */
    static int exercici4(int[][] g) {
        int orden= g.length;
        int[][] adyaciencia = new int[orden][orden];
        
        int[][] distancia = new int[orden][orden];
        for(int i=0;i<orden;i++){
            for(int u=0;u<orden;u++){
                adyaciencia[i][u]=0;
                distancia[i][u]=0;
            }
       }
        for(int i=0; i<orden; i++){
            for(int u=0; u<g[i].length; u++){
                adyaciencia[i][g[i][u]]=1;
                adyaciencia[g[i][u]][i]=1;
            }
        }
        int[][] caminos1;
        caminos1=adyaciencia;
        for(int k=0;k<orden;k++){
            for(int i=0;i<orden;i++){
                for(int u=0;u<orden;u++){
                    if(distancia[i][u]==0 && caminos1[i][u]>0 && i!=u ){
                        distancia[i][u]=k+1;
                    }
                }
            }
            int[][] caminos2 = new int[orden][orden];
            //elevo adj
            for(int i=0;i<orden;i++){
                for(int u=0;u<orden;u++){
                    int suma=0;
                    for(int h=0; h<orden; h++){
                        suma=suma+(caminos1[i][h]*adyaciencia[h][u]);
                    }
                    caminos2[i][u]=suma;
                }
            }
            caminos1 = caminos2;
        }
        int Valor_Maximo=0;
        for(int i=0;i<orden;i++){
            for(int u=0;u<orden;u++){
               if(distancia[i][u]>Valor_Maximo){
                   Valor_Maximo=distancia[i][u];
               }
            }
        } 
      return Valor_Maximo;
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      final int[][] undirectedK6 = {
        { 1, 2, 3, 4, 5 },
        { 0, 2, 3, 4, 5 },
        { 0, 1, 3, 4, 5 },
        { 0, 1, 2, 4, 5 },
        { 0, 1, 2, 3, 5 },
        { 0, 1, 2, 3, 4 },
      };

      /*
         1
      4  0  2
         3
      */
      final int[][] undirectedW4 = {
        { 1, 2, 3, 4 },
        { 0, 2, 4 },
        { 0, 1, 3 },
        { 0, 2, 4 },
        { 0, 1, 3 },
      };

      // 0, 1, 2 | 3, 4
      final int[][] undirectedK23 = {
        { 3, 4 },
        { 3, 4 },
        { 3, 4 },
        { 0, 1, 2 },
        { 0, 1, 2 },
      };

      /*
             7
             0
           1   2
             3   8
             4
           5   6
      */
      final int[][] directedG1 = {
        { 1, 2 }, // 0
        { 3 },    // 1
        { 3, 8 }, // 2
        { 4 },    // 3
        { 5, 6 }, // 4
        {},       // 5
        {},       // 6
        { 0 },    // 7
        {},
      };


      /*
              0
         1    2     3
            4   5   6
           7 8
      */

      final int[][] directedRTree1 = {
        { 1, 2, 3 }, // 0 = r
        {},          // 1
        { 4, 5 },    // 2
        { 6 },       // 3
        { 7, 8 },    // 4
        {},          // 5
        {},          // 6
        {},          // 7
        {},          // 8
      };

      /*
            0
            1
         2     3
             4   5
                6  7
      */

      final int[][] directedRTree2 = {
        { 1 },
        { 2, 3 },
        {},
        { 4, 5 },
        {},
        { 6, 7 },
        {},
        {},
      };

      assertThat(exercici1(undirectedK6) == 6 - 5*6/2);
      assertThat(exercici1(undirectedW4) == 5 - 2*4);

      assertThat(exercici2(undirectedK23));
      assertThat(!exercici2(undirectedK6));

      assertThat(exercici3(directedG1, 0) == 3);
      assertThat(exercici3(directedRTree1, 2) == 3);

      assertThat(exercici4(directedRTree1) == 5);
      assertThat(exercici4(directedRTree2) == 4);
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 4 (Aritmètica).
   *
   * Per calcular residus podeu utilitzar l'operador %, però anau alerta amb els signes.
   * Podeu suposar que cada vegada que se menciona un mòdul, és major que 1.
   */
  static class Tema4 {
    /*
     * Donau la solució de l'equació
     *
     *   ax ≡ b (mod n),
     *
     * Els paràmetres `a` i `b` poden ser negatius (`b` pot ser zero), però podeu suposar que n > 1.
     *
     * Si la solució és x ≡ c (mod m), retornau `new int[] { c, m }`, amb 0 ⩽ c < m.
     * Si no en té, retornau null.
     */
    static int[] exercici1(int a, int b, int n) {
      if(a<0){
            a=-a;
            b=-b;
        }
        if(b<0){
            b=b+n;
        }

        int[] q=new int[50];
        int[] w=new int[50];
        int[] x=new int[50];

        w[0]=1;
        w[1]=0;
        
        x[0]=0;
        x[1]=1;
        
        int resto=a;
        int A=n;
        int B=a;
        int C=b;
        int indiceTemporal=2;
        int t=0;
        
        for(;resto>0;indiceTemporal++){
            q[indiceTemporal-1]=A/B;
            t=resto;
            resto=A%B;
            w[indiceTemporal]=w[indiceTemporal-2]-w[indiceTemporal-1]*q[indiceTemporal-1];
            x[indiceTemporal]=x[indiceTemporal-2]-x[indiceTemporal-1]*q[indiceTemporal-1];
            A=B;
            B=resto;
        }
        int c=x[indiceTemporal-2]*C/t;
        int m=n/t;

        while(c<0){
            c=c+m;
        }

        if(C%t==0){
            return new int[] { c, m }; 
        }
        else{
            return null;
        } 
    }

    /*
     * Donau la solució (totes) del sistema d'equacions
     *
     *  { x ≡ b[0] (mod n[0])
     *  { x ≡ b[1] (mod n[1])
     *  { x ≡ b[2] (mod n[2])
     *  { ...
     *
     * Cada b[i] pot ser negatiu o zero, però podeu suposar que n[i] > 1. També podeu suposar
     * que els dos arrays tenen la mateixa longitud.
     *
     * Si la solució és de la forma x ≡ c (mod m), retornau `new int[] { c, m }`, amb 0 ⩽ c < m.
     * Si no en té, retornau null.
     */
    static int[] exercici2a(int[] b, int[] n) {
    int x = 0;
    int N = 1;
    for (int ni : n) {
        N *= ni;
    }
    for (int i = 0; i < b.length; i++) {
        int Ni = N / n[i];
        int Mi = -1;
        for (int j = 1; j < n[i]; j++) {
            if ((Ni * j) % n[i] == 1) {
                Mi = j;
                break;
            }
        }
        if (Mi == -1) {
            return null;
        }
        x += b[i] * Ni * Mi;
    }
    x %= N;
    if (x < 0) {
        x += N;
    }
    return new int[]{x, N};
}

    /*
     * Donau la solució (totes) del sistema d'equacions
     *
     *  { a[0]·x ≡ b[0] (mod n[0])
     *  { a[1]·x ≡ b[1] (mod n[1])
     *  { a[2]·x ≡ b[2] (mod n[2])
     *  { ...
     *
     * Cada a[i] o b[i] pot ser negatiu (b[i] pot ser zero), però podeu suposar que n[i] > 1. També
     * podeu suposar que els tres arrays tenen la mateixa longitud.
     *
     * Si la solució és de la forma x ≡ c (mod m), retornau `new int[] { c, m }`, amb 0 ⩽ c < m.
     * Si no en té, retornau null.
     */
    static int[] exercici2b(int[] a, int[] b, int[] n) {
    int x = 0;
    int m = 1;
    for (int i = 0; i < a.length; i++) {
        while (a[i] < 0) {
            a[i] += n[i];
            b[i] += n[i];
        }
        a[i] %= n[i];
        b[i] %= n[i];
        if (a[i] == 0 || n[i] == 0) return null;
        int d = gcd(a[i], n[i]);
        if (b[i] % d != 0) return null;
        a[i] /= d;
        b[i] /= d;
        n[i] /= d;
        if (n[i] == 0) return null;
        int t = (b[i] * inversa(a[i], n[i])) % n[i];
        if ((t - x) % gcd(m, n[i]) != 0) return null;
        x = teoremaChinoResto(x, m, t, n[i]);
        m *= n[i];
    }
    if (x < 0) x += m;
    return new int[]{x, m};
}

static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
}

static int inversa(int a, int m) {
    if (m == 1 || m == 0) return 0;
    int m0 = m;
    int y = 0;
    int x = 1;
    while (a > 1) {
        if (m == 0) return 0;
        int q = a / m;
        int t = m;
        m = a % m;
        a = t;
        t = y;
        y = x - q * y;
        x = t;
    }
    if (x < 0) x += m0;
    return x;
}

static int teoremaChinoResto(int x1, int m1, int x2, int m2) {
    if (m1 == 0 || m2 == 0) return 0;
    return ((x2 - x1) * inversa(m1, m2) % m2 * m1 + x1) % (m1 * m2);
}

    /*
     * Suposau que n > 1. Donau-ne la seva descomposició en nombres primers, ordenada de menor a
     * major, on cada primer apareix tantes vegades com el seu ordre. Per exemple,
     *
     * exercici4a(300) --> new int[] { 2, 2, 3, 5, 5 }
     *
     * No fa falta que cerqueu algorismes avançats de factorització, podeu utilitzar la força bruta
     * (el que coneixeu com el mètode manual d'anar provant).
     */
      static ArrayList<Integer> exercici3a(int n) {
          //Almacena los factores primos de n
          ArrayList<Integer> factoresPrimos = new ArrayList<>();
          System.out.print("Ejercicio 3a Tema 4 - (" +n+ ")-->");
          for (int i = 2; i <= n; i++) {
              //Comprobamos si n es divisible por el factor primo actual
              while (n % i == 0) {
                  factoresPrimos.add(i);
                  //Actualizamos el valor de n dividiéndolo por el factor primo
                  //(n /= i)
                  n /= i;
              }
          }
          System.out.println(factoresPrimos);
          return factoresPrimos;
      }

    /*
     * Retornau el nombre d'elements invertibles a Z mòdul n³.
     *
     * Alerta: podeu suposar que el resultat hi cap a un int (32 bits a Java), però n³ no té perquè.
     * De fet, no doneu per suposat que pogueu tractar res més gran que el resultat.
     *
     * No podeu utilitzar `long` per solucionar aquest problema. Necessitareu l'exercici 3a.
     * No, tampoc podeu utilitzar `double`.
     */
static int exercici3b(int n) {
    ArrayList<Integer> factoresPrimos = exercici3a(n);
    int phi = 1;

    while (!factoresPrimos.isEmpty()) {
        // Obtener el primer factor primo 
        int numeroPrimoActual = factoresPrimos.get(0);
        // contador para almacenar el número de veces que este factor primo aparece en la lista
        int repeticionPrimo = 0;
        while (!factoresPrimos.isEmpty() && factoresPrimos.get(0) == numeroPrimoActual) {
            repeticionPrimo++;
            factoresPrimos.remove(0);
        }

        // Actualizar el valor de phi 
        phi *= (Math.pow(numeroPrimoActual, repeticionPrimo) - Math.pow(numeroPrimoActual, repeticionPrimo - 1));
    }

    // Multiplicar phi por n^2 y devolver el resultado
    return phi * n * n;
}

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      assertThat(Arrays.equals(exercici1(17, 1, 30), new int[] { 23, 30 }));
      assertThat(Arrays.equals(exercici1(-2, -4, 6), new int[] { 2, 3 }));
      assertThat(exercici1(2, 3, 6) == null);

      assertThat(
        exercici2a(
          new int[] { 1, 0 },
          new int[] { 2, 4 }
        )
        == null
      );

      assertThat(
        Arrays.equals(
          exercici2a(
            new int[] { 3, -1, 2 },
            new int[] { 5,  8, 9 }
          ),
          new int[] { 263, 360 }
        )
      );

      assertThat(
        exercici2b(
          new int[] { 1, 1 },
          new int[] { 1, 0 },
          new int[] { 2, 4 }
        )
        == null
      );

      assertThat(
        Arrays.equals(
          exercici2b(
            new int[] { 2,  -1, 5 },
            new int[] { 6,   1, 1 },
            new int[] { 10,  8, 9 }
          ),
          new int[] { 263, 360 }
        )
      );

      assertThat(exercici3a(10).equals(List.of(2, 5)));
      assertThat(exercici3a(1291).equals(List.of(1291)));
      assertThat(exercici3a(1292).equals(List.of(2, 2, 17, 19 )));

      assertThat(exercici3b(10) == 400);

      // Aquí 1292³ ocupa més de 32 bits amb el signe, però es pot resoldre sense calcular n³.
      assertThat(exercici3b(1292) == 961_496_064);

      // Aquest exemple té el resultat fora de rang
      //assertThat(exercici3b(1291) == 2_150_018_490);
    }
  }

  /*
   * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
   */

  public static void main(String[] args) {
    Tema1.tests();
    Tema2.tests();
    Tema3.tests();
    Tema4.tests();
  }

  /// Si b és cert, no fa res. Si b és fals, llança una excepció (AssertionError).
  static void assertThat(boolean b) {
    if (!b)
      throw new AssertionError();
    }
}



// vim: set textwidth=100 shiftwidth=2 expandtab :
