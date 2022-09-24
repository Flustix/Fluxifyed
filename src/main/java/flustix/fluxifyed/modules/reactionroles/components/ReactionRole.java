package flustix.fluxifyed.modules.reactionroles.components;

import flustix.fluxifyed.Main;

public class ReactionRole {
    public String emoji;
    public String roleid;
    public String name;
    public String description;

    public ReactionRole(String emoji, String roleid, String name, String description) {
        this.emoji = emoji;
        this.roleid = roleid;
        this.name = name;
        this.description = description;

        Main.LOGGER.info("Loaded reaction role " + name + " with emoji " + emoji + " and roleid " + roleid);
    }
}
