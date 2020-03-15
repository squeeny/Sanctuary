package Sanctuary.relics;

import Sanctuary.Sanctuary;
import Sanctuary.actions.utility.MessageCaller;
import Sanctuary.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static Sanctuary.Sanctuary.*;
import static Sanctuary.Sanctuary.sanctuaryMessages;
import static Sanctuary.util.UC.atb;

public class BurnerInserter extends CustomRelic {

    public static final String ID = Sanctuary.makeID(BurnerInserter.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("inserter.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("inserter.png"));

    public BurnerInserter() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, AbstractRelic.LandingSound.MAGICAL);
        this.counter = 0;
    }

    public void atTurnStart() {


        if (this.counter == -1) {
            this.counter += 2;

        } else {
            this.counter++;
        }

        if (this.counter == 5) {
            this.counter = 0;

            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new IncreaseMaxOrbAction(1));
        }

    }

    @Override
    public void onEquip() {
        /* 23 */     this.counter = 0;
        /*    */   }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
