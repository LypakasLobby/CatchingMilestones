package com.lypaka.catchingmilestones.Milestones;

public class Milestone {

    // we need these objects to NOT be final so we can update the values associated with them on player login
    private String type;
    private int amount;
    private String commandReward;
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

    public void setType (String type) {

        this.type = type;

    }

    public int getAmount() {

        return this.amount;

    }

    public void setAmount (int amount) {

        this.amount = amount;

    }

    public String getCommandReward() {

        return this.commandReward;

    }

    public void setCommandReward (String commandReward) {

        this.commandReward = commandReward;

    }

    public int getProgress() {

        return this.progress;

    }

    public void setProgress (int progress) {

        this.progress = progress;

    }

}
