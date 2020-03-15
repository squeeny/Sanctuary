package Sanctuary.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Sanctuary.Sanctuary.*;


import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;


public class TimewalkingPendant extends CustomRelic {

    public static final String ID = Sanctuary.makeID(TimewalkingPendant.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TimewalkingPendant.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TimewalkingPendant.png"));


    public TimewalkingPendant() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
        this.counter = 0;
    }


    public void onPlayCard(AbstractCard card, AbstractMonster m) {

        this.counter++;

        if (this.counter >= 32) {

            CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
            AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));
            AbstractDungeon.topLevelEffectsQueue.add(new TimeWarpTurnEndEffect());

            addToBot(new PressEndTurnButtonAction());
            addToBot(new SkipEnemiesTurnAction());

            this.counter -= 32;

        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}