import java.util.Scanner;
import java.util.Random;
import static java.lang.Integer.parseInt;

public class WhackAMole {
    int score;
    int molesLeft = 0;
    int attemptsLeft;

    char[][] moleGrid;

    WhackAMole(int numAttempts, int gridDimension) {
        attemptsLeft = numAttempts;
        moleGrid = new char[gridDimension][gridDimension];
        var random = new Random();
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(gridDimension-1);
            int y = random.nextInt(gridDimension-1);
            if (moleGrid[x][y] != 'M') {
                this.place(x, y);
            } else {
                i--;
            }
        }
    }

    boolean place(int x, int y) {
        if (moleGrid[x][y] != 'M') {
            moleGrid[x][y] = 'M';
            molesLeft += 1;
            return true;
        } else {
            return false;
        }
    }

    void whack(int x, int y) {
        if (moleGrid[x][y] == 'M') {
            score += 1;
            molesLeft -= 1;
            moleGrid[x][y] = 'W';
        }
        attemptsLeft -= 1;
    }

    void printGridToUser() {
        for (int i = 0; i < moleGrid.length; i++) {
            for (int j = 0; j < moleGrid[i].length; j++) {
                if (moleGrid[i][j] == 'W') {
                    System.out.print(moleGrid[i][j]);
                } else {
                    System.out.print("*");
                }
            }
            System.out.println("");
        }
    }

    void printGrid() {
        for (int i = 0; i < moleGrid.length; i++) {
            for (int j = 0; j < moleGrid[i].length; j++) {
                if (moleGrid[i][j] == 'W' || moleGrid[i][j] == 'M') {
                    System.out.print(moleGrid[i][j]);
                } else {
                    System.out.print("*");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        WhackAMole tenByTen = new WhackAMole(50, 10);
        int numAttempts = tenByTen.attemptsLeft;
        for(int i = 0; i < numAttempts; i++) {
            System.out.println("Enter where you like to take a whack within our " + tenByTen.moleGrid.length + " by " + tenByTen.moleGrid[0].length +
                    "\ngrid or enter -1 twice to give up and see the grid. \n\nYou have " + tenByTen.attemptsLeft + " attempts to get all the moles");
            System.out.println("x (1 through " + (tenByTen.moleGrid.length) + "):");
            int x = parseInt(in.nextLine()) - 1;
            System.out.println("y: (1 through " + (tenByTen.moleGrid[0].length) + "):");
            int y = parseInt(in.nextLine()) - 1;

            if (x == -2 && y == -2) {
                tenByTen.printGrid();
                System.exit(0);
            } else {
                tenByTen.whack(x, y);
                if (tenByTen.molesLeft == 0) {
                    System.out.println("Congratulations! You won!");
                    tenByTen.printGrid();
                } else {
                    tenByTen.printGridToUser();
                    if (tenByTen.moleGrid[x][y] == 'W') {
                        System.out.println("\nGot one! Only " + (tenByTen.molesLeft) + " moles left! \n");
                    } else {
                        System.out.println("\nDarn! So close! Only " + tenByTen.molesLeft + " moles left! \n");
                    }

                }


            }
        }
        System.out.println("\nSorry! You lose! Here is where they were hiding...");
        tenByTen.printGrid();






    }




}
