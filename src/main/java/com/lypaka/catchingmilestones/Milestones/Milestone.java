package com.lypaka.catchingmilestones.Milestones;

public class Milestone {

    private final String type;
    private final int amount;
    private final String commandReward;
    private int progress;

    public Milestone (String type, int amount, String commandReward, int progress) {

        this.type = type;
        this.amount = amount;
        this.commandReward = commandReward;
        this.progress = progress;

    }

    public String getType() {

        return this.type;

    }

    public int getAmount() {

        return this.amount;

    }

    public String getCommandReward() {

        return this.commandReward;

    }

    public int getProgress() {

        return this.progress;

    }

    public void setProgress (int progress) {

        this.progress = progress;

    }

}
