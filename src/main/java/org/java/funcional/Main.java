package org.java.funcional;

import org.java.funcional.model.Person;
import org.java.funcional.model.Product;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

        Product play = new Product(1, "Play station 5", 2500000.0);
        Product balon = new Product(2, "Balon adidas", 120000.0);
        Product monitor = new Product(3, "Monitor LG", 500000.0);
        Product teclado = new Product(4, "Teclado logitech", 2500000.0);

        List<Person> personas = Arrays.asList(mateo, santiago, alejandra, maria, gloria, ivan);
        List<Product> productos = Arrays.asList(play, balon, monitor, teclado);


    }
}