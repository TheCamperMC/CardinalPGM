package in.twizmwaz.cardinal.module.modules.blitz;

import in.twizmwaz.cardinal.match.Match;
import in.twizmwaz.cardinal.module.BuilderData;
import in.twizmwaz.cardinal.module.ModuleBuilder;
import in.twizmwaz.cardinal.module.ModuleCollection;
import in.twizmwaz.cardinal.module.ModuleLoadTime;
import in.twizmwaz.cardinal.module.modules.rage.Rage;
import in.twizmwaz.cardinal.util.TimeUtils;
import org.jdom2.Element;

@BuilderData(load = ModuleLoadTime.LATEST)
public class BlitzBuilder implements ModuleBuilder {

    public ModuleCollection load(Match match) {
        ModuleCollection<Blitz> result = new ModuleCollection<Blitz>();
        for (Element element : match.getDocument().getRootElement().getChildren("blitz")) {
            boolean broadcastLives = element.getChild("broadcastLives") != null && Boolean.parseBoolean(element.getChild("broadcastLives").getText());
            int lives = element.getChild("lives") == null ? 1 : Integer.parseInt(element.getChild("lives").getText());
            int time = element.getChild("time") == null ? 0 : TimeUtils.timePeriodsToSeconds(element.getChild("time").getText());
            String title = element.getChildText("title") == null ? (match.getModules().getModule(Rage.class) != null ? "Blitz: Rage" : "Blitz") : element.getChildText("title");
            result.add(new Blitz(title, broadcastLives, lives, time));
        }
        return result;
    }

}

