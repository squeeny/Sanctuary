package Sanctuary.relics;

import Sanctuary.util.SanctuaryMessages;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;

import java.io.IOException;
import java.math.BigInteger;

import static Sanctuary.Sanctuary.*;

public class TheSun extends CustomRelic implements ClickableRelic {

    public static final String ID = Sanctuary.makeID(TheSun.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TheSun.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TheSun.png"));

    public TheSun() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    private boolean used = false;
    private boolean isPlayerTurn = false;

    @Override
    public void onRightClick() {

        if (this.counter == -2) {
            return; // Don't do anything.
        }

        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {

            flash();
            //addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new StunMonsterAction(m, p, 1));
            setCounter(-2);

        }

    }

    public void setCounter(int setCounter) {

        if (setCounter == -2) {

            usedUp();
            this.counter = -2;
        }
    }



    @Override
    public String getUpdatedDescription() {

        return DESCRIPTIONS[0];
    }

}