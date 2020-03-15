package Sanctuary.relics;

import Sanctuary.powers.JojoPower;
import Sanctuary.powers.SwarmPower;
import Sanctuary.util.UC;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Sanctuary.Sanctuary.*;


import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.*;

public class MenacingAura extends CustomRelic {

    public static final String ID = Sanctuary.makeID(MenacingAura.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Menacing.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Menacing.png"));

    public MenacingAura() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {

        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;

        UC.doPow(p, new StrengthPower(p, 5), true);
        Iterator<AbstractMonster> var3 = (AbstractDungeon.getCurrRoom()).monsters.monsters.iterator();

        while (var3.hasNext()) {
            AbstractMonster mo = var3.next();
            UC.doPow(mo, new StrengthPower(mo, 3), true);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new JojoPower(mo, 1), 1, true, AbstractGameAction.AttackEffect.NONE));
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}