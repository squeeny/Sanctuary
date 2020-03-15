package Sanctuary.actions.utility;

import Sanctuary.Sanctuary;
import Sanctuary.util.SanctuaryMessages;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.FtueTip;

import java.io.IOException;

public class MessageCaller extends AbstractGameAction {
    private float startingDuration;
    private DamageInfo info;
    private boolean isupgraded;
    private int code;
    private int state;


    public MessageCaller(int code,int state) {

        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.code = code;
        this.state = state;

    }


    public void update() {

        AbstractDungeon.ftue = new SanctuaryMessages(code,state);
        Sanctuary.sanctuaryMessages[code] = false;

        try {
            Sanctuary.saveData();
            this.isDone = true;;
        } catch (IOException e) {
            e.printStackTrace();
            this.isDone = true;
        }

    }
}