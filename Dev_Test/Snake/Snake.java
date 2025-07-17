package Dev_Test.Snake;

import java.util.Random;
import java.util.Scanner;

public class Snake {

    private static final int WIDTH = 20;
    private static final int HEIGHT = 10;
    private static final char EMPTY = ' ';
    private static final char SNAKE_HEAD = '@';
    private static final char SNAKE_BODY = 'O';
    private static final char FOOD = '*';

    private int[][] snake;
    private int snakeLength;
    private int foodX;
    private int foodY;
    private char direction;
    private boolean isGameOver;
    private char[][] board;
    private volatile char inputDirection; // Variable to hold the latest input

    public Snake() {
        board = new char[HEIGHT][WIDTH];
        snake = new int[WIDTH * HEIGHT][2];
        snakeLength = 1;
        snake[0][0] = HEIGHT / 2;
        snake[0][1] = WIDTH / 2;
        direction = 'R';
        inputDirection = direction;
        isGameOver = false;
        placeFood();
    }

    private void placeFood() {
        Random rand = new Random();
        do {
            foodX = rand.nextInt(WIDTH);
            foodY = rand.nextInt(HEIGHT);
        } while (board[foodY][foodX] == SNAKE_BODY);
    }

    private void update() {
        // Move the snake body
        for (int i = snakeLength; i > 0; i--) {
            snake[i][0] = snake[i - 1][0];
            snake[i][1] = snake[i - 1][1];
        }

        // Update direction based on the latest input
        changeDirection(inputDirection);

        // Move the head
        switch (direction) {
            case 'U' -> snake[0][0]--; // Up
            case 'D' -> snake[0][0]++; // Down
            case 'L' -> snake[0][1]--; // Left
            case 'R' -> snake[0][1]++; // Right
        }

        // Check for collision with walls or itself
        if (snake[0][0] < 0 || snake[0][0] >= HEIGHT || snake[0][1] < 0 || snake[0][1] >= WIDTH) {
            isGameOver = true;
        }
        for (int i = 1; i < snakeLength; i++) {
            if (snake[0][0] == snake[i][0] && snake[0][1] == snake[i][1]) {
                isGameOver = true;
            }
        }

        // Check if food is eaten
        if (snake[0][0] == foodY && snake[0][1] == foodX) {
            snakeLength++;
            placeFood();
        }
    }

    private void draw() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[i][j] = EMPTY;
            }
        }

        // Place the food
        board[foodY][foodX] = FOOD;

        // Place the snake
        for (int i = 0; i < snakeLength; i++) {
            if (i == 0) {
                board[snake[i][0]][snake[i][1]] = SNAKE_HEAD;
            } else {
                board[snake[i][0]][snake[i][1]] = SNAKE_BODY;
            }
        }

        // Draw the board
        System.out.println("\n".repeat(5)); // Clear the console (simulate refresh)
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private void changeDirection(char newDirection) {
        if ((newDirection == 'U' && direction != 'D') ||
            (newDirection == 'D' && direction != 'U') ||
            (newDirection == 'L' && direction != 'R') ||
            (newDirection == 'R' && direction != 'L')) {
            direction = newDirection;
        }
    }

    private void startInputListener() {
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!isGameOver) {
                char input = scanner.next().charAt(0);
                switch (input) {
                    case 'w' -> inputDirection = 'U';
                    case 's' -> inputDirection = 'D';
                    case 'a' -> inputDirection = 'L';
                    case 'd' -> inputDirection = 'R';
                }
            }
            scanner.close();
        });
        inputThread.start();
    }

    public void start() {
        startInputListener();
        while (!isGameOver) {
            update();
            draw();
            try {
                Thread.sleep(300); // Adjust speed here
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Game Over!");
    }

    public static void main(String[] args) {
        Snake game = new Snake();
        game.start();
    }
}
