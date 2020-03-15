package Sanctuary.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import static Sanctuary.Sanctuary.*;


public class MetalUpa extends CustomRelic {

    public static final String ID = Sanctuary.makeID(MetalUpa.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MetalUpa.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MetalUpa.png"));


    public MetalUpa() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {

        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        AbstractPlayer p = AbstractDungeon.player;

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MetallicizePower(p, 3), 3, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
