package Sanctuary.relics;

import Sanctuary.actions.utility.ExhaustHandAction;
import Sanctuary.powers.SwarmPower;
import Sanctuary.util.UC;
import Sanctuary.vfx.general.LunarClockLunarDial;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Sanctuary.Sanctuary.*;


import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;

import java.util.*;


public class LunarClock extends CustomRelic {

    public static final String ID = Sanctuary.makeID(LunarClock.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("LunarClock.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LunarClock.png"));

    public LunarClock() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
        this.counter = -1;
    }

    @Override
    public void wasHPLost(int damageAmount) {

        if (damageAmount > 0 && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {

            logger.info("damage taken: " + damageAmount);
            flash();
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            int x = damageAmount * 3;
            logger.info(x + " seconds deducted");
            CardCrawlGame.playtime += x;
        }

    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS) {
            flash();
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            logger.info("damage dealt: " + damageAmount);
            CardCrawlGame.playtime -= damageAmount / 2;
            logger.info("time deducted: " + damageAmount / 2);

        }
        return damageAmount;
    }

    @Override
    public void atBattleStart() {

        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GRAY, true));
        AbstractDungeon.topLevelEffectsQueue.add(new LunarClockLunarDial());

        AbstractPlayer p = AbstractDungeon.player;


    }
    


    public static void TimeUp() {

        if (CardCrawlGame.playtime >= 0.0F && !AbstractDungeon.player.isDead) {
            AbstractDungeon.player.isDead = true;
            AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());
        }

    }


    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
        AbstractDungeon.player.energy.energyMaster++;
        CardCrawlGame.playtime = -120F;
        this.counter = -2;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
        AbstractDungeon.player.energy.energyMaster--;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}