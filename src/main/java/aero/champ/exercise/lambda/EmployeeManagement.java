package aero.champ.exercise.lambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeManagement {
    private final List<Employee> employees;
    public EmployeeManagement() {
        this.employees = new ArrayList<>();
        initializeEmployees();
    }
    private void initializeEmployees() {
        // Add sample employees
        employees.add(new Employee(1, "John Smith", "Engineering", 75000, 28));
        employees.add(new Employee(2, "Maria Garcia", "HR", 65000, 35));
        employees.add(new Employee(3, "Alex Johnson", "Engineering", 85000, 32));
        employees.add(new Employee(4, "Sarah Williams", "Marketing", 72000, 41));
        employees.add(new Employee(5, "James Brown", "Finance", 95000, 39));
        employees.add(new Employee(6, "Emily Davis", "HR", 68000, 27));
        employees.add(new Employee(7, "Michael Miller", "Engineering", 78000, 29));
        employees.add(new Employee(8, "Jessica Wilson", "Marketing", 71000, 36));
        employees.add(new Employee(9, "David Martinez", "Finance", 92000, 43));
        employees.add(new Employee(10, "Jennifer Taylor", "Engineering", 88000, 31));
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    // TODO: Implement methods using Lambda expressions and Stream API
    public List<Employee> filterByDepartment(String department) {
        // TODO: Use a Lambda expression with Stream API to filter employees by department
        return getEmployees().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .toList();
    }

    public List<Employee> findEmployeesWithSalaryAbove(double threshold) {
        // TODO: Use Stream API with Lambda to filter employees by salary
        return getEmployees().stream()
                .filter(employee -> employee.getSalary() > threshold)
                .toList();
    }

    public Map<String, Double> calculateAverageAgeByDepartment() {
        // TODO: Use Stream API with collect and groupingBy to calculate average age by department
        return getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt(Employee::getAge)));
    }

    public Map<String, Employee> findHighestPaidByDepartment() {
        // TODO: Use Stream API with collect, groupingBy, and collectingAndThen to find highest paid employee
        return getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::getSalary)), Optional::get)));
    }

    public Map<String, Long> countByDepartment() {
        // TODO: Use Stream API to count employees in each department
        return getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    public List<Employee> customFilter(Predicate<Employee> predicate) {
        // TODO: Use the provided Predicate with Stream API to filter employees
        return getEmployees().stream()
                .filter(predicate)
                .toList();
    }

    public String findDepartmentWithHighestAverageSalary() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No department found");
    }

    public Map<String, List<Employee>> groupByAgeRange() {
        return employees.stream()
                .collect(Collectors.groupingBy(emp -> {
                    int age = emp.getAge();
                    if (age <= 30) return "20-30";
                    else if (age <= 40) return "31-40";
                    else return "41-50";
                }));
    }

    public List<Employee> increaseSalaries(double percentage) {
        double factor = 1 + percentage / 100;
// We can't modify the original employees directly through a stream
// So we'll create new Employee objects with updated salaries
        return employees.stream()
                .map(emp -> new Employee(
                        emp.getId(),
                        emp.getName(),
                        emp.getDepartment(),
                        emp.getSalary() * factor,
                        emp.getAge()))
                .collect(Collectors.toList());
    }

    public List<String> getSortedEmployeeNames() {
        return employees.stream()
                .map(Employee::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public String findDepartmentWithYoungestEmployee() {
        return employees.stream()
                .min(Comparator.comparing(Employee::getAge))
                .map(Employee::getDepartment)
                .orElse("No department found");
    }
}
