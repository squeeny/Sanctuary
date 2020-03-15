package Sanctuary.actions.utility;

import Sanctuary.cards.SanctuaryCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;
import java.util.Random;

import static com.badlogic.gdx.utils.JsonValue.ValueType.array;

public class GlitchAction extends AbstractGameAction {

    public String nonunicode = "¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿĀāĂăĄąĆćĈĉĊċČčĎďĐđĒēĔĕĖėĘęĚěĜĝĞğĠġĢģĤĥĦħĨĩĪīĬĭĮįİıĲĳĴĵĶķĸĹĺĻļĽľĿŀŁłŃńŅņŇňŉŊŋŌōŎŏŐőŒœŔŕŖŗŘřŚśŜŝŞşŠšŢţŤťŦŧŨũŪūŬŭŮůŰűŲųŴŵŶŷŸŹźŻżŽž";

    public GlitchAction() {

        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;

    }

    public String glitchtext(int length) {
        Random random = new Random();
        int index;

        String output = "";

        for (int x = 0; x <= length; x = x + 1) {
            index = random.nextInt(nonunicode.length());
            output += nonunicode.charAt(index);

        }
        return output;
    }

    public void update() {

        if (this.duration == Settings.ACTION_DUR_FAST) {

            Iterator<AbstractCard> var5 = AbstractDungeon.player.exhaustPile.group.iterator();

            while (var5.hasNext()) {

                AbstractCard card = var5.next();

                card.name = glitchtext(card.name.length());
                if(card.upgraded)
                {
                    card.name += "+";
                }

                card.rawDescription = "Just Monika.";
                card.initializeDescription();
                card.update();
            }

            Iterator<AbstractCard> var4 = AbstractDungeon.player.drawPile.group.iterator();

            while (var4.hasNext()) {

                AbstractCard card = var4.next();

                card.name = glitchtext(card.name.length());
                if(card.upgraded)
                {
                    card.name += "+";
                }

                card.rawDescription = "Just Monika.";
                card.initializeDescription();
                card.update();

            }

            Iterator<AbstractCard> var3 = AbstractDungeon.player.discardPile.group.iterator();

            while (var3.hasNext()) {

                AbstractCard card = var3.next();

                card.name = glitchtext(card.name.length());
                if(card.upgraded)
                {
                    card.name += "+";
                }

                card.rawDescription = "Just Monika.";
                card.initializeDescription();
                card.update();

            }

            Iterator<AbstractCard> var2 = AbstractDungeon.player.hand.group.iterator();

            while (var2.hasNext()) {

                AbstractCard card = var2.next();

                card.name = glitchtext(card.name.length());
                if(card.upgraded)
                {
                    card.name += "+";
                }

                card.rawDescription = "Just Monika.";
                card.initializeDescription();
                card.update();

            }
            this.isDone = true;
        }

        tickDuration();

    }
}

