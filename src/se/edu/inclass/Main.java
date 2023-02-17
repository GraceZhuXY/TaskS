package se.edu.inclass;

import org.jcp.xml.dsig.internal.SignerOutputStream;
import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.sql.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println();
        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        System.out.println();

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Printing deadlines after sorting:");

        printDeadlinesUsingStream(tasksData);
        System.out.println(countDeadlinesUsingStream(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter(t -> (t instanceof Deadline)) // filter takes a predicate
                .count();

        return count;
    }
    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using iteration");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data using streams");
        tasks.stream()          // convert to stream
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadline using iteration");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }



    public static ArrayList<Task> filterTaskListUsingStreams(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredlist = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
        return filteredlist;
    }


    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing deadline using streams");
        tasks.stream()
                .filter(t -> (t instanceof Deadline)) // filter takes a predicate
                .forEach(System.out::println);
    }
}
