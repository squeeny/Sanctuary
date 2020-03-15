package Sanctuary.relics;

import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.HappyFlower;
import com.megacrit.cardcrawl.relics.Orichalcum;

import static Sanctuary.Sanctuary.makeRelicOutlinePath;
import static Sanctuary.Sanctuary.makeRelicPath;

public class Adamantium extends CustomRelic {

    public static final String ID = Sanctuary.makeID(Adamantium.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("adamantium.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("adamantium.png"));

    public Adamantium() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(Orichalcum.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(Orichalcum.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.currentBlock == 0) {
            flash();
            stopPulse();
            addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 9));
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }

    public void atTurnStart() {
        if (AbstractDungeon.player.currentBlock == 0) {
            beginLongPulse();
        }
    }

    public int onPlayerGainedBlock (float blockAmount){
        if (blockAmount > 0.0F) {
            stopPulse();
        }
        return MathUtils.floor(blockAmount);
    }

    public void onVictory() {
        stopPulse();
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Orichalcum.ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}