package Sanctuary.cards;

import Sanctuary.Sanctuary;
import Sanctuary.actions.utility.ExhaustHandAction;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static Sanctuary.Sanctuary.makeCardPath;

public class osremovebad extends SanctuaryCard {

    public static final String ID = Sanctuary.makeID(osremovebad.class.getSimpleName());
    public static final String IMG = makeCardPath("07.png");
    private static final AbstractCard.CardRarity RARITY = CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = CardTarget.NONE;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final int COST = -2;
    private static final int DRAW = 3;
    private static final int UPG_DRAW = 1;

    public osremovebad() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.name = "os." + glitchtext(6) + "()";
        magicNumber = baseMagicNumber = DRAW;
    }

    @Override
    public void triggerWhenDrawn() {

        addToBot(new ExhaustHandAction());
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(magicNumber));
        addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));


    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        /* 49 */     return false;
        /*    */   }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }

    @Override
    public void upgrade() {

    }
}
