package com.lypaka.catchingmilestones;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.List;
import java.util.Map;

public class ConfigGetters {

    public static Map<String, List<Integer>> milestoneMap;
    public static List<String> rewards;

    public static void load() throws ObjectMappingException {

        milestoneMap = CatchingMilestones.configManager.getConfigNode(0, "Milestones").getValue(new TypeToken<Map<String, List<Integer>>>() {});
        rewards = CatchingMilestones.configManager.getConfigNode(0, "Rewards").getList(TypeToken.of(String.class));

    }

}
