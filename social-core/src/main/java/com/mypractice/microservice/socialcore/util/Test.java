package com.mypractice.microservice.socialcore.util;

import org.springframework.aot.hint.predicate.ProxyHintsPredicates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("main run");
//            }
//        });
//        t.start();
//
//
//        Thread t2 = new Thread(() -> System.out.println("main t2 run"));
//        t2.start();
//
//        Supplier<Date> date = () -> new Date(System.currentTimeMillis());
//        IntPredicate evenPredicate = n -> n%2==0;
//        Function<Integer, Integer>  multiply  = n -> (n * n * n) ;
//        Consumer<Integer> consumer = n-> System.out.println(n);
//        List<Integer> numbers = Arrays.asList(1,2, 4, 5, 6);
//        for (int no:numbers) {
//            if (evenPredicate.test(no))
//                consumer.accept(multiply.apply(no));
//
//        }
//        System.out.println(date.get());
//
//
//        numbers.forEach(System.out::println);
        List<Employee> employees = new ArrayList<>();
        IntStream.rangeClosed(1, 100).mapToObj(e -> new Employee(e, "name  " + e, new Random().nextInt())).forEach(employees::add);
        var  emps= getEvenSalariesEmployees(employees);
        System.out.println(emps.size());


        employees.stream().filter(e -> e.salary()%2 == 0).collect(Collectors.toList());
        getEmployees(employees,e -> e.salary()%2==0);
        getEmployees(employees,e -> e.salary()%2==0);
        getEmployees(employees,e -> e.salary()%2==0);
        getEmployees(employees,e -> e.salary()%2==0);
        getEmployees(employees,e -> e.salary()%2==0);
        getEmployees(employees,e -> e.salary()%2==0);

    }


    public static List<Employee> getEmployees(List<Employee> employees, Predicate<Employee> employeePredicate){
          return employees.stream().filter(employeePredicate).collect(Collectors.toList());
      }

    public static List<Employee> getEvenSalariesEmployees(List<Employee> employees) {
        List<Employee> emp = new ArrayList<>();


        for (Employee e : employees) {
            if (e.salary() % 2==0) {
                emp.add(e);
            }
        }
        return emp;
    }

    public static List<Employee> get100000GreaterEmployees(List<Employee> employees) {
        List<Employee> emp = new ArrayList<>();


        for (Employee e : employees) {
            if (e.salary() > 10000) {
                emp.add(e);
            }
        }
        return emp;
    }
}

record Employee(Integer id, String name, int salary) {

}
