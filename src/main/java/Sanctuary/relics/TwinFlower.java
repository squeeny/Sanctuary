package Sanctuary.relics;

import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.HappyFlower;

import static Sanctuary.Sanctuary.makeRelicOutlinePath;
import static Sanctuary.Sanctuary.makeRelicPath;

public class TwinFlower extends CustomRelic {

    public static final String ID = Sanctuary.makeID(TwinFlower.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TwinFlower.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TwinFlower.png"));


    public TwinFlower() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(HappyFlower.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                //AbstractRelic r = AbstractDungeon.player.relics.get(i);
                //r.img = ""
                if (AbstractDungeon.player.relics.get(i).relicId.equals(HappyFlower.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public void onEquip() {
        this.counter = 0;
    }


    @Override
    public void atTurnStart() {

        this.counter++;

        if (this.counter == 3) {

            this.counter = 0;

            flash();

            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new GainEnergyAction(2));

        }

    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(HappyFlower.ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}