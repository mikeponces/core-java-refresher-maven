package aero.champ.exercise.lambda;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class LambdaStreamExercise
{
    public static void main(String[] args) {
        EmployeeManagement management = new EmployeeManagement();
// Test 1: Filter by department
        System.out.println("Engineers:");
        List<Employee> engineers = management.filterByDepartment("Engineering");
        engineers.forEach(System.out::println);
// Test 2: Find employees with salary above threshold
        System.out.println("\nEmployees with salary above 80000:");
        List<Employee> highPaid = management.findEmployeesWithSalaryAbove(80000);
        highPaid.forEach(System.out::println);
// Test 3: Calculate average age by department
        System.out.println("\nAverage age by department:");
        Map<String, Double> avgAgeByDept =
                management.calculateAverageAgeByDepartment();
        avgAgeByDept.forEach((dept, avgAge) ->
                System.out.println(dept + ": " + avgAge)
        );
// Test 4: Find highest paid employee by department
        System.out.println("\nHighest paid employee by department:");
        Map<String, Employee> highestPaid = management.findHighestPaidByDepartment();
        highestPaid.forEach((dept, emp) ->
                System.out.println(dept + ": " + emp.getName() + " ($" + emp.getSalary() +
                        ")")
        );
// Test 5: Count employees by department
        System.out.println("\nEmployee count by department:");
        Map<String, Long> countByDept = management.countByDepartment();
        countByDept.forEach((dept, count) ->
                System.out.println(dept + ": " + count)
        );
// Test 6: Custom filter using Predicate
        System.out.println("\nCustom filter - Young Engineers (Engineering, Age < 30):");
        Predicate<Employee> youngEngineer = emp ->
                emp.getDepartment().equals("Engineering") && emp.getAge() < 30;
        List<Employee> youngEngineers = management.customFilter(youngEngineer);
        youngEngineers.forEach(System.out::println);
    }
}
