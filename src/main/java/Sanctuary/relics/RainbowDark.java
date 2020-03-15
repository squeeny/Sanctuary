package Sanctuary.relics;

import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.orbs.Plasma;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import static Sanctuary.Sanctuary.makeRelicOutlinePath;
import static Sanctuary.Sanctuary.makeRelicPath;

public class RainbowDark extends CustomRelic {

    public static final String ID = Sanctuary.makeID(RainbowDark.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Rainbow.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Rainbow.png"));

    public RainbowDark() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {

        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        AbstractDungeon.player.channelOrb(new Frost());
        AbstractDungeon.player.channelOrb(new Lightning());
        AbstractDungeon.player.channelOrb(new Dark());

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
