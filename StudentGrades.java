package studentgrades;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Ali Haji M
 */
public class StudentGrades {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String sid = "";
        System.out.println("Welcome to Grade Calculator");
        sid = getStudentID();
        while (!sid.equalsIgnoreCase("quit")) {
            calcGrade(sid);
            sc.nextLine();
            sid = getStudentID();
        }//end of while
        System.out.println("Thanks for using Grade Calculator");
        System.out.println("*********************************");
    }// end of main

    public static String getStudentID() {
        String id = "";
        boolean goodval = false;

        do {
            System.out.print("Enter Student ID or type 'quit' to exit: ");
            id = sc.nextLine();
            //validate id:
            try {
                if (id.isEmpty()) {
                    System.out.println("Please enter an ID or quit ");
                } else if (id.equalsIgnoreCase("quit")) {
                    goodval = true;
                } else if (!id.substring(0, 1).equals("A")) {
                    System.out.println("ID must start with 'A' ");
                } else if (id.length() != 9) {
                    System.out.println("'A' must be followed by 8 digits ");
                } else {
                    long d = Long.parseLong(id.substring(1));
                    if (d <= 0) {
                        System.out.println("Numaric component must be > 0 ");
                    } else {
                        goodval = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Value after 'A' must be numaric");
            }
        } while (!goodval);
        return id;       
    }//end of getStudentID
    
    public static void calcGrade(String sid) {
        double q1, q2, q3, q4, q5, qm, mt, pr, fe, qavg, cavg;
        String lgrade;

        q1 = getScore("Quiz 1 score: ");
        q2 = getScore("Quiz 2 score: ");
        q3 = getScore("Quiz 3 score: ");
        q4 = getScore("Quiz 4 score: ");
        q5 = getScore("Quiz 5 score: ");
        qm = getScore("Quiz make up score: ");
        mt = getScore("Midterm score: ");
        pr = getScore("Problems score: ");

        double[] q = {q1, q2, q3, q4, q5, qm};
        Arrays.sort(q);
        qavg = (q[2] + q[3] + q[4] + q[5]) / 4.0;
        if (qavg >= 89.5 && mt >= 89.5 && pr >= 89.5) {
            //no final
            cavg = (qavg + mt + pr) / 3.0;
            lgrade = "A";
        } else {
            fe = getScore("Final Exam Score: ");
            cavg = (qavg * .5) + (mt * .15) + (pr * .1) + (fe * .25);

            if (cavg >= 89.5) {
                lgrade = "A";
            } else if (cavg >= 79.5) {
                lgrade = "B";
            } else if (cavg >= 69.5) {
                lgrade = "C";
            } else if (cavg >= 59.5) {
                lgrade = "D";
            } else {
                lgrade = "F";
            }
        }
        System.out.println("Student # " + sid + " earned a quiz avg of: "
                + qavg + " and a course avg of: "
                + cavg + " for a final grade of: " + lgrade);
    }// end of calGrade
    
    private static double getScore(String prompt) {
        double score = 0;
        boolean goodscore;
        do {
            try {
                System.out.print(prompt);
                score = sc.nextDouble();                
                if (score<0){
                    System.out.println("Invalid entry,score input must be a posetive intiger, please try again");
                    goodscore = false;
                }else{
                    goodscore = true;                
                }
            }catch(Exception e){            
                System.out.println("invalid score,must be a digit, please try again");
                goodscore = false;
                sc.nextLine();
            }
        } while (!goodscore);
        return score;
    }//end of getScore

}//end of class
