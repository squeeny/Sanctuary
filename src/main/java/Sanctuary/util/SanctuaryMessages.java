package Sanctuary.util;

import Sanctuary.Sanctuary;
import Sanctuary.actions.utility.MessageCaller;
import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.FtueTip;

import static Sanctuary.Sanctuary.makeID;
import static Sanctuary.util.UC.atb;


public class SanctuaryMessages extends FtueTip {

    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Sanctuary:SanctuaryMessages");
    public static final String[] txt = tutorialStrings.TEXT;
    public static final String[] LABEL = tutorialStrings.LABEL;
    public static final String[] POEM_NAME = {
            "Hole in the Wall.",
            "Dear Sunshine",
            "Bottles",
            "Ghost Under the Light",
            "Ghost Under the Light pt 2",
            "Eagles can Fly.",
            "Amy Likes Spiders"};

    private Color screen = Color.valueOf("1c262a00");
    private float x;
    private float x1;
    private float x2;
    private float x3;
    private float targetX;
    private float startX;
    private float scrollTimer = 0.0F;
    private int currentSlot = 0;

    private static String labeltxt;
    private static String txt1;
    private static String txt2;
    private static String txt3;

    private int code;
    private int state;
    private int closeScreen;


    public SanctuaryMessages(int code, int state) {

        // Parameters:

        // Code - Displays the Message.
        // Code 999 is for no text

        // States - determines how many pages
        // state 0 - Poem [ 1 page ],
        // state 1 - Poem with 2 Pages.
        // state 2 - Poem with 3 Pages.

        // Poem States:

        // Special poems 1-11

        this.state = state;
        this.code = code;
        int n;
        int cutoff;
        txt1 = "";
        txt2 = "";
        txt3 = "";

        switch (code) {

            // easter egg
            case -1:
                txt1 = txt[0];
                labeltxt = "Thank You";
                break;

            case 0:

                cutoff = 15;
                for(n = 1; n < cutoff; ++n)
                {
                    txt1 += txt[n];
                }
                labeltxt = POEM_NAME[0];
                this.closeScreen = -2;

                break;

            case 1:

                cutoff = 27;
                for(n = 15; n < cutoff; ++n)
                {
                    txt1 += txt[n];
                }
                labeltxt = POEM_NAME[0];
                this.closeScreen = -2;

                break;


            case 2:
                cutoff = 38;
                for(n = 27; n < cutoff; ++n)
                {
                    txt1 += txt[n];
                }
                labeltxt = POEM_NAME[1];
                this.closeScreen = -2;

                break;

            case 3:

                cutoff = 50;
                for(n = 38; n < cutoff; ++n)
                {
                    txt1 += txt[n];
                }

                cutoff = 60;
                for(n = 50; n < cutoff; ++n)
                {
                    txt2 += txt[n];
                }

                cutoff = 71;
                for(n = 60; n < cutoff; ++n)
                {
                    txt3 += txt[n];
                }

                labeltxt = POEM_NAME[2];
                this.closeScreen = -2;

                break;

            case 4:

                cutoff =79;
                for(n = 71; n < cutoff; ++n)
                {
                    txt1 += txt[n];
                }
                labeltxt = POEM_NAME[3];
                this.closeScreen = -2;

                break;

            case 5:

                cutoff = 89;
                for(n = 79; n < cutoff; ++n)
                {
                    txt1 += txt[n];
                }
                cutoff = 97;
                for(n = 89; n < cutoff; ++n)
                {
                    txt2 += txt[n];
                }
                labeltxt = POEM_NAME[4];
                this.closeScreen = -1;

                break;
            case 6:
                cutoff = 105;
                for(n = 97; n < cutoff; ++n)
                {
                    txt1 += txt[n];
                }
                labeltxt = POEM_NAME[5];
                this.closeScreen = -2;

                break;
            case 7:

                cutoff = 114;
                for(n = 105; n < cutoff; ++n)
                {
                    txt1 += txt[n];
                }

                cutoff = 124;
                for(n = 114; n < cutoff; ++n)
                {
                    txt2 += txt[n];
                }

                cutoff = 131;
                for(n = 124; n < cutoff; ++n)
                {
                    txt3 += txt[n];
                }
                labeltxt = POEM_NAME[6];
                this.closeScreen = -2;

                break;

            default:
                break;


        }


        AbstractDungeon.player.releaseCard();
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.FTUE;
        AbstractDungeon.overlayMenu.showBlackScreen();
        this.x = 0.0F;
        this.x1 = 250.0F * Settings.scale;
        this.x2 = this.x1 + Settings.WIDTH;
        this.x3 = this.x2 + Settings.WIDTH;
        AbstractDungeon.overlayMenu.proceedButton.show();
        AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
    }


    public void update() {
        AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
        if (this.currentSlot <= this.closeScreen) {
            AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[1]);
        }
        if (this.screen.a != 0.8F) {
            this.screen.a += Gdx.graphics.getDeltaTime();
            if (this.screen.a > 0.8F) {
                this.screen.a = 0.8F;
            }
        }


        if ((AbstractDungeon.overlayMenu.proceedButton.isHovered && InputHelper.justClickedLeft) || CInputActionSet.proceed
            .isJustPressed()) {

            CInputActionSet.proceed.unpress();

            switch(state) {

                // ONE PAGE PAGE
                case 0:

                    if (this.currentSlot >= this.closeScreen) {

                        CardCrawlGame.sound.play("DECK_CLOSE");
                        AbstractDungeon.closeCurrentScreen();
                        AbstractDungeon.overlayMenu.proceedButton.hide();
                        AbstractDungeon.effectList.clear();
                    }

                    break;

                default:

                    if (this.currentSlot <= this.closeScreen) {

                        CardCrawlGame.sound.play("DECK_CLOSE");
                        AbstractDungeon.closeCurrentScreen();
                        AbstractDungeon.overlayMenu.proceedButton.hide();
                        AbstractDungeon.effectList.clear();
                    }

                    break;
            }

            atb(new SFXAction("Sanctuary:Poem"));
            this.currentSlot--;
            this.startX = this.x;
            this.targetX = (this.currentSlot * Settings.WIDTH);
            this.scrollTimer = 0.3F;

            switch(state) {

                case 0:

                    if (this.currentSlot >= this.closeScreen) {
                        AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
                    }

                    break;

                default:

                    if (this.currentSlot <= this.closeScreen) {
                        AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[1]);
                    }

                    break;
            }

        }
        if (this.scrollTimer != 0.0F) {
            this.scrollTimer -= Gdx.graphics.getDeltaTime();
            if (this.scrollTimer < 0.0F) {
                this.scrollTimer = 0.0F;
            }
        }
        this.x = Interpolation.fade.apply(this.targetX, this.startX, this.scrollTimer / 0.3F);

    }


     public void render(SpriteBatch sb) {

        sb.setColor(this.screen);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);


        switch(state){

            case 0:

                FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt1, this.x + this.x1 * Settings.scale, Settings.HEIGHT / 2.0F -
                        FontHelper.getSmartHeight(FontHelper.panelNameFont, txt1, 1000.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 1000.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);
                break;

            case 1:

                FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt1, this.x + this.x1 + 380.0F * Settings.scale, Settings.HEIGHT / 2.0F -
                        FontHelper.getSmartHeight(FontHelper.panelNameFont, txt1, 1000.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 1000.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);

                FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt2, this.x + this.x2 + 380.0F * Settings.scale, Settings.HEIGHT / 2.0F -
                        FontHelper.getSmartHeight(FontHelper.panelNameFont, txt2, 1000.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 1000.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);

                break;

            case 2:

                FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt1, this.x + this.x1 * Settings.scale, Settings.HEIGHT / 2.0F -
                        FontHelper.getSmartHeight(FontHelper.panelNameFont, txt1, 1000.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 1000.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);

                FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt2, this.x + this.x2 + 380.0F * Settings.scale, Settings.HEIGHT / 2.0F -
                        FontHelper.getSmartHeight(FontHelper.panelNameFont, txt2, 1000.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 1000.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);

                FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt3, this.x + this.x3 + 380.0F * Settings.scale, Settings.HEIGHT / 2.0F -
                        FontHelper.getSmartHeight(FontHelper.panelNameFont, txt3, 1000.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 1000.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);

                break;

            default:
                break;
        }


        FontHelper.renderFontCenteredWidth(sb, FontHelper.panelNameFont, labeltxt, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 370.0F * Settings.scale, Settings.GOLD_COLOR);


        AbstractDungeon.overlayMenu.proceedButton.render(sb);
     }


}


