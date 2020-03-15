package Sanctuary.relics;

import Sanctuary.powers.SwarmPower;
import Sanctuary.util.SanctuaryMessages;
import Sanctuary.vfx.general.ObtainRelicLater;
import basemod.abstracts.CustomBottleRelic;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import static Sanctuary.Sanctuary.*;
import static com.megacrit.cardcrawl.neow.NeowEvent.rng;

public class OrderPad extends CustomRelic implements ClickableRelic {

    public static final String ID = Sanctuary.makeID(OrderPad.class.getSimpleName());

    public AbstractRelic shop;

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("OrderPad.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("OrderPad.png"));

    public OrderPad() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onRightClick() {

        int gold = AbstractDungeon.player.gold;
        //shop = AbstractDungeon.returnRandomRelic(RelicTier.SHOP);
        if (gold <= 149) { return; }
        else { buyrelic(); }
    }

    public void buyrelic()
    {


        Random rng = AbstractDungeon.relicRng;
        ArrayList<AbstractRelic> tmp = new ArrayList<AbstractRelic>();
        for(AbstractRelic r : RelicLibrary.uncommonList) {
            if(!AbstractDungeon.player.hasRelic(r.relicId)) {
                tmp.add(r.makeCopy());
            }
        }

        if(!tmp.isEmpty()) {
            AbstractDungeon.player.gold -= 150;
            if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                AbstractDungeon.effectList.add(new RainingGoldEffect(150, true));
                AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
            }
            AbstractRelic shop = tmp.get(rng.random(tmp.size() - 1));
            AbstractDungeon.effectsQueue.add(0, new ObtainRelicLater(shop));
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}



