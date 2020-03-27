package Sanctuary.relics;

import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import Sanctuary.vfx.general.LunarClockLunarDial;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

import static Sanctuary.Sanctuary.*;
import static Sanctuary.Sanctuary.logger;

public class DoubleEdgedShield extends CustomRelic {

    public static final String ID = Sanctuary.makeID(DoubleEdgedShield.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Shield.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Shield.png"));

    public DoubleEdgedShield() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }


    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 7) {
            flash();
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            return 7;
        }
        else if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount < 7 && damageAmount != 0) {
            flash();
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            return 7;
        }
        return damageAmount;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
