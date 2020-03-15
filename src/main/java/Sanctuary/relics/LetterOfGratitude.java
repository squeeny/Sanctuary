package Sanctuary.relics;

import Sanctuary.util.SanctuaryMessages;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;

import java.io.IOException;
import java.math.BigInteger;

import static Sanctuary.Sanctuary.*;

public class LetterOfGratitude extends CustomRelic implements ClickableRelic {

    public static final String ID = Sanctuary.makeID(LetterOfGratitude.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ReinaLetter.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DokiDokiMemoir.png"));

    private boolean isPlayerTurn = true;

    public LetterOfGratitude() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public void onRightClick() {

        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {

            flash();
            stopPulse();

            AbstractDungeon.ftue = new SanctuaryMessages(-1,0);

            try {
                Sanctuary.saveData();
            } catch (IOException e) {
                e.printStackTrace();

            }

        }



    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
