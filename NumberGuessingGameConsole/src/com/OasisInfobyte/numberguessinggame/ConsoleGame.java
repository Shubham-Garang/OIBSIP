package com.OasisInfobyte.numberguessinggame;


import java.util.Random;
import java.util.Scanner;

public class ConsoleGame {
 private Random random = new Random();
 private int secretNumber;
 private int maxAttempts = 10;
 private int attempts = 0;
 private int score = 100;

 public void start() {
     Scanner scanner = new Scanner(System.in);
     secretNumber = random.nextInt(100) + 1;

     System.out.println("Welcome to the Console-Based Guess the Number Game!");
     System.out.println("I'm thinking of a number between 1 and 100.");

     while (attempts < maxAttempts) {
         System.out.print("Guess the number: ");
         int guess = scanner.nextInt();
         attempts++;

         if (guess < secretNumber) {
             System.out.println("Too low! Try again.");
             System.out.println("Attempts left: ");
             System.out.println(maxAttempts-attempts);
         } else if (guess > secretNumber) {
             System.out.println("Too high! Try again.");
             System.out.println("Attempts left: ");
             System.out.println(maxAttempts-attempts);
         } else {
             int roundScore = calculateScore(attempts);
             score += roundScore;
             System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
             System.out.println("You scored " + roundScore + " points in this round.");
             break;
         }
     }

     if (attempts == maxAttempts) {
         System.out.println("Sorry, you've reached the maximum number of attempts. The secret number was: " + secretNumber);
     }

     System.out.println("Thanks for playing! Your final score is: " + score);
     scanner.close();
 }

 private int calculateScore(int attempts) {
     if (attempts <= 3) {
         return 10;
     } else if (attempts <= 6) {
         return 5;
     } else {
         return 2;
     }
 }
}
