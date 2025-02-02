import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Course 
{
    String code;
    String title;
    String description;
    int capacity;
    List<String> schedule;

    public Course(String code, String title, String description, int capacity, List<String> schedule) 
    {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() 
    {
        return code;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getDescription() 
    {
        return description;
    }

    public int getCapacity() 
    {
        return capacity;
    }

    public List<String> getSchedule() 
    {
        return schedule;
    }

    public void decreaseCapacity() 
    {
        if (capacity > 0) 
        {
            capacity--;
        }
    }

    public void increaseCapacity() 
    {
        capacity++;
    }
}

class Student 
{
    String id;
    String name;
    List<Course> registeredCourses;

    public Student(String id, String name) 
    {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() 
    {
        return id;
    }

    public String getName() 
    {
        return name;
    }

    public List<Course> getRegisteredCourses() 
    {
        return registeredCourses;
    }

    public void register(Course course) 
    {
        registeredCourses.add(course);
    }

    public void drop(Course course) 
    {
        registeredCourses.remove(course);
    }
}

public class CourseRegistrationSystem 
{
    List<Course> courses;
    List<Student> students;
    Scanner scanner;

    public CourseRegistrationSystem() 
    {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        scanner = new Scanner(System.in);

        courses.add(new Course("CS101", "Intro to CS", "Fundamentals of Programming", 30, List.of("Mon 9:00 AM", "Wed 11:00 AM")));
        courses.add(new Course("MATH101", "Calculus", "Differential and Integral Calculus", 25, List.of("Tue 1:00 PM", "Thu 3:00 PM")));
        students.add(new Student("S123", "Alice"));
        students.add(new Student("S456", "Bob"));
    }

    public void listCourses() 
    {
        for (Course course : courses) 
        {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity() + " available");
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("----------------------");
        }
    }

    public boolean registerStudent() 
    {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) 
        {
            System.out.println("Student not found.");
            return false;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) 
        {
            System.out.println("Course not found.");
            return false;
        }

        if (registerStudent(student, course)) 
        {
            System.out.println(student.getName() + " registered for " + course.getTitle());
            return true;
        } 
        else 
        {
            System.out.println("Registration failed.");
            return false;
        }
    }

    public boolean dropStudentFromCourse() 
    {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) 
        {
            System.out.println("Student not found.");
            return false;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) 
        {
            System.out.println("Course not found.");
            return false;
        }

        if (dropStudentFromCourse(student, course)) 
        {
            System.out.println(student.getName() + " dropped " + course.getTitle());
            return true;
        } 
        else 
        {
            System.out.println("Drop failed.");
            return false;
        }
    }

    private Student findStudentById(String id) 
    {
        for (Student student : students) 
        {
            if (student.getId().equals(id)) 
            {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByCode(String code) 
    {
        for (Course course : courses) 
        {
            if (course.getCode().equals(code)) 
            {
                return course;
            }
        }
        return null;
    }

    public boolean registerStudent(Student student, Course course) 
    {
        if (course.getCapacity() > 0 && !student.getRegisteredCourses().contains(course)) 
        {
            student.register(course);
            course.decreaseCapacity();
            return true;
        }
        return false;
    }

    public boolean dropStudentFromCourse(Student student, Course course) 
    {
        if (student.getRegisteredCourses().contains(course)) 
        {
            student.drop(course);
            course.increaseCapacity();
            return true;
        }
        return false;
    }

    public static void main(String[] args) 
    {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            try 
            {
                System.out.println("\nChoose an option:");
                System.out.println("1. List Courses");
                System.out.println("2. Register Student");
                System.out.println("3. Drop Course");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        system.listCourses();
                        break;
                    case 2:
                        system.registerStudent();
                        break;
                    case 3:
                        system.dropStudentFromCourse();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) 
            {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            }
        }
    }
}
