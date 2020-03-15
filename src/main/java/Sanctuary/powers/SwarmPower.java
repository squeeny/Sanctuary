package Sanctuary.powers;

import Sanctuary.Sanctuary;

import Sanctuary.util.TextureLoader;
import Sanctuary.util.UC;
import Sanctuary.vfx.general.MenacingVFX;
import Sanctuary.vfx.general.StraightSwarmParticle;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.Random;

import static Sanctuary.Sanctuary.makePowerPath;

public class SwarmPower extends AbstractPower implements InvisiblePower {
    public static final String POWER_ID = Sanctuary.makeID("SwarmPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static Random rng = new Random();
    public static Color tintCol = Color.BLACK.cpy();
    private float particleTimer;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("empty84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("empty32.png"));

    public SwarmPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        updateDescription();
        isTurnBased = true;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {

    }

    @Override
    public void updateParticles() {
        this.particleTimer -= Gdx.graphics.getRawDeltaTime();

        if (this.particleTimer < 0.0F) {
            float xOff = ((owner.hb_w) * (float) rng.nextGaussian())*0.25f;
            if(MathUtils.randomBoolean()) {
                xOff = -xOff;
            }

            AbstractDungeon.effectList.add(new StraightSwarmParticle(owner.drawX + xOff, owner.drawY + MathUtils.random(owner.hb_h/2f), 75f));
            this.particleTimer = 0.5F;

        }
    }


    @SpirePatch(clz = AbstractMonster.class, method = "render")
    public static class ChangeColorOfAffectedMonsterPls {
        @SpirePrefixPatch
        public static void patch(AbstractMonster __instance, SpriteBatch sb) {
            if(__instance.hasPower(SwarmPower.POWER_ID)) {
                __instance.tint.color.mul(SwarmPower.tintCol);
            }
        }
    }


    @Override
    public void onInitialApplication()
    {
        super.onInitialApplication();
        this.owner.increaseMaxHp((int) (this.owner.maxHealth / 1.5), true);

        particleTimer = 0.4f;
    }

    @Override
    public void stackPower(int i) {
        super.stackPower(i);
        updateDescription();
    }

}
