import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
//Made by Savannah Aaron and Isaac Copenhaver
public class Main{
    static Stack<GameObject> tower1 = new Stack<GameObject>();
    static Stack<GameObject> tower2 = new Stack<GameObject>();
    static Stack<GameObject> tower3 = new Stack<GameObject>();
    static Queue<GameObject> queue1 = new LinkedList<GameObject>();
    static Queue<GameObject> queue2 = new LinkedList<GameObject>();
    static Queue<GameObject> queue3 = new LinkedList<GameObject>();
    static GameObject placeHolder = new GameObject(0, "|    ");
    public static void main(String[] args){

        tower1.push(new GameObject(5, "XXXXX"));
        tower1.push(new GameObject(4, "XXXX "));
        tower1.push(new GameObject(3, "XXX  "));
        tower1.push(new GameObject(2, "XX   "));
        tower1.push(new GameObject(1, "X    "));
        display();
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to have the computer solve it (1) or play yourself(2)?");
        int input = scan.nextInt();
        if (input == 1) {
            hanoi(5,1,3);
        } else if (input == 2) {
            System.out.println("you are now playing towers of Hanoi");
            game();
        } else {
            System.out.println("Pick an option");
        }
    }
    // recursive solution provided by Reducible on youtube: https://www.youtube.com/watch?v=rf6uf3jNjbo
    public static void hanoi(int num, int start, int end){
        if(num==1){
            makeMove(start,end);
        }else{
            int other = 6 -(start + end);
            hanoi(num-1,start, other);
            makeMove(start, end);
            hanoi(num-1, other, end);
        }
    }
    public static void makeMove(int start, int end){
        GameObject disk = null;
        if(start == 1 && !tower1.isEmpty()){
            disk = tower1.pop();
        }
        if(start == 2 && !tower2.isEmpty()){
            disk = tower2.pop();
        }
        if(start == 3 && !tower3.isEmpty()){
            disk = tower3.pop();
        }
        if(end == 1) {
            tower1.add(disk);
        }
        if(end == 2) {
            tower2.add(disk);
        }if(end == 3) {
            tower3.add(disk);
        }
        display();
    }


    public static void game() {
        boolean gameWon = false;
        Scanner scan = new Scanner(System.in);
        int moveCount = 0;
        while (!gameWon) {
            display();
            System.out.println("Where would you like to move the top disk from?");
            int from = scan.nextInt();
            System.out.println("Where would you like to move the disk to?");
            int to = scan.nextInt();

                if (isValidMove(from, to)) {
                    GameObject disk = new GameObject(0," ");
                    if (from == 1) {
                         disk = tower1.pop();
                    }
                    if (from == 2) {
                        disk = tower2.pop();
                    }
                    if (from == 3) {
                        disk = tower3.pop();
                    }
                    if (to == 1) {
                        tower1.push(disk);
                    }
                    if (to == 2) {
                        tower2.push(disk);
                    }
                    if (to == 3) {
                        tower3.push(disk);
                    }
                    moveCount++; // increment move count
                    if (tower3.size() == 5) {
                        gameWon = true;
                        System.out.println("You won!!!");
                        display();
                        System.out.println("Number of moves made: " + moveCount); // print move count
                    }
                }
        }
    }

    public static boolean isValidMove(int from, int to) {
        if (from < 1 || from > 3 || to < 1 || to > 3) {
            System.out.println("Move not allowed: Tower " + from + " or " + to + " does not exist");
            return false;
        }
        if(from == 1){
        if (tower1.isEmpty()) {
            System.out.println("Move not allowed: Tower " + from + " is empty");
            return false;
        }
        }else if(from == 2){
        if (tower2.isEmpty()) {
            System.out.println("Move not allowed: Tower " + from + " is empty");
            return false;
        }
        }else if(from == 3){
            if (tower3.isEmpty()) {
                System.out.println("Move not allowed: Tower " + from + " is empty");
                return false;
            }
        }
        int fromSize = 0;
        int toSize = 0;
        if(from == 1 && !tower1.isEmpty()){
            fromSize = tower1.peek().getSize();
        }
        if(from == 2&& !tower1.isEmpty()){
            fromSize = tower2.peek().getSize();
        }
        if(from == 3&& !tower1.isEmpty()){
            fromSize = tower3.peek().getSize();
        }
        if(to == 1&& !tower1.isEmpty()){
            toSize = tower1.peek().getSize();
            if(fromSize > toSize){
                System.out.println("You can not put a bigger disk on a smaller disk");
                return false;
            }
        }
        if(to == 2&& !tower2.isEmpty()){
            toSize = tower2.peek().getSize();
            if(fromSize > toSize){
                System.out.println("You can not put a bigger disk on a smaller disk");
                return false;
            }
        }
        if(to == 3&& !tower3.isEmpty()){
            toSize = tower3.peek().getSize();
            if(fromSize > toSize){
                System.out.println("You can not put a bigger disk on a smaller disk");
                return false;
            }
        }



        if (from == to) {
            System.out.println("Move not allowed: Cannot move disk to the same tower");
            return false;
        }
        return true;
    }

    private static void display(){
        System.out.println();
        Stack<GameObject> temp1 = new Stack<GameObject>();
        Stack<GameObject> temp2 = new Stack<GameObject>();
        Stack<GameObject> temp3 = new Stack<GameObject>();
        for(int i = 0; i < 5; i++){
            if (!tower1.isEmpty()){
                queue1.add(tower1.peek());
            }
            if (!tower2.isEmpty()){
                queue2.add(tower2.peek());
            }
            if (!tower3.isEmpty()){
                queue3.add(tower3.peek());
            }
            if (!tower1.isEmpty()){
                temp1.add(tower1.pop());
            }
            if (!tower2.isEmpty()){
                temp2.add(tower2.pop());
            }
            if (!tower3.isEmpty()){
                temp3.add(tower3.pop());
            }
        }
        for(int i = 0; i < 5; i++){
            if (!temp1.isEmpty()){
                tower1.add(temp1.pop());
            }
            if (!temp2.isEmpty()){
                tower2.add(temp2.pop());
            }
            if (!temp3.isEmpty()){
                tower3.add(temp3.pop());
            }
        }
        for(int i = 0; i < 5; i++){
            GameObject print1 = placeHolder;
            GameObject print2 = placeHolder;
            GameObject print3 = placeHolder;
            if(!queue1.isEmpty()){
                print1 = queue1.remove();
            }
            if(!queue2.isEmpty()){
                print2 = queue2.remove();
            }
            if(!queue3.isEmpty()){
                print3 = queue3.remove();
            }
            System.out.println(print1 + " " + print2 + " " + print3);
        }
    }
}
