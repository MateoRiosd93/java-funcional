package org.java.funcional;

import org.java.funcional.model.Person;
import org.java.funcional.model.Product;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Person mateo = new Person(1, "Mateo", LocalDate.of(1993,7,29));
        Person santiago = new Person(2, "Santiago", LocalDate.of(1994,12,28));
        Person alejandra = new Person(3, "Alejandra", LocalDate.of(1995,6,1));
        Person maria = new Person(4, "Maria Fernanda", LocalDate.of(1995,10,11));
        Person gloria = new Person(5, "Gloria", LocalDate.of(1961,4,3));
        Person ivan = new Person(6, "Ivan", LocalDate.of(1963,8,9));
        Person roma = new Person(7, "Roma", LocalDate.of(2022, 9, 8));

        Product play = new Product(1, "Play station 5", 2500000.0);
        Product balon = new Product(2, "Balon adidas", 120000.0);
        Product monitor = new Product(3, "Monitor LG", 500000.0);
        Product teclado = new Product(4, "Teclado logitech", 2500000.0);
        Product monitor2 = new Product(5, "Monitor LG", 500000.0);

        List<Person> personas = Arrays.asList(mateo, santiago, alejandra, maria, gloria, ivan, roma);
        List<Product> productos = Arrays.asList(play, balon, monitor, teclado, monitor2);

        /* Lambda (En programacion funcional)
         * Basicamente es un metodo que se pasa por referencia es una manera corta de crear una funcion () -> {}
         *
         * Una lambda tambien puede describirse como una funcion anonima (parametro) -> { cuerpo de la funcion }
         *
         * Java lo que hace es que convierte esta lambda en una implementacion de una Interfaz
         * Funcinal @FunctionalInterface, como Consumer, Function, Predicate, etc...
         *
         * Sirve:
         * Para escribir código más limpio y corto
         * Para trabajar fácilmente con streams, listas, filtros, y transformaciones de datos
         * Para usar programación funcional en Java
         */


        // Ejemplo: Recorer e imprimir en pantalla cada una de las personas de la lista
        // Imperativo
        for(int i = 0; i < personas.size(); i++){
            System.out.println(personas.get(i));
        }

        System.out.println("///////////////");

        for(Person persona : personas){
            System.out.println(persona);
        }


        System.out.println("///////////////");
        // Funcional
        personas.forEach(persona -> System.out.println(persona));
        // Metodos como referencia: si tenemos una lambda en donde el parametro que recibe es el mismo
        // que se envia a la funcion que se esta llamando podemos expresarlo de la siguiente manera.
        System.out.println("///////////////");
        personas.forEach(System.out::println);


        // NOTA: Cuando trabajamos en la programacion funcional debemos pensar en que es lo que se necesita
        // mas no en como lo necesitas!


        // Predicate @FunctionalInterface <Parametro que recibe>
        Predicate<Person> getOlders = persona -> Main.getAge(persona.getBirthDate()) >= 18;

        // stream(): es un metodo que facilita trabajar de una forma declarativa con las colecciones
        // filter (parametro: Predicate) Permite filtrar una lista dependiendo de una condicion retornando una nueva lista
        // filtrar todas las personas que tengan mas de 18 años
        System.out.println("///////////////");
        List<Person> mayoresDeEdad = personas.stream()
                .filter(getOlders)
                .toList();

        Main.printList(mayoresDeEdad);


        // Interfaz @FunctionalInterface <Parametro que recibe, Parametro que retorna>
        Function<LocalDate, Integer> getAges = Main::getAge;

        // map (parametro: Function) Permite devolver una nueva lista
        // Crear una lista con todas las edades de las personas
        System.out.println("///////////////");
        List<Integer> edades = personas.stream()
                .map(Person::getBirthDate)
                .map(getAges)
                .toList();

        Main.printList(edades);

        Comparator<Person> byNamesAsc = (persona1, person2) -> persona1.getName().compareTo(person2.getName());
        Comparator<Person> byNamesAsc2 = Comparator.comparing(Person::getName);

        System.out.println("///////////////");

        // sorted (parametro: Comparator)
        List<Person> listOrdenada = personas.stream()
                .sorted(byNamesAsc2)
                .toList();

        Main.printList(listOrdenada);


        // match (parametro: Predicate)
        // Predicate para evaluar que person.name empiece con la letra M
        Predicate<Person> startWithM = person -> person.getName().startsWith("M");
        System.out.println("///////////////");

        // anyMatch: No recorre tod@ el stream, termina en la primera coincidencia
        Boolean respuesta1 = personas.stream()
                .anyMatch(startWithM);

        System.out.println(respuesta1);
        System.out.println("///////////////");

        // allMatch: recorre tod@ el stream bajo la condicion. si todos cumplen return true de lo contrario retorna false
        Boolean respuesta2 = personas.stream()
                .allMatch(startWithM);

        System.out.println(respuesta2);
        System.out.println("///////////////");

        // noneMatch: recorre tod@ el stream bajo la condicion. si ninguno cumple return true de lo contrario retorna false
        Boolean respuesta3 = personas.stream()
                .noneMatch(startWithM);

        System.out.println(respuesta3);
        System.out.println("///////////////");

        // skip -  limit : Sirve para el tema de paginacion
        int pageNumber = 0;
        int pageSize = 2;
        List<Person> filteredList4 = personas.stream()
                .skip(pageNumber * pageSize)
                .limit(pageSize)
                .toList();

        Main.printList(filteredList4);
        System.out.println("///////////////");

        // Collectors
        // groupingBy : Ejemplo agrupar productos que sean mayores a 250mil
        Map<Double, List<Product>> filteredProducts = productos.stream()
                .filter(product -> product.getPrice() > 250000.0)
                .collect(Collectors.groupingBy(Product::getPrice));

        System.out.println(filteredProducts);
        System.out.println("///////////////");

        // Counting : Ejemplo agrupa por nombre y cuenta cuantas veces se encuentra el producto
        Map<String, Long> countProducts = productos.stream()
                .collect(Collectors.groupingBy(Product::getName, Collectors.counting()));

        System.out.println(countProducts);
        System.out.println("///////////////");

        // Agrupar por nombre de producto y sumar los precios
        Map<String, Double> sumProducts = productos.stream()
                .collect(Collectors.groupingBy(Product::getName, Collectors.summingDouble(Product::getPrice)));

        System.out.println(sumProducts);
        System.out.println("///////////////");

        // Obtener la suma y el resumen
        DoubleSummaryStatistics statistics = productos.stream().collect(Collectors.summarizingDouble(Product::getPrice));

        System.out.println(statistics);
        System.out.println("///////////////");

        // reduce: obtener la sumatoria de todos los precios de los productos
        // Double sumatory = productos.stream().map(Product::getPrice).reduce(0.0, (subtotal, price) ->  subtotal + price);
        Optional<Double> sumatory = productos.stream().map(Product::getPrice).reduce(Double::sum);
        System.out.println(sumatory.get());

    }

    public static int getAge(LocalDate birthDate){
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // ? es igual a decir <? extends Objects> es decir viene algo generico
    public static void printList(List<?> lista){
        lista.forEach(System.out::println);
    }
}