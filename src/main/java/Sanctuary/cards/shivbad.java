package Sanctuary.cards;

import Sanctuary.Sanctuary;
import Sanctuary.actions.utility.ExhaustHandAction;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static Sanctuary.Sanctuary.makeCardPath;

public class shivbad extends SanctuaryCard {

    public static final String ID = Sanctuary.makeID(shivbad.class.getSimpleName());
    public static final String IMG = makeCardPath("05.png");
    private static final AbstractCard.CardRarity RARITY = CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = CardTarget.NONE;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final int COST = 0;
    private static final int DMG = 4;
    private static final int UPG_DMG = -1;

    public shivbad() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DMG;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            upgradeName();
            upgradeMagicNumber(UPG_DMG);
            initializeDescription();
        }

    }
}