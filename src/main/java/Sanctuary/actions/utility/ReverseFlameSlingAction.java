package Sanctuary.actions.utility;

import Sanctuary.util.UC;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class ReverseFlameSlingAction extends AbstractGameAction {

    public ReverseFlameSlingAction(AbstractCreature m, AbstractPlayer p) {
        this.actionType = ActionType.WAIT;
    }

    public void update() {

        this.isDone = true;
    }
}