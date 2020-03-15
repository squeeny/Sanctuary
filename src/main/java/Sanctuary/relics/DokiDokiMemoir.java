package Sanctuary.relics;

// I apologize in advance for anybody who actually wants to read this
// (lol)

import Sanctuary.actions.utility.GlitchAction;
import Sanctuary.actions.utility.MessageCaller;
import Sanctuary.cards.*;
import Sanctuary.powers.SwarmPower;
import Sanctuary.util.UC;
import Sanctuary.vfx.general.dokiVFX;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Sanctuary.Sanctuary.*;
import static Sanctuary.util.UC.atb;


import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.apache.logging.log4j.LogManager;

import java.text.SimpleDateFormat;
import java.util.*;


public class DokiDokiMemoir extends CustomRelic {

    public Random random = new Random();
    public static final String ID = Sanctuary.makeID(DokiDokiMemoir.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DokiDokiMemoir.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DokiDokiMemoir.png"));
    public int SAY = 0;
    public int FT = 0;

    public DokiDokiMemoir() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
        this.counter = 0;
    }

    public void onEquip() { AbstractDungeon.player.energy.energyMaster++; }

    public void onUnequip() { AbstractDungeon.player.energy.energyMaster--; }

    public void atTurnStartPostDraw() {

        org.apache.logging.log4j.Logger logger = LogManager.getLogger(Sanctuary.class.getName());
        this.counter++;
        if (this.counter == 3) {
            this.counter = 0;
            dokipoem();
        }
    }
    public void dokipoem(){

        int MON = 0;
        int NAT = 0;
        int YUR = 0;
        int COR = 0;
        int poem = AbstractDungeon.cardRandomRng.random(0, 7);

        /*

                                                         dP
                                                        dP       .888888888b
                                                       dP        88888P  Y8888
                                                       8        888888888888888.
                                                      d8      d88888888888888888b
                                                      88     d8888888888888888888888.
                                                      Y8    d888888888888888888888888b
                                                       8  d888888888888888888888888888P od88.
                                                        888888888888888888888888P' .ood88888b
                                          d8888888bo.   88888888888888888888P'.ood"oooooooo88
                                          888888888P'''' 'Y88888888888888P o""'od888888888888
                                          """"""'..d88888b..88888888888' o'd88888888888888888
                                        d888''''''Y88888888888888888' o8888888888888888888888b
                                     88888P.d88888b Y88888888888888. "888888888888888888888888
                                   8888888'  'Y8888b. '88888888888888b "8888888888888888888888
                               d888888P'       '888888b Y88888888888888."Y88888888888888888888 8.
                            d8888888'.....db.   '8888888 Y888888888888888 '8888888888888888888 88b
                         d88888888888888888888b. '8888888 Y888888888888888  888888888888888888 888b
                     .d88888888888888888888888888 Y8888888 Y888888888888888 8..''.d88888888888 8P88b
                   d888888888888888888888888888888 88888888 888888888888888b "8888888888888888 88 Y8b
                 d888888888888888888888888888888888 8888888. 8888888888888888 b.'8888888888888 888 Y88
                P    .d8888888888888888888888888888b 8888888 Y888888888888888 88888.'888888888 8888 `88
               d    d8888888888888888888888888888888.'8888888 888888888888888. 888888bo.''"'88 8888  `88.
               8   d888888888888888888888888888888888 8888888. 888888888888888 "88888888888888 88888  `88.
               8  d8888888888888888888888888888888888. 8888888 Y888888888888888 Y8888888888888 88888    Y8b
               8 .88888888888888888888888888888888888b  8888888 888888888888888   Y88888888888 888888    Y8b
               8 8888888888888888888888888888888888888. 8888888 Y88888888888888b 8b Y88888888' 888888     Y8b
               8888888888888888888888888888888888888888  8888888 888888888888888 88b . "8888 .88888888     Y8b
               8888888888888888888888888888888888888888. 8888888  88888888888888 888 888 '" .888888888      Y8b
               8888888888888888888888888888888888888888b  8888888 88888888888888  88.'88888888888888888      Y8b
              d888888888888888888888888888888888888888888 8888888 Y88888888888888 888 88888888888888888b      '8b
             d8888888888888888888888888888888888888888888 Y8888888  8888888888888. 88 888888888888888888       '8b
             88888888888888888888888888888888888888888888b 8888888b 88888888888888 88b 88888888888888888b       '8.
             888888888888888888888888888888888888888888888 88888888 Y8888888888888 888 888888888888888888        '8.
            d888888888888888888888888888888888888888888888 88888888b 8888888888888 888 8888888888888888888        '8.
            8 88888888888888888888888888P88888888888888888 888888888 Y888888888888 888b 888888888888888888b        '8
            8 88888888888888888888888888 .Y888888888888888b 88888888. 888888888888  888 8888888888888888888         8b
           88 88888888888888888888888888 8. 888888888888888 888888888 888888888888  888 8888888888888888888b         8
           8P.88888888888888888888888888b 8b Y8888888888888 8888888'8. 88888888888  888 88888888888888888888         '8
           8 '8888888888Y8888888888888888 888 '888888888888b 888888. 8 Y8888888888  888 88888888888888888888b         8.
           8  88888888888 888888888888888b .d88  '88. '88888 8888888 8b 888888888   888 888888888888888888888         '8
           8  88888888888 .'88888888888888b 88888b. ''b '888 8888888 88 Y88888888   Y88 8888888888888888888888         8
           8  88888888888 88.'8888888888888b '8888888b..     8888888 Y8  Y888888P    88b 8888888888888888888888        Y8
           8  88888888888 888b. '888888888888b.    d8888b.'8 88888888 8   888888     888  888888888888888888888         8
           8  88888888888 Y88888b. 8888  .....dP  .888"Y8b   Y8888888 Y8   8888      888   888888888888888888888        8b
           8  Y8888888888b 88888888. .'b Y88888   d88   88  . 8888888  8   888       888   '88888888888888888888        88
           8   888888 ' .'Y 88888888 88b.  '8888  Y888bd88 .8 8888888  8b  88P       888.   '88888888888888888888       88.
           8   888888 b 8P d88.'8888888888888888b. 888888'd88 88888888 88  88        888b    Y8888888888888888888       888
           8   Y88888 Y8'  888"b 88888888888888888b."""'d8888 88888888 88 d8         8888     Y8888888888888888888      888
           Yb   88888b 8   88'  8 888888888888888888888PdPdPd. 8888888 Y8 P          8888      Y8888888888888888888      88
            8   Y88888 '.  '88bd8 888888888888888888PdPdPdPdPd 8888888  8d           8888        888888888888888888      88b
            8    88888b 'b  Y88".d88888888888888888PdPdPdPdPdP 8888888  8            8888         888888888888888888     888
                 Y8 888  8888888888888888888888888PdPdPdPdPdPd 8888888  8            8888          88888888888888888.    888
                  Y8 Y88  888dPd888888888888888888888PdPdPd888 8888888  8            Y888          '88888888888888888    888
                   'b  Y8 'PdPdPd88888888888888888888888888888 8888888b 8             888           88888888888888888b   888
                    ' 8888 dPdPdPd8888888888888888888888888888 Y8888888 8             888            88888888888888888   888
                     d8888b dPdPd888.'888888888888888888888888b 8888888 8             888b           888888888888888888  888
                     888888b dPd8888888888888888888888888888888 8888888 8             8888           '88888888888888888b 888
                    d8888888b 888888888888888888888888888888888 888888888             Y888            '88888888888888888 Y88
                   dP 8888888 Y888888888888888P'.888888888888  d888888888              888b            888888888888888888888
                  d8 d8888888P Y8888888b.'""'.d888888888888P . 8888888888              8888            888888888888888888888
                 dP  88888888   Y8888888888888888888888888' .8 888888888'              88888           888888888888888888888
                d8  88888888'    Y88888888888888888888888'.d88 888888888               Y88888          '88888888888888888888
               d8  d88888888       Y8888888888888888888' d8888 888888888     .d8''..    888888          8888888888888888888P
              dP  d88888888'         Y888888888888888'.d888888 88888888'88. d8'.d88888b. 88888b          8888888888888888888
             dP  .88888888P            ''8888888888P '88888888 8888888 d88'd8'd888888888b.'88888         '8888888888888888888
            .P  .888888888             8b. 'Y8888P    '8888888 8888888 88 d8'.888888888888b 88888          888888888888888888b
           .8   888888888P            .8888            '888888 8888888 P d8' 888888888888888.'8888b         '88888888888888888.
           88  d888888888             88888           db '88P  8888888   88' 8888888888888888 88888b          88888888888888888
           8P d888888888             88888'          .888 ' d8 888888 .'d88 88888888888888888b 888888         Y88888888888888888
          d8 d8888888888            d8888'           888 .d888 888888 8 888 888888888888888888. 888888         Y8888888888888888b
          888888888888P            88888'           8P'P  8888 888888 8 88' 888888888888888888b Y888888         '8888888888888888b
          88888888888P           88888'           d'8   8 Y888 88888P 8 88 888888888888888888888 Y888888         Y8888888888888888.
          8888888888P           888P         ..d888'8   88 888 88888 d'.88 888888888888888888888. 'Y88888b         8888888888888888b
         d88888888P           d888      .d88888888' 8   88.Y8' 88888 8 d88 8888888888888888888888   Y888888         8888888888888888b
        d88888888P           d88      d8888888888P. P   88b 8 888888 8 Y88 88888888888888888888888    '88888.        8888888888888888b
       d8888888P            d8"      d8888888888P.d .   888 8 888888 8b 88 88888888888888888888888b     '8888        Y88888888888888888                                                    .d8888888b...d888888888b..
       888888P'            dP       88888888888P.8' d   88P 8 888888 88 88 888888888888888888888888.      8888        Y88888888888888888b                                             ..d88888888888888888888888b   'Y88.
      d88888"            .8'        8888888888P.88 d8P     .8 88888 .88 Y8b 888888888888888888888888       Y88b        888888888888888888b                                       .d8888888888888888888888888888888b     'Y8.
      88888             .8         88888888888 d88 '      ' . 88888 .88 '88.'88888888888888888888888b       888b        8888888888888888888.                                 .d8888888888888888888888888888888888888.      Y8b
     d8888              8'         8888888888'.88' .'   db  ' 88888 d8' d888 '88888888888888888888888        888         8888888888888888888b                             .d88888888888888888888888888888888888888888b      '88
     8888"             88          888888888'd''  P     '88   88888   .88888. 888888888888888888888888       8888        '88P888888888888888888b.                   ..d888888888888888888888888888888888888888888888888.     '88.
    d8888             88           88888888'.P.d8    8   88P  88888  88888888b 88888888888888888888888.      Y888          88  Y888888888888888888888888b..d888888888888888888888888888888888888888888888888888888888888b     'Y8.
    8888             88P           88888888  .d'   .88   '8 8 88888 '888888.'8b 88888888888888888888888       8888          Y8b 'Y88888888888888888888888888888888888888888888888888P'''   'Y8888888888888888888888888888b      Y8
   .8888            d88            '888888   ' .  d88P     88 88888 '88888888.'' 88888888888888888888888      '888.          Y8.  '8888888888888888888888888888888888888888888888P'             Y8888888888888888888888888.      88
   88888            888             88888'   .'  d888  .d  8' 88888 b '88888888b  8888888888888888888888.      8888           '8b    '888888888888888888888888888888888888888P''                  'Y88888888888888888888888.     Y8.
   88888            888             '8P'  .b '  '888P d88  ' 888888 88 d888'88888b '888888888888888888888      Y888             Y8b.     'Y88888888888888888888888888888P"'                         'Y8888888888888888888888      88
   8888P           d888b               d'.d88' b.''. .8. d   888888 ' d88888b.'''Y8b 888888888888888888888      888               Y88888888888888888888888""""""''                                    Y888888888888888888888b     88
  d8888            88888              d8 8888 Y8bd8' '.8 8. d888888b d88888888888b.'  `8888888888888888888      Y88                  'Y88888888888P'                                                    888888888888888888888.     8b
  88888            888888             Y8 8888  Y88P .88 d88'88888888 8888888888888888b .`888888888888888888      88                       '"""'                                                          Y88888888888888888888     88
  88888            8888888             'd888 db '.d 88P.888 88888888 88888888888888888b 8b``8888888888888888     Y8                                                                                       Y8888888888888888888.    88
 d88888            '888888              888'.88bd8P 88 d888 88888888b 88888888888888888b 88b.`88888888888888      8                                                                                        Y8888888888888888888    88
 888888             Y88888              888 888888b 8' 8888 888888888 888888888888888888b 888b.`'888888888888     Y                                                                                         Y888888888888888888   d88
 888888b             88888            d 88' 888888b88 88888 888888888 Y88888888888888'888  88888b.`88888888888                                                                                               888888888888888888.  88'
 8888888              Y888           d8 88 8888888888 88888 888888888  88888888888888 888  88888888.``88888888                                                                                               Y888888888888888888 .88
 8888888.              888           88 88 8888888888 88888 8888888888 '888888888888'd888   888888888b.`'888888                                                                                               888888888888888888 888
 88888888              Y88          d88 88 8888888888 88888 8888888888b 88888888888'.8888 b  888888888888.`Y888                                                                                               Y88888888888888888d888
 Y8888888b              Y8          888 88 8888888888 88888 '8888888888b 8888888888 d8888 8b 88888888888888b.`88'b.                                                                                           '88888888888888888888
  88888888b              8b        d888 88 8888888888 88888b 88888888  8 '88888888 d88888888 88888888888888888b`8`8b                                                                                           8888888888888888888'
  888888888b             Y8        8888 88 Y888888888 88888'.'8888888 '   8888888 d888888888 .'888888888888888888888b                                                                                          888888888888888888'
  88888888888             "        8888.'8  888888888 8888'.b. 8888888 b.  88888 d8888888888 '8'8888888888888888888888                                                                                         888888888888888888b
   88888888888b                    '888b 8b `88888888 8888 d88.'888888 '8.  888 d888888888888 88 888888888888888888888                                                                                         8888888888888888888
    888888888888b..                 8888.'8b `8888888 888' 8888 8888888 88.  8 d8888888888888.`8.`88888888888888888888                                                                                         8888888888888888888
    '88888888888b'''                 Y888 '8b `888888 888 d8888. 888888 Y88b  '888888888888888b Y8 `888888888888888888                                                                                        .8888888888888888888
     '88888888888b                    8888 Y8b 888888 888 888888 Y88888b 8P db '888888888888888b `8  `8888888888888888                                                                                        88888888888888888888
      '88.Y88888888                    8888 Y8.`88888 888 888888b 8.8888b d8888. '888888888888888b      `88888888888888                                                                    .                 .8888888888888888888'
        88. 88888888b                   'Y88 Y8.`8888 Y88 8888888 8b 8888 '88888b..'88888888888888888  .   `888888888888                                                                   8                 88888888888888888888
         Y8b  Y8888888b                    '8 '8.`888b 88 .''''''  8  8888 888888888888888888888888888 88888. `8888888888                                                                  8               .d88888888888888888888
           Yb    'Y88888b                      Y8.`888 88.'88888888. '. 88.'88888888888888888888888888 `888888b `88888888                                                                  88.            d888888888888888888888P
                                                Y8b.`8.`88 888888888. 'b '8.'8888888888888888888888888 . 88888888b `888888                                                                  88.        .d88888888888888888888888'
                                                 'Y8.`8 88 8888888888b.'8b.'b '8888888888888888888888' 8 . `'888888b.. `888                                                                  Y88b. .d8888888888P8888888888888888
                                                  'Y8.`.`8 '88888888888bd88b..  ''Y8888888888888888'.8888 88b. `'Y888888. `                                                                   8888888888888888'd888888888888888'
                                                    'Y.  8. 8888888888888888888b..d888888888888888'.88888 8888888b.. `Y888888                                                                  Y888888888888'.d8888888888888888
                                                      'Y. 8 888888888888888888888888888888888888P.88888'. '88888  Y8888b..`Y88                                                                   '"Y8888P'  d88888888888888888P
                                                        8.' Y888888888888888888888888888888888P.8888888'db '8888b .. 888888b.                                                                             .d888888888888888888
                                                         ' ''8888888888888888888888888888888P.d88888888 88  88888.'8.'888888888b.                                                                        d8888888888888888888
                                                           ' 888888888888888888888888888888'd888888888P.88 .'8888b 88 88888888888b.                                                  `.               .d8888888888888888888Y"
                                                            ' 8888888888888888888888888888d88888888888 888 b 88888 88 88888888888888.                                                  Y8b.        .d888888888888888888888 P
                                                             ''888888888888888888888888888888888888888 888 8 88888 88 d88888888888888. 8b.                                               8888888888888888888888888888888'.P
                                                                88888888888888888888888888888888888888d888d888888' 88 888888888888888b '888b.                                              Y88888888888888888888888888P .P
                                                                 '888'Y888888888888888888888888888888888888888888 .88 8888888888888888  'Y888b.                                              'Y888888888888888888888' .8'
                                                                   '8. Y8888888888888888888888888888888888888888P d8'.88888888888888888 8b.''888b                                                 '"""""""Y88888P'  .8P
                                                                     '8b88888888888888888888888888888888888888888b.. d88888888888888888 '888b.''888.                                                             .d8P
                                                                       '88888888888888888888888888888888888888888888888888888888888888' b.'Y888b.''88.                                                       .d88P
                                                                          'b.'88888888888888888888888888888888888888888888888888888888  888b. '888b '8b                                          '..oooood88P'
                                                                             b88888888888888888888888888888888888888888888888888888888  '88888b '888b. 'b                                            '''''
                                                                              8888888888888888888888888888888888888888888888888888888'  b.'88888b.''88b. Y.
                                                                                ' 'Y8888888888888888888888888888888888888888888888888  .888b '88888b '888b '
                                                                                b. .888888888888888888888888888888888888888888888888' d. Y8888 '88888b '888b
                                                                                '888888888888888888888888888888888888888888888888888  88b '88888 '88888b '8888b.
                                                                                  . .. 'Y888888888888888888888888888888888888888888' d888b '88888b.'Y8888. '88888
                                                                                  8 8888b. '88888888888888888888888888888888888888'  88888b  Y88888b. 88888b '88888.
                                                                                 .8 888888888b 'Y88888888888888888888888888888888'  '8888888  Y8888888. Y8888b Y88888.
                                                                                 dP  ''''Y8888888. '8888888888888888888888888888'    '8888888  Y8888888b '88888 '888888
                                                                                 ' db.       'Y88888b.'Y88888888888888888888888P   .  '8888888. '88888888. '8888b Y88888b.
                                                                                  .8888888   .   'Y88888b8888888888888888888888   .db  '8888888. '888888888.'88888b Y88888b
                                                                                .d888888P  .d88b.   'Y888888888888888888888888'   d88b  Y8888888. '888888888b '88888. 888888b
                                                                             d88888888P  .d88888888b     'Y8888888888888888888   d8888b  88888888b '8888888888 '88888b Y888888.
                                                                           d888888P''  .d888888888888' d.    '888888888888888'  d888888b '88888888b '8888888888b 888888. Y888888
                                                                        d8888P'''.d' d88888888888888' d8888b.    '8888888888'  'Y8888888b '888888888 '88888888888. 88888b Y888888.
                                                                    .d88P' .d8888' .d888888888888888 d888888888b.    '88888'  d8 Y8888888b 8888888888 '88888888888b '88888. Y88888
                                                                    ...d888888' .d88888888888888888 .888888888888 8b..   `'   '8b 88888888b 8888888888 '888888888888. '8888b Y88888
                                                               .d88888888888' d8888888888888888888 .d888888888888 888888b..  d 88.'88888888. 8888888888 '888888888888b '88888 '88888
                                                          .d88888888888888' d88888888888888888888' 88888888P'd888 888888888888.'88 888888888. 8888888888 '888888888888b '88888b 88888
                                                      .d888888888888888P .d8888888888888888888888 d8888888'd88888 888888888888b 88. 888888888 '8888888888  Y88888888888b '888888 88888
                                                 .d888888888888888888' .888888888888888888888888 d888888'd8888888 8888888888888 888 8888888888 '8888888888  Y888888888888. '88888 88888
                                              d8888888888888888888P'.d8888888888888888888888888'.8888'.d888888888 8888888888888 888b'8888888888 88888888888. Y888888888888. '8888b Y888b
                                          ''''Y8888888888888888P'.d888888888888888888888888888'.888'.d88888888888 8888888888888 Y888.'888888888b Y8888888888. '888888888888b '88888 Y888
                                          '88b.  'Y888888888' .d88888888888888888888888888888'.P'.d8888888888888P 8888888888888. 8888 8888888888b 88888888888. '888888888888b '88888 '88
                                           '88888b. 'Y888'  'Y888888888888888888888888888888' '.d888888888888888  88888888888888 8888 '8888888888. 88888888888. '888888888888b '8888b YP
                                             888888b. '888 'o.  'Y8888888888888888888888888' .88888888888888888'  88888888888888 8888b 88888888888.'88888888888b '888888888888b '8888b
                                              88888888b  Y8 Y888b  'Y888888888888888888888'.d888888888888888888 . 88888888Pd8888 8888b 888888888888 Y888888888888 '888888888888b '88888
                                              '888888888b  8 '8888'  '888888888888888888'.d8888888888888888888'.d 8888888'd88888 Y8888.'88888888888b 8888888888888 '888888888888b '88888
                                               'Y8888888888 ' 88P'.db. Y88888888888888P d88888888888888888888' 88 888888 d888888 '88888 888888888888. 8888888888888 '888888888888b '8888
                                              '8. Y888888888b.d'.d8888b '888888888888'.8888888888888888888888 d88 8888P d888888'  88888 '888888888888. 8888888888888 '888888888888b Y888.
                                              . '. '88888'.888b88888888b '888888888'.d8888888888888888888888'.888 888 d88888888   88888b '888888888888  8888888888888 '888888888888b 8888
                                              '8. '. Y8'.888888888888888b  888888P d88888888888888888888888'.8888 8'.8888888888 . 888888  888888888888b '8888888888888 '888888888888. 888
                                                8b.    d88888888888888888b  8888'.d888888888888888888888888.88888 '.8888888888P 8 888888  8888888888888b '8888888888888b 88888888888b Y88
                                                 8888888888888888888888888b '88 'Y888888888888888888888888'd8888' d88888888888' 8.'88888b 888888P8888888. '8888888888888b Y8888888888  8P
                                                  '8888888888888888888888P . '8 b.  'Y8888888888888888888'd8888' d888888888888  8b 888888 '88888 88888888  88888888888888b Y888888888  ''
                                                   '88888888888888888888P d8b ' 88' b.'Y88888888888888888.888' d88888888888888 .88 888888. 88888 88888888b '88888888888888. 888888888
                                                     Y88888888888888888' d8888  '   88b Y888888888888888'd8'.d888888888888888' 888b888888b 88888 888888888. '88888888888888b '8888888
                                                       Y8888888888888P .d888888.d88 8888.'Y888888888888'P'.d88888888888888888  88888888888 '8888 8888888888. '888888888888888 '888888
                                                         'Y888888888P d888888888888 88888b Y8888888888' d8888888888888888888P .88888888888  888P 88888888888. '888888888888888 '88888
                                                       d8b Y8888888' d8888888888888 888888b Y8888888'.d888888888888888888888' 888888888888b 888 d888888888888. '88888888888888. Y8888
                                                      'Y888b 'Y888' d88888888888888 88888888 Y888P .d88888888888888888888888 .8888888888888 888 88888888888888b 888888888888888. Y88P
                                                        . ''8b.'P' 888888888888888' 888888888.'Y. '888888888888888888888888P 88888888888888 888 888888888888888 '888888888888888  Y8
                                                         '8b..    d888888888888888  8888888888.'Y  '88888888888888888888888' 88888888888888 '88 888888888888888b '88888888888888  'P
                                                           'Y88888888888888888888'  88888888888.'b . Y888888888888888888888 .88888888888888. Y8 8888888888888888b '888888888888P
                                                               'Y888888888888888P 8 Y88888888888.'  b '8888888888888888888P 8888888888888888 '8 88888888888888888  Y88888888888
                                                                    'Y8888888888 d8b 888888888888.  'b '888888888888888888 d8888888888888888  '.88888888888888888b  8888888888P
                                                                      .. Y88888P 888 88888888888888b 'b  8888888888888888P 88888888888888888  88888888888888888888b Y888888888'
                                                                    ''Y88b. 'Y8'.888 8888888888888888. b  888888888888888 .88888888888888888  888888888888888888888b Y88888888'
                                                                         ''Yb.  .888 88888888888888888. '  '888888888888P d88888888888888888  Y888888888888888888888b Y888888P
                                                                         'Y.....d888 888888888888888888b .b  Y8888888888 .888888888888888888   8888888888888888888888b Y88888
                                                                            Y8888888 88888888888888888888888b '88888888P d888888888888888888. d88888888888888888888888' Y888P
                                                                                 ''' 8888888888888888888888888b '88888P .88888888888888888888 88888888888888888888888'   Y88'
                                                                                     888888888888888888888888888  '888' d8888888888888888888' 888888888888888888888P      Y
                                                                                     888888888888888888888888888 b. 88 ''Y888888888888888888'.88888888888888888888P
                                                                                     888888888888888888888888888 88b '. ..  'Y8888888888888P 8888888888888888888P'
                                                                                     888888888888888888888888888.'888b.' Y88 o. 'Y888888888' 88888888888888888P'
                                                                                     8888888888888888888888888888 88888b   ' 888b. 'Y888888  8888888888888888P
                                                                                     8888888888888888888888888888 88888888b d888888b  '8888 d8P'''''''''YP''
                                                                                     Y888888888888888888888888888 88888888888888888888b. ''   d8888b  d
                                                                                      888888888888888888888888888 Y88888888888888888888888b... '....d88
                                                                                      888888888888888888888888888b 88888888888888888888888888888888888'
                                                                                      8888888888888888888888888888 88888888888888888888888888888888888
                                                                                      8888888888888888888888888888 Y8888888888888888888888888888888888
                                                                                      Y888888888888888888888888888b 8888888888888888888888888888888888
                                                                                       8888888888888888888888888888 888888888888888888888888888888888'
                                                                                       8888888888888888888888888888 888888888888888888888888888888888
                                                                                       8888888888888888888888888888.'8888888888888888888888888888888P
         */
        if ((poem == 0 || poem == 1) && sanctuaryMessages[0]) {
            MON = 1;
            atb(new SFXAction("Sanctuary:Poem"));
            atb(new MessageCaller(0, 0));
            FT = 1;

        } else if (!sanctuaryMessages[0] && sanctuaryMessages[1]) {
            MON = 1;
            atb(new SFXAction("Sanctuary:Poem"));
            atb(new MessageCaller(1, 0));
            FT = 1;

        } else if (poem == 2 && sanctuaryMessages[poem]) {
            SAY = 1;
            atb(new SFXAction("Sanctuary:Poem"));
            atb(new MessageCaller(poem, 0));

        } else if (poem == 3 && sanctuaryMessages[poem]) {
            SAY = 1;
            atb(new SFXAction("Sanctuary:Poem"));
            atb(new MessageCaller(poem, 2));
            s_kill = true;
            try {
                Sanctuary.saveData();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        } else if ((poem == 4 || poem == 5) && sanctuaryMessages[4]) {
            YUR = 1;
            atb(new SFXAction("Sanctuary:Poem"));
            atb(new MessageCaller(4, 0));

        } else if (!sanctuaryMessages[4] && sanctuaryMessages[5]) {
            YUR = 1;
            atb(new SFXAction("Sanctuary:Poem"));
            atb(new MessageCaller(5, 1));

        } else if (poem == 6 && sanctuaryMessages[poem]) {
            NAT = 1;
            atb(new SFXAction("Sanctuary:Poem"));
            atb(new MessageCaller(poem, 0));
            FT = 1;

        } else if (poem == 7 && sanctuaryMessages[poem]) {
            NAT = 1;
            atb(new SFXAction("Sanctuary:Poem"));
            atb(new MessageCaller(poem, 2));

        } else {
            int dokiendcase = AbstractDungeon.cardRandomRng.random(0, 4);
            switch (dokiendcase){
                case 0:
                    MON = 1;
                    break;
                case 1:
                    if(s_kill) {
                        SAY = 0; }
                    else {
                        SAY = 1; }
                    break;
                case 2:
                    YUR = 1;
                    break;
                case 3:
                    NAT = 1;
                    break;
                case 4:
                    COR = 1;
                    break;
            }
        }
        if(MON == 1){
            if(random.nextInt(52) == 0) { AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(1))); }
            else { AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(0))); }
            if (FT == 1){ addToBot(new MakeTempCardInDrawPileAction(new osremove(), 1, true, true)); }
            else {
                if (AbstractDungeon.cardRandomRng.random(0, 1) == 0) {
                    addToBot(new MakeTempCardInDiscardAction(new osremovebad(), 1));
                } else {
                    addToBot(new MakeTempCardInDrawPileAction(new osremove(), 1, true, true));
                }
            }
            FT = 0;
        } else if(SAY == 1){
            if(random.nextInt(52) == 0) { AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(5))); }
            else { AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(4))); }
            SAY = 0;
            if (!s_kill) {
                addToBot(new MakeTempCardInDrawPileAction(new sunnyday(), 1, true, true));
            } else {
                addToBot(new MakeTempCardInDrawPileAction(new sunnydaybad(), 1, true, true));
            }
        } else if(YUR == 1){
            if(random.nextInt(52) == 0) { AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(7))); }
            else { AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(6))); }
            if (AbstractDungeon.cardRandomRng.random(0, 1) == 0) {
                addToBot(new MakeTempCardInHandAction(new Shiv(), 3, false));
            } else {
                addToBot(new MakeTempCardInHandAction(new shivbad(), 2, false));
            }
        } else if(NAT == 1){
            if(random.nextInt(52) == 0) { AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(3))); }
            else { AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(2))); }
            if (FT == 1){ addToBot(new MakeTempCardInDrawPileAction(new cupcake(), 1, true, true)); }
            else {
                if (AbstractDungeon.cardRandomRng.random(0, 1) == 0) {
                    addToBot(new MakeTempCardInDrawPileAction(new cupcake(), 1, true, true));
                } else {
                    addToBot(new MakeTempCardInDrawPileAction(new cupcakebad(), 1, true, true));
                }
            }
            FT = 0;
        }else if(COR == 1) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(8)));
            AbstractDungeon.actionManager.addToBottom(new GlitchAction());
        } else if(s_kill) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new dokiVFX(9)));
            UC.doPow(AbstractDungeon.player, new WeakPower(AbstractDungeon.player, 1, true));
            UC.doPow(AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player, 1, true));
            addToBot(new MakeTempCardInDrawPileAction(new sunnydaybad(), 1, true, true));
        }
    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
