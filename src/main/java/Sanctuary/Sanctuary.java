package Sanctuary;

import Sanctuary.relics.*;

import basemod.*;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import Sanctuary.util.IDCheckDontTouchPls;
import Sanctuary.util.TextureLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class Sanctuary implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber,
        OnStartBattleSubscriber,
        StartActSubscriber,
        PostUpdateSubscriber,
        AddAudioSubscriber{

    public static final Logger logger = LogManager.getLogger(Sanctuary.class.getName());
    private static String modID;

    // Sanctuary Saved Data

    public static boolean[] sanctuaryMessages = new boolean[]{true, true, true, true, true, true, true, true};
    public static boolean s_kill = false;
    public static boolean GratitudeCheck = false;
    public static int HakureiDonations = 0;


    public static Properties SanctuaryDefaultSettings = new Properties();

    private static final String MODNAME = "Sanctuary";
    private static final String AUTHOR = "squeeny";
    private static final String DESCRIPTION = "Content mod!";

    public static final String BADGE_IMAGE = "SanctuaryResources/images/Badge.png";

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }

    public static String makeEffectPath(String resourcePath) {
        return getModID() + "Resources/images/effects/" + resourcePath;
    }

    public static String makeAudioPath(String resourcePath) {
        return getModID() + "Resources/audio/" + resourcePath;
    }

    public Sanctuary() {
        logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        setModID("Sanctuary");
        logger.info("Done subscribing");

        logger.info("Done adding mod settings");
    }

    public static void setModID(String ID) {
        Gson coolG = new Gson();
        InputStream in = Sanctuary.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }

    public static String getModID() {
        return modID;
    }

    private static void pathCheck() {
        Gson coolG = new Gson();
        InputStream in = Sanctuary.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = Sanctuary.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }
            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Sanctuary  =========================");
        Sanctuary sanctuary = new Sanctuary();
        logger.info("========================= a fleeting dream =========================");

        try {

            int i;
            int j;
            logger.info("Setting default values.");
            SanctuaryDefaultSettings.setProperty("GratitudeCheck", "false");
            SanctuaryDefaultSettings.setProperty("s_kill", "false");
            SanctuaryDefaultSettings.setProperty("HakureiDonations", "0");

            for (i = 0; i < sanctuaryMessages.length; i++) {
                SanctuaryDefaultSettings.setProperty("sanctuaryMessages" + i, "true");
            }

            SpireConfig config = new SpireConfig("sanctuary", "SanctuaryConfig", SanctuaryDefaultSettings);

            GratitudeCheck = config.getBool("GratitudeCheck");
            s_kill = config.getBool("s_kill");
            HakureiDonations = config.getInt("HakureiDonations");


            for (j = 0; j < sanctuaryMessages.length; j++) {
                sanctuaryMessages[j] = config.getBool("sanctuaryMessages" + j);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void easteregg_7265696e610a() {

        String x = CardCrawlGame.playerName;

        BaseMod.logger.info(x);

        if (x.equals("reina")) {

            GratitudeCheck = true;
            BaseMod.logger.info("Gratitude Check Successful.");

            try {
                Sanctuary.saveData();
            } catch (Exception e) {
                e.printStackTrace();
            }

            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), new LetterOfGratitude());

        }
    }


    @Override
    public void receivePostInitialize() {

        logger.info("Loading badge image and mod options");
        try {
            CreatePanel();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Sanctuary.saveData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        // 7265696e610a
        BaseMod.addRelic(new LetterOfGratitude(), RelicType.SHARED);

        // ddlc
        BaseMod.addRelic(new DokiDokiMemoir(), RelicType.SHARED);

        // steins;gate
        BaseMod.addRelic(new MetalUpa(), RelicType.SHARED);

        // spiral knights
        //BaseMod.addRelic(new TheSwarm(), RelicType.SHARED);

        // touhou
        BaseMod.addRelic(new LunarClock(), RelicType.SHARED);

        // shield hero
        BaseMod.addRelic(new DoubleEdgedShield(), RelicType.SHARED);

        // jojo
        BaseMod.addRelic(new MenacingAura(), RelicType.SHARED);





        // oneshot
        BaseMod.addRelic(new TheSun(), RelicType.SHARED);

        // other
        BaseMod.addRelic(new TimewalkingPendant(), RelicType.SHARED);
        BaseMod.addRelic(new OrderPad(), RelicType.SHARED);


        // Defect
        BaseMod.addRelic(new RainbowDark(), RelicType.BLUE);
        BaseMod.addRelic(new BurnerInserter(), RelicType.BLUE);

        // Upgrades:
        BaseMod.addRelic(new TwinFlower(), RelicType.SHARED);
        BaseMod.addRelic(new Adamantium(), RelicType.SHARED);

        logger.info("Done adding relics!");
    }

    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        pathCheck();
        logger.info("Add variabls");

        logger.info("Adding cards");
        new AutoAdd(modID)
                .packageFilter("Sanctuary.cards")
                .setDefaultSeen(true)
                .cards();

        logger.info("Done adding cards!");
    }

    @Override
    public void receiveEditStrings() {

        logger.info("Beginning to edit strings for mod with ID: " + getModID());

        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/Sanctuary-Card-Strings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/Sanctuary-Power-Strings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/Sanctuary-Relic-Strings.json");

        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/Sanctuary-Event-Strings.json");

        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/Sanctuary-Orb-Strings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class,
                getModID() + "Resources/localization/eng/Sanctuary-Ui-Strings.json");

        BaseMod.loadCustomStringsFile(TutorialStrings.class,
                getModID() + "Resources/localization/eng/Sanctuary-Tutorial-Strings.json");

        logger.info("Strings are done.");
    }

    @Override
    public void receiveEditKeywords() {

        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Sanctuary-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receivePostUpdate() {
        if (AbstractDungeon.player == null)
            return;

        if (AbstractDungeon.player.hasRelic(LunarClock.ID) && (AbstractDungeon.player.getRelic(LunarClock.ID)).counter == -2) {
            LunarClock.TimeUp();
        }

    }


    public void receiveOnBattleStart(AbstractRoom room) {
        if(!GratitudeCheck) {
            easteregg_7265696e610a();
        }

    }


    private void CreatePanel() throws IOException {
        SpireConfig spireConfig = new SpireConfig("sanctuary", "SanctuaryConfig");
        ModPanel settingsPanel = new ModPanel();
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("CreatePanelMokou"));
        String[] TEXT = uiStrings.TEXT;
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);


    }

    public static void saveData() throws IOException {
        logger.info("Saving a lot of data....");
        SpireConfig config = new SpireConfig("sanctuary", "SanctuaryConfig");
        int i;

        config.setBool("GratitudeCheck", GratitudeCheck);
        config.setBool("s_kill", s_kill);
        config.setInt("HakureiDonations", HakureiDonations);


        for (i = 0; i < sanctuaryMessages.length; i++) {
            config.setBool("sanctuaryMessages" + i, sanctuaryMessages[i]);
        }

        config.save();
        logger.info("Done!");
    }


    @Override
    public void receiveStartAct() {

    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio("Sanctuary:Poem", makeAudioPath("pageflip.ogg") );

    }
}
