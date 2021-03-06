package com.company;

import java.util.ArrayList;

public class FruitsAndFrogsGeneratorThread extends Thread implements GameObjectThread {
    private Board board;
    private FruitsAndFrogsGenerator fruitsAndFrogsGenerator;
    private boolean canCalculateNextAction = false;
    private ArrayList<Fruit> missingFruits;
    private ArrayList<Frog> missingFrogs;
    private boolean killed = false;

    FruitsAndFrogsGeneratorThread(Board board) {
        this.board = board;
        this.fruitsAndFrogsGenerator = new FruitsAndFrogsGenerator(board, 3, 3);
    }

    /***
     * Generates missing fruits and frogs lists when the flag is set
     */
    @Override
    public void run() {
        while(true) {
            if (killed) {
                break;
            }
            if (this.canCalculateNextAction) {
                this.missingFruits = fruitsAndFrogsGenerator.generateMissingFruitsList();
                this.missingFrogs = fruitsAndFrogsGenerator.generateMissingFrogsList();
                this.canCalculateNextAction = false;
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }

    /***
     * Sets the flag that allows the thread to calculate its next action
     */
    @Override
    public void startCalculatingNextAction() {
        this.canCalculateNextAction = true;
    }

    /***
     * Sets missing frogs list and places missing fruits and frogs on the board
     */
    @Override
    public void performNextAction() {
        this.board.setMissingFrogs(this.missingFrogs);
        this.fruitsAndFrogsGenerator.generateFruitsAndFrogs(this.missingFruits, this.missingFrogs);
    }

    /***
     * Returns game object threads to be removed
     * @return game object threads to be removed
     */
    @Override
    public ArrayList<Collidable> getGameObjectsToRemove() {
        return new ArrayList<>();
    }

    /***
     * Returns the related game object
     * @return related game object
     */
    @Override
    public Collidable getRelatedGameObject() {
        return new Collidable() {
            @Override
            public String getName() {
                return "dummy";
            }
        };
    }

    /***
     * Sets thread kill flag to true
     */
    @Override
    public void forceKill() {
        killed = true;
    }

}
