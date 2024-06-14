package com.lypaka.catchingmilestones.PlayerAccounts;

import com.lypaka.catchingmilestones.Milestones.Milestone;
import net.minecraft.entity.player.ServerPlayerEntity;

public class Account {

    private final ServerPlayerEntity player;
    private Milestone milestone;

    public Account (ServerPlayerEntity player, Milestone milestone) {

        this.player = player;
        this.milestone = milestone;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public Milestone getMilestone() {

        return this.milestone;

    }

    public void setMilestone (Milestone milestone) {

        this.milestone = milestone;

    }

    public void clearMilestone() {

        this.milestone = null;

    }

}
