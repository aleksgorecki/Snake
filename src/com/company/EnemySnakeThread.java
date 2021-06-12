package com.company;

import java.util.ArrayList;

public class EnemySnakeThread extends Thread implements GameObjectThread{
    private Board board;
    private SnakeAI snakeAI;
    private SnakeController snakeController;
    private boolean canCalculateNextAction = false;
    private Direction nextAction;
    private ArrayList<Collidable> gameObjectsToRemove = new ArrayList<>();

    EnemySnakeThread(Board board) {
        this.board = board;
        this.snakeAI = new SnakeAI(board, board.getEnemySnake());
        this.snakeController = new SnakeController(board, board.getEnemySnake());
    }

    @Override
    public void run() {
        while (true) {
            if (board.getEnemySnake() == null) {
                break;
            }
            gameObjectsToRemove = snakeController.handleCollisions();
            if (this.canCalculateNextAction) {
                this.nextAction = this.snakeAI.getNextMoveDirection();
                this.canCalculateNextAction = false;
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void startCalculatingNextAction() {
        this.canCalculateNextAction = true;
    }

    @Override
    public void performNextAction() {
        try {
            this.board.getEnemySnake().setMoveDirection(this.nextAction);
        } catch (IncorrectDirectionException ignored) {
        }
        this.snakeController.move();
    }

    public ArrayList<Collidable> getGameObjectsToRemove() {
        return gameObjectsToRemove;
    }

    public void clearGameObjectsToRemove() {
        gameObjectsToRemove.clear();
    }
}
